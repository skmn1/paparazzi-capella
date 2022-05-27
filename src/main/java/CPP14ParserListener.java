// Generated from CPP14Parser.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPP14Parser}.
 */
public interface CPP14ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#handler}.
	 * @param ctx the parse tree
	 */
	void enterHandler(@NotNull CPP14Parser.HandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#handler}.
	 * @param ctx the parse tree
	 */
	void exitHandler(@NotNull CPP14Parser.HandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#lambdaIntroducer}.
	 * @param ctx the parse tree
	 */
	void enterLambdaIntroducer(@NotNull CPP14Parser.LambdaIntroducerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#lambdaIntroducer}.
	 * @param ctx the parse tree
	 */
	void exitLambdaIntroducer(@NotNull CPP14Parser.LambdaIntroducerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(@NotNull CPP14Parser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(@NotNull CPP14Parser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void enterForInitStatement(@NotNull CPP14Parser.ForInitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void exitForInitStatement(@NotNull CPP14Parser.ForInitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#asmDefinition}.
	 * @param ctx the parse tree
	 */
	void enterAsmDefinition(@NotNull CPP14Parser.AsmDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#asmDefinition}.
	 * @param ctx the parse tree
	 */
	void exitAsmDefinition(@NotNull CPP14Parser.AsmDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#templateArgumentList}.
	 * @param ctx the parse tree
	 */
	void enterTemplateArgumentList(@NotNull CPP14Parser.TemplateArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#templateArgumentList}.
	 * @param ctx the parse tree
	 */
	void exitTemplateArgumentList(@NotNull CPP14Parser.TemplateArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#namespaceAliasDefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceAliasDefinition(@NotNull CPP14Parser.NamespaceAliasDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#namespaceAliasDefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceAliasDefinition(@NotNull CPP14Parser.NamespaceAliasDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#baseSpecifierList}.
	 * @param ctx the parse tree
	 */
	void enterBaseSpecifierList(@NotNull CPP14Parser.BaseSpecifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#baseSpecifierList}.
	 * @param ctx the parse tree
	 */
	void exitBaseSpecifierList(@NotNull CPP14Parser.BaseSpecifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#attributeArgumentClause}.
	 * @param ctx the parse tree
	 */
	void enterAttributeArgumentClause(@NotNull CPP14Parser.AttributeArgumentClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#attributeArgumentClause}.
	 * @param ctx the parse tree
	 */
	void exitAttributeArgumentClause(@NotNull CPP14Parser.AttributeArgumentClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#pointerAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterPointerAbstractDeclarator(@NotNull CPP14Parser.PointerAbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#pointerAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitPointerAbstractDeclarator(@NotNull CPP14Parser.PointerAbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumHead}.
	 * @param ctx the parse tree
	 */
	void enterEnumHead(@NotNull CPP14Parser.EnumHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumHead}.
	 * @param ctx the parse tree
	 */
	void exitEnumHead(@NotNull CPP14Parser.EnumHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#pointerMemberExpression}.
	 * @param ctx the parse tree
	 */
	void enterPointerMemberExpression(@NotNull CPP14Parser.PointerMemberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#pointerMemberExpression}.
	 * @param ctx the parse tree
	 */
	void exitPointerMemberExpression(@NotNull CPP14Parser.PointerMemberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#staticAssertDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStaticAssertDeclaration(@NotNull CPP14Parser.StaticAssertDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#staticAssertDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStaticAssertDeclaration(@NotNull CPP14Parser.StaticAssertDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#functionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterFunctionSpecifier(@NotNull CPP14Parser.FunctionSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#functionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitFunctionSpecifier(@NotNull CPP14Parser.FunctionSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#simpleTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTypeSpecifier(@NotNull CPP14Parser.SimpleTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#simpleTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTypeSpecifier(@NotNull CPP14Parser.SimpleTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#attributeNamespace}.
	 * @param ctx the parse tree
	 */
	void enterAttributeNamespace(@NotNull CPP14Parser.AttributeNamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#attributeNamespace}.
	 * @param ctx the parse tree
	 */
	void exitAttributeNamespace(@NotNull CPP14Parser.AttributeNamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclarator(@NotNull CPP14Parser.InitDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclarator(@NotNull CPP14Parser.InitDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#meminitializerid}.
	 * @param ctx the parse tree
	 */
	void enterMeminitializerid(@NotNull CPP14Parser.MeminitializeridContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#meminitializerid}.
	 * @param ctx the parse tree
	 */
	void exitMeminitializerid(@NotNull CPP14Parser.MeminitializeridContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#idExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpression(@NotNull CPP14Parser.IdExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#idExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpression(@NotNull CPP14Parser.IdExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclSpecifier(@NotNull CPP14Parser.DeclSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclSpecifier(@NotNull CPP14Parser.DeclSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(@NotNull CPP14Parser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(@NotNull CPP14Parser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#simpleTypeLengthModifier}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTypeLengthModifier(@NotNull CPP14Parser.SimpleTypeLengthModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#simpleTypeLengthModifier}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTypeLengthModifier(@NotNull CPP14Parser.SimpleTypeLengthModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(@NotNull CPP14Parser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(@NotNull CPP14Parser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#decltypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDecltypeSpecifier(@NotNull CPP14Parser.DecltypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#decltypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDecltypeSpecifier(@NotNull CPP14Parser.DecltypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(@NotNull CPP14Parser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(@NotNull CPP14Parser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#initializerClause}.
	 * @param ctx the parse tree
	 */
	void enterInitializerClause(@NotNull CPP14Parser.InitializerClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#initializerClause}.
	 * @param ctx the parse tree
	 */
	void exitInitializerClause(@NotNull CPP14Parser.InitializerClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#throwExpression}.
	 * @param ctx the parse tree
	 */
	void enterThrowExpression(@NotNull CPP14Parser.ThrowExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#throwExpression}.
	 * @param ctx the parse tree
	 */
	void exitThrowExpression(@NotNull CPP14Parser.ThrowExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#typeIdList}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdList(@NotNull CPP14Parser.TypeIdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#typeIdList}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdList(@NotNull CPP14Parser.TypeIdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#simpleDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDeclaration(@NotNull CPP14Parser.SimpleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#simpleDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDeclaration(@NotNull CPP14Parser.SimpleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#classHead}.
	 * @param ctx the parse tree
	 */
	void enterClassHead(@NotNull CPP14Parser.ClassHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#classHead}.
	 * @param ctx the parse tree
	 */
	void exitClassHead(@NotNull CPP14Parser.ClassHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(@NotNull CPP14Parser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(@NotNull CPP14Parser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(@NotNull CPP14Parser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(@NotNull CPP14Parser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#gimplePreamble}.
	 * @param ctx the parse tree
	 */
	void enterGimplePreamble(@NotNull CPP14Parser.GimplePreambleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#gimplePreamble}.
	 * @param ctx the parse tree
	 */
	void exitGimplePreamble(@NotNull CPP14Parser.GimplePreambleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#newPlacement}.
	 * @param ctx the parse tree
	 */
	void enterNewPlacement(@NotNull CPP14Parser.NewPlacementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#newPlacement}.
	 * @param ctx the parse tree
	 */
	void exitNewPlacement(@NotNull CPP14Parser.NewPlacementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#newExpression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(@NotNull CPP14Parser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#newExpression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(@NotNull CPP14Parser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#functionTryBlock}.
	 * @param ctx the parse tree
	 */
	void enterFunctionTryBlock(@NotNull CPP14Parser.FunctionTryBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#functionTryBlock}.
	 * @param ctx the parse tree
	 */
	void exitFunctionTryBlock(@NotNull CPP14Parser.FunctionTryBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#exceptionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterExceptionSpecification(@NotNull CPP14Parser.ExceptionSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#exceptionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitExceptionSpecification(@NotNull CPP14Parser.ExceptionSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#explicitInstantiation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitInstantiation(@NotNull CPP14Parser.ExplicitInstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#explicitInstantiation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitInstantiation(@NotNull CPP14Parser.ExplicitInstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#simpleCapture}.
	 * @param ctx the parse tree
	 */
	void enterSimpleCapture(@NotNull CPP14Parser.SimpleCaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#simpleCapture}.
	 * @param ctx the parse tree
	 */
	void exitSimpleCapture(@NotNull CPP14Parser.SimpleCaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#usingDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterUsingDeclaration(@NotNull CPP14Parser.UsingDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#usingDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitUsingDeclaration(@NotNull CPP14Parser.UsingDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#cvQualifier}.
	 * @param ctx the parse tree
	 */
	void enterCvQualifier(@NotNull CPP14Parser.CvQualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#cvQualifier}.
	 * @param ctx the parse tree
	 */
	void exitCvQualifier(@NotNull CPP14Parser.CvQualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#handlerSeq}.
	 * @param ctx the parse tree
	 */
	void enterHandlerSeq(@NotNull CPP14Parser.HandlerSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#handlerSeq}.
	 * @param ctx the parse tree
	 */
	void exitHandlerSeq(@NotNull CPP14Parser.HandlerSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationUnit(@NotNull CPP14Parser.TranslationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationUnit(@NotNull CPP14Parser.TranslationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(@NotNull CPP14Parser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(@NotNull CPP14Parser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#templateArgument}.
	 * @param ctx the parse tree
	 */
	void enterTemplateArgument(@NotNull CPP14Parser.TemplateArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#templateArgument}.
	 * @param ctx the parse tree
	 */
	void exitTemplateArgument(@NotNull CPP14Parser.TemplateArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#qualifiedId}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedId(@NotNull CPP14Parser.QualifiedIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#qualifiedId}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedId(@NotNull CPP14Parser.QualifiedIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#noeExceptSpecification}.
	 * @param ctx the parse tree
	 */
	void enterNoeExceptSpecification(@NotNull CPP14Parser.NoeExceptSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#noeExceptSpecification}.
	 * @param ctx the parse tree
	 */
	void exitNoeExceptSpecification(@NotNull CPP14Parser.NoeExceptSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#emptyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEmptyDeclaration(@NotNull CPP14Parser.EmptyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#emptyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEmptyDeclaration(@NotNull CPP14Parser.EmptyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#lambdaCapture}.
	 * @param ctx the parse tree
	 */
	void enterLambdaCapture(@NotNull CPP14Parser.LambdaCaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#lambdaCapture}.
	 * @param ctx the parse tree
	 */
	void exitLambdaCapture(@NotNull CPP14Parser.LambdaCaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#typeNameSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeNameSpecifier(@NotNull CPP14Parser.TypeNameSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#typeNameSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeNameSpecifier(@NotNull CPP14Parser.TypeNameSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(@NotNull CPP14Parser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(@NotNull CPP14Parser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#explicitSpecialization}.
	 * @param ctx the parse tree
	 */
	void enterExplicitSpecialization(@NotNull CPP14Parser.ExplicitSpecializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#explicitSpecialization}.
	 * @param ctx the parse tree
	 */
	void exitExplicitSpecialization(@NotNull CPP14Parser.ExplicitSpecializationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(@NotNull CPP14Parser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(@NotNull CPP14Parser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclaratorList(@NotNull CPP14Parser.InitDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclaratorList(@NotNull CPP14Parser.InitDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#declSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void enterDeclSpecifierSeq(@NotNull CPP14Parser.DeclSpecifierSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#declSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void exitDeclSpecifierSeq(@NotNull CPP14Parser.DeclSpecifierSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#refqualifier}.
	 * @param ctx the parse tree
	 */
	void enterRefqualifier(@NotNull CPP14Parser.RefqualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#refqualifier}.
	 * @param ctx the parse tree
	 */
	void exitRefqualifier(@NotNull CPP14Parser.RefqualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#theTypeId}.
	 * @param ctx the parse tree
	 */
	void enterTheTypeId(@NotNull CPP14Parser.TheTypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#theTypeId}.
	 * @param ctx the parse tree
	 */
	void exitTheTypeId(@NotNull CPP14Parser.TheTypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(@NotNull CPP14Parser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(@NotNull CPP14Parser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(@NotNull CPP14Parser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(@NotNull CPP14Parser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(@NotNull CPP14Parser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(@NotNull CPP14Parser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#constructorInitializer}.
	 * @param ctx the parse tree
	 */
	void enterConstructorInitializer(@NotNull CPP14Parser.ConstructorInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#constructorInitializer}.
	 * @param ctx the parse tree
	 */
	void exitConstructorInitializer(@NotNull CPP14Parser.ConstructorInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#literalOperatorId}.
	 * @param ctx the parse tree
	 */
	void enterLiteralOperatorId(@NotNull CPP14Parser.LiteralOperatorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#literalOperatorId}.
	 * @param ctx the parse tree
	 */
	void exitLiteralOperatorId(@NotNull CPP14Parser.LiteralOperatorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#theOperator}.
	 * @param ctx the parse tree
	 */
	void enterTheOperator(@NotNull CPP14Parser.TheOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#theOperator}.
	 * @param ctx the parse tree
	 */
	void exitTheOperator(@NotNull CPP14Parser.TheOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#tryBlock}.
	 * @param ctx the parse tree
	 */
	void enterTryBlock(@NotNull CPP14Parser.TryBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#tryBlock}.
	 * @param ctx the parse tree
	 */
	void exitTryBlock(@NotNull CPP14Parser.TryBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull CPP14Parser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull CPP14Parser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#linkageSpecification}.
	 * @param ctx the parse tree
	 */
	void enterLinkageSpecification(@NotNull CPP14Parser.LinkageSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#linkageSpecification}.
	 * @param ctx the parse tree
	 */
	void exitLinkageSpecification(@NotNull CPP14Parser.LinkageSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#noPointerAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoPointerAbstractDeclarator(@NotNull CPP14Parser.NoPointerAbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#noPointerAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoPointerAbstractDeclarator(@NotNull CPP14Parser.NoPointerAbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#virtualSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterVirtualSpecifier(@NotNull CPP14Parser.VirtualSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#virtualSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitVirtualSpecifier(@NotNull CPP14Parser.VirtualSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#initcapture}.
	 * @param ctx the parse tree
	 */
	void enterInitcapture(@NotNull CPP14Parser.InitcaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#initcapture}.
	 * @param ctx the parse tree
	 */
	void exitInitcapture(@NotNull CPP14Parser.InitcaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#blockDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBlockDeclaration(@NotNull CPP14Parser.BlockDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#blockDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBlockDeclaration(@NotNull CPP14Parser.BlockDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#declarationseq}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationseq(@NotNull CPP14Parser.DeclarationseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#declarationseq}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationseq(@NotNull CPP14Parser.DeclarationseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#conversionTypeId}.
	 * @param ctx the parse tree
	 */
	void enterConversionTypeId(@NotNull CPP14Parser.ConversionTypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#conversionTypeId}.
	 * @param ctx the parse tree
	 */
	void exitConversionTypeId(@NotNull CPP14Parser.ConversionTypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#templateParameter}.
	 * @param ctx the parse tree
	 */
	void enterTemplateParameter(@NotNull CPP14Parser.TemplateParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#templateParameter}.
	 * @param ctx the parse tree
	 */
	void exitTemplateParameter(@NotNull CPP14Parser.TemplateParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#attributeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAttributeDeclaration(@NotNull CPP14Parser.AttributeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#attributeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAttributeDeclaration(@NotNull CPP14Parser.AttributeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#pointerOperator}.
	 * @param ctx the parse tree
	 */
	void enterPointerOperator(@NotNull CPP14Parser.PointerOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#pointerOperator}.
	 * @param ctx the parse tree
	 */
	void exitPointerOperator(@NotNull CPP14Parser.PointerOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStatement(@NotNull CPP14Parser.LabeledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStatement(@NotNull CPP14Parser.LabeledStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void enterShiftOperator(@NotNull CPP14Parser.ShiftOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void exitShiftOperator(@NotNull CPP14Parser.ShiftOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#lambdaDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterLambdaDeclarator(@NotNull CPP14Parser.LambdaDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#lambdaDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitLambdaDeclarator(@NotNull CPP14Parser.LambdaDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(@NotNull CPP14Parser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(@NotNull CPP14Parser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAbstractDeclarator(@NotNull CPP14Parser.AbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAbstractDeclarator(@NotNull CPP14Parser.AbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(@NotNull CPP14Parser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(@NotNull CPP14Parser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(@NotNull CPP14Parser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(@NotNull CPP14Parser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull CPP14Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull CPP14Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#pureSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterPureSpecifier(@NotNull CPP14Parser.PureSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#pureSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitPureSpecifier(@NotNull CPP14Parser.PureSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(@NotNull CPP14Parser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(@NotNull CPP14Parser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#qualifiednamespacespecifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiednamespacespecifier(@NotNull CPP14Parser.QualifiednamespacespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#qualifiednamespacespecifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiednamespacespecifier(@NotNull CPP14Parser.QualifiednamespacespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#pseudoDestructorName}.
	 * @param ctx the parse tree
	 */
	void enterPseudoDestructorName(@NotNull CPP14Parser.PseudoDestructorNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#pseudoDestructorName}.
	 * @param ctx the parse tree
	 */
	void exitPseudoDestructorName(@NotNull CPP14Parser.PseudoDestructorNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(@NotNull CPP14Parser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(@NotNull CPP14Parser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#parameterDeclarationClause}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclarationClause(@NotNull CPP14Parser.ParameterDeclarationClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#parameterDeclarationClause}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclarationClause(@NotNull CPP14Parser.ParameterDeclarationClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#noPointerNewDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoPointerNewDeclarator(@NotNull CPP14Parser.NoPointerNewDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#noPointerNewDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoPointerNewDeclarator(@NotNull CPP14Parser.NoPointerNewDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(@NotNull CPP14Parser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(@NotNull CPP14Parser.TypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(@NotNull CPP14Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(@NotNull CPP14Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(@NotNull CPP14Parser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(@NotNull CPP14Parser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#noExceptExpression}.
	 * @param ctx the parse tree
	 */
	void enterNoExceptExpression(@NotNull CPP14Parser.NoExceptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#noExceptExpression}.
	 * @param ctx the parse tree
	 */
	void exitNoExceptExpression(@NotNull CPP14Parser.NoExceptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#templateparameterList}.
	 * @param ctx the parse tree
	 */
	void enterTemplateparameterList(@NotNull CPP14Parser.TemplateparameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#templateparameterList}.
	 * @param ctx the parse tree
	 */
	void exitTemplateparameterList(@NotNull CPP14Parser.TemplateparameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull CPP14Parser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull CPP14Parser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#accessSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterAccessSpecifier(@NotNull CPP14Parser.AccessSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#accessSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitAccessSpecifier(@NotNull CPP14Parser.AccessSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(@NotNull CPP14Parser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(@NotNull CPP14Parser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#noPointerAbstractPackDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoPointerAbstractPackDeclarator(@NotNull CPP14Parser.NoPointerAbstractPackDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#noPointerAbstractPackDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoPointerAbstractPackDeclarator(@NotNull CPP14Parser.NoPointerAbstractPackDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#baseSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterBaseSpecifier(@NotNull CPP14Parser.BaseSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#baseSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitBaseSpecifier(@NotNull CPP14Parser.BaseSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#balancedTokenSeq}.
	 * @param ctx the parse tree
	 */
	void enterBalancedTokenSeq(@NotNull CPP14Parser.BalancedTokenSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#balancedTokenSeq}.
	 * @param ctx the parse tree
	 */
	void exitBalancedTokenSeq(@NotNull CPP14Parser.BalancedTokenSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#classHeadName}.
	 * @param ctx the parse tree
	 */
	void enterClassHeadName(@NotNull CPP14Parser.ClassHeadNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#classHeadName}.
	 * @param ctx the parse tree
	 */
	void exitClassHeadName(@NotNull CPP14Parser.ClassHeadNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#memInitializer}.
	 * @param ctx the parse tree
	 */
	void enterMemInitializer(@NotNull CPP14Parser.MemInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#memInitializer}.
	 * @param ctx the parse tree
	 */
	void exitMemInitializer(@NotNull CPP14Parser.MemInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#simpleTemplateId}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTemplateId(@NotNull CPP14Parser.SimpleTemplateIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#simpleTemplateId}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTemplateId(@NotNull CPP14Parser.SimpleTemplateIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#initializerList}.
	 * @param ctx the parse tree
	 */
	void enterInitializerList(@NotNull CPP14Parser.InitializerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#initializerList}.
	 * @param ctx the parse tree
	 */
	void exitInitializerList(@NotNull CPP14Parser.InitializerListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#classKey}.
	 * @param ctx the parse tree
	 */
	void enterClassKey(@NotNull CPP14Parser.ClassKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#classKey}.
	 * @param ctx the parse tree
	 */
	void exitClassKey(@NotNull CPP14Parser.ClassKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#typeIdOfTheTypeId}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdOfTheTypeId(@NotNull CPP14Parser.TypeIdOfTheTypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#typeIdOfTheTypeId}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdOfTheTypeId(@NotNull CPP14Parser.TypeIdOfTheTypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#unqualifiedId}.
	 * @param ctx the parse tree
	 */
	void enterUnqualifiedId(@NotNull CPP14Parser.UnqualifiedIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#unqualifiedId}.
	 * @param ctx the parse tree
	 */
	void exitUnqualifiedId(@NotNull CPP14Parser.UnqualifiedIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(@NotNull CPP14Parser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(@NotNull CPP14Parser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(@NotNull CPP14Parser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(@NotNull CPP14Parser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#theTypeName}.
	 * @param ctx the parse tree
	 */
	void enterTheTypeName(@NotNull CPP14Parser.TheTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#theTypeName}.
	 * @param ctx the parse tree
	 */
	void exitTheTypeName(@NotNull CPP14Parser.TheTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#originalNamespaceName}.
	 * @param ctx the parse tree
	 */
	void enterOriginalNamespaceName(@NotNull CPP14Parser.OriginalNamespaceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#originalNamespaceName}.
	 * @param ctx the parse tree
	 */
	void exitOriginalNamespaceName(@NotNull CPP14Parser.OriginalNamespaceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarator(@NotNull CPP14Parser.MemberDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarator(@NotNull CPP14Parser.MemberDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#classVirtSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterClassVirtSpecifier(@NotNull CPP14Parser.ClassVirtSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#classVirtSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitClassVirtSpecifier(@NotNull CPP14Parser.ClassVirtSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#namespaceAlias}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceAlias(@NotNull CPP14Parser.NamespaceAliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#namespaceAlias}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceAlias(@NotNull CPP14Parser.NamespaceAliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#templateId}.
	 * @param ctx the parse tree
	 */
	void enterTemplateId(@NotNull CPP14Parser.TemplateIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#templateId}.
	 * @param ctx the parse tree
	 */
	void exitTemplateId(@NotNull CPP14Parser.TemplateIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumkey}.
	 * @param ctx the parse tree
	 */
	void enterEnumkey(@NotNull CPP14Parser.EnumkeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumkey}.
	 * @param ctx the parse tree
	 */
	void exitEnumkey(@NotNull CPP14Parser.EnumkeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(@NotNull CPP14Parser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(@NotNull CPP14Parser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#newDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNewDeclarator(@NotNull CPP14Parser.NewDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#newDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNewDeclarator(@NotNull CPP14Parser.NewDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#pointerDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterPointerDeclarator(@NotNull CPP14Parser.PointerDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#pointerDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitPointerDeclarator(@NotNull CPP14Parser.PointerDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#noPointerDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoPointerDeclarator(@NotNull CPP14Parser.NoPointerDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#noPointerDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoPointerDeclarator(@NotNull CPP14Parser.NoPointerDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#trailingTypeSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void enterTrailingTypeSpecifierSeq(@NotNull CPP14Parser.TrailingTypeSpecifierSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#trailingTypeSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void exitTrailingTypeSpecifierSeq(@NotNull CPP14Parser.TrailingTypeSpecifierSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#baseTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterBaseTypeSpecifier(@NotNull CPP14Parser.BaseTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#baseTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitBaseTypeSpecifier(@NotNull CPP14Parser.BaseTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#namespaceName}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceName(@NotNull CPP14Parser.NamespaceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#namespaceName}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceName(@NotNull CPP14Parser.NamespaceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#typedefName}.
	 * @param ctx the parse tree
	 */
	void enterTypedefName(@NotNull CPP14Parser.TypedefNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#typedefName}.
	 * @param ctx the parse tree
	 */
	void exitTypedefName(@NotNull CPP14Parser.TypedefNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(@NotNull CPP14Parser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(@NotNull CPP14Parser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#classSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterClassSpecifier(@NotNull CPP14Parser.ClassSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#classSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitClassSpecifier(@NotNull CPP14Parser.ClassSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#memInitializerList}.
	 * @param ctx the parse tree
	 */
	void enterMemInitializerList(@NotNull CPP14Parser.MemInitializerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#memInitializerList}.
	 * @param ctx the parse tree
	 */
	void exitMemInitializerList(@NotNull CPP14Parser.MemInitializerListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#dynamicExceptionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterDynamicExceptionSpecification(@NotNull CPP14Parser.DynamicExceptionSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#dynamicExceptionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitDynamicExceptionSpecification(@NotNull CPP14Parser.DynamicExceptionSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#balancedtoken}.
	 * @param ctx the parse tree
	 */
	void enterBalancedtoken(@NotNull CPP14Parser.BalancedtokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#balancedtoken}.
	 * @param ctx the parse tree
	 */
	void exitBalancedtoken(@NotNull CPP14Parser.BalancedtokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(@NotNull CPP14Parser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(@NotNull CPP14Parser.InclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#forRangeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterForRangeDeclaration(@NotNull CPP14Parser.ForRangeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#forRangeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitForRangeDeclaration(@NotNull CPP14Parser.ForRangeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#capture}.
	 * @param ctx the parse tree
	 */
	void enterCapture(@NotNull CPP14Parser.CaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#capture}.
	 * @param ctx the parse tree
	 */
	void exitCapture(@NotNull CPP14Parser.CaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(@NotNull CPP14Parser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(@NotNull CPP14Parser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#braceOrEqualInitializer}.
	 * @param ctx the parse tree
	 */
	void enterBraceOrEqualInitializer(@NotNull CPP14Parser.BraceOrEqualInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#braceOrEqualInitializer}.
	 * @param ctx the parse tree
	 */
	void exitBraceOrEqualInitializer(@NotNull CPP14Parser.BraceOrEqualInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#exceptionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExceptionDeclaration(@NotNull CPP14Parser.ExceptionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#exceptionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExceptionDeclaration(@NotNull CPP14Parser.ExceptionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#newInitializer}.
	 * @param ctx the parse tree
	 */
	void enterNewInitializer(@NotNull CPP14Parser.NewInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#newInitializer}.
	 * @param ctx the parse tree
	 */
	void exitNewInitializer(@NotNull CPP14Parser.NewInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#memberSpecification}.
	 * @param ctx the parse tree
	 */
	void enterMemberSpecification(@NotNull CPP14Parser.MemberSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#memberSpecification}.
	 * @param ctx the parse tree
	 */
	void exitMemberSpecification(@NotNull CPP14Parser.MemberSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#declarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationStatement(@NotNull CPP14Parser.DeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#declarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationStatement(@NotNull CPP14Parser.DeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull CPP14Parser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull CPP14Parser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#captureList}.
	 * @param ctx the parse tree
	 */
	void enterCaptureList(@NotNull CPP14Parser.CaptureListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#captureList}.
	 * @param ctx the parse tree
	 */
	void exitCaptureList(@NotNull CPP14Parser.CaptureListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(@NotNull CPP14Parser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(@NotNull CPP14Parser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#memberdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberdeclaration(@NotNull CPP14Parser.MemberdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#memberdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberdeclaration(@NotNull CPP14Parser.MemberdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#conversionFunctionId}.
	 * @param ctx the parse tree
	 */
	void enterConversionFunctionId(@NotNull CPP14Parser.ConversionFunctionIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#conversionFunctionId}.
	 * @param ctx the parse tree
	 */
	void exitConversionFunctionId(@NotNull CPP14Parser.ConversionFunctionIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#typeSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifierSeq(@NotNull CPP14Parser.TypeSpecifierSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#typeSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifierSeq(@NotNull CPP14Parser.TypeSpecifierSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumName}.
	 * @param ctx the parse tree
	 */
	void enterEnumName(@NotNull CPP14Parser.EnumNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumName}.
	 * @param ctx the parse tree
	 */
	void exitEnumName(@NotNull CPP14Parser.EnumNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclarationList(@NotNull CPP14Parser.ParameterDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclarationList(@NotNull CPP14Parser.ParameterDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#cvqualifierseq}.
	 * @param ctx the parse tree
	 */
	void enterCvqualifierseq(@NotNull CPP14Parser.CvqualifierseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#cvqualifierseq}.
	 * @param ctx the parse tree
	 */
	void exitCvqualifierseq(@NotNull CPP14Parser.CvqualifierseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#memberDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaratorList(@NotNull CPP14Parser.MemberDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#memberDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaratorList(@NotNull CPP14Parser.MemberDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#virtualSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void enterVirtualSpecifierSeq(@NotNull CPP14Parser.VirtualSpecifierSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#virtualSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void exitVirtualSpecifierSeq(@NotNull CPP14Parser.VirtualSpecifierSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(@NotNull CPP14Parser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(@NotNull CPP14Parser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#simpleTypeSignednessModifier}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTypeSignednessModifier(@NotNull CPP14Parser.SimpleTypeSignednessModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#simpleTypeSignednessModifier}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTypeSignednessModifier(@NotNull CPP14Parser.SimpleTypeSignednessModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumeratorDefinition}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratorDefinition(@NotNull CPP14Parser.EnumeratorDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumeratorDefinition}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratorDefinition(@NotNull CPP14Parser.EnumeratorDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#attributeList}.
	 * @param ctx the parse tree
	 */
	void enterAttributeList(@NotNull CPP14Parser.AttributeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#attributeList}.
	 * @param ctx the parse tree
	 */
	void exitAttributeList(@NotNull CPP14Parser.AttributeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratorList(@NotNull CPP14Parser.EnumeratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratorList(@NotNull CPP14Parser.EnumeratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(@NotNull CPP14Parser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(@NotNull CPP14Parser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#aliasDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAliasDeclaration(@NotNull CPP14Parser.AliasDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#aliasDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAliasDeclaration(@NotNull CPP14Parser.AliasDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumbase}.
	 * @param ctx the parse tree
	 */
	void enterEnumbase(@NotNull CPP14Parser.EnumbaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumbase}.
	 * @param ctx the parse tree
	 */
	void exitEnumbase(@NotNull CPP14Parser.EnumbaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#storageClassSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStorageClassSpecifier(@NotNull CPP14Parser.StorageClassSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#storageClassSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStorageClassSpecifier(@NotNull CPP14Parser.StorageClassSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#newTypeId}.
	 * @param ctx the parse tree
	 */
	void enterNewTypeId(@NotNull CPP14Parser.NewTypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#newTypeId}.
	 * @param ctx the parse tree
	 */
	void exitNewTypeId(@NotNull CPP14Parser.NewTypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#statementSeq}.
	 * @param ctx the parse tree
	 */
	void enterStatementSeq(@NotNull CPP14Parser.StatementSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#statementSeq}.
	 * @param ctx the parse tree
	 */
	void exitStatementSeq(@NotNull CPP14Parser.StatementSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(@NotNull CPP14Parser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(@NotNull CPP14Parser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#usingDirective}.
	 * @param ctx the parse tree
	 */
	void enterUsingDirective(@NotNull CPP14Parser.UsingDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#usingDirective}.
	 * @param ctx the parse tree
	 */
	void exitUsingDirective(@NotNull CPP14Parser.UsingDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#parametersAndQualifiers}.
	 * @param ctx the parse tree
	 */
	void enterParametersAndQualifiers(@NotNull CPP14Parser.ParametersAndQualifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#parametersAndQualifiers}.
	 * @param ctx the parse tree
	 */
	void exitParametersAndQualifiers(@NotNull CPP14Parser.ParametersAndQualifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#declaratorid}.
	 * @param ctx the parse tree
	 */
	void enterDeclaratorid(@NotNull CPP14Parser.DeclaratoridContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#declaratorid}.
	 * @param ctx the parse tree
	 */
	void exitDeclaratorid(@NotNull CPP14Parser.DeclaratoridContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#abstractPackDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAbstractPackDeclarator(@NotNull CPP14Parser.AbstractPackDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#abstractPackDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAbstractPackDeclarator(@NotNull CPP14Parser.AbstractPackDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#templateDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTemplateDeclaration(@NotNull CPP14Parser.TemplateDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#templateDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTemplateDeclaration(@NotNull CPP14Parser.TemplateDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#nestedNameSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterNestedNameSpecifier(@NotNull CPP14Parser.NestedNameSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#nestedNameSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitNestedNameSpecifier(@NotNull CPP14Parser.NestedNameSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull CPP14Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull CPP14Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumerator}.
	 * @param ctx the parse tree
	 */
	void enterEnumerator(@NotNull CPP14Parser.EnumeratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumerator}.
	 * @param ctx the parse tree
	 */
	void exitEnumerator(@NotNull CPP14Parser.EnumeratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#trailingTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTrailingTypeSpecifier(@NotNull CPP14Parser.TrailingTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#trailingTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTrailingTypeSpecifier(@NotNull CPP14Parser.TrailingTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#baseClause}.
	 * @param ctx the parse tree
	 */
	void enterBaseClause(@NotNull CPP14Parser.BaseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#baseClause}.
	 * @param ctx the parse tree
	 */
	void exitBaseClause(@NotNull CPP14Parser.BaseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#opaqueEnumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterOpaqueEnumDeclaration(@NotNull CPP14Parser.OpaqueEnumDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#opaqueEnumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitOpaqueEnumDeclaration(@NotNull CPP14Parser.OpaqueEnumDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(@NotNull CPP14Parser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(@NotNull CPP14Parser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#conversionDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConversionDeclarator(@NotNull CPP14Parser.ConversionDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#conversionDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConversionDeclarator(@NotNull CPP14Parser.ConversionDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#captureDefault}.
	 * @param ctx the parse tree
	 */
	void enterCaptureDefault(@NotNull CPP14Parser.CaptureDefaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#captureDefault}.
	 * @param ctx the parse tree
	 */
	void exitCaptureDefault(@NotNull CPP14Parser.CaptureDefaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#attributeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterAttributeSpecifier(@NotNull CPP14Parser.AttributeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#attributeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitAttributeSpecifier(@NotNull CPP14Parser.AttributeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceDefinition(@NotNull CPP14Parser.NamespaceDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceDefinition(@NotNull CPP14Parser.NamespaceDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#attributeSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void enterAttributeSpecifierSeq(@NotNull CPP14Parser.AttributeSpecifierSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#attributeSpecifierSeq}.
	 * @param ctx the parse tree
	 */
	void exitAttributeSpecifierSeq(@NotNull CPP14Parser.AttributeSpecifierSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(@NotNull CPP14Parser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(@NotNull CPP14Parser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#bracedInitList}.
	 * @param ctx the parse tree
	 */
	void enterBracedInitList(@NotNull CPP14Parser.BracedInitListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#bracedInitList}.
	 * @param ctx the parse tree
	 */
	void exitBracedInitList(@NotNull CPP14Parser.BracedInitListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#classOrDeclType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrDeclType(@NotNull CPP14Parser.ClassOrDeclTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#classOrDeclType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrDeclType(@NotNull CPP14Parser.ClassOrDeclTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#elaboratedTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterElaboratedTypeSpecifier(@NotNull CPP14Parser.ElaboratedTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#elaboratedTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitElaboratedTypeSpecifier(@NotNull CPP14Parser.ElaboratedTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#templateName}.
	 * @param ctx the parse tree
	 */
	void enterTemplateName(@NotNull CPP14Parser.TemplateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#templateName}.
	 * @param ctx the parse tree
	 */
	void exitTemplateName(@NotNull CPP14Parser.TemplateNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#deleteExpression}.
	 * @param ctx the parse tree
	 */
	void enterDeleteExpression(@NotNull CPP14Parser.DeleteExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#deleteExpression}.
	 * @param ctx the parse tree
	 */
	void exitDeleteExpression(@NotNull CPP14Parser.DeleteExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#trailingReturnType}.
	 * @param ctx the parse tree
	 */
	void enterTrailingReturnType(@NotNull CPP14Parser.TrailingReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#trailingReturnType}.
	 * @param ctx the parse tree
	 */
	void exitTrailingReturnType(@NotNull CPP14Parser.TrailingReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#forRangeInitializer}.
	 * @param ctx the parse tree
	 */
	void enterForRangeInitializer(@NotNull CPP14Parser.ForRangeInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#forRangeInitializer}.
	 * @param ctx the parse tree
	 */
	void exitForRangeInitializer(@NotNull CPP14Parser.ForRangeInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#alignmentspecifier}.
	 * @param ctx the parse tree
	 */
	void enterAlignmentspecifier(@NotNull CPP14Parser.AlignmentspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#alignmentspecifier}.
	 * @param ctx the parse tree
	 */
	void exitAlignmentspecifier(@NotNull CPP14Parser.AlignmentspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#operatorFunctionId}.
	 * @param ctx the parse tree
	 */
	void enterOperatorFunctionId(@NotNull CPP14Parser.OperatorFunctionIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#operatorFunctionId}.
	 * @param ctx the parse tree
	 */
	void exitOperatorFunctionId(@NotNull CPP14Parser.OperatorFunctionIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(@NotNull CPP14Parser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(@NotNull CPP14Parser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterEnumSpecifier(@NotNull CPP14Parser.EnumSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitEnumSpecifier(@NotNull CPP14Parser.EnumSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPP14Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(@NotNull CPP14Parser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPP14Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(@NotNull CPP14Parser.JumpStatementContext ctx);
}