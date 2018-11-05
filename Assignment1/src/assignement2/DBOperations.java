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

public class DBOperations {
	/*public static void main(String[] args) throws ClassNotFoundException {
		Map<Integer,Course> listCourses = new HashMap<Integer,Course>();
		//Course c = new Course("Computer Science 101");
		//System.out.println(insertCourse(c));
		System.out.println(getNewId());
		//Student s = new Student("Sunit Mishra",2018);
		//insertStudent(s);
		viewStatus("01001");
		listCourses = getCourses();
		for (Map.Entry<Integer,Course> entry : listCourses.entrySet())  
            System.out.println("Course Number: " + entry.getKey() + 
                             ", Course Name: " + entry.getValue().getCourseName()); 
		//Enroll courses
		//sid, List<Integer> cid
//		List<Integer> coursesId = new ArrayList<Integer>();
//		coursesId.add(1);
//		coursesId.add(2);
//		coursesId.add(5);
//		enrollCourses("01001", coursesId);
		System.out.println(viewStatus("01001"));
	}*/
	
	
	
	public static boolean insertStudent(Student s) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
			PreparedStatement pstmt = conn.prepareStatement("insert into" +" stdnt.student (sid,s_name,year, fee)" + 
					" values(?,?,?,?)" );
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int grade = currentYear-s.getYear();
			String genSid = Integer.toString(grade) + getNewId();
			pstmt.setString(1, genSid);
			pstmt.setString(2, s.getName());
			pstmt.setInt(3, s.getYear());
			pstmt.setFloat(4, 0.0f);
			pstmt.execute();
			pstmt.close();
			conn.close();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static ViewStudentStatus viewStatus(String id) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		ViewStudentStatus vs = new ViewStudentStatus();
		List<Course> courses = new ArrayList<>();
		Map<Integer,Course> mapCourses = new HashMap<Integer,Course>(); 
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from stdnt.student where sid = ?");
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
				//System.out.println("Sid: "+ sid  +"\n"+"Name: "+ name + "\n" + "Year: " + year + "\n" + "Fee: " + fee);
			}
			
			
			pstmt = conn.prepareStatement("select cid from stdnt.student_course where sid = ?");
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			mapCourses = getCourses();
			while(rs.next()) {
				Integer cid = rs.getInt("cid");
				courses.add(new Course(mapCourses.get(cid).getCourseName()));
			}
			vs.setCourses(courses);
			rs.close();
			pstmt.close();
			conn.close();
			
			return vs;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean insertCourse(Course c) throws ClassNotFoundException {
		//register the driver
				Class.forName("org.postgresql.Driver");
				//create the connection
				//URL Syntax : "jdbc:<DBTYPE>:<HOST>:<PORT>:<DB>
				try {
					//Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432:otf_data", "postgres", "root");
					Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
					PreparedStatement pstmt = conn.prepareStatement("insert into" +" stdnt.course (course_name)" + 
							" values(?)" );
					Statement stmt = conn.createStatement();
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
					pstmt.close();
					stmt.close();
					conn.close();
					//Handle exception
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return false;
	}
	
	
	public static String getNewId() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select sid from stdnt.student");
			rs.afterLast();
			while(rs.previous()) {
				String str = rs.getString("sid");
				String sub_str = str.substring(1, str.length());
				int result = Integer.parseInt(sub_str);
				result+=1;
				sub_str = Integer.toString(result);
				//System.out.println(sub_str);
				return sub_str;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<Integer,Course> getCourses() throws ClassNotFoundException {
		Map<Integer,Course> mapCourses = new HashMap<Integer,Course>();
		Class.forName("org.postgresql.Driver");
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from stdnt.course");
			while(rs.next()) {
				Integer id = rs.getInt("cid");
				String name = rs.getString("course_name");
				mapCourses.put(id, new Course(name));
				
				}
				
			//close resources in reverse order of creation
			rs.close();
			stmt.close();
			conn.close();
			return mapCourses;
			//Handle exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//Insert into the student_course table and update the fee in the student table
	public static boolean enrollCourses(String sid, List<Integer> cid) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
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
			conn.close();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean payFees(String sid) throws ClassNotFoundException {
		
		Class.forName("org.postgresql.Driver");
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
			
			PreparedStatement pstmt = conn.prepareStatement("update stdnt.student" + " set fee = ?" 
			+ " where sid = ?");
			pstmt.setFloat(1, 0);
			pstmt.setString(2, sid);
			pstmt.executeUpdate();
			conn.close();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return false;
	}
	
	public static List<String> getUnregisteredCourses(String sid){
		return null;
	
	}
	public static void viewStudents() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		ViewStudentStatus vs = new ViewStudentStatus();
		List<Course> courses = new ArrayList<>();
		Map<Integer,Course> mapCourses = new HashMap<Integer,Course>(); 
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from stdnt.student");
			
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
				System.out.println("Student Id: "+ sid  +"\n"+"Name: "+ name + "\n" + "Year: " + year + "\n" + "Outstanding Fee: " + fee);
				System.out.println("\n");
			}
			
			
			
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
