import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;
//
import businessStructure.AADLFunction;
//import businessStructure.AADLThread;
import businessStructure.DECL_AADLFunction;

public class CPPFunctionFileListener extends CPP14ParserBaseListener {

	public ArrayList<String> functionList = new ArrayList<String>();

	@Override
	public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {
		//		System.out.println(" function def : " + ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText());
		String currrentfunctionName = getxChild0(ctx.getChild(0).getChild(1), 3).getText();
		functionList.add(currrentfunctionName);;
		
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
		
	}

	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {

		//		System.err.println("class....... " + ctx.getChild(0).getChild(2).getChild(0).getChild(1).getClass());
		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);

	}

	private boolean isStatementFuntion (CPP14Parser.StatementContext ctx) {
		boolean flag = false;
		//	if(ctx != null) {
		//	System.out.println(ctx.getText());
		//    Object grammarElement = ctx.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1);
		//    System.err.println(grammarElement.getClass());
		//      if(grammarElement instanceof CPP14Parser.ParametersAndQualifiersContext) 
		//        flag = true;
		//      
		//	}
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

			if(grammarElement instanceof CPP14Parser.InitDeclaratorContext &&  grammarElement.getChild(1) instanceof CPP14Parser.InitializerContext) {
				flag = true;
				break;
			}

		}
		return flag;

	}

	public ParseTree getxChild0(ParseTree ctx, int x) {
		for (int i = 0; i < x; i++) {
			ctx = ctx.getChild(0);
		}
	return ctx;
	}

	private String getFunctionName (CPP14Parser.StatementContext ctx) {
		return ctx.getText().split(",")[2];

	}
}
