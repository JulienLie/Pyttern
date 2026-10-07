
// Generated from JavaParser.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"
#include "JavaParser.h"



/**
 * This class defines an abstract visitor for a parse tree
 * produced by JavaParser.
 */
class  JavaParserVisitor : public antlr4::tree::AbstractParseTreeVisitor {
public:

  /**
   * Visit parse trees produced by JavaParser.
   */
    virtual std::any visitCompilationUnit(JavaParser::CompilationUnitContext *context) = 0;

    virtual std::any visitPackageDeclaration(JavaParser::PackageDeclarationContext *context) = 0;

    virtual std::any visitImportDeclaration(JavaParser::ImportDeclarationContext *context) = 0;

    virtual std::any visitTypeDeclaration(JavaParser::TypeDeclarationContext *context) = 0;

    virtual std::any visitModifier(JavaParser::ModifierContext *context) = 0;

    virtual std::any visitClassOrInterfaceModifier(JavaParser::ClassOrInterfaceModifierContext *context) = 0;

    virtual std::any visitVariableModifier(JavaParser::VariableModifierContext *context) = 0;

    virtual std::any visitClassDeclaration(JavaParser::ClassDeclarationContext *context) = 0;

    virtual std::any visitTypeParameters(JavaParser::TypeParametersContext *context) = 0;

    virtual std::any visitTypeParameter(JavaParser::TypeParameterContext *context) = 0;

    virtual std::any visitTypeBound(JavaParser::TypeBoundContext *context) = 0;

    virtual std::any visitEnumDeclaration(JavaParser::EnumDeclarationContext *context) = 0;

    virtual std::any visitEnumConstants(JavaParser::EnumConstantsContext *context) = 0;

    virtual std::any visitEnumConstant(JavaParser::EnumConstantContext *context) = 0;

    virtual std::any visitEnumBodyDeclarations(JavaParser::EnumBodyDeclarationsContext *context) = 0;

    virtual std::any visitInterfaceDeclaration(JavaParser::InterfaceDeclarationContext *context) = 0;

    virtual std::any visitClassBody(JavaParser::ClassBodyContext *context) = 0;

    virtual std::any visitInterfaceBody(JavaParser::InterfaceBodyContext *context) = 0;

    virtual std::any visitClassBodyDeclaration(JavaParser::ClassBodyDeclarationContext *context) = 0;

    virtual std::any visitMemberDeclaration(JavaParser::MemberDeclarationContext *context) = 0;

    virtual std::any visitMethodDeclaration(JavaParser::MethodDeclarationContext *context) = 0;

    virtual std::any visitMethodBody(JavaParser::MethodBodyContext *context) = 0;

    virtual std::any visitTypeTypeOrVoid(JavaParser::TypeTypeOrVoidContext *context) = 0;

    virtual std::any visitGenericMethodDeclaration(JavaParser::GenericMethodDeclarationContext *context) = 0;

    virtual std::any visitGenericConstructorDeclaration(JavaParser::GenericConstructorDeclarationContext *context) = 0;

    virtual std::any visitConstructorDeclaration(JavaParser::ConstructorDeclarationContext *context) = 0;

    virtual std::any visitCompactConstructorDeclaration(JavaParser::CompactConstructorDeclarationContext *context) = 0;

    virtual std::any visitFieldDeclaration(JavaParser::FieldDeclarationContext *context) = 0;

    virtual std::any visitInterfaceBodyDeclaration(JavaParser::InterfaceBodyDeclarationContext *context) = 0;

    virtual std::any visitInterfaceMemberDeclaration(JavaParser::InterfaceMemberDeclarationContext *context) = 0;

    virtual std::any visitConstDeclaration(JavaParser::ConstDeclarationContext *context) = 0;

    virtual std::any visitConstantDeclarator(JavaParser::ConstantDeclaratorContext *context) = 0;

    virtual std::any visitInterfaceMethodDeclaration(JavaParser::InterfaceMethodDeclarationContext *context) = 0;

    virtual std::any visitInterfaceMethodModifier(JavaParser::InterfaceMethodModifierContext *context) = 0;

    virtual std::any visitGenericInterfaceMethodDeclaration(JavaParser::GenericInterfaceMethodDeclarationContext *context) = 0;

    virtual std::any visitInterfaceCommonBodyDeclaration(JavaParser::InterfaceCommonBodyDeclarationContext *context) = 0;

    virtual std::any visitVariableDeclarators(JavaParser::VariableDeclaratorsContext *context) = 0;

    virtual std::any visitVariableDeclarator(JavaParser::VariableDeclaratorContext *context) = 0;

    virtual std::any visitVariableDeclaratorId(JavaParser::VariableDeclaratorIdContext *context) = 0;

    virtual std::any visitVariableInitializer(JavaParser::VariableInitializerContext *context) = 0;

    virtual std::any visitArrayInitializer(JavaParser::ArrayInitializerContext *context) = 0;

    virtual std::any visitClassOrInterfaceType(JavaParser::ClassOrInterfaceTypeContext *context) = 0;

    virtual std::any visitTypeArgument(JavaParser::TypeArgumentContext *context) = 0;

    virtual std::any visitQualifiedNameList(JavaParser::QualifiedNameListContext *context) = 0;

    virtual std::any visitFormalParameters(JavaParser::FormalParametersContext *context) = 0;

    virtual std::any visitReceiverParameter(JavaParser::ReceiverParameterContext *context) = 0;

    virtual std::any visitFormalParameterList(JavaParser::FormalParameterListContext *context) = 0;

    virtual std::any visitFormalParameter(JavaParser::FormalParameterContext *context) = 0;

    virtual std::any visitLastFormalParameter(JavaParser::LastFormalParameterContext *context) = 0;

    virtual std::any visitLambdaLVTIList(JavaParser::LambdaLVTIListContext *context) = 0;

    virtual std::any visitLambdaLVTIParameter(JavaParser::LambdaLVTIParameterContext *context) = 0;

    virtual std::any visitQualifiedName(JavaParser::QualifiedNameContext *context) = 0;

    virtual std::any visitLiteral(JavaParser::LiteralContext *context) = 0;

    virtual std::any visitIntegerLiteral(JavaParser::IntegerLiteralContext *context) = 0;

    virtual std::any visitFloatLiteral(JavaParser::FloatLiteralContext *context) = 0;

    virtual std::any visitAltAnnotationQualifiedName(JavaParser::AltAnnotationQualifiedNameContext *context) = 0;

    virtual std::any visitAnnotation(JavaParser::AnnotationContext *context) = 0;

    virtual std::any visitElementValuePairs(JavaParser::ElementValuePairsContext *context) = 0;

    virtual std::any visitElementValuePair(JavaParser::ElementValuePairContext *context) = 0;

    virtual std::any visitElementValue(JavaParser::ElementValueContext *context) = 0;

    virtual std::any visitElementValueArrayInitializer(JavaParser::ElementValueArrayInitializerContext *context) = 0;

    virtual std::any visitAnnotationTypeDeclaration(JavaParser::AnnotationTypeDeclarationContext *context) = 0;

    virtual std::any visitAnnotationTypeBody(JavaParser::AnnotationTypeBodyContext *context) = 0;

    virtual std::any visitAnnotationTypeElementDeclaration(JavaParser::AnnotationTypeElementDeclarationContext *context) = 0;

    virtual std::any visitAnnotationTypeElementRest(JavaParser::AnnotationTypeElementRestContext *context) = 0;

    virtual std::any visitAnnotationMethodOrConstantRest(JavaParser::AnnotationMethodOrConstantRestContext *context) = 0;

    virtual std::any visitAnnotationMethodRest(JavaParser::AnnotationMethodRestContext *context) = 0;

    virtual std::any visitAnnotationConstantRest(JavaParser::AnnotationConstantRestContext *context) = 0;

    virtual std::any visitDefaultValue(JavaParser::DefaultValueContext *context) = 0;

    virtual std::any visitModuleDeclaration(JavaParser::ModuleDeclarationContext *context) = 0;

    virtual std::any visitModuleBody(JavaParser::ModuleBodyContext *context) = 0;

    virtual std::any visitModuleDirective(JavaParser::ModuleDirectiveContext *context) = 0;

    virtual std::any visitRequiresModifier(JavaParser::RequiresModifierContext *context) = 0;

    virtual std::any visitRecordDeclaration(JavaParser::RecordDeclarationContext *context) = 0;

    virtual std::any visitRecordHeader(JavaParser::RecordHeaderContext *context) = 0;

    virtual std::any visitRecordComponentList(JavaParser::RecordComponentListContext *context) = 0;

    virtual std::any visitRecordComponent(JavaParser::RecordComponentContext *context) = 0;

    virtual std::any visitRecordBody(JavaParser::RecordBodyContext *context) = 0;

    virtual std::any visitBlock(JavaParser::BlockContext *context) = 0;

    virtual std::any visitBlockStatement(JavaParser::BlockStatementContext *context) = 0;

    virtual std::any visitLocalVariableDeclaration(JavaParser::LocalVariableDeclarationContext *context) = 0;

    virtual std::any visitIdentifier(JavaParser::IdentifierContext *context) = 0;

    virtual std::any visitTypeIdentifier(JavaParser::TypeIdentifierContext *context) = 0;

    virtual std::any visitLocalTypeDeclaration(JavaParser::LocalTypeDeclarationContext *context) = 0;

    virtual std::any visitStatement(JavaParser::StatementContext *context) = 0;

    virtual std::any visitCompound_stmt(JavaParser::Compound_stmtContext *context) = 0;

    virtual std::any visitCatchClause(JavaParser::CatchClauseContext *context) = 0;

    virtual std::any visitCatchType(JavaParser::CatchTypeContext *context) = 0;

    virtual std::any visitFinallyBlock(JavaParser::FinallyBlockContext *context) = 0;

    virtual std::any visitResourceSpecification(JavaParser::ResourceSpecificationContext *context) = 0;

    virtual std::any visitResources(JavaParser::ResourcesContext *context) = 0;

    virtual std::any visitResource(JavaParser::ResourceContext *context) = 0;

    virtual std::any visitSwitchBlockStatementGroup(JavaParser::SwitchBlockStatementGroupContext *context) = 0;

    virtual std::any visitSwitchLabel(JavaParser::SwitchLabelContext *context) = 0;

    virtual std::any visitForControl(JavaParser::ForControlContext *context) = 0;

    virtual std::any visitForInit(JavaParser::ForInitContext *context) = 0;

    virtual std::any visitEnhancedForControl(JavaParser::EnhancedForControlContext *context) = 0;

    virtual std::any visitParExpression(JavaParser::ParExpressionContext *context) = 0;

    virtual std::any visitExpressionList(JavaParser::ExpressionListContext *context) = 0;

    virtual std::any visitMethodCall(JavaParser::MethodCallContext *context) = 0;

    virtual std::any visitExpression(JavaParser::ExpressionContext *context) = 0;

    virtual std::any visitPattern(JavaParser::PatternContext *context) = 0;

    virtual std::any visitLambdaExpression(JavaParser::LambdaExpressionContext *context) = 0;

    virtual std::any visitLambdaParameters(JavaParser::LambdaParametersContext *context) = 0;

    virtual std::any visitLambdaBody(JavaParser::LambdaBodyContext *context) = 0;

    virtual std::any visitPrimary(JavaParser::PrimaryContext *context) = 0;

    virtual std::any visitSwitchExpression(JavaParser::SwitchExpressionContext *context) = 0;

    virtual std::any visitSwitchLabeledRule(JavaParser::SwitchLabeledRuleContext *context) = 0;

    virtual std::any visitGuardedPattern(JavaParser::GuardedPatternContext *context) = 0;

    virtual std::any visitSwitchRuleOutcome(JavaParser::SwitchRuleOutcomeContext *context) = 0;

    virtual std::any visitClassType(JavaParser::ClassTypeContext *context) = 0;

    virtual std::any visitCreator(JavaParser::CreatorContext *context) = 0;

    virtual std::any visitCreatedName(JavaParser::CreatedNameContext *context) = 0;

    virtual std::any visitInnerCreator(JavaParser::InnerCreatorContext *context) = 0;

    virtual std::any visitArrayCreatorRest(JavaParser::ArrayCreatorRestContext *context) = 0;

    virtual std::any visitClassCreatorRest(JavaParser::ClassCreatorRestContext *context) = 0;

    virtual std::any visitExplicitGenericInvocation(JavaParser::ExplicitGenericInvocationContext *context) = 0;

    virtual std::any visitTypeArgumentsOrDiamond(JavaParser::TypeArgumentsOrDiamondContext *context) = 0;

    virtual std::any visitNonWildcardTypeArgumentsOrDiamond(JavaParser::NonWildcardTypeArgumentsOrDiamondContext *context) = 0;

    virtual std::any visitNonWildcardTypeArguments(JavaParser::NonWildcardTypeArgumentsContext *context) = 0;

    virtual std::any visitTypeList(JavaParser::TypeListContext *context) = 0;

    virtual std::any visitTypeType(JavaParser::TypeTypeContext *context) = 0;

    virtual std::any visitPrimitiveType(JavaParser::PrimitiveTypeContext *context) = 0;

    virtual std::any visitTypeArguments(JavaParser::TypeArgumentsContext *context) = 0;

    virtual std::any visitSuperSuffix(JavaParser::SuperSuffixContext *context) = 0;

    virtual std::any visitExplicitGenericInvocationSuffix(JavaParser::ExplicitGenericInvocationSuffixContext *context) = 0;

    virtual std::any visitArguments(JavaParser::ArgumentsContext *context) = 0;

    virtual std::any visitSimple_wildcard(JavaParser::Simple_wildcardContext *context) = 0;

    virtual std::any visitVar_wildcard(JavaParser::Var_wildcardContext *context) = 0;

    virtual std::any visitList_wildcard(JavaParser::List_wildcardContext *context) = 0;

    virtual std::any visitContains_wildcard(JavaParser::Contains_wildcardContext *context) = 0;

    virtual std::any visitSimple_compound_wildcard(JavaParser::Simple_compound_wildcardContext *context) = 0;

    virtual std::any visitMultiple_compound_wildcard(JavaParser::Multiple_compound_wildcardContext *context) = 0;

    virtual std::any visitNumber_wildcard(JavaParser::Number_wildcardContext *context) = 0;

    virtual std::any visitWildcard_number(JavaParser::Wildcard_numberContext *context) = 0;

    virtual std::any visitStmt_wildcard(JavaParser::Stmt_wildcardContext *context) = 0;

    virtual std::any visitExpr_wildcard(JavaParser::Expr_wildcardContext *context) = 0;

    virtual std::any visitCompound_wildcard(JavaParser::Compound_wildcardContext *context) = 0;


};

