import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import businessStructure.AADLFunction;
import businessStructure.AADLThread;
import businessStructure.CALLED_AADLFunction;
import businessStructure.DECL_AADLFunction;
import helpers.FileHelper;

public class CPPXMLTransformer {

	public static HashMap<String,String> functionFileMap = new HashMap<String,String>();
	public static HashMap<String,Integer> Breakdown = new HashMap<String,Integer>();
//	public static HashMap<String,AADLFunction> functionSet = new HashMap<String,AADLFunction>();
	public static HashMap<String,CALLED_AADLFunction> callfunctionSet = new HashMap<String,CALLED_AADLFunction>();
	public static HashMap<String,DECL_AADLFunction> declfunctionSet = new HashMap<String,DECL_AADLFunction>();
	public static HashMap<String, ArrayList<String>> globalvariablesSet = new HashMap<String, ArrayList<String>>();


	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.err.println(fileEntry.getName());
				System.out.println(fileEntry);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		final File folder = new File("\\\\dataetu\\Etudiants$\\decoutd\\Documents\\astageSI2022\\lower");
		//    	System.out.println(FileHelper.listFilesForFolder(folder));
		String wholeFolderContent = FileHelper.getFolderTextContent(folder);

		//		String content = FileHelper.getFolderTextContent(folder);

		Breakdown = FileHelper.convertFileToMap(new File("\\\\dataetu\\Etudiants$\\decoutd\\Documents\\astageSI2022\\paparazzi-capella\\src\\main\\java\\paparazzi_files.csv"));
		//		System.out.println(fileDecompositionMap);

		//		for (Entry<String,Integer> entry : fileDecompositionMap.entrySet()) {
		//			System.out.println(entry.getKey() + " value =====> " + entry.getValue());
		//		}

		//    	HashMap<String, Integer> datamap = new HashMap<String, Integer>();

		//        Scanner useDelimiter = new Scanner(new File("Z:\\xtextWS\\ANTLR4\\HelloANTLR\\src\\main\\java\\input.txt")).useDelimiter("\\Z");
		//		String content = useDelimiter.next();

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
			System.out.println("                  " +fileEntry.getName()+ "  CSV value = " +Breakdown.get(fileEntry.getName()));
		}
		ANTLRInputStream input = new ANTLRInputStream( wholeFolderContent );

		CPP14Lexer lexer = new CPP14Lexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CPP14Parser parser = new CPP14Parser(tokens);

		CPPCustomListener listener = new CPPCustomListener(Breakdown);
		//		CPPFunctionFileListener listenerff = new CPPFunctionFileListener();

		parser.addParseListener(listener);

		ParseTree tree = parser.translationUnit();
//		functionSet = listener.functionSet;
		callfunctionSet = listener.callfunctionSet;
		declfunctionSet = listener.declfunctionSet;
		globalvariablesSet =listener.globalvariablesSet;
		System.out.println(listener.threadSet.toString());
		String XMLTags = getXMLfromThreadDataStructure(listener.threadSet);

		String FileName = "\\\\DATAETU\\Etudiants$\\decoutd\\Documents\\astageSI2022\\paparazzi-capella\\test-papparrazi.melodymodeller";
		String startpoint = "\"deployment:AADLProcess\"";

		FileHelper.insertStringIntoFile(FileName, startpoint, XMLTags);

//		System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n"); 
//		System.out.println(listener.toString());
//		System.out.println("\r\n");
//		System.out.println("~~all callAADLfunctions : ");
//		
//		for (Iterator i = callfunctionSet.keySet().iterator(); i.hasNext();) {
//			Object key = i.next();
//			System.out.println("- " +key + "\n" + callfunctionSet.get(key).toString() +"\n");
//		}
//		
//		System.out.println("\r\n");
//		System.out.println("~~all declAADLfunctions : ");
//		for (Iterator i = declfunctionSet.keySet().iterator(); i.hasNext();) {
//			Object key = i.next();
//			System.out.println("- " +key + "\n" + declfunctionSet.get(key).toString() +"\n");
//		}
//		
//		System.out.println("\r\n");
//		System.out.println("\n ~~all Global variables : ");
//		for (Iterator j = globalvariablesSet.keySet().iterator(); j.hasNext();) {
//			Object key = j.next();
//			System.out.println("- " +key + "\n" +globalvariablesSet.get(key).toString() +"\n");
//		}
		System.out.println("\r\n");
		System.out.println();
	}

	private static String getXMLfromThreadDataStructure(HashMap<String, AADLThread>  threadSet) {
		String str = "";
		final String functionTabs = "\t";

		for (Entry<String, AADLThread>  threadEntry : threadSet.entrySet()) {
			str += injectFunction(threadEntry);
		}
		System.out.println(str);
		return str;
	}


	public static void setUpParsingProcess (String content) {
		ANTLRInputStream input = new ANTLRInputStream( content );

		CPP14Lexer lexer = new CPP14Lexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CPP14Parser parser = new CPP14Parser(tokens);

		CPPCustomListener listener = new CPPCustomListener(Breakdown);

		parser.addParseListener(listener);

		ParseTree tree = parser.translationUnit();
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
		System.out.println(threadEntry.getValue().getThreadFunctionSet().entrySet());
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
				// the injectsubfunction must be called here
				
				str += injectSoftwareBus(ThreadFunctionSet.getKey(),functionTabs);
				for (String subFunctionName : ThreadFunctionSet.getValue().getSubFunctionSet()) {		
					str += injectSubFunction(subFunctionName,"\t");
				}

				str+= "\t</ownedExtensions>\r\n";
			}
			
		}

		str += "<header xsi:type=\"deployment:AADLThreadHeader\" id=\"_T_SWgdA7Eey3zKUaIaFcdg\"/>\r\n"
				+ "            </AADLThreadSet>\n";

		return str;

	}

	public static String  injectSubFunction (String subFunctionName, String lvl ) {
		String str = "";
		String functionTabs = "\t" + lvl;
		Integer FileValue = getFileValue1(debugFunctionName(subFunctionName));
		System.out.println(subFunctionName + FileValue);
		System.out.println( functionFileMap.get(debugFunctionName(subFunctionName)) + Breakdown.get(functionFileMap.get(debugFunctionName(subFunctionName))));
//		System.err.println("subfunction name : " + subFunctionName);
//		System.out.println(functionSet.get(subFunctionName));
//		System.err.println(functionSet);
		
		// we check if the function exist in the callFunction list if it doesnt autoclose xml function
		
		if(callfunctionSet.containsKey(subFunctionName)) {
//			System.out.println(subFunctionName);
			str += functionTabs  + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
					+ functionTabs + "\t" +"name=\"" + debugFunctionName(subFunctionName);
//			if (functionFileMap.containsKey(debugFunctionName(subFunctionName))){
//				str += "::" + functionFileMap.get(debugFunctionName(subFunctionName)) + "\"" ;
//			}
//			else 
				str +="\"";
			// arguments must be called here 
			
			// inject software bus method
			Boolean containaGVariable = isFunctionHaveGlobalVariable(subFunctionName);
			if (containaGVariable) {
				str+= ">\r\n";
				str += injectSoftwareBus(subFunctionName, functionTabs );
			}
			
			
			
			if(FileValue>0) {
				// we check if a method exist for this function
				if(declfunctionSet.containsKey(debugFunctionName(subFunctionName))){
					//if the method doesn'tcontain any information we close xml function
					if(declfunctionSet.get(debugFunctionName(subFunctionName)).getSubFunctionSet() == null || declfunctionSet.get(debugFunctionName(subFunctionName)).getSubFunctionSet().isEmpty()) {
						if (containaGVariable) {
							str+= functionTabs + "</ownedExtensions>\r\n";
						}
						else str+="/>\r\n";
					}
					else {
						//				System.out.println("-----------------" +FileValue);
						if (FileValue > 1) {
							//if the file have a value = 2, we inject subfunctions by calling subfunction method
							//							System.out.println("subset ::::" + subFunctionName + declfunctionSet.get(debugFunctionName(subFunctionName)).getSubFunctionSet() );
							if (!containaGVariable) str += ">\r\n";
							for (String subSubFunctionName : declfunctionSet.get(debugFunctionName(subFunctionName)).getSubFunctionSet()) {
								str += injectSubFunction(subSubFunctionName, functionTabs);
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
		else str += "/>\r\n";
//		str+= functionTabs + "<ownedExtensions/>\r\n";
		return str;
	}

	public static String injectSoftwareBus (String FunctionName, String tabs) {
		
		String str = "";
		String globalVariablTabs = tabs+"\t";
		// to delete after bus names
		Boolean StopBus1 = false;
		Boolean StopBus2 = false;
		// we check across the global variable set in order to find GV parent names equal to the current function 
		for (String variableName : globalvariablesSet.keySet()) {
		if (StopBus1 && StopBus2) break;
		if (globalvariablesSet.get(variableName).get(0).equals(FunctionName)) {
				// we check the GV mode read/write
				if(globalvariablesSet.get(variableName).get(1) == "read"&& !StopBus1 ) {
					str += globalVariablTabs + "<SofwareBusInputPort_set xsi:type=\"deployment:SoftwareBusInputPort\" id=\"0fd48a77-c477-4535-b51b-d7bdc5f53942\" bustType=\"Paparazzi ABI\"/>\r\n";
					StopBus1 = true;
				}
				else if(globalvariablesSet.get(variableName).get(1) == "write" && !StopBus2){
					str +=  globalVariablTabs + "<SofwareBusOutputPort_set xsi:type=\"deployment:SoftwareBusOutputPort\" id=\"9e4a061e-378a-412e-8adb-e9f94a22ed47\" bustType=\"ROS2\"/>\r\n";
					StopBus2 = true;
				}
			}
		}
		return str;
	}
	
	public static Boolean isFunctionHaveGlobalVariable (String FunctionName) {
		Boolean flag = false;
		for (String variableName : globalvariablesSet.keySet()) {
			if (globalvariablesSet.get(variableName).get(0).equals(FunctionName)) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}
	
	public static int getFileValue (String Name) {
		Integer value;
		return value = Breakdown.get(functionFileMap.get(Name)) ;
	}
	
	public static String debugFunctionName(String Functionname) {
		// function/1 => function
		// function use to remove /number from called function 
//		System.out.println(Functionname);
			if (Functionname.contains("/")) {
				Functionname = Functionname.split("/",2)[0];
//				System.out.println(Functionname);
			}
			return Functionname;
		}
	public static int getFileValue1 (String FunctionName) {
		if (Breakdown.containsKey((functionFileMap.get(debugFunctionName(FunctionName))))) {
			if (functionFileMap.get(debugFunctionName(FunctionName)).contains("sys_time")) {
				return 2;
			}
			if (functionFileMap.get(debugFunctionName(FunctionName)).contains("main") ) {
				return 2;
			}
			return Breakdown.get(functionFileMap.get(debugFunctionName(FunctionName)));
		}
		else 
			return 0;
	}
}