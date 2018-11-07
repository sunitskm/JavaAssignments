package assignment3;

public class Loan {
	private int age;
	private String gender;
	private String profession;
	private Double assets;
	private Double loanAmount;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Double getAssests() {
		return assets;
	}
	public void setAssests(Double assests) {
		this.assets = assests;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Loan(int age, String gender, String profession, Double assests) {
		super();
		this.age = age;
		this.gender = gender;
		this.profession = profession;
		this.assets = assests;
		this.loanAmount = 0.0;
	}
	
}
