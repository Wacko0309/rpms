package com.inext.rpms.service;

import com.inext.rpms.vo.ClientInfoReq;
import com.inext.rpms.vo.ClientInfoRes;

public interface ClientService {

	// �V�K�o�^
	public ClientInfoRes signUp(ClientInfoReq req);

	// ���؃R�[�h�𔭐M
	public void sendMail(String email, String verifyCode);

	// �A�J�E���g����
	public ClientInfoRes activeAccount(ClientInfoReq req);

}
