package com.inext.rpms.service;

import com.inext.rpms.vo.ClientInfoReq;
import com.inext.rpms.vo.ClientInfoRes;

public interface ClientService {

	// 新規登録
	public ClientInfoRes signUp(ClientInfoReq req);

	// 検証コードを発信
	public void sendMail(String email, String verifyCode);

	// アカウント検証
	public ClientInfoRes activeAccount(ClientInfoReq req);

	// ログイン
	public ClientInfoRes login(ClientInfoReq req);

	// パスワードアシスタントのアカウント検証
	public ClientInfoRes checkAccount(ClientInfoReq req);

	// パスワード更新
	public ClientInfoRes pwdUpdate(String email, String pwd);
}
