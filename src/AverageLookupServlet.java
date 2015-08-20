
/*
 * THis servlet is used to loop up average grade for a specific student 
 * and/or a specific assignment type
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AverageLookup
 */
@WebServlet("/AverageLookup")
public class AverageLookupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AverageLookupServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//accepts parameters from client
		String student_idStr = request.getParameter("student_id");
		String assignment_type = request.getParameter("assignment_type");
		
		
		//validate inputs, if not valid, send error to client
		if(    (student_idStr == null || student_idStr.trim().equals(""))
			||((student_idStr == null || student_idStr.trim().equals("")) && (assignment_type == null || assignment_type.trim().equals(""))))
		{
			System.out.println("both are null");
			response.sendError(400, "invalid inputs");
		}
		
		//if valid, look up data
		else
		{
			DB db = new DB();
			Student student = db.getAverage(student_idStr, assignment_type);
			String result ="";
			
			if(student.getStudent_id()==0)
			{
				result = "Student Not Found!";
			}
			else
			{
				result += "<div class=\"jumbotron\">";
				result += "Student ID: " + student.getStudent_id() + "<br>";
				result += "Assignment Type: " + student.getAssignment_type() + "<br>";
				result += "Average Grade: " + student.getAverage() + "<br>";
				result += "</div>";
			}
			
			//actual logic goes here
			request.setAttribute("result", result);
			
			getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);
		}
	}

}
