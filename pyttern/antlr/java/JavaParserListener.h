
// Generated from JavaParser.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"
#include "JavaParser.h"


/**
 * This interface defines an abstract listener for a parse tree produced by JavaParser.
 */
class  JavaParserListener : public antlr4::tree::ParseTreeListener {
public:

  virtual void enterCompilationUnit(JavaParser::CompilationUnitContext *ctx) = 0;
  virtual void exitCompilationUnit(JavaParser::CompilationUnitContext *ctx) = 0;

  virtual void enterPackageDeclaration(JavaParser::PackageDeclarationContext *ctx) = 0;
  virtual void exitPackageDeclaration(JavaParser::PackageDeclarationContext *ctx) = 0;

  virtual void enterImportDeclaration(JavaParser::ImportDeclarationContext *ctx) = 0;
  virtual void exitImportDeclaration(JavaParser::ImportDeclarationContext *ctx) = 0;

  virtual void enterTypeDeclaration(JavaParser::TypeDeclarationContext *ctx) = 0;
  virtual void exitTypeDeclaration(JavaParser::TypeDeclarationContext *ctx) = 0;

  virtual void enterModifier(JavaParser::ModifierContext *ctx) = 0;
  virtual void exitModifier(JavaParser::ModifierContext *ctx) = 0;

  virtual void enterClassOrInterfaceModifier(JavaParser::ClassOrInterfaceModifierContext *ctx) = 0;
  virtual void exitClassOrInterfaceModifier(JavaParser::ClassOrInterfaceModifierContext *ctx) = 0;

  virtual void enterVariableModifier(JavaParser::VariableModifierContext *ctx) = 0;
  virtual void exitVariableModifier(JavaParser::VariableModifierContext *ctx) = 0;

  virtual void enterClassDeclaration(JavaParser::ClassDeclarationContext *ctx) = 0;
  virtual void exitClassDeclaration(JavaParser::ClassDeclarationContext *ctx) = 0;

  virtual void enterTypeParameters(JavaParser::TypeParametersContext *ctx) = 0;
  virtual void exitTypeParameters(JavaParser::TypeParametersContext *ctx) = 0;

  virtual void enterTypeParameter(JavaParser::TypeParameterContext *ctx) = 0;
  virtual void exitTypeParameter(JavaParser::TypeParameterContext *ctx) = 0;

  virtual void enterTypeBound(JavaParser::TypeBoundContext *ctx) = 0;
  virtual void exitTypeBound(JavaParser::TypeBoundContext *ctx) = 0;

  virtual void enterEnumDeclaration(JavaParser::EnumDeclarationContext *ctx) = 0;
  virtual void exitEnumDeclaration(JavaParser::EnumDeclarationContext *ctx) = 0;

  virtual void enterEnumConstants(JavaParser::EnumConstantsContext *ctx) = 0;
  virtual void exitEnumConstants(JavaParser::EnumConstantsContext *ctx) = 0;

  virtual void enterEnumConstant(JavaParser::EnumConstantContext *ctx) = 0;
  virtual void exitEnumConstant(JavaParser::EnumConstantContext *ctx) = 0;

  virtual void enterEnumBodyDeclarations(JavaParser::EnumBodyDeclarationsContext *ctx) = 0;
  virtual void exitEnumBodyDeclarations(JavaParser::EnumBodyDeclarationsContext *ctx) = 0;

  virtual void enterInterfaceDeclaration(JavaParser::InterfaceDeclarationContext *ctx) = 0;
  virtual void exitInterfaceDeclaration(JavaParser::InterfaceDeclarationContext *ctx) = 0;

  virtual void enterClassBody(JavaParser::ClassBodyContext *ctx) = 0;
  virtual void exitClassBody(JavaParser::ClassBodyContext *ctx) = 0;

  virtual void enterInterfaceBody(JavaParser::InterfaceBodyContext *ctx) = 0;
  virtual void exitInterfaceBody(JavaParser::InterfaceBodyContext *ctx) = 0;

  virtual void enterClassBodyDeclaration(JavaParser::ClassBodyDeclarationContext *ctx) = 0;
  virtual void exitClassBodyDeclaration(JavaParser::ClassBodyDeclarationContext *ctx) = 0;

  virtual void enterMemberDeclaration(JavaParser::MemberDeclarationContext *ctx) = 0;
  virtual void exitMemberDeclaration(JavaParser::MemberDeclarationContext *ctx) = 0;

  virtual void enterMethodDeclaration(JavaParser::MethodDeclarationContext *ctx) = 0;
  virtual void exitMethodDeclaration(JavaParser::MethodDeclarationContext *ctx) = 0;

  virtual void enterMethodBody(JavaParser::MethodBodyContext *ctx) = 0;
  virtual void exitMethodBody(JavaParser::MethodBodyContext *ctx) = 0;

  virtual void enterTypeTypeOrVoid(JavaParser::TypeTypeOrVoidContext *ctx) = 0;
  virtual void exitTypeTypeOrVoid(JavaParser::TypeTypeOrVoidContext *ctx) = 0;

  virtual void enterGenericMethodDeclaration(JavaParser::GenericMethodDeclarationContext *ctx) = 0;
  virtual void exitGenericMethodDeclaration(JavaParser::GenericMethodDeclarationContext *ctx) = 0;

  virtual void enterGenericConstructorDeclaration(JavaParser::GenericConstructorDeclarationContext *ctx) = 0;
  virtual void exitGenericConstructorDeclaration(JavaParser::GenericConstructorDeclarationContext *ctx) = 0;

  virtual void enterConstructorDeclaration(JavaParser::ConstructorDeclarationContext *ctx) = 0;
  virtual void exitConstructorDeclaration(JavaParser::ConstructorDeclarationContext *ctx) = 0;

  virtual void enterCompactConstructorDeclaration(JavaParser::CompactConstructorDeclarationContext *ctx) = 0;
  virtual void exitCompactConstructorDeclaration(JavaParser::CompactConstructorDeclarationContext *ctx) = 0;

  virtual void enterFieldDeclaration(JavaParser::FieldDeclarationContext *ctx) = 0;
  virtual void exitFieldDeclaration(JavaParser::FieldDeclarationContext *ctx) = 0;

  virtual void enterInterfaceBodyDeclaration(JavaParser::InterfaceBodyDeclarationContext *ctx) = 0;
  virtual void exitInterfaceBodyDeclaration(JavaParser::InterfaceBodyDeclarationContext *ctx) = 0;

  virtual void enterInterfaceMemberDeclaration(JavaParser::InterfaceMemberDeclarationContext *ctx) = 0;
  virtual void exitInterfaceMemberDeclaration(JavaParser::InterfaceMemberDeclarationContext *ctx) = 0;

  virtual void enterConstDeclaration(JavaParser::ConstDeclarationContext *ctx) = 0;
  virtual void exitConstDeclaration(JavaParser::ConstDeclarationContext *ctx) = 0;

  virtual void enterConstantDeclarator(JavaParser::ConstantDeclaratorContext *ctx) = 0;
  virtual void exitConstantDeclarator(JavaParser::ConstantDeclaratorContext *ctx) = 0;

  virtual void enterInterfaceMethodDeclaration(JavaParser::InterfaceMethodDeclarationContext *ctx) = 0;
  virtual void exitInterfaceMethodDeclaration(JavaParser::InterfaceMethodDeclarationContext *ctx) = 0;

  virtual void enterInterfaceMethodModifier(JavaParser::InterfaceMethodModifierContext *ctx) = 0;
  virtual void exitInterfaceMethodModifier(JavaParser::InterfaceMethodModifierContext *ctx) = 0;

  virtual void enterGenericInterfaceMethodDeclaration(JavaParser::GenericInterfaceMethodDeclarationContext *ctx) = 0;
  virtual void exitGenericInterfaceMethodDeclaration(JavaParser::GenericInterfaceMethodDeclarationContext *ctx) = 0;

  virtual void enterInterfaceCommonBodyDeclaration(JavaParser::InterfaceCommonBodyDeclarationContext *ctx) = 0;
  virtual void exitInterfaceCommonBodyDeclaration(JavaParser::InterfaceCommonBodyDeclarationContext *ctx) = 0;

  virtual void enterVariableDeclarators(JavaParser::VariableDeclaratorsContext *ctx) = 0;
  virtual void exitVariableDeclarators(JavaParser::VariableDeclaratorsContext *ctx) = 0;

  virtual void enterVariableDeclarator(JavaParser::VariableDeclaratorContext *ctx) = 0;
  virtual void exitVariableDeclarator(JavaParser::VariableDeclaratorContext *ctx) = 0;

  virtual void enterVariableDeclaratorId(JavaParser::VariableDeclaratorIdContext *ctx) = 0;
  virtual void exitVariableDeclaratorId(JavaParser::VariableDeclaratorIdContext *ctx) = 0;

  virtual void enterVariableInitializer(JavaParser::VariableInitializerContext *ctx) = 0;
  virtual void exitVariableInitializer(JavaParser::VariableInitializerContext *ctx) = 0;

  virtual void enterArrayInitializer(JavaParser::ArrayInitializerContext *ctx) = 0;
  virtual void exitArrayInitializer(JavaParser::ArrayInitializerContext *ctx) = 0;

  virtual void enterClassOrInterfaceType(JavaParser::ClassOrInterfaceTypeContext *ctx) = 0;
  virtual void exitClassOrInterfaceType(JavaParser::ClassOrInterfaceTypeContext *ctx) = 0;

  virtual void enterTypeArgument(JavaParser::TypeArgumentContext *ctx) = 0;
  virtual void exitTypeArgument(JavaParser::TypeArgumentContext *ctx) = 0;

  virtual void enterQualifiedNameList(JavaParser::QualifiedNameListContext *ctx) = 0;
  virtual void exitQualifiedNameList(JavaParser::QualifiedNameListContext *ctx) = 0;

  virtual void enterFormalParameters(JavaParser::FormalParametersContext *ctx) = 0;
  virtual void exitFormalParameters(JavaParser::FormalParametersContext *ctx) = 0;

  virtual void enterReceiverParameter(JavaParser::ReceiverParameterContext *ctx) = 0;
  virtual void exitReceiverParameter(JavaParser::ReceiverParameterContext *ctx) = 0;

  virtual void enterFormalParameterList(JavaParser::FormalParameterListContext *ctx) = 0;
  virtual void exitFormalParameterList(JavaParser::FormalParameterListContext *ctx) = 0;

  virtual void enterFormalParameter(JavaParser::FormalParameterContext *ctx) = 0;
  virtual void exitFormalParameter(JavaParser::FormalParameterContext *ctx) = 0;

  virtual void enterLastFormalParameter(JavaParser::LastFormalParameterContext *ctx) = 0;
  virtual void exitLastFormalParameter(JavaParser::LastFormalParameterContext *ctx) = 0;

  virtual void enterLambdaLVTIList(JavaParser::LambdaLVTIListContext *ctx) = 0;
  virtual void exitLambdaLVTIList(JavaParser::LambdaLVTIListContext *ctx) = 0;

  virtual void enterLambdaLVTIParameter(JavaParser::LambdaLVTIParameterContext *ctx) = 0;
  virtual void exitLambdaLVTIParameter(JavaParser::LambdaLVTIParameterContext *ctx) = 0;

  virtual void enterQualifiedName(JavaParser::QualifiedNameContext *ctx) = 0;
  virtual void exitQualifiedName(JavaParser::QualifiedNameContext *ctx) = 0;

  virtual void enterLiteral(JavaParser::LiteralContext *ctx) = 0;
  virtual void exitLiteral(JavaParser::LiteralContext *ctx) = 0;

  virtual void enterIntegerLiteral(JavaParser::IntegerLiteralContext *ctx) = 0;
  virtual void exitIntegerLiteral(JavaParser::IntegerLiteralContext *ctx) = 0;

  virtual void enterFloatLiteral(JavaParser::FloatLiteralContext *ctx) = 0;
  virtual void exitFloatLiteral(JavaParser::FloatLiteralContext *ctx) = 0;

  virtual void enterAltAnnotationQualifiedName(JavaParser::AltAnnotationQualifiedNameContext *ctx) = 0;
  virtual void exitAltAnnotationQualifiedName(JavaParser::AltAnnotationQualifiedNameContext *ctx) = 0;

  virtual void enterAnnotation(JavaParser::AnnotationContext *ctx) = 0;
  virtual void exitAnnotation(JavaParser::AnnotationContext *ctx) = 0;

  virtual void enterElementValuePairs(JavaParser::ElementValuePairsContext *ctx) = 0;
  virtual void exitElementValuePairs(JavaParser::ElementValuePairsContext *ctx) = 0;

  virtual void enterElementValuePair(JavaParser::ElementValuePairContext *ctx) = 0;
  virtual void exitElementValuePair(JavaParser::ElementValuePairContext *ctx) = 0;

  virtual void enterElementValue(JavaParser::ElementValueContext *ctx) = 0;
  virtual void exitElementValue(JavaParser::ElementValueContext *ctx) = 0;

  virtual void enterElementValueArrayInitializer(JavaParser::ElementValueArrayInitializerContext *ctx) = 0;
  virtual void exitElementValueArrayInitializer(JavaParser::ElementValueArrayInitializerContext *ctx) = 0;

  virtual void enterAnnotationTypeDeclaration(JavaParser::AnnotationTypeDeclarationContext *ctx) = 0;
  virtual void exitAnnotationTypeDeclaration(JavaParser::AnnotationTypeDeclarationContext *ctx) = 0;

  virtual void enterAnnotationTypeBody(JavaParser::AnnotationTypeBodyContext *ctx) = 0;
  virtual void exitAnnotationTypeBody(JavaParser::AnnotationTypeBodyContext *ctx) = 0;

  virtual void enterAnnotationTypeElementDeclaration(JavaParser::AnnotationTypeElementDeclarationContext *ctx) = 0;
  virtual void exitAnnotationTypeElementDeclaration(JavaParser::AnnotationTypeElementDeclarationContext *ctx) = 0;

  virtual void enterAnnotationTypeElementRest(JavaParser::AnnotationTypeElementRestContext *ctx) = 0;
  virtual void exitAnnotationTypeElementRest(JavaParser::AnnotationTypeElementRestContext *ctx) = 0;

  virtual void enterAnnotationMethodOrConstantRest(JavaParser::AnnotationMethodOrConstantRestContext *ctx) = 0;
  virtual void exitAnnotationMethodOrConstantRest(JavaParser::AnnotationMethodOrConstantRestContext *ctx) = 0;

  virtual void enterAnnotationMethodRest(JavaParser::AnnotationMethodRestContext *ctx) = 0;
  virtual void exitAnnotationMethodRest(JavaParser::AnnotationMethodRestContext *ctx) = 0;

  virtual void enterAnnotationConstantRest(JavaParser::AnnotationConstantRestContext *ctx) = 0;
  virtual void exitAnnotationConstantRest(JavaParser::AnnotationConstantRestContext *ctx) = 0;

  virtual void enterDefaultValue(JavaParser::DefaultValueContext *ctx) = 0;
  virtual void exitDefaultValue(JavaParser::DefaultValueContext *ctx) = 0;

  virtual void enterModuleDeclaration(JavaParser::ModuleDeclarationContext *ctx) = 0;
  virtual void exitModuleDeclaration(JavaParser::ModuleDeclarationContext *ctx) = 0;

  virtual void enterModuleBody(JavaParser::ModuleBodyContext *ctx) = 0;
  virtual void exitModuleBody(JavaParser::ModuleBodyContext *ctx) = 0;

  virtual void enterModuleDirective(JavaParser::ModuleDirectiveContext *ctx) = 0;
  virtual void exitModuleDirective(JavaParser::ModuleDirectiveContext *ctx) = 0;

  virtual void enterRequiresModifier(JavaParser::RequiresModifierContext *ctx) = 0;
  virtual void exitRequiresModifier(JavaParser::RequiresModifierContext *ctx) = 0;

  virtual void enterRecordDeclaration(JavaParser::RecordDeclarationContext *ctx) = 0;
  virtual void exitRecordDeclaration(JavaParser::RecordDeclarationContext *ctx) = 0;

  virtual void enterRecordHeader(JavaParser::RecordHeaderContext *ctx) = 0;
  virtual void exitRecordHeader(JavaParser::RecordHeaderContext *ctx) = 0;

  virtual void enterRecordComponentList(JavaParser::RecordComponentListContext *ctx) = 0;
  virtual void exitRecordComponentList(JavaParser::RecordComponentListContext *ctx) = 0;

  virtual void enterRecordComponent(JavaParser::RecordComponentContext *ctx) = 0;
  virtual void exitRecordComponent(JavaParser::RecordComponentContext *ctx) = 0;

  virtual void enterRecordBody(JavaParser::RecordBodyContext *ctx) = 0;
  virtual void exitRecordBody(JavaParser::RecordBodyContext *ctx) = 0;

  virtual void enterBlock(JavaParser::BlockContext *ctx) = 0;
  virtual void exitBlock(JavaParser::BlockContext *ctx) = 0;

  virtual void enterBlockStatement(JavaParser::BlockStatementContext *ctx) = 0;
  virtual void exitBlockStatement(JavaParser::BlockStatementContext *ctx) = 0;

  virtual void enterLocalVariableDeclaration(JavaParser::LocalVariableDeclarationContext *ctx) = 0;
  virtual void exitLocalVariableDeclaration(JavaParser::LocalVariableDeclarationContext *ctx) = 0;

  virtual void enterIdentifier(JavaParser::IdentifierContext *ctx) = 0;
  virtual void exitIdentifier(JavaParser::IdentifierContext *ctx) = 0;

  virtual void enterTypeIdentifier(JavaParser::TypeIdentifierContext *ctx) = 0;
  virtual void exitTypeIdentifier(JavaParser::TypeIdentifierContext *ctx) = 0;

  virtual void enterLocalTypeDeclaration(JavaParser::LocalTypeDeclarationContext *ctx) = 0;
  virtual void exitLocalTypeDeclaration(JavaParser::LocalTypeDeclarationContext *ctx) = 0;

  virtual void enterStatement(JavaParser::StatementContext *ctx) = 0;
  virtual void exitStatement(JavaParser::StatementContext *ctx) = 0;

  virtual void enterCompound_stmt(JavaParser::Compound_stmtContext *ctx) = 0;
  virtual void exitCompound_stmt(JavaParser::Compound_stmtContext *ctx) = 0;

  virtual void enterCatchClause(JavaParser::CatchClauseContext *ctx) = 0;
  virtual void exitCatchClause(JavaParser::CatchClauseContext *ctx) = 0;

  virtual void enterCatchType(JavaParser::CatchTypeContext *ctx) = 0;
  virtual void exitCatchType(JavaParser::CatchTypeContext *ctx) = 0;

  virtual void enterFinallyBlock(JavaParser::FinallyBlockContext *ctx) = 0;
  virtual void exitFinallyBlock(JavaParser::FinallyBlockContext *ctx) = 0;

  virtual void enterResourceSpecification(JavaParser::ResourceSpecificationContext *ctx) = 0;
  virtual void exitResourceSpecification(JavaParser::ResourceSpecificationContext *ctx) = 0;

  virtual void enterResources(JavaParser::ResourcesContext *ctx) = 0;
  virtual void exitResources(JavaParser::ResourcesContext *ctx) = 0;

  virtual void enterResource(JavaParser::ResourceContext *ctx) = 0;
  virtual void exitResource(JavaParser::ResourceContext *ctx) = 0;

  virtual void enterSwitchBlockStatementGroup(JavaParser::SwitchBlockStatementGroupContext *ctx) = 0;
  virtual void exitSwitchBlockStatementGroup(JavaParser::SwitchBlockStatementGroupContext *ctx) = 0;

  virtual void enterSwitchLabel(JavaParser::SwitchLabelContext *ctx) = 0;
  virtual void exitSwitchLabel(JavaParser::SwitchLabelContext *ctx) = 0;

  virtual void enterForControl(JavaParser::ForControlContext *ctx) = 0;
  virtual void exitForControl(JavaParser::ForControlContext *ctx) = 0;

  virtual void enterForInit(JavaParser::ForInitContext *ctx) = 0;
  virtual void exitForInit(JavaParser::ForInitContext *ctx) = 0;

  virtual void enterEnhancedForControl(JavaParser::EnhancedForControlContext *ctx) = 0;
  virtual void exitEnhancedForControl(JavaParser::EnhancedForControlContext *ctx) = 0;

  virtual void enterParExpression(JavaParser::ParExpressionContext *ctx) = 0;
  virtual void exitParExpression(JavaParser::ParExpressionContext *ctx) = 0;

  virtual void enterExpressionList(JavaParser::ExpressionListContext *ctx) = 0;
  virtual void exitExpressionList(JavaParser::ExpressionListContext *ctx) = 0;

  virtual void enterMethodCall(JavaParser::MethodCallContext *ctx) = 0;
  virtual void exitMethodCall(JavaParser::MethodCallContext *ctx) = 0;

  virtual void enterExpression(JavaParser::ExpressionContext *ctx) = 0;
  virtual void exitExpression(JavaParser::ExpressionContext *ctx) = 0;

  virtual void enterPattern(JavaParser::PatternContext *ctx) = 0;
  virtual void exitPattern(JavaParser::PatternContext *ctx) = 0;

  virtual void enterLambdaExpression(JavaParser::LambdaExpressionContext *ctx) = 0;
  virtual void exitLambdaExpression(JavaParser::LambdaExpressionContext *ctx) = 0;

  virtual void enterLambdaParameters(JavaParser::LambdaParametersContext *ctx) = 0;
  virtual void exitLambdaParameters(JavaParser::LambdaParametersContext *ctx) = 0;

  virtual void enterLambdaBody(JavaParser::LambdaBodyContext *ctx) = 0;
  virtual void exitLambdaBody(JavaParser::LambdaBodyContext *ctx) = 0;

  virtual void enterPrimary(JavaParser::PrimaryContext *ctx) = 0;
  virtual void exitPrimary(JavaParser::PrimaryContext *ctx) = 0;

  virtual void enterSwitchExpression(JavaParser::SwitchExpressionContext *ctx) = 0;
  virtual void exitSwitchExpression(JavaParser::SwitchExpressionContext *ctx) = 0;

  virtual void enterSwitchLabeledRule(JavaParser::SwitchLabeledRuleContext *ctx) = 0;
  virtual void exitSwitchLabeledRule(JavaParser::SwitchLabeledRuleContext *ctx) = 0;

  virtual void enterGuardedPattern(JavaParser::GuardedPatternContext *ctx) = 0;
  virtual void exitGuardedPattern(JavaParser::GuardedPatternContext *ctx) = 0;

  virtual void enterSwitchRuleOutcome(JavaParser::SwitchRuleOutcomeContext *ctx) = 0;
  virtual void exitSwitchRuleOutcome(JavaParser::SwitchRuleOutcomeContext *ctx) = 0;

  virtual void enterClassType(JavaParser::ClassTypeContext *ctx) = 0;
  virtual void exitClassType(JavaParser::ClassTypeContext *ctx) = 0;

  virtual void enterCreator(JavaParser::CreatorContext *ctx) = 0;
  virtual void exitCreator(JavaParser::CreatorContext *ctx) = 0;

  virtual void enterCreatedName(JavaParser::CreatedNameContext *ctx) = 0;
  virtual void exitCreatedName(JavaParser::CreatedNameContext *ctx) = 0;

  virtual void enterInnerCreator(JavaParser::InnerCreatorContext *ctx) = 0;
  virtual void exitInnerCreator(JavaParser::InnerCreatorContext *ctx) = 0;

  virtual void enterArrayCreatorRest(JavaParser::ArrayCreatorRestContext *ctx) = 0;
  virtual void exitArrayCreatorRest(JavaParser::ArrayCreatorRestContext *ctx) = 0;

  virtual void enterClassCreatorRest(JavaParser::ClassCreatorRestContext *ctx) = 0;
  virtual void exitClassCreatorRest(JavaParser::ClassCreatorRestContext *ctx) = 0;

  virtual void enterExplicitGenericInvocation(JavaParser::ExplicitGenericInvocationContext *ctx) = 0;
  virtual void exitExplicitGenericInvocation(JavaParser::ExplicitGenericInvocationContext *ctx) = 0;

  virtual void enterTypeArgumentsOrDiamond(JavaParser::TypeArgumentsOrDiamondContext *ctx) = 0;
  virtual void exitTypeArgumentsOrDiamond(JavaParser::TypeArgumentsOrDiamondContext *ctx) = 0;

  virtual void enterNonWildcardTypeArgumentsOrDiamond(JavaParser::NonWildcardTypeArgumentsOrDiamondContext *ctx) = 0;
  virtual void exitNonWildcardTypeArgumentsOrDiamond(JavaParser::NonWildcardTypeArgumentsOrDiamondContext *ctx) = 0;

  virtual void enterNonWildcardTypeArguments(JavaParser::NonWildcardTypeArgumentsContext *ctx) = 0;
  virtual void exitNonWildcardTypeArguments(JavaParser::NonWildcardTypeArgumentsContext *ctx) = 0;

  virtual void enterTypeList(JavaParser::TypeListContext *ctx) = 0;
  virtual void exitTypeList(JavaParser::TypeListContext *ctx) = 0;

  virtual void enterTypeType(JavaParser::TypeTypeContext *ctx) = 0;
  virtual void exitTypeType(JavaParser::TypeTypeContext *ctx) = 0;

  virtual void enterPrimitiveType(JavaParser::PrimitiveTypeContext *ctx) = 0;
  virtual void exitPrimitiveType(JavaParser::PrimitiveTypeContext *ctx) = 0;

  virtual void enterTypeArguments(JavaParser::TypeArgumentsContext *ctx) = 0;
  virtual void exitTypeArguments(JavaParser::TypeArgumentsContext *ctx) = 0;

  virtual void enterSuperSuffix(JavaParser::SuperSuffixContext *ctx) = 0;
  virtual void exitSuperSuffix(JavaParser::SuperSuffixContext *ctx) = 0;

  virtual void enterExplicitGenericInvocationSuffix(JavaParser::ExplicitGenericInvocationSuffixContext *ctx) = 0;
  virtual void exitExplicitGenericInvocationSuffix(JavaParser::ExplicitGenericInvocationSuffixContext *ctx) = 0;

  virtual void enterArguments(JavaParser::ArgumentsContext *ctx) = 0;
  virtual void exitArguments(JavaParser::ArgumentsContext *ctx) = 0;

  virtual void enterSimple_wildcard(JavaParser::Simple_wildcardContext *ctx) = 0;
  virtual void exitSimple_wildcard(JavaParser::Simple_wildcardContext *ctx) = 0;

  virtual void enterVar_wildcard(JavaParser::Var_wildcardContext *ctx) = 0;
  virtual void exitVar_wildcard(JavaParser::Var_wildcardContext *ctx) = 0;

  virtual void enterList_wildcard(JavaParser::List_wildcardContext *ctx) = 0;
  virtual void exitList_wildcard(JavaParser::List_wildcardContext *ctx) = 0;

  virtual void enterContains_wildcard(JavaParser::Contains_wildcardContext *ctx) = 0;
  virtual void exitContains_wildcard(JavaParser::Contains_wildcardContext *ctx) = 0;

  virtual void enterSimple_compound_wildcard(JavaParser::Simple_compound_wildcardContext *ctx) = 0;
  virtual void exitSimple_compound_wildcard(JavaParser::Simple_compound_wildcardContext *ctx) = 0;

  virtual void enterMultiple_compound_wildcard(JavaParser::Multiple_compound_wildcardContext *ctx) = 0;
  virtual void exitMultiple_compound_wildcard(JavaParser::Multiple_compound_wildcardContext *ctx) = 0;

  virtual void enterNumber_wildcard(JavaParser::Number_wildcardContext *ctx) = 0;
  virtual void exitNumber_wildcard(JavaParser::Number_wildcardContext *ctx) = 0;

  virtual void enterWildcard_number(JavaParser::Wildcard_numberContext *ctx) = 0;
  virtual void exitWildcard_number(JavaParser::Wildcard_numberContext *ctx) = 0;

  virtual void enterStmt_wildcard(JavaParser::Stmt_wildcardContext *ctx) = 0;
  virtual void exitStmt_wildcard(JavaParser::Stmt_wildcardContext *ctx) = 0;

  virtual void enterExpr_wildcard(JavaParser::Expr_wildcardContext *ctx) = 0;
  virtual void exitExpr_wildcard(JavaParser::Expr_wildcardContext *ctx) = 0;

  virtual void enterCompound_wildcard(JavaParser::Compound_wildcardContext *ctx) = 0;
  virtual void exitCompound_wildcard(JavaParser::Compound_wildcardContext *ctx) = 0;


};

