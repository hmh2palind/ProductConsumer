package com.tutorial.couchbase;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class UtilsCouchbase {
	
	/**
	 * print jsonDocument to console
	 * @param jDoc
	 */
	static void print(JsonDocument jDoc){
		String id = jDoc.id();
		JsonObject content = jDoc.content();
		long cas = jDoc.cas();
		int expiry = jDoc.expiry();
		
		System.out.println();
		System.out.format("id     : %s%n", id);
		System.out.format("content: %s%n", content);
		System.out.format("cas    : %d%n", cas);
		System.out.format("expiry : %d%n", expiry);
	}
}
