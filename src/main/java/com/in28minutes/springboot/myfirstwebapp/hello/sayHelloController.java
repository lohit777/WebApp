package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class sayHelloController {
	
	
	@RequestMapping("say-hello") //when you hit the url say-hello it will return "Hello what are you doing today!"
	@ResponseBody  //Without this we will get error when we hit the url because spring expects return as String directly but not the statement. 
	//But @ResponseBody returns the statement as is to be displayed on the browser.
	public String sayHello()
	{
		return "Hello what are you doing today!" ;
	}
	
	
	//We can use html to display the  above text in screen
	@RequestMapping("say-hello-html") //when you hit the url say-hello it will return "Hello what are you doing today!"
	@ResponseBody  //Without this we will get error when we hit the url because spring expects return as String directly but not the statement. 
	//But @ResponseBody returns the statement as is to be displayed on the browser from the return statement.
	public String sayHelloHtml()
	{
		//Writing code with html as below is really difficult
		//So we will use instead of this
		//On real world web pages are complex so we use html
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>My first web app</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body");
		sb.append("</body>");
		sb.append("</html>");
		

		return sb.toString() ;
	}
	
	//Managing html code like above will be really tough.
	//So for that we go for views.
	//one of the most  popular view technology is jsp.
	//If we have 10 jsps we can creater those under this path.
	 // /myfirstwebapp/src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	//If we want to create a login file 
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	//just the jsp file name will be changed rest all will be constant.
	// As the entire path is constant other than name we can define it in application.properties file as prefix and suffix
	@RequestMapping("say-hello-jsp") //If we hit the url say-hello-jsp it will go to sayHello.jsp file based on the return statement
	//@ResponseBody  - here we don't need response body as we don't need the return statement value directly.Instead it shou;d redirect to a view.
	public String sayHelloJsp()
	{
		return "sayHello" ; //this jsp file is called as a view. it is easier to format. We need to add tomcat-embed-jasper for the jsp file.
	}
	//If you want to check how it works in web you can go to inspect in the ewebpage and then Network -> doc. In that you will find the type of the request.
	//In this scenario the type of the request is get request. You will also find the status code whether it is success or failure.
	
	
	//How does Web work
	// Whenever we execute a url browser sends a request to our application  and this request is called as http request.
	// Our Application/Server handles the request and sends the response back and this is called httpresponse
	

}
