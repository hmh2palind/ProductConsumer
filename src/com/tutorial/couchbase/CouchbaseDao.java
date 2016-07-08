package com.tutorial.couchbase;

import com.couchbase.client.java.document.JsonDocument;

public interface CouchbaseDao{
	/**
	 * @param key
	 * @throws Exception 
	 */
	public JsonDocument getDoc(String key) throws Exception;
	/**
	 * Creating a document using upsert() method. If is does not already exist
	 * in the bucket, if it does exist, the Observable fails with a
	 * DocumentAlreadyExistsException
	 * 
	 * @throws Exception 
	 */
	public JsonDocument insertDoc(String key, Object object) throws Exception;
}
