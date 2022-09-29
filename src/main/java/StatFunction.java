
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import helpers.treeHelpers;			
public class StatFunction {
	
	private boolean Isstatfunction ;
	private Integer FunctionType;

	public StatFunction(Boolean Isf, Integer Ftype) {
		this.FunctionType = Ftype;
		this.Isstatfunction = Isf;
	}

	public StatFunction() {
		// TODO Auto-generated constructor stub
	}

	public boolean getIsstatfunction() {
		return Isstatfunction;
	}

	public void setIsstatfunction(boolean isstatfunction) {
		this.Isstatfunction = isstatfunction;
	}
	public void setstatfunction(boolean isstatfunction, int FType) {
		this.Isstatfunction = isstatfunction;
		this.FunctionType = FType;
	}

	public Integer getFunctionType() {
		return FunctionType;
	}
	 

	public void setFunctionType(Integer functionType) {
		this.FunctionType = functionType;
	}
	
	
		/**
		 *@ This function is used to know which kind of function the statement contains and therefore, how to get information
		 *@ in :  parse tree
		 *@ return : stat function type (Is the statement a function? true/false, type of function )		
		 *@ key = 1   : functions without parameters & multiples arguments : f(); or  f(x,y,...);		
		 *@ key = 11  : functions without parameters : xxxx::f();		
		 *@ key = 12  : functions without parameters : xxxx::f(x,y,...);		
		 *@ key = 2   : functions with 1 parameter with ou without pointer : f(x); or f(&x);		
		 *@ key = 22  : functions with 1 parameter with ou without pointer : xxxx::f(x); or xxxxxf(&x);		
		 *@ key = 3   : functions with multiples arguments including at least 1 pointer : f(&x,y,...);		
		 *@ key = 33  : function 		
		 *@ key = 4   : functions called for a temporary variable _1 = f(..) ;		
		 *@ key = 44  :		
		 */
	public StatFunction isStatementFuntion (ParseTree ctx) {

		StatFunction Sfun = new StatFunction(false, 0);
		ANTLRInputStream input = new ANTLRInputStream(  );
		CPP14Lexer lexer = new CPP14Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CPP14Parser parser = new CPP14Parser(tokens);
		treeHelpers tH = new treeHelpers();
		ParseTree grammarElement = ctx;
		while (true) {
			if(grammarElement.getChild(0) == null) 
				break;
			grammarElement =  grammarElement.getChild(0);
			if(grammarElement instanceof CPP14Parser.NoPointerDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.ParametersAndQualifiersContext) 
				return new StatFunction(true, 1);

			if(grammarElement instanceof CPP14Parser.SimpleDeclarationContext &&  grammarElement.getChild(0) instanceof CPP14Parser.DeclSpecifierSeqContext 
					&& grammarElement.getChild(0).getChild(0).getChild(0).getChild(0)  instanceof CPP14Parser.TrailingTypeSpecifierContext) {
				
				if (tH.getxChild0(grammarElement.getChild(1), 4).getChildCount() == 3) {
					if (tH.getxChild0(grammarElement, 5).getChildCount()==2) {
						return new StatFunction(true, 22);
					}
					else return new StatFunction(true, 2);
				}
					
				
				if(grammarElement.getChild(1).getChild(0).getChild(1) == null) {
					if (tH.getxChild0(grammarElement.getChild(1), 4) instanceof CPP14Parser.NoPointerDeclaratorContext && tH.getxChild0(grammarElement.getChild(1), 4).getChild(1) instanceof CPP14Parser.ParametersAndQualifiersContext) {
						
						if (tH.getxChild0(grammarElement.getChild(1), 4).getChild(1).getChildCount()==2) 
							return new StatFunction(true, 11);
						if (tH.getxChild0(grammarElement.getChild(1), 4).getChild(1).getChildCount()==3) 
							return new StatFunction(true, 12);
					}
					else break;
				}
				else if (grammarElement.getChild(1).getChild(0) instanceof CPP14Parser.InitDeclaratorContext &&  grammarElement.getChild(1).getChild(0).getChild(1) instanceof CPP14Parser.InitializerContext) {
					return new StatFunction(true, 33);
				}
			}
			if(grammarElement instanceof CPP14Parser.InitDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.InitializerContext) {
				
				if (grammarElement.getChild(1).getChildCount() == 3) {
					return new StatFunction(true, 3);
				}
				else if (grammarElement.getChild(1).getChild(0).getChild(1).toStringTree(parser).contains("initializerList (")
						&& grammarElement.getChild(1).getChild(0).getChild(1).toStringTree(parser).contains("postfixExpression (")) {
					return new StatFunction(true, 4);
				}
				else if (grammarElement.getChild(1).getChild(0).getChild(1).toStringTree(parser).contains("( )")
						&& grammarElement.getChild(1).getChild(0).getChild(1).toStringTree(parser).contains("postfixExpression (")) {
					return new StatFunction(true, 44);
				}
			}
		}
		return Sfun ;
	}

}
