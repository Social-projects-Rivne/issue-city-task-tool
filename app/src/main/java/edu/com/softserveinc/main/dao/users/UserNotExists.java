package edu.com.softserveinc.main.dao.users;

import java.lang.annotation.Target;

import  org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.internal.xml.ElementType;
/**
 * 
 * 
 * 
 * @author nazar
 *
 */
@Target(java.lang.annotation.ElementType.METHOD)
public @interface UserNotExists {

/*
	//TODO: Create annotation with those exception!  
	catch(org.hibernate.exception.ConstraintViolationException ex1){
		
		//TODO:add here notification window//TODO:chage it on logger
		System.out.println("==================== !!! USER EXISTS !!! =================");
	}
        
	
*/
}
