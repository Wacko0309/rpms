package com.inext.rpms.service;

import com.inext.rpms.vo.ClientInfoReq;
import com.inext.rpms.vo.ClientInfoRes;


public interface ClientService {

	//V ċjû
	public ClientInfoRes signUp(ClientInfoReq req);
	
	//á˘éĉX
	public void sendMail(String email, String verifyCode);
	
	//éĉ ċj
	public ClientInfoRes activeAccount(ClientInfoReq req);
		
	// ċjoü
	public ClientInfoRes logIn(ClientInfoReq req);
	
}
