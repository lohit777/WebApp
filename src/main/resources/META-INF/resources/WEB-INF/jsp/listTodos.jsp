		<%@include file = "Common/Header.jspf" %>
	    <%@include file = "Common/navigation.jspf" %>
      <div class="container"> <!-- in bootstrap it is recommended to put all the content inside a dive with class container -->
	       <div> welcome to todos page ${name}</div>   <!-- to use this name attribute from login page we need to use SessionAttributes annotation --> 
	       <!--  <div>your todos are ${todos}</div>  -->
	       <hr>
	       <h1>your todos are</h1>
	       <table class="table"> <!-- bootstrap provides withh a class called container to get table properties -->
		       <thead> <!--  to give headings in the table -->
		       <!--  when creating a table all the headings should be in <thead> under <tr> and the content should be inside <tbody> -->
		       		<tr>
			       		<th>id</th>  <!-- table headings -->
			       		<th>Description</th>
			       		<th>Target date</th>
			       		<th>Is done?</th>
			       		<th></th>
			       		<th></th>
		       		</tr>
		       </thead>
		       <tbody>
			       <c:forEach items="${todos}" var="todo">
			       		<tr>
			       			<td>${todo.id }</td>
			       			<td>${todo.description}</td>
			       			<td>${todo.targetDate}</td>
			       			<td>${todo.done}</td>
			       			<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
			       			<td><a href="update-todo?id=${todo.id}" class="btn btn-success">UPDATE</a></td>
			       			<!-- In the statement ?id=${todo.id} we are passing id directly to the link.This id here is called queryparameters. -->
			       		</tr>
			       </c:forEach> <!-- We are looping through todos using Enhanched foreach loop. For that we have used JSTL library -->
			    </tbody>
	        </table>
	        <a href="add-todo" class="btn btn-success">add Todo</a><!-- Here we are trying to add a Todo -->
        </div>
        <%@include file = "Common/Footer.jspf" %>