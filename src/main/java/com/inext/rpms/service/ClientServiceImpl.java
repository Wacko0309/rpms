package com.inext.rpms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inext.rpms.constants.ClientServiceRtnCode;
import com.inext.rpms.entity.ClientInfo;
import com.inext.rpms.repository.ClientDao;
import com.inext.rpms.vo.ClientInfoReq;
import com.inext.rpms.vo.ClientInfoRes;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	// 新規登録
	public ClientInfoRes signUp(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// 新規登録の資料が入力しないとメッセージを返事する
		if (checkParams(req) != null) {
			return checkParams(req);

			// パスワードが入力しないとメッセージを返事する
		} else if (!StringUtils.hasText(req.getPwd()) || !StringUtils.hasText(req.getConfirmPwd())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_002.getMessage());
			return res;

			// 新規登録の資料が正しくないとメッセージを返事する
		} else if (checkFormat(req) != null) {
			return checkFormat(req);

		}
		return res;
	}

	@Override
	// 検証コードを発信
	public void sendMail(String email, String verifyCode) {

		SimpleMailMessage message = new SimpleMailMessage();

		// 新規登録のEメール
		message.setTo(email);
		
		//Eメールのタイトル
		message.setSubject(ClientServiceRtnCode.ACCOUNT_VERIFYCATION_CODE.getMessage());
		
		//Eメールの内容(検証コード)
		message.setText("検証コードは：　" + verifyCode);

		
		// 送信
		mailSender.send(message);
		//
	}

	@Override
	// アカウント検証
	public ClientInfoRes activeAccount(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();
		
		//　新規登録の資料をDBに入れる
		ClientInfo clientInfo = new ClientInfo(req.getLastName(), req.getFirstName(), req.getBirth(), req.getEmail(),
				req.getPwd());
		
		clientDao.save(clientInfo);

		return res;
	}

	@Override
	// ログイン
	public ClientInfoRes login(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// ログイン資料が入力しないとメッセージを返事する
		//　Eメール
		if (!StringUtils.hasText(req.getEmail())) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_001.getMessage());
			return res;

		//　パスワード
		} else if (!StringUtils.hasText(req.getPwd())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_002.getMessage());
			return res;

		}

		// Eメールでアカウント資料をDBから取り出す
		Optional<ClientInfo> clientInfoOp = clientDao.findById(req.getEmail());
		
		// DB中でアカウントの存在を確認
		// 入力したパスワードをDBのパスワードと確認、不一致とメッセージを返事する
		if (clientInfoOp.isEmpty() || !req.getPwd().equals(clientInfoOp.get().getPwd())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_001.getMessage());
			return res;

		}
		return res;
	}

	@Override
	// パスワードアシスタント
	public ClientInfoRes checkAccount(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// 資料が入力しないとメッセージを返事する
		if (checkParams(req) != null) {

			return checkParams(req);
		}
		
		// Eメールでアカウント資料をDBから取り出す
		Optional<ClientInfo> clientInfoOp = clientDao.findById(req.getEmail());

		// DB中でアカウントの存在を確認
		if (clientInfoOp.isEmpty()) {

			res.setMessage(ClientServiceRtnCode.ACCOUNT_FAILURE_003.getMessage());
			return res;
			
		}
		
		ClientInfo clientInfo = clientInfoOp.get();

		// 入力値をDB資料と確認、不一致とメッセージを返事する
		if (!clientInfo.getLastName().equals(req.getLastName()) || !clientInfo.getFirstName().equals(req.getFirstName())
				|| !clientInfo.getBirth().equals(req.getBirth())) {

			res.setMessage(ClientServiceRtnCode.ACCOUNT_FAILURE_003.getMessage());

			return res;
		}
		
		return res;
	}

	
	@Override
	// パスワード更新
	public ClientInfoRes pwdUpdate(String email, String pwd) {

		//　Eメールでアカウント資料をDBから取り出す
		Optional<ClientInfo> clientInfoOp = clientDao.findById(email);
		ClientInfo clientInfo = clientInfoOp.get();

		//　新たなパスワードをDBの資料を更新する
		clientInfo.setPwd(pwd);
		clientDao.save(clientInfo);

		return new ClientInfoRes();
	}

	// ===========================================================================
	
	// 資料が入力しないとメッセージを返事する
	public ClientInfoRes checkParams(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// 苗字
		if (!StringUtils.hasText(req.getLastName())) {

			res.setMessage(ClientServiceRtnCode.LASTNAME_FAILURE_001.getMessage());
			return res;

		// 名前
		} else if (!StringUtils.hasText(req.getFirstName())) {

			res.setMessage(ClientServiceRtnCode.FIRSTNAME_FAILURE_001.getMessage());
			return res;

		// Eメール
		} else if (!StringUtils.hasText(req.getEmail())) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_001.getMessage());
			return res;

		// 生年月日
		} else if (req.getBirth() == null) {

			res.setMessage(ClientServiceRtnCode.DATE_FAILURE_001.getMessage());
			return res;

		}
		return null;
	}

	// 入力資料が正しくないとメッセージを返事する
	public ClientInfoRes checkFormat(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// Eメールの正規表現 
		String emailFormat = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		// パスワードの正規表現 
		String pwdFormat = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";

		// 苗字と名前の正規表現 
		String nameFormat = "[A-Za-z]{1,20}$";

		// パスワード
		if (!req.getPwd().matches(pwdFormat)) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_004.getMessage());
			return res;

		// Eメール
		} else if (!req.getEmail().matches(emailFormat)) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_002.getMessage());
			return res;

		// 苗字
		} else if (!req.getLastName().matches(nameFormat)) {

			res.setMessage(ClientServiceRtnCode.LASTNAME_FAILURE_002.getMessage());
			return res;

		// 名前
		} else if (!req.getFirstName().matches(nameFormat)) {

			res.setMessage(ClientServiceRtnCode.FIRSTNAME_FAILURE_002.getMessage());
			return res;
			
		// パスワードと確認パスワート合わないとメッセージを返事する
		} else if (!req.getPwd().equals(req.getConfirmPwd())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_003.getMessage());
			return res;

		// アカウントが存在するとメッセージを返事する
		} else if (clientDao.existsById(req.getEmail())) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_003.getMessage());
			return res;

		}
		return null;
	}

}
