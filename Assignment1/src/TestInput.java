import java.util.Scanner;

public class TestInput {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Integer n;
		while(true) {
			System.out.println("Please enter a number");
			sc.next();
			if(sc.hasNextInt()) {
				n = sc.nextInt();
				System.out.println("Thanks! number is " + n);
				break;
			}
			System.out.println("Incorrect entry");
			
		}
			
	}
}
