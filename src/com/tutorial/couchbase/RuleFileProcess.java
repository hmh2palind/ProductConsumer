package com.tutorial.couchbase;

import java.io.File;
import java.util.Comparator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.directv.edmcs.jaxb.rules.ListBuilderDeltaListing;
import com.directv.edmcs.jaxb.rules.ListBuilderFullListing;
import com.directv.edmcs.jaxb.rules.ListbuilderConfig;

/**
 * @author hunghm5
 *
 */
public class RuleFileProcess {
	private static Logger logger = Logger.getLogger(RuleFileProcess.class);
	
    private static final String FILE_TIMESTAMP_FORMAT = "-yyyyMMdd_HHmmss_SSS";
    private static final String FILENAME_EXT = ".xml";
    
	private String ruleFilesDir;
	private String ruleFullFileNamePrefix;
	private String ruleDeltaFileNamePrefix;
	
	private boolean fullFileProcessed = false;
	
	private static RuleFileProcess instance;
	private static Unmarshaller listbuilderConfigUnmarshaller;
	
    private final Comparator<String> fileNameComparator =
            new Comparator<String>()
    {
        public int compare(String fn1, String fn2)
        {
            // Full files should appear first, and be in descending order (latest full file becomes the first one).
            // Delta files should follow full files, and appear in ascending order (from older to newer).
            if (fn1.startsWith(ruleFullFileNamePrefix) && !fn2.startsWith(ruleFullFileNamePrefix))
                return -1;
            if (!fn1.startsWith(ruleFullFileNamePrefix) && fn2.startsWith(ruleFullFileNamePrefix))
                return 1;
            if (fn1.startsWith(ruleFullFileNamePrefix) && fn2.startsWith(ruleFullFileNamePrefix))
                return fn2.compareTo(fn1); // sort full files in descending order
            return fn1.compareTo(fn2); // sort delta files in ascending order
        }
    }; // fileNameComparator

        
	private RuleFileProcess() {
		try
        {
            JAXBContext jaxbCtx = JAXBContext.newInstance(ListbuilderConfig.class);
            listbuilderConfigUnmarshaller = jaxbCtx.createUnmarshaller();
        }
        catch (JAXBException ex)
        {
            logger.error("RuleCache JAXBException: ", ex);
        }
	}
	
	public static RuleFileProcess getInstance() 
	{
		if (instance != null) {
			return instance;
		}
		
		synchronized (RuleFileProcess.class)
        {
            if (instance == null)
            {
                instance = new RuleFileProcess();
            }
        }
		
		return instance;
	}
	
	/*public String getNextFileName(){
		//TODO Auto-generated method stub
		return ruleDeltaFileNamePrefix + "-" + "20150610_101036_896.xml";
	}*/

	public void setFileDir(String fileDir) 
	{
		if (fileDir == null) {
			return;
		}
		
		if (fileDir.endsWith("/"))
			ruleFilesDir = fileDir;
		else 
			ruleFilesDir = fileDir + "/";
	}

	public String getNextRuleFileName()
    {
        //TODO Auto-generated method stub
    	return ruleDeltaFileNamePrefix + "-" + "20150610_101036_896.xml";
    }

	public ListBuilderFullListing getListBuilderFullListing(String ruleFileName) throws JAXBException {
		ListbuilderConfig lbConfig = getListBuiderConfig(ruleFileName);
		return lbConfig.getListBuilderFullListing();
	}
	
	public ListBuilderDeltaListing getListBuilderDeltaListing(String ruleFileName) throws JAXBException {
		ListbuilderConfig lbConfig = getListBuiderConfig(ruleFileName);
		return lbConfig.getListBuilderDeltaListing();
	}
	
	private ListbuilderConfig getListBuiderConfig(String ruleFileName) throws JAXBException {
		ListbuilderConfig lbConfig = (ListbuilderConfig)listbuilderConfigUnmarshaller.unmarshal(new File(ruleFilesDir + ruleFileName));
		return lbConfig;
	}

	public void setRuleFullFileNamePrefix(String ruleFullFileNamePrefix) {
		this.ruleFullFileNamePrefix = ruleFullFileNamePrefix;
	}

	public String getRuleFullFileNamePrefix() {
		return ruleFullFileNamePrefix;
	}
	
    public String getRuleDeltaFileNamePrefix() {
		return ruleDeltaFileNamePrefix;
	}

	public void setRuleDeltaFileNamePrefix(String ruleDeltaFileNamePrefix) {
		this.ruleDeltaFileNamePrefix = ruleDeltaFileNamePrefix;
	}

	public boolean isFullFileProcessed() {
		return fullFileProcessed;
	}

	public void setFullFileProcessed(boolean fullFileProcessed) {
		this.fullFileProcessed = fullFileProcessed;
	}

	public String getRuleFilesDir() {
		return ruleFilesDir;
	}

	public void setRuleFilesDir(String ruleFilesDir) {
		this.ruleFilesDir = ruleFilesDir;
	}
}
