
/*
 * This servlet is used to accepts parameter of a assignment object, 
 * validate all fields, 
 * and insert the object to the database
 */
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AssignmentServlet
 */
@WebServlet("/Assignment")
public class AssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignmentServlet() {
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
		// TODO Auto-generated method stub
		
		//get parameters
		String student_idStr = request.getParameter("student_id");
		String assignment_name = request.getParameter("assignment_name");
		String assignment_type = request.getParameter("assignment_type");
		String dateStr = request.getParameter("date");
		String gradeStr = request.getParameter("grade");
		
		//validate all inputs
		boolean isValid = true;
		boolean isValidGrade = true;
		boolean isValidDate =true;
		boolean isValidStudentID = true;
		isValid = Validator.validateNullEmptyString(student_idStr) &&
				Validator.validateNullEmptyString(assignment_name) &&
				Validator.validateNullEmptyString(assignment_type) &&
				Validator.validateNullEmptyString(dateStr) &&
				Validator.validateNullEmptyString(gradeStr);
		String errorMessage = "";
		if (!isValid)
		{
			errorMessage += "Inputs cannot be null \n";
		}
		
		isValidGrade = isValidGrade && Validator.validateDoubleWithRange(gradeStr, 0, 100);
		if (!isValidGrade)
		{
			errorMessage += "Grade should be between 0 and 100 \n";
		}
		
		isValidDate = isValidDate && Validator.validateDateWithFormat(dateStr);
		if (!isValidDate)
		{
			errorMessage += "Date format should be mm/dd/yyyy \n";
		}
		
		isValidStudentID = isValidStudentID && Validator.validateInt(student_idStr);
		if (!isValidStudentID)
		{
			errorMessage += "Invalid student id \n";
		}
		
		//if no error, continues
		if(isValid && isValidGrade && isValidDate & isValidStudentID)
		{
			//format data
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date assignment_date = new Date();
			try
			{
				assignment_date = sdf.parse(dateStr);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double grade = Double.parseDouble(gradeStr);
			int student_id = Integer.parseInt(student_idStr);
			
			// initialize assignment object
			Assignment assignment = new Assignment(student_id,assignment_name,assignment_type,assignment_date,grade);
			
			//save assignment object to the database
			DB db = new DB();
			db.insertAssignment(assignment);
			
			//generate result as a add more button and forward to Result.jsp to display the result
			String result = "<p>Assignment Created!</p><a class = \"button btn-primary\" href = \"/StrongHeimGradebook/AssignmentForm.jsp\" > Add More </a>";
			request.setAttribute("result", result);
			getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);
		}
		
		//if there's a error, sendError to client
		else
		{
			response.sendError(400, errorMessage);
		}

	}

}
