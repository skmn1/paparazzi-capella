package businessStructure;

import java.util.HashMap;

public class AADLThread {
	
	private String threadName;
	private String threadfunctionName;
	private HashMap<String, AADLFunction> ThreadFunctionSet = new HashMap<String, AADLFunction> ();
	
	
	
	
	public AADLThread(String threadName, String threadfunctionName, HashMap<String, AADLFunction> threadFunctionSet) {
		super();
		this.threadName = threadName;
		this.threadfunctionName = threadfunctionName;
		ThreadFunctionSet = threadFunctionSet;
	}

	public HashMap<String, AADLFunction> getThreadFunctionSet() {
		return ThreadFunctionSet;
	}

	public void setThreadFunctionSet(HashMap<String, AADLFunction> threadFunctionSet) {
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

	@Override
	public String toString() {
		return "AADLThread [threadName=" + threadName + ", threadfunctionName=" + threadfunctionName
				+ ", ThreadFunctionSet=" + ThreadFunctionSet + "]";
	}

}
