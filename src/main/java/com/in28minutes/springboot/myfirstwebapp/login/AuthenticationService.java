package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

//in this service we can authenicate the user i.e., we need to go to the next page only if a specified username and password are given
@Service //below class contains business logic so we use @service so spring manages it
public class AuthenticationService {
	public boolean authenicate(String username,String password) {
	  boolean isValidUserName = username.equalsIgnoreCase("Lohit") ;
	  boolean isValidPassword = password.equalsIgnoreCase("Virat") ;
		return isValidUserName && isValidPassword ; //If either username or password gives false then it will return false
	}

}
