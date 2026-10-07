#include "TreeBuilder.h"
#include <cctype>

TreeBuilder::TreeBuilder(std::vector<std::string> names) : ruleNames(std::move(names)) {
    if (ruleNames.empty()) {
        Python3Parser parser(nullptr);
        ruleNames = parser.getRuleNames();
    }
}

std::string TreeBuilder::getRuleClassName(antlr4::ParserRuleContext *ctx) {
    if (!ctx) return "";
    size_t idx = ctx->getRuleIndex();
    if (idx < ruleNames.size()) {
        std::string r = ruleNames[idx];
        if (!r.empty()) {
            r[0] = static_cast<char>(std::toupper(r[0]));
        }
        return r;
    }
    return "ParserRule";
}

std::vector<std::shared_ptr<Node>> TreeBuilder::collectChildren(antlr4::tree::ParseTree *node) {
    std::vector<std::shared_ptr<Node>> result;
    if (!node) return result;

    for (auto *child : node->children) {
        if (!child) continue;
        std::any res = child->accept(this);
        if (res.has_value()) {
            if (auto *nodePtr = std::any_cast<std::shared_ptr<Node>>(&res)) {
                if (*nodePtr != nullptr) {
                    result.push_back(*nodePtr);
                }
            }

        }
    }
    return result;
}

std::shared_ptr<Node> TreeBuilder::visitRuleNode(antlr4::ParserRuleContext *ctx) {
    if (!ctx) return nullptr;

    auto resultNode = std::make_shared<Node>();
    resultNode->rule_name = getRuleClassName(ctx);
    resultNode->text = ctx->getText();

    auto children = collectChildren(ctx);
    resultNode->children = std::move(children);
    for (auto &child : resultNode->children) {
        if (child) {
            child->parent = resultNode;
        }
    }

    return resultNode;
}

std::any TreeBuilder::visitChildren(antlr4::tree::ParseTree *node) {
    if (!node) return std::any();
    auto *ruleCtx = dynamic_cast<antlr4::ParserRuleContext*>(node);
    if (!ruleCtx) {
        return std::any();
    }
    auto result = visitRuleNode(ruleCtx);
    if (!result) return std::any();
    return result;
}

std::any TreeBuilder::visitTerminal(antlr4::tree::TerminalNode *node) {
    if (!node) return std::any();
    auto *sym = node->getSymbol();
    if (!sym) return std::any();

    size_t tokenType = sym->getType();
    if (tokenType == Python3Parser::NEWLINE ||
        tokenType == Python3Parser::INDENT ||
        tokenType == Python3Parser::DEDENT) {
        return std::any();
    }

    if (tokenType == antlr4::Token::EOF) {
        return std::any();
    }

    std::string txt = node->getText();
    // Trim surrounding spaces
    size_t first = txt.find_first_not_of(" \t\r\n");
    std::string trimmed = (first == std::string::npos) ? "" : txt.substr(first, txt.find_last_not_of(" \t\r\n") - first + 1);

    // Drop punctuation: ( ) : , .
    if (trimmed == "(" || trimmed == ")" || trimmed == ":" || trimmed == "," || trimmed == ".") {
        return std::any();
    }

    auto termNode = std::make_shared<Node>();
    termNode->is_terminal = true;
    termNode->text = txt;
    termNode->rule_name = "TerminalNode";
    return termNode;
}

std::any TreeBuilder::visitErrorNode(antlr4::tree::ErrorNode *node) {
    if (!node) return std::any();
    auto errNode = std::make_shared<Node>();
    errNode->is_terminal = true;
    errNode->text = node->getText();
    errNode->rule_name = "ErrorNode";
    return errNode;
}

std::shared_ptr<Node> TreeBuilder::pruneSingleChild(antlr4::ParserRuleContext *ctx) {
    if (!ctx) return nullptr;
    auto current = visitRuleNode(ctx);

    while (current && current->children.size() == 1) {
        auto nextChild = current->children[0];
        current = nextChild;
        if (current->is_terminal ||
            current->rule_name == "Name" ||
            current->rule_name == "Expr_wildcard" ||
            current->rule_name == "Expr") {
            return current;
        }
    }
    return current;
}

std::any TreeBuilder::visitAtom_expr(Python3Parser::Atom_exprContext *ctx) {
    auto pruned = pruneSingleChild(ctx);
    if (!pruned) return std::any();
    return pruned;
}

std::any TreeBuilder::visitExpr_stmt(Python3Parser::Expr_stmtContext *ctx) {
    auto pruned = pruneSingleChild(ctx);
    if (!pruned) return std::any();
    return pruned;
}

std::any TreeBuilder::visitTfpdef(Python3Parser::TfpdefContext *ctx) {
    auto pruned = pruneSingleChild(ctx);
    if (!pruned) return std::any();
    return pruned;
}

static bool isDoubleWildcard(const std::shared_ptr<Node> &node) {
    if (!node) return false;
    if (node->rule_name == "Double_wildcard") return true;
    for (const auto &child : node->children) {
        if (isDoubleWildcard(child)) return true;
    }
    return false;
}

std::any TreeBuilder::visitBlock(Python3Parser::BlockContext *ctx) {
    auto blockNode = visitRuleNode(ctx);
    if (!blockNode) return std::any();

    bool put = true;
    if (blockNode->children.size() == 1) {
        if (isDoubleWildcard(blockNode->children[0])) {
            put = false;
        }
    }

    if (put) {
        auto endNode = std::make_shared<Node>();
        endNode->rule_name = "BlockEnd";
        endNode->text = "";
        endNode->parent = blockNode;
        blockNode->children.push_back(endNode);
    }

    return blockNode;
}