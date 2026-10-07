
// Generated from JavaParser.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"
#include "JavaParserListener.h"


/**
 * This class provides an empty implementation of JavaParserListener,
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
class  JavaParserBaseListener : public JavaParserListener {
public:

  virtual void enterCompilationUnit(JavaParser::CompilationUnitContext * /*ctx*/) override { }
  virtual void exitCompilationUnit(JavaParser::CompilationUnitContext * /*ctx*/) override { }

  virtual void enterPackageDeclaration(JavaParser::PackageDeclarationContext * /*ctx*/) override { }
  virtual void exitPackageDeclaration(JavaParser::PackageDeclarationContext * /*ctx*/) override { }

  virtual void enterImportDeclaration(JavaParser::ImportDeclarationContext * /*ctx*/) override { }
  virtual void exitImportDeclaration(JavaParser::ImportDeclarationContext * /*ctx*/) override { }

  virtual void enterTypeDeclaration(JavaParser::TypeDeclarationContext * /*ctx*/) override { }
  virtual void exitTypeDeclaration(JavaParser::TypeDeclarationContext * /*ctx*/) override { }

  virtual void enterModifier(JavaParser::ModifierContext * /*ctx*/) override { }
  virtual void exitModifier(JavaParser::ModifierContext * /*ctx*/) override { }

  virtual void enterClassOrInterfaceModifier(JavaParser::ClassOrInterfaceModifierContext * /*ctx*/) override { }
  virtual void exitClassOrInterfaceModifier(JavaParser::ClassOrInterfaceModifierContext * /*ctx*/) override { }

  virtual void enterVariableModifier(JavaParser::VariableModifierContext * /*ctx*/) override { }
  virtual void exitVariableModifier(JavaParser::VariableModifierContext * /*ctx*/) override { }

  virtual void enterClassDeclaration(JavaParser::ClassDeclarationContext * /*ctx*/) override { }
  virtual void exitClassDeclaration(JavaParser::ClassDeclarationContext * /*ctx*/) override { }

  virtual void enterTypeParameters(JavaParser::TypeParametersContext * /*ctx*/) override { }
  virtual void exitTypeParameters(JavaParser::TypeParametersContext * /*ctx*/) override { }

  virtual void enterTypeParameter(JavaParser::TypeParameterContext * /*ctx*/) override { }
  virtual void exitTypeParameter(JavaParser::TypeParameterContext * /*ctx*/) override { }

  virtual void enterTypeBound(JavaParser::TypeBoundContext * /*ctx*/) override { }
  virtual void exitTypeBound(JavaParser::TypeBoundContext * /*ctx*/) override { }

  virtual void enterEnumDeclaration(JavaParser::EnumDeclarationContext * /*ctx*/) override { }
  virtual void exitEnumDeclaration(JavaParser::EnumDeclarationContext * /*ctx*/) override { }

  virtual void enterEnumConstants(JavaParser::EnumConstantsContext * /*ctx*/) override { }
  virtual void exitEnumConstants(JavaParser::EnumConstantsContext * /*ctx*/) override { }

  virtual void enterEnumConstant(JavaParser::EnumConstantContext * /*ctx*/) override { }
  virtual void exitEnumConstant(JavaParser::EnumConstantContext * /*ctx*/) override { }

  virtual void enterEnumBodyDeclarations(JavaParser::EnumBodyDeclarationsContext * /*ctx*/) override { }
  virtual void exitEnumBodyDeclarations(JavaParser::EnumBodyDeclarationsContext * /*ctx*/) override { }

  virtual void enterInterfaceDeclaration(JavaParser::InterfaceDeclarationContext * /*ctx*/) override { }
  virtual void exitInterfaceDeclaration(JavaParser::InterfaceDeclarationContext * /*ctx*/) override { }

  virtual void enterClassBody(JavaParser::ClassBodyContext * /*ctx*/) override { }
  virtual void exitClassBody(JavaParser::ClassBodyContext * /*ctx*/) override { }

  virtual void enterInterfaceBody(JavaParser::InterfaceBodyContext * /*ctx*/) override { }
  virtual void exitInterfaceBody(JavaParser::InterfaceBodyContext * /*ctx*/) override { }

  virtual void enterClassBodyDeclaration(JavaParser::ClassBodyDeclarationContext * /*ctx*/) override { }
  virtual void exitClassBodyDeclaration(JavaParser::ClassBodyDeclarationContext * /*ctx*/) override { }

  virtual void enterMemberDeclaration(JavaParser::MemberDeclarationContext * /*ctx*/) override { }
  virtual void exitMemberDeclaration(JavaParser::MemberDeclarationContext * /*ctx*/) override { }

  virtual void enterMethodDeclaration(JavaParser::MethodDeclarationContext * /*ctx*/) override { }
  virtual void exitMethodDeclaration(JavaParser::MethodDeclarationContext * /*ctx*/) override { }

  virtual void enterMethodBody(JavaParser::MethodBodyContext * /*ctx*/) override { }
  virtual void exitMethodBody(JavaParser::MethodBodyContext * /*ctx*/) override { }

  virtual void enterTypeTypeOrVoid(JavaParser::TypeTypeOrVoidContext * /*ctx*/) override { }
  virtual void exitTypeTypeOrVoid(JavaParser::TypeTypeOrVoidContext * /*ctx*/) override { }

  virtual void enterGenericMethodDeclaration(JavaParser::GenericMethodDeclarationContext * /*ctx*/) override { }
  virtual void exitGenericMethodDeclaration(JavaParser::GenericMethodDeclarationContext * /*ctx*/) override { }

  virtual void enterGenericConstructorDeclaration(JavaParser::GenericConstructorDeclarationContext * /*ctx*/) override { }
  virtual void exitGenericConstructorDeclaration(JavaParser::GenericConstructorDeclarationContext * /*ctx*/) override { }

  virtual void enterConstructorDeclaration(JavaParser::ConstructorDeclarationContext * /*ctx*/) override { }
  virtual void exitConstructorDeclaration(JavaParser::ConstructorDeclarationContext * /*ctx*/) override { }

  virtual void enterCompactConstructorDeclaration(JavaParser::CompactConstructorDeclarationContext * /*ctx*/) override { }
  virtual void exitCompactConstructorDeclaration(JavaParser::CompactConstructorDeclarationContext * /*ctx*/) override { }

  virtual void enterFieldDeclaration(JavaParser::FieldDeclarationContext * /*ctx*/) override { }
  virtual void exitFieldDeclaration(JavaParser::FieldDeclarationContext * /*ctx*/) override { }

  virtual void enterInterfaceBodyDeclaration(JavaParser::InterfaceBodyDeclarationContext * /*ctx*/) override { }
  virtual void exitInterfaceBodyDeclaration(JavaParser::InterfaceBodyDeclarationContext * /*ctx*/) override { }

  virtual void enterInterfaceMemberDeclaration(JavaParser::InterfaceMemberDeclarationContext * /*ctx*/) override { }
  virtual void exitInterfaceMemberDeclaration(JavaParser::InterfaceMemberDeclarationContext * /*ctx*/) override { }

  virtual void enterConstDeclaration(JavaParser::ConstDeclarationContext * /*ctx*/) override { }
  virtual void exitConstDeclaration(JavaParser::ConstDeclarationContext * /*ctx*/) override { }

  virtual void enterConstantDeclarator(JavaParser::ConstantDeclaratorContext * /*ctx*/) override { }
  virtual void exitConstantDeclarator(JavaParser::ConstantDeclaratorContext * /*ctx*/) override { }

  virtual void enterInterfaceMethodDeclaration(JavaParser::InterfaceMethodDeclarationContext * /*ctx*/) override { }
  virtual void exitInterfaceMethodDeclaration(JavaParser::InterfaceMethodDeclarationContext * /*ctx*/) override { }

  virtual void enterInterfaceMethodModifier(JavaParser::InterfaceMethodModifierContext * /*ctx*/) override { }
  virtual void exitInterfaceMethodModifier(JavaParser::InterfaceMethodModifierContext * /*ctx*/) override { }

  virtual void enterGenericInterfaceMethodDeclaration(JavaParser::GenericInterfaceMethodDeclarationContext * /*ctx*/) override { }
  virtual void exitGenericInterfaceMethodDeclaration(JavaParser::GenericInterfaceMethodDeclarationContext * /*ctx*/) override { }

  virtual void enterInterfaceCommonBodyDeclaration(JavaParser::InterfaceCommonBodyDeclarationContext * /*ctx*/) override { }
  virtual void exitInterfaceCommonBodyDeclaration(JavaParser::InterfaceCommonBodyDeclarationContext * /*ctx*/) override { }

  virtual void enterVariableDeclarators(JavaParser::VariableDeclaratorsContext * /*ctx*/) override { }
  virtual void exitVariableDeclarators(JavaParser::VariableDeclaratorsContext * /*ctx*/) override { }

  virtual void enterVariableDeclarator(JavaParser::VariableDeclaratorContext * /*ctx*/) override { }
  virtual void exitVariableDeclarator(JavaParser::VariableDeclaratorContext * /*ctx*/) override { }

  virtual void enterVariableDeclaratorId(JavaParser::VariableDeclaratorIdContext * /*ctx*/) override { }
  virtual void exitVariableDeclaratorId(JavaParser::VariableDeclaratorIdContext * /*ctx*/) override { }

  virtual void enterVariableInitializer(JavaParser::VariableInitializerContext * /*ctx*/) override { }
  virtual void exitVariableInitializer(JavaParser::VariableInitializerContext * /*ctx*/) override { }

  virtual void enterArrayInitializer(JavaParser::ArrayInitializerContext * /*ctx*/) override { }
  virtual void exitArrayInitializer(JavaParser::ArrayInitializerContext * /*ctx*/) override { }

  virtual void enterClassOrInterfaceType(JavaParser::ClassOrInterfaceTypeContext * /*ctx*/) override { }
  virtual void exitClassOrInterfaceType(JavaParser::ClassOrInterfaceTypeContext * /*ctx*/) override { }

  virtual void enterTypeArgument(JavaParser::TypeArgumentContext * /*ctx*/) override { }
  virtual void exitTypeArgument(JavaParser::TypeArgumentContext * /*ctx*/) override { }

  virtual void enterQualifiedNameList(JavaParser::QualifiedNameListContext * /*ctx*/) override { }
  virtual void exitQualifiedNameList(JavaParser::QualifiedNameListContext * /*ctx*/) override { }

  virtual void enterFormalParameters(JavaParser::FormalParametersContext * /*ctx*/) override { }
  virtual void exitFormalParameters(JavaParser::FormalParametersContext * /*ctx*/) override { }

  virtual void enterReceiverParameter(JavaParser::ReceiverParameterContext * /*ctx*/) override { }
  virtual void exitReceiverParameter(JavaParser::ReceiverParameterContext * /*ctx*/) override { }

  virtual void enterFormalParameterList(JavaParser::FormalParameterListContext * /*ctx*/) override { }
  virtual void exitFormalParameterList(JavaParser::FormalParameterListContext * /*ctx*/) override { }

  virtual void enterFormalParameter(JavaParser::FormalParameterContext * /*ctx*/) override { }
  virtual void exitFormalParameter(JavaParser::FormalParameterContext * /*ctx*/) override { }

  virtual void enterLastFormalParameter(JavaParser::LastFormalParameterContext * /*ctx*/) override { }
  virtual void exitLastFormalParameter(JavaParser::LastFormalParameterContext * /*ctx*/) override { }

  virtual void enterLambdaLVTIList(JavaParser::LambdaLVTIListContext * /*ctx*/) override { }
  virtual void exitLambdaLVTIList(JavaParser::LambdaLVTIListContext * /*ctx*/) override { }

  virtual void enterLambdaLVTIParameter(JavaParser::LambdaLVTIParameterContext * /*ctx*/) override { }
  virtual void exitLambdaLVTIParameter(JavaParser::LambdaLVTIParameterContext * /*ctx*/) override { }

  virtual void enterQualifiedName(JavaParser::QualifiedNameContext * /*ctx*/) override { }
  virtual void exitQualifiedName(JavaParser::QualifiedNameContext * /*ctx*/) override { }

  virtual void enterLiteral(JavaParser::LiteralContext * /*ctx*/) override { }
  virtual void exitLiteral(JavaParser::LiteralContext * /*ctx*/) override { }

  virtual void enterIntegerLiteral(JavaParser::IntegerLiteralContext * /*ctx*/) override { }
  virtual void exitIntegerLiteral(JavaParser::IntegerLiteralContext * /*ctx*/) override { }

  virtual void enterFloatLiteral(JavaParser::FloatLiteralContext * /*ctx*/) override { }
  virtual void exitFloatLiteral(JavaParser::FloatLiteralContext * /*ctx*/) override { }

  virtual void enterAltAnnotationQualifiedName(JavaParser::AltAnnotationQualifiedNameContext * /*ctx*/) override { }
  virtual void exitAltAnnotationQualifiedName(JavaParser::AltAnnotationQualifiedNameContext * /*ctx*/) override { }

  virtual void enterAnnotation(JavaParser::AnnotationContext * /*ctx*/) override { }
  virtual void exitAnnotation(JavaParser::AnnotationContext * /*ctx*/) override { }

  virtual void enterElementValuePairs(JavaParser::ElementValuePairsContext * /*ctx*/) override { }
  virtual void exitElementValuePairs(JavaParser::ElementValuePairsContext * /*ctx*/) override { }

  virtual void enterElementValuePair(JavaParser::ElementValuePairContext * /*ctx*/) override { }
  virtual void exitElementValuePair(JavaParser::ElementValuePairContext * /*ctx*/) override { }

  virtual void enterElementValue(JavaParser::ElementValueContext * /*ctx*/) override { }
  virtual void exitElementValue(JavaParser::ElementValueContext * /*ctx*/) override { }

  virtual void enterElementValueArrayInitializer(JavaParser::ElementValueArrayInitializerContext * /*ctx*/) override { }
  virtual void exitElementValueArrayInitializer(JavaParser::ElementValueArrayInitializerContext * /*ctx*/) override { }

  virtual void enterAnnotationTypeDeclaration(JavaParser::AnnotationTypeDeclarationContext * /*ctx*/) override { }
  virtual void exitAnnotationTypeDeclaration(JavaParser::AnnotationTypeDeclarationContext * /*ctx*/) override { }

  virtual void enterAnnotationTypeBody(JavaParser::AnnotationTypeBodyContext * /*ctx*/) override { }
  virtual void exitAnnotationTypeBody(JavaParser::AnnotationTypeBodyContext * /*ctx*/) override { }

  virtual void enterAnnotationTypeElementDeclaration(JavaParser::AnnotationTypeElementDeclarationContext * /*ctx*/) override { }
  virtual void exitAnnotationTypeElementDeclaration(JavaParser::AnnotationTypeElementDeclarationContext * /*ctx*/) override { }

  virtual void enterAnnotationTypeElementRest(JavaParser::AnnotationTypeElementRestContext * /*ctx*/) override { }
  virtual void exitAnnotationTypeElementRest(JavaParser::AnnotationTypeElementRestContext * /*ctx*/) override { }

  virtual void enterAnnotationMethodOrConstantRest(JavaParser::AnnotationMethodOrConstantRestContext * /*ctx*/) override { }
  virtual void exitAnnotationMethodOrConstantRest(JavaParser::AnnotationMethodOrConstantRestContext * /*ctx*/) override { }

  virtual void enterAnnotationMethodRest(JavaParser::AnnotationMethodRestContext * /*ctx*/) override { }
  virtual void exitAnnotationMethodRest(JavaParser::AnnotationMethodRestContext * /*ctx*/) override { }

  virtual void enterAnnotationConstantRest(JavaParser::AnnotationConstantRestContext * /*ctx*/) override { }
  virtual void exitAnnotationConstantRest(JavaParser::AnnotationConstantRestContext * /*ctx*/) override { }

  virtual void enterDefaultValue(JavaParser::DefaultValueContext * /*ctx*/) override { }
  virtual void exitDefaultValue(JavaParser::DefaultValueContext * /*ctx*/) override { }

  virtual void enterModuleDeclaration(JavaParser::ModuleDeclarationContext * /*ctx*/) override { }
  virtual void exitModuleDeclaration(JavaParser::ModuleDeclarationContext * /*ctx*/) override { }

  virtual void enterModuleBody(JavaParser::ModuleBodyContext * /*ctx*/) override { }
  virtual void exitModuleBody(JavaParser::ModuleBodyContext * /*ctx*/) override { }

  virtual void enterModuleDirective(JavaParser::ModuleDirectiveContext * /*ctx*/) override { }
  virtual void exitModuleDirective(JavaParser::ModuleDirectiveContext * /*ctx*/) override { }

  virtual void enterRequiresModifier(JavaParser::RequiresModifierContext * /*ctx*/) override { }
  virtual void exitRequiresModifier(JavaParser::RequiresModifierContext * /*ctx*/) override { }

  virtual void enterRecordDeclaration(JavaParser::RecordDeclarationContext * /*ctx*/) override { }
  virtual void exitRecordDeclaration(JavaParser::RecordDeclarationContext * /*ctx*/) override { }

  virtual void enterRecordHeader(JavaParser::RecordHeaderContext * /*ctx*/) override { }
  virtual void exitRecordHeader(JavaParser::RecordHeaderContext * /*ctx*/) override { }

  virtual void enterRecordComponentList(JavaParser::RecordComponentListContext * /*ctx*/) override { }
  virtual void exitRecordComponentList(JavaParser::RecordComponentListContext * /*ctx*/) override { }

  virtual void enterRecordComponent(JavaParser::RecordComponentContext * /*ctx*/) override { }
  virtual void exitRecordComponent(JavaParser::RecordComponentContext * /*ctx*/) override { }

  virtual void enterRecordBody(JavaParser::RecordBodyContext * /*ctx*/) override { }
  virtual void exitRecordBody(JavaParser::RecordBodyContext * /*ctx*/) override { }

  virtual void enterBlock(JavaParser::BlockContext * /*ctx*/) override { }
  virtual void exitBlock(JavaParser::BlockContext * /*ctx*/) override { }

  virtual void enterBlockStatement(JavaParser::BlockStatementContext * /*ctx*/) override { }
  virtual void exitBlockStatement(JavaParser::BlockStatementContext * /*ctx*/) override { }

  virtual void enterLocalVariableDeclaration(JavaParser::LocalVariableDeclarationContext * /*ctx*/) override { }
  virtual void exitLocalVariableDeclaration(JavaParser::LocalVariableDeclarationContext * /*ctx*/) override { }

  virtual void enterIdentifier(JavaParser::IdentifierContext * /*ctx*/) override { }
  virtual void exitIdentifier(JavaParser::IdentifierContext * /*ctx*/) override { }

  virtual void enterTypeIdentifier(JavaParser::TypeIdentifierContext * /*ctx*/) override { }
  virtual void exitTypeIdentifier(JavaParser::TypeIdentifierContext * /*ctx*/) override { }

  virtual void enterLocalTypeDeclaration(JavaParser::LocalTypeDeclarationContext * /*ctx*/) override { }
  virtual void exitLocalTypeDeclaration(JavaParser::LocalTypeDeclarationContext * /*ctx*/) override { }

  virtual void enterStatement(JavaParser::StatementContext * /*ctx*/) override { }
  virtual void exitStatement(JavaParser::StatementContext * /*ctx*/) override { }

  virtual void enterCompound_stmt(JavaParser::Compound_stmtContext * /*ctx*/) override { }
  virtual void exitCompound_stmt(JavaParser::Compound_stmtContext * /*ctx*/) override { }

  virtual void enterCatchClause(JavaParser::CatchClauseContext * /*ctx*/) override { }
  virtual void exitCatchClause(JavaParser::CatchClauseContext * /*ctx*/) override { }

  virtual void enterCatchType(JavaParser::CatchTypeContext * /*ctx*/) override { }
  virtual void exitCatchType(JavaParser::CatchTypeContext * /*ctx*/) override { }

  virtual void enterFinallyBlock(JavaParser::FinallyBlockContext * /*ctx*/) override { }
  virtual void exitFinallyBlock(JavaParser::FinallyBlockContext * /*ctx*/) override { }

  virtual void enterResourceSpecification(JavaParser::ResourceSpecificationContext * /*ctx*/) override { }
  virtual void exitResourceSpecification(JavaParser::ResourceSpecificationContext * /*ctx*/) override { }

  virtual void enterResources(JavaParser::ResourcesContext * /*ctx*/) override { }
  virtual void exitResources(JavaParser::ResourcesContext * /*ctx*/) override { }

  virtual void enterResource(JavaParser::ResourceContext * /*ctx*/) override { }
  virtual void exitResource(JavaParser::ResourceContext * /*ctx*/) override { }

  virtual void enterSwitchBlockStatementGroup(JavaParser::SwitchBlockStatementGroupContext * /*ctx*/) override { }
  virtual void exitSwitchBlockStatementGroup(JavaParser::SwitchBlockStatementGroupContext * /*ctx*/) override { }

  virtual void enterSwitchLabel(JavaParser::SwitchLabelContext * /*ctx*/) override { }
  virtual void exitSwitchLabel(JavaParser::SwitchLabelContext * /*ctx*/) override { }

  virtual void enterForControl(JavaParser::ForControlContext * /*ctx*/) override { }
  virtual void exitForControl(JavaParser::ForControlContext * /*ctx*/) override { }

  virtual void enterForInit(JavaParser::ForInitContext * /*ctx*/) override { }
  virtual void exitForInit(JavaParser::ForInitContext * /*ctx*/) override { }

  virtual void enterEnhancedForControl(JavaParser::EnhancedForControlContext * /*ctx*/) override { }
  virtual void exitEnhancedForControl(JavaParser::EnhancedForControlContext * /*ctx*/) override { }

  virtual void enterParExpression(JavaParser::ParExpressionContext * /*ctx*/) override { }
  virtual void exitParExpression(JavaParser::ParExpressionContext * /*ctx*/) override { }

  virtual void enterExpressionList(JavaParser::ExpressionListContext * /*ctx*/) override { }
  virtual void exitExpressionList(JavaParser::ExpressionListContext * /*ctx*/) override { }

  virtual void enterMethodCall(JavaParser::MethodCallContext * /*ctx*/) override { }
  virtual void exitMethodCall(JavaParser::MethodCallContext * /*ctx*/) override { }

  virtual void enterExpression(JavaParser::ExpressionContext * /*ctx*/) override { }
  virtual void exitExpression(JavaParser::ExpressionContext * /*ctx*/) override { }

  virtual void enterPattern(JavaParser::PatternContext * /*ctx*/) override { }
  virtual void exitPattern(JavaParser::PatternContext * /*ctx*/) override { }

  virtual void enterLambdaExpression(JavaParser::LambdaExpressionContext * /*ctx*/) override { }
  virtual void exitLambdaExpression(JavaParser::LambdaExpressionContext * /*ctx*/) override { }

  virtual void enterLambdaParameters(JavaParser::LambdaParametersContext * /*ctx*/) override { }
  virtual void exitLambdaParameters(JavaParser::LambdaParametersContext * /*ctx*/) override { }

  virtual void enterLambdaBody(JavaParser::LambdaBodyContext * /*ctx*/) override { }
  virtual void exitLambdaBody(JavaParser::LambdaBodyContext * /*ctx*/) override { }

  virtual void enterPrimary(JavaParser::PrimaryContext * /*ctx*/) override { }
  virtual void exitPrimary(JavaParser::PrimaryContext * /*ctx*/) override { }

  virtual void enterSwitchExpression(JavaParser::SwitchExpressionContext * /*ctx*/) override { }
  virtual void exitSwitchExpression(JavaParser::SwitchExpressionContext * /*ctx*/) override { }

  virtual void enterSwitchLabeledRule(JavaParser::SwitchLabeledRuleContext * /*ctx*/) override { }
  virtual void exitSwitchLabeledRule(JavaParser::SwitchLabeledRuleContext * /*ctx*/) override { }

  virtual void enterGuardedPattern(JavaParser::GuardedPatternContext * /*ctx*/) override { }
  virtual void exitGuardedPattern(JavaParser::GuardedPatternContext * /*ctx*/) override { }

  virtual void enterSwitchRuleOutcome(JavaParser::SwitchRuleOutcomeContext * /*ctx*/) override { }
  virtual void exitSwitchRuleOutcome(JavaParser::SwitchRuleOutcomeContext * /*ctx*/) override { }

  virtual void enterClassType(JavaParser::ClassTypeContext * /*ctx*/) override { }
  virtual void exitClassType(JavaParser::ClassTypeContext * /*ctx*/) override { }

  virtual void enterCreator(JavaParser::CreatorContext * /*ctx*/) override { }
  virtual void exitCreator(JavaParser::CreatorContext * /*ctx*/) override { }

  virtual void enterCreatedName(JavaParser::CreatedNameContext * /*ctx*/) override { }
  virtual void exitCreatedName(JavaParser::CreatedNameContext * /*ctx*/) override { }

  virtual void enterInnerCreator(JavaParser::InnerCreatorContext * /*ctx*/) override { }
  virtual void exitInnerCreator(JavaParser::InnerCreatorContext * /*ctx*/) override { }

  virtual void enterArrayCreatorRest(JavaParser::ArrayCreatorRestContext * /*ctx*/) override { }
  virtual void exitArrayCreatorRest(JavaParser::ArrayCreatorRestContext * /*ctx*/) override { }

  virtual void enterClassCreatorRest(JavaParser::ClassCreatorRestContext * /*ctx*/) override { }
  virtual void exitClassCreatorRest(JavaParser::ClassCreatorRestContext * /*ctx*/) override { }

  virtual void enterExplicitGenericInvocation(JavaParser::ExplicitGenericInvocationContext * /*ctx*/) override { }
  virtual void exitExplicitGenericInvocation(JavaParser::ExplicitGenericInvocationContext * /*ctx*/) override { }

  virtual void enterTypeArgumentsOrDiamond(JavaParser::TypeArgumentsOrDiamondContext * /*ctx*/) override { }
  virtual void exitTypeArgumentsOrDiamond(JavaParser::TypeArgumentsOrDiamondContext * /*ctx*/) override { }

  virtual void enterNonWildcardTypeArgumentsOrDiamond(JavaParser::NonWildcardTypeArgumentsOrDiamondContext * /*ctx*/) override { }
  virtual void exitNonWildcardTypeArgumentsOrDiamond(JavaParser::NonWildcardTypeArgumentsOrDiamondContext * /*ctx*/) override { }

  virtual void enterNonWildcardTypeArguments(JavaParser::NonWildcardTypeArgumentsContext * /*ctx*/) override { }
  virtual void exitNonWildcardTypeArguments(JavaParser::NonWildcardTypeArgumentsContext * /*ctx*/) override { }

  virtual void enterTypeList(JavaParser::TypeListContext * /*ctx*/) override { }
  virtual void exitTypeList(JavaParser::TypeListContext * /*ctx*/) override { }

  virtual void enterTypeType(JavaParser::TypeTypeContext * /*ctx*/) override { }
  virtual void exitTypeType(JavaParser::TypeTypeContext * /*ctx*/) override { }

  virtual void enterPrimitiveType(JavaParser::PrimitiveTypeContext * /*ctx*/) override { }
  virtual void exitPrimitiveType(JavaParser::PrimitiveTypeContext * /*ctx*/) override { }

  virtual void enterTypeArguments(JavaParser::TypeArgumentsContext * /*ctx*/) override { }
  virtual void exitTypeArguments(JavaParser::TypeArgumentsContext * /*ctx*/) override { }

  virtual void enterSuperSuffix(JavaParser::SuperSuffixContext * /*ctx*/) override { }
  virtual void exitSuperSuffix(JavaParser::SuperSuffixContext * /*ctx*/) override { }

  virtual void enterExplicitGenericInvocationSuffix(JavaParser::ExplicitGenericInvocationSuffixContext * /*ctx*/) override { }
  virtual void exitExplicitGenericInvocationSuffix(JavaParser::ExplicitGenericInvocationSuffixContext * /*ctx*/) override { }

  virtual void enterArguments(JavaParser::ArgumentsContext * /*ctx*/) override { }
  virtual void exitArguments(JavaParser::ArgumentsContext * /*ctx*/) override { }

  virtual void enterSimple_wildcard(JavaParser::Simple_wildcardContext * /*ctx*/) override { }
  virtual void exitSimple_wildcard(JavaParser::Simple_wildcardContext * /*ctx*/) override { }

  virtual void enterVar_wildcard(JavaParser::Var_wildcardContext * /*ctx*/) override { }
  virtual void exitVar_wildcard(JavaParser::Var_wildcardContext * /*ctx*/) override { }

  virtual void enterList_wildcard(JavaParser::List_wildcardContext * /*ctx*/) override { }
  virtual void exitList_wildcard(JavaParser::List_wildcardContext * /*ctx*/) override { }

  virtual void enterContains_wildcard(JavaParser::Contains_wildcardContext * /*ctx*/) override { }
  virtual void exitContains_wildcard(JavaParser::Contains_wildcardContext * /*ctx*/) override { }

  virtual void enterSimple_compound_wildcard(JavaParser::Simple_compound_wildcardContext * /*ctx*/) override { }
  virtual void exitSimple_compound_wildcard(JavaParser::Simple_compound_wildcardContext * /*ctx*/) override { }

  virtual void enterMultiple_compound_wildcard(JavaParser::Multiple_compound_wildcardContext * /*ctx*/) override { }
  virtual void exitMultiple_compound_wildcard(JavaParser::Multiple_compound_wildcardContext * /*ctx*/) override { }

  virtual void enterNumber_wildcard(JavaParser::Number_wildcardContext * /*ctx*/) override { }
  virtual void exitNumber_wildcard(JavaParser::Number_wildcardContext * /*ctx*/) override { }

  virtual void enterWildcard_number(JavaParser::Wildcard_numberContext * /*ctx*/) override { }
  virtual void exitWildcard_number(JavaParser::Wildcard_numberContext * /*ctx*/) override { }

  virtual void enterStmt_wildcard(JavaParser::Stmt_wildcardContext * /*ctx*/) override { }
  virtual void exitStmt_wildcard(JavaParser::Stmt_wildcardContext * /*ctx*/) override { }

  virtual void enterExpr_wildcard(JavaParser::Expr_wildcardContext * /*ctx*/) override { }
  virtual void exitExpr_wildcard(JavaParser::Expr_wildcardContext * /*ctx*/) override { }

  virtual void enterCompound_wildcard(JavaParser::Compound_wildcardContext * /*ctx*/) override { }
  virtual void exitCompound_wildcard(JavaParser::Compound_wildcardContext * /*ctx*/) override { }


  virtual void enterEveryRule(antlr4::ParserRuleContext * /*ctx*/) override { }
  virtual void exitEveryRule(antlr4::ParserRuleContext * /*ctx*/) override { }
  virtual void visitTerminal(antlr4::tree::TerminalNode * /*node*/) override { }
  virtual void visitErrorNode(antlr4::tree::ErrorNode * /*node*/) override { }

};

