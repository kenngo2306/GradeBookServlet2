
public class Student
{
	private int student_id = 0;
	private double average;
	private String assignment_type = "all";
	
	
	public String getAssignment_type()
	{
		return assignment_type;
	}
	public void setAssignment_type(String assignment_type)
	{
		this.assignment_type = assignment_type;
	}
	public int getStudent_id()
	{
		return student_id;
	}
	public void setStudent_id(int student_id)
	{
		this.student_id = student_id;
	}
	public double getAverage()
	{
		return average;
	}
	public void setAverage(double average)
	{
		this.average = average;
	}
	
	
}
