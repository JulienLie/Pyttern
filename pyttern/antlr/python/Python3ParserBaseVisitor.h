#include "Python3ParserBase.h"

// Generated from Python3Parser.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"
#include "Python3ParserVisitor.h"


/**
 * This class provides an empty implementation of Python3ParserVisitor, which can be
 * extended to create a visitor which only needs to handle a subset of the available methods.
 */
class  Python3ParserBaseVisitor : public Python3ParserVisitor {
public:

  virtual std::any visitSingle_input(Python3Parser::Single_inputContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFile_input(Python3Parser::File_inputContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitEval_input(Python3Parser::Eval_inputContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubpattern_input(Python3Parser::Subpattern_inputContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDecorator(Python3Parser::DecoratorContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDecorators(Python3Parser::DecoratorsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDecorated(Python3Parser::DecoratedContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAsync_funcdef(Python3Parser::Async_funcdefContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFuncdef(Python3Parser::FuncdefContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitParameters(Python3Parser::ParametersContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTfpdef(Python3Parser::TfpdefContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVarargslist(Python3Parser::VarargslistContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVfpdef(Python3Parser::VfpdefContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStmt(Python3Parser::StmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSimple_stmts(Python3Parser::Simple_stmtsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSimple_stmt(Python3Parser::Simple_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExpr_stmt(Python3Parser::Expr_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnassign(Python3Parser::AnnassignContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTestlist_star_expr(Python3Parser::Testlist_star_exprContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAugassign(Python3Parser::AugassignContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDel_stmt(Python3Parser::Del_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPass_stmt(Python3Parser::Pass_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFlow_stmt(Python3Parser::Flow_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitBreak_stmt(Python3Parser::Break_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitContinue_stmt(Python3Parser::Continue_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitReturn_stmt(Python3Parser::Return_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitYield_stmt(Python3Parser::Yield_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitRaise_stmt(Python3Parser::Raise_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitImport_stmt(Python3Parser::Import_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitImport_name(Python3Parser::Import_nameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitImport_from(Python3Parser::Import_fromContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitImport_as_name(Python3Parser::Import_as_nameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDotted_as_name(Python3Parser::Dotted_as_nameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitImport_as_names(Python3Parser::Import_as_namesContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDotted_as_names(Python3Parser::Dotted_as_namesContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDotted_name(Python3Parser::Dotted_nameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitGlobal_stmt(Python3Parser::Global_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitNonlocal_stmt(Python3Parser::Nonlocal_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAssert_stmt(Python3Parser::Assert_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCompound_stmt(Python3Parser::Compound_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAsync_stmt(Python3Parser::Async_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitIf_stmt(Python3Parser::If_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitWhile_stmt(Python3Parser::While_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFor_stmt(Python3Parser::For_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTry_stmt(Python3Parser::Try_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitWith_stmt(Python3Parser::With_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitWith_item(Python3Parser::With_itemContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExcept_clause(Python3Parser::Except_clauseContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitBlock(Python3Parser::BlockContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMatch_stmt(Python3Parser::Match_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubject_expr(Python3Parser::Subject_exprContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStar_named_expressions(Python3Parser::Star_named_expressionsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStar_named_expression(Python3Parser::Star_named_expressionContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCase_block(Python3Parser::Case_blockContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitGuard(Python3Parser::GuardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPatterns(Python3Parser::PatternsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPattern(Python3Parser::PatternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAs_pattern(Python3Parser::As_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitOr_pattern(Python3Parser::Or_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClosed_pattern(Python3Parser::Closed_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLiteral_pattern(Python3Parser::Literal_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLiteral_expr(Python3Parser::Literal_exprContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitComplex_number(Python3Parser::Complex_numberContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSigned_number(Python3Parser::Signed_numberContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSigned_real_number(Python3Parser::Signed_real_numberContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitReal_number(Python3Parser::Real_numberContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitImaginary_number(Python3Parser::Imaginary_numberContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCapture_pattern(Python3Parser::Capture_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPattern_capture_target(Python3Parser::Pattern_capture_targetContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitWildcard_pattern(Python3Parser::Wildcard_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitValue_pattern(Python3Parser::Value_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAttr(Python3Parser::AttrContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitName_or_attr(Python3Parser::Name_or_attrContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitGroup_pattern(Python3Parser::Group_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSequence_pattern(Python3Parser::Sequence_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitOpen_sequence_pattern(Python3Parser::Open_sequence_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMaybe_sequence_pattern(Python3Parser::Maybe_sequence_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMaybe_star_pattern(Python3Parser::Maybe_star_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStar_pattern(Python3Parser::Star_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMapping_pattern(Python3Parser::Mapping_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitItems_pattern(Python3Parser::Items_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitKey_value_pattern(Python3Parser::Key_value_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDouble_star_pattern(Python3Parser::Double_star_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClass_pattern(Python3Parser::Class_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPositional_patterns(Python3Parser::Positional_patternsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitKeyword_patterns(Python3Parser::Keyword_patternsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitKeyword_pattern(Python3Parser::Keyword_patternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStar_expr(Python3Parser::Star_exprContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExpr(Python3Parser::ExprContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitComp_op(Python3Parser::Comp_opContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAtom_expr(Python3Parser::Atom_exprContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAtom(Python3Parser::AtomContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitName(Python3Parser::NameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTestlist_comp(Python3Parser::Testlist_compContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTrailer(Python3Parser::TrailerContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubscriptlist(Python3Parser::SubscriptlistContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubscript_(Python3Parser::Subscript_Context *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSliceop(Python3Parser::SliceopContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExprlist(Python3Parser::ExprlistContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTestlist(Python3Parser::TestlistContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDictorsetmaker(Python3Parser::DictorsetmakerContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassdef(Python3Parser::ClassdefContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitArglist(Python3Parser::ArglistContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitArgument(Python3Parser::ArgumentContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitComp_iter(Python3Parser::Comp_iterContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitComp_for(Python3Parser::Comp_forContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitComp_if(Python3Parser::Comp_ifContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitEncoding_decl(Python3Parser::Encoding_declContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitYield_expr(Python3Parser::Yield_exprContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitYield_arg(Python3Parser::Yield_argContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStrings(Python3Parser::StringsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitWildcard_number(Python3Parser::Wildcard_numberContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStmt_wildcard(Python3Parser::Stmt_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExpr_wildcard(Python3Parser::Expr_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAtom_wildcard(Python3Parser::Atom_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSimple_wildcard(Python3Parser::Simple_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitNumber_wildcard(Python3Parser::Number_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDouble_wildcard(Python3Parser::Double_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVar_wildcard(Python3Parser::Var_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitContains_wildcard(Python3Parser::Contains_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCompound_wildcard(Python3Parser::Compound_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSimple_compound_wildcard(Python3Parser::Simple_compound_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMultiple_compound_wildcard(Python3Parser::Multiple_compound_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitList_wildcard(Python3Parser::List_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubpattern_call(Python3Parser::Subpattern_callContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubpattern_stmts(Python3Parser::Subpattern_stmtsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubpattern(Python3Parser::SubpatternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubpattern_args(Python3Parser::Subpattern_argsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSubpattern_arg(Python3Parser::Subpattern_argContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSimple_subpattern(Python3Parser::Simple_subpatternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCompound_subpattern(Python3Parser::Compound_subpatternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTransformation(Python3Parser::TransformationContext *ctx) override {
    return visitChildren(ctx);
  }


};

