package businessStructure;

import java.util.ArrayList;

//in order to differentiate a function called by another of a function declaration we must create 2 subclass of AADL FUNCTION

public class AADLFunction {
	private String functionName;
	private String containingFile;
	
	public AADLFunction() {
		super();
	}

	public AADLFunction(String functionName, String containingFile, ArrayList<String> parameters,
			ArrayList<String> subFunctionSet, String parentName) {
		super();
		this.functionName = functionName;
		this.containingFile = containingFile;
	}
	
	public AADLFunction(String functionName,String parent ,ArrayList<String> parameters) {
		this.functionName = functionName;
	}
	

	public AADLFunction(String functionName) {
		super();
		this.functionName = functionName;
	}

	public AADLFunction(String functionName, String containingFile) {
		super();
		this.functionName = functionName;
		this.containingFile = containingFile;
	}

	public String getFunctionName() {
		return functionName;
	}
	
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getContainingFile() {
		return containingFile;
	}

	public void setContainingFile(String containingFile) {
		this.containingFile = containingFile;
	}
	


	

	


//	@Override
//	public String toString() {
//		return "AADLFunction [functionName=" + functionName + ", containingFile=" + containingFile + ", parameters="
//				+ parameters + ", subFunctionSet=" + subFunctionSet + ", parentName=" + parentName + "]";
//	}
	
	

}
