package assignement2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StudentDriver {
	public static void main(String[] args) throws ClassNotFoundException {

		String ch = "Y";
		Scanner sc = new Scanner(System.in);
		while(ch.equalsIgnoreCase("Y")) {

			System.out.println("Sign In as: \n 1. User \n 2. Student");
			try {
				Integer n = sc.nextInt();
				if(n==1) {
					System.out.println("Select one of the following options");
					System.out.println("1. Add Students");
					System.out.println("2. View Student Info");
					System.out.println("3. View All Students");
					n = sc.nextInt();
					if(n==1) {
						addStudents();
					}
					else if(n==2) {
						System.out.println("Enter the student Id");
						String sid = sc.next();
						System.out.println(DBOperations.viewStatus(sid));
					}
					else if(n==3) {
						DBOperations.viewStudents();
						System.out.println("---------------------------");
					}
					else {
						System.out.println("Wrong option selected");
					}
				}
				else if(n==2) {
					System.out.println("Enter the student Id");
					String sid = sc.next();
					System.out.println("1. Add Courses \n2. View Student Info");
					n = sc.nextInt();
					if(n==1) {
						//System.out.println("Show the courses and ask to for all the courses to be added");
						Map<Integer,Course> listCourses = new HashMap<Integer,Course>();
						//Course c = new Course("Computer Science 101");
						//System.out.println(insertCourse(c));
						//Student s = new Student("Sunit Mishra",2018);
						//insertStudent(s);
						listCourses = DBOperations.getCourses();
						System.out.println("Select the course number from the list of the courses given below separated by commas");
						for (Map.Entry<Integer,Course> entry : listCourses.entrySet())  
							System.out.println("Course Number: " + entry.getKey() + 
									", Course Name: " + entry.getValue().getCourseName()); 

						System.out.println("Capture all the course numbers in a list");
						String courseLine = sc.next();
						System.out.println(courseLine);
						String[] courseString = courseLine.split(",");
						List<Integer> courseList = new ArrayList<Integer>();
						for(int i = 0;i < courseString.length;i++) {
							System.out.println("Course # added "+courseString[i]);
							courseList.add(Integer.parseInt(courseString[i]));
						}
						DBOperations.enrollCourses(sid, courseList);
						System.out.println("Go to add Courses functions");
					}
					else if(n==2) {
						//System.out.println("Take the student id and show the student info" + " If they have an outstanding fee ask them if they want to pay the fee");
						ViewStudentStatus vs = DBOperations.viewStatus(sid);
						System.out.println(vs);

						if(vs.getFee()!=0) {
							System.out.println("Do you want to pay the fees?(Y/N)");
							ch = sc.next();
							if(ch.equalsIgnoreCase("Y")) {
								DBOperations.payFees(sid);
							}

						}
					}
				}
				else {
					System.out.println("Incorrect Choice selected. Please select 1 or 2");

				}
				System.out.println("Do you want to continue (Y/N)");
				ch = sc.next();

				//System.out.println("Choice selected was " + ch);

				if(!ch.equalsIgnoreCase("Y")){
					System.out.println("Thank you!");
				}

			}
			catch(Exception e){
				ch="N";
				System.out.println("Sorry! That was an incorrect choice. Please restart again");
			}

		}
		sc.close();
	}

	public static boolean addStudents() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of students you want to enter: ");
		Integer n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter the name of the student #" + (i + 1) + ":");
			String name = sc.next();

			System.out.println("Enter the year of the student #" + (i + 1) + ":");
			Integer year = 0;
			
			while(year == 0) {
				String line = sc.next();
				try {
					year = Integer.valueOf(line);
					
				}
				catch(Exception e) {
					System.out.println("You have entered an incorrect year. Please enter a correct year");
				}
			}
			System.out.println(name + " " + year);
			Student s = new Student(name, year);
			DBOperations.insertStudent(s);
		}
		sc.close();
		return true;
	}

}
