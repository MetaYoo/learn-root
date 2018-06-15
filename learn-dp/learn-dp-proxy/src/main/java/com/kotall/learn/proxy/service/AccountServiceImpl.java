package com.kotall.learn.proxy.service;

public class AccountServiceImpl implements AccountService {

	@Override
	public String createAccount(String accountname, String password, int age) {
		System.out.println("====== create account :" + accountname + ", " + password + ", " + age);
		return "create success !";
	}

}
