import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import businessStructure.AADLThread;
import businessStructure.CALLED_AADLFunction;
import businessStructure.DECL_AADLFunction;
import businessStructure.GlobalVariable;
import businessStructure.GotoStructure;
import businessStructure.IfStructure;
import helpers.Data_Moderator;
import helpers.FileHelper;
import helpers.treeHelpers;

@SuppressWarnings("unused")
public class CPPXMLTransformer {

	public static HashMap<String,String> functionFileMap = new HashMap<String,String>();
	public static HashMap<String,Integer> Breakdown = new HashMap<String,Integer>();
	
	
	public static HashMap<String,CALLED_AADLFunction> callfunctionSet = new HashMap<String,CALLED_AADLFunction>();
	public static HashMap<String,DECL_AADLFunction> declfunctionSet = new HashMap<String,DECL_AADLFunction>();
	
	public static HashMap<String, GlobalVariable> globalvariablesSet = new HashMap<String, GlobalVariable>() ;
	public static HashMap<String, ArrayList<String>> globalvariablesSetKeyName = new HashMap<String, ArrayList<String>>(); // [function name , list of global variable names that function contains]
	
	public static HashMap<String, IfStructure> ifstructureSet = new HashMap<String, IfStructure>();
	public static HashMap<String, GotoStructure> gotoStructureSet = new HashMap<String, GotoStructure>();
	
	public static HashMap<String, Integer> linkAndIds = new HashMap<String, Integer>(); // use to manage Id issues with function exchange
	public static ArrayList<String> FunctionAlreadydone =  new ArrayList<String>(); // use to not build a function multiple times
	public static ArrayList<String> IfAlreadyDone = new ArrayList<String>(); // use to manage conditional declaration
	
	static treeHelpers tH = new treeHelpers(); // use to call functions of this class
		
	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} 
			else {
				System.err.println(fileEntry.getName());
				System.out.println(fileEntry);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		final File folder = new File("\\\\dataetu\\Etudiants$\\decoutd\\Documents\\astageSI2022\\lower");
		
		String wholeFolderContent = FileHelper.getFolderTextContent(folder);

		Breakdown = FileHelper.convertFileToMap(new File("\\\\dataetu\\Etudiants$\\decoutd\\Documents\\astageSI2022\\paparazzi-capella\\src\\main\\java\\paparazzi_files.csv"));

		String content = "";
		
		for (final File fileEntry : folder.listFiles()) {
			@SuppressWarnings("resource")
			Scanner useDelimiter = new Scanner(fileEntry).useDelimiter("\\Z");
			content = useDelimiter.next();
			ANTLRInputStream input = new ANTLRInputStream( content );
			CPP14Lexer lexer = new CPP14Lexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CPP14Parser parser = new CPP14Parser(tokens);
			CPPFunctionFileListener listenerff = new CPPFunctionFileListener();
			parser.addParseListener(listenerff);
			ParseTree tree = parser.translationUnit();
			addEntriesToFunctionMap(listenerff.functionList, fileEntry.getName());
			System.out.println("  " +fileEntry.getName()+ "  CSV value = " +Breakdown.get(fileEntry.getName()));
		}
		
		// parser initialization
		ANTLRInputStream input = new ANTLRInputStream( wholeFolderContent );

		CPP14Lexer lexer = new CPP14Lexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CPP14Parser parser = new CPP14Parser(tokens);

		CPPCustomListener listener = new CPPCustomListener(Breakdown);

		parser.addParseListener(listener);

		ParseTree tree = parser.translationUnit();
		
		// Data retrieving with custom listener 
		callfunctionSet = listener.callfunctionSet;
		globalvariablesSet =listener.globalvariablesSet;
		declfunctionSet = listener.declfunctionSet;
		ifstructureSet = listener.ifstructureSet;
		gotoStructureSet = listener.gotostructureSet;
		
		Data_Moderator dataModerator = new Data_Moderator ();
		
		// data management with data_moderator class
		globalvariablesSet = dataModerator.GlobalVariableSet(globalvariablesSet);
		globalvariablesSetKeyName = dataModerator.switchGlobalvariable(globalvariablesSet);	
		linkAndIds = dataModerator.ManageLinks(declfunctionSet, globalvariablesSetKeyName, globalvariablesSet);
		declfunctionSet = Data_Moderator.CountGlobalWeight(declfunctionSet);
		
		// XML generation
		String XMLTags = getXMLfromThreadDataStructure(listener.threadSet);
		String FileName = "\\\\DATAETU\\Etudiants$\\decoutd\\Documents\\astageSI2022\\paparazzi-capella\\test-papparrazi.melodymodeller";
		String startpoint = "\"deployment:AADLProcess\"";
		
		FileHelper.insertStringIntoFile(FileName, startpoint, XMLTags);
		
		// display
//		System.out.println("\r\n~~DECL Functions");
//		for (String Function : declfunctionSet.keySet()) {
//			System.out.println("\r\n" + Function + "  =>  " +declfunctionSet.get(Function).toString());
//		}
//		System.out.println("\r\n~~ CalledFunctions");
//		for (String Function : callfunctionSet.keySet()) {
//			System.out.println("\r\n" + Function + "  =>  " +callfunctionSet.get(Function).toString());
//		}
//		System.out.println("\r\n\r\n~~ IfStructures");
//		for (String IfStructure : ifstructureSet.keySet()) {
//			System.out.println("\r\n" + IfStructure + "  =>  " + ifstructureSet.get(IfStructure).toString());
//		}
//		System.out.println("\r\n~~ Goto");
//		for (String Label : gotoStructureSet.keySet()) {
//			System.out.println("\r\n" + Label + "  =>  " +gotoStructureSet.get(Label).toString());
//		}
	}
	

	private static String getXMLfromThreadDataStructure(HashMap<String, AADLThread>  threadSet) {
		String str = "";
		
		for (Entry<String, AADLThread>  threadEntry : threadSet.entrySet()) {
			str += injectFunction(threadEntry);
		}
		return str;
	}


	public static void addEntriesToFunctionMap(ArrayList<String> functionList, String fileName) {
		for (String functionName : functionList) {
			functionFileMap.put(functionName, fileName);
		}
	}

	public static void printFunctionFileMap() {
		for(Entry<String,String> entry: functionFileMap.entrySet()) {
			System.out.println("function name : " + entry.getKey() +" ======> " + entry.getValue()+"\n\n" );
		}
	}

	public static String injectFunction (Entry<String, AADLThread>  threadEntry) {

		String str = "";

		//		 thread creation
		str += "<AADLThreadSet xsi:type=\"deployment:AADLThread\" id=\"_T_SWgNA7Eey3zKUaIaFcdg\"\r\n"
				+ "\tname=\"" + threadEntry.getKey() + "\" timeBudgetUnit=\"ms\">\r\n";
		String functionTabs = "\t";

		//		thread functions creation
		for (Entry<String, DECL_AADLFunction> ThreadFunctionSet : threadEntry.getValue().getThreadFunctionSet().entrySet()) {
			if(ThreadFunctionSet.getValue().getSubFunctionSet().isEmpty())

				//				if the function doesn't contain any subfunction then we create autoclosed xml thread function
				str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
						+ functionTabs  +"name=\"" + ThreadFunctionSet.getKey() + "::" + functionFileMap.get(ThreadFunctionSet.getKey()) + "\"/>\r\n";
			
			else {
				//				if the thread function has some subfunctions
				//				we create the function and then parcour the functions by calling the injectsubfunction method
				str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
						+ functionTabs +"\tname=\"" + ThreadFunctionSet.getKey() + "::" 
//						+ functionFileMap.get(ThreadFunctionSet.getKey()) // ajout nom du fichier
						+ "\">\r\n";

				str += injectSoftwareBus(ThreadFunctionSet.getKey(),functionTabs);
				ArrayList<String> FunctionTest = new ArrayList<>();
				for (String subFunctionName : ThreadFunctionSet.getValue().getSubFunctionSet()) {	
					if (!FunctionTest.contains(tH.debugFunctionName(subFunctionName))) {
					str += injectSubFunction(subFunctionName,ThreadFunctionSet.getValue().getFunctionName(),"\t");
					FunctionTest.add(tH.debugFunctionName(subFunctionName));
					}
				}

				str+= "\t</ownedExtensions>\r\n";
			}
			
		}

		str += "<header xsi:type=\"deployment:AADLThreadHeader\" id=\"_T_SWgdA7Eey3zKUaIaFcdg\"/>\r\n"
				+ "            </AADLThreadSet>\n";

		return str;

	}

	public static String  injectSubFunction (String subFunctionName, String Parent, String lvl ) {
		String str = "";
		String functionTabs = "\t" + lvl;
		Integer FileValue = getFileValue1(tH.debugFunctionName(subFunctionName));
		Parent = tH.debugFunctionName(Parent);
		// we check if the function exist in the callFunction list if it doesnt autoclose xml function
		if(callfunctionSet.containsKey(subFunctionName)) {
			str += functionTabs  + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad" + callfunctionSet.get(subFunctionName).getId() +"\"\r\n"
					+ functionTabs + "\t" +"name=\"" + tH.debugFunctionName(subFunctionName) ;
			str += injectLinesOfCode(subFunctionName);
			str +="\"";
			str += injectDataFlow(subFunctionName, Parent);
			
			// arguments must be called here 
			// we check if the function has been already developed. if not we develop it
			if (!FunctionAlreadydone.contains(tH.debugFunctionName(subFunctionName))) {
				FunctionAlreadydone.add(tH.debugFunctionName(subFunctionName));
				// inject software bus method we check if the function contains a variable first
				Boolean containaGVariable = isFunctionHaveGlobalVariable(subFunctionName);
				if (containaGVariable) {
					str+= ">\r\n";
					str += injectLinkedfunction(subFunctionName,Parent, functionTabs);
					str += injectSoftwareBus(subFunctionName, functionTabs );
				}


				if(FileValue>0) {
					// we check if a method exist for this function
					if(declfunctionSet.containsKey(tH.debugFunctionName(subFunctionName))){
						//if the method doesn'tcontain any information we close xml function
						if(declfunctionSet.get(tH.debugFunctionName(subFunctionName)).getSubFunctionSet() == null || declfunctionSet.get(tH.debugFunctionName(subFunctionName)).getSubFunctionSet().isEmpty()) {
							if (containaGVariable) {
								str+= functionTabs + "</ownedExtensions>\r\n";
							}
							else str+="/>\r\n";
						}
						else {
							if (FileValue > 1) {
								//if the file have a value = 2, we inject subfunctions by calling subfunction method
								if (!containaGVariable) str += ">\r\n";
								ArrayList<String> Functiontest = new ArrayList<>();
								for (String subSubFunctionName : declfunctionSet.get(tH.debugFunctionName(subFunctionName)).getSubFunctionSet()) {
									// we check if function has been already called 
									if (!Functiontest.contains(tH.debugFunctionName(subSubFunctionName))) {
										str += injectSubFunction(subSubFunctionName, subFunctionName, functionTabs);
										Functiontest.add(tH.debugFunctionName(subSubFunctionName));
									}
									
								}
								str+= functionTabs + "</ownedExtensions>\r\n";	
							}
							else if (containaGVariable) {
								str+= functionTabs + "</ownedExtensions>\r\n";
							}
							else str+= "/>\r\n";
						}
					}
					else str += "/>\r\n";
				}
				else if (containaGVariable) {
					str+= functionTabs + "</ownedExtensions>\r\n";
				}
				else str+="/>\r\n";
			}
			else str+="/>\r\n";
		}
		else str += "/>\r\n";
		return str;
	}

	
	private static String injectDataFlow(String subFunctionName, String parent) {
		String str = new String();
		String ParentBis = new String();
		if (IfAlreadyDone.contains(parent)) {
			Integer i = 1;
			while (true) {
				ParentBis = parent +"/" + i;
				if(!ifstructureSet.containsKey(ParentBis)) {
					ParentBis = parent;
					break; //y'en a plus
				}
				if (!IfAlreadyDone.contains(ParentBis)&&ifstructureSet.containsKey(ParentBis))
					break;
				i +=1;
			}
		}
		else ParentBis = parent;
		
		if (ifstructureSet.containsKey(ParentBis)&& !declfunctionSet.get(parent).SubFunctionSetIsEmpty()) {
			if ((declfunctionSet.get(parent).getSubFunctionSet().size()-1) > declfunctionSet.get(parent).getSubFunctionSet().indexOf(subFunctionName)) {
				
				// if the next step is a if statement 
				if(subFunctionName.equals(ifstructureSet.get(ParentBis).getLastFunctionCalled())) {
				Integer Idthen = 0;
				Integer Idelse = 0;
				for (int i = 0; i < declfunctionSet.get(parent).getSubFunctionSet().size(); i++) {
					if( callfunctionSet.get(declfunctionSet.get(parent).getSubFunctionSet().get(i)).getLabel().equals(ifstructureSet.get(ParentBis).getThenLabel()) ) {
						Idthen = callfunctionSet.get(declfunctionSet.get(parent).getSubFunctionSet().get(i)).getId();
						break;
					}
				}
				for (int i = 0; i < declfunctionSet.get(parent).getSubFunctionSet().size(); i++) {
					if( callfunctionSet.get(declfunctionSet.get(parent).getSubFunctionSet().get(i)).getLabel().equals(ifstructureSet.get(ParentBis).getElseLabel()) ) {
						Idelse = callfunctionSet.get(declfunctionSet.get(parent).getSubFunctionSet().get(i)).getId();
						break;
					}
				}
				if (Idthen==0 &&Idelse == 0)
					System.out.println("Label not found");

				str += " execution_dataflow=\"#baaec95a-deed-4d91-9998-c10e6aec4ad" + Idthen.toString() + " #baaec95a-deed-4d91-9998-c10e6aec4ad"+Idelse + "\"" ;
				IfAlreadyDone.add(parent);

				}
				
				
				else if (callfunctionSet.get(subFunctionName).getLabel().equals("")) {
					if (!subFunctionName.equals(ifstructureSet.get(ParentBis).getLastFunctionCalled())) {
						str += " execution_dataflow=\"#baaec95a-deed-4d91-9998-c10e6aec4ad" + (callfunctionSet.get(subFunctionName).getId()+1)+"\"";
					}
				}
				else if (callfunctionSet.get(subFunctionName).getLabel() == getNextLabel(parent, subFunctionName)) { //Label= NextLabel 
					str += " execution_dataflow=\"#baaec95a-deed-4d91-9998-c10e6aec4ad" + (callfunctionSet.get(subFunctionName).getId()+1)+"\"";
				}
				else if(!(callfunctionSet.get(subFunctionName).getLabel() == getNextLabel(parent, subFunctionName))) {//(Label!= label suivant)
					if(gotoStructureSet.containsKey(callfunctionSet.get(subFunctionName).getLabel()))  {//si goto va chercher le premier avec ce label 
						Integer Idgoto = 0;
						String gotoLabel = gotoStructureSet.get(callfunctionSet.get(subFunctionName).getLabel()).getLabel();
						for (int i = 0; i < declfunctionSet.get(parent).getSubFunctionSet().size(); i++) {
							if( callfunctionSet.get(declfunctionSet.get(parent).getSubFunctionSet().get(i)).getLabel().equals(gotoLabel) ) {
								Idgoto = callfunctionSet.get(declfunctionSet.get(parent).getSubFunctionSet().get(i)).getId();
								break;
							}
						}
						str += " execution_dataflow=\"#baaec95a-deed-4d91-9998-c10e6aec4ad" + Idgoto.toString() + "\"" ;
						
					}
					else 
						str += " execution_dataflow=\"#baaec95a-deed-4d91-9998-c10e6aec4ad" + (callfunctionSet.get(subFunctionName).getId()+1)+"\"";
					
				}
			}
		}
		return str;
	}

	private static String injectLinesOfCode(String subFunctionName) {
		String str = new String();
		if (declfunctionSet.containsKey((tH.debugFunctionName(subFunctionName))))
		str += ":::LOC =" + declfunctionSet.get(tH.debugFunctionName(subFunctionName)).getGlobalWeight();
		return str;
	}

	public static String injectSoftwareBus (String FunctionName, String tabs) {
		
		String str = "";
		String globalVariablTabs = tabs+"\t";
		// use if you want 1 in/out max 
		Boolean StopBus1 = false;
		Boolean StopBus2 = false;
		// we check across the global variable set in order to find GV parent names equal to the current function 
		if (globalvariablesSetKeyName.containsKey(FunctionName))
			for (String variableName : globalvariablesSetKeyName.get(FunctionName)) {
				//		if (StopBus1 && StopBus2) break;
				if (globalvariablesSet.get(variableName).getFunctionName().equals(FunctionName)) {
					
					// we check the GV mode read/write
					if(globalvariablesSet.get(variableName).getType() == "read"&& !StopBus1 ) {
						str += globalVariablTabs + "<SofwareBusInputPort_set xsi:type=\"deployment:SoftwareBusInputPort\" id=\"0fd48a77-c477-4535-b51b-d7bdc5f53942\" variableName=\" "+ tH.debugFunctionName(variableName) +"\"/>\r\n";
						//					StopBus1 = true;
					}
					else if(globalvariablesSet.get(variableName).getType() == "write" && !StopBus2){
						str +=  globalVariablTabs + "<SofwareBusOutputPort_set xsi:type=\"deployment:SoftwareBusOutputPort\" id=\"9e4a061e-378a-412e-8adb-e9f94a22ed47\" variableName=\""+ tH.debugFunctionName(variableName) +"\"/>\r\n";
						//					StopBus2 = true;
					}
				}
			}
		return str;
	}

	public static Boolean isFunctionHaveGlobalVariable (String FunctionName) {
		Boolean flag = false;
		for (String variableName : globalvariablesSet.keySet()) {
			if (globalvariablesSet.get(variableName).getFunctionName().equals(FunctionName)) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}

	public static int getFileValue1 (String FunctionName) {
		if (Breakdown.containsKey(functionFileMap.get(tH.debugFunctionName(FunctionName)))) {
			if (functionFileMap.get(tH.debugFunctionName(FunctionName)).contains("sys_time")) {
				return 2;
			}
			if (functionFileMap.get(tH.debugFunctionName(FunctionName)).contains("main") ) {
				return 2;
			}
			if (functionFileMap.get(tH.debugFunctionName(FunctionName)).contains("autopilot") ) {
				return 2;
				
			}
			return Breakdown.get(functionFileMap.get(tH.debugFunctionName(FunctionName)));
		}
		else 
			return 0;
	}

	public static String injectLinkedfunction (String FunctionName, String ParentFunctionName , String tabs) {
		
		String str = new String();
		tabs= "\t" + tabs;
		
		if (declfunctionSet.containsKey(ParentFunctionName))
			for (String FunctionTestName : declfunctionSet.get(ParentFunctionName).getSubFunctionSet() ) {
				String testLink= tH.debugFunctionName(FunctionName) + "///" + tH.debugFunctionName(FunctionTestName) ;

				if (linkAndIds.containsKey(testLink+"//in")) 
					str+=tabs+"<functionInputPort_set xsi:type=\"deployment:AADLFunctionInputPort\"\r\n"
							+tabs+ "                        id=\"_LatrkChaEe2-m"+ linkAndIds.get(testLink+"//in") +"yQCw0pg\"/>\n";
				if (linkAndIds.containsKey(testLink+"//out")) 
					str +=tabs+"<functionOutputPort_set xsi:type=\"deployment:AADLFunctionOutputPort\"\r\n"
							+tabs+ "                        id=\"_LatEgChaEe2-m4YyQCw0pg\" Function2function_edges=\"#_LatrkChaEe2-m"+ linkAndIds.get(testLink+"//out")+"yQCw0pg\"/>\n";
			}
		return str;
	}
	
	
	public static Integer getNextFunctionIndex(String Parent, String subFunctionName) {
		Integer Index = declfunctionSet.get(tH.debugFunctionName(Parent)).getSubFunctionSet().indexOf(subFunctionName)+1 ;
		return Index;
	}
	
	public static Integer getPreviousFunctionIndex(String Parent, String subFunctionName) {
		Integer Index = 0;
		Index = declfunctionSet.get(tH.debugFunctionName(Parent)).getSubFunctionSet().indexOf(subFunctionName)-1 ;
		return Index;
	}
	
	public static boolean IsNextFunctionLinked(String Parent, String subFunctionName, String Label) {
		String NextFunctionname = new String();
		if (declfunctionSet.get(Parent).getSubFunctionSet().size() <= getNextFunctionIndex(Parent, subFunctionName) ) {
			NextFunctionname= declfunctionSet.get(Parent).getSubFunctionSet().get(getNextFunctionIndex(Parent, subFunctionName));
			if( callfunctionSet.get(NextFunctionname).getLabel().equals(Label))
				return true;
			else 
				return false;
		}
		else 
			return false;
	}
	
	public static String getNextLabel(String ParentName, String subfunctionName) {
		String NextLabel = new String();
		Integer NextIndex = declfunctionSet.get(ParentName).getSubFunctionSet().indexOf(subfunctionName)+1;
		String NextFunctionName = declfunctionSet.get(ParentName).getSubFunctionSet().get(NextIndex);
		NextLabel = callfunctionSet.get(NextFunctionName).getLabel();
		return NextLabel;
	}

}