package assignment3;

import java.util.Scanner;

public class CheckVowel {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter an alphabet");
	String s = sc.next().substring(0, 1);
	if(s.equalsIgnoreCase("A") || s.equalsIgnoreCase("E") || s.equalsIgnoreCase("I") || s.equalsIgnoreCase("O") || s.equalsIgnoreCase("U")) {
		System.out.println("Character given is a vowel");
	}
	else {
		System.out.println("It's not a vowel");
	}
}
}
