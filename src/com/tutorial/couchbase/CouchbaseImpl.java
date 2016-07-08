
package com.tutorial.couchbase;

import org.apache.log4j.Logger;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.error.DocumentAlreadyExistsException;

/**
 * @author HungHM5
 *
 */
public class CouchbaseImpl implements CouchbaseDao{
	private static Logger logger = Logger.getLogger(CouchBaseTest.class);
	
	private DefaultCouchbase defaultCouchbase;
	
	public CouchbaseImpl(){
		defaultCouchbase = DefaultCouchbase.getInstance();
	}
	
	/**
	 * @param key
	 * @return
	 */
	public JsonDocument getDoc(String key) throws Exception {
		logger.debug("Get document by key ### " + key);
		JsonDocument jDocument = defaultCouchbase.bucket.get(key);
		return jDocument;
	}
	
	/**
	 * Creating a document using upsert() method. If is does not already exist
	 * in the bucket, if it does exist, the Observable fails with a
	 * DocumentAlreadyExistsException
	 * 
	 * @param key
	 * @param object
	 * @throws Exception 
	 */
	public JsonDocument insertDoc(String key, Object object) throws Exception {
		logger.debug("Insert a document has key ### " + key + ", value ### " + object);
		
		JsonDocument response = null;
		try {
			response = convertObject2Document(key, object);
			response = defaultCouchbase.bucket.insert(response);
		} catch (DocumentAlreadyExistsException docAlready) {
			logger.debug("This document already exists" + docAlready.toString());
		}
		return response;
	}
	
	/**
	 * This method convert a object java to jsonDocument Couchbase
	 * @param object
	 * @return
	 * @throws Exception 
	 */
	private JsonDocument convertObject2Document(String key, Object object) throws Exception {
		if (key == null || key.isEmpty() || object == null)
			return null;
		
		String str = "{}";
		
		//Object -> string
		str = defaultCouchbase.mapper.writeValueAsString(object);
		//string -> jsonObject
		JsonObject jsonObject = defaultCouchbase.trans.stringToJsonObject(str);
		//jsonObject -> jsonDocument
		JsonDocument jsonDocument = JsonDocument.create(key, jsonObject);
		
		return jsonDocument;
	}
	
	/**
	 * This method convert a object java to RawJsonDocument Couchbase
	 * @param object
	 * @return
	 * @throws Exception 
	 */
	protected RawJsonDocument objectToRaw(String key, Object object) throws Exception {
		if (key == null || key.isEmpty() || object == null)
			return null;
		
		String str = "{}";
		
		//Object -> string
		str = defaultCouchbase.mapper.writeValueAsString(object);
		//string -> rawJsonDocument
		RawJsonDocument document = RawJsonDocument.create(key, str);
		
		return document;
	}
}
