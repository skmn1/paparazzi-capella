package businessStructure;

public class GotoStructure {
	
	
	private String Label;
	private Boolean IsaFunctionEnd;
	private String LastFunction;
	private Integer LastFunctionId;
	
	
	public GotoStructure(String lastFunctionCalled, Boolean lastStatement) {
		this.IsaFunctionEnd = lastStatement;
		this.LastFunction = lastFunctionCalled;
	}
	
	
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public Boolean getFunctionEnd() {
		return IsaFunctionEnd;
	}
	public void setFunctionEnd(Boolean functionEnd) {
		IsaFunctionEnd = functionEnd;
	}
	public String getLastFunction() {
		return LastFunction;
	}
	public void setLastFunction(String lastFunction) {
		LastFunction = lastFunction;
	}

	public Integer getLastFunctionId() {
		return LastFunctionId;
	}


	public void setLastFunctionId(Integer lastFunctionId) {
		LastFunctionId = lastFunctionId;
	}


	@Override
	public String toString() {
		return "Label to reach :" + Label + ", isaEnd of a function decl : " + IsaFunctionEnd + ", lastfunction : " + LastFunction +" id : " + LastFunctionId+  "\n";
	}
}
