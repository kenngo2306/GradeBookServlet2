import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/*
 * This class handles in and out data from/to the database
 */



public class DB 
{
	public static Connection getConnection() throws SQLException 
	{
	    //URL of Oracle database server
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	String url = "jdbc:oracle:thin:testuser/password@localhost";
	    //properties for creating connection to Oracle database
	    Properties props = new Properties();
	    props.setProperty("user", "testdb");
	    props.setProperty("password", "password");
	  
	    //creating connection to Oracle database using JDBC
	    Connection conn = DriverManager.getConnection(url,props);
    
	    return conn;
	}
	
	//select sql method to accepts a SQL String and return a resultset
	public ResultSet selectSQL(String sql)
	{
		Connection conn = null;
		ResultSet result = null;
		try
		{
			conn =  DB.getConnection();
			PreparedStatement preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//method to turn a resultset to a assignment object
	public Assignment resultToAssignment(ResultSet result)
	{
		Assignment assignment = new Assignment();
		
		try
		{
			assignment.setStudent_id(result.getInt("STUDENT_ID"));
			assignment.setAssignment_name(result.getString("ASSIGNMENT_NAME"));
			assignment.setAssignment_type(result.getString("ASSIGNMENT_TYPE"));
			assignment.setAssignment_date(result.getDate("ASSIGNMENT_DATE"));
			assignment.setGrade(result.getDouble("GRADE"));
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return assignment;
	}
	
	//method to get a average grad with a specific student id and assignment type
	public Student getAverage(String student_idStr, String assignment_type)
	{
		ArrayList<Assignment> assignments= new ArrayList<Assignment>();
		String whereClause = "";
		
		boolean hasStudentId = true;
		boolean hasType = false;
		

		Student student = new Student();
		if(assignment_type == null || assignment_type.trim().equals(""))
		{
			//DO NOTHING
		}
		else
		{
			hasType = true;
		}
		
		if (hasStudentId)
		{
			whereClause +=" WHERE STUDENT_ID = " + student_idStr;
			
			if (hasType)
			{
				whereClause += " AND ASSIGNMENT_TYPE = '" + assignment_type + "'";
				student.setAssignment_type(assignment_type);
			}
		}
		else
		{
			whereClause +=" WHERE ASSIGNMENT_TYPE = '" + assignment_type + "'";
			
		}
		
		String sql = "SELECT STUDENT_ID, AVG(GRADE) AS AVG FROM ASSIGNMENT "+ whereClause +" GROUP BY STUDENT_ID";
		System.out.println(sql);
		ResultSet result = selectSQL(sql);
		
		try
		{
			while (result.next())
			{
				
				student.setStudent_id(result.getInt("STUDENT_ID"));
				student.setAverage(result.getDouble("AVG"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student;
	}
	
	//method to get all assignments from the database
	public ArrayList<Assignment> getAllAssignments()
	{
		ArrayList<Assignment> assignments= new ArrayList<Assignment>();
		
		
		String sql = "SELECT * FROM ASSIGNMENT ";
		System.out.println(sql);
		ResultSet result = selectSQL(sql);
		
		try
		{
			while (result.next())
			{
				Assignment assignment = new Assignment();
				assignment = resultToAssignment(result);
				assignments.add(assignment);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return assignments;
	}
	
	//method to get all assignments from the database with a specific student id and an assignment type
	public ArrayList<Assignment> getAssignments(String student_idStr, String assignment_type)
	{
		ArrayList<Assignment> assignments= new ArrayList<Assignment>();
		String whereClause = "";
		
		boolean hasStudentId = false;
		boolean hasType = false;
		
		if(student_idStr == null || student_idStr.trim().equals(""))
		{
			//DO NOTHING
		}
		else
		{
			hasStudentId = true;
		}
		
		if(assignment_type == null || assignment_type.trim().equals(""))
		{
			//DO NOTHING
		}
		else
		{
			hasType = true;
		}
		
		if (hasStudentId)
		{
			whereClause +=" WHERE STUDENT_ID = " + student_idStr;
			
			if (hasType)
			{
				whereClause += " AND ASSIGNMENT_TYPE = '" + assignment_type + "'";
			}
		}
		else
		{
			whereClause +=" WHERE ASSIGNMENT_TYPE = '" + assignment_type + "'";
		}
		
		String sql = "SELECT * FROM ASSIGNMENT " + whereClause;
		System.out.println(sql);
		ResultSet result = selectSQL(sql);
		
		try
		{
			while (result.next())
			{
				Assignment assignment = new Assignment();
				assignment = resultToAssignment(result);
				assignments.add(assignment);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return assignments;
	}
	
	//method to insert a assignment to the database
	public void insertAssignment(Assignment assignment)
	{
		String insertStatement = "INSERT INTO ASSIGNMENT (STUDENT_ID,ASSIGNMENT_NAME,ASSIGNMENT_TYPE,ASSIGNMENT_DATE,GRADE) VALUES (?,?,?,?,?)";
		try
		{
			//convert utilDate to sqlDate
			java.util.Date utilDate = assignment.getAssignment_date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    
			PreparedStatement prepareStatement = getConnection().prepareStatement(insertStatement);
			prepareStatement.setInt(1, assignment.getStudent_id());
			prepareStatement.setString(2, assignment.getAssignment_name());
			prepareStatement.setString(3, assignment.getAssignment_type());
			prepareStatement.setDate(4, sqlDate);
			prepareStatement.setDouble(5, assignment.getGrade());
			prepareStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//method to get min and max values of a specific assignment type
	public double[] getMinMax(String assignmentType)
	{
		double[] minmax = new double[2];
		minmax[0] = getMin(assignmentType);
		minmax[1] = getMax(assignmentType);
		
		return minmax;
	}
	
	public double getMin(String assignmentType)
	{
		double min = 0;
		String sql = "SELECT MIN(GRADE) AS MIN FROM ASSIGNMENT WHERE ASSIGNMENT_TYPE = '" + assignmentType +"'";
		System.out.println(sql);
		ResultSet result = selectSQL(sql);
		try
		{
			while(result.next())
			{
				min = result.getDouble("MIN");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			min = -1;
		}
		return min;
		
	}
	
	public double getMax(String assignmentType)
	{
		double max = 0;
		String sql = "SELECT MAX(GRADE) AS MAX FROM ASSIGNMENT WHERE ASSIGNMENT_TYPE = '" + assignmentType +"'";
		System.out.println(sql);
		ResultSet result = selectSQL(sql);
		try
		{
			while(result.next())
			{
				max = result.getDouble("MAX");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			max = -1;
		}
		return max;
	}
	
}
