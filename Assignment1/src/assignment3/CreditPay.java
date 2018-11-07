package assignment3;

import java.util.Scanner;

public class CreditPay {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the charge ");
		Double charge = sc.nextDouble();
		Double payBack = 0.0 ;
		Double actualCharge = 0.0;
		if(charge<=2500) {
			if(charge>0) {
				if(charge>500) {
					actualCharge = 500.0;
				}
				else {
					actualCharge = charge;
				}
				payBack += actualCharge*0.25/100;
				charge -= 500;
			}
			if(charge>0) {
				if(charge>1000) {
					actualCharge = 1000.0;
				}
				else {
					actualCharge = charge;
				}
				payBack += actualCharge*0.5/100;
				charge -= 1000;
			}
			if(charge>0) {
				if(charge>500) {
					actualCharge = 1000.0;
				}
				else {
					actualCharge = charge;
				}
				payBack += actualCharge*0.75/100;
				charge -= 1000;
			}
			
		}
		else {
			payBack = charge/100;
		}
		System.out.println("Pay back is "+payBack);
	}
}
