import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;


import businessStructure.DECL_AADLFunction;
import businessStructure.CALLED_AADLFunction;
import businessStructure.AADLThread;
import helpers.GlobalVariablesEnum;
import helpers.treeHelpers;



public class CPPFlowListener extends CPP14ParserBaseListener {
	
	private HashMap<String, Integer> fileDecompositionMap;

	public CPPFlowListener(HashMap<String, Integer> fileDecompositionMap) {
		super();
		this.fileDecompositionMap = fileDecompositionMap;
	}
	
	public HashMap<String, ArrayList<String>> ifstructureSet = new HashMap<String, ArrayList<String>>() ;
	treeHelpers tH = new treeHelpers();
	String currentLabel = new String();
	
	@Override
	public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {
		String currrentfunctionName = tH.getxChild0(ctx.getChild(0).getChild(1), 3).getText();
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
		System.out.println("bouuu");
		for(int i=0; i < statementSeq.getChildCount() ; i++) {

			if(statementSeq.getChild(i).getText().contains("try{")) {
				// all try statements managed at any level 
				RetrieveIfStatement(statementSeq.getChild(i), currrentfunctionName);
				tryStatementdebug(statementSeq.getChild(i), i, currrentfunctionName);
			}
		}
	}
	
	public void RetrieveIfStatement (ParseTree ctx, String currentFunctionName) {
		if (ctx.getText().contains("if(") && !ctx.getText().contains("try{")) {
			System.out.println("bouuuuu" + ctx.getText());	
			
			ArrayList<String> ifstruct = new ArrayList<>();
			String ifElse = new String();
			String ifThen = new String();
			String ifCondition = ctx.getChild(0).getChild(2).getText();
			if (!(ctx.getChild(0).getChild(4).getChild(0).getChild(1)==null))
			ifThen = ctx.getChild(0).getChild(4).getChild(0).getChild(1).getText();
			
			if (ctx.getText().contains("else")) {
			ifElse = ctx.getChild(0).getChild(6).getChild(0).getChild(1).getText();
			}
			
			ifstruct.add(ifCondition);
			ifstruct.add(ifThen);
			ifstruct.add(ifElse);
			
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
//			System.out.println(" if (" +ifCondition + ")   then" + ifThen + " else goto " +ifElse);
		}
	}
	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {
		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);
	}
	public void tryStatementdebug (ParseTree ctx, Integer iterator, String currrentfunctionName ) {
		ParseTree tryStatementSeq;
		
		
		//check if the statement is labeled and get the statement through labeled 
		if (ctx.getChild(0).getClass().toString().contains("LabeledStatement")) {
			tryStatementSeq = LabeledStatementdebug(ctx).getChild(0).getChild(1).getChild(1);
			// this variable is create in order to identify an entire try statement labeled
			//TODO manage try statement label
			String tryLabel = tH.getxChild0(ctx, 2).getText();
			System.out.println("trylabel" + tryLabel);
		}	
		else tryStatementSeq = ctx.getChild(0).getChild(1).getChild(1);
			
		// iterate over each statement
		for (int j = 0; j < tryStatementSeq.getChildCount(); j++) {
			String Label = new String("");
			// check if the statement contains try if true call try statement debug
			if (tryStatementSeq.getChild(j).getText().contains("try{")) {
				iterator = j;
				tryStatementdebug(tryStatementSeq.getChild(iterator), iterator,currrentfunctionName);
			}
			else {
				ParseTree tryState;
				//check if the statement is labeled and get the statement through labeled
				if (tryStatementSeq.getChild(j).getChild(0).getClass().toString().contains("LabeledStatement")) {
					tryState = LabeledStatementdebug(tryStatementSeq.getChild(j));
					RetrieveIfStatement(tryState, currrentfunctionName);
					Label = tH.getxChild0(tryStatementSeq.getChild(j), 2).getText();
					currentLabel = Label;
				}
				else tryState = tryStatementSeq.getChild(j);
				
				//check if the statement contains a "if structure" 
				RetrieveIfStatement(tryState, currrentfunctionName);
				
				//if the statement is a function retrieve data 
				//retrieve global variable from statement				
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