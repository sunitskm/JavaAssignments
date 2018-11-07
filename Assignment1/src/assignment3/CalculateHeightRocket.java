package assignment3;

import java.util.Scanner;

public class CalculateHeightRocket {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the time in seconds");
		Double time = sc.nextDouble();
		Double velocity = time * 9.8;
		Double height = 0.5*velocity*time;
		System.out.println("Height of rocket is "+height + "meters");
		
	}
}
