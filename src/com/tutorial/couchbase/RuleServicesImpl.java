package com.tutorial.couchbase;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.couchbase.client.java.document.JsonDocument;
import com.directv.edmcs.jaxb.rules.Group;
import com.directv.edmcs.jaxb.rules.GroupSet;
import com.directv.edmcs.jaxb.rules.ListBuilderDeltaListing;
import com.directv.edmcs.jaxb.rules.ListBuilderFullListing;
import com.directv.edmcs.jaxb.rules.RuleSet;
import com.directv.edmcs.jaxb.rules.RuleType;

/**
 * @author hunghm5
 *
 */
public class RuleServicesImpl implements RuleServices {
	private static final Logger logger = Logger.getLogger(RuleServicesImpl.class);
	private static RuleServicesImpl instance;
	private RuleFileProcess ruleFile;
	private DefaultCouchbase couchBase;
	
	private RuleServicesImpl(){
		ruleFile = RuleFileProcess.getInstance();
		couchBase = DefaultCouchbase.getInstance();
	}
	
	public static RuleServicesImpl getInstance(){
		if (instance != null)
			return instance;
		
		synchronized (RuleServicesImpl.class) {
			if (instance == null) {
				instance = new RuleServicesImpl();
			}
		}
		return instance;
	}
	
	/**
     * Update RuleCache reading available full and delta rule files
     * @return
     */
	
	@Override
	public boolean update() {
		String ruleFileName = null;
		
		ListBuilderFullListing lbFullListing = null;
		ListBuilderDeltaListing lbDeltaListing = null;
		while (true) {
			ruleFileName = ruleFile.getNextRuleFileName();
			if (ruleFileName == null)
				break;

			try {
				if (ruleFileName.startsWith(ruleFile.getRuleFullFileNamePrefix())) {
					lbFullListing = ruleFile.getListBuilderFullListing(ruleFileName);
					
					if (lbFullListing != null) {
						// TODO update rules and groups
						updateRules(lbFullListing.getRuleSet());
						updateGroups(lbFullListing.getGroupSet());
					}
					
					// TODO derby logger
					logger.debug("Full rule file processed: " + ruleFileName);
				}

				//if (!ruleFile.isFullFileProcessed())
					//continue; // do not process delta files if full file was not
								// processed

				if (ruleFileName.startsWith(ruleFile.getRuleDeltaFileNamePrefix())) {
					lbDeltaListing = ruleFile.getListBuilderDeltaListing(ruleFileName);
					
					if (lbDeltaListing != null) {
						// TODO remove rules and groups
						removeRules(lbDeltaListing.getRemoveRuleName());
						removeGroups(lbDeltaListing.getRemoveGroupName());

						// TODO update rules and groups
						updateRules(lbDeltaListing.getRuleSet());
						updateGroups(lbDeltaListing.getGroupSet());
					}
					
					// TODO derby logger
					logger.debug("Full rule file processed: " + ruleFileName);
				}
			} catch (JAXBException ex) {
				logger.error("Exception parsing rule file '" + ruleFileName
						+ "': " + ex.getMessage());
				return false;
			}
		}
		
		// Update full file
        /*if (tempRulesMap != null)
            rulesMap = tempRulesMap;
        if(tempGroupsMap != null)
        	groupsMap = tempGroupsMap;*/
		
		return true;
	}

	@Override
	public Rule getRule(String ruleName) {
		JsonDocument doc = couchBase.getDoc(ruleName);
		couchBase.print(doc);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rule> getAllRule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getAllGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group getGroup(String groupName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method updates group set to couchbase server
	 * @param groupSet
	 */
	private void updateGroups(GroupSet groupSet) {
		if (groupSet == null)
			return;
		
		List<Group> groupList = groupSet.getGroup();
		if (groupList == null || groupList.isEmpty())
			return;
		
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method updates rule set to couchbase server
	 * @param ruleSet
	 */
	private void updateRules(RuleSet ruleSet) {
		if (ruleSet == null)
			return;
		
		List<RuleType> ruleTypeList = ruleSet.getRule();
		if (ruleTypeList == null || ruleTypeList.isEmpty())
			return;
		
		String ruleName = null;
		Rule rule;
		for (RuleType ruleType : ruleTypeList){
			ruleName = ruleType.getRuleName();
			if (ruleName == null || ruleName.isEmpty())
				continue;
			
			rule = new Rule(ruleType);
			couchBase.insertDoc(ruleName, rule);
		}
		// TODO Auto-generated method stub
		
	}

	private void removeGroups(List<String> removeGroupName) {
		if (removeGroupName == null || removeGroupName.isEmpty())
			return;
		
		// TODO Auto-generated method stub
		
	}

	private void removeRules(List<String> removeRuleName) {
		if (removeRuleName == null || removeRuleName.isEmpty())
			return;
		
		// TODO Auto-generated method stub
		
	}
}
