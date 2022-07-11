package businessStructure;

import java.util.ArrayList;

public class CALLED_AADLFunction extends AADLFunction {
	private String parentName;
	private ArrayList<String> parameters;
	
	
	
	public CALLED_AADLFunction(String functionName, String parent , ArrayList<String> parameters) {
		super(functionName);
		this.parameters = parameters;
		this.parentName = parent;
	}
	
	
	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public String getParentName() {
		return parentName;
	}
	
	@Override
	public String toString() {
		return "AADLFunction [functionName=" + getFunctionName() + ", containingFile=" + getContainingFile() + ", parameters="
				+ parameters + ", parentName=" + parentName + "]";
	}

}
