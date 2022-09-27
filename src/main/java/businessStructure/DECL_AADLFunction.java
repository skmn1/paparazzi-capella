package businessStructure;

import java.util.ArrayList;


public class DECL_AADLFunction extends AADLFunction {
	
	private ArrayList<String> subFunctionSet;
	private Integer Weight; //number of statement of the function 
	private Integer GlobalWeight; // number of statement of the function and is subfunctions 


	public DECL_AADLFunction() {
		super();
	}

	public DECL_AADLFunction(String functionName, ArrayList<String> list, Integer globalweight) {
		super(functionName);
		this.GlobalWeight= globalweight;
		this.subFunctionSet = list;
		
	}
	public DECL_AADLFunction(String functionName, Integer GlobalWeight) {
		super(functionName);
		this.GlobalWeight = GlobalWeight;
	}
	
	public DECL_AADLFunction(String currrentfunctionName) {
		super(currrentfunctionName);	
		}

	public void setSubFunctionSet(ArrayList<String> subFunctionSet) {
		this.subFunctionSet = subFunctionSet;
	}

	public ArrayList<String> getSubFunctionSet() {
		if (this.subFunctionSet==null)
			return null;

		return this.subFunctionSet;
	}
	public Boolean SubFunctionSetIsEmpty () {
		if (this.subFunctionSet==null)
			return true;
		else return false;
	}

	public Integer getWeight() {
		return Weight;
	}

	public void setWeight(Integer weight) {
		Weight = weight;
	}

	public Integer getGlobalWeight() {
		return GlobalWeight;
	}

	public void setGlobalWeight(Integer globalWeight) {
		GlobalWeight = globalWeight;
	}
	
	public void addGlobalWeight(int weigth) {
		this.GlobalWeight += weigth;
	}

	@Override
	public String toString() {
		return "AADLFunction functionName=" + getFunctionName() + " : {\ncontainingFile = " + getContainingFile() 
				 + "\nsubFunctionSet = " + subFunctionSet + "\nWeight = " + Weight + "\nGlobalWeight = " + GlobalWeight +"\n }"  ;
	}
}
