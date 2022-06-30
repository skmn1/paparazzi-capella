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
		
		String currrentfunctionName = getxChild0(ctx.getChild(0).getChild(1), 3).getText();
		
		AADLFunction currentFunction = new AADLFunction(currrentfunctionName);
		ArrayList<String> subFunctionSet = new ArrayList<>();
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
		
		HashMap<Integer, ArrayList<String>> listArguments = new HashMap<Integer, ArrayList<String>>();
		HashMap<Integer, ArrayList<String>> trylistArguments = new HashMap<Integer, ArrayList<String>>();
		HashMap<Integer, ArrayList<String>> trytrylistArguments = new HashMap<Integer, ArrayList<String>>();
		
		
		for(int i=0; i < statementSeq.getChildCount() ; i++) {
			
			if(statementSeq.getChild(i).getText().contains("try{")) {

				CPP14Parser.StatementSeqContext tryStatementSeq = null;
				if (!statementSeq.getChild(i).getChild(0).getClass().toString().contains("LabeledStatement"))
					tryStatementSeq = (CPP14Parser.StatementSeqContext) statementSeq.getChild(i).getChild(0).getChild(1).getChild(1);
				else
					tryStatementSeq = (CPP14Parser.StatementSeqContext) statementSeq.getChild(i).getChild(0).getChild(2).getChild(0).getChild(1).getChild(1);
				
				
				for (int j = 0; j < tryStatementSeq.getChildCount(); j++) {

					/////////////////////////////////////////////////////////////
					
					if(tryStatementSeq.getChild(j).getText().contains("try{")) {

						CPP14Parser.StatementSeqContext trytryStatementSeq = null;
						if (!tryStatementSeq.getChild(j).getChild(0).getClass().toString().contains("LabeledStatement"))
							trytryStatementSeq = (CPP14Parser.StatementSeqContext) tryStatementSeq.getChild(j).getChild(0).getChild(1).getChild(1);
						else
							trytryStatementSeq = (CPP14Parser.StatementSeqContext) tryStatementSeq.getChild(j).getChild(0).getChild(2).getChild(0).getChild(1).getChild(1);
						
						
						for (int z = 0; z < trytryStatementSeq.getChildCount(); z++) {
							
//							StatFunction trytrySubStatementFunction = isStatementFuntion((CPP14Parser.StatementContext) trytryStatementSeq.getChild(z));
							
							ParseTree trytryState ;
							if (trytryStatementSeq.getChild(z).getChild(0).getClass().toString().contains("LabeledStatement")) {
								trytryState = LabeledStatementdebug(trytryStatementSeq.getChild(z));
							}
							else 
								trytryState = trytryStatementSeq.getChild(z);
							StatFunction trytrySubStatementFunction = isStatementFuntion(trytryState);
							
							// trytry statement
							if(trytrySubStatementFunction.Isstatfunction) {
								//Args retrieving from function
								getArgumentsfromfunctionStatementtoFunctionSet(trytrySubStatementFunction, trytryStatementSeq, currrentfunctionName, z, trytrylistArguments, subFunctionSet);
								//	try global variables retrieving
								getGlobalVariablesfromListArgstoGlobVSet(trytrylistArguments, currrentfunctionName, trytrySubStatementFunction, z);
							}
							// global variables retrieving 
						    getGlobalVariablesfromStatementtoGlobVSet(trytryStatementSeq.getChild(z), trytrySubStatementFunction, currrentfunctionName);	
						}
					}
					//////////////////////////////////////////////////////////////
					//end trytry
				
					ParseTree tryState ;
					if (tryStatementSeq.getChild(j).getChild(0).getClass().toString().contains("LabeledStatement")) {
						tryState = LabeledStatementdebug(tryStatementSeq.getChild(j));
					}
					else 
						tryState = tryStatementSeq.getChild(j);

					StatFunction trySubStatementFunction = isStatementFuntion(tryState);
					
					// try statement
					if(trySubStatementFunction.Isstatfunction) {
						//Args retrieving from function
						getArgumentsfromfunctionStatementtoFunctionSet(trySubStatementFunction, tryStatementSeq, currrentfunctionName, j, trylistArguments, subFunctionSet);
//						System.out.println(trylistArguments);
						//	try global variables retrieving
						getGlobalVariablesfromListArgstoGlobVSet(trylistArguments, currrentfunctionName, trySubStatementFunction, j);
					}
					// global variables retrieving 
				    getGlobalVariablesfromStatementtoGlobVSet(tryStatementSeq.getChild(j), trySubStatementFunction, currrentfunctionName);
				}
				
			}
// end try statement

// classic statement
			//LabeledStatement DEBUG
			ParseTree State ;
			if (statementSeq.getChild(i).getChild(0).getClass().toString().contains("LabeledStatement")) {
				State = LabeledStatementdebug(statementSeq.getChild(i));
			}
			else 
				State = statementSeq.getChild(i);

			StatFunction statementFunction = isStatementFuntion(State);

			if(statementFunction.Isstatfunction) {

				// Arguments retrieving
				getArgumentsfromfunctionStatementtoFunctionSet(statementFunction, statementSeq, currrentfunctionName, i, listArguments, subFunctionSet);

				// global variables retrieving  in functions		
				getGlobalVariablesfromListArgstoGlobVSet(listArguments, currrentfunctionName, statementFunction, i);
			}

			// global variables retrieving from common statement (!try, !if, !function) 
			getGlobalVariablesfromStatementtoGlobVSet(statementSeq.getChild(i), statementFunction, currrentfunctionName);
			
			// thread 
			// get the threadfunctions and save them in the threadSet map
			if(!statementSeq.getChild(i).getText().contains("try{") && statementSeq.getChild(i).getText().contains("pthread_create"))
				threadSet.put(getFunctionName((CPP14Parser.StatementContext) statementSeq.getChild(i)), new AADLThread());
		}
		if (!currrentfunctionName.contains("matrix::") && !currrentfunctionName.contains("math::") && !currrentfunctionName.contains("sqrtf")
				&& !currrentfunctionName.contains("std::")) {
		currentFunction.setSubFunctionSet(subFunctionSet);
		// insert entry in the functionSet
		functionSet.put(currrentfunctionName, currentFunction);
		}

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
	private StatFunction isStatementFuntion (ParseTree ctx) {

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
	
	public void addGlobalVariablesToglobalvariablesSet(String gvName, ArrayList<String> gvParameters  ) {
		if (gvName.contains("&this->_")) {
			String str[] = gvName.split("->_",2);
			gvName = str[1];
		}
		else if (gvName.contains("&")) {
			String str[] = gvName.split("&",2);
			gvName = str[1];
		}
		else if (gvName.contains("this->D.")) {
			String str[] = gvName.split("._",2);
			gvName = str[1];
		}
		if (gvName.contains(".D.")) {
			String str[] = gvName.split(".D.",2);
			gvName = str[0];
		}
		
		if (!globalvariablesSet.containsKey(gvName)) {
			globalvariablesSet.put(gvName, gvParameters);
		}
		else {
			int n = 0;
			while(true) {
				n++;
				if (!globalvariablesSet.containsKey(gvName+"/"+n))  {
					gvName=gvName+"/"+n;
					globalvariablesSet.put(gvName, gvParameters);
					break;
				}
			}
		}
	}
	public void addFunctionTofunctionSet(String fname, String cfunctionName, ArrayList<String >arguments, ArrayList<String> subfonctionset ) {
		if (!functionSet.containsKey(fname)) {
			functionSet.put(fname, new AADLFunction(fname, cfunctionName, arguments));
			subfonctionset.add((fname));
		}
		else {
			int n = 0;
			while(true) {
				n++;
				if (!functionSet.containsKey(fname+"/"+n))  {
					fname=fname+"/"+n;
					functionSet.put(fname, new AADLFunction(fname, cfunctionName, arguments));
					subfonctionset.add((fname));
					break;
				}
			}
		}
	}
	public void getGlobalVariablesfromStatementtoGlobVSet(ParseTree ctx, StatFunction stf, String currentfonctionName ) {

		if (!stf.Isstatfunction && !ctx.getText().contains("try{") && !ctx.getText().contains("if")&&!ctx.getText().contains("case")) {
			if (ctx.getChild(0).getClass().toString().contains("LabeledStatement") && !ctx.getText().contains("return")
					&& !ctx.getText().contains("goto") && GlobalVariablesEnum.testenumb(ctx.getText())) {
//				System.out.println(ctx.getText());
				//multiple LabeledStatement debug
				ParseTree labstatement = LabeledStatementdebug(ctx);
//				System.out.println(labstatement.getText());
				if (GlobalVariablesEnum.testenumb(labstatement.getText())) {
					String globalvariableName = new String();
					ArrayList<String> globalvariableParameters = new ArrayList<String>();
					globalvariableParameters.add(currentfonctionName);					
					if (GlobalVariablesEnum.testenumb(getxChild0(labstatement,8).getText())) {
						globalvariableName = getxChild0(labstatement,8).getText();
						globalvariableParameters.add("write");
					}
					else if (GlobalVariablesEnum.testenumb(getxChild0(labstatement,5).getChild(1).getText())) {
						globalvariableName = getxChild0(labstatement,5).getChild(1).getChild(0).getChild(1).getText();
						globalvariableParameters.add("read");
					}
					addGlobalVariablesToglobalvariablesSet(globalvariableName, globalvariableParameters);
				}
			}
			else {
				if (GlobalVariablesEnum.testenumb(ctx.getText())) {
					String globalvariableName = new String();
					ArrayList<String> globalvariableParameters = new ArrayList<String>();
					globalvariableParameters.add(currentfonctionName);					
					if (GlobalVariablesEnum.testenumb(getxChild0(ctx,8).getText())) {
						globalvariableName = getxChild0(ctx,8).getText();
						globalvariableParameters.add("write");
					}
					else if (GlobalVariablesEnum.testenumb(getxChild0(ctx,5).getChild(1).getText())) {
						globalvariableName = getxChild0(ctx,5).getChild(1).getChild(0).getChild(1).getText();
						globalvariableParameters.add("read");
					}
					addGlobalVariablesToglobalvariablesSet(globalvariableName, globalvariableParameters);
				}
			}
		}
	}
	public void getGlobalVariablesfromListArgstoGlobVSet(HashMap<Integer, ArrayList<String>> listArgs,String currentFunctionName, StatFunction stf ,Integer j) {
		if (GlobalVariablesEnum.testenumb(listArgs.toString())) {
			for (int m = 0; m < listArgs.get(j).size(); m++) {
				if (GlobalVariablesEnum.testenumb(listArgs.get(j).get(m))) {
					String globalvariableName = new String ();
					ArrayList<String> globalvariableParameters = new ArrayList<String>();
					globalvariableName = listArgs.get(j).get(m);
					globalvariableParameters.add(currentFunctionName);
//					System.out.println(globalvariableParameters + globalvariableName);
					if (stf.FunctionType == 4) 
						globalvariableParameters.add("read");
					else 
						globalvariableParameters.add("write");
					addGlobalVariablesToglobalvariablesSet(globalvariableName, globalvariableParameters);
				}
			}
		}
	}
	public void getArgumentsfromfunctionStatementtoFunctionSet (StatFunction stf, ParseTree ctx, 
			String currentFunctionName, Integer i, HashMap<Integer, ArrayList<String>> listArgs,ArrayList<String> subfonctionset) {
		
		String subFunctionName = new String();
		
		ParseTree State ;
		if (ctx.getChild(i).getChild(0).getClass().toString().contains("LabeledStatement")) {
			State = LabeledStatementdebug(ctx.getChild(i));
		}
		else State = ctx.getChild(i);
		
		listArgs.put(i, new ArrayList<String>());
		if (stf.FunctionType == 1) {
			subFunctionName = getxChild0(State, 9).getText();	
		//case f(x,y,...)
			if (getxChild0(State,8).getChild(1).getChildCount() == 3) {

				for (int k = 0; k < getxChild0(State,8).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
					String str = getxChild0(State,8).getChild(1).getChild(1).getChild(0).getChild(k).getText();
					if (!str.equals(",") && !str.contains("()")&& !str.chars().allMatch( Character::isDigit )) {
						listArgs.get(i).add(str);
					}
				}
				
			}
			else listArgs.get(i).add("");// case f()
		}

		if(stf.FunctionType == 2)	{				
		// case f(&)
			subFunctionName = getxChild0(State, 9).getText();
			if (getxChild0(getxChild0( State,3).getChild(1),4).getChild(1).getChildCount() ==2) {
		//case (&x)
				listArgs.get(i).add(getxChild0(getxChild0(State,3).getChild(1),4).getChild(1).getText());
			}
		// case f(x)
			else //					System.out.println("statement n "+ i+" 1 arg");
				listArgs.get(i).add(getxChild0(getxChild0(State,3).getChild(1),4).getChild(1).getText());
		}
		//case function(&abc, x, ...)
		if (stf.FunctionType == 3) { 
			subFunctionName = getxChild0(State, 9).getText();
			for (int j = 0; j < getxChild0(State,5).getChild(1).getChild(1).getChild(0).getChildCount(); j++) {
				String str = getxChild0(State,5).getChild(1).getChild(1).getChild(0).getChild(j).getText();
				if (!str.equals(",") && !str.contains("()")&& !str.chars().allMatch( Character::isDigit )) 
					listArgs.get(i).add(str);
			}
		}
		// case _1 = function(...)
		if (stf.FunctionType==4){
			subFunctionName = getxChild0( getxChild0(State, 5).getChild(1).getChild(0).getChild(1), 17).getText();

			for (int k = 0; k < getxChild0( getxChild0(State, 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount(); k++) {
				String str = getxChild0( getxChild0(State, 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText();
				if (!str.equals(",") && !str.contains("()") && !str.chars().allMatch( Character::isDigit )) 
					listArgs.get(i).add(str);
			}
		}
		else {
			subFunctionName = getxChild0(State, 9).getText();
			if (!subFunctionName.contains("matrix::") && !subFunctionName.contains("math::") && !subFunctionName.contains("sqrtf")
					&& !subFunctionName.contains("std::")) {
				addFunctionTofunctionSet(subFunctionName, currentFunctionName, listArgs.get(i), subfonctionset);
			}
		}
	}
	public ParseTree LabeledStatementdebug(ParseTree ctx) {
		while (ctx.getChild(0).getClass().toString().contains("LabeledStatement") && !(ctx.getChild(0).getChildCount()==2)) {
			ctx = ctx.getChild(0).getChild(2);
		}
		return ctx;
	}
}

