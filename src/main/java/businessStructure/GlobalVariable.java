package businessStructure;

import helpers.GlobalVariablesEnum;

public class GlobalVariable {
	private String VariableName;
	private GlobalVariablesEnum KeyName;
	private String Type;
	private String FunctionName;
	
	
	
	public GlobalVariable(String name, String type, String functionName) {
		super();
		VariableName = name;
		Type = type;
		FunctionName = functionName;
	}
	
	public String getName() {
		return VariableName;
	}
	public void setName(String name) {
		this.VariableName = name;
		for (GlobalVariablesEnum globalVariable : GlobalVariablesEnum.values()) {
			if (VariableName.contains(globalVariable.toString())) {
				KeyName = globalVariable;
			}
		}
		
		
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		this.Type = type;
	}
	
	public String getFunctionName() {
		return FunctionName;
	}
	public void setFunctionName(String functionName) {
		this.FunctionName = functionName;
	}
	
	public String getVariableName() {
		return VariableName;
	}
	public void setVariableName(String variableName) {
		this.VariableName = variableName;
	}

	public GlobalVariablesEnum getKeyName() {
		return KeyName;
	}
	public void setKeyName(GlobalVariablesEnum keyName) {
		this.KeyName = keyName;
	}

	@Override
	public String toString() {
		return "GlobalVariable  Name=" + VariableName + ", Type=" + Type + ", FunctionName=" + FunctionName + "\n";
	}
}
