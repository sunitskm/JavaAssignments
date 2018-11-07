package assignment3;

import java.util.Scanner;

public class CalculatePerimeter {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the area of the square");
	Double area = sc.nextDouble();
	Double side = Math.sqrt(area);
	System.out.println("Perimeter is "+ 4*side);
}
}
