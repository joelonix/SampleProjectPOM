package com.opentext.base;

import org.apache.commons.codec.binary.Base64;


public class PasswordEncryption {

	public static void main(String[] args) 
	{

		
		String str=encode("password123");
		System.out.println(str);
		
		
	}
	
	private static String encode(String str)
	{
		byte[] encodedStr=Base64.encodeBase64(str.getBytes());
		
		return new String(encodedStr);
	}

}
