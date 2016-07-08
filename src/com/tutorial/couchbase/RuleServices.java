package com.tutorial.couchbase;

import java.util.List;

import com.directv.edmcs.jaxb.rules.Group;

/**
 * @author hunghm5
 *
 */
public interface RuleServices {
	/**
     * Update RuleCache reading available full and delta rule files
     * @return
     */
	public boolean update();
	
	public Rule getRule(String ruleName);
	public List<Rule> getAllRule();
	public List<Group> getAllGroup();
	public Group getGroup(String groupName);
}
