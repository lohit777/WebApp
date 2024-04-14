package com.in28minutes.springboot.myfirstwebapp.security;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //It will contsain configuration of number of spring beans
//We will create number of beans and return it back
public class SpringSecurityConfiguration {
	
	//When we want to store usernames and passwords we will make use of LDAP or a database
	//we will use inmemory configuration
	
//	InMemoryUserDetailsManager
//	InMemoryUserDetailsManager(UserDetails... users) //UserDetails is a interface
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager()
	{

//The password encoder takes string as a input and returns string as a output. 
//We are taking input and with help of lambda expression we are encoding by passing input to encode function
		//		UserDetails userdetails  = User.withDefaultPasswordEncoder()  
//		.username("in28minutes")
//		.password("dummy")
//		.roles("USER","ADMIN").build() ; //We are using User class and the methods inside it to authenicate the user.
		//withDefaultPasswordEncoder is depricated but just for testing purpose we are using.
		//As UserDetails is a interface we are using User class instead of UserDetails
		UserDetails userdetails1 = createNewUser("in28minutes", "dummy");
		UserDetails userdetails2 = createNewUser("Lohit", "Virat1"); // We can create multiple users like this
		
		return new InMemoryUserDetailsManager(userdetails1,userdetails2) ;
	}
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		UserDetails userdetails  = User.builder()
				.passwordEncoder(passwordEncoder)
				.username(username)
				.password(password)
				.roles("USER","ADMIN").build() ;
		return userdetails;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ; //It is an implementation of password Encoder that uses Bcrypt  stronghashing function
		
	}
	//In the below method we are authenticating all the requests using httprequests.
	//If the user is not authenicated you will be redirected to login form
	//We are also disabling csrf and enabling frames so that we can access h2-console
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->auth.anyRequest().authenticated()) ; //Here we are authenticating all the requests which we receive
		http.formLogin(withDefaults()) ;  //it is used to create a login form
		//withDefaults method isd present in customizer class 
		http.csrf().disable() ; //to disabvle the csrf
		http.headers().frameOptions().disable() ;  //if it is enabled we cant use frames in h2
		//To use frames we need to duisable this
		return http.build() ; //this returns SecurityFilterChain
	}
//	SecurityFilterChain - Whenever a web request comes in it will be processed by this securityfilterchain
//	Whenever the user enters a url the user will be redirected to login page this is done by the securityfilterchain
//
//	Httpsecurity allows us to configure web based security.

}
