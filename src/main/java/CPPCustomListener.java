import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;

import businessStructure.AADLFunction;
import businessStructure.AADLThread;

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
		
//		System.out.println(" function def : " + ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText());
		
		String currrentfunctionName = ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText();
		AADLFunction currentFunction = new AADLFunction(currrentfunctionName);
		ArrayList<String> subFunctionSet = new ArrayList<>();

		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);

		for(int i=0; i < statementSeq.getChildCount(); i++) {
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
					boolean trySubStatementFunction = isStatementFuntion((CPP14Parser.StatementContext) tryStatementSeq.getChild(j));
					if(trySubStatementFunction) {
						String trySubFunctionName = statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChild(j).getChild(0).getChild(0).getChild(0)
								.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
//						System.out.println("debug = " + statementSeq.getChild(i).getChild(0).getChild(1).getClass());
//						String trySubFunctionName = statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChild(j).getChild(0).getChild(0).getChild(0)
//								.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
						subFunctionSet.add(trySubFunctionName);
					}
//					System.err.println(" try sub statement : " + statementSeq.getChild(i).getChild(0).getChild(1).getChild(1).getChild(j).getText());	
				}
			}
			
			boolean statementFunction = isStatementFuntion((CPP14Parser.StatementContext) statementSeq.getChild(i));
//			System.out.println("isStatementFuntion "+statementFuntion);
			if(statementFunction) {
				String subFunctionName = statementSeq.getChild(i).getChild(0).getChild(0).getChild(0)
						.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
				subFunctionSet.add(subFunctionName);
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
			if(grammarElement instanceof CPP14Parser.TrailingTypeSpecifierContext &&  grammarElement.getChild(0).getChild(0).getChild(0) instanceof CPP14Parser.ClassNameContext) {
				flag = true;
				break;
			}
			
//			if(grammarElement instanceof CPP14Parser.InitDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.InitializerContext) {
//				flag = true;
//				break;
//			}
			
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
