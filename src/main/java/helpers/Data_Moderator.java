package helpers;

import java.util.ArrayList;
import java.util.HashMap;

import businessStructure.DECL_AADLFunction;
import businessStructure.GlobalVariable;


public class Data_Moderator {
	static ArrayList<String> treated =  new ArrayList<String>();
	public static int LinkIdCount = 10;
	public static HashMap<String,DECL_AADLFunction> decllfunctionSet ;
	static treeHelpers tH = new treeHelpers();

//class use to ease the use of data and moderate duplications 
	
	/**
	 * 
	 * @param globalvariablesSet
	 * @return
	 */
	//this function remove global variables (GVs) if they're called multiple times in the same function
	public HashMap<String, GlobalVariable> GlobalVariableSet(HashMap<String, GlobalVariable> globalvariablesSet) {
		
		ArrayList<String> Todelete = new ArrayList<String>();
		for (String GlobVar : globalvariablesSet.keySet()) {
			for (String testGlobVar : globalvariablesSet.keySet()) {
				if(!(Todelete.contains(GlobVar))) {
					if (!(GlobVar.equals(testGlobVar)) //if GV isn't itself
							&& tH.debugFunctionName(GlobVar).equals(tH.debugFunctionName(testGlobVar)) //if gv has the same name despite call number "/x"
							&& globalvariablesSet.get(GlobVar).getFunctionName().equals(globalvariablesSet.get(testGlobVar).getFunctionName()) // same parent?
							&& globalvariablesSet.get(GlobVar).getType().equals(globalvariablesSet.get(testGlobVar).getType())) { // same mode ? 
						Todelete.add(testGlobVar);	// add to delete List
					}	
				}	
			}	
		}
		for (String gvname : Todelete) {
			globalvariablesSet.remove(gvname); // delete GVs
		}
		return globalvariablesSet;
	}
	
	/**
	 * 
	 * @param globalvariablesSet
	 * @return
	 * 
	 * This function turn the global variable set witch
	 */
	public HashMap<String, ArrayList<String>> switchGlobalvariable(HashMap<String, GlobalVariable> globalvariablesSet) {
		HashMap<String, ArrayList<String>> globalVariableSetChanged = new HashMap<String, ArrayList<String>>() ;
		
		for (String GlobVar : globalvariablesSet.keySet()) {
			if (!globalVariableSetChanged.containsKey(globalvariablesSet.get(GlobVar).getFunctionName())) 
				globalVariableSetChanged.put(globalvariablesSet.get(GlobVar).getFunctionName(), new ArrayList<String>());
		}
		
		for (String GlobVariable : globalvariablesSet.keySet()) {
			globalVariableSetChanged.get(globalvariablesSet.get(GlobVariable).getFunctionName()).add(GlobVariable);
		}
		return globalVariableSetChanged;
	}

	
	/**
	 * 
	 * @param declfunctionSet
	 * @param globalvariablesSetKeyName
	 * @param globalvariablesSet
	 * @return
	 */
	
	public  HashMap<String, ArrayList<String>> ManageLinkedGBV (HashMap<String,DECL_AADLFunction> declfunctionSet, HashMap<String, ArrayList<String>> globalvariablesSetKeyName, HashMap<String, GlobalVariable> globalvariablesSet) {
		HashMap<String, ArrayList<String>> functionlink = new HashMap<String, ArrayList<String>>();
		for (DECL_AADLFunction function : declfunctionSet.values()) {//parcour DECL_function
			ArrayList<String> Linklist = new ArrayList<>();
			if (globalvariablesSetKeyName.containsKey(function.getFunctionName())) {
				for (String GlobalVariable : globalvariablesSetKeyName.get(function.getFunctionName())) {
					for (DECL_AADLFunction functiontest : declfunctionSet.values()) {
						Boolean flag = false;
						if (globalvariablesSetKeyName.containsKey(functiontest.getFunctionName())) {
							for (String GlobalVariableTest : globalvariablesSetKeyName.get(functiontest.getFunctionName())) {
								if ( tH.debugFunctionName(GlobalVariable).equals(tH.debugFunctionName(GlobalVariableTest)) && !(functiontest.getFunctionName().equals(function.getFunctionName())) ) {
									flag = true;
//									System.out.println(globalvariablesSet.get(GlobalVariable).get(1) +  "::" +GlobalVariable);
									if(!Linklist.contains(globalvariablesSet.get(GlobalVariable).getType() +  "::" +GlobalVariable))
									Linklist.add(globalvariablesSet.get(GlobalVariable).getType() +  "::" +GlobalVariable);
								}
							}
						}
						if (flag) {
							if (!(functionlink.containsKey(function.getFunctionName() + "///" + functiontest.getFunctionName()))) {
								functionlink.put(function.getFunctionName() + "///" + functiontest.getFunctionName(),Linklist);
							}
						}
					}
				}
			}
		}
		return (functionlink); 
	}
	
	/**
	 * 
	 * @param declfunctionSet
	 * @param globalvariablesSetKeyName
	 * @param globalvariablesSet
	 * @return
	 */
	
	public  HashMap<String, Integer> ManageLinks (HashMap<String,DECL_AADLFunction> declfunctionSet, HashMap<String, ArrayList<String>> globalvariablesSetKeyName, HashMap<String, GlobalVariable> globalvariablesSet) {
		// in this function we identify which function communicate with which function in each function set of declFunctionset
		HashMap<String, Integer> linkAndIds = new HashMap<String, Integer>();
		Integer Id = 100;
		for (DECL_AADLFunction function : declfunctionSet.values()) {//parcour DECL_function
			if (!function.getSubFunctionSet().isEmpty()) 
			for (String Subfunction : function.getSubFunctionSet()) {//parcour DECL_function subfunctionset
				for (String Subfunctiontest : function.getSubFunctionSet()) {//parcour DECL_function subfunctionset again
					
					if (!Subfunction.equals(Subfunctiontest)&& !linkAndIds.containsKey(Subfunction+"///"+Subfunctiontest) &&!linkAndIds.containsKey(Subfunctiontest+"///"+Subfunction)) {//we verify if we didn't already treat those links
						Boolean stopIn = false;
						Boolean stopOut = false;
						if (globalvariablesSetKeyName.containsKey(Subfunction)&&globalvariablesSetKeyName.containsKey(Subfunctiontest)) 
						for (String Globalvariable : globalvariablesSetKeyName.get(Subfunction)) {
							for (String GlobalvariableTest : globalvariablesSetKeyName.get(Subfunctiontest)) { //we verify if the to functions have common globalvariables 
								if (tH.debugFunctionName(Globalvariable).equals(tH.debugFunctionName(GlobalvariableTest))) {
									
									if(globalvariablesSet.get(Globalvariable).getType().equals("read")&&!stopOut) {
										linkAndIds.put(Subfunction+"///"+Subfunctiontest+"//in", Id);
										linkAndIds.put(Subfunctiontest+"///"+ Subfunction+"//out", Id);
										Id+=1;
										stopOut=true;
									}
									if(globalvariablesSet.get(Globalvariable).getType().equals("write")&&!stopIn) {
										linkAndIds.put(Subfunction+"///"+Subfunctiontest+"//out", Id);
										linkAndIds.put(Subfunctiontest+"///"+Subfunction+"//in", Id);
										Id+=1;
										stopOut=true;
									}
								}
							}
						}
					}
				}
			}
		}
		return (linkAndIds); 
		// <FunctionName1//FunctionName2, <in::nomSFin, out::nomSFout> >
	}
	
	
	
	/**
	 * weight <=> number of statement / global weight <=> number of statement function + subfunctions
	 * @ only functions with global weight = null will be managed
	 * 
	 * @param 
	 * @return declfunctionset 
	 * @ declfunctionset = data_Moderator(declfunctionSet) 
	 * @ all subfunctionSets must be declared 
	 * recursive function will be count *2
	 */
	public static HashMap<String, DECL_AADLFunction> CountGlobalWeight (HashMap<String, DECL_AADLFunction> declfunctionset ) {
		
		decllfunctionSet = declfunctionset; // copy declfunction set 
		
		for (DECL_AADLFunction function : decllfunctionSet.values()) { // for all functions without sub functions global weight = weight 
			if(function.getSubFunctionSet().isEmpty()) 
				function.setGlobalWeight(function.getWeight());
		}
		
		
		for (String functionName : declfunctionset.keySet()) {
			if(decllfunctionSet.get(functionName).getGlobalWeight()==null) { // if the function isn't already treated count its global weight 
				countGlobalWeight(functionName);
			}
		}
		return decllfunctionSet; // then return the functionSet modified
	}

	
	public static  void countGlobalWeight(String functionname){
			 
			functionname=tH.debugFunctionName(functionname); //real name of the function
			
			if (!decllfunctionSet.get(functionname).getSubFunctionSet().isEmpty() && decllfunctionSet.get(functionname).getGlobalWeight()==null) {
				ArrayList<String> subset = decllfunctionSet.get(functionname).getSubFunctionSet();
				decllfunctionSet.get(functionname).setGlobalWeight(decllfunctionSet.get(functionname).getWeight());
				if (subset.contains(functionname)) { // if the function is recursive delete itself from subfunction set and add its weight
					subset.remove(functionname);
					decllfunctionSet.get(functionname).addGlobalWeight(decllfunctionSet.get(functionname).getWeight());
				}
				countSubStatement(functionname,subset); //count sub statement
			}
		}
	
	
	private static void countSubStatement(String FunctionName,ArrayList<String> subFunctionSet) {
		for (String subFunctionName : subFunctionSet) {
			subFunctionName = tH.debugFunctionName(subFunctionName);
			if (decllfunctionSet.containsKey(subFunctionName)) {
				if (!(decllfunctionSet.get(subFunctionName).getSubFunctionSet().isEmpty())) {
					countGlobalWeight(subFunctionName);
					decllfunctionSet.get(FunctionName).addGlobalWeight(decllfunctionSet.get(subFunctionName).getGlobalWeight());					
				}
				else decllfunctionSet.get(FunctionName).addGlobalWeight(decllfunctionSet.get(subFunctionName).getGlobalWeight());
			}
		}
	}
	
	

}
