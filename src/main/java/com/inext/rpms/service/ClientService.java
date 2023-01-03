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

	// ���O�C��
	public ClientInfoRes login(ClientInfoReq req);

	// �p�X���[�h�A�V�X�^���g�̃A�J�E���g����
	public ClientInfoRes checkAccount(ClientInfoReq req);

	// �p�X���[�h�X�V
	public ClientInfoRes pwdUpdate(String email, String pwd);
}
