import java.beans.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import com.sun.jdi.connect.Connector.Argument;

import CPP14Parser.StatementSeqContext;
import businessStructure.AADLFunction;
import businessStructure.AADLThread;
import helpers.CPP14Parser;
import helpers.treeHelpers;

public class CPPCustomListener extends CPP14ParserBaseListener {


	public HashMap<String, AADLThread>  threadSet = new HashMap<String,AADLThread>();
	public HashMap<String,AADLFunction> functionSet = new HashMap<String,AADLFunction>();
	public HashMap<String,String> functionFileMap = new HashMap<String,String>();
	public HashMap<String,Integer> fileDecompositionMap = new HashMap<String,Integer>();






	public CPPCustomListener(HashMap<String, Integer> fileDecompositionMap) {
		super();
		this.fileDecompositionMap = fileDecompositionMap;
	}

	@Override
	public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {

		//		System.err.println(fileDecompositionMap);
		// find all functions during the definition and save them in memory

		System.out.println(" function def : " + ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText());
		

		
		String currrentfunctionName = ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText();
		AADLFunction currentFunction = new AADLFunction(currrentfunctionName);
		ArrayList<String> subFunctionSet = new ArrayList<>();
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
		HashMap<Integer, ArrayList<String>> listArguments = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> Arguments = new ArrayList<String>();
		ArrayList<String> Tryarguments = new ArrayList<String>();
		HashMap<Integer, ArrayList<String>> trylistArguments = new HashMap<Integer, ArrayList<String>>();

		for(int i=0; i < statementSeq.getChildCount() ; i++) {
			//			System.out.println("statement " + statementSeq.getChild(i).getText());

			if(statementSeq.getChild(i).getText().contains("try{")) {
				//				System.err.println("count : " + statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChildCount());	
				//				System.err.println("class " + statementSeq.getChild(i).getChild(0).getClass());
				CPP14Parser.StatementSeqContext tryStatementSeq = null;
				if (!statementSeq.getChild(i).getChild(0).getClass().toString().contains("LabeledStatement"))
					tryStatementSeq = (CPP14Parser.StatementSeqContext) statementSeq.getChild(i).getChild(0).getChild(1).getChild(1);
				else
					tryStatementSeq = (CPP14Parser.StatementSeqContext) statementSeq.getChild(i).getChild(0).getChild(2).getChild(0).getChild(1).getChild(1);
				for (int j = 0; j < tryStatementSeq.getChildCount(); j++) {
					StatFunction trySubStatementFunction = isStatementFuntion((CPP14Parser.StatementContext) tryStatementSeq.getChild(j));
					
					if(trySubStatementFunction.Isstatfunction) {
						String trySubFunctionName = new String();
						//						System.out.println("debug = " + statementSeq.getChild(i).getChild(0).getChild(1).getClass());
						//						String trySubFunctionName = statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChild(j).getChild(0).getChild(0).getChild(0)
						//								.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
						
						
						// get Arguments inside a try statement
						trylistArguments.put(j, new ArrayList<String>());
//						System.out.println(j);
						if (trySubStatementFunction.FunctionType == 1) {
							trySubFunctionName = getxChild0(tryStatementSeq.getChild(j), 8).getText();
						//case f(x,y,...)
							if (tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChildCount() == 3) {

//								System.out.println("statement n" + j + "is a function with multiples args");

								for (int k = 0; k < tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
									if (!tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText().equals(",")) {
										trylistArguments.get(j).add(tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText());
									}
								}
							}
							else {
						// case  f()	
								trylistArguments.get(j).add("");
//								System.out.println("statement n "+ j +"NoArg");
							}
						}

						if(trySubStatementFunction.FunctionType == 2) {					
						// case f(&x)
							trySubFunctionName = getxChild0(tryStatementSeq.getChild(j), 8).getText();
							if (tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChildCount() ==2) {//cas(&x)
//								System.out.println("statement n "+ j +" pointer");
								trylistArguments.get(j).add(tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getText());
							}
						// case f(x)
							else {
//								System.out.println("statement n "+ j +" 1 arg");
								trylistArguments.get(j).add(tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getText());
							}
						}
						// case function(&abc, x, ...)
						if (trySubStatementFunction.FunctionType == 3) { 
							trySubFunctionName = getxChild0(tryStatementSeq.getChild(j), 8).getText();
//							System.out.println("statement n" + j + "is a function with multiples args&pointer");
							for (int k = 0; k < tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
								if (!(tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText().equals(","))) {
									trylistArguments.get(j).add(tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText());
//									System.out.println(tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText());
								}	
							}
						}
						if (trySubStatementFunction.FunctionType == 4) {
								trySubFunctionName = getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 17).getText();
									System.out.println(getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount());
									for (int k = 0; k < getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount(); k++) {
										if (!getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().equals(",")) {
											trylistArguments.get(j).add(getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText());
										}
									}
								}
								else {
									trySubFunctionName = getxChild0(tryStatementSeq.getChild(j), 9).getText();
								}
						subFunctionSet.add(trySubFunctionName);
						
//						System.out.println("~~~    " + trySubFunctionName +trylistArguments.get(j));
						functionSet.put(trySubFunctionName, new AADLFunction(trySubFunctionName, currrentfunctionName, trylistArguments.get(j)));
					}
//					System.err.println(" try sub statement : " + statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChild(j).getText());	
				}
			}
			// get arguments inside a statement
			StatFunction statementFunction = isStatementFuntion((CPP14Parser.StatementContext) statementSeq.getChild(i));
//						System.out.println("isStatementFuntion "+ statementFunction);
			
			if(statementFunction.Isstatfunction) {
				String subFunctionName = new String();
				// arguments 
				listArguments.put(i, new ArrayList<String>());
//				System.out.println("i = " + i);
				if (statementFunction.FunctionType == 1) {
					//arguments
				subFunctionName = getxChild0(statementSeq.getChild(i), 9).getText();	
				//case f(x,y,...)
					if (statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChildCount() == 3) {
						
						System.out.println("statement n" + i + "is a function with multiples args");
						
						for (int k = 0; k < statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
							if (!statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText().equals(",")) {
								listArguments.get(i).add(statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText());
							}
						}
					}
					else {
				// case f()
						listArguments.get(i).add("");
//						System.out.println("statement n "+ i+"NoArg");
					}
			}
				
			if(statementFunction.FunctionType == 2)	{				
				// case f(&)
				subFunctionName = getxChild0(statementSeq.getChild(i), 9).getText();
				if (statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChildCount() ==2) {//cas(&x)
					System.out.println("statement n "+ i+" pointer");
					listArguments.get(i).add(statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getText());
				}
			   // case f(x)
				else {
					System.out.println("statement n "+ i+" 1 arg");
					listArguments.get(i).add(statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getText());
				}
			}
			   //case function(&abc, x, ...)
			if (statementFunction.FunctionType == 3) { 
				subFunctionName = getxChild0(statementSeq.getChild(i), 9).getText();
				System.out.println("statement n" + i + "is a function with multiples args&pointer");
				for (int j = 0; j < statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChildCount(); j++) {
					if (!(statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(j).getText().equals(","))) {
						listArguments.get(i).add(statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(j).getText());
					    System.out.println(statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(j).getText());
					}	
				}
			}
			// case _1 = function(...)
			if (statementFunction.FunctionType==4){
			subFunctionName = getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 17).getText();
	
				System.out.println(getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount());
				for (int k = 0; k < getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount(); k++) {
					if (!getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().equals(",")) {
						listArguments.get(i).add(getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText());
					}
				}
			}
			else {
				subFunctionName = getxChild0(statementSeq.getChild(i), 9).getText();
			}
			    
			    
//				String subFunctionName = statementSeq.getChild(i).getChild(0).getChild(0).getChild(0)
//						.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
				subFunctionSet.add(subFunctionName);
//				System.out.println( "i = " + i + subFunctionName + listArguments.get(i) );
				functionSet.put(subFunctionName, new AADLFunction(subFunctionName, currrentfunctionName ,listArguments.get(i)));//ajouter les arguments	
			}
			
			
			
			
			// get the threadfunctions and save them in the threadSet map
			if(!statementSeq.getChild(i).getText().contains("try{") && statementSeq.getChild(i).getText().contains("pthread_create"))
				threadSet.put(getFunctionName((CPP14Parser.StatementContext) statementSeq.getChild(i)), new AADLThread());
		}
		
		currentFunction.setSubFunctionSet(subFunctionSet);
		// insert entry in the functionSet
		functionSet.put(currrentfunctionName, currentFunction);

		// join the thread with its subfunctions
		if(threadSet.containsKey(currrentfunctionName))
			threadSet.get(currrentfunctionName).getThreadFunctionSet().put(currrentfunctionName, currentFunction);
		else System.out.println("thread not found yet");
		//		System.out.println(currentFunction);
	   }

	ParseTree tree = new ParseTree() {
		
		@Override
		public String toStringTree() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object getPayload() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getChildCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Interval getSourceInterval() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String toStringTree(Parser arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getText() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ParseTree getParent() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ParseTree getChild(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	};
//	
/////////////////////////////////////////////////
	
	
	
	
	
	@Override
	public void exitDeclarator(CPP14Parser.DeclaratorContext ctx) {
		// TODO Auto-generated method stub
		super.exitDeclarator(ctx);
	}

	@Override
	public void exitStatement(CPP14Parser.StatementContext ctx) {
		// TODO Auto-generated method stub
		//		super.exitStatement(ctx);

		//		System.out.println("hello from custom listener ###########");

		String statementContent = ctx.getText();
		if (statementContent.contains("pthread_create") && !statementContent.contains("try{")) {

			String threadFunctionName = statementContent.split(",")[2];

			//			System.out.println("Thread \n thread Function name +++++++> " + threadFunctionName);		

			threadSet.put(threadFunctionName, GrammarHelper.getThread(ctx));

			//			System.out.println(this);
		}
	}

	@Override
	public String toString() {
		return "CPPCustomListener [threadSet=" + threadSet + ",\n\t\t\t\t\t functionSet=" + functionSet + "]";
	}
	
	
	private StatFunction isStatementFuntion (CPP14Parser.StatementContext ctx) {

		StatFunction Sfun = new StatFunction(false, 0);
		ANTLRInputStream input = new ANTLRInputStream(  );
		CPP14Lexer lexer = new CPP14Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CPP14Parser parser = new CPP14Parser(tokens);

		//		if(ctx != null) {
		//		System.out.println(ctx.getText());
		//	    Object grammarElement = ctx.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1);
		//	    System.err.println(grammarElement.getClass());
		//	      if(grammarElement instanceof CPP14Parser.ParametersAndQualifiersContext) 
		//	        flag = true;
		//		}
		ParseTree grammarElement = ctx;
		while (true) {
			if(grammarElement.getChild(0) == null) 
				break;
			// key = 1 : functions without parameters & multiples arguments : f(); ou  f(x,y,...);
			// key = 2 functions with 1 parameter with ou without pointer : f(x); ou f(&x);
			// key = 3 functions with multiples arguments including at least 1 pointer : f(&x,y,...);
			// key = 4 functions called for a temporary variable _1 = f(..) ;

			grammarElement =  grammarElement.getChild(0);
			if(grammarElement instanceof CPP14Parser.NoPointerDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.ParametersAndQualifiersContext) {
				Sfun.setFunctionType(1);
				Sfun.setIsstatfunction(true);
				break;
			}
			if(grammarElement instanceof CPP14Parser.SimpleDeclarationContext &&  grammarElement.getChild(0) instanceof CPP14Parser.DeclSpecifierSeqContext && grammarElement.getChild(0).getChild(0).getChild(0).getChild(0)  instanceof CPP14Parser.TrailingTypeSpecifierContext) {
				if (grammarElement.getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChildCount() == 3) {
				Sfun.setFunctionType(2);
				Sfun.setIsstatfunction(true);	
				}
				break;
			}
			if(grammarElement instanceof CPP14Parser.InitDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.InitializerContext) {
				if (grammarElement.getChild(1).getChildCount() == 3) {
					Sfun.setFunctionType(3);
					Sfun.setIsstatfunction(true);
					break;
				}
				else if (grammarElement.getChild(1).getChild(0).getChild(1).toStringTree(parser).contains("initializerList (")&& grammarElement.getChild(1).getChild(0).getChild(1).toStringTree(parser).contains("postfixExpression (")) {
					System.out.println(grammarElement.toStringTree(parser));
					Sfun.setFunctionType(4);
					Sfun.setIsstatfunction(true);
					break;
				}
				
			}
		}
		return Sfun ;
	}
	
	public ParseTree getxChild0(ParseTree ctx, int x) {
		for (int i = 0; i < x; i++) {
			ctx = ctx.getChild(0);
		}
	return ctx;
	}

	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {

		//		System.err.println("class....... " + ctx.getChild(0).getChild(2).getChild(0).getChild(1).getClass());
		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);

	}

	private String getFunctionName (CPP14Parser.StatementContext ctx) {
		return ctx.getText().split(",")[2];

	}

}
