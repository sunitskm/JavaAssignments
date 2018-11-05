package assignement2;

public class StudentCourse {
	private String studentId;
	private Integer courseId;
	public StudentCourse(String studentId, Integer courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	

}
