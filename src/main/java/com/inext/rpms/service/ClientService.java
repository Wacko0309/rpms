package com.inext.rpms.service;

import com.inext.rpms.vo.ClientInfoReq;
import com.inext.rpms.vo.ClientInfoRes;


public interface ClientService {

	//V’ åj’û
	public ClientInfoRes signUp(ClientInfoReq req);
	
	//á¢‘—é„æš—XŒ
	public void sendMail(String email, String verifyCode);
	
	//é„æš’ åj
	public ClientInfoRes activeAccount(ClientInfoReq req);
		
	//’ åj“o“ü
	public ClientInfoRes logIn(ClientInfoReq req);
	
}
