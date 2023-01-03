package com.inext.rpms.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inext.rpms.entity.ClientInfo;

@JsonInclude(JsonInclude.Include.NON_NULL) // –hŽ~null‰ñœä
public class ClientInfoRes {

	private ClientInfo clientInfo;

	private String message;

	public ClientInfoRes() {

	}

	public ClientInfoRes(ClientInfo clientInfo, String message) {
		this.clientInfo = clientInfo;
		this.message = message;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
