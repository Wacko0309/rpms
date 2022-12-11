package com.inext.rpms.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inext.rpms.constants.ClientServiceRtnCode;
import com.inext.rpms.entity.ClientInfo;
import com.inext.rpms.service.ClientService;
import com.inext.rpms.vo.ClientInfoReq;
import com.inext.rpms.vo.ClientInfoRes;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class ClientServiceController {

	@Autowired
	private ClientService clientService;

	@PostMapping(value = "/api/signUp")
	public ClientInfoRes signUp(@RequestBody ClientInfoReq req, HttpSession httpSession) {

		ClientInfo clientInfo = new ClientInfo(req.getLastName(), req.getFirstName(), req.getBirth(), req.getEmail(),
				req.getPwd());

		// session保留註冊資料7天待驗證後啟動帳號
		httpSession.setAttribute("clientInfo", clientInfo);
		httpSession.setMaxInactiveInterval(60 * 60 * 24 * 7);

		return clientService.signUp(req);

	}

	@PostMapping(value = "/api/sendMail")
	public void sendMail(@RequestBody ClientInfoReq req, HttpSession httpSession) {

		ClientInfo clientInfo = (ClientInfo) httpSession.getAttribute("clientInfo");
		String email = clientInfo.getEmail();

		// 生成驗證碼
		UUID uuid = UUID.randomUUID();
		String verifyCode = uuid.toString();

		// 將該次驗證碼存入session並保存30分鐘
		httpSession.setAttribute("verifyCode", verifyCode);
		httpSession.setMaxInactiveInterval(60 * 30);

		clientService.sendMail(email, verifyCode);

	}

	@PostMapping(value = "/api/activeAccount")
	public ClientInfoRes activeAccount(@RequestBody ClientInfoReq req, HttpSession httpSession) {

		ClientInfo clientInfo = (ClientInfo) httpSession.getAttribute("clientInfo");

		String email = clientInfo.getEmail();

		String code = httpSession.getAttribute("verifyCode").toString();

		ClientInfoRes res = new ClientInfoRes();

		if (!email.isEmpty() || !code.isEmpty()) {

			// 驗證碼是否符合
			if (req.getVerifyCode().equals(code)) {

				ClientInfoReq finalReq = new ClientInfoReq(clientInfo.getLastName(), clientInfo.getFirstName(),
						clientInfo.getBirth(), clientInfo.getEmail(), clientInfo.getPwd());

				return clientService.activeAccount(finalReq);

			} else {
				
				res.setMessage(ClientServiceRtnCode.ACCOUNT_FAILURE_001.getMessage());
				return res;
			}
		}

		res.setMessage(ClientServiceRtnCode.ACCOUNT_FAILURE_002.getMessage());
		return res;
	}

	@PostMapping(value = "/api/logIn")
	public ClientInfoRes logIn(@RequestBody ClientInfoReq req) {

		return clientService.logIn(req);

	}
}
