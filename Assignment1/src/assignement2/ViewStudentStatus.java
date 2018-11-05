package assignement2;

import java.util.List;

public class ViewStudentStatus {
	private String sid;
	private String name;
	private Integer year;
	private List<Course> courses;
	private Float fee;
	
	
	public ViewStudentStatus() {
		
	}
	public ViewStudentStatus(String sid, String name, Integer year, List<Course> courses, Float fee) {
		this.sid = sid;
		this.name = name;
		this.year = year;
		this.courses = courses;
		this.fee = fee;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Float getFee() {
		return fee;
	}
	public void setFee(Float fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "Student Id= " + sid + "\nStudent Name: " + name + "\nYear: " + year + "\nCourses Enrolled: " + courses + "\nFees to be paid: " 
				+ fee + "\n";
	}
	
	
	
	
}
