package com.in28minutes.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") //It is used if we want to hold the value until the next page.
//We need to pass the attribute which is required in next controller/page as parameter to the seesionattribute
//If we give sessionattributes we need to make sure that we are using the attribute in all the controllers also
public class WelcomeController {
	
	@RequestMapping(value="/",method=RequestMethod.GET)  //When user bits the root of the url / we will go to Welcome Page
	//Login will be taken care by  spring security
	//The method goToWelcomepage and LoginController is also not required
	public String gotoWelcomePage(ModelMap model)
	{
		//model.put("name", "Lohit") ; // We are hardcoding the name
		model.put("name",getLoggedInUsername()  ) ; //Instead of hardcoding the username we are getting it with the help of spring security
		return "Welcome" ; 
	}
	//In the below method we are getting the logged in username using springsecurity
	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
		//In spring security we will have a class called SecurityContextHolder inside which we will have getcontext inside which we will, have getAuthentication
	   return authentication.getName() ;
	}
	//We are doing constructor based injection of  AuthenticationService
//	private AuthenticationService authenticationservice ;
//	
//	public LoginController(AuthenticationService authenticationservice) {
//		super();
//		this.authenticationservice = authenticationservice;
//	}
	//System.out.println is not recommended to log messages. Instead we use logger
//	private Logger logger = LoggerFactory.getLogger(getClass()) ;
	
   //In this we are trying to build a  web application when we it the url /login it should get the content from login.jsp file
	//We can pass a name in the url  and print it in output
	//http://localhost:8080/login?name=Lohit -> Using requestparam we will tell logincontroller that we will get a requestr parameter with name "name"
	//If we want to pass the value to the jsp instead of printing in console we will pass it using "MODEL"
///	@RequestMapping("login") 
//	public String gotoLoginPage(@RequestParam String name, ModelMap model)
//	{
//		model.put("name",name); //Here we are passing the variable name  to jsp  using model
//		logger.debug("Request param is {}", name);//Instead of sout we can use logger to log anything in console.before this we need to enable debug level logs ihn application.properties
//		//we will get like this in console c.i.s.m.login.LoginController            : Request param is Lohit
//		//If you want to log messages under info level use as below
 //		//spring-boot-starter-logging will help us to add logging levels. It comes under spring-boot-starter-web
//		logger.info("I want to print this at info level");
//		System.out.println("Request param is"+ name); // Based on what name value you givve in the url it will be printed in console.
//		return "Login" ; //this jsp file is called as a view. it is easier to format. We need to add tomcat-embed-jasper for the jsp file.
//	}
	
	
	

	//This method handles bot h the get and post requests.
	//We can restrict it to only handle get requests or post requests by using an additional parameter requestMethod
	//Initially when login page is loaded it will be a get request but as in views we gave form method = post the request type will be post after giving the credentials.
	//Login jsp only handles get requests as we have given method= GET
//	@RequestMapping(value="login",method=RequestMethod.GET) 
//	public String gotoLoginPage()
//	{
//		
//		return "Login" ; //this jsp file is called as a view. it is easier to format. We need to add tomcat-embed-jasper for the jsp file.
//	}
	
	
	//Here after giving the credentials and clicking on submit as the request type will be post we are navigating to 
	//Welcome page after entering the credentials and clicking on submit.
//	@RequestMapping(value="login",method=RequestMethod.POST) 
//	//To display the username and password with which we have loggedin on screen use requestparams and models
//	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password,ModelMap model)
//	{
//		//We are authenticating username and password using authenticationservice.
//		//We will redirect to welcome page only if the username and password are validated as in the AuthenticationService
//		if(authenticationservice.authenicate(name, password))
//		{
//		model.put("password", password); //Whenever you put something in model it will be only available for that request i.e., login POST request in this case.
//		//If you want to make the value last longer you need to keep it in something called session
//		model.put("name", name) ;
//		return "Welcome" ; 
//		}
//		
//		model.put("errormessage","Invalid credentials. Please try again"); //In case of invalid credentials
//		//we can give like above also to raise error message and show it  in view
//		return "Login" ;
//	}
//	
}
