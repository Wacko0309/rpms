package com.inext.rpms.constants;

public enum ClientServiceRtnCode {
	
	SUCCESSFUL("200", "����"),
	
	ACCOUNT_VERIFYCATION_CODE("400", "���؃R�[�h����͂��āA�A�J�E���g���N�����܂��B"),
	
	ACCOUNT_FAILURE_001("400", "E���[���̌��؃R�[�h���m�F���Ă��������B"),
	
	ACCOUNT_FAILURE_002("400", "������x�V�K�o�^�����Ă��������B"),
	
	ACCOUNT_FAILURE_003("400", "���͎����͐������ł͂���܂���B"),
	
	ACCOUNT_FAILURE_004("400", "������x���؂��Ă��������B"),
	
	LASTNAME_FAILURE_001("400", "�c���i�p�j����͂��Ă��������B"),
	
	LASTNAME_FAILURE_002("400", "�c���i�p�j�����[�}���œ��͂��Ă��������B"),
	
	FIRSTNAME_FAILURE_001("400", "���O�i�p�j����͂��Ă��������B"),
	
	FIRSTNAME_FAILURE_002("400", "���O�i�p�j�����[�}���œ��͂��Ă��������B"),
	
	EMAIL_FAILURE_001("400", "E���[������͂��Ă��������B"),
	
	EMAIL_FAILURE_002("400", "E���[���̌`�����s���ł��B"),
	
	EMAIL_FAILURE_003("400", "E���[�����ɑ��݂��܂��B"),
	
	DATE_FAILURE_001("400", "���N�����͑S�Ă̍��ڂ���͂��ĉ������B"),
	
	PASSWORD_FAILUE_001("400", "���[���A�h���X�܂��̓p�X���[�h���������ł͂���܂���B"),
	
	PASSWORD_FAILUE_002("400", "�p�X���[�h�܂��͊m�F�p�X���[�h����͂��Ă��������B"),
	
	PASSWORD_FAILUE_003("400", "�p�X���[�h�Ɗm�F�p�X���[�h���s��v�ł��B"),
	
	PASSWORD_FAILUE_004("400", "�p�X���[�h�̓A���t�@�x�b�g�Ɛ����������āA8�����ȏ�20�����ȓ��œ��͂��Ă��������B");
	
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
