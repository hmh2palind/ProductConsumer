package com.sunil.file.reader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ContentConstants {
	protected static final String FILE_NOT_FOUND = "Check the file name or path";
	
	protected static final String INPUT_DIR = "D:\\opt\\dtv\\guidelisting_share\\Secondary_XML\\";
	protected static final String OUTPUT_DIR = "D:\\opt\\dtv\\guidelisting_share\\";
	protected static final String FILE_LARGE = "secondary-pgwslisting_full-20150515_114000_000.xml";
	protected static final String FILE_VERY_LARGE = "secondary-pgwslisting_full-20150526_062306_755.xml";
	
	protected static final String sourcefileName_0 = "C:\\Users\\hunghm5\\Desktop\\Small Program\\temp_edmrec_movies_tv.xml";
	protected static final String sourcefileName_1 = "D:\\opt\\dtv\\PGWS_files\\DLS_file\\secondary-pgwslisting_full-20150403_063442_479.xml";
//	protected static final String sourcefileName_1 = "D:\\opt\\dtv\\PGWS_files\\DLS_file\\secondary-pgwslisting_full-20150403_063442_479_test.xml";
	protected static final String sourcefileName_2 = "D:\\opt\\dtv\\guidelisting_share\\Secondary_XML\\secondary-pgwslisting_full-20150526_062306_755.xml";
	protected static final String sourcefileName_3 = "/opt/dtv/guidelisting_share/Secondary_XML/secondary-pgwslisting_full_restart-20150407_171738_726.xml";
	protected static final String sourcefileName_4 = "D:\\opt\\dtv\\PGWS_files\\DLS_file\\secondary-pgwslisting_full_restart-20150414_050406_661.xml";
	
	protected static final String destinationFileName = "D:\\opt\\dtv\\PGWS_files\\DLS_file\\Sunil_version\\finalOutput.txt";
	
	private static final String[] _openTags = new String[] {"<channels>", "<programs>", "<schedules>", "<providers>", "<contentDetails>"};
	private static final String[] _closeTags = new String[] {"</channels>", "</programs>", "</schedules>", "</providers>", "</contentDetails>"};
	
	protected static final Set<String> openTags = new HashSet<String>(Arrays.asList(_openTags));
	protected static final Set<String> closeTags = new HashSet<String>(Arrays.asList(_closeTags));
	
}
//
//enum OpenTag{
//	CONTENT_DETAILS	("<contentDetails>"),
//	PROGRAMS		("<programID>"),
//	SCHEDULES		("<schedules>"),
//	CHANNELS 		("<channelID>"),
//	PROVIDERS		("<providers>");
//	
//	private final String tagName;
//
//	OpenTag(String tagName) {
//        this.tagName = tagName;
//    }
//    
//    public String getTag() {
//        return this.tagName;
//    }
//    
//}
//
//enum CloseTag{
//	CONTENT_DETAILS	("</contentDetails>"),
//	PROGRAMS		("</programID>"),
//	SCHEDULES		("</schedules>"),
//	CHANNELS 		("</channelID>"),
//	PROVIDERS		("</providers>");
//	
//	private final String tagName;
//
//	CloseTag(String tagName) {
//        this.tagName = tagName;
//    }
//    
//    public String getTag() {
//        return this.tagName;
//    }
//}