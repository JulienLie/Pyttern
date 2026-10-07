
// Generated from JavaParser.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"
#include "JavaParserVisitor.h"


/**
 * This class provides an empty implementation of JavaParserVisitor, which can be
 * extended to create a visitor which only needs to handle a subset of the available methods.
 */
class  JavaParserBaseVisitor : public JavaParserVisitor {
public:

  virtual std::any visitCompilationUnit(JavaParser::CompilationUnitContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPackageDeclaration(JavaParser::PackageDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitImportDeclaration(JavaParser::ImportDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeDeclaration(JavaParser::TypeDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitModifier(JavaParser::ModifierContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassOrInterfaceModifier(JavaParser::ClassOrInterfaceModifierContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVariableModifier(JavaParser::VariableModifierContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassDeclaration(JavaParser::ClassDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeParameters(JavaParser::TypeParametersContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeParameter(JavaParser::TypeParameterContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeBound(JavaParser::TypeBoundContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitEnumDeclaration(JavaParser::EnumDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitEnumConstants(JavaParser::EnumConstantsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitEnumConstant(JavaParser::EnumConstantContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitEnumBodyDeclarations(JavaParser::EnumBodyDeclarationsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInterfaceDeclaration(JavaParser::InterfaceDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassBody(JavaParser::ClassBodyContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInterfaceBody(JavaParser::InterfaceBodyContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassBodyDeclaration(JavaParser::ClassBodyDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMemberDeclaration(JavaParser::MemberDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMethodDeclaration(JavaParser::MethodDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMethodBody(JavaParser::MethodBodyContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeTypeOrVoid(JavaParser::TypeTypeOrVoidContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitGenericMethodDeclaration(JavaParser::GenericMethodDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitGenericConstructorDeclaration(JavaParser::GenericConstructorDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitConstructorDeclaration(JavaParser::ConstructorDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCompactConstructorDeclaration(JavaParser::CompactConstructorDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFieldDeclaration(JavaParser::FieldDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInterfaceBodyDeclaration(JavaParser::InterfaceBodyDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInterfaceMemberDeclaration(JavaParser::InterfaceMemberDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitConstDeclaration(JavaParser::ConstDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitConstantDeclarator(JavaParser::ConstantDeclaratorContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInterfaceMethodDeclaration(JavaParser::InterfaceMethodDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInterfaceMethodModifier(JavaParser::InterfaceMethodModifierContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitGenericInterfaceMethodDeclaration(JavaParser::GenericInterfaceMethodDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInterfaceCommonBodyDeclaration(JavaParser::InterfaceCommonBodyDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVariableDeclarators(JavaParser::VariableDeclaratorsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVariableDeclarator(JavaParser::VariableDeclaratorContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVariableDeclaratorId(JavaParser::VariableDeclaratorIdContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVariableInitializer(JavaParser::VariableInitializerContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitArrayInitializer(JavaParser::ArrayInitializerContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassOrInterfaceType(JavaParser::ClassOrInterfaceTypeContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeArgument(JavaParser::TypeArgumentContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitQualifiedNameList(JavaParser::QualifiedNameListContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFormalParameters(JavaParser::FormalParametersContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitReceiverParameter(JavaParser::ReceiverParameterContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFormalParameterList(JavaParser::FormalParameterListContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFormalParameter(JavaParser::FormalParameterContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLastFormalParameter(JavaParser::LastFormalParameterContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLambdaLVTIList(JavaParser::LambdaLVTIListContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLambdaLVTIParameter(JavaParser::LambdaLVTIParameterContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitQualifiedName(JavaParser::QualifiedNameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLiteral(JavaParser::LiteralContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitIntegerLiteral(JavaParser::IntegerLiteralContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFloatLiteral(JavaParser::FloatLiteralContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAltAnnotationQualifiedName(JavaParser::AltAnnotationQualifiedNameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotation(JavaParser::AnnotationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitElementValuePairs(JavaParser::ElementValuePairsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitElementValuePair(JavaParser::ElementValuePairContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitElementValue(JavaParser::ElementValueContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitElementValueArrayInitializer(JavaParser::ElementValueArrayInitializerContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotationTypeDeclaration(JavaParser::AnnotationTypeDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotationTypeBody(JavaParser::AnnotationTypeBodyContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotationTypeElementDeclaration(JavaParser::AnnotationTypeElementDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotationTypeElementRest(JavaParser::AnnotationTypeElementRestContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotationMethodOrConstantRest(JavaParser::AnnotationMethodOrConstantRestContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotationMethodRest(JavaParser::AnnotationMethodRestContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitAnnotationConstantRest(JavaParser::AnnotationConstantRestContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitDefaultValue(JavaParser::DefaultValueContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitModuleDeclaration(JavaParser::ModuleDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitModuleBody(JavaParser::ModuleBodyContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitModuleDirective(JavaParser::ModuleDirectiveContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitRequiresModifier(JavaParser::RequiresModifierContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitRecordDeclaration(JavaParser::RecordDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitRecordHeader(JavaParser::RecordHeaderContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitRecordComponentList(JavaParser::RecordComponentListContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitRecordComponent(JavaParser::RecordComponentContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitRecordBody(JavaParser::RecordBodyContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitBlock(JavaParser::BlockContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitBlockStatement(JavaParser::BlockStatementContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLocalVariableDeclaration(JavaParser::LocalVariableDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitIdentifier(JavaParser::IdentifierContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeIdentifier(JavaParser::TypeIdentifierContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLocalTypeDeclaration(JavaParser::LocalTypeDeclarationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStatement(JavaParser::StatementContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCompound_stmt(JavaParser::Compound_stmtContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCatchClause(JavaParser::CatchClauseContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCatchType(JavaParser::CatchTypeContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitFinallyBlock(JavaParser::FinallyBlockContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitResourceSpecification(JavaParser::ResourceSpecificationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitResources(JavaParser::ResourcesContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitResource(JavaParser::ResourceContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSwitchBlockStatementGroup(JavaParser::SwitchBlockStatementGroupContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSwitchLabel(JavaParser::SwitchLabelContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitForControl(JavaParser::ForControlContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitForInit(JavaParser::ForInitContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitEnhancedForControl(JavaParser::EnhancedForControlContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitParExpression(JavaParser::ParExpressionContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExpressionList(JavaParser::ExpressionListContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMethodCall(JavaParser::MethodCallContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExpression(JavaParser::ExpressionContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPattern(JavaParser::PatternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLambdaExpression(JavaParser::LambdaExpressionContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLambdaParameters(JavaParser::LambdaParametersContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitLambdaBody(JavaParser::LambdaBodyContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPrimary(JavaParser::PrimaryContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSwitchExpression(JavaParser::SwitchExpressionContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSwitchLabeledRule(JavaParser::SwitchLabeledRuleContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitGuardedPattern(JavaParser::GuardedPatternContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSwitchRuleOutcome(JavaParser::SwitchRuleOutcomeContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassType(JavaParser::ClassTypeContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCreator(JavaParser::CreatorContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCreatedName(JavaParser::CreatedNameContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitInnerCreator(JavaParser::InnerCreatorContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitArrayCreatorRest(JavaParser::ArrayCreatorRestContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitClassCreatorRest(JavaParser::ClassCreatorRestContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExplicitGenericInvocation(JavaParser::ExplicitGenericInvocationContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeArgumentsOrDiamond(JavaParser::TypeArgumentsOrDiamondContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitNonWildcardTypeArgumentsOrDiamond(JavaParser::NonWildcardTypeArgumentsOrDiamondContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitNonWildcardTypeArguments(JavaParser::NonWildcardTypeArgumentsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeList(JavaParser::TypeListContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeType(JavaParser::TypeTypeContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitPrimitiveType(JavaParser::PrimitiveTypeContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitTypeArguments(JavaParser::TypeArgumentsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSuperSuffix(JavaParser::SuperSuffixContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExplicitGenericInvocationSuffix(JavaParser::ExplicitGenericInvocationSuffixContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitArguments(JavaParser::ArgumentsContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSimple_wildcard(JavaParser::Simple_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitVar_wildcard(JavaParser::Var_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitList_wildcard(JavaParser::List_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitContains_wildcard(JavaParser::Contains_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitSimple_compound_wildcard(JavaParser::Simple_compound_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitMultiple_compound_wildcard(JavaParser::Multiple_compound_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitNumber_wildcard(JavaParser::Number_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitWildcard_number(JavaParser::Wildcard_numberContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitStmt_wildcard(JavaParser::Stmt_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitExpr_wildcard(JavaParser::Expr_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }

  virtual std::any visitCompound_wildcard(JavaParser::Compound_wildcardContext *ctx) override {
    return visitChildren(ctx);
  }


};

