#pragma once
#include "Python3ParserBaseVisitor.h"
#include "Python3Parser.h"
#include "Node.h"
#include <vector>
#include <memory>
#include <string>

class TreeBuilder : public Python3ParserBaseVisitor {
private:
    std::vector<std::string> ruleNames;
    std::string getRuleClassName(antlr4::ParserRuleContext *ctx);

public:
    TreeBuilder(std::vector<std::string> names = {});

    std::vector<std::shared_ptr<Node>> collectChildren(antlr4::tree::ParseTree *node);
    std::shared_ptr<Node> visitRuleNode(antlr4::ParserRuleContext *ctx);
    std::shared_ptr<Node> pruneSingleChild(antlr4::ParserRuleContext *ctx);

    // General visit overrides
    std::any visitChildren(antlr4::tree::ParseTree *node) override;
    std::any visitTerminal(antlr4::tree::TerminalNode *node) override;
    std::any visitErrorNode(antlr4::tree::ErrorNode *node) override;

    // Pruned and synthetic rule overrides
    std::any visitBlock(Python3Parser::BlockContext *ctx) override;
    std::any visitAtom_expr(Python3Parser::Atom_exprContext *ctx) override;
    std::any visitExpr_stmt(Python3Parser::Expr_stmtContext *ctx) override;
    std::any visitTfpdef(Python3Parser::TfpdefContext *ctx) override;
};
