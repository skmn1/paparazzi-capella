package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
			System.out.println("Successfully written to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

	public static Map<String, Integer> convertFileToMap(File file) {
		HashMap<String, Integer> fileToMap = new HashMap<>();

		try {  
			//the file to be opened for reading  
			FileInputStream fis=new FileInputStream(file);       
			Scanner sc=new Scanner(fis);    //file to be scanned  
			//returns true if there is another line to read  
			while(sc.hasNextLine())  {  
				System.out.println(sc.nextLine());      //returns the line that was skipped 
				fileToMap.put(sc.nextLine().split(",")[0], Integer.parseInt(sc.nextLine().split(",")[1]));
			}  
			sc.close();     //closes the scanner  
		}  
		catch(IOException e) {  
			e.printStackTrace();  
		}  


		return fileToMap;

	}



	public static String getFolderTextContent(final File folder) throws FileNotFoundException {
		String allFilesContent = "";
		String asterisks = "     ****************************     ";
		String fileName = "";

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				getFolderTextContent(fileEntry);
			} else {
				fileName = "\n\n//" + asterisks + fileEntry.getName() + asterisks +"\n\n";
				System.err.println(fileEntry.getName());
				@SuppressWarnings("resource")
				Scanner useDelimiter = new Scanner(fileEntry).useDelimiter("\\Z");
				String content = useDelimiter.next();
				allFilesContent += fileName + content;
				//	    		allFilesContent +=  content;
				//	            System.out.println(  content + "\n\n");
			}
		}
		return allFilesContent;
	}

	public static boolean fileContainsFunction(File file, String functionName) throws FileNotFoundException {
		String content  = "";

		System.err.println(file.getName());
		@SuppressWarnings("resource")
		Scanner useDelimiter = new Scanner(file).useDelimiter("\\Z");
		content = useDelimiter.next();

		return content.contains(functionName);
	}
	
	public static String getFileTextContent(File file) throws FileNotFoundException {
		String content  = "";
		
		System.err.println(file.getName());
		@SuppressWarnings("resource")
		Scanner useDelimiter = new Scanner(file).useDelimiter("\\Z");
		content = useDelimiter.next();
		
		return content;
	}
	
	
	




}
