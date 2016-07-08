package com.tutorial.couchbase;

import org.codehaus.jackson.map.ObjectMapper;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.transcoder.JsonTranscoder;

import static com.tutorial.couchbase.ConstantCoubase.ADDRESS;
import static com.tutorial.couchbase.ConstantCoubase.BUKET_NAME;

/**
 * @author HungHM5
 *
 */
public abstract class BaseCouchbase {	
	protected CouchbaseEnvironment environment;
	protected Cluster cluster;
	protected Bucket bucket;
	
	protected ObjectMapper mapper;
	protected JsonTranscoder trans;
	
	protected void initConnection(){
		environment = DefaultCouchbaseEnvironment
				.builder()
			    .queryEnabled(true)
			    .build();
		
		//Connect to a cluster on address
		cluster = CouchbaseCluster.create(environment, ADDRESS);

		// Open the default bucket
		bucket = cluster.openBucket(BUKET_NAME);
		
		mapper = new ObjectMapper();
		trans = new JsonTranscoder();
	}
	
	protected void disconnect() {
		cluster.disconnect();
	}
}
