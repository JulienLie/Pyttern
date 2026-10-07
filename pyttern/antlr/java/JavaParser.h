
// Generated from JavaParser.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"




class  JavaParser : public antlr4::Parser {
public:
  enum {
    ABSTRACT = 1, ASSERT = 2, BOOLEAN = 3, BREAK = 4, BYTE = 5, CASE = 6, 
    CATCH = 7, CHAR = 8, CLASS = 9, CONST = 10, CONTINUE = 11, DEFAULT = 12, 
    DO = 13, DOUBLE = 14, ELSE = 15, ENUM = 16, EXTENDS = 17, FINAL = 18, 
    FINALLY = 19, FLOAT = 20, FOR = 21, IF = 22, GOTO = 23, IMPLEMENTS = 24, 
    IMPORT = 25, INSTANCEOF = 26, INT = 27, INTERFACE = 28, LONG = 29, NATIVE = 30, 
    NEW = 31, PACKAGE = 32, PRIVATE = 33, PROTECTED = 34, PUBLIC = 35, RETURN = 36, 
    SHORT = 37, STATIC = 38, STRICTFP = 39, SUPER = 40, SWITCH = 41, SYNCHRONIZED = 42, 
    THIS = 43, THROW = 44, THROWS = 45, TRANSIENT = 46, TRY = 47, VOID = 48, 
    VOLATILE = 49, WHILE = 50, MODULE = 51, OPEN = 52, REQUIRES = 53, EXPORTS = 54, 
    OPENS = 55, TO = 56, USES = 57, PROVIDES = 58, WITH = 59, TRANSITIVE = 60, 
    VAR = 61, YIELD = 62, RECORD = 63, SEALED = 64, PERMITS = 65, NON_SEALED = 66, 
    DECIMAL_LITERAL = 67, HEX_LITERAL = 68, OCT_LITERAL = 69, BINARY_LITERAL = 70, 
    FLOAT_LITERAL = 71, HEX_FLOAT_LITERAL = 72, BOOL_LITERAL = 73, CHAR_LITERAL = 74, 
    STRING_LITERAL = 75, TEXT_BLOCK = 76, NULL_LITERAL = 77, LPAREN = 78, 
    RPAREN = 79, LBRACE = 80, RBRACE = 81, LBRACK = 82, RBRACK = 83, SEMI = 84, 
    COMMA = 85, DOT = 86, ASSIGN = 87, GT = 88, LT = 89, BANG = 90, TILDE = 91, 
    QUESTION = 92, COLON = 93, EQUAL = 94, LE = 95, GE = 96, NOTEQUAL = 97, 
    AND = 98, OR = 99, INC = 100, DEC = 101, ADD = 102, SUB = 103, MUL = 104, 
    DIV = 105, BITAND = 106, BITOR = 107, CARET = 108, MOD = 109, ADD_ASSIGN = 110, 
    SUB_ASSIGN = 111, MUL_ASSIGN = 112, DIV_ASSIGN = 113, AND_ASSIGN = 114, 
    OR_ASSIGN = 115, XOR_ASSIGN = 116, MOD_ASSIGN = 117, LSHIFT_ASSIGN = 118, 
    RSHIFT_ASSIGN = 119, URSHIFT_ASSIGN = 120, WILDCARD_SPACE = 121, VAR_WILDCARD = 122, 
    WILDCARD = 123, ARROW = 124, COLONCOLON = 125, AT = 126, ELLIPSIS = 127, 
    WS = 128, COMMENT = 129, LINE_COMMENT = 130, IDENTIFIER = 131
  };

  enum {
    RuleCompilationUnit = 0, RulePackageDeclaration = 1, RuleImportDeclaration = 2, 
    RuleTypeDeclaration = 3, RuleModifier = 4, RuleClassOrInterfaceModifier = 5, 
    RuleVariableModifier = 6, RuleClassDeclaration = 7, RuleTypeParameters = 8, 
    RuleTypeParameter = 9, RuleTypeBound = 10, RuleEnumDeclaration = 11, 
    RuleEnumConstants = 12, RuleEnumConstant = 13, RuleEnumBodyDeclarations = 14, 
    RuleInterfaceDeclaration = 15, RuleClassBody = 16, RuleInterfaceBody = 17, 
    RuleClassBodyDeclaration = 18, RuleMemberDeclaration = 19, RuleMethodDeclaration = 20, 
    RuleMethodBody = 21, RuleTypeTypeOrVoid = 22, RuleGenericMethodDeclaration = 23, 
    RuleGenericConstructorDeclaration = 24, RuleConstructorDeclaration = 25, 
    RuleCompactConstructorDeclaration = 26, RuleFieldDeclaration = 27, RuleInterfaceBodyDeclaration = 28, 
    RuleInterfaceMemberDeclaration = 29, RuleConstDeclaration = 30, RuleConstantDeclarator = 31, 
    RuleInterfaceMethodDeclaration = 32, RuleInterfaceMethodModifier = 33, 
    RuleGenericInterfaceMethodDeclaration = 34, RuleInterfaceCommonBodyDeclaration = 35, 
    RuleVariableDeclarators = 36, RuleVariableDeclarator = 37, RuleVariableDeclaratorId = 38, 
    RuleVariableInitializer = 39, RuleArrayInitializer = 40, RuleClassOrInterfaceType = 41, 
    RuleTypeArgument = 42, RuleQualifiedNameList = 43, RuleFormalParameters = 44, 
    RuleReceiverParameter = 45, RuleFormalParameterList = 46, RuleFormalParameter = 47, 
    RuleLastFormalParameter = 48, RuleLambdaLVTIList = 49, RuleLambdaLVTIParameter = 50, 
    RuleQualifiedName = 51, RuleLiteral = 52, RuleIntegerLiteral = 53, RuleFloatLiteral = 54, 
    RuleAltAnnotationQualifiedName = 55, RuleAnnotation = 56, RuleElementValuePairs = 57, 
    RuleElementValuePair = 58, RuleElementValue = 59, RuleElementValueArrayInitializer = 60, 
    RuleAnnotationTypeDeclaration = 61, RuleAnnotationTypeBody = 62, RuleAnnotationTypeElementDeclaration = 63, 
    RuleAnnotationTypeElementRest = 64, RuleAnnotationMethodOrConstantRest = 65, 
    RuleAnnotationMethodRest = 66, RuleAnnotationConstantRest = 67, RuleDefaultValue = 68, 
    RuleModuleDeclaration = 69, RuleModuleBody = 70, RuleModuleDirective = 71, 
    RuleRequiresModifier = 72, RuleRecordDeclaration = 73, RuleRecordHeader = 74, 
    RuleRecordComponentList = 75, RuleRecordComponent = 76, RuleRecordBody = 77, 
    RuleBlock = 78, RuleBlockStatement = 79, RuleLocalVariableDeclaration = 80, 
    RuleIdentifier = 81, RuleTypeIdentifier = 82, RuleLocalTypeDeclaration = 83, 
    RuleStatement = 84, RuleCompound_stmt = 85, RuleCatchClause = 86, RuleCatchType = 87, 
    RuleFinallyBlock = 88, RuleResourceSpecification = 89, RuleResources = 90, 
    RuleResource = 91, RuleSwitchBlockStatementGroup = 92, RuleSwitchLabel = 93, 
    RuleForControl = 94, RuleForInit = 95, RuleEnhancedForControl = 96, 
    RuleParExpression = 97, RuleExpressionList = 98, RuleMethodCall = 99, 
    RuleExpression = 100, RulePattern = 101, RuleLambdaExpression = 102, 
    RuleLambdaParameters = 103, RuleLambdaBody = 104, RulePrimary = 105, 
    RuleSwitchExpression = 106, RuleSwitchLabeledRule = 107, RuleGuardedPattern = 108, 
    RuleSwitchRuleOutcome = 109, RuleClassType = 110, RuleCreator = 111, 
    RuleCreatedName = 112, RuleInnerCreator = 113, RuleArrayCreatorRest = 114, 
    RuleClassCreatorRest = 115, RuleExplicitGenericInvocation = 116, RuleTypeArgumentsOrDiamond = 117, 
    RuleNonWildcardTypeArgumentsOrDiamond = 118, RuleNonWildcardTypeArguments = 119, 
    RuleTypeList = 120, RuleTypeType = 121, RulePrimitiveType = 122, RuleTypeArguments = 123, 
    RuleSuperSuffix = 124, RuleExplicitGenericInvocationSuffix = 125, RuleArguments = 126, 
    RuleSimple_wildcard = 127, RuleVar_wildcard = 128, RuleList_wildcard = 129, 
    RuleContains_wildcard = 130, RuleSimple_compound_wildcard = 131, RuleMultiple_compound_wildcard = 132, 
    RuleNumber_wildcard = 133, RuleWildcard_number = 134, RuleStmt_wildcard = 135, 
    RuleExpr_wildcard = 136, RuleCompound_wildcard = 137
  };

  explicit JavaParser(antlr4::TokenStream *input);

  JavaParser(antlr4::TokenStream *input, const antlr4::atn::ParserATNSimulatorOptions &options);

  ~JavaParser() override;

  std::string getGrammarFileName() const override;

  const antlr4::atn::ATN& getATN() const override;

  const std::vector<std::string>& getRuleNames() const override;

  const antlr4::dfa::Vocabulary& getVocabulary() const override;

  antlr4::atn::SerializedATNView getSerializedATN() const override;


  class CompilationUnitContext;
  class PackageDeclarationContext;
  class ImportDeclarationContext;
  class TypeDeclarationContext;
  class ModifierContext;
  class ClassOrInterfaceModifierContext;
  class VariableModifierContext;
  class ClassDeclarationContext;
  class TypeParametersContext;
  class TypeParameterContext;
  class TypeBoundContext;
  class EnumDeclarationContext;
  class EnumConstantsContext;
  class EnumConstantContext;
  class EnumBodyDeclarationsContext;
  class InterfaceDeclarationContext;
  class ClassBodyContext;
  class InterfaceBodyContext;
  class ClassBodyDeclarationContext;
  class MemberDeclarationContext;
  class MethodDeclarationContext;
  class MethodBodyContext;
  class TypeTypeOrVoidContext;
  class GenericMethodDeclarationContext;
  class GenericConstructorDeclarationContext;
  class ConstructorDeclarationContext;
  class CompactConstructorDeclarationContext;
  class FieldDeclarationContext;
  class InterfaceBodyDeclarationContext;
  class InterfaceMemberDeclarationContext;
  class ConstDeclarationContext;
  class ConstantDeclaratorContext;
  class InterfaceMethodDeclarationContext;
  class InterfaceMethodModifierContext;
  class GenericInterfaceMethodDeclarationContext;
  class InterfaceCommonBodyDeclarationContext;
  class VariableDeclaratorsContext;
  class VariableDeclaratorContext;
  class VariableDeclaratorIdContext;
  class VariableInitializerContext;
  class ArrayInitializerContext;
  class ClassOrInterfaceTypeContext;
  class TypeArgumentContext;
  class QualifiedNameListContext;
  class FormalParametersContext;
  class ReceiverParameterContext;
  class FormalParameterListContext;
  class FormalParameterContext;
  class LastFormalParameterContext;
  class LambdaLVTIListContext;
  class LambdaLVTIParameterContext;
  class QualifiedNameContext;
  class LiteralContext;
  class IntegerLiteralContext;
  class FloatLiteralContext;
  class AltAnnotationQualifiedNameContext;
  class AnnotationContext;
  class ElementValuePairsContext;
  class ElementValuePairContext;
  class ElementValueContext;
  class ElementValueArrayInitializerContext;
  class AnnotationTypeDeclarationContext;
  class AnnotationTypeBodyContext;
  class AnnotationTypeElementDeclarationContext;
  class AnnotationTypeElementRestContext;
  class AnnotationMethodOrConstantRestContext;
  class AnnotationMethodRestContext;
  class AnnotationConstantRestContext;
  class DefaultValueContext;
  class ModuleDeclarationContext;
  class ModuleBodyContext;
  class ModuleDirectiveContext;
  class RequiresModifierContext;
  class RecordDeclarationContext;
  class RecordHeaderContext;
  class RecordComponentListContext;
  class RecordComponentContext;
  class RecordBodyContext;
  class BlockContext;
  class BlockStatementContext;
  class LocalVariableDeclarationContext;
  class IdentifierContext;
  class TypeIdentifierContext;
  class LocalTypeDeclarationContext;
  class StatementContext;
  class Compound_stmtContext;
  class CatchClauseContext;
  class CatchTypeContext;
  class FinallyBlockContext;
  class ResourceSpecificationContext;
  class ResourcesContext;
  class ResourceContext;
  class SwitchBlockStatementGroupContext;
  class SwitchLabelContext;
  class ForControlContext;
  class ForInitContext;
  class EnhancedForControlContext;
  class ParExpressionContext;
  class ExpressionListContext;
  class MethodCallContext;
  class ExpressionContext;
  class PatternContext;
  class LambdaExpressionContext;
  class LambdaParametersContext;
  class LambdaBodyContext;
  class PrimaryContext;
  class SwitchExpressionContext;
  class SwitchLabeledRuleContext;
  class GuardedPatternContext;
  class SwitchRuleOutcomeContext;
  class ClassTypeContext;
  class CreatorContext;
  class CreatedNameContext;
  class InnerCreatorContext;
  class ArrayCreatorRestContext;
  class ClassCreatorRestContext;
  class ExplicitGenericInvocationContext;
  class TypeArgumentsOrDiamondContext;
  class NonWildcardTypeArgumentsOrDiamondContext;
  class NonWildcardTypeArgumentsContext;
  class TypeListContext;
  class TypeTypeContext;
  class PrimitiveTypeContext;
  class TypeArgumentsContext;
  class SuperSuffixContext;
  class ExplicitGenericInvocationSuffixContext;
  class ArgumentsContext;
  class Simple_wildcardContext;
  class Var_wildcardContext;
  class List_wildcardContext;
  class Contains_wildcardContext;
  class Simple_compound_wildcardContext;
  class Multiple_compound_wildcardContext;
  class Number_wildcardContext;
  class Wildcard_numberContext;
  class Stmt_wildcardContext;
  class Expr_wildcardContext;
  class Compound_wildcardContext; 

  class  CompilationUnitContext : public antlr4::ParserRuleContext {
  public:
    CompilationUnitContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    PackageDeclarationContext *packageDeclaration();
    std::vector<ImportDeclarationContext *> importDeclaration();
    ImportDeclarationContext* importDeclaration(size_t i);
    std::vector<antlr4::tree::TerminalNode *> SEMI();
    antlr4::tree::TerminalNode* SEMI(size_t i);
    std::vector<TypeDeclarationContext *> typeDeclaration();
    TypeDeclarationContext* typeDeclaration(size_t i);
    ModuleDeclarationContext *moduleDeclaration();
    antlr4::tree::TerminalNode *EOF();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  CompilationUnitContext* compilationUnit();

  class  PackageDeclarationContext : public antlr4::ParserRuleContext {
  public:
    PackageDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *PACKAGE();
    QualifiedNameContext *qualifiedName();
    antlr4::tree::TerminalNode *SEMI();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  PackageDeclarationContext* packageDeclaration();

  class  ImportDeclarationContext : public antlr4::ParserRuleContext {
  public:
    ImportDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *IMPORT();
    QualifiedNameContext *qualifiedName();
    antlr4::tree::TerminalNode *SEMI();
    antlr4::tree::TerminalNode *STATIC();
    antlr4::tree::TerminalNode *DOT();
    antlr4::tree::TerminalNode *MUL();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ImportDeclarationContext* importDeclaration();

  class  TypeDeclarationContext : public antlr4::ParserRuleContext {
  public:
    TypeDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ClassDeclarationContext *classDeclaration();
    EnumDeclarationContext *enumDeclaration();
    InterfaceDeclarationContext *interfaceDeclaration();
    AnnotationTypeDeclarationContext *annotationTypeDeclaration();
    RecordDeclarationContext *recordDeclaration();
    std::vector<ClassOrInterfaceModifierContext *> classOrInterfaceModifier();
    ClassOrInterfaceModifierContext* classOrInterfaceModifier(size_t i);
    Multiple_compound_wildcardContext *multiple_compound_wildcard();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeDeclarationContext* typeDeclaration();

  class  ModifierContext : public antlr4::ParserRuleContext {
  public:
    ModifierContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ClassOrInterfaceModifierContext *classOrInterfaceModifier();
    antlr4::tree::TerminalNode *NATIVE();
    antlr4::tree::TerminalNode *SYNCHRONIZED();
    antlr4::tree::TerminalNode *TRANSIENT();
    antlr4::tree::TerminalNode *VOLATILE();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ModifierContext* modifier();

  class  ClassOrInterfaceModifierContext : public antlr4::ParserRuleContext {
  public:
    ClassOrInterfaceModifierContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    AnnotationContext *annotation();
    antlr4::tree::TerminalNode *PUBLIC();
    antlr4::tree::TerminalNode *PROTECTED();
    antlr4::tree::TerminalNode *PRIVATE();
    antlr4::tree::TerminalNode *STATIC();
    antlr4::tree::TerminalNode *ABSTRACT();
    antlr4::tree::TerminalNode *FINAL();
    antlr4::tree::TerminalNode *STRICTFP();
    antlr4::tree::TerminalNode *SEALED();
    antlr4::tree::TerminalNode *NON_SEALED();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ClassOrInterfaceModifierContext* classOrInterfaceModifier();

  class  VariableModifierContext : public antlr4::ParserRuleContext {
  public:
    VariableModifierContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FINAL();
    AnnotationContext *annotation();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  VariableModifierContext* variableModifier();

  class  ClassDeclarationContext : public antlr4::ParserRuleContext {
  public:
    ClassDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *CLASS();
    IdentifierContext *identifier();
    ClassBodyContext *classBody();
    TypeParametersContext *typeParameters();
    antlr4::tree::TerminalNode *EXTENDS();
    TypeTypeContext *typeType();
    antlr4::tree::TerminalNode *IMPLEMENTS();
    std::vector<TypeListContext *> typeList();
    TypeListContext* typeList(size_t i);
    antlr4::tree::TerminalNode *PERMITS();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ClassDeclarationContext* classDeclaration();

  class  TypeParametersContext : public antlr4::ParserRuleContext {
  public:
    TypeParametersContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LT();
    std::vector<TypeParameterContext *> typeParameter();
    TypeParameterContext* typeParameter(size_t i);
    antlr4::tree::TerminalNode *GT();
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeParametersContext* typeParameters();

  class  TypeParameterContext : public antlr4::ParserRuleContext {
  public:
    TypeParameterContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    antlr4::tree::TerminalNode *EXTENDS();
    TypeBoundContext *typeBound();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeParameterContext* typeParameter();

  class  TypeBoundContext : public antlr4::ParserRuleContext {
  public:
    TypeBoundContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<TypeTypeContext *> typeType();
    TypeTypeContext* typeType(size_t i);
    std::vector<antlr4::tree::TerminalNode *> BITAND();
    antlr4::tree::TerminalNode* BITAND(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeBoundContext* typeBound();

  class  EnumDeclarationContext : public antlr4::ParserRuleContext {
  public:
    EnumDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *ENUM();
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    antlr4::tree::TerminalNode *IMPLEMENTS();
    TypeListContext *typeList();
    EnumConstantsContext *enumConstants();
    antlr4::tree::TerminalNode *COMMA();
    EnumBodyDeclarationsContext *enumBodyDeclarations();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  EnumDeclarationContext* enumDeclaration();

  class  EnumConstantsContext : public antlr4::ParserRuleContext {
  public:
    EnumConstantsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<EnumConstantContext *> enumConstant();
    EnumConstantContext* enumConstant(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  EnumConstantsContext* enumConstants();

  class  EnumConstantContext : public antlr4::ParserRuleContext {
  public:
    EnumConstantContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    ArgumentsContext *arguments();
    ClassBodyContext *classBody();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  EnumConstantContext* enumConstant();

  class  EnumBodyDeclarationsContext : public antlr4::ParserRuleContext {
  public:
    EnumBodyDeclarationsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *SEMI();
    std::vector<ClassBodyDeclarationContext *> classBodyDeclaration();
    ClassBodyDeclarationContext* classBodyDeclaration(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  EnumBodyDeclarationsContext* enumBodyDeclarations();

  class  InterfaceDeclarationContext : public antlr4::ParserRuleContext {
  public:
    InterfaceDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *INTERFACE();
    IdentifierContext *identifier();
    InterfaceBodyContext *interfaceBody();
    TypeParametersContext *typeParameters();
    antlr4::tree::TerminalNode *EXTENDS();
    std::vector<TypeListContext *> typeList();
    TypeListContext* typeList(size_t i);
    antlr4::tree::TerminalNode *PERMITS();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InterfaceDeclarationContext* interfaceDeclaration();

  class  ClassBodyContext : public antlr4::ParserRuleContext {
  public:
    ClassBodyContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<ClassBodyDeclarationContext *> classBodyDeclaration();
    ClassBodyDeclarationContext* classBodyDeclaration(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ClassBodyContext* classBody();

  class  InterfaceBodyContext : public antlr4::ParserRuleContext {
  public:
    InterfaceBodyContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<InterfaceBodyDeclarationContext *> interfaceBodyDeclaration();
    InterfaceBodyDeclarationContext* interfaceBodyDeclaration(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InterfaceBodyContext* interfaceBody();

  class  ClassBodyDeclarationContext : public antlr4::ParserRuleContext {
  public:
    ClassBodyDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *SEMI();
    BlockContext *block();
    antlr4::tree::TerminalNode *STATIC();
    MemberDeclarationContext *memberDeclaration();
    std::vector<ModifierContext *> modifier();
    ModifierContext* modifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ClassBodyDeclarationContext* classBodyDeclaration();

  class  MemberDeclarationContext : public antlr4::ParserRuleContext {
  public:
    MemberDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    RecordDeclarationContext *recordDeclaration();
    MethodDeclarationContext *methodDeclaration();
    GenericMethodDeclarationContext *genericMethodDeclaration();
    FieldDeclarationContext *fieldDeclaration();
    ConstructorDeclarationContext *constructorDeclaration();
    GenericConstructorDeclarationContext *genericConstructorDeclaration();
    InterfaceDeclarationContext *interfaceDeclaration();
    AnnotationTypeDeclarationContext *annotationTypeDeclaration();
    ClassDeclarationContext *classDeclaration();
    EnumDeclarationContext *enumDeclaration();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  MemberDeclarationContext* memberDeclaration();

  class  MethodDeclarationContext : public antlr4::ParserRuleContext {
  public:
    MethodDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeOrVoidContext *typeTypeOrVoid();
    IdentifierContext *identifier();
    FormalParametersContext *formalParameters();
    MethodBodyContext *methodBody();
    std::vector<antlr4::tree::TerminalNode *> LBRACK();
    antlr4::tree::TerminalNode* LBRACK(size_t i);
    std::vector<antlr4::tree::TerminalNode *> RBRACK();
    antlr4::tree::TerminalNode* RBRACK(size_t i);
    antlr4::tree::TerminalNode *THROWS();
    QualifiedNameListContext *qualifiedNameList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  MethodDeclarationContext* methodDeclaration();

  class  MethodBodyContext : public antlr4::ParserRuleContext {
  public:
    MethodBodyContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    BlockContext *block();
    antlr4::tree::TerminalNode *SEMI();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  MethodBodyContext* methodBody();

  class  TypeTypeOrVoidContext : public antlr4::ParserRuleContext {
  public:
    TypeTypeOrVoidContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    antlr4::tree::TerminalNode *VOID();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeTypeOrVoidContext* typeTypeOrVoid();

  class  GenericMethodDeclarationContext : public antlr4::ParserRuleContext {
  public:
    GenericMethodDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeParametersContext *typeParameters();
    MethodDeclarationContext *methodDeclaration();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  GenericMethodDeclarationContext* genericMethodDeclaration();

  class  GenericConstructorDeclarationContext : public antlr4::ParserRuleContext {
  public:
    GenericConstructorDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeParametersContext *typeParameters();
    ConstructorDeclarationContext *constructorDeclaration();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  GenericConstructorDeclarationContext* genericConstructorDeclaration();

  class  ConstructorDeclarationContext : public antlr4::ParserRuleContext {
  public:
    JavaParser::BlockContext *constructorBody = nullptr;
    ConstructorDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    FormalParametersContext *formalParameters();
    BlockContext *block();
    antlr4::tree::TerminalNode *THROWS();
    QualifiedNameListContext *qualifiedNameList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ConstructorDeclarationContext* constructorDeclaration();

  class  CompactConstructorDeclarationContext : public antlr4::ParserRuleContext {
  public:
    JavaParser::BlockContext *constructorBody = nullptr;
    CompactConstructorDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    BlockContext *block();
    std::vector<ModifierContext *> modifier();
    ModifierContext* modifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  CompactConstructorDeclarationContext* compactConstructorDeclaration();

  class  FieldDeclarationContext : public antlr4::ParserRuleContext {
  public:
    FieldDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    VariableDeclaratorsContext *variableDeclarators();
    antlr4::tree::TerminalNode *SEMI();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  FieldDeclarationContext* fieldDeclaration();

  class  InterfaceBodyDeclarationContext : public antlr4::ParserRuleContext {
  public:
    InterfaceBodyDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    InterfaceMemberDeclarationContext *interfaceMemberDeclaration();
    std::vector<ModifierContext *> modifier();
    ModifierContext* modifier(size_t i);
    antlr4::tree::TerminalNode *SEMI();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InterfaceBodyDeclarationContext* interfaceBodyDeclaration();

  class  InterfaceMemberDeclarationContext : public antlr4::ParserRuleContext {
  public:
    InterfaceMemberDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    RecordDeclarationContext *recordDeclaration();
    ConstDeclarationContext *constDeclaration();
    InterfaceMethodDeclarationContext *interfaceMethodDeclaration();
    GenericInterfaceMethodDeclarationContext *genericInterfaceMethodDeclaration();
    InterfaceDeclarationContext *interfaceDeclaration();
    AnnotationTypeDeclarationContext *annotationTypeDeclaration();
    ClassDeclarationContext *classDeclaration();
    EnumDeclarationContext *enumDeclaration();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InterfaceMemberDeclarationContext* interfaceMemberDeclaration();

  class  ConstDeclarationContext : public antlr4::ParserRuleContext {
  public:
    ConstDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    std::vector<ConstantDeclaratorContext *> constantDeclarator();
    ConstantDeclaratorContext* constantDeclarator(size_t i);
    antlr4::tree::TerminalNode *SEMI();
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ConstDeclarationContext* constDeclaration();

  class  ConstantDeclaratorContext : public antlr4::ParserRuleContext {
  public:
    ConstantDeclaratorContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *ASSIGN();
    VariableInitializerContext *variableInitializer();
    std::vector<antlr4::tree::TerminalNode *> LBRACK();
    antlr4::tree::TerminalNode* LBRACK(size_t i);
    std::vector<antlr4::tree::TerminalNode *> RBRACK();
    antlr4::tree::TerminalNode* RBRACK(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ConstantDeclaratorContext* constantDeclarator();

  class  InterfaceMethodDeclarationContext : public antlr4::ParserRuleContext {
  public:
    InterfaceMethodDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    InterfaceCommonBodyDeclarationContext *interfaceCommonBodyDeclaration();
    std::vector<InterfaceMethodModifierContext *> interfaceMethodModifier();
    InterfaceMethodModifierContext* interfaceMethodModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InterfaceMethodDeclarationContext* interfaceMethodDeclaration();

  class  InterfaceMethodModifierContext : public antlr4::ParserRuleContext {
  public:
    InterfaceMethodModifierContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    AnnotationContext *annotation();
    antlr4::tree::TerminalNode *PUBLIC();
    antlr4::tree::TerminalNode *ABSTRACT();
    antlr4::tree::TerminalNode *DEFAULT();
    antlr4::tree::TerminalNode *STATIC();
    antlr4::tree::TerminalNode *STRICTFP();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InterfaceMethodModifierContext* interfaceMethodModifier();

  class  GenericInterfaceMethodDeclarationContext : public antlr4::ParserRuleContext {
  public:
    GenericInterfaceMethodDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeParametersContext *typeParameters();
    InterfaceCommonBodyDeclarationContext *interfaceCommonBodyDeclaration();
    std::vector<InterfaceMethodModifierContext *> interfaceMethodModifier();
    InterfaceMethodModifierContext* interfaceMethodModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  GenericInterfaceMethodDeclarationContext* genericInterfaceMethodDeclaration();

  class  InterfaceCommonBodyDeclarationContext : public antlr4::ParserRuleContext {
  public:
    InterfaceCommonBodyDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeOrVoidContext *typeTypeOrVoid();
    IdentifierContext *identifier();
    FormalParametersContext *formalParameters();
    MethodBodyContext *methodBody();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    std::vector<antlr4::tree::TerminalNode *> LBRACK();
    antlr4::tree::TerminalNode* LBRACK(size_t i);
    std::vector<antlr4::tree::TerminalNode *> RBRACK();
    antlr4::tree::TerminalNode* RBRACK(size_t i);
    antlr4::tree::TerminalNode *THROWS();
    QualifiedNameListContext *qualifiedNameList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InterfaceCommonBodyDeclarationContext* interfaceCommonBodyDeclaration();

  class  VariableDeclaratorsContext : public antlr4::ParserRuleContext {
  public:
    VariableDeclaratorsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<VariableDeclaratorContext *> variableDeclarator();
    VariableDeclaratorContext* variableDeclarator(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  VariableDeclaratorsContext* variableDeclarators();

  class  VariableDeclaratorContext : public antlr4::ParserRuleContext {
  public:
    VariableDeclaratorContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    VariableDeclaratorIdContext *variableDeclaratorId();
    antlr4::tree::TerminalNode *ASSIGN();
    VariableInitializerContext *variableInitializer();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  VariableDeclaratorContext* variableDeclarator();

  class  VariableDeclaratorIdContext : public antlr4::ParserRuleContext {
  public:
    VariableDeclaratorIdContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Simple_wildcardContext *simple_wildcard();
    Var_wildcardContext *var_wildcard();
    IdentifierContext *identifier();
    std::vector<antlr4::tree::TerminalNode *> LBRACK();
    antlr4::tree::TerminalNode* LBRACK(size_t i);
    std::vector<antlr4::tree::TerminalNode *> RBRACK();
    antlr4::tree::TerminalNode* RBRACK(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  VariableDeclaratorIdContext* variableDeclaratorId();

  class  VariableInitializerContext : public antlr4::ParserRuleContext {
  public:
    VariableInitializerContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ArrayInitializerContext *arrayInitializer();
    ExpressionContext *expression();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  VariableInitializerContext* variableInitializer();

  class  ArrayInitializerContext : public antlr4::ParserRuleContext {
  public:
    ArrayInitializerContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<List_wildcardContext *> list_wildcard();
    List_wildcardContext* list_wildcard(size_t i);
    std::vector<Number_wildcardContext *> number_wildcard();
    Number_wildcardContext* number_wildcard(size_t i);
    std::vector<VariableInitializerContext *> variableInitializer();
    VariableInitializerContext* variableInitializer(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ArrayInitializerContext* arrayInitializer();

  class  ClassOrInterfaceTypeContext : public antlr4::ParserRuleContext {
  public:
    ClassOrInterfaceTypeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeIdentifierContext *typeIdentifier();
    std::vector<IdentifierContext *> identifier();
    IdentifierContext* identifier(size_t i);
    std::vector<antlr4::tree::TerminalNode *> DOT();
    antlr4::tree::TerminalNode* DOT(size_t i);
    std::vector<TypeArgumentsContext *> typeArguments();
    TypeArgumentsContext* typeArguments(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ClassOrInterfaceTypeContext* classOrInterfaceType();

  class  TypeArgumentContext : public antlr4::ParserRuleContext {
  public:
    TypeArgumentContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    antlr4::tree::TerminalNode *QUESTION();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    antlr4::tree::TerminalNode *EXTENDS();
    antlr4::tree::TerminalNode *SUPER();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeArgumentContext* typeArgument();

  class  QualifiedNameListContext : public antlr4::ParserRuleContext {
  public:
    QualifiedNameListContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<QualifiedNameContext *> qualifiedName();
    QualifiedNameContext* qualifiedName(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  QualifiedNameListContext* qualifiedNameList();

  class  FormalParametersContext : public antlr4::ParserRuleContext {
  public:
    FormalParametersContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    antlr4::tree::TerminalNode *RPAREN();
    ReceiverParameterContext *receiverParameter();
    antlr4::tree::TerminalNode *COMMA();
    FormalParameterListContext *formalParameterList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  FormalParametersContext* formalParameters();

  class  ReceiverParameterContext : public antlr4::ParserRuleContext {
  public:
    ReceiverParameterContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    antlr4::tree::TerminalNode *THIS();
    std::vector<IdentifierContext *> identifier();
    IdentifierContext* identifier(size_t i);
    std::vector<antlr4::tree::TerminalNode *> DOT();
    antlr4::tree::TerminalNode* DOT(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ReceiverParameterContext* receiverParameter();

  class  FormalParameterListContext : public antlr4::ParserRuleContext {
  public:
    FormalParameterListContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<List_wildcardContext *> list_wildcard();
    List_wildcardContext* list_wildcard(size_t i);
    std::vector<Number_wildcardContext *> number_wildcard();
    Number_wildcardContext* number_wildcard(size_t i);
    std::vector<FormalParameterContext *> formalParameter();
    FormalParameterContext* formalParameter(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);
    LastFormalParameterContext *lastFormalParameter();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  FormalParameterListContext* formalParameterList();

  class  FormalParameterContext : public antlr4::ParserRuleContext {
  public:
    FormalParameterContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    VariableDeclaratorIdContext *variableDeclaratorId();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  FormalParameterContext* formalParameter();

  class  LastFormalParameterContext : public antlr4::ParserRuleContext {
  public:
    LastFormalParameterContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    antlr4::tree::TerminalNode *ELLIPSIS();
    VariableDeclaratorIdContext *variableDeclaratorId();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LastFormalParameterContext* lastFormalParameter();

  class  LambdaLVTIListContext : public antlr4::ParserRuleContext {
  public:
    LambdaLVTIListContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<LambdaLVTIParameterContext *> lambdaLVTIParameter();
    LambdaLVTIParameterContext* lambdaLVTIParameter(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LambdaLVTIListContext* lambdaLVTIList();

  class  LambdaLVTIParameterContext : public antlr4::ParserRuleContext {
  public:
    LambdaLVTIParameterContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *VAR();
    IdentifierContext *identifier();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LambdaLVTIParameterContext* lambdaLVTIParameter();

  class  QualifiedNameContext : public antlr4::ParserRuleContext {
  public:
    QualifiedNameContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<IdentifierContext *> identifier();
    IdentifierContext* identifier(size_t i);
    std::vector<antlr4::tree::TerminalNode *> DOT();
    antlr4::tree::TerminalNode* DOT(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  QualifiedNameContext* qualifiedName();

  class  LiteralContext : public antlr4::ParserRuleContext {
  public:
    LiteralContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IntegerLiteralContext *integerLiteral();
    FloatLiteralContext *floatLiteral();
    antlr4::tree::TerminalNode *CHAR_LITERAL();
    antlr4::tree::TerminalNode *STRING_LITERAL();
    antlr4::tree::TerminalNode *BOOL_LITERAL();
    antlr4::tree::TerminalNode *NULL_LITERAL();
    antlr4::tree::TerminalNode *TEXT_BLOCK();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LiteralContext* literal();

  class  IntegerLiteralContext : public antlr4::ParserRuleContext {
  public:
    IntegerLiteralContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *DECIMAL_LITERAL();
    antlr4::tree::TerminalNode *HEX_LITERAL();
    antlr4::tree::TerminalNode *OCT_LITERAL();
    antlr4::tree::TerminalNode *BINARY_LITERAL();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  IntegerLiteralContext* integerLiteral();

  class  FloatLiteralContext : public antlr4::ParserRuleContext {
  public:
    FloatLiteralContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FLOAT_LITERAL();
    antlr4::tree::TerminalNode *HEX_FLOAT_LITERAL();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  FloatLiteralContext* floatLiteral();

  class  AltAnnotationQualifiedNameContext : public antlr4::ParserRuleContext {
  public:
    AltAnnotationQualifiedNameContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *AT();
    std::vector<IdentifierContext *> identifier();
    IdentifierContext* identifier(size_t i);
    std::vector<antlr4::tree::TerminalNode *> DOT();
    antlr4::tree::TerminalNode* DOT(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AltAnnotationQualifiedNameContext* altAnnotationQualifiedName();

  class  AnnotationContext : public antlr4::ParserRuleContext {
  public:
    AnnotationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *AT();
    QualifiedNameContext *qualifiedName();
    AltAnnotationQualifiedNameContext *altAnnotationQualifiedName();
    antlr4::tree::TerminalNode *LPAREN();
    antlr4::tree::TerminalNode *RPAREN();
    ElementValuePairsContext *elementValuePairs();
    ElementValueContext *elementValue();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationContext* annotation();

  class  ElementValuePairsContext : public antlr4::ParserRuleContext {
  public:
    ElementValuePairsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<ElementValuePairContext *> elementValuePair();
    ElementValuePairContext* elementValuePair(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ElementValuePairsContext* elementValuePairs();

  class  ElementValuePairContext : public antlr4::ParserRuleContext {
  public:
    ElementValuePairContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *ASSIGN();
    ElementValueContext *elementValue();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ElementValuePairContext* elementValuePair();

  class  ElementValueContext : public antlr4::ParserRuleContext {
  public:
    ElementValueContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ExpressionContext *expression();
    AnnotationContext *annotation();
    ElementValueArrayInitializerContext *elementValueArrayInitializer();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ElementValueContext* elementValue();

  class  ElementValueArrayInitializerContext : public antlr4::ParserRuleContext {
  public:
    ElementValueArrayInitializerContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<ElementValueContext *> elementValue();
    ElementValueContext* elementValue(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ElementValueArrayInitializerContext* elementValueArrayInitializer();

  class  AnnotationTypeDeclarationContext : public antlr4::ParserRuleContext {
  public:
    AnnotationTypeDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *AT();
    antlr4::tree::TerminalNode *INTERFACE();
    IdentifierContext *identifier();
    AnnotationTypeBodyContext *annotationTypeBody();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationTypeDeclarationContext* annotationTypeDeclaration();

  class  AnnotationTypeBodyContext : public antlr4::ParserRuleContext {
  public:
    AnnotationTypeBodyContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<AnnotationTypeElementDeclarationContext *> annotationTypeElementDeclaration();
    AnnotationTypeElementDeclarationContext* annotationTypeElementDeclaration(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationTypeBodyContext* annotationTypeBody();

  class  AnnotationTypeElementDeclarationContext : public antlr4::ParserRuleContext {
  public:
    AnnotationTypeElementDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    AnnotationTypeElementRestContext *annotationTypeElementRest();
    std::vector<ModifierContext *> modifier();
    ModifierContext* modifier(size_t i);
    antlr4::tree::TerminalNode *SEMI();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationTypeElementDeclarationContext* annotationTypeElementDeclaration();

  class  AnnotationTypeElementRestContext : public antlr4::ParserRuleContext {
  public:
    AnnotationTypeElementRestContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    AnnotationMethodOrConstantRestContext *annotationMethodOrConstantRest();
    antlr4::tree::TerminalNode *SEMI();
    ClassDeclarationContext *classDeclaration();
    InterfaceDeclarationContext *interfaceDeclaration();
    EnumDeclarationContext *enumDeclaration();
    AnnotationTypeDeclarationContext *annotationTypeDeclaration();
    RecordDeclarationContext *recordDeclaration();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationTypeElementRestContext* annotationTypeElementRest();

  class  AnnotationMethodOrConstantRestContext : public antlr4::ParserRuleContext {
  public:
    AnnotationMethodOrConstantRestContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    AnnotationMethodRestContext *annotationMethodRest();
    AnnotationConstantRestContext *annotationConstantRest();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationMethodOrConstantRestContext* annotationMethodOrConstantRest();

  class  AnnotationMethodRestContext : public antlr4::ParserRuleContext {
  public:
    AnnotationMethodRestContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *LPAREN();
    antlr4::tree::TerminalNode *RPAREN();
    DefaultValueContext *defaultValue();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationMethodRestContext* annotationMethodRest();

  class  AnnotationConstantRestContext : public antlr4::ParserRuleContext {
  public:
    AnnotationConstantRestContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    VariableDeclaratorsContext *variableDeclarators();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  AnnotationConstantRestContext* annotationConstantRest();

  class  DefaultValueContext : public antlr4::ParserRuleContext {
  public:
    DefaultValueContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *DEFAULT();
    ElementValueContext *elementValue();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  DefaultValueContext* defaultValue();

  class  ModuleDeclarationContext : public antlr4::ParserRuleContext {
  public:
    ModuleDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *MODULE();
    QualifiedNameContext *qualifiedName();
    ModuleBodyContext *moduleBody();
    antlr4::tree::TerminalNode *OPEN();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ModuleDeclarationContext* moduleDeclaration();

  class  ModuleBodyContext : public antlr4::ParserRuleContext {
  public:
    ModuleBodyContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<ModuleDirectiveContext *> moduleDirective();
    ModuleDirectiveContext* moduleDirective(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ModuleBodyContext* moduleBody();

  class  ModuleDirectiveContext : public antlr4::ParserRuleContext {
  public:
    ModuleDirectiveContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *REQUIRES();
    std::vector<QualifiedNameContext *> qualifiedName();
    QualifiedNameContext* qualifiedName(size_t i);
    antlr4::tree::TerminalNode *SEMI();
    std::vector<RequiresModifierContext *> requiresModifier();
    RequiresModifierContext* requiresModifier(size_t i);
    antlr4::tree::TerminalNode *EXPORTS();
    antlr4::tree::TerminalNode *TO();
    antlr4::tree::TerminalNode *OPENS();
    antlr4::tree::TerminalNode *USES();
    antlr4::tree::TerminalNode *PROVIDES();
    antlr4::tree::TerminalNode *WITH();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ModuleDirectiveContext* moduleDirective();

  class  RequiresModifierContext : public antlr4::ParserRuleContext {
  public:
    RequiresModifierContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *TRANSITIVE();
    antlr4::tree::TerminalNode *STATIC();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  RequiresModifierContext* requiresModifier();

  class  RecordDeclarationContext : public antlr4::ParserRuleContext {
  public:
    RecordDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *RECORD();
    IdentifierContext *identifier();
    RecordHeaderContext *recordHeader();
    RecordBodyContext *recordBody();
    TypeParametersContext *typeParameters();
    antlr4::tree::TerminalNode *IMPLEMENTS();
    TypeListContext *typeList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  RecordDeclarationContext* recordDeclaration();

  class  RecordHeaderContext : public antlr4::ParserRuleContext {
  public:
    RecordHeaderContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    antlr4::tree::TerminalNode *RPAREN();
    RecordComponentListContext *recordComponentList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  RecordHeaderContext* recordHeader();

  class  RecordComponentListContext : public antlr4::ParserRuleContext {
  public:
    RecordComponentListContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<RecordComponentContext *> recordComponent();
    RecordComponentContext* recordComponent(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  RecordComponentListContext* recordComponentList();

  class  RecordComponentContext : public antlr4::ParserRuleContext {
  public:
    RecordComponentContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    IdentifierContext *identifier();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  RecordComponentContext* recordComponent();

  class  RecordBodyContext : public antlr4::ParserRuleContext {
  public:
    RecordBodyContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<ClassBodyDeclarationContext *> classBodyDeclaration();
    ClassBodyDeclarationContext* classBodyDeclaration(size_t i);
    std::vector<CompactConstructorDeclarationContext *> compactConstructorDeclaration();
    CompactConstructorDeclarationContext* compactConstructorDeclaration(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  RecordBodyContext* recordBody();

  class  BlockContext : public antlr4::ParserRuleContext {
  public:
    BlockContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<BlockStatementContext *> blockStatement();
    BlockStatementContext* blockStatement(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  BlockContext* block();

  class  BlockStatementContext : public antlr4::ParserRuleContext {
  public:
    BlockStatementContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    LocalVariableDeclarationContext *localVariableDeclaration();
    antlr4::tree::TerminalNode *SEMI();
    LocalTypeDeclarationContext *localTypeDeclaration();
    StatementContext *statement();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  BlockStatementContext* blockStatement();

  class  LocalVariableDeclarationContext : public antlr4::ParserRuleContext {
  public:
    LocalVariableDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *VAR();
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *ASSIGN();
    ExpressionContext *expression();
    TypeTypeContext *typeType();
    VariableDeclaratorsContext *variableDeclarators();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LocalVariableDeclarationContext* localVariableDeclaration();

  class  IdentifierContext : public antlr4::ParserRuleContext {
  public:
    IdentifierContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *IDENTIFIER();
    antlr4::tree::TerminalNode *MODULE();
    antlr4::tree::TerminalNode *OPEN();
    antlr4::tree::TerminalNode *REQUIRES();
    antlr4::tree::TerminalNode *EXPORTS();
    antlr4::tree::TerminalNode *OPENS();
    antlr4::tree::TerminalNode *TO();
    antlr4::tree::TerminalNode *USES();
    antlr4::tree::TerminalNode *PROVIDES();
    antlr4::tree::TerminalNode *WITH();
    antlr4::tree::TerminalNode *TRANSITIVE();
    antlr4::tree::TerminalNode *YIELD();
    antlr4::tree::TerminalNode *SEALED();
    antlr4::tree::TerminalNode *PERMITS();
    antlr4::tree::TerminalNode *RECORD();
    antlr4::tree::TerminalNode *VAR();
    Simple_wildcardContext *simple_wildcard();
    Var_wildcardContext *var_wildcard();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  IdentifierContext* identifier();

  class  TypeIdentifierContext : public antlr4::ParserRuleContext {
  public:
    TypeIdentifierContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *IDENTIFIER();
    antlr4::tree::TerminalNode *MODULE();
    antlr4::tree::TerminalNode *OPEN();
    antlr4::tree::TerminalNode *REQUIRES();
    antlr4::tree::TerminalNode *EXPORTS();
    antlr4::tree::TerminalNode *OPENS();
    antlr4::tree::TerminalNode *TO();
    antlr4::tree::TerminalNode *USES();
    antlr4::tree::TerminalNode *PROVIDES();
    antlr4::tree::TerminalNode *WITH();
    antlr4::tree::TerminalNode *TRANSITIVE();
    antlr4::tree::TerminalNode *SEALED();
    antlr4::tree::TerminalNode *PERMITS();
    antlr4::tree::TerminalNode *RECORD();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeIdentifierContext* typeIdentifier();

  class  LocalTypeDeclarationContext : public antlr4::ParserRuleContext {
  public:
    LocalTypeDeclarationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ClassDeclarationContext *classDeclaration();
    InterfaceDeclarationContext *interfaceDeclaration();
    RecordDeclarationContext *recordDeclaration();
    std::vector<ClassOrInterfaceModifierContext *> classOrInterfaceModifier();
    ClassOrInterfaceModifierContext* classOrInterfaceModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LocalTypeDeclarationContext* localTypeDeclaration();

  class  StatementContext : public antlr4::ParserRuleContext {
  public:
    JavaParser::ExpressionContext *statementExpression = nullptr;
    StatementContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Compound_stmtContext *compound_stmt();
    antlr4::tree::TerminalNode *ASSERT();
    std::vector<ExpressionContext *> expression();
    ExpressionContext* expression(size_t i);
    antlr4::tree::TerminalNode *SEMI();
    antlr4::tree::TerminalNode *COLON();
    antlr4::tree::TerminalNode *RETURN();
    antlr4::tree::TerminalNode *THROW();
    antlr4::tree::TerminalNode *BREAK();
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *CONTINUE();
    antlr4::tree::TerminalNode *YIELD();
    SwitchExpressionContext *switchExpression();
    Stmt_wildcardContext *stmt_wildcard();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  StatementContext* statement();

  class  Compound_stmtContext : public antlr4::ParserRuleContext {
  public:
    JavaParser::BlockContext *blockLabel = nullptr;
    JavaParser::IdentifierContext *identifierLabel = nullptr;
    Compound_stmtContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Compound_wildcardContext *compound_wildcard();
    BlockContext *block();
    antlr4::tree::TerminalNode *IF();
    ParExpressionContext *parExpression();
    std::vector<StatementContext *> statement();
    StatementContext* statement(size_t i);
    antlr4::tree::TerminalNode *ELSE();
    antlr4::tree::TerminalNode *FOR();
    antlr4::tree::TerminalNode *LPAREN();
    ForControlContext *forControl();
    antlr4::tree::TerminalNode *RPAREN();
    antlr4::tree::TerminalNode *WHILE();
    antlr4::tree::TerminalNode *DO();
    antlr4::tree::TerminalNode *SEMI();
    antlr4::tree::TerminalNode *TRY();
    FinallyBlockContext *finallyBlock();
    std::vector<CatchClauseContext *> catchClause();
    CatchClauseContext* catchClause(size_t i);
    ResourceSpecificationContext *resourceSpecification();
    antlr4::tree::TerminalNode *SWITCH();
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<SwitchBlockStatementGroupContext *> switchBlockStatementGroup();
    SwitchBlockStatementGroupContext* switchBlockStatementGroup(size_t i);
    std::vector<SwitchLabelContext *> switchLabel();
    SwitchLabelContext* switchLabel(size_t i);
    antlr4::tree::TerminalNode *SYNCHRONIZED();
    antlr4::tree::TerminalNode *COLON();
    IdentifierContext *identifier();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Compound_stmtContext* compound_stmt();

  class  CatchClauseContext : public antlr4::ParserRuleContext {
  public:
    CatchClauseContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *CATCH();
    antlr4::tree::TerminalNode *LPAREN();
    CatchTypeContext *catchType();
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *RPAREN();
    BlockContext *block();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  CatchClauseContext* catchClause();

  class  CatchTypeContext : public antlr4::ParserRuleContext {
  public:
    CatchTypeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<QualifiedNameContext *> qualifiedName();
    QualifiedNameContext* qualifiedName(size_t i);
    std::vector<antlr4::tree::TerminalNode *> BITOR();
    antlr4::tree::TerminalNode* BITOR(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  CatchTypeContext* catchType();

  class  FinallyBlockContext : public antlr4::ParserRuleContext {
  public:
    FinallyBlockContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FINALLY();
    BlockContext *block();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  FinallyBlockContext* finallyBlock();

  class  ResourceSpecificationContext : public antlr4::ParserRuleContext {
  public:
    ResourceSpecificationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    ResourcesContext *resources();
    antlr4::tree::TerminalNode *RPAREN();
    antlr4::tree::TerminalNode *SEMI();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ResourceSpecificationContext* resourceSpecification();

  class  ResourcesContext : public antlr4::ParserRuleContext {
  public:
    ResourcesContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<ResourceContext *> resource();
    ResourceContext* resource(size_t i);
    std::vector<antlr4::tree::TerminalNode *> SEMI();
    antlr4::tree::TerminalNode* SEMI(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ResourcesContext* resources();

  class  ResourceContext : public antlr4::ParserRuleContext {
  public:
    ResourceContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *ASSIGN();
    ExpressionContext *expression();
    ClassOrInterfaceTypeContext *classOrInterfaceType();
    VariableDeclaratorIdContext *variableDeclaratorId();
    antlr4::tree::TerminalNode *VAR();
    IdentifierContext *identifier();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);
    QualifiedNameContext *qualifiedName();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ResourceContext* resource();

  class  SwitchBlockStatementGroupContext : public antlr4::ParserRuleContext {
  public:
    SwitchBlockStatementGroupContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<SwitchLabelContext *> switchLabel();
    SwitchLabelContext* switchLabel(size_t i);
    std::vector<BlockStatementContext *> blockStatement();
    BlockStatementContext* blockStatement(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  SwitchBlockStatementGroupContext* switchBlockStatementGroup();

  class  SwitchLabelContext : public antlr4::ParserRuleContext {
  public:
    JavaParser::ExpressionContext *constantExpression = nullptr;
    antlr4::Token *enumConstantName = nullptr;
    JavaParser::IdentifierContext *varName = nullptr;
    SwitchLabelContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *CASE();
    antlr4::tree::TerminalNode *COLON();
    TypeTypeContext *typeType();
    ExpressionContext *expression();
    antlr4::tree::TerminalNode *IDENTIFIER();
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *DEFAULT();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  SwitchLabelContext* switchLabel();

  class  ForControlContext : public antlr4::ParserRuleContext {
  public:
    JavaParser::ExpressionListContext *forUpdate = nullptr;
    ForControlContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    EnhancedForControlContext *enhancedForControl();
    std::vector<antlr4::tree::TerminalNode *> SEMI();
    antlr4::tree::TerminalNode* SEMI(size_t i);
    ForInitContext *forInit();
    ExpressionContext *expression();
    ExpressionListContext *expressionList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ForControlContext* forControl();

  class  ForInitContext : public antlr4::ParserRuleContext {
  public:
    ForInitContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    LocalVariableDeclarationContext *localVariableDeclaration();
    ExpressionListContext *expressionList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ForInitContext* forInit();

  class  EnhancedForControlContext : public antlr4::ParserRuleContext {
  public:
    EnhancedForControlContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    VariableDeclaratorIdContext *variableDeclaratorId();
    antlr4::tree::TerminalNode *COLON();
    ExpressionContext *expression();
    TypeTypeContext *typeType();
    antlr4::tree::TerminalNode *VAR();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  EnhancedForControlContext* enhancedForControl();

  class  ParExpressionContext : public antlr4::ParserRuleContext {
  public:
    ParExpressionContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    ExpressionContext *expression();
    antlr4::tree::TerminalNode *RPAREN();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ParExpressionContext* parExpression();

  class  ExpressionListContext : public antlr4::ParserRuleContext {
  public:
    ExpressionListContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<List_wildcardContext *> list_wildcard();
    List_wildcardContext* list_wildcard(size_t i);
    std::vector<Number_wildcardContext *> number_wildcard();
    Number_wildcardContext* number_wildcard(size_t i);
    std::vector<ExpressionContext *> expression();
    ExpressionContext* expression(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ExpressionListContext* expressionList();

  class  MethodCallContext : public antlr4::ParserRuleContext {
  public:
    MethodCallContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ArgumentsContext *arguments();
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *THIS();
    antlr4::tree::TerminalNode *SUPER();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  MethodCallContext* methodCall();

  class  ExpressionContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *prefix = nullptr;
    antlr4::Token *bop = nullptr;
    antlr4::Token *postfix = nullptr;
    ExpressionContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Expr_wildcardContext *expr_wildcard();
    Number_wildcardContext *number_wildcard();
    PrimaryContext *primary();
    MethodCallContext *methodCall();
    std::vector<TypeTypeContext *> typeType();
    TypeTypeContext* typeType(size_t i);
    antlr4::tree::TerminalNode *COLONCOLON();
    IdentifierContext *identifier();
    antlr4::tree::TerminalNode *NEW();
    TypeArgumentsContext *typeArguments();
    ClassTypeContext *classType();
    SwitchExpressionContext *switchExpression();
    std::vector<ExpressionContext *> expression();
    ExpressionContext* expression(size_t i);
    antlr4::tree::TerminalNode *ADD();
    antlr4::tree::TerminalNode *SUB();
    antlr4::tree::TerminalNode *INC();
    antlr4::tree::TerminalNode *DEC();
    antlr4::tree::TerminalNode *TILDE();
    antlr4::tree::TerminalNode *BANG();
    antlr4::tree::TerminalNode *LPAREN();
    antlr4::tree::TerminalNode *RPAREN();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    std::vector<antlr4::tree::TerminalNode *> BITAND();
    antlr4::tree::TerminalNode* BITAND(size_t i);
    CreatorContext *creator();
    LambdaExpressionContext *lambdaExpression();
    antlr4::tree::TerminalNode *MUL();
    antlr4::tree::TerminalNode *DIV();
    antlr4::tree::TerminalNode *MOD();
    std::vector<antlr4::tree::TerminalNode *> LT();
    antlr4::tree::TerminalNode* LT(size_t i);
    std::vector<antlr4::tree::TerminalNode *> GT();
    antlr4::tree::TerminalNode* GT(size_t i);
    antlr4::tree::TerminalNode *LE();
    antlr4::tree::TerminalNode *GE();
    antlr4::tree::TerminalNode *EQUAL();
    antlr4::tree::TerminalNode *NOTEQUAL();
    antlr4::tree::TerminalNode *CARET();
    antlr4::tree::TerminalNode *BITOR();
    antlr4::tree::TerminalNode *AND();
    antlr4::tree::TerminalNode *OR();
    antlr4::tree::TerminalNode *COLON();
    antlr4::tree::TerminalNode *QUESTION();
    antlr4::tree::TerminalNode *ASSIGN();
    antlr4::tree::TerminalNode *ADD_ASSIGN();
    antlr4::tree::TerminalNode *SUB_ASSIGN();
    antlr4::tree::TerminalNode *MUL_ASSIGN();
    antlr4::tree::TerminalNode *DIV_ASSIGN();
    antlr4::tree::TerminalNode *AND_ASSIGN();
    antlr4::tree::TerminalNode *OR_ASSIGN();
    antlr4::tree::TerminalNode *XOR_ASSIGN();
    antlr4::tree::TerminalNode *RSHIFT_ASSIGN();
    antlr4::tree::TerminalNode *URSHIFT_ASSIGN();
    antlr4::tree::TerminalNode *LSHIFT_ASSIGN();
    antlr4::tree::TerminalNode *MOD_ASSIGN();
    antlr4::tree::TerminalNode *LBRACK();
    antlr4::tree::TerminalNode *RBRACK();
    antlr4::tree::TerminalNode *DOT();
    antlr4::tree::TerminalNode *THIS();
    InnerCreatorContext *innerCreator();
    antlr4::tree::TerminalNode *SUPER();
    SuperSuffixContext *superSuffix();
    ExplicitGenericInvocationContext *explicitGenericInvocation();
    NonWildcardTypeArgumentsContext *nonWildcardTypeArguments();
    antlr4::tree::TerminalNode *INSTANCEOF();
    PatternContext *pattern();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ExpressionContext* expression();
  ExpressionContext* expression(int precedence);
  class  PatternContext : public antlr4::ParserRuleContext {
  public:
    PatternContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    TypeTypeContext *typeType();
    IdentifierContext *identifier();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  PatternContext* pattern();

  class  LambdaExpressionContext : public antlr4::ParserRuleContext {
  public:
    LambdaExpressionContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    LambdaParametersContext *lambdaParameters();
    antlr4::tree::TerminalNode *ARROW();
    LambdaBodyContext *lambdaBody();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LambdaExpressionContext* lambdaExpression();

  class  LambdaParametersContext : public antlr4::ParserRuleContext {
  public:
    LambdaParametersContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<IdentifierContext *> identifier();
    IdentifierContext* identifier(size_t i);
    antlr4::tree::TerminalNode *LPAREN();
    antlr4::tree::TerminalNode *RPAREN();
    FormalParameterListContext *formalParameterList();
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);
    LambdaLVTIListContext *lambdaLVTIList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LambdaParametersContext* lambdaParameters();

  class  LambdaBodyContext : public antlr4::ParserRuleContext {
  public:
    LambdaBodyContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ExpressionContext *expression();
    BlockContext *block();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  LambdaBodyContext* lambdaBody();

  class  PrimaryContext : public antlr4::ParserRuleContext {
  public:
    PrimaryContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    ExpressionContext *expression();
    antlr4::tree::TerminalNode *RPAREN();
    antlr4::tree::TerminalNode *THIS();
    antlr4::tree::TerminalNode *SUPER();
    LiteralContext *literal();
    IdentifierContext *identifier();
    TypeTypeOrVoidContext *typeTypeOrVoid();
    antlr4::tree::TerminalNode *DOT();
    antlr4::tree::TerminalNode *CLASS();
    NonWildcardTypeArgumentsContext *nonWildcardTypeArguments();
    ExplicitGenericInvocationSuffixContext *explicitGenericInvocationSuffix();
    ArgumentsContext *arguments();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  PrimaryContext* primary();

  class  SwitchExpressionContext : public antlr4::ParserRuleContext {
  public:
    SwitchExpressionContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *SWITCH();
    ParExpressionContext *parExpression();
    antlr4::tree::TerminalNode *LBRACE();
    antlr4::tree::TerminalNode *RBRACE();
    std::vector<SwitchLabeledRuleContext *> switchLabeledRule();
    SwitchLabeledRuleContext* switchLabeledRule(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  SwitchExpressionContext* switchExpression();

  class  SwitchLabeledRuleContext : public antlr4::ParserRuleContext {
  public:
    SwitchLabeledRuleContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *CASE();
    SwitchRuleOutcomeContext *switchRuleOutcome();
    antlr4::tree::TerminalNode *ARROW();
    antlr4::tree::TerminalNode *COLON();
    ExpressionListContext *expressionList();
    antlr4::tree::TerminalNode *NULL_LITERAL();
    GuardedPatternContext *guardedPattern();
    antlr4::tree::TerminalNode *DEFAULT();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  SwitchLabeledRuleContext* switchLabeledRule();

  class  GuardedPatternContext : public antlr4::ParserRuleContext {
  public:
    GuardedPatternContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    GuardedPatternContext *guardedPattern();
    antlr4::tree::TerminalNode *RPAREN();
    TypeTypeContext *typeType();
    IdentifierContext *identifier();
    std::vector<VariableModifierContext *> variableModifier();
    VariableModifierContext* variableModifier(size_t i);
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    std::vector<antlr4::tree::TerminalNode *> AND();
    antlr4::tree::TerminalNode* AND(size_t i);
    std::vector<ExpressionContext *> expression();
    ExpressionContext* expression(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  GuardedPatternContext* guardedPattern();
  GuardedPatternContext* guardedPattern(int precedence);
  class  SwitchRuleOutcomeContext : public antlr4::ParserRuleContext {
  public:
    SwitchRuleOutcomeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    BlockContext *block();
    std::vector<BlockStatementContext *> blockStatement();
    BlockStatementContext* blockStatement(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  SwitchRuleOutcomeContext* switchRuleOutcome();

  class  ClassTypeContext : public antlr4::ParserRuleContext {
  public:
    ClassTypeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    ClassOrInterfaceTypeContext *classOrInterfaceType();
    antlr4::tree::TerminalNode *DOT();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    TypeArgumentsContext *typeArguments();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ClassTypeContext* classType();

  class  CreatorContext : public antlr4::ParserRuleContext {
  public:
    CreatorContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    CreatedNameContext *createdName();
    ClassCreatorRestContext *classCreatorRest();
    NonWildcardTypeArgumentsContext *nonWildcardTypeArguments();
    ArrayCreatorRestContext *arrayCreatorRest();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  CreatorContext* creator();

  class  CreatedNameContext : public antlr4::ParserRuleContext {
  public:
    CreatedNameContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<IdentifierContext *> identifier();
    IdentifierContext* identifier(size_t i);
    std::vector<TypeArgumentsOrDiamondContext *> typeArgumentsOrDiamond();
    TypeArgumentsOrDiamondContext* typeArgumentsOrDiamond(size_t i);
    std::vector<antlr4::tree::TerminalNode *> DOT();
    antlr4::tree::TerminalNode* DOT(size_t i);
    PrimitiveTypeContext *primitiveType();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  CreatedNameContext* createdName();

  class  InnerCreatorContext : public antlr4::ParserRuleContext {
  public:
    InnerCreatorContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    IdentifierContext *identifier();
    ClassCreatorRestContext *classCreatorRest();
    NonWildcardTypeArgumentsOrDiamondContext *nonWildcardTypeArgumentsOrDiamond();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  InnerCreatorContext* innerCreator();

  class  ArrayCreatorRestContext : public antlr4::ParserRuleContext {
  public:
    ArrayCreatorRestContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ArrayInitializerContext *arrayInitializer();
    std::vector<antlr4::tree::TerminalNode *> LBRACK();
    antlr4::tree::TerminalNode* LBRACK(size_t i);
    std::vector<antlr4::tree::TerminalNode *> RBRACK();
    antlr4::tree::TerminalNode* RBRACK(size_t i);
    std::vector<ExpressionContext *> expression();
    ExpressionContext* expression(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ArrayCreatorRestContext* arrayCreatorRest();

  class  ClassCreatorRestContext : public antlr4::ParserRuleContext {
  public:
    ClassCreatorRestContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ArgumentsContext *arguments();
    ClassBodyContext *classBody();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ClassCreatorRestContext* classCreatorRest();

  class  ExplicitGenericInvocationContext : public antlr4::ParserRuleContext {
  public:
    ExplicitGenericInvocationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    NonWildcardTypeArgumentsContext *nonWildcardTypeArguments();
    ExplicitGenericInvocationSuffixContext *explicitGenericInvocationSuffix();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ExplicitGenericInvocationContext* explicitGenericInvocation();

  class  TypeArgumentsOrDiamondContext : public antlr4::ParserRuleContext {
  public:
    TypeArgumentsOrDiamondContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LT();
    antlr4::tree::TerminalNode *GT();
    TypeArgumentsContext *typeArguments();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeArgumentsOrDiamondContext* typeArgumentsOrDiamond();

  class  NonWildcardTypeArgumentsOrDiamondContext : public antlr4::ParserRuleContext {
  public:
    NonWildcardTypeArgumentsOrDiamondContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LT();
    antlr4::tree::TerminalNode *GT();
    NonWildcardTypeArgumentsContext *nonWildcardTypeArguments();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  NonWildcardTypeArgumentsOrDiamondContext* nonWildcardTypeArgumentsOrDiamond();

  class  NonWildcardTypeArgumentsContext : public antlr4::ParserRuleContext {
  public:
    NonWildcardTypeArgumentsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LT();
    TypeListContext *typeList();
    antlr4::tree::TerminalNode *GT();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  NonWildcardTypeArgumentsContext* nonWildcardTypeArguments();

  class  TypeListContext : public antlr4::ParserRuleContext {
  public:
    TypeListContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<TypeTypeContext *> typeType();
    TypeTypeContext* typeType(size_t i);
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeListContext* typeList();

  class  TypeTypeContext : public antlr4::ParserRuleContext {
  public:
    TypeTypeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Simple_wildcardContext *simple_wildcard();
    Var_wildcardContext *var_wildcard();
    ClassOrInterfaceTypeContext *classOrInterfaceType();
    PrimitiveTypeContext *primitiveType();
    std::vector<AnnotationContext *> annotation();
    AnnotationContext* annotation(size_t i);
    std::vector<antlr4::tree::TerminalNode *> LBRACK();
    antlr4::tree::TerminalNode* LBRACK(size_t i);
    std::vector<antlr4::tree::TerminalNode *> RBRACK();
    antlr4::tree::TerminalNode* RBRACK(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeTypeContext* typeType();

  class  PrimitiveTypeContext : public antlr4::ParserRuleContext {
  public:
    PrimitiveTypeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *BOOLEAN();
    antlr4::tree::TerminalNode *CHAR();
    antlr4::tree::TerminalNode *BYTE();
    antlr4::tree::TerminalNode *SHORT();
    antlr4::tree::TerminalNode *INT();
    antlr4::tree::TerminalNode *LONG();
    antlr4::tree::TerminalNode *FLOAT();
    antlr4::tree::TerminalNode *DOUBLE();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  PrimitiveTypeContext* primitiveType();

  class  TypeArgumentsContext : public antlr4::ParserRuleContext {
  public:
    TypeArgumentsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LT();
    std::vector<TypeArgumentContext *> typeArgument();
    TypeArgumentContext* typeArgument(size_t i);
    antlr4::tree::TerminalNode *GT();
    std::vector<antlr4::tree::TerminalNode *> COMMA();
    antlr4::tree::TerminalNode* COMMA(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  TypeArgumentsContext* typeArguments();

  class  SuperSuffixContext : public antlr4::ParserRuleContext {
  public:
    SuperSuffixContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    ArgumentsContext *arguments();
    antlr4::tree::TerminalNode *DOT();
    IdentifierContext *identifier();
    TypeArgumentsContext *typeArguments();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  SuperSuffixContext* superSuffix();

  class  ExplicitGenericInvocationSuffixContext : public antlr4::ParserRuleContext {
  public:
    ExplicitGenericInvocationSuffixContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *SUPER();
    SuperSuffixContext *superSuffix();
    IdentifierContext *identifier();
    ArgumentsContext *arguments();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ExplicitGenericInvocationSuffixContext* explicitGenericInvocationSuffix();

  class  ArgumentsContext : public antlr4::ParserRuleContext {
  public:
    ArgumentsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    antlr4::tree::TerminalNode *RPAREN();
    List_wildcardContext *list_wildcard();
    Number_wildcardContext *number_wildcard();
    ExpressionListContext *expressionList();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  ArgumentsContext* arguments();

  class  Simple_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Simple_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *WILDCARD();
    antlr4::tree::TerminalNode *WILDCARD_SPACE();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Simple_wildcardContext* simple_wildcard();

  class  Var_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Var_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *VAR_WILDCARD();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Var_wildcardContext* var_wildcard();

  class  List_wildcardContext : public antlr4::ParserRuleContext {
  public:
    List_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *WILDCARD();
    antlr4::tree::TerminalNode *MUL();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  List_wildcardContext* list_wildcard();

  class  Contains_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Contains_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *WILDCARD();
    antlr4::tree::TerminalNode *LT();
    antlr4::tree::TerminalNode *GT();
    Simple_wildcardContext *simple_wildcard();
    Var_wildcardContext *var_wildcard();
    Contains_wildcardContext *contains_wildcard();
    ExpressionContext *expression();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Contains_wildcardContext* contains_wildcard();

  class  Simple_compound_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Simple_compound_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *WILDCARD();
    BlockContext *block();
    Wildcard_numberContext *wildcard_number();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Simple_compound_wildcardContext* simple_compound_wildcard();

  class  Multiple_compound_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Multiple_compound_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *WILDCARD();
    antlr4::tree::TerminalNode *MUL();
    BlockContext *block();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Multiple_compound_wildcardContext* multiple_compound_wildcard();

  class  Number_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Number_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *WILDCARD();
    Wildcard_numberContext *wildcard_number();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Number_wildcardContext* number_wildcard();

  class  Wildcard_numberContext : public antlr4::ParserRuleContext {
  public:
    Wildcard_numberContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *LPAREN();
    std::vector<antlr4::tree::TerminalNode *> DECIMAL_LITERAL();
    antlr4::tree::TerminalNode* DECIMAL_LITERAL(size_t i);
    antlr4::tree::TerminalNode *RPAREN();
    antlr4::tree::TerminalNode *COMMA();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Wildcard_numberContext* wildcard_number();

  class  Stmt_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Stmt_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Simple_wildcardContext *simple_wildcard();
    Var_wildcardContext *var_wildcard();
    List_wildcardContext *list_wildcard();
    Contains_wildcardContext *contains_wildcard();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Stmt_wildcardContext* stmt_wildcard();

  class  Expr_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Expr_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Var_wildcardContext *var_wildcard();
    Contains_wildcardContext *contains_wildcard();
    Simple_wildcardContext *simple_wildcard();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Expr_wildcardContext* expr_wildcard();

  class  Compound_wildcardContext : public antlr4::ParserRuleContext {
  public:
    Compound_wildcardContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Simple_compound_wildcardContext *simple_compound_wildcard();
    Multiple_compound_wildcardContext *multiple_compound_wildcard();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;

    virtual std::any accept(antlr4::tree::ParseTreeVisitor *visitor) override;
   
  };

  Compound_wildcardContext* compound_wildcard();


  bool sempred(antlr4::RuleContext *_localctx, size_t ruleIndex, size_t predicateIndex) override;

  bool expressionSempred(ExpressionContext *_localctx, size_t predicateIndex);
  bool guardedPatternSempred(GuardedPatternContext *_localctx, size_t predicateIndex);

  // By default the static state used to implement the parser is lazily initialized during the first
  // call to the constructor. You can call this function if you wish to initialize the static state
  // ahead of time.
  static void initialize();

private:
};

