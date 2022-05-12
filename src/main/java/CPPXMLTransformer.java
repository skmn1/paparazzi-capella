import java.io.File;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

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
//        try {
//		      FileWriter myWriter = new FileWriter("Z:\\capella-1.4.2.latest\\capella\\eclipse\\workspace\\test\\test.melodymodeller");
//		      
//		      // write inside the file
//		      myWriter.write(listener.parsingResult);
//		      myWriter.close();
//		      System.out.println("Successfully wrote to the file.");
//	    } catch (IOException e) {
//		      System.out.println("An error occurred.");
//		      e.printStackTrace();
//	    }
        
        String FileName = "Z:\\capella-1.4.2.latest\\capella\\eclipse\\workspace\\test\\test.melodymodeller";
        String startpoint = "\"deployment:AADLProcess\"";
//        FileHelper.insertStringIntoFile(FileName, startpoint, listener.parsingResult);
        
        
        
        System.err.println(listener.functionSet);
//        System.err.println(listener.threadSet);
        
        System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n"); 
    }

}