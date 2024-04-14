package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

//In this we will create a static todos to store the todo list.
@Service
public class TodoService {
  private static List<Todo> todos = new ArrayList() ;
  private static int todosCount = 0 ;
  static {
	  todos.add(new Todo(++todosCount,"in28minutes","Learn AWS",LocalDate.now().plusYears(1),false)) ;
	  todos.add(new Todo(++todosCount,"in28minutes","Learn Java",LocalDate.now().plusYears(2),false)) ;
	  todos.add(new Todo(++todosCount,"in28minutes","Learn Devops",LocalDate.now().plusYears(2),false)) ;
  }
  public List<Todo> findByUsername(String username){
	  Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
	  //If the username matches then we are retueerning the todos only with the username which the user passes
	  return todos.stream().filter(predicate).toList() ;
  }
  public void addToDo(String username,String description,LocalDate targetDate,boolean done) {
	  Todo todo = new Todo(++todosCount,username,description,targetDate,done) ;
	  todos.add(todo) ;
  }
  //This method is used to delete the todo
  public void deleteById(int id) {
	   
	  Predicate<? super Todo> predicate = todo -> todo.getId() == id;
	  //Here we are using lambda expressions where we are chedcking if the id which we pass to delete is equal to any id in todo bean
	  //and giving the response to predicate
	todos.removeIf(predicate) ; //predicate will have the condition when we want to remove the todo
  }
  public Todo findById(int id) {
	 Predicate<? super Todo> predicate = todo -> todo.getId() == id;
	 Todo todo = todos.stream().filter(predicate).findFirst().get() ;//Here using streams we are getting the first element which matches the input Id and assigning it to a local variable.
	return todo;
  }
  public void updateToDo(@Valid Todo todo) {
	deleteById(todo.getId());
	todos.add(todo) ; //Here first we are deeleting the existing todo then we are adding the new todo
	
  }
}
