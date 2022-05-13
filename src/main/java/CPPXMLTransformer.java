import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import businessStructure.AADLFunction;
import businessStructure.AADLThread;
import helpers.FileHelper;

public class CPPXMLTransformer
{

    public static void main(String[] args) throws Exception {

        Scanner useDelimiter = new Scanner(new File("target/generated-sources/antlr4/input.txt")).useDelimiter("\\Z");
		String content = useDelimiter.next();
        System.out.println( "File:\n" + content + "\n\n");
        
        ANTLRInputStream input = new ANTLRInputStream( content );
        
        CPP14Lexer lexer = new CPP14Lexer(input);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        CPP14Parser parser = new CPP14Parser(tokens);
        
        CPPCustomListener listener = new CPPCustomListener();
        
        parser.addParseListener(listener);
        
        
        ParseTree tree = parser.translationUnit();

//        System.err.println(listener.parsingResult);
        
        
        // Capella file accessing and modifying
        
        // get the XML Tags 
        
        String XMLTags = getXMLfromThreadDataStructure(listener.threadSet);
//        
//        try {
//		      FileWriter myWriter = new FileWriter("Z:\\capella-1.4.2.latest\\capella\\eclipse\\workspace\\test\\test.melodymodeller");
//		      
//		      // write inside the file
//		      myWriter.write(XMLTags);
//		      myWriter.close();
//		      System.out.println("Successfully wrote to the file.");
//	    } catch (IOException e) {
//		      System.out.println("An error occurred.");
//		      e.printStackTrace();
//	    }
//        
        String FileName = "Z:\\capella-1.4.2.latest\\capella\\eclipse\\workspace\\test-papparrazi\\test-papparrazi.melodymodeller";
        String startpoint = "\"deployment:AADLProcess\"";
        FileHelper.insertStringIntoFile(FileName, startpoint, XMLTags);
        
        
//        System.err.println(listener.functionSet);
//        System.err.println(listener.threadSet);
        System.err.println(getXMLfromThreadDataStructure(listener.threadSet));
        
        System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n"); 
    }
    
    private static String getXMLfromThreadDataStructure(HashMap<String, AADLThread>  threadSet) {
    	String str = "";
    	final String functionTabs = "\t";
//    	String openthreadXMLTag = "<AADLThreadSet xsi:type=\"deployment:AADLThread\" id=\"_T_SWgNA7Eey3zKUaIaFcdg\"\r\n"
//    			+ "                name=\"Thread\" timeBudgetUnit=\"ms\">\r\n"
//    			+ "              <ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
//    			+ "                  name=\"sys_time_thread_main\"/>\r\n";
//    			
//		String closeThreadXMLTag = "              <header xsi:type=\"deployment:AADLThreadHeader\" id=\"_T_SWgdA7Eey3zKUaIaFcdg\"/>\r\n"
//    			+ "            </AADLThreadSet>\n";
    	
    	for (Entry<String, AADLThread>  threadEntry : threadSet.entrySet()) {
    		str += "<AADLThreadSet xsi:type=\"deployment:AADLThread\" id=\"_T_SWgNA7Eey3zKUaIaFcdg\"\r\n"
        			+ "                name=\"" + threadEntry.getKey() + "\" timeBudgetUnit=\"ms\">\r\n";
        			
        	for (Entry<String, AADLFunction> ThreadFunctionSet : threadEntry.getValue().getThreadFunctionSet().entrySet()) {
        		if(ThreadFunctionSet.getValue().getSubFunctionSet().isEmpty())
        		str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
			 		+ functionTabs + functionTabs +"name=\"" + ThreadFunctionSet.getKey() + "\"/>\r\n";
        		else {
        			str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
        			 		+ functionTabs + functionTabs +"name=\"" + ThreadFunctionSet.getKey() + "\">\r\n";
        				for (String subFunctionName : ThreadFunctionSet.getValue().getSubFunctionSet()) {
        					str += functionTabs + "<ownedExtensions xsi:type=\"deployment:AADLFunction\" id=\"baaec95a-deed-4d91-9998-c10e6aec4ad2\"\r\n"
        					 		+ functionTabs + functionTabs +"name=\"" + subFunctionName + "\"/>\r\n";
						}
        			str += "</ownedExtensions>\n";
        		}
			}
        			
        			
        			
    
    		str += "<header xsi:type=\"deployment:AADLThreadHeader\" id=\"_T_SWgdA7Eey3zKUaIaFcdg\"/>\r\n"
        			+ "            </AADLThreadSet>\n";
		}
    	
    	return str;
    }

}