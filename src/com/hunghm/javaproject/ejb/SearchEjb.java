package com.hunghm.javaproject.ejb;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.hunghm.javaproject.common.RuleSearchRequest;
import com.hunghm.javaproject.common.SearchRequest;
import com.hunghm.javaproject.common.SearchResponse;
import com.hunghm.javaproject.contentsearch.Search;
import com.hunghm.javaproject.ejbint.SearchInterfaceRemote;

/**
 * @author HungHM5
 *
 */
@Stateless
public class SearchEjb implements SearchInterfaceRemote{
	private static final Logger logger = Logger.getLogger(SearchEjb.class);
	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		try{
			return Search.search(searchRequest);
		}
		catch (Exception ex){
			logger.error("Exception: ", ex);
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public SearchResponse ruleSearch(RuleSearchRequest ruleSearchRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
