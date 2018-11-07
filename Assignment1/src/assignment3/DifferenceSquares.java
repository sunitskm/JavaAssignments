package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DifferenceSquares {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Double> l = new ArrayList<Double>();
		System.out.println("Enter the number of Integers you want to enter");
		Integer n = sc.nextInt();
		for(int i = 0; i < n;i++) {
			System.out.println("Enter value for number " + (i+1));
			Double d = sc.nextDouble();
			l.add(d);
		}
		Double sumOfSquare = l.stream().map(x->x*x).collect(Collectors.summingDouble(i->i));
		Double sum = l.stream().collect(Collectors.summingDouble(i->i));
		System.out.println("Sum of squares "+sumOfSquare);
		System.out.println("Squares of sum "+sum*sum);
		System.out.println(sumOfSquare-(sum*sum));
	}
	

}
