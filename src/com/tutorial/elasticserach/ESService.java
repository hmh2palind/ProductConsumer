package com.tutorial.elasticserach;

import org.elasticsearch.action.search.SearchResponse;

public interface ESService {
	public SearchResponse searchDocument(RuleSearchRequest request, SubquerySearchRequest subquery);
	public SearchResponse searchDocument(SubquerySearchRequest subquery);
	public void printRequest();
	public void printRespone();
}
