import java.util.Scanner;

public class TestUserInput {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				System.out.println("Enter a number");
				
				Integer n = sc.nextInt();
				System.out.println("Thank you. The number is " + n);
				break;
			}catch(Exception e) {sc.nextLine();System.out.println("Excepting an integer. Please input an appropriate value");	}
		}
		sc.close();
	}
}
