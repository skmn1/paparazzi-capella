import java.util.HashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import businessStructure.AADLFunction;
import businessStructure.AADLThread;

public class CPPCustomListener extends CPP14ParserBaseListener {
	
	
	public HashMap<String, AADLThread>  threadSet = new HashMap<String,AADLThread>();
	
	public HashMap<String,AADLFunction> functionSet = new HashMap<String,AADLFunction>();
	

	@Override
	public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {
		// TODO Auto-generated method stub
//		super.exitDeclaration(ctx);
//		System.out.println("");
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
//		while (true) {
////			if()
//		}
		System.out.println("count " + statementSeq.getChildCount());
		for(int i=0; i < statementSeq.getChildCount(); i++) {
			
			
			boolean statementFuntion = isStatementFuntion((CPP14Parser.StatementContext) statementSeq.getChild(i));
			System.out.println("isStatementFuntion "+statementFuntion);
			if(statementFuntion)
				functionSet.put(statementSeq.getChild(i).getText(), null);
			
			// get the threadfunctions and save them in the threadSet map
			if(!statementSeq.getChild(i).getText().contains("try{") && statementSeq.getChild(i).getText().contains("pthread_create"))
				threadSet.put(getFunctionName((CPP14Parser.StatementContext) statementSeq.getChild(i)), null);
		}
		
	}

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
		
			System.out.println(this);
		}
		
	}

	@Override
	public String toString() {
		return "CPPCustomListener [threadSet=" + threadSet + ",\n\t\t\t\t\t functionSet=" + functionSet + "]";
	}
	
	
	
	private boolean isStatementFuntion (CPP14Parser.StatementContext ctx) {
		boolean flag = false;
//		if(ctx != null) {
//		System.out.println(ctx.getText());
//	    Object grammarElement = ctx.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1);
//	    System.err.println(grammarElement.getClass());
//	      if(grammarElement instanceof CPP14Parser.ParametersAndQualifiersContext) 
//	        flag = true;
//	      
//		}
		ParseTree grammarElement = ctx;
		while (true) {
			if(grammarElement.getChild(0) == null) 
				break;
			
			grammarElement =  grammarElement.getChild(0);
			if(grammarElement instanceof CPP14Parser.NoPointerDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.ParametersAndQualifiersContext) {
				flag = true;
				break;
			}
			
		}
		return flag;
		
	}
	
	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {
		
//		System.err.println("class....... " + ctx.getChild(0).getChild(2).getChild(0).getChild(1).getClass());
		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);
		
	}
	
	
	private String getFunctionName (CPP14Parser.StatementContext ctx) {
		return ctx.getText().split(",")[2];
			
	}

}
