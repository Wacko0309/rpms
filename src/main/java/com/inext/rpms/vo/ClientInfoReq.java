package com.inext.rpms.vo;

import java.util.Calendar;


public class ClientInfoReq {
	
	private String lastName;
	
	private String firstName;
		
	private Calendar birth;
	
	private String email;
	
	private String pwd;
	
	private String confirmPwd;
	
	private String verifyCode;
	
	
	public ClientInfoReq() {

	}
	
	public ClientInfoReq(String lastName, String firstName, Calendar birth, String email, String pwd) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birth = birth;
		this.email = email;
		this.pwd = pwd;
	}

	public ClientInfoReq(String lastName, String firstName, Calendar birth, String email, String pwd, String confirmPwd) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.birth = birth;
		this.email = email;
		this.pwd = pwd;
		this.confirmPwd = confirmPwd;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Calendar getBirth() {
		return birth;
	}

	public void setBirth(Calendar birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
}
