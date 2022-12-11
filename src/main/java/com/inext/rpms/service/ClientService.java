package com.inext.rpms.service;

import com.inext.rpms.vo.ClientInfoReq;
import com.inext.rpms.vo.ClientInfoRes;


public interface ClientService {

	//�V���j����
	public ClientInfoRes signUp(ClientInfoReq req);
	
	//ᢑ��暗X��
	public void sendMail(String email, String verifyCode);
	
	//�暒��j
	public ClientInfoRes activeAccount(ClientInfoReq req);
		
	//���j�o��
	public ClientInfoRes logIn(ClientInfoReq req);
	
}
