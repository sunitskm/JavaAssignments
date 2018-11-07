package assignment3;

import java.util.Scanner;

public class CalculateSI {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter principal");
		Double p = sc.nextDouble();
		System.out.println("Enter rate ");
		Double r = sc.nextDouble();
		System.out.println("Enter months");
		Double t = sc.nextDouble()/12;
		Double interest = p*r*t/100;
		System.out.println("Balance is "+(p+interest));
	}
	
}
