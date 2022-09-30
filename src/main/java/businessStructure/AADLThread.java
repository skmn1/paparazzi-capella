package businessStructure;

import java.util.HashMap;
import java.util.Map.Entry;

public class AADLThread {
	
	private String threadName;
	private String threadfunctionName;
	private HashMap<String, DECL_AADLFunction> ThreadFunctionSet = new HashMap<String, DECL_AADLFunction> ();
	
	
	
	public AADLThread(String threadName, String threadfunctionName, HashMap<String, DECL_AADLFunction> threadFunctionSet) {
		super();
		this.threadName = threadName;
		this.threadfunctionName = threadfunctionName;
		ThreadFunctionSet = threadFunctionSet;
	}
	
	public AADLThread(String threadName) {
		super();
		this.threadName = threadName;
	}
	public HashMap<String, DECL_AADLFunction> getThreadFunctionSet() {
		return ThreadFunctionSet;
	}
	

	public void setThreadFunctionSet(HashMap<String, DECL_AADLFunction> threadFunctionSet) {
		ThreadFunctionSet = threadFunctionSet;
	}

	public AADLThread() {
		super();
	}

	public AADLThread(String threadName, String threadfunctionName) {
		super();
		this.threadName = threadName;
		this.threadfunctionName = threadfunctionName;
	}
	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadfunctionName() {
		return threadfunctionName;
	}

	public void setThreadfunctionName(String threadfunctionName) {
		this.threadfunctionName = threadfunctionName;
	}
	
	
	private String ThreadFunctionSetPattern () {
		String temp = "functionSet [";
		for (Entry<String, DECL_AADLFunction> functionEntry : ThreadFunctionSet.entrySet()) {
			for (String subFunctionName : functionEntry.getValue().getSubFunctionSet()) {				
				temp += subFunctionName +",\n\t\t\t\t\t\t ";
			}
		}
		temp+="]\n";
		return temp;
	}

	@Override
	public String toString() {
		return " AADLThread [ "
				+ "\n\t\tthreadName = " + threadName
				+ "\n\t\tthreadfunctionName = " + threadfunctionName
				+ "\n\t\tThreadFunctionSet = " +
				ThreadFunctionSetPattern() + "]";
	}

}
