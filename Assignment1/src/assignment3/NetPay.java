package assignment3;

import java.util.Scanner;

public class NetPay {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the hours worked");
		Integer h = sc.nextInt();
		Double grossPay = 12.0*h;
		Double tax = 15*grossPay/100;
		Double netPay = grossPay - tax;
		System.out.println("The tax was "+ tax + " and the net pay was "+ netPay);
	}
}
