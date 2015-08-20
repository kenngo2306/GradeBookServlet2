<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Add Assignment</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Grade Book Application</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
      	<li><a href="/StrongHeimGradebook/AssignmentForm.jsp">Add Assignment Grade</a></li>
        <li><a href="/StrongHeimGradebook/AssignmentLookup.jsp">Assignment Report</a></li>
        <li><a href="/StrongHeimGradebook/AverageLookup.jsp">Average Grade Report</a></li>
        <li><a href="/StrongHeimGradebook/MinMaxLookup.jsp">Min Max Grade Report</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="col-md-3 col-md-offset-4">
	<form role="form" action="Assignment" method="POST">
		<div class="form-group">
			<label for="student_id">Student ID:</label>
			<input class="form-control" id="student_id" name="student_id">
		</div>
		
		<div class="form-group">
			<label for="assignment_name">Assignment Name:</label>
			<input class="form-control" id="assignment_name" name="assignment_name">
		</div>
		
		
		<div class="form-group">
			<div class="radio">
				  <label><input type="radio" name="assignment_type" value="homework">homework</label>
			</div>
			<div class="radio">
				  <label><input type="radio" name="assignment_type" value="quiz">quiz</label>
			</div>
			<div class="radio">
				  <label><input type="radio" name="assignment_type" value="test">test</label>
			</div>
			<div class="radio">
				  <label><input type="radio" name="assignment_type" value="project">project</label>
			</div>
		</div>
		
		<div class="form-group">
			<label for="date">Date:</label>
			<input class="form-control" id="date" name="date">
		</div>
		
		<div class="form-group">
			<label for="grade">Grade:</label>
			<input class="form-control" id="grade" name="grade">
		</div>
		
		<input type="submit" value="Submit" class="btn btn-primary">
	</form>
</div>
</body>
</html>