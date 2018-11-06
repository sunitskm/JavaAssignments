package assignement2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DBOperations {
	
//	public static void main(String[] args) throws ClassNotFoundException {
//		Map<Integer,Course> listCourses = new HashMap<Integer,Course>();
//		Course c = new Course("Political Science 101");
//		System.out.println(insertCourse(c));
		//System.out.println(validateId("12100"));
		//Student s = new Student("Sunit Mishra",2018);
		//insertStudent(s);
		//viewStatus("01001");
//		listCourses = getCourses();
//		for (Map.Entry<Integer,Course> entry : listCourses.entrySet())  
//            System.out.println("Course Number: " + entry.getKey() + 
//                             ", Course Name: " + entry.getValue().getCourseName()); 
		//Enroll courses
		//sid, List<Integer> cid
//		List<Integer> coursesId = new ArrayList<Integer>();
//		coursesId.add(1);
//		coursesId.add(2);
//		coursesId.add(5);
//		enrollCourses("01001", coursesId);
		//System.out.println(viewStatus("01001"));
		//viewStudents();
//	}
	
	
	
	public static boolean insertStudent(Student s) throws ClassNotFoundException {
		DBUtil db = new DBUtil();
		try(Connection conn = db.getConnection();PreparedStatement pstmt = conn.prepareStatement("insert into" +" stdnt.student (sid,s_name,year,fee)" + 
				" values(?,?,?,?)" );) {
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int grade = currentYear-s.getYear()+1;
			String genSid = null;
			if(grade>9) {
				grade = 9;
			}
			while(true) {
				Random r = new Random();
			    String randomNumber = String.format("%04d", r.nextInt(9999));
			    //System.out.println(randomNumber);
			    genSid = Integer.toString(grade) + randomNumber;
				if(validateId(genSid))
					break;
			}
			System.out.println(genSid);
			pstmt.setString(1, genSid);
			pstmt.setString(2, s.getName());
			pstmt.setInt(3, s.getYear());
			pstmt.setFloat(4, 0.0f);
			if(conn!=null) {
				System.out.println("Connection has not ended");
			}

			int x = pstmt.executeUpdate();
			System.out.println(x);
			if(pstmt!=null) {
				System.out.println("Connection has not ended");
			}
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static ViewStudentStatus viewStatus(String id) throws ClassNotFoundException {
		//Class.forName("org.postgresql.Driver");
		ViewStudentStatus vs = new ViewStudentStatus();
		List<Course> courses = new ArrayList<>();
		Map<Integer,Course> mapCourses = new HashMap<Integer,Course>(); 
		DBUtil db = new DBUtil();
		try(Connection conn = db.getConnection()
				;PreparedStatement pstmt = conn.prepareStatement("select * from stdnt.student where sid = ?")
				;PreparedStatement pstmt1 = conn.prepareStatement("select cid from stdnt.student_course where sid = ?")) {
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String sid = rs.getString("sid");
				vs.setSid(sid);
				String name = rs.getString("s_name");
				vs.setName(name);
				Integer year = rs.getInt("year");
				vs.setYear(year);
				Float fee = rs.getFloat("fee");
				vs.setFee(fee);
			}
			pstmt1.setString(1,id);
			rs = pstmt1.executeQuery();
			mapCourses = getCourses();
			while(rs.next()) {
				Integer cid = rs.getInt("cid");
				courses.add(new Course(mapCourses.get(cid).getCourseName()));
			}
			vs.setCourses(courses);
			rs.close();
			return vs;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean insertCourse(Course c) throws ClassNotFoundException {
		//register the driver
				//Class.forName("org.postgresql.Driver");
				//create the connection
				//URL Syntax : "jdbc:<DBTYPE>:<HOST>:<PORT>:<DB>
				DBUtil db = new DBUtil();
				try(Connection conn = db.getConnection()
						;PreparedStatement pstmt = conn.prepareStatement("insert into" +" stdnt.course (course_name)" + 
						" values(?)" )
						;Statement stmt = conn.createStatement()) {
					pstmt.setString(1,c.getCourseName());
					pstmt.execute();
					ResultSet rs = stmt.executeQuery("select * from stdnt.course");
					while(rs.next()) {
						Integer id = rs.getInt("cid");
						String name = rs.getString("course_name");
						
						System.out.println(id + " "+ name + " ");
					}
					//close resources in reverse order of creation
					rs.close();
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return false;
	}
	
	
	public static boolean validateId(String sid) throws ClassNotFoundException {
		DBUtil db = new DBUtil();
		try(Connection conn = db.getConnection()
				;PreparedStatement pstmt = conn.prepareStatement("select * from stdnt.student where sid = ?");) {
			pstmt.setString(1, sid);
			ResultSet rs = pstmt.executeQuery();
			if(!rs.isBeforeFirst()) 
				return true;
			rs.close();
			pstmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Map<Integer,Course> getCourses() throws ClassNotFoundException {
		Map<Integer,Course> mapCourses = new HashMap<Integer,Course>();
		DBUtil db = new DBUtil();
		try(Connection conn = db.getConnection()
				;Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from stdnt.course");
			while(rs.next()) {
				Integer id = rs.getInt("cid");
				String name = rs.getString("course_name");
				mapCourses.put(id, new Course(name));
				
				}
			return mapCourses;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Insert into the student_course table and update the fee in the student table
	public static boolean enrollCourses(String sid, List<Integer> cid) throws ClassNotFoundException {
		DBUtil db = new DBUtil();
		try(Connection conn = db.getConnection()) {
			for(int i = 0;i < cid.size(); i++) {
				PreparedStatement pstmt = conn.prepareStatement("insert into" +" stdnt.student_course (sid,cid)" + 
						" values(?,?)" );
				pstmt.setString(1, sid);
				pstmt.setInt(2, cid.get(i));
				pstmt.execute();
				pstmt.close();
			}
			Float feeCourse = (Float)(cid.size()*600.0f);
			PreparedStatement pstmt = conn.prepareStatement("update stdnt.student" + " set fee = ?" 
			+ " where sid = ?");
			pstmt.setFloat(1, feeCourse);
			pstmt.setString(2, sid);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean payFees(String sid) throws ClassNotFoundException {
		
		DBUtil db = new DBUtil();
		try(Connection conn = db.getConnection()
				;PreparedStatement pstmt = conn.prepareStatement("update stdnt.student" + " set fee = ?" 
				+ " where sid = ?")) {
			pstmt.setFloat(1, 0);
			pstmt.setString(2, sid);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}
	
	public static List<String> getUnregisteredCourses(String sid){
		return null;
	
	}
	public static void viewStudents() throws ClassNotFoundException {
		DBUtil db = new DBUtil();
		ViewStudentStatus vs = new ViewStudentStatus();
		
		try(Connection conn = db.getConnection()
				;Statement stmt = conn.createStatement()
				;ResultSet rs = stmt.executeQuery("select * from stdnt.student")) {
			while(rs.next()) {
				String sid = rs.getString("sid");
				vs.setSid(sid);
				String name = rs.getString("s_name");
				vs.setName(name);
				Integer year = rs.getInt("year");
				vs.setYear(year);
				Float fee = rs.getFloat("fee");
				vs.setFee(fee);
				System.out.println("Student Id: "+ sid  +"\n"+"Name: "+ name + "\n" + "Year: " + year + "\n" + "Outstanding Fee: " + fee);
				System.out.println("\n");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
