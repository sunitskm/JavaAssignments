package assignment3;

import java.util.Scanner;

public class LoanSelection {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your age");
		Integer age = sc.nextInt();
		
		System.out.println("Enter your Gender(M/F)");
		String gender = sc.next();
		
		System.out.println("Enter your profession (Self-employed/Professional/Retired");
		String profession = sc.next();
		
		System.out.println("Enter your assets");
		Double assets = sc.nextDouble();
		
		Loan loan = new Loan(age, gender, profession, assets);
		calculateLoan(loan);
	}
	public static void calculateLoan(Loan loan) {
		if(loan.getAge()>60 && loan.getAssests()>25000) {
			System.out.println("You entered retired");
				if(loan.getProfession().equalsIgnoreCase("Retired")) {
					loan.setLoanAmount(25000.0-(loan.getAge()*100));
				}
				if(loan.getProfession().equalsIgnoreCase("Self-Employed")) {
					loan.setLoanAmount(35000.0-(loan.getAge()*100));
				}
		}
		else if(loan.getAge()>40 && loan.getAssests()>50000 ) {
			System.out.println("You entered > 40");
			if((loan.getProfession().equalsIgnoreCase("Self-Employed")
					||loan.getProfession().equalsIgnoreCase("Professional"))) {
				loan.setLoanAmount(40000.0);
			}
		}
		else if(loan.getAge()>25 &&
			   (loan.getProfession().equalsIgnoreCase("Self-Employed")) 
			   && loan.getAssests()>40000.0) {
			System.out.println("You entered >25");
			if(loan.getGender().equalsIgnoreCase("M")) {
				loan.setLoanAmount(25000.0);
			}
			else {
				loan.setLoanAmount(30000.0);
			}
		}
		else if(loan.getAge()>15&&loan.getAssests()>25000) {
			System.out.println("School jao");
			if(loan.getProfession().equalsIgnoreCase("Self-Employed")) {
				loan.setLoanAmount(10000.0);
			}
			if(loan.getProfession().equalsIgnoreCase("Professional")) {
				loan.setLoanAmount(15000.0);
			}
		}
		
		else {
			System.out.println("Yeh duniya, yeh mehfil mere kaam ki nahi");
			loan.setLoanAmount(0.0);
		}
		
		System.out.println("The loan granted is "+loan.getLoanAmount());
		
	}
}
