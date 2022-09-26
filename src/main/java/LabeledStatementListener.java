import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;

import businessStructure.DECL_AADLFunction;
import helpers.treeHelpers; 

public class LabeledStatementListener extends CPP14ParserBaseListener { 
	
	public static HashMap<String,DECL_AADLFunction> declfunctionSet = new HashMap<String,DECL_AADLFunction>();
	public HashMap<String, String> LabeledStatementDef = new HashMap<String, String>();
	
	@Override
	public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {
		treeHelpers th = new treeHelpers();
		System.out.println(" function def : " + ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText());
		
		String currrentfunctionName = th.getxChild0(ctx.getChild(0).getChild(1), 3).getText();
		DECL_AADLFunction currentFunction = new DECL_AADLFunction(currrentfunctionName);
		
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
		for(int i=0; i < statementSeq.getChildCount() ; i++) {
			if (statementSeq.getChild(i).getClass().toString().contains("LabeledStatement") && !statementSeq.getChild(i).getText().contains("try{")) {
			
			}
			else if(statementSeq.getChild(i).getText().contains("try{")) {
				// all try statements managed at any level 
				tryStatementdebug(statementSeq.getChild(i), i, currrentfunctionName);
			}
			else {

			}
		}
	}

	
	
	
	
	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {
		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);
	}
	
	public void tryStatementdebug (ParseTree ctx, Integer iterator, String currrentfunctionName) {
		ParseTree tryStatementSeq;
		if (ctx.getChild(0).getClass().toString().contains("LabeledStatement"))
			tryStatementSeq = LabeledStatementdebug(ctx).getChild(0).getChild(1).getChild(1);	
		else 
			tryStatementSeq = ctx.getChild(0).getChild(1).getChild(1);
		
		for (int j = 0; j < tryStatementSeq.getChildCount(); j++) {
			if (tryStatementSeq.getChild(j).getText().contains("try{")) {
				iterator = j;
				tryStatementdebug(tryStatementSeq.getChild(iterator), iterator,currrentfunctionName);
			}
			
			else {
//				RetrieveIfStatement(tryStatementSeq, currrentfunctionName);
				ParseTree tryState;
				if (tryStatementSeq.getChild(j).getChild(0).getClass().toString().contains("LabeledStatement")) {
					tryState = LabeledStatementdebug(tryStatementSeq.getChild(j));
				}
				else tryState = tryStatementSeq.getChild(j);
				
				
//				RetrieveIfStatement(tryState, currrentfunctionName);
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
