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
import helpers.FileHelper;
import sun.tools.tree.ThisExpression;

public class CPPXMLTransformer {

	public static HashMap<String,String> functionFileMap = new HashMap<String,String>();
	public static HashMap<String,Integer> fileDecompositionMap = new HashMap<String,Integer>();
	public static HashMap<String,AADLFunction> functionSet = new HashMap<String,AADLFunction>();


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

		final File folder = new File("/home/decoutd/Documents/lower");
		//    	System.out.println(FileHelper.listFilesForFolder(folder));
		String wholeFolderContent = FileHelper.getFolderTextContent(folder);


		//		String content = FileHelper.getFolderTextContent(folder);

		fileDecompositionMap = FileHelper.convertFileToMap(new File("/home/decoutd/Java/paparazzi-capella/src/main/java/paparazzi_files.csv"));
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
		}

		ANTLRInputStream input = new ANTLRInputStream( wholeFolderContent );

		CPP14Lexer lexer = new CPP14Lexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CPP14Parser parser = new CPP14Parser(tokens);

		CPPCustomListener listener = new CPPCustomListener(fileDecompositionMap);
		//		CPPFunctionFileListener listenerff = new CPPFunctionFileListener();

		parser.addParseListener(listener);

		ParseTree tree = parser.translationUnit();
		functionSet = listener.functionSet;

		String XMLTags = getXMLfromThreadDataStructure(listener.threadSet);

		String FileName = "test-papparrazi.melodymodeller";
		String startpoint = "\"deployment:AADLProcess\"";

		FileHelper.insertStringIntoFile(FileName, startpoint, XMLTags);

//		System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n"); 
		System.out.println("~~tostring");
		for (Iterator i = functionSet.keySet().iterator(); i.hasNext();) {
			Object key = i.next();
			System.out.println(key + "-- \n" +functionSet.get(key).toString() +"\n");
			
		}
			
	}

	private static String getXMLfromThreadDataStructure(HashMap<String, AADLThread>  threadSet) {
		String str = "";
		final String functionTabs = "\t";

		for (Entry<String, AADLThread>  threadEntry : threadSet.entrySet()) {
			str += injectFunction(threadEntry);

		}

		return str;
	}


	public static void setUpParsingProcess (String content) {
		ANTLRInputStream input = new ANTLRInputStream( content );

		CPP14Lexer lexer = new CPP14Lexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CPP14Parser parser = new CPP14Parser(tokens);

		CPPCustomListener listener = new CPPCustomListener(fileDecompositionMap);

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
				+ "                name=\"" + threadEntry.getKey() + "\" timeBudgetUnit=\"ms\">\r\n";
		String functionTabs = "\t";

		//		thread functions creation
		for (Entry<String, AADLFunction> ThreadFunctionSet : threadEntry.getValue().getThreadFunctionSet().entrySet()) {
			if(ThreadFunctionSet.getValue().getSubFunctionSet().isEmpty())

				//				if the function doesn't contain any subfunction then we create autoclosed xml thread function
				str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
						+ functionTabs + functionTabs +"name=\"" + ThreadFunctionSet.getKey() + "::" + functionFileMap.get(ThreadFunctionSet.getKey()) + "\"/>\r\n";
			else {
				//				if the thread function has some subfunctions
				//				we create the function and then parcour the functions by calling the injectsubfunction method
				str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
						+ functionTabs + functionTabs +"name=\"" + ThreadFunctionSet.getKey() + "::" + functionFileMap.get(ThreadFunctionSet.getKey()) + "\">\r\n";

				// the injectsubfunction must be called here

				for (String subFunctionName : ThreadFunctionSet.getValue().getSubFunctionSet()) {
					str += injectSubFunction(subFunctionName);
				}

				str += "</ownedExtensions>\n";
			}
		}

		str += "<header xsi:type=\"deployment:AADLThreadHeader\" id=\"_T_SWgdA7Eey3zKUaIaFcdg\"/>\r\n"
				+ "            </AADLThreadSet>\n";

		return str;

	}

	public static String  injectSubFunction (String subFunctionName) {
		String str = "";
		String functionTabs = "\t";

		System.err.println("subfunction name : " + subFunctionName);
		System.out.println(functionSet.get(subFunctionName));
		System.err.println(functionSet);
		if(functionSet.containsKey(subFunctionName)){
			if(functionSet.get(subFunctionName).getSubFunctionSet() == null || functionSet.get(subFunctionName).getSubFunctionSet().isEmpty())
				str += functionTabs  + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
						+ functionTabs + functionTabs +"name=\"" + subFunctionName + "\"/>\r\n";
			else {

				str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
						+ functionTabs + functionTabs +"name=\"" + subFunctionName + "\">\r\n";

				for (String subSubFunctionName : functionSet.get(subFunctionName).getSubFunctionSet()) {
					str += injectSubFunction(subSubFunctionName);
				}
				str += "</ownedExtensions>\n";
			}
		}
		return str;
	}

	public void injectX (String x) {

	}
	

	
	
	


}