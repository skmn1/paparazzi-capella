import org.antlr.v4.runtime.misc.NotNull;

import businessStructure.AADLFunction;
import businessStructure.AADLThread;

public class GrammarHelper {
	
	/**
	 * 
	 * @param ctx
	 */
	
	public static void generateFunctionIDFromPreambul (Object ctx) {
		
	}
	
	/**
	 * 	
	 * @param ctx
	 */
	
	public static void getSubFunctionSet () {
		
	}
	
	
	/**
	 * 
	 * @param ctx
	 */
	
	public static AADLFunction getFunction (@NotNull CPP14Parser.DeclarationContext ctx) {
		AADLFunction function = new AADLFunction();
		
		function.setFunctionName(ctx.getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getText());
		
		
		return function;
	}

	
	/**
	 * 
	 * @param ctx
	 */
	
	public static AADLThread getThread (CPP14Parser.StatementContext ctx) {
		AADLThread thread = new AADLThread();
		
		thread.setThreadName(null);
		thread.setThreadfunctionName(null);
		
		return thread;
	}
	
	/**
	 * 
	 * @param ctx
	 */
	
	public static void getThreadFunction (Object ctx) {
		
	}
	
	/**
	 * 
	 * @param ctx
	 */
	
	public static void getGobalVariable (Object ctx) {
		
	}
}
