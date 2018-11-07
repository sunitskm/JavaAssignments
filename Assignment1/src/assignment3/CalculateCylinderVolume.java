package assignment3;

import java.util.Scanner;

public class CalculateCylinderVolume {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the radius ");
		Double r = sc.nextDouble();
		System.out.println("Enter the height ");
		Double h = sc.nextDouble();
		System.out.println("The volume is " + Math.PI*r*r*h);
	}
}
