package businessStructure;

import java.util.ArrayList;

public class AADLFunction {
	private String functionName;
	private String containingFile;
	private ArrayList<String> parameters;
	private ArrayList<String> subFunctionSet;
	private String parentName;
	
	
	
	public AADLFunction() {
		super();
	}

	public AADLFunction(String functionName, String containingFile, ArrayList<String> parameters,
			ArrayList<String> subFunctionSet, String parentName) {
		super();
		this.functionName = functionName;
		this.containingFile = containingFile;
		this.parameters = parameters;
		this.subFunctionSet = subFunctionSet;
		this.parentName = parentName;
	}
	
	public AADLFunction(String functionName, ArrayList<String> parameters) {
		this.functionName = functionName;
		this.parameters = parameters;
	}
	

	public String getParentName() {
		return parentName;
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

	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	
	public ArrayList<String> getParameters() {
		return parameters;
	}
	
	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameters(String param) {
		this.parameters.add(param);
	}
	
	public ArrayList<String> getSubFunctionSet() {
		return subFunctionSet;
	}
	
	public void setSubFunctionSet(ArrayList<String> subFunctionSet) {
		this.subFunctionSet = subFunctionSet;
	}
	
	@Override
	public String toString() {
		return "AADLFunction [functionName=" + functionName + ", containingFile=" + containingFile + ", parameters="
				+ parameters + ", subFunctionSet=" + subFunctionSet + ", parentName=" + parentName + "]";
	}
	
	

}
