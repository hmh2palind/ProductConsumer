package com.tutorial.couchbase;

import org.apache.log4j.Logger;

public class CouchBaseTest {
	static Logger logger = Logger.getLogger(CouchBaseTest.class);
	
	public static void main(String[] args) {
		DefaultCouchbase instance = DefaultCouchbase.getInstance();
		
		RuleFileProcess fileProcess = RuleFileProcess.getInstance();
		fileProcess.setFileDir("C:\\Users\\hunghm5\\Desktop\\Small Program\\SunilProject\\recommend_snaps/");
		fileProcess.setRuleDeltaFileNamePrefix("listbuilder-rulelisting_delta");
		fileProcess.setRuleFullFileNamePrefix("listbuilder-rulelisting_full");
		
		RuleServices ruleServices = RuleServicesImpl.getInstance();
//		if (ruleServices.update())
//			System.out.println("Job is updating ...");
		
		String ruleName = "ManualPosition";
		ruleServices.getRule(ruleName);
		
		// Disconnect from the cluster
		instance.disconnect();
		
		Rule rule = unmarshall("rule1", Rule.class);
	}
	
	public static <T> T unmarshall(String content, Class<T> clazz) {
		try {
			return null;
		} catch (Exception e) {
			logger.error("Parse json document error " + clazz + ": \n"
					+ content, e);
		}
		return null;
	}
}
