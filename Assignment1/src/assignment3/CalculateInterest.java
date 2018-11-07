package assignment3;

import java.util.Scanner;

public class CalculateInterest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the deposit amount ");
		Double deposit = sc.nextDouble();
		Double interest;
		Double rate = 0.0;
		if(deposit>5000.0) {
			rate = 5.0;
		}
		else if(deposit>1000) {
			rate = 4.5;
		}
		else {
			rate = 4.0;
		}
		interest = deposit * rate /100;
		System.out.println("The interest is "+interest);
	}
}
