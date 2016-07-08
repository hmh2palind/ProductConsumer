package com.tutorial.elasticserach;


public class DefaultElasticSearch extends BaseElasticSearch{
	DefaultElasticSearch(){
		initConnection(false);
	}

	DefaultElasticSearch(boolean isTransport){
		initConnection(isTransport);
	}
}
