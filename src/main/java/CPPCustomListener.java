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
import helpers.GlobalVariablesEnum;
import helpers.treeHelpers;

public class CPPCustomListener extends CPP14ParserBaseListener {


	public HashMap<String, AADLThread>  threadSet = new HashMap<String,AADLThread>();
	public HashMap<String,AADLFunction> functionSet = new HashMap<String,AADLFunction>();
	public HashMap<String,String> functionFileMap = new HashMap<String,String>();
	public HashMap<String,Integer> fileDecompositionMap = new HashMap<String,Integer>();
	public HashMap<String, ArrayList<String>> globalvariablesSet = new HashMap<String, ArrayList<String>>() ;

	
	

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
// try statement
					if(trySubStatementFunction.Isstatfunction) {
// function name + arguments retrieving 
						String trySubFunctionName = new String();
						//						System.out.println("debug = " + statementSeq.getChild(i).getChild(0).getChild(1).getClass());
						//						String trySubFunctionName = statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChild(j).getChild(0).getChild(0).getChild(0)
						//								.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
						
						
						trylistArguments.put(j, new ArrayList<String>());
//						System.out.println(j);
						if (trySubStatementFunction.FunctionType == 1) {
							trySubFunctionName = getxChild0(tryStatementSeq.getChild(j), 8).getText();
						//case f(x,y,...)
							if (getxChild0(tryStatementSeq.getChild(j),8).getChild(1).getChildCount() == 3) {

//								System.out.println("statement n" + j + "is a function with multiples args");

								for (int k = 0; k < getxChild0(tryStatementSeq.getChild(j),8).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
									if (!getxChild0(tryStatementSeq.getChild(j),8).getChild(1).getChild(1).getChild(0).getChild(k).getText().equals(",") 
											&& !getxChild0(tryStatementSeq.getChild(j),8).getChild(1).getChild(1).getChild(0).getChild(k).getText().contains("()")
											&& !getxChild0(tryStatementSeq.getChild(j),8).getChild(1).getChild(1).getChild(0).getChild(k).getText().chars().allMatch( Character::isDigit )) {
										trylistArguments.get(j).add(getxChild0(tryStatementSeq.getChild(j),8).getChild(1).getChild(1).getChild(0).getChild(k).getText());
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
							if (getxChild0(getxChild0(tryStatementSeq.getChild(j),3).getChild(1),4).getChild(1).getChildCount() ==2) {//cas(&x)
//								System.out.println("statement n "+ j +" pointer");
								trylistArguments.get(j).add(getxChild0(getxChild0(tryStatementSeq.getChild(j),3).getChild(1),4).getChild(1).getText());
							}
						// case f(x)
							else {
//								System.out.println("statement n "+ j +" 1 arg");
								trylistArguments.get(j).add(getxChild0(getxChild0(tryStatementSeq.getChild(j),3).getChild(1),4).getChild(1).getText());
							}
						}
						// case function(&abc, x, ...)
						if (trySubStatementFunction.FunctionType == 3) { 
							trySubFunctionName = getxChild0(tryStatementSeq.getChild(j), 8).getText();
//							System.out.println("statement n" + j + "is a function with multiples args&pointer");
							for (int k = 0; k < getxChild0(tryStatementSeq.getChild(j),5).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
								if (!(getxChild0(tryStatementSeq.getChild(j),5).getChild(1).getChild(1).getChild(0).getChild(k).getText().equals(",")
										&& !getxChild0(tryStatementSeq.getChild(j),5).getChild(1).getChild(1).getChild(0).getChild(k).getText().contains("()"))
										&& !getxChild0(tryStatementSeq.getChild(j),5).getChild(1).getChild(1).getChild(0).getChild(k).getText().chars().allMatch( Character::isDigit)) {
									trylistArguments.get(j).add(getxChild0(tryStatementSeq.getChild(j),5).getChild(1).getChild(1).getChild(0).getChild(k).getText());
//									System.out.println(tryStatementSeq.getChild(j).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(k).getText());
								}	
							}
						}
						if (trySubStatementFunction.FunctionType == 4) {
						//case _1 = f(...)
								trySubFunctionName = getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 17).getText();
//									System.out.println(getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount());
									for (int k = 0; k < getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount(); k++) {
										if (!getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().equals(",") 
												&& !getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().contains("()")
												&& !getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().chars().allMatch( Character::isDigit)) {
											trylistArguments.get(j).add(getxChild0( getxChild0(tryStatementSeq.getChild(j), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText());
										}
									}
								}
								else {
									trySubFunctionName = getxChild0(tryStatementSeq.getChild(j), 9).getText();
								}			

//	try global variables retrieving
						if (GlobalVariablesEnum.testenumb(trylistArguments.toString())) {
							String tryfglobalvariableName = new String ();
							ArrayList<String> tryfglobalvariableParameters = new ArrayList<String>();
//							System.out.println("fonction avec glob");
							for (int m = 0; m < trylistArguments.size(); m++) {
								if (GlobalVariablesEnum.testenumb(listArguments.get(j).get(m))) {

									if (trySubStatementFunction.FunctionType == 4) {
										tryfglobalvariableName = listArguments.get(j).get(m);
										tryfglobalvariableParameters.add(currrentfunctionName);
										tryfglobalvariableParameters.add("read");
									}
									else {
										tryfglobalvariableName = listArguments.get(j).get(m);
										tryfglobalvariableParameters.add(currrentfunctionName);
										tryfglobalvariableParameters.add("write");
									}
								}
							}
							if (!globalvariablesSet.containsKey(tryfglobalvariableName)) {
								globalvariablesSet.put(tryfglobalvariableName, tryfglobalvariableParameters);
							}
							else {
								int n = 0;
								while(true) {
									n++;
									if (!functionSet.containsKey(tryfglobalvariableName+"/"+n))  {
										tryfglobalvariableName=tryfglobalvariableName+"/"+n;
										globalvariablesSet.put(tryfglobalvariableName, tryfglobalvariableParameters);
										break;
									}
								}
							}
						}
// add function into function set
//						System.out.println("~~~    " + trySubFunctionName +trylistArguments.get(j));
						if (!functionSet.containsKey(trySubFunctionName)) {
					    functionSet.put(trySubFunctionName, new AADLFunction(trySubFunctionName, currrentfunctionName, trylistArguments.get(j)));
						}
						else {
							int n = 0;
							while(true) {
								n++;
								if (!functionSet.containsKey(trySubFunctionName+"/"+n))  {
									trySubFunctionName=trySubFunctionName+"/"+n;
									functionSet.put(trySubFunctionName, new AADLFunction(trySubFunctionName, currrentfunctionName, trylistArguments.get(j)));
									break;
								}
							}
						}
						subFunctionSet.add(trySubFunctionName);
					}
//					System.err.println(" try sub statement : " + statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChild(j).getText());	
				}
			}
// end try statement
			
// classic statement
			// get arguments + name inside a statement
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
					if (getxChild0(statementSeq.getChild(i),8).getChild(1).getChildCount() == 3) {
						
//						System.out.println("statement n" + i + "is a function with multiples args");
						
						for (int k = 0; k < getxChild0(statementSeq.getChild(i),8).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
							if (!getxChild0(statementSeq.getChild(i),8).getChild(1).getChild(1).getChild(0).getChild(k).getText().equals(",") 
									&& !getxChild0(statementSeq.getChild(i),8).getChild(1).getChild(1).getChild(0).getChild(k).getText().contains("()")
									&& !getxChild0(statementSeq.getChild(i),8).getChild(1).getChild(1).getChild(0).getChild(k).getText().chars().allMatch( Character::isDigit )) {
								listArguments.get(i).add(getxChild0(statementSeq.getChild(i),8).getChild(1).getChild(1).getChild(0).getChild(k).getText());
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
				if (getxChild0(getxChild0( statementSeq.getChild(i),3).getChild(1),4).getChild(1).getChildCount() ==2) {//cas(&x)
//					System.out.println("statement n "+ i+" pointer");
					listArguments.get(i).add(getxChild0(getxChild0(statementSeq.getChild(i),3).getChild(1),4).getChild(1).getText());
				}
			   // case f(x)
				else {
//					System.out.println("statement n "+ i+" 1 arg");
					listArguments.get(i).add(getxChild0(getxChild0(statementSeq.getChild(i),3).getChild(1),4).getChild(1).getText());
				}
			}
			   //case function(&abc, x, ...)
			if (statementFunction.FunctionType == 3) { 
				subFunctionName = getxChild0(statementSeq.getChild(i), 9).getText();
//				System.out.println("statement n" + i + "is a function with multiples args&pointer");
				for (int j = 0; j < getxChild0(statementSeq.getChild(i),5).getChild(1).getChild(1).getChild(0).getChildCount(); j++) {
					if (!(getxChild0(statementSeq.getChild(i),5).getChild(1).getChild(1).getChild(0).getChild(j).getText().equals(",") 
							&& !getxChild0(statementSeq.getChild(i),5).getChild(1).getChild(1).getChild(0).getChild(j).getText().contains("()"))
							&& !getxChild0(statementSeq.getChild(i),5).getChild(1).getChild(1).getChild(0).getChild(j).getText().chars().allMatch( Character::isDigit )) {
						listArguments.get(i).add(getxChild0(statementSeq.getChild(i),5).getChild(1).getChild(1).getChild(0).getChild(j).getText());
//					    System.out.println(statementSeq.getChild(i).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(j).getText());
					}	
				}
			}
			// case _1 = function(...)
			if (statementFunction.FunctionType==4){
			subFunctionName = getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 17).getText();
	
//				System.out.println(getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount());
				for (int k = 0; k < getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount(); k++) {
					if (!getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().equals(",") 
					 && !getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().contains("()")
					 && !getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText().chars().allMatch( Character::isDigit )) {
						listArguments.get(i).add(getxChild0( getxChild0(statementSeq.getChild(i), 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText());
					}
				}
			}
			else {
				subFunctionName = getxChild0(statementSeq.getChild(i), 9).getText();
			}
			    
			    
//				String subFunctionName = statementSeq.getChild(i).getChild(0).getChild(0).getChild(0)
//						.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
//				System.out.println( "i = " + i + subFunctionName + listArguments.get(i) );
				if (!functionSet.containsKey(subFunctionName)) {
				    functionSet.put(subFunctionName, new AADLFunction(subFunctionName, currrentfunctionName, listArguments.get(i)));
					}
					else {
						int n = 0;
						while(true) {
							n++;
							if (!functionSet.containsKey(subFunctionName+"/"+n))  {
								subFunctionName=subFunctionName+"/"+n;
								functionSet.put(subFunctionName, new AADLFunction(subFunctionName, currrentfunctionName, listArguments.get(i)));
								break;
							}
						}
					}
				subFunctionSet.add(subFunctionName);
				
				
// global variables retrieving  in functions				
				if (GlobalVariablesEnum.testenumb(listArguments.toString())) {
					String fglobalvariableName = new String ();
					ArrayList<String> fglobalvariableParameters = new ArrayList<String>();
//					System.out.println("fonction avec glob");
					for (int m = 0; m < listArguments.size(); m++) {
						if (GlobalVariablesEnum.testenumb(listArguments.get(i).get(m))) {

							if (statementFunction.FunctionType == 4) {
								fglobalvariableName = listArguments.get(i).get(m);
								fglobalvariableParameters.add(currrentfunctionName);
								fglobalvariableParameters.add("read");
							}
							else {
								fglobalvariableName = listArguments.get(i).get(m);
								fglobalvariableParameters.add(currrentfunctionName);
								fglobalvariableParameters.add("write");
							}
						}
					}
					if (!globalvariablesSet.containsKey(fglobalvariableName)) {
						globalvariablesSet.put(fglobalvariableName, fglobalvariableParameters);
					}
					else {
						int n = 0;
						while(true) {
							n++;
							if (!functionSet.containsKey(fglobalvariableName+"/"+n))  {
								fglobalvariableName=fglobalvariableName+"/"+n;
								globalvariablesSet.put(fglobalvariableName, fglobalvariableParameters);
								break;
							}
						}
					}
				}
			}

// global variables retrieving
			if (!statementFunction.Isstatfunction) {
				if (GlobalVariablesEnum.testenumb(statementSeq.getChild(i).getText())) {
					String globalvariableName = new String();
					ArrayList<String> globalvariableParameters = new ArrayList<String>();
					if (GlobalVariablesEnum.testenumb(getxChild0(statementSeq.getChild(i),8).getText())) {
						globalvariableName = getxChild0(statementSeq.getChild(i),8).getText();
						globalvariableParameters.add(currrentfunctionName);
						globalvariableParameters.add("write");
					}

					else if (GlobalVariablesEnum.testenumb(getxChild0(statementSeq.getChild(i),5).getChild(1).getText())) {
						globalvariableName = getxChild0(statementSeq.getChild(i),5).getChild(1).getChild(0).getChild(1).getText();
						globalvariableParameters.add(currrentfunctionName);
						globalvariableParameters.add("read");
//						System.out.println("true");
					}
					if (!globalvariablesSet.containsKey(globalvariableName)) {
						globalvariablesSet.put(globalvariableName, globalvariableParameters);
					}
					else {
						int n = 0;
						while(true) {
							n++;
							if (!functionSet.containsKey(globalvariableName+"/"+n))  {
								globalvariableName=globalvariableName+"/"+n;
								globalvariablesSet.put(globalvariableName, globalvariableParameters);
								break;
							}
						}
					}
//					System.out.println(globalvariableName);
				}
			}
// thread 
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
// end
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
	
// StatFunction : defines if a statement contains a function and its type (key)
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
			// key = 1 : functions without parameters & multiples arguments : f(); or  f(x,y,...);
			// key = 2 functions with 1 parameter with ou without pointer : f(x); or f(&x);
			// key = 3 functions with multiples arguments including at least 1 pointer : f(&x,y,...);
			// key = 4 functions called for a temporary variable _1 = f(..) ;

			grammarElement =  grammarElement.getChild(0);
			if(grammarElement instanceof CPP14Parser.NoPointerDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.ParametersAndQualifiersContext) {
				return new StatFunction(true, 1);
			}
			if(grammarElement instanceof CPP14Parser.SimpleDeclarationContext &&  grammarElement.getChild(0) instanceof CPP14Parser.DeclSpecifierSeqContext 
					&& grammarElement.getChild(0).getChild(0).getChild(0).getChild(0)  instanceof CPP14Parser.TrailingTypeSpecifierContext) {
				if (grammarElement.getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChildCount() == 3) {
					return new StatFunction(true, 2);
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
			}
		}
		return Sfun ;
	}
	
	// TODO move this to helpers
	// limit uses of multiple getchild(0)
	public ParseTree getxChild0(ParseTree ctx, int x) {
		for (int i = 0; i < x; i++) {
			ctx = ctx.getChild(0);
		}
	return ctx;
	}
	// until here
	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {

		//		System.err.println("class....... " + ctx.getChild(0).getChild(2).getChild(0).getChild(1).getClass());
		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);

	}

	private String getFunctionName (CPP14Parser.StatementContext ctx) {
		return ctx.getText().split(",")[2];

	}

}
