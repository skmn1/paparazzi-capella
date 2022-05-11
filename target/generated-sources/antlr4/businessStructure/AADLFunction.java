package businessStructure;

import java.util.ArrayList;

public class AADLFunction {
	private String functionName;
	private ArrayList<String> parameters;
	private ArrayList<String> subFunctionSet;
	private String parentName;
	
	
	
	public AADLFunction() {
		super();
	}

	public AADLFunction(String functionName, ArrayList<String> parameters, ArrayList<String> subFunctionSet,
			String parentName) {
		super();
		this.functionName = functionName;
		this.parameters = parameters;
		this.subFunctionSet = subFunctionSet;
		this.parentName = parentName;
	}

	public String getParentName() {
		return parentName;
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
	
	public ArrayList<String> getParameters() {
		return parameters;
	}
	
	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}
	
	public ArrayList<String> getSubFunctionSet() {
		return subFunctionSet;
	}
	
	public void setSubFunctionSet(ArrayList<String> subFunctionSet) {
		this.subFunctionSet = subFunctionSet;
	}
	
	@Override
	public String toString() {
		return "AADLFunction [functionName=" + functionName + ", parameters=" + parameters + ", subFunctionSet="
				+ subFunctionSet + ", parentName=" + parentName + "]";
	}
	
	

}
