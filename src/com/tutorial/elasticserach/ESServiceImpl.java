package com.tutorial.elasticserach;

import static com.tutorial.elasticserach.ElastichSearchUtils.prinSearchHit;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.SearchHits;
import org.jboss.logging.Logger;

public class ESServiceImpl implements ESService {
	private Logger logger = Logger.getLogger(ESServiceImpl.class);
	
	private SearchRequestBuilder request;
	private SearchResponse response = null;
	
	private DefaultElasticSearch deafultElasticSearch;
	
	ESServiceImpl(){
		deafultElasticSearch = new DefaultElasticSearch(true);
	}
	
	@Override
	public SearchResponse searchDocument(RuleSearchRequest request,SubquerySearchRequest subquery) {
		// XXX need implementaion
		return null;
	}

	@Override
	public SearchResponse searchDocument(SubquerySearchRequest subquery) {
		logger.debug("Starting document for subquery ### " + subquery);
		
		request = deafultElasticSearch.client.prepareSearch(subquery.getIndex())
				.setTypes(subquery.getType())
				.setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(matchQuery(subquery.getField(), subquery.getValue()))
				.setFrom(0).setSize(60).setExplain(true);

		response = request.execute().actionGet();
		return response;
	}
	
	public void printRequest(){
		logger.debug("print request ### " + request.internalBuilder().toString());
		System.out.println(request.internalBuilder().toString());
	}
	
	public void printRespone(){
		SearchHits hits = response.getHits();
		long totalHits = hits.getTotalHits();
		
		logger.debug("print response Current results ### "+ totalHits);
		System.out.println("Current results: "+ totalHits);
		prinSearchHit(response.getHits());
	}
}
