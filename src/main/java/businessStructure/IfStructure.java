package businessStructure;


public class IfStructure {
	
	
	private String lastFunctionCalled;
	private String IfCondition;
	private String ThenLabel;
	private String ElseLabel;
 
public IfStructure(String lastFunctionCalled, String ifCondition, String thenLabel, String elseLabel) {
	super();
	this.lastFunctionCalled = lastFunctionCalled;
	IfCondition = ifCondition;
	ThenLabel = thenLabel;
	ElseLabel = elseLabel;
}
public String getLastFunctionCalled() {
	return lastFunctionCalled;
}
public void setLastFunctionCalled(String lastFunctionCalled) {
	this.lastFunctionCalled = lastFunctionCalled;
}
public String getIfCondition() {
	return IfCondition;
}
public void setIfCondition(String ifCondition) {
	IfCondition = ifCondition;
}
public String getThenLabel() {
	return ThenLabel;
}
public void setThenLabel(String thenLabel) {
	ThenLabel = thenLabel;
}
public String getElseLabel() {
	return ElseLabel;
}
public void setElseLabel(String elseLabel) {
	ElseLabel = elseLabel;
}
@Override
public String toString() {
	return "IfStructure [lastFunctionCalled=" + lastFunctionCalled + ", IfCondition=" + IfCondition + ", ThenLabel="
			+ ThenLabel + ", ElseLabel=" + ElseLabel + "]";
}
 
 
}
