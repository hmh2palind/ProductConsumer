package com.sunil.file.reader;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ContentRetrieverTest {
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
//		String tmsId = "EP000009991992";
//		String parentTmsId = "SH000002020121";
//		String parentTmsId = "SH000002";
//		String tmsId = "EP016130160013";
//		String parentTmsId = "";
//		String parentTmsId = "abdcalksdfsdfsdlfk2342342";

//		String providerId = "772177118";
//		String providerId = "1629352832";
//		String providerId = "";
		
		String parentTmsId = "_SH016130160000";
//		String tmsId = "EP016130160013";
//		String tmsId = "EP016130160018";
//		String providerId = "1657160769";
		String[] providerId = {"136798407", "1657160769"};
		
		String tmsId = "MV005334960000";
		
		FullFileHandler hander = new FullFileHandler(tmsId);
		
		ContentRetriever retriever = new ContentRetriever(hander, true);
		
		long startTime = System.currentTimeMillis();
		retriever.retrive();
		System.out.printf("Time toal %d(s)", (System.currentTimeMillis()-startTime)/1000);
	}
}
