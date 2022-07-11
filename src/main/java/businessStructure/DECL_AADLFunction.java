package businessStructure;

import java.util.ArrayList;


// in order to differentiate a function called by another of a function declaration we must create 2 subclass 
public class DECL_AADLFunction extends AADLFunction {
	
	private ArrayList<String> subFunctionSet;

	public DECL_AADLFunction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DECL_AADLFunction(String functionName) {
		super(functionName);
	}
	
	public void setSubFunctionSet(ArrayList<String> subFunctionSet) {
		this.subFunctionSet = subFunctionSet;
	}
	
	
	public ArrayList<String> getSubFunctionSet() {
		return subFunctionSet;
	}

	@Override
	public String toString() {
		return "AADLFunction [functionName=" + getFunctionName() + ", containingFile=" + getContainingFile() 
				 + ", subFunctionSet=" + subFunctionSet ;
	}
	
}
