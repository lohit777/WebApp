package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.myfirstwebapp.login.WelcomeController;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService ;
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	@RequestMapping("list-todos") //here we are just navigating to list-todos and trying to show todos on screen .
	public String listAllTodos(ModelMap model){
		//List<Todo> todos = todoService.findByUsername("in28minutes") ;
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoService.findByUsername(username) ; //Instead of hardcoding the username we are getting it from model
		model.addAttribute("todos", todos) ;
		return "listTodos" ;
		
	}
	
	
	@RequestMapping(value="add-todo", method = 	RequestMethod.GET)
	public String showNewTodoPage(ModelMap model){
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0,username,"",LocalDate.now().plusYears(1), false);//Here we are passing default values to model ToDo for modelattribute in jsp
		//These values will be set by default when we go to todo add page
		//Here we are binding the values to the bean when in GET method.
		model.put("todo", todo) ;
		return "todo" ;
		
	}
	@RequestMapping(value="add-todo", method = 	RequestMethod.POST)
	//public String addNewTodoPage(@RequestParam String description,ModelMap model)
	public String addNewTodoPage(ModelMap model,@Valid Todo todo, BindingResult result)//Here insteadof using requestparam to get the values from user
	//we are directly binding it to todo bean as if there are multiple inputs from user it will be difficult to bimd each request.
	//This is called form backing object. We need to use this in jsp for that we need spring form tag libraries
	//Once we enter the value in the add todo page and click on submit this post method executes and the description
	//value given in text will be mapped to todo method here using 2 way binding
	//Wherever we  are validating the variable of any bean we need to give @valid before the bean.
	{
		if(result.hasErrors()) {
			return "todo" ;// we will check whether validation is successful or not using bindingresult.
			//Here if we are getting any errors in valifdation we are redirecting to todo page
		}
		String username = getLoggedInUsername(model); //We are getting the name of the user who logged in
		todoService.addToDo(username, todo.getDescription(),todo.getTargetDate(), false);
		return "redirect:list-todos" ; //Here redirect will direct add-todo page to list todos page having same values which  list-todo has initially
		//If we return listtodos directly without redirecting it will not print the todos
		
	}
	
	@RequestMapping("delete-todo") 
	public String deleteToDo(@RequestParam int id)//Here we are getting the id from listtodos.jsp
	{
		todoService.deleteById(id);
		return "redirect:list-todos" ;
		
	}
	
	@RequestMapping(value="update-todo",method = 	RequestMethod.GET) //When you ewant to show a updatetodo page you will send a get request
	public String showUpdateToDoPage(@RequestParam int id,ModelMap model)//Here we are getting the id from listtodos.jsp
	{
		Todo todo = todoService.findById(id) ;
		model.addAttribute("todo",todo) ;
		return "todo" ;
		
	}
	@RequestMapping(value="update-todo", method = 	RequestMethod.POST)//When you ewant to update a todo in updatetodo page you will send a post request
	public String updateToDo(ModelMap model,@Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors()) {
			return "todo" ;// we will check whether validation is successful or not using bindingresult.
			//Here if we are getting any errors in valifdation we are redirecting to todo page
		}
		String username = getLoggedInUsername(model); //We are getting the name of the user who logged in
		todo.setUsername(username);
		todoService.updateToDo(todo);
		return "redirect:list-todos" ; //Here redirect will direct add-todo page to list todos page having same values which  list-todo has initially
		//If we return listtodos directly without redirecting it will not print the todos
		
	}
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
		
	   return authentication.getName() ;
	}
	
}
