package com.sunil.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileUtils {
	/**
	 * This method write a string to file
	 * 
	 * @param destinationFileName
	 * @param str
	 * @throws IOException 
	 */
	public static void writeToFile(String destinationFileName, String str) throws IOException{
		File destinationFile = new File(destinationFileName);

		if (!destinationFile.exists()) {
			destinationFile.createNewFile();
		} else {
			destinationFile.delete();
			destinationFile.createNewFile();
		}

		PrintWriter pw = new PrintWriter(new FileWriter(destinationFileName));
		pw.println(str);
		pw.close();
	}
	
	/**
	 * The method get line set from a file
	 * @param file
	 */
	public static Set<String> getLineSet(File file) throws FileNotFoundException{
		Set<String> lineSet = new HashSet<String>(); 
		
		Scanner scanner = new Scanner(file);
		
		String lineText;
		while (scanner.hasNextLine())
		{
			lineText = scanner.nextLine();
			if (lineText != null && !lineText.trim().isEmpty()) {
				lineSet.add(lineText.trim());
			}
		}
		scanner.close();
		
		return lineSet;
	}
}
