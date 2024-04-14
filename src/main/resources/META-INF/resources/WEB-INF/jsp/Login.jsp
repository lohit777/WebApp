<html>
    <head>
        <title>
            Login Page
        </title>
    </head>
    <body>
    <div class="container">
       <!--   Welcome to login page ${name}   -->
        <!-- In this way we will be able to use the name passed from LoginController -->
        
         <h1>Login</h1>
         <pre>${errormessage}</pre>   <!-- This will throw the error message defined in java  -->
         <!--  pre tag in html is used for predefined values -->
         <form method="post"> <!-- To get inputs from the userr we use form tag -->
         <!--  Normally whenever we creatre a form by default it willl be a get method and the name and password(values) will be visible-->
         <!-- So in such cases we will use post method so we can't see the credentials -->
         <!-- If we want to send any secure information to a website it is recoommended to use post -->
         <!-- When we load the login page url it will be a Get request and when we give credentials it will be a post request -->
         Name: <input type="text" name="name">
          <!-- Here the Name field allows text type  and the value which we give here will go as part of query paramaeter-->
        <!-- Here the name field given in right hand side will go in  query parameters and give the name="Entered name" like below -->
        <!-- if you give Lohit in name field it will take as login?name="Lohit" -->
        Password: <input type="password" name="password">
         <input type="Submit">
         </form>
      </div>
    </body>
</html>