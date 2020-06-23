package com.home.interview.algo.strings;

public class Atoi {
	
	//Write your own atoi()
	
	public static int atoi(char []str) {
		int res = 0;
		int sign = 0;
		if(str[0] == '-') {
			sign = 1;
		}
		for(int i = sign; i < str.length; i++) {
			char ch = str[i];
			if(!Character.isDigit(ch)) {
				return 0;
			}
			res = res * 10 + str[i] - '0';
		}
		if(sign == 1) {
			return -res;
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(atoi("-14568".toCharArray()));
	}
}
