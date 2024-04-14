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

@Controller
@SessionAttributes("name")
//In this controller we are adding/deleting/updating todos using JpaRepository methods instead of defining our own methods
//by connecting to database.
public class TodoControllerJpa {
   
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		
		this.todoRepository = todoRepository ;
	}
	private TodoRepository todoRepository ;
	@RequestMapping("list-todos") //here we are just navigating to list-todos and trying to show todos on screen .
	public String listAllTodos(ModelMap model){
		//List<Todo> todos = todoService.findByUsername("in28minutes") ;
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoRepository.findByUsername(username) ; //We are getting the todos by passing username as input to the database
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
	public String addNewTodoPage(ModelMap model,@Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors()) {
			return "todo" ;
		}
		String username = getLoggedInUsername(model); 
		todo.setUsername(username);
		todoRepository.save(todo) ; //Normal Jpacontroller we need to pass individual attributes as paramenters
		//But the save method in JPARepository will accept only the entity as a whole instead of attribute. So we needf to set the username in totdo entity
		
//		todoService.addToDo(username, todo.getDescription(),todo.getTargetDate(), todo.isDone());
		return "redirect:list-todos" ; 
		
	}
	
	@RequestMapping("delete-todo") 
	public String deleteToDo(@RequestParam int id)//Here we are getting the id from listtodos.jsp
	{
		todoRepository.deleteById(id);
		//todoService.deleteById(id);
		return "redirect:list-todos" ;
		
	}
	
	@RequestMapping(value="update-todo",method = 	RequestMethod.GET) //When you ewant to show a updatetodo page you will send a get request
	public String showUpdateToDoPage(@RequestParam int id,ModelMap model)//Here we are getting the id from listtodos.jsp
	{
		//Todo todo = todoService.findById(id) ;
		Todo todo = todoRepository.findById(id).get() ;
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
		todoRepository.save(todo) ;
		//todoService.updateToDo(todo);
		return "redirect:list-todos" ; 
		
	}
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
		
	   return authentication.getName() ;
	}
	
}
