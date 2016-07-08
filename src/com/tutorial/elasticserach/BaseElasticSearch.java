package com.tutorial.elasticserach;

import static com.tutorial.elasticserach.ElasticSearchConstants.ADDRESS;
import static com.tutorial.elasticserach.ElasticSearchConstants.CLUSTER_NAME;
import static com.tutorial.elasticserach.ElasticSearchConstants.CLUSTER_NAME_VALUE;
import static com.tutorial.elasticserach.ElasticSearchConstants.PORT;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public abstract class BaseElasticSearch {
	protected Node node;
	protected NodeBuilder nodeBuilder = new NodeBuilder();

	protected Client client;
	protected TransportClient transportClient;

	protected void initConnection(boolean isTransport) {
		if (isTransport) {
			Settings settings = ImmutableSettings.settingsBuilder()
					.put(CLUSTER_NAME, CLUSTER_NAME_VALUE).build();

			transportClient = new TransportClient(settings);

			transportClient.addTransportAddress(new InetSocketTransportAddress(
					ADDRESS, PORT));
		}

		else {
			node = nodeBuilder.clusterName(CLUSTER_NAME_VALUE).node();
			client = node.client();
		}
	}
}