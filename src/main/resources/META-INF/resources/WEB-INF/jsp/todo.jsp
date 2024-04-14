    <%@include file = "Common/Header.jspf" %>
    <%@include file = "Common/navigation.jspf" %> 
    
      <div class="container"> <!-- in bootstrap it is recommended to put all the content inside a dive with class container -->
	       <div> welcome to todos page ${name}</div>   <!-- to use this name attribute from login page we need to use SessionAttributes annotation --> 
	       
	       <h1>Enter todo details</h1>
	       <form:form method="post" modelAttribute="todo"> <!-- Here we are mapping the todo in todocontroller.java which is given in add without using requestparam -->
	       
	       <fieldset class="mb-3">
		       <form:label path="description">Description</form:label>
		       <form:input type="text" path="description" required="required"/>
		       <form:errors path="description" cssClass="text-warning"/> <!-- This form tag is used to shw the error given as messsage under @size validation -->
	      </fieldset> <!-- In HTML we can group all the fields related to a attribute under filedset -->
	       <!--  Description: <input type="text" name="description" required="required"/>--> <!-- here required makes sure to enter the value for sure under description -->
	       
	     <!--    Description: <form:input type="text" path="description" required="required"/>--><!-- here we are trying to map description variable inside Todo bean in ToDo.java -->
	       
	       <fieldset class="mb-3">
		       <form:label path="targetDate">targetDate</form:label>
		       <form:input type="text" path="targetDate" required="required"/>
		       <form:errors path="targetDate" cssClass="text-warning"/> <!-- This form tag is used to shw the error given as messsage under @size validation -->
	      </fieldset> 
	        <form:input type="hidden" path="id" />
	        
	        <form:input type="hidden" path="done" /><!-- Here we are creating 2 hidden fields so that validations don't fail for not having id and done fields -->
	       <input type="submit" class="btn btn-success"/>
	       </form:form>
	       
        </div> 
         <%@include file = "Common/Footer.jspf" %> 
        
        <script type="text/javascript"> 
        $('#targetDate').datepicker({
            format: 'yyyy-mm-dd'  
        });
        </script>
    