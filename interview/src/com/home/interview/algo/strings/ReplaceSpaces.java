package com.home.interview.algo.strings;

public class ReplaceSpaces {
	
	//Write a method to replace all the spaces in a string with ‘%20’. 
	
	public static char[] replace(char []str) {
		int blanks = 0;
		for(int i = 0; i < str.length; i++) {
			if(str[i] == ' ') {
				blanks++;
			}
		}
		int new_length = str.length + 2 * blanks;
		char new_str[] = str;
		str = new char[new_length];
        int index = new_str.length - 1;
        new_length--;
        while(index >= 0) {
        	if(new_str[index] == ' ') {
        		str[new_length--] = '0';
        		str[new_length--] = '2';
        		str[new_length--] = '%';
        	}else {
        		str[new_length--] = new_str[index];
        	}
        	index--;
        }
        return str;
	}
	
	public static void main(String[] args) {
		System.out.println(replace("mama are mere multe".toCharArray()));
	}
}
