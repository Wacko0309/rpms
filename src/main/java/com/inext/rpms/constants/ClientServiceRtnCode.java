package com.inext.rpms.constants;

public enum ClientServiceRtnCode {
	
	SUCCESSFUL("200", "成功"),
	ACCOUNT_VERIFYCATION_CODE("200", "検証コードを入力して、アカウントを起動します。"),
	ACCOUNT_FAILURE_001("400", "Eメールの検証コードを確認してください。"),
	ACCOUNT_FAILURE_002("400", "もう一度新規登録をしてください。"),
	LASTNAME_FAILURE_001("400", "苗字（英）を入力してください。"),
	LASTNAME_FAILURE_002("400", "苗字（英）をローマ字で入力してください。"),
	FIRSTNAME_FAILURE_001("400", "名前（英）を入力してください。"),
	FIRSTNAME_FAILURE_002("400", "名前（英）をローマ字で入力してください。"),
	EMAIL_FAILURE_001("400", "Eメールを入力してください。"),
	EMAIL_FAILURE_002("400", "Eメールの形式が不正です。"),
	EMAIL_FAILURE_003("400", "Eメール既に存在します。"),
	DATE_FAILURE_001("400", "生年月日は全ての項目を入力して下さい。"),
	PASSWORD_FAILUE_001("400", "メールアドレスまたはパスワードが正しくありません。"),
	PASSWORD_FAILUE_002("400", "パスワードと確認パスポートを入力してください。"),
	PASSWORD_FAILUE_003("400", "パスワードと確認パスポートが不一致です。"),
	PASSWORD_FAILUE_004("400", "パスワードはアルファベットと数字を混ぜて、8文字以上20文字以内で入力してください。");
	
	private String code;
	
	private String message;
	
	private ClientServiceRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
