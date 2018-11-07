package assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MaximumThree {
	public static void main(String[] args) {
		List<Double> l = new ArrayList<Double>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number ");
		Double n1 = sc.nextDouble();
		l.add(n1);
		System.out.println("Enter a number ");
		Double n2 = sc.nextDouble();
		l.add(n2);
		System.out.println("Enter a number ");
		Double n3 = sc.nextDouble();
		l.add(n3);

		l.sort(Comparator.naturalOrder());
		System.out.println("The maximum number is ");
		if(Math.round(l.get(2))-l.get(2)==0.0){
			System.out.println(Math.round(l.get(2)));
		}
		else {
			System.out.println(l.get(2));
		}
	}
}
