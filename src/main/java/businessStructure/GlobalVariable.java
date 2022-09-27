package businessStructure;

public class GlobalVariable {
	String VariableName;
	String Type;
	String FunctionName;
	
	
	
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

	@Override
	public String toString() {
		return "GlobalVariable  Name=" + VariableName + ", Type=" + Type + ", FunctionName=" + FunctionName + "\n";
	}
}
