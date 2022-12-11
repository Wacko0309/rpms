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
	// 註冊
	public ClientInfoRes signUp(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// 抽出方法判斷輸入不得為空
		if (checkParams(req) != null) {

			return checkParams(req);

			// 抽出方法判斷格式是否符合規範
		} else if (checkFormat(req) != null) {

			return checkFormat(req);

		}

		// 註冊資料裝進 clientInfo
		ClientInfo clientInfo = new ClientInfo(req.getLastName(), req.getFirstName(), req.getBirth(), req.getEmail(),
				req.getPwd());

		res.setClientInfo(clientInfo);

		// 回傳註冊資訊
		return res;
	}

	@Override
	//發送驗證郵件
	public void sendMail(String email, String verifyCode) {
		
		SimpleMailMessage message = new SimpleMailMessage();

		// 送信
		message.setTo(email);
		message.setSubject(ClientServiceRtnCode.ACCOUNT_VERIFYCATION_CODE.getMessage());
		message.setText("検証コードは：　" + verifyCode);

		mailSender.send(message);
	}

	@Override
	// 激活帳號
	public ClientInfoRes activeAccount(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		ClientInfo clientInfo = new ClientInfo(req.getLastName(), req.getFirstName(), req.getBirth(), req.getEmail(),
				req.getPwd());

		// 存進資料庫
		clientDao.save(clientInfo);
		Optional<ClientInfo> infoOp = clientDao.findById(req.getEmail());
		//啟動帳號
		infoOp.get().setActive(true);
		clientDao.save(infoOp.get());

		return res;
	}

	@Override
	// 登錄
	public ClientInfoRes logIn(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// Email輸入不得為空
		if (!StringUtils.hasText(req.getEmail())) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_001.getMessage());
			return res;

			// 密碼輸入不得為空
		} else if (!StringUtils.hasText(req.getPwd())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_002.getMessage());
			return res;

		}

		// 確認DB中有無資料
		if (!clientDao.existsById(req.getEmail())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_001.getMessage());
			return res;

		}

		// 確認後取出該帳號資料
		Optional<ClientInfo> clientInfoOp = clientDao.findById(req.getEmail());
		ClientInfo clientInfo = clientInfoOp.get();

		// 確認帳號是否通過驗證
		if (!clientInfo.isActive()) {

			res.setMessage(ClientServiceRtnCode.ACCOUNT_FAILURE_001.getMessage());
			return res;

			// 確認密碼是否符合
		} else if (req.getPwd() != clientInfo.getPwd()) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_001.getMessage());
			return res;

		}

		// 符合登入資格回傳成功訊息
		res.setMessage(ClientServiceRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// ==========================================================================================================
	// 抽出方法判斷輸入不得為空
	public ClientInfoRes checkParams(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// 姓氏
		if (!StringUtils.hasText(req.getLastName())) {

			res.setMessage(ClientServiceRtnCode.LASTNAME_FAILURE_001.getMessage());
			return res;

			// 名字
		} else if (!StringUtils.hasText(req.getFirstName())) {

			res.setMessage(ClientServiceRtnCode.FIRSTNAME_FAILURE_001.getMessage());
			return res;

			// Email
		} else if (!StringUtils.hasText(req.getEmail())) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_001.getMessage());
			return res;

			// 出生年月日
		} else if (req.getBirth() == null) {

			res.setMessage(ClientServiceRtnCode.DATE_FAILURE_001.getMessage());
			return res;

			// 密碼及確認密碼
		} else if (!StringUtils.hasText(req.getPwd()) || !StringUtils.hasText(req.getConfirmPwd())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_002.getMessage());
			return res;

		}
		return null;
	}

	// 抽出方法判斷格式是否符合規範
	public ClientInfoRes checkFormat(ClientInfoReq req) {

		ClientInfoRes res = new ClientInfoRes();

		// email regex
		String emailFormat = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		// password regex : 英數混合最少8最多20字
		String pwdFormat = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";

		// lastName regex: 羅馬拼音最少1最多20字
		String lastNameFormat = "[A-Za-z]{1,20}$";

		// lastName regex: 羅馬拼音最少1最多20字
		String firstNameFormat = "[A-Za-z]{1,20}$";

		// 確認密碼格式
		if (!req.getPwd().matches(pwdFormat)) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_004.getMessage());
			return res;

			// 確認email格式
		} else if (!req.getEmail().matches(emailFormat)) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_002.getMessage());
			return res;

			// 密碼及確認密碼是否一致
		} else if (!req.getPwd().equals(req.getConfirmPwd())) {

			res.setMessage(ClientServiceRtnCode.PASSWORD_FAILUE_003.getMessage());
			return res;

			// 判斷信箱是否註冊過
		} else if (clientDao.existsById(req.getEmail())) {

			res.setMessage(ClientServiceRtnCode.EMAIL_FAILURE_003.getMessage());
			return res;

			// 確認姓格式
		} else if (!req.getLastName().matches(lastNameFormat)) {

			res.setMessage(ClientServiceRtnCode.LASTNAME_FAILURE_002.getMessage());
			return res;

			// 確認名格式
		} else if (!req.getFirstName().matches(firstNameFormat)) {

			res.setMessage(ClientServiceRtnCode.FIRSTNAME_FAILURE_002.getMessage());
			return res;

		}
		return null;
	}

}
