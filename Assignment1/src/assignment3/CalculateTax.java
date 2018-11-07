package assignment3;

import java.util.Scanner;

public class CalculateTax {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the gross pay");
		Double pay = sc.nextDouble();
		Double tax;
		if(pay>480) {
			tax = 28.0;
		}
		else if(pay>240) {
			tax = 15.0;
		}
		else {
			tax = 0.0;
		}
		Double taxOwed = pay*tax/100;
		System.out.println("The tax owed "+taxOwed);
		
	}
	
}
