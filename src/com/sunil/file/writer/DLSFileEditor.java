package com.sunil.file.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class DLSFileEditor {

	public static void main(String[] args) {
		Calendar calendarStart = Calendar.getInstance();
		
		try {
			final String contentFileName = "D:\\opt\\dtv\\PGWS_files\\DLS_file\\content.txt";
			final String mainfileName = "D:\\opt\\dtv\\PGWS_files\\DLS_file\\secondary-pgwslisting_full-20150403_063442_479.xml.backup";
			
//			final String contentFileName = "/users/a09244/Desktop/PGWS_files/DLS_file/source.txt";
//			final String mainfileName = "/users/a09244/Desktop/PGWS_files/DLS_file/test.txt";
			
			final String destfileName = "D:\\opt\\dtv\\PGWS_files\\DLS_file\\secondary-pgwslisting_full-20150403_063442_479.xml";
			
			
//			final int lineNumber = 8;
			final int lineNumber = 48253063;
			
			String content = copySourceFileContent(contentFileName);

			File file = new File(mainfileName);

			if (!file.exists()) {
				System.err.println("Check the file name or path");
				return;
			} 

			PrintWriter pw = null; 
			BufferedReader br = null;

			br = new BufferedReader(new FileReader(mainfileName));
			pw =  new PrintWriter(new FileWriter(destfileName));

			String line;
			double lineCounter = 0;
			while ((line = br.readLine()) != null) {
				lineCounter++;
				
				if (lineCounter == lineNumber + 1) {
					pw.println(content);
				}
					
				pw.println(line);
			}

			br.close();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		float seconds = (System.currentTimeMillis() - calendarStart.getTime().getTime()) / 1000f;
		System.err.println("=================================\nCompleted " + "and took " + seconds + "  seconds") ;
	}

	private static String copySourceFileContent(String sourceFileName) { 

		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader( sourceFileName ));

			String line;
			while ((line = br.readLine()) != null) {
				stringBuffer.append(line + "\n");
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.err.println(stringBuffer);
		
		return stringBuffer.toString();
	}
	
}

