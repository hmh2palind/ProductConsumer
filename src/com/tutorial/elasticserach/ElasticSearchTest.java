package com.tutorial.elasticserach;

public class ElasticSearchTest {
	public static void main(String[] args) {
		//1. Set up servers
		ESService esService = new ESServiceImpl();
		
		//2. Create request
		SubquerySearchRequest subquery = new SubquerySearchRequest("edm", "content", "linear", "true"); 
		esService.searchDocument(subquery);
		esService.printRequest();
		
		
		//3. print response
		esService.printRespone();
	}
}
