package helpers;

import org.antlr.v4.runtime.tree.ParseTree;

public class treeHelpers {
	
	public ParseTree getxChild0(ParseTree ctx, int x) {
		for (int i = 0; i < x; i++) {
			ctx = ctx.getChild(0);
		}
	return ctx;
	}
}
