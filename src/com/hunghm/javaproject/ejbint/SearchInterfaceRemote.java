package com.hunghm.javaproject.ejbint;

import javax.ejb.Remote;

import com.hunghm.javaproject.common.RuleSearchRequest;
import com.hunghm.javaproject.common.SearchRequest;
import com.hunghm.javaproject.common.SearchResponse;

@Remote
public interface SearchInterfaceRemote {
	public SearchResponse search(SearchRequest searchRequest);
	public SearchResponse ruleSearch(RuleSearchRequest ruleSearchRequest);
}
