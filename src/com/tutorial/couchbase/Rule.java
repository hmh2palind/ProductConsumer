package com.tutorial.couchbase;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.directv.edmcs.jaxb.rules.RuleType;

/**
 * @author hunghm5
 *
 */
public class Rule {
	@JsonProperty
	private long loadTime;
	@JsonProperty
	private long lastRunTime = 0; // last time when the rule was run
	@JsonProperty
	private boolean resultsCachingDisabled = false;
	@JsonProperty
	private Set<String> contentIds;
	
	private RuleType ruleType;
	
	public Rule() {
		// loadTime = getCurrentTimeMillis();
	}
	
	public Rule(RuleType ruleType) {
		this.ruleType = ruleType;
		// loadTime = getCurrentTimeMillis();
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}

	public long getLastRunTime() {
		return lastRunTime;
	}

	public void setLastRunTime(long lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

	public boolean isResultsCachingDisabled() {
		return resultsCachingDisabled;
	}

	public void setResultsCachingDisabled(boolean resultsCachingDisabled) {
		this.resultsCachingDisabled = resultsCachingDisabled;
	}

	public Set<String> getContentIds() {
		return contentIds;
	}

	public void setContentIds(Set<String> contentIds) {
		this.contentIds = contentIds;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}
}
