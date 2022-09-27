package helpers;

import org.antlr.v4.runtime.tree.ParseTree;

public class treeHelpers {
	
	
	/**
	 * @ return as much .getchild(0) as needed 
	 * @param ctx
	 * @param x 
	 * @return
	 */
	public ParseTree getxChild0(ParseTree ctx, int x) {
		for (int i = 0; i < x; i++) {
			ctx = ctx.getChild(0);
		}
	return ctx;
	}
	
	
	/**
	 * @ turn "functionName/12" => "functionName"
	 * @param Functionname
	 * @return
	 */
	public String debugFunctionName(String Functionname) {
		// function/1 => function <=> function use to remove /number from called function 
			if (Functionname.contains("/")) {
				Functionname = Functionname.split("/",2)[0];
			}
			return Functionname;
		}
	
}
