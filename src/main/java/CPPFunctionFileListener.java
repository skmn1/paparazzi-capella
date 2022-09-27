import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;
//
import businessStructure.AADLFunction;
//import businessStructure.AADLThread;
import businessStructure.DECL_AADLFunction;
import helpers.treeHelpers;


public class CPPFunctionFileListener extends CPP14ParserBaseListener {
	treeHelpers tH = new treeHelpers();
	public ArrayList<String> functionList = new ArrayList<String>();

	@Override
	public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {
		//		System.out.println(" function def : " + ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText());
		String currrentfunctionName = tH.getxChild0(ctx.getChild(0).getChild(1), 3).getText();
		functionList.add(currrentfunctionName);;
		
		CPP14Parser.StatementSeqContext statementSeq = getStatmentSeq(ctx);
		
	}

	private CPP14Parser.StatementSeqContext getStatmentSeq(CPP14Parser.DeclarationContext ctx) {

		//		System.err.println("class....... " + ctx.getChild(0).getChild(2).getChild(0).getChild(1).getClass());
		return (CPP14Parser.StatementSeqContext) ctx.getChild(0).getChild(2).getChild(0).getChild(1);

	}

	private String getFunctionName (CPP14Parser.StatementContext ctx) {
		return ctx.getText().split(",")[2];

	}
}
