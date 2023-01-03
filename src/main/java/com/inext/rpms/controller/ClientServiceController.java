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

@CrossOrigin
@RestController
public class ClientServiceController {

	@Autowired
	private ClientService clientService;

	@PostMapping(value = "/api/signUp")
	// 新規登録
	public ClientInfoRes signUp(@RequestBody ClientInfoReq req, HttpSession httpSession) {

		// clientInfoに入れる
		ClientInfo clientInfo = new ClientInfo(req.getLastName(), req.getFirstName(), req.getBirth(), req.getEmail(),
				req.getPwd());

		// sessionに保存して、検証成功後DBに埋め込む		
		httpSession.setAttribute("clientInfo", clientInfo);

		// サーバーで入力資料を判断
		return clientService.signUp(req);

	}

	@PostMapping(value = "/api/sendMail")
	// 検証コードを発信
	public void sendMail(@RequestBody(required = false) ClientInfoReq req, HttpSession httpSession) {

		// sessionで保存したEメールを取り出す
		ClientInfo clientInfo = (ClientInfo) httpSession.getAttribute("clientInfo");
		String email = clientInfo.getEmail();

		// 検証コードを生成する
		UUID uuid = UUID.randomUUID();
		String verifyCode = uuid.toString();

		// 検証コードをsessionに保存する
		httpSession.setAttribute("verifyCode", verifyCode);

		// sendMailのメッソドで送信
		clientService.sendMail(email, verifyCode);

	}

	@PostMapping(value = "/api/activeAccount")
	// アカウント検証
	public ClientInfoRes activeAccount(@RequestBody ClientInfoReq req, HttpSession httpSession) {

		// sessionで保存したEメールを取り出す
		ClientInfo clientInfo = (ClientInfo) httpSession.getAttribute("clientInfo");
		String email = clientInfo.getEmail();

		// sessionで保存した検証コードを取り出す
		String code = httpSession.getAttribute("verifyCode").toString();

		ClientInfoRes res = new ClientInfoRes();

		// sessionで保存した資料を確認
		if (!email.isEmpty() || !code.isEmpty()) {

			// 入力コードと検証コードを確認
			if (req.getVerifyCode().equals(code)) {

				// 検証成功すれば、sessionで保存した新規登録の資料を取り出す
				ClientInfoReq finalReq = new ClientInfoReq(clientInfo.getLastName(), clientInfo.getFirstName(),
						clientInfo.getBirth(), clientInfo.getEmail(), clientInfo.getPwd());

				// 新規資料をactiveAccountでデータベースに埋め込む
				return clientService.activeAccount(finalReq);

			} else {

				// 検証失敗すれば、メッセージを返事する
				res.setMessage(ClientServiceRtnCode.ACCOUNT_FAILURE_001.getMessage());
				return res;
			}
		}

		// sessionの有効期限を過ぎると、メッセージを返事する
		res.setMessage(ClientServiceRtnCode.ACCOUNT_FAILURE_002.getMessage());
		return res;
	}

}