package com.sunil.file.reader;

import static com.sunil.file.reader.FullFileTags.CHANNEL;
import static com.sunil.file.reader.FullFileTags.CONTENT;
import static com.sunil.file.reader.FullFileTags.MATERIAL;
import static com.sunil.file.reader.FullFileTags.PROGRAM;
import static com.sunil.file.reader.FullFileTags.PROVIDER;
import static com.sunil.file.reader.FullFileTags.SCHEDULE;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DeltaFileHandler extends DefaultHandler {
	private StringBuilder channelBuilder 	= new StringBuilder();
	private StringBuilder programBuilder 	= new StringBuilder();
	private StringBuilder scheduleBuilder 	= new StringBuilder();
	private StringBuilder contentBuilder 	= new StringBuilder();
	private StringBuilder providerBuilder 	= new StringBuilder();
	private StringBuilder materialBuilder	= new StringBuilder();
	
	private StringBuilder tempBuilder = null;
	
	boolean desiredElementFound = false;
	private boolean isCheckProviderTag = false;
	
//	public DeltaFileHandler(String tmsId) {
//		if (tmsId != null && !tmsId.isEmpty())
//			this.tmsId = ">" + tmsId + "<";
//		
//		channelBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n");
//		channelBuilder.append("<PGWSFullListing>").append("\n");
//		channelBuilder.append("	<fullListing>").append("\n");
//	}

	@Override
	public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
		
		if (CHANNEL.equals(qName)) {
			if (!isCheckProviderTag) {				
				tempBuilder = new StringBuilder("		");
				tempBuilder.append("<referencedChannelsArray>");
				desiredElementFound = true;
			}
		}
		
		else if (PROGRAM.equals(qName)) {
			tempBuilder = new StringBuilder("		");
			tempBuilder.append("<referencedProgramsArray>");
			desiredElementFound = true;
		}
		
		else if (SCHEDULE.equals(qName)) {
			tempBuilder = new StringBuilder("		");
			tempBuilder.append("<updatedSchedulesArray>");
			desiredElementFound = true;
		}
		
		else if (CONTENT.equals(qName)) {
			tempBuilder = new StringBuilder("		");
			tempBuilder.append("<contentDetailArray>");
			desiredElementFound = true;
		}

		else if (PROVIDER.equals(qName)) {
			tempBuilder = new StringBuilder("		");
			tempBuilder.append("<nlpgwsProvider>");
			
			desiredElementFound = true;
			isCheckProviderTag = true; //providers tag has channels tags
		}
		
		else if (MATERIAL.equals(qName)) {
			tempBuilder = new StringBuilder("	");
			tempBuilder.append("<nlpgwsTitleInfo>");
			desiredElementFound = true;
		} 
		
		else if (desiredElementFound == true) {
			tempBuilder.append("<").append(qName).append(">");
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if (CHANNEL.equals(qName)) {
			if (!isCheckProviderTag) {
				tempBuilder.append("</referencedChannelsArray>");
				channelBuilder.append(tempBuilder).append("\n");
				
				desiredElementFound = false;
				tempBuilder = null;
				
//				if (checkContain(tempBuilder, channelIdSet)) {
//					channelBuilder.append(tempBuilder).append("\n");
//					
//					if (++channelCount == channelIdSet.size()) {
//						start = false;
//					}
//					tempBuilder = null;
//				}
			}
		}
		
		else if (PROGRAM.equals(qName)) {
//			tempBuilder.append("</").append(qName).append(">");
			tempBuilder.append("</referencedProgramsArray>");
			programBuilder.append(tempBuilder).append("\n");
			
			desiredElementFound = false;
			tempBuilder = null;
			
//			if (tempBuilder.indexOf(tmsId) > -1 || tempBuilder.indexOf(parentTmsId) > -1) {
//				finalBuilder.append(tempBuilder).append("\n");
//				
//				if (programIdSet == null)
//					programIdSet = calculateSetValue(tempBuilder, OPEN_PROGRAM_ID, CLOSE_PROGRAM_ID);
//				else 
//					programIdSet.addAll(calculateSetValue(tempBuilder, OPEN_PROGRAM_ID, CLOSE_PROGRAM_ID));
//				
//				tempBuilder = null;
//			}
		}

		else if (SCHEDULE.equals(qName)) {
			tempBuilder.append("</updatedSchedulesArray>");
			scheduleBuilder.append(tempBuilder).append("\n");
			
			desiredElementFound = false;
			tempBuilder = null;
			
//			if (checkContain(tempBuilder, programIdSet)) {
//				finalBuilder.append(tempBuilder).append("\n");
//				
//				if (channelIdSet == null) {
//					channelIdSet = calculateSetValue(tempBuilder, OPEN_CHANNEL_ID, CLOSE_CHANNEL_ID);
//				}
//				else {
//					channelIdSet.addAll(calculateSetValue(tempBuilder, OPEN_CHANNEL_ID, CLOSE_CHANNEL_ID));
//				}
//				tempBuilder = null;
//			}
		}
		
		else if (CONTENT.equals(qName)) {
			tempBuilder.append("</contentDetailArray>");
			contentBuilder.append(tempBuilder).append("\n");
			
			desiredElementFound = false;
			tempBuilder = null;
			
//			if (tempBuilder.indexOf(tmsId) > -1 || tempBuilder.indexOf(parentTmsId) > -1) {
//				finalBuilder.append(tempBuilder).append("\n");
//				
//				providerIdSet = calculateSetValue(tempBuilder, OPEN_PROVIDER_ID, CLOSE_PROVIDER_ID);
//				if (providerIdSet == null || providerIdSet.isEmpty()) //content is Linear or Upcoming
//					start = false;
//				else
//					materialCount = calculateSetValue(tempBuilder, OPEN_MATERIAL_ID, CLOSE_MATERIAL_ID).size();
//				
//				tempBuilder = null;
//			}
		}
		
		else if (PROVIDER.equals(qName)) {
			tempBuilder.append("</nlpgwsProvider>");
			providerBuilder.append(tempBuilder).append("\n");
			
			isCheckProviderTag = false;
			
			desiredElementFound = false;
			tempBuilder = null;
			
//			if (providerIdSet != null && !providerIdSet.isEmpty()) {
//				if (checkContain(tempBuilder,providerIdSet)) {
//					finalBuilder.append(tempBuilder).append("\n");
//					tempBuilder = null;
//				}
//			}
		}
		
		else if (MATERIAL.equals(qName)) {
			tempBuilder.append("</nlpgwsTitleInfo>");
			materialBuilder.append(tempBuilder).append("\n");
			
			desiredElementFound = false;
			tempBuilder = null;
			
//			if (tempBuilder.indexOf(tmsId) > -1 || tempBuilder.indexOf(parentTmsId) > -1) {
//				finalBuilder.append(tempBuilder).append("\n");
//				
//				if (++count == materialCount) {
//					start = false;
//				}
//				
//				tempBuilder = null;
//			}
		}
		
		else if (desiredElementFound == true) {
			tempBuilder.append("</").append(qName).append(">");
		}
		
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (desiredElementFound) {
			String temp = new String(ch, start, length).replace("&", "&amp;");
			tempBuilder = tempBuilder.append(temp);
		}
	}
	
//	public String getFinalOutput() {
//		finalBuilder.append("	</fullListing>").append("\n");
//		finalBuilder.append("</PGWSFullListing>");
//		
//		return channelBuilder.append(finalBuilder).toString();
//	}

	public StringBuilder getChannelBuilder() {
		return channelBuilder;
	}

	public StringBuilder getProgramBuilder() {
		return programBuilder;
	}

	public StringBuilder getScheduleBuilder() {
		return scheduleBuilder;
	}

	public StringBuilder getContentBuilder() {
		return contentBuilder;
	}

	public StringBuilder getProviderBuilder() {
		return providerBuilder;
	}

	public StringBuilder getMaterialBuilder() {
		return materialBuilder;
	}
	
	public StringBuilder getProgramScheduleBuilder() {
		return new StringBuilder().append(scheduleBuilder).append(programBuilder);
	}
}
