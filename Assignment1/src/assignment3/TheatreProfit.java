package assignment3;

import java.util.Scanner;

public class TheatreProfit {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of customers ");
		Integer n = sc.nextInt();
		Double total = n*5.0-(n*0.5);
		total = total - 20;
		System.out.println("The profit is "+total);
	}
}
