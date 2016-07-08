package com.tutorial.elasticserach;

import java.util.Map;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class ElastichSearchUtils {
	static void prinSearchHit(SearchHits hits) {
		Map<String, Object> result = null;
		for (SearchHit hit : hits) {
			System.out.println("------------------------------");
			result = hit.getSource();
			System.out.println(hit.getId());
			System.out.println(result);
		}
	}
	
	static void buildSearchRequest(){
		
	}
}
