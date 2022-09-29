package businessStructure;

import java.util.ArrayList;

public class CALLED_AADLFunction extends AADLFunction {
	private String parentName;
	private ArrayList<String> parameters;
	private String Label;
	private Integer Id;
	
	
	public CALLED_AADLFunction(String fname, String cfunctionName, ArrayList<String> arguments, String currentLabel) {
		super(fname);
		this.parameters = arguments;
		this.parentName = cfunctionName;
		this.Label = currentLabel;
		
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
	
	
	public String getLabel() {
		return Label;
	}
	
	public void setLabel(String label) {
		this.Label = label;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	@Override
	public String toString() {
		return " functionName=" + getFunctionName()  + ", parameters="
				+ parameters + ", parentName=" + parentName + " Label : " +Label + "   id : " +Id ;
	}

}
