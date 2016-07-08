package com.sunil.file.reader;

import static com.sunil.file.reader.ContentConstants.FILE_LARGE;
import static com.sunil.file.reader.ContentConstants.FILE_NOT_FOUND;
import static com.sunil.file.reader.ContentConstants.INPUT_DIR;
import static com.sunil.file.reader.ContentConstants.OUTPUT_DIR;
import static com.sunil.file.reader.ContentConstants.closeTags;
import static com.sunil.file.reader.ContentConstants.destinationFileName;
import static com.sunil.file.reader.ContentConstants.openTags;
import static com.sunil.file.reader.ContentConstants.sourcefileName_1;
import static com.sunil.file.reader.FileUtils.writeToFile;
import static com.sunil.file.reader.FullFileTags.CHANNEL_WRAPPER;
import static com.sunil.file.reader.FullFileTags.CLOSE_CHANNEL_ID;
import static com.sunil.file.reader.FullFileTags.CONTENT_DETAIL_WRAPPER;
import static com.sunil.file.reader.FullFileTags.DATA_SET_FOLDER;
import static com.sunil.file.reader.FullFileTags.NLPGWS_PROVIDER;
import static com.sunil.file.reader.FullFileTags.NLPGWS_TITLE_INFO;
import static com.sunil.file.reader.FullFileTags.OPEN_CHANNEL_ID;
import static com.sunil.file.reader.FullFileTags.SCHEDULE_WRAPPER;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ContentRetriever {
	private FullFileHandler fullFileHandler;
	private DeltaFileHandler deltaFileHandler;
	
	private boolean isCreateDeltaFile;
	
	
	
	ContentRetriever(FullFileHandler handler){
		this.fullFileHandler = handler;
		this.isCreateDeltaFile = false;
	}
	
	ContentRetriever(FullFileHandler handler, boolean isCreateDeltaFile){
		this.fullFileHandler = handler;
		this.isCreateDeltaFile = isCreateDeltaFile;
	}
	
	protected void retrive() throws IOException, ParserConfigurationException, SAXException {
		// 0. create parser
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		
		// 1. Read xml file
		File file = new File(INPUT_DIR + FILE_LARGE);
		if (!file.exists()) {
			System.err.println(FILE_NOT_FOUND);
			return;
		}

		// 2. parse file
//		 parseDocument(fullFileHandler, file);

		// 2.1 Parse channel
		if (fullFileHandler.getChannelIdSet() != null && !fullFileHandler.getChannelIdSet().isEmpty()) {
			fullFileHandler.setStart(true);
			
			Set<String> tempChannelSet = new HashSet<String>();
			Iterator<String> it = fullFileHandler.getChannelIdSet().iterator();
			while (it.hasNext()) {
				tempChannelSet.add(OPEN_CHANNEL_ID + it.next() + CLOSE_CHANNEL_ID);
			}
			
			fullFileHandler.setChannelIdSet(tempChannelSet);
			
			parser.parse(file, fullFileHandler);
		}
		
		// 3. get final output
		String finalOutput = fullFileHandler.getFinalOutput();

		// 4. write final output to file
//		 writeToFile(OUTPUT_DIR + FILE_LARGE, finalOutput);

		
		// 5. create delta file
		if (isCreateDeltaFile){
			deltaFileHandler = new DeltaFileHandler();
			file = new File(OUTPUT_DIR + FILE_LARGE);
			InputStream inputStream = new FileInputStream(file);
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");
			
			parser.parse(is, deltaFileHandler);
			
			StringBuilder tempBuilder = deltaFileHandler.getChannelBuilder();
			
			// TODO parse system time.
			String prefix = "secondary-pgwslisting_delta-20150803_104500_";
			
			// is linear
			if (isNotEmpty(tempBuilder)) {
				createDeltaFile(CHANNEL_WRAPPER, tempBuilder, OUTPUT_DIR + prefix + "100");
				System.out.println(tempBuilder);
			}
			
			tempBuilder = deltaFileHandler.getProgramScheduleBuilder(); 
			if (isNotEmpty(tempBuilder)){
				createDeltaFile(SCHEDULE_WRAPPER, tempBuilder, OUTPUT_DIR + prefix + "300");
				System.out.println(tempBuilder);
			}
			
//			tempBuilder = deltaFileHandler.getProgramBuilder(); 
//			if (isNotEmpty(tempBuilder)) {
//				createDeltaFile(SCHEDULE_WRAPPER, tempBuilder, prefix + "300");
//				System.out.println(tempBuilder);
//			}
			
			
			// is nolinear, streaming, ott, shadow
			tempBuilder = deltaFileHandler.getProviderBuilder(); 
			if (isNotEmpty(tempBuilder)) 
			{
				createDeltaFile(NLPGWS_PROVIDER, tempBuilder, OUTPUT_DIR + prefix + "200");
				System.out.println(tempBuilder);
			}
			
			tempBuilder = deltaFileHandler.getMaterialBuilder(); 
			if (isNotEmpty(tempBuilder)) {
				createDeltaFile(NLPGWS_TITLE_INFO, tempBuilder, OUTPUT_DIR + prefix + "400");
				System.out.println(tempBuilder);
			}
			
			tempBuilder = deltaFileHandler.getContentBuilder(); 
			if (isNotEmpty(tempBuilder))
			{
				createDeltaFile(CONTENT_DETAIL_WRAPPER, tempBuilder, OUTPUT_DIR + prefix + "500");
				System.out.println(tempBuilder);
			}
		}
		
		System.out.println("Completed");
	}

	private void createDeltaFile(String nameFile,
			StringBuilder strBuilder, String outputFile) {
		File file = null;
		Scanner scanner = null;
		StringBuilder tempBuilder = new StringBuilder();
		try {
			file = new File(DATA_SET_FOLDER + "//" + nameFile);
			scanner = new Scanner(file);
			
			String line;
			while (scanner.hasNext()) {
				line = scanner.nextLine();
				if ("<!-- TODO -->".equals(line.trim())) {
					tempBuilder.append("\n").append(strBuilder);
				}
				else {
					tempBuilder.append("\n").append(line);
				}
			}
			
			writeToFile(outputFile, tempBuilder.toString());
			
		} catch (FileNotFoundException e) {
			System.out.printf("File not found exception: %s", e.getMessage());
		} catch (IOException e) {
			System.out.printf("IO Exception: %s", e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		
	}

	private boolean isNotEmpty(StringBuilder builder) {
		if (builder != null && builder.length() != 0)
			return true;
		
		return false;
	}

	protected void retrive(Set<String> target) throws IOException{
		long startTime = System.currentTimeMillis();
		// 1. Read xml file
		File file = new File(sourcefileName_1);
		if (!file.exists()) {
			System.err.println("Check the file name or path");
			return;
		}
		
		// 2. parse file

		// 3. get final output
		String finalOutput = parseDocument(new Scanner(file), target);

		// 4. write final output to file
		writeToFile(destinationFileName, finalOutput);
		System.out.println("Complete with time total : " + (System.currentTimeMillis() - startTime)/1000);
	}

	private String parseDocument(Scanner scanner, Set<String> target) {
		String lineText;
		boolean canRead = false;
		StringBuilder finalOutput = new StringBuilder();
		StringBuilder tempOutput = new StringBuilder();
		while (scanner.hasNextLine())
		{
			lineText = scanner.nextLine().trim();
			if (openTags.contains(lineText)){
				tempOutput.append(lineText + "\n");
				canRead = true;
			}
			
			else if (closeTags.contains(lineText)) {
				tempOutput.append(lineText + "\n");
				if (isSetContain(target, tempOutput)){
					finalOutput.append(tempOutput.toString());
				}
				canRead = false;
				tempOutput = new StringBuilder();
			}
			
			else if (canRead) {
				tempOutput.append("         " + lineText.replace("&", "&amp;") + "\n");
			}
		}
		scanner.close();
		
		return finalOutput.toString();
	}

	private boolean isSetContain(Set<String> target, StringBuilder strBuilder) {
		for (String str : target) {
			if (strBuilder.indexOf(str) > -1){
				return true;
			}
		}
		
		return false;
	}
}

