package com.inext.rpms.entity;

import java.util.Calendar;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientInfo {
	
	@Column(name = "last_name")
	//註冊人 姓
	private String lastName;
	
	@Column(name = "first_name")
	//註冊人 名
	private String firstName;
		
	@Column(name = "birth")
	//註冊人 出生年月日
	private Calendar birth;
	
	@Id
	@Column(name = "email")
	//註冊信箱
	private String email;
	
	@Column(name = "pwd")
	//密碼
	private String pwd;
	
	@Column(name = "active")
	//是否從信箱激活
	private boolean active;
	
	public ClientInfo() {

	}
	
	public ClientInfo(String lastName, String firstName, Calendar birth, String email, String pwd) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.birth = birth;
		this.email = email;
		this.pwd = pwd;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getBirth() {
		return birth;
	}

	public void setBirth(Calendar birth) {
		this.birth = birth;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
