package com.tutorial.couchbase;


public class DefaultCouchbase extends BaseCouchbase{
	private static DefaultCouchbase instance = null;
	
	private DefaultCouchbase(){
		initConnection();
	}
	
	public synchronized static DefaultCouchbase getInstance() {
		if (instance != null)
			return instance;
		else 
			instance = new DefaultCouchbase();
		
		return instance;
	}
}
