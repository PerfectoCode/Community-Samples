package com.excelReadTimer;


public class PasswordEncryptTester {

	static String myPassword = "PasswordToEncrypt";
	static String mySalt = "Perfecto";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = StringEncrypt.encryptXOR(myPassword, mySalt);
		System.out.println(a);
	}

}
