import java.util.Date;


public class Assignment
{
	private int student_id;
	private String assignment_name,assignment_type;
	private Date assignment_date;
	private double grade;
	public static String getTableHeader()
	{
		String header = "<thead>";
		header += "<tr>";
		header += "<th>";
		header += "Student ID";
		header += "</th>";
		header += "<th>";
		header += "Assignment Name";
		header += "</th>";	
		header += "<th>";
		header += "Assignment Type";
		header += "</th>";	
		header += "<th>";
		header += "Assignment Date";
		header += "</th>";	
		header += "<th>";
		header += "Grade";
		header += "</th>";	
		
		header += "</tr>";
		header += "</thead>";
		
		return header;
	}
	public int getStudent_id()
	{
		return student_id;
	}
	public void setStudent_id(int student_id)
	{
		this.student_id = student_id;
	}
	public String getAssignment_name()
	{
		return assignment_name;
	}
	public void setAssignment_name(String assignment_name)
	{
		this.assignment_name = assignment_name;
	}
	public String getAssignment_type()
	{
		return assignment_type;
	}
	public void setAssignment_type(String assignment_type)
	{
		this.assignment_type = assignment_type;
	}
	public Date getAssignment_date()
	{
		return assignment_date;
	}
	public void setAssignment_date(Date assignment_date)
	{
		this.assignment_date = assignment_date;
	}
	public double getGrade()
	{
		return grade;
	}
	public void setGrade(double grade)
	{
		this.grade = grade;
	}
	public Assignment(int student_id, String assignment_name,
			String assignment_type, Date assignment_date, double grade)
	{
		super();
		this.student_id = student_id;
		this.assignment_name = assignment_name;
		this.assignment_type = assignment_type;
		this.assignment_date = assignment_date;
		this.grade = grade;
	}
	public Assignment()
	{
		super();
	}
	
	
}
