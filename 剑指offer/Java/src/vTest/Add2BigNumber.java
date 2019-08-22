package vTest;
import java.util.Collections;
import  java.util.Scanner;

/**
 * 求解两个非负大数之和，两个大数可以为浮点数。
 * **/

public class Add2BigNumber {

	public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			String aString = scan.nextLine();
			String bString = scan.nextLine();
			String[] s1 = new String[2];
			String[] s2 = new String[2];
			if (aString.contains("."))
				s1 = aString.split("\\.");
			
			else {
				s1[0] = aString;
				s1[1] = "";
			}
			if (bString.contains("."))
				s2 = bString.split("\\.");
			else {
				s2[0] = bString;
				s2[1] = "";
			}
			add(s1, s2);
	}
	
	public static void add(String[] s1, String[] s2) {
		char takeOver = '0';
		int n0 = s1[0].length() >= s2[0].length() ? s1[0].length() : s2[0].length();
		int n1 = s1[1].length() >= s2[1].length() ? s1[1].length() : s2[1].length();
		char[] result = new char[n0+n1+2];
		
		if (s1[0].length() != s2[0].length()) {
			if (s1[0].length() > s2[0].length())
				s2[0] = String.join("", Collections.nCopies(s1[0].length() -s2[0].length(), "0")) + s2[0];
			else
				s1[0] = String.join("", Collections.nCopies(s2[0].length() -s1[0].length(), "0")) + s1[0];
		}
		
		if (s1[1].length() != s2[1].length()) {
			if (s1[1].length() > s2[1].length())
				s2[1] = s2[1] + String.join("", Collections.nCopies(s1[1].length() -s2[1].length(), "0")) ;
			else
				s1[1] = s1[1] + String.join("", Collections.nCopies(s2[1].length() -s1[1].length(), "0"));
		}
		
		for (int i = n1-1; i >=0; --i) {
			int start = n0 + 2;
			int num = (int)(s1[1].charAt(i) - '0') + (int)(s2[1].charAt(i) - '0') + (int)(takeOver - '0');
			if (num >= 10) {
				num -= 10;
				takeOver = '1';
			}
			else
				takeOver = '0';
			result[start + i] = (char)(num + '0');
		}
		
		result[n0+1] = '.';
		for (int i = n0-1; i >= 0; --i) {
			int num = (int)(s1[0].charAt(i) - '0') + (int)(s2[0].charAt(i) - '0') + (int)(takeOver - '0');
			if (num >= 10) {
				num -= 10;
				takeOver = '1';
			}
			else
				takeOver = '0';
				
			result[i+1] = (char)(num + '0');
		}
		result[0] = takeOver;
		boolean begin = true;
		for (int i = 0; i < result.length; ++i) {
			if (result[i] == '0' && begin)
				continue;
			else if (result[i] != '0')
				begin = false;
			System.out.print(result[i]);
		}
	}
}