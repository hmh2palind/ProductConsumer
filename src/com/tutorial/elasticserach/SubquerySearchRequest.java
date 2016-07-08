package com.tutorial.elasticserach;

public class SubquerySearchRequest {
	private String index;
	private String type;
	private String field;
	private String value;
	
	
	public SubquerySearchRequest(String index, String type, String field,
			String value) {
		super();
		this.index = index;
		this.type = type;
		this.field = field;
		this.value = value;
	}

	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
}
