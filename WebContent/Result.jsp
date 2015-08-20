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
<title>Result</title>
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
	${result}
</body>
</html>