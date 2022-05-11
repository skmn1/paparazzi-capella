package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHelper {
	
	public static void insertStringIntoFile (String fileName, String startPoint, String text) {
		
		String firstPart = "", lastPart = "";
		String aadlprocessLine = startPoint;
		
		// read from the file first
		File file = new File(fileName);
		
		try {
		      Scanner myReader = new Scanner(file);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        firstPart += data+"\n";
		        
		        if (data.contains(aadlprocessLine))
					break;
		      }
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        lastPart += data+"\n";
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		
		 try {
		      FileWriter myWriter = new FileWriter(file);
		      
		      // write inside the file
		      myWriter.write(firstPart);
		      myWriter.write(text);
		      myWriter.write(lastPart);
		      myWriter.flush();
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
	    }
		
		
	}

}
