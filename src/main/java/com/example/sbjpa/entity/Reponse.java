package com.example.sbjpa.entity;

public class Reponse {
	
	private String status;
	private Object data;
	
	public Reponse(String status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Reponse [status=" + status + ", data=" + data + "]";
	}
	
}
