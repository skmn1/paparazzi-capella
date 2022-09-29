import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import businessStructure.DECL_AADLFunction;
import businessStructure.GlobalVariable;
import businessStructure.GotoStructure;
import businessStructure.IfStructure;
import businessStructure.CALLED_AADLFunction;
import businessStructure.AADLThread;
import helpers.GlobalVariablesEnum;
import helpers.treeHelpers;



@SuppressWarnings("unused")
public class CPPCustomListener extends CPP14ParserBaseListener {

	public HashMap<String, AADLThread>  threadSet = new HashMap<String,AADLThread>();
	public HashMap<String,CALLED_AADLFunction> callfunctionSet = new HashMap<String,CALLED_AADLFunction>();
	public HashMap<String,DECL_AADLFunction> declfunctionSet = new HashMap<String,DECL_AADLFunction>();
	
	public HashMap<String,String> functionFileMap = new HashMap<String,String>();
	
	public HashMap<String,Integer> fileDecompositionMap = new HashMap<String,Integer>();
	public HashMap<String, GlobalVariable> globalvariablesSet = new HashMap<String, GlobalVariable>() ;
	public HashMap<String, IfStructure> ifstructureSet = new HashMap<String, IfStructure>() ;
	public HashMap<String, GotoStructure> gotostructureSet = new HashMap<String, GotoStructure>() ;
	
	public Integer Id = 0;
	public String LastFunctionCalled;
	treeHelpers tH= new treeHelpers() ;
	
	public CPPCustomListener(HashMap<String, Integer> fileDecompositionMap) {
		super();
		this.fileDecompositionMap = fileDecompositionMap;
	}
    String currentLabel = new String();
    
	@Override
	public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {

		currentLabel = "";
		String currrentfunctionName = tH.getxChild0(ctx.getChild(0).getChild(1), 3).getText();
		
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
		ArrayList<String> subFunctionSet = new ArrayList<String>();
		DECL_AADLFunction currentFunction = new DECL_AADLFunction(currrentfunctionName, subFunctionSet , null);
		
		
		
		HashMap<Integer, ArrayList<String>> listArguments = new HashMap<Integer, ArrayList<String>>();
		currentFunction.setWeight(statementSeq.getChildCount() - 1);
		declfunctionSet.put(currrentfunctionName, currentFunction);
		Boolean LastStatement = false;
		for(int i=0; i < statementSeq.getChildCount() ; i++) {
			
			if ((i+1)== statementSeq.getChildCount())
				 LastStatement = true;
			
//			System.out.println("LastStatement : "+ LastStatement);
			if(statementSeq.getChild(i).getText().contains("try{")) {
				// all try statements managed at any level 
				tryStatementdebug(statementSeq.getChild(i), i, currrentfunctionName, subFunctionSet);
			}
			String Label = new String("");

			//LabeledStatement DEBUG
			ParseTree State ;
			RetrieveJumpStatement(statementSeq.getChild(i), currrentfunctionName,LastStatement );
			if (statementSeq.getChild(i).getChild(0).getClass().toString().contains("LabeledStatement")) {
				State = LabeledStatementdebug(statementSeq.getChild(i));
				Label = tH.getxChild0(statementSeq.getChild(i), 2).getText();
				currentLabel = Label;
			}
			else {
				State = statementSeq.getChild(i); 
				}
			
			if (statementSeq.getChild(i).getText().contains("if(")) {
				RetrieveIfStatement(State, currrentfunctionName);
			}
			
			// We check if the statement is a function
			StatFunction statefunction = new StatFunction();

			StatFunction statementFunction = statefunction.isStatementFuntion(State);
			if(statementFunction.getIsstatfunction()) {
				// Arguments and called functions retrieving 
				getArgumentsfromfunctionStatementtoFunctionSet(statementFunction, statementSeq, currrentfunctionName, i, listArguments, subFunctionSet, Label);

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
				&& !currrentfunctionName.contains("std::") && !currrentfunctionName.contains("perror") && !currrentfunctionName.contains("fprintf")) {
		declfunctionSet.get(currrentfunctionName).setSubFunctionSet(subFunctionSet);
		}

		// join the thread with its subfunctions
		if(threadSet.containsKey(currrentfunctionName))
			threadSet.get(currrentfunctionName).getThreadFunctionSet().put(currrentfunctionName, currentFunction);
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
	
	
	@Override
	public void exitDeclarator(CPP14Parser.DeclaratorContext ctx) {
		super.exitDeclarator(ctx);
	}

	@Override
	public void exitStatement(CPP14Parser.StatementContext ctx) {

		String statementContent = ctx.getText();
		if (statementContent.contains("pthread_create") && !statementContent.contains("try{")) {

			String threadFunctionName = statementContent.split(",")[2];

			//			System.out.println("Thread \n thread Function name +++++++> " + threadFunctionName);		

			threadSet.put(threadFunctionName, GrammarHelper.getThread(ctx));

			//			System.out.println(this);
		}
	}

//	@Override
//	public String toString() {
//		return "CPPCustomListener [threadSet=" + threadSet + ",\n\t\t\t\t\t functionSet=" + functionSet + "]";
//	}
	

	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {

		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);

	}

	private String getFunctionName (CPP14Parser.StatementContext ctx) {
		return ctx.getText().split(",")[2];

	}
	
	public void addGlobalVariablesToglobalvariablesSet(String gvName, GlobalVariable globalVariable) {
		// global variable extraction 
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
		if(gvName.contains("BIT_FIELD_REF<")) {
			String str[] = gvName.split("BIT_FIELD_REF<",2);
			gvName = str[1];
		}
		
		if (!globalvariablesSet.containsKey(gvName)) {
			globalvariablesSet.put(gvName, globalVariable);
		}
		else {
			int n = 0;
			while(true) {
				n++;
				if (!globalvariablesSet.containsKey(gvName+"/"+n))  {
					gvName=gvName+"/"+n;
					globalvariablesSet.put(gvName, globalVariable);
					break;
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @param function name
	 * @param currentfunctionName (parent)
	 * @param arguments
	 * @param subfonctionset
	 * @param Label
	 * @ if the function already exist we add a "/" + a number 
	 * @ 
	 */
	public void addFunctionTofunctionSet(String fname, String cfunctionName, ArrayList<String > arguments, ArrayList<String> subfonctionset, String Label) {
		
		if (fname.charAt(0) == "_".charAt(0) ) {
			if(fname.split("_",2)[1].chars().allMatch(Character::isDigit)) {
				fname="";
			}
		}
		if (!fname.isEmpty()) {
			if (!callfunctionSet.containsKey(fname)) {
				callfunctionSet.put(fname, new CALLED_AADLFunction(fname, cfunctionName, arguments, currentLabel));
				callfunctionSet.get(fname).setId(Id);
				Id+=1;
				subfonctionset.add((fname));
				LastFunctionCalled = fname;

			}
			else {
				String fnamebis = fname+"/";
				int n = 0;
				while(true) {
					n++;
					if (!callfunctionSet.containsKey(fnamebis+n))  {
						fnamebis = fnamebis + n;
						callfunctionSet.put(fnamebis, new CALLED_AADLFunction(fname, cfunctionName, arguments, currentLabel));
						LastFunctionCalled = fnamebis;
						callfunctionSet.get(fnamebis).setId(Id);
						Id+=1;
						subfonctionset.add((fnamebis));
						if (!Label.equals("")) {
							LastFunctionCalled = fnamebis;
						}
						break;
					}
				}
			}
			
		}
	}
	
	
	
	/**
	 * 
	 * @param ctx
	 * @param stf
	 * @param currentfonctionName
	 * This function is used to find global Variable in none function statement
	 */
	public void getGlobalVariablesfromStatementtoGlobVSet(ParseTree ctx, StatFunction stf, String currentfonctionName ) {

		if (!stf.getIsstatfunction() && !ctx.getText().contains("try{") && !ctx.getText().contains("if")&&!ctx.getText().contains("case")) {
			if (ctx.getChild(0).getClass().toString().contains("LabeledStatement") && !ctx.getText().contains("return")
					&& !ctx.getText().contains("goto") && GlobalVariablesEnum.testenumb(ctx.getText())) {
				//multiple LabeledStatement debug
				ParseTree labstatement = LabeledStatementdebug(ctx);
//				System.out.println(labstatement.getText());
				if (GlobalVariablesEnum.testenumb(labstatement.getText())) {
					String globalvariableName = new String();
					String Type = new String();
					ArrayList<String> globalvariableParameters = new ArrayList<String>();
							
					globalvariableParameters.add(currentfonctionName);					
					if (GlobalVariablesEnum.testenumb(tH.getxChild0(labstatement,8).getText())) {
						globalvariableName = tH.getxChild0(labstatement,8).getText();
						Type = "write";
						globalvariableParameters.add("write");
					}
					
					else if (GlobalVariablesEnum.testenumb(tH.getxChild0(labstatement,5).getChild(1).getText())) {
						globalvariableName = tH.getxChild0(labstatement,5).getChild(1).getChild(0).getChild(1).getText();
						globalvariableParameters.add("read");
						 Type = "read";
					}
					
					GlobalVariable globalVariable = new GlobalVariable(globalvariableName, Type, currentfonctionName);
					addGlobalVariablesToglobalvariablesSet(globalvariableName, globalVariable);
				}
			}
			else {
				if (GlobalVariablesEnum.testenumb(ctx.getText())) {
					String globalvariableName = new String();
					String Type = new String();
					ArrayList<String> globalvariableParameters = new ArrayList<String>();
					
					if (GlobalVariablesEnum.testenumb(tH.getxChild0(ctx,8).getText())) {
						globalvariableParameters.add(currentfonctionName);
						
						
						globalvariableName = tH.getxChild0(ctx,8).getText();
						globalvariableParameters.add("write");
						Type = "write";
					}
					else if (GlobalVariablesEnum.testenumb(tH.getxChild0(ctx,5).getChild(1).getText())) {
						globalvariableParameters.add(currentfonctionName);
						globalvariableName = tH.getxChild0(ctx,5).getChild(1).getChild(0).getChild(1).getText();
						globalvariableParameters.add("read");
						Type = "read";
					}
					GlobalVariable globalvariable = new GlobalVariable(globalvariableName, Type, currentfonctionName);
					addGlobalVariablesToglobalvariablesSet(globalvariableName, globalvariable);					
				}
			}
		}
	}
	
	/**
	 * 
	 * @param listArgs
	 * @param currentFunctionName
	 * @param stf
	 * @param j
	 * @ this function retrieve global variables in a function statement
	 * 
	 */
	public void getGlobalVariablesfromListArgstoGlobVSet(HashMap<Integer, ArrayList<String>> listArgs,String currentFunctionName, StatFunction stf ,Integer j) {
		if (GlobalVariablesEnum.testenumb(listArgs.toString())) {
			for (int m = 0; m < listArgs.get(j).size(); m++) {
				if (GlobalVariablesEnum.testenumb(listArgs.get(j).get(m))) {
					String globalvariableName = new String ();
					String Type = new String();
					ArrayList<String> globalvariableParameters = new ArrayList<String>();
					globalvariableName = listArgs.get(j).get(m);
					globalvariableName.replace("<", "-"); // character bug xml
					globalvariableParameters.add(currentFunctionName);

					if (stf.getFunctionType() == 4) {
						globalvariableParameters.add("read");
						Type ="read";
					}

					else {
						Type = "write";
						globalvariableParameters.add("write");
					}
					GlobalVariable globalvariable = new GlobalVariable(globalvariableName, Type, currentFunctionName);
					addGlobalVariablesToglobalvariablesSet(globalvariableName, globalvariable);
				}
			}
		}
	}
	
	
	// add called function to calledfunctionset and retrieve arguments
	/**
	 * @implSpec this function extract function name and its parameters from a statement then put them into calledfunctionSet
	 * @param stf refer to StatFunction class
	 * @param ctx Must be a statement context
	 * @param currentFunctionName
	 * @param i
	 * @param listArgs 
	 * @param subfonctionset
	 * @param Label
	 * 
	 */
	public void getArgumentsfromfunctionStatementtoFunctionSet (StatFunction stf, ParseTree ctx, String currentFunctionName, 
			Integer i, HashMap<Integer, ArrayList<String>> listArgs, ArrayList<String> subfonctionset, String Label) {
		String subFunctionName = new String();
		ParseTree State ;
		
		// check if the statement is labeled
		if (ctx.getChild(i).getChild(0).getClass().toString().contains("LabeledStatement")) {
			State = LabeledStatementdebug(ctx.getChild(i));
		}
		else State = ctx.getChild(i);
		
		// for each case we idientify the structure, we retrieve the function name, we check if arguments aren't numbers
		listArgs.put(i, new ArrayList<String>());
		switch (stf.getFunctionType()) {
		case (1) : {
			subFunctionName = tH.getxChild0(State, 9).getText();	
			//case f(x,y,...)
			if (tH.getxChild0(State,8).getChild(1).getChildCount() == 3) {
				for (int k = 0; k < tH.getxChild0(State,8).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
					String str = tH.getxChild0(State,8).getChild(1).getChild(1).getChild(0).getChild(k).getText();
					if (!str.equals(",") && !str.contains("()")&& !str.chars().allMatch( Character::isDigit )) {
						listArgs.get(i).add(str);
					}
				}
			}
			else listArgs.get(i).add("");// case f()
			break;
		}
		
		case (11) : {
			subFunctionName = tH.getxChild0(State, 4).getText() + tH.getxChild0(tH.getxChild0(State, 3).getChild(1),5).getText();
			break;
		}
		
		case (12) : {
			subFunctionName = tH.getxChild0(State, 4).getText() + tH.getxChild0(tH.getxChild0(State, 3).getChild(1),5).getText();
			for (int k = 0; k < tH.getxChild0(tH.getxChild0(State, 3).getChild(1),4).getChild(1).getChild(1).getChild(0).getChildCount(); k++) {
				String str = tH.getxChild0(tH.getxChild0(State, 3).getChild(1),4).getChild(1).getChild(1).getChild(0).getChild(k).getText();
				if (!str.equals(",") && !str.contains("()") && !str.chars().allMatch( Character::isDigit )) 
					listArgs.get(i).add(str);
			}
			break;
		}
		
		case (2) :	{				
			subFunctionName = tH.getxChild0(State, 9).getText();
			if (tH.getxChild0(tH.getxChild0( State,3).getChild(1),4).getChild(1).getChildCount() ==2) {
			//case (&x)
				listArgs.get(i).add(tH.getxChild0(tH.getxChild0(State,3).getChild(1),4).getChild(1).getText());
			}
			// case f(x)
			else listArgs.get(i).add(tH.getxChild0(tH.getxChild0(State,3).getChild(1),4).getChild(1).getText());
			break;
		}
		case (22) :	{			
			subFunctionName = tH.getxChild0(State, 5).getText()  ;
			if (tH.getxChild0(tH.getxChild0( State,3).getChild(1),4).getChild(1).getChildCount() ==2) {
			//case (&x)
				listArgs.get(i).add(tH.getxChild0(tH.getxChild0(State,3).getChild(1),4).getChild(1).getText());
			}
			// case f(x)
			else listArgs.get(i).add(tH.getxChild0(tH.getxChild0(State,3).getChild(1),4).getChild(1).getText());
			break;
		}
		
		//case function(&abc, x, ...)
		case (3) : { 
			subFunctionName = tH.getxChild0(State, 9).getText();
			for (int j = 0; j < tH.getxChild0(State,5).getChild(1).getChild(1).getChild(0).getChildCount(); j++) {
				String str = tH.getxChild0(State,5).getChild(1).getChild(1).getChild(0).getChild(j).getText();
				if (!str.equals(",") && !str.contains("()")&& !str.chars().allMatch( Character::isDigit )) 
					listArgs.get(i).add(str);
			}
			break;
		}

		case(33) : {

			subFunctionName = tH.getxChild0(State, 9).getText() + tH.getxChild0(tH.getxChild0(State, 3).getChild(1).getChild(0),2).getText();
			for (int j = 0; j < tH.getxChild0(State, 3).getChild(1).getChild(0).getChild(1).getChild(1).getChild(0).getChildCount(); j++) {
				String str = tH.getxChild0(State, 3).getChild(1).getChild(0).getChild(1).getChild(1).getChild(0).getChild(j).getText();
				if (!str.equals(",") && !str.contains("()")&& !str.chars().allMatch( Character::isDigit ))
					listArgs.get(i).add(str);
			}
			break;
		}

		// case _1 = function(...)
		case (4) : {
			subFunctionName = tH.getxChild0( tH.getxChild0(State, 5).getChild(1).getChild(0).getChild(1), 17).getText();

			for (int k = 0; k < tH.getxChild0( tH.getxChild0(State, 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChildCount(); k++) {
				String str = tH.getxChild0( tH.getxChild0(State, 5).getChild(1).getChild(0).getChild(1), 16).getChild(2).getChild(0).getChild(k).getText();
				if (!str.equals(",") && !str.contains("()") && !str.chars().allMatch( Character::isDigit )) 
					listArgs.get(i).add(str);
			}
			break;
		}
		case (44) : {
			subFunctionName = tH.getxChild0( tH.getxChild0(State, 5).getChild(1).getChild(0).getChild(1), 17).getText();
			break;
		}
		}
		
		// we delete math function that are no relevant and add function to function set
			if (!subFunctionName.contains("matrix::") && !subFunctionName.contains("math::") && !subFunctionName.equals("sqrtf")
					&& !subFunctionName.equals("std::")&& !subFunctionName.equals("perror") && !subFunctionName.equals("printf")
					&&!subFunctionName.equals("sinf")&&!subFunctionName.equals("cosf")&&!subFunctionName.equals("coshf")&&!subFunctionName.equals("sinhf")
				    &&!subFunctionName.equals("asinf")&&!subFunctionName.equals("acosf")&&!subFunctionName.equals("printf")&&!subFunctionName.equals("expf")
				    &&!subFunctionName.equals("atanf")&&!subFunctionName.equals("atanhf")&&!subFunctionName.equals("tanf")&&!subFunctionName.equals("expf")){
				addFunctionTofunctionSet(subFunctionName, currentFunctionName, listArgs.get(i), subfonctionset, Label);
			}
	}
	// permits to go through the labeled statement and get the statement only
	public ParseTree LabeledStatementdebug(ParseTree ctx) { 
		while (ctx.getChild(0).getClass().toString().contains("LabeledStatement") && !(ctx.getChild(0).getChildCount()==2)) {
			ctx = ctx.getChild(0).getChild(2);
		}
		return ctx;
	}
	
	/**
	 * @implSpec manage all try statement even nested ones, information extraction is included in that function
	 * @param ctx
	 * @param iterator
	 * @param currrentfunctionName
	 * @param subFunctionSet
	 */
	// process all "try" statements recursively
	public void tryStatementdebug (ParseTree ctx, Integer iterator, String currrentfunctionName, ArrayList<String>  subFunctionSet ) {

		HashMap<Integer, ArrayList<String>> trylistArguments = new HashMap<Integer, ArrayList<String>>();
		ParseTree tryStatementSeq;
		
		
		//check if the statement is labeled and get the statement through labeled 
		if (ctx.getChild(0).getClass().toString().contains("LabeledStatement")) {
//			System.out.println("/1 : " + ctx.getText() + "\n /2 :" + LabeledStatementdebug(ctx).getText() );
			tryStatementSeq = LabeledStatementdebug(ctx).getChild(0).getChild(1).getChild(1);
			// this variable is create in order to identify an entire try statement labeled
			String tryLabel = tH.getxChild0(ctx, 2).getText();
//			System.out.println("trylabel" + tryLabel);
		}	
		else tryStatementSeq = ctx.getChild(0).getChild(1).getChild(1);
		 
		// add try statement to function weight
		declfunctionSet.get(currrentfunctionName).setWeight(declfunctionSet.get(currrentfunctionName).getWeight() + tryStatementSeq.getChildCount());
		
		// iterate over each statement
		for (int j = 0; j < tryStatementSeq.getChildCount(); j++) {
			String Label = new String("");
			// check if the statement contains try if true call try statement debug
			if (tryStatementSeq.getChild(j).getText().contains("try{")) {
				iterator = j;
				tryStatementdebug(tryStatementSeq.getChild(iterator), iterator,currrentfunctionName, subFunctionSet);
			}
			else {
				ParseTree tryState;
				//check if the statement is labeled and get the statement through labeled
				if (tryStatementSeq.getChild(j).getChild(0).getClass().toString().contains("LabeledStatement")) {
					tryState = LabeledStatementdebug(tryStatementSeq.getChild(j));
					Label = tH.getxChild0(tryStatementSeq.getChild(j), 2).getText();
					currentLabel = Label;
				}
				else tryState = tryStatementSeq.getChild(j);
				
				//check if the statement contains a "if structure" 
				RetrieveIfStatement(tryState, currrentfunctionName);
				
				// create a local variable to call Statfunction class 
				StatFunction statefunction = new StatFunction();
				StatFunction trySubStatementFunction = statefunction.isStatementFuntion(tryState);
				
				//if the statement is a function retrieve data 
				if(trySubStatementFunction.getIsstatfunction()) {
					getArgumentsfromfunctionStatementtoFunctionSet(trySubStatementFunction, tryStatementSeq, currrentfunctionName, j, 
							trylistArguments, subFunctionSet, Label);
					getGlobalVariablesfromListArgstoGlobVSet(trylistArguments, currrentfunctionName, trySubStatementFunction, j);
				}
				//retrieve global variable from statement
				getGlobalVariablesfromStatementtoGlobVSet(tryStatementSeq.getChild(j), trySubStatementFunction, currrentfunctionName);
				
			}
		}
	}
	
	/**
	 * @ 
	 * @param ctx
	 * @param currentFunctionName
	 */
	// process "if" statement and save them in ifstructure
	public void RetrieveIfStatement (ParseTree ctx, String currentFunctionName) {
		if (ctx.getText().contains("if(") && !ctx.getText().contains("try{")) {

			String ifElse = new String();
			String ifThen = new String();
			String ifCondition = ctx.getChild(0).getChild(2).getText();
			if (!(ctx.getChild(0).getChild(4).getChild(0).getChild(1)==null))
			ifThen = ctx.getChild(0).getChild(4).getChild(0).getChild(1).getText();
			
			if (ctx.getText().contains("else")) {
			ifElse = ctx.getChild(0).getChild(6).getChild(0).getChild(1).getText();
			}
			IfStructure ifstruct = new IfStructure(LastFunctionCalled,ifCondition,ifThen,ifElse);
			if (!ifstructureSet.containsKey(currentFunctionName)) {
			ifstructureSet.put(currentFunctionName, ifstruct);
			}
			else {
				int n = 0;
				while(true) {
					n++;
					if (!ifstructureSet.containsKey(currentFunctionName+"/"+n))  {
						currentFunctionName=currentFunctionName+"/"+n;
						ifstructureSet.put(currentFunctionName, ifstruct);
						break;
					}
				}
			}			
		}
	}
	
	/**
	 * @ the goto statement will be put as : last Label encounter , GotoStructure : (LabelToreach, Last FunctionEncounter, Id) 
	 * @param ctx
	 * @param currentFunctionName
	 * @param LastStatement
	 */
	public void RetrieveJumpStatement (ParseTree ctx, String currentFunctionName, Boolean LastStatement) {
		
		if (!LastStatement) {
			if (ctx.getChild(0).getClass().toString().contains("JumpStatement") && !ctx.getText().contains("if(") && !ctx.getText().contains("try{") ) {
				GotoStructure Goto = new GotoStructure(LastFunctionCalled, LastStatement);
				Goto.setLabel(ctx.getChild(0).getChild(1).getText());
				Goto.setLastFunctionId(callfunctionSet.get(LastFunctionCalled).getId());
				gotostructureSet.put(currentLabel, Goto);
			}
		}
		else {
			if(ctx.getChild(0).getClass().toString().contains("LabeledStatement")) {
				GotoStructure Goto = new GotoStructure(LastFunctionCalled, LastStatement);
				Goto.setLabel(ctx.getChild(0).getChild(1).getText());
				Goto.setLastFunctionId(callfunctionSet.get(LastFunctionCalled).getId());
				gotostructureSet.put(currentLabel, Goto);
			}
		}
	}
	
}