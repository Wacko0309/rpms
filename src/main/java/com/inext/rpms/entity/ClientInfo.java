package com.inext.rpms.entity;

import java.time.LocalDate;

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
	// �c���i�p�j
	private String lastName;

	@Column(name = "first_name")
	// ���O�i�p�j
	private String firstName;

	@Column(name = "birth")
	// ���N����
	private LocalDate birth;

	@Id
	@Column(name = "email")
	// E���[��
	private String email;

	@Column(name = "password")
	// �p�X���[�h
	private String pwd;

	public ClientInfo() {

	}

	public ClientInfo(String email) {
		this.email = email;
	}

	public ClientInfo(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}

	public ClientInfo(String lastName, String firstName, LocalDate birth, String email, String pwd) {
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

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
