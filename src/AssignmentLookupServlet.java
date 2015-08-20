

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AssignmentLookupServlet
 */
@WebServlet("/AssignmentLookup")
public class AssignmentLookupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignmentLookupServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String student_idStr = request.getParameter("student_id");
		String assignment_type = request.getParameter("assignment_type");
		
		
		DB db = new DB();
		ArrayList<Assignment> assignments;
		String result = "<table class = \"table table-striped table-bordered\">";
		result += Assignment.getTableHeader();
		
		if((student_idStr == null || student_idStr.trim().equals(""))
				&& (assignment_type == null || assignment_type.trim().equals("")))
		{
			System.out.println("both are null");
			assignments =db.getAllAssignments();
		}
		
		else
		{
			assignments = db.getAssignments(student_idStr, assignment_type);
		}
		
		for(Assignment assignment: assignments)
		{
			result += "<tr>";
				result += "<td>";
				result += assignment.getStudent_id();
				result += "</td>";
				result += "<td>";
				result += assignment.getAssignment_name();
				result += "</td>";
				result += "<td>";
				result += assignment.getAssignment_type();
				result += "</td>";
				result += "<td>";
				result += assignment.getAssignment_date();
				result += "</td>";
				result += "<td>";
				result += assignment.getGrade();
				result += "</td>";
			result += "</tr>";
		}
		result += "</table>";
		
		//actual logic goes here
		request.setAttribute("result", result);
		
		getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);

	}

}
