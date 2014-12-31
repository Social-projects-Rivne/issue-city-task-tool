package edu.com.softserveinc.main.models;

import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserModel {
	
	/**
	 * Name max length = 30
	 */
    @Size(max=30) 
    @NotNull(message="It can't be empty")
    protected String name;
    
	/**
	 * E-mail
	 */
    @Email 
    @NotNull(message="It can't be empty")
    protected String email;
        
    /**
	 * Login max length = 30
	 */
    @Size(max = 30) 
    @NotNull(message="It can't be empty")
    protected String login;
    
    /**
     * Reole id min = 0 max = 3
	 */ 
    @Size(min = 0, max = 3) 
    @NotNull(message="It can't be empty")
    protected int role_id;
    
     /**
	 * Passvord min length = 6, max length = 32
	 */
    @Size(min = 6, max = 32) 
    @NotNull(message="It can't be empty")
    protected String password;
    
    /**
     * Avatar url
	 */
    protected String avatar;
    
    
    /**
     * Extended Constructor for adding all fields
     * 
     * @param name
     * @param email
     * @param login
     * @param role_id
     * @param password
     * @param avatar
     */
    public UserModel(String name, String email, String login,
    		int role_id, String password, String avatar ){
    	
    	this.name = name;
    	this.email = email;
    	this.login = login;
    	this.role_id = role_id;
    	this.password = password;
    	this.avatar = avatar;
    }
    /**
     * Constructor
     */
    public UserModel(){
    	
    }
    
    //Email get set
    /**
     * Method for setup user's name
     * 
     * @param name
     */
    public void setName(String name){
    	this.name = name;
    }
    /**
     * 
     * @return user's name
     */
    public String getName(){
		return this.name;
	}
    
  //Email get set
    /**
     * Method for setup user's email
     * 
     * @param email
     */
    public void setEmail(String email){
    	this.email = email;
    }
    /**
     * 
     * @return user's email
     */
    public String getEmail(){
		return this.email;
	}
    
    //Login get set
    /**
     * Method for setup user's login
     * 
     * @param login
     */
    public void setLogin(String login){
    	this.login = login;
    }
    /**
     * 
     * @return user's login
     */
    public String getLogin(){
		return this.login;
	}
    
  //Role_id get set
    /**
     * Method for setup user's Role_id
     * 
     * @param role_id
     */
    public void setRole_id(int role_id){
    	this.role_id = role_id;
    }
    /**
     * 
     * @return user's role_id
     */
    public int getRole_id(){
		return this.role_id;
	}
    
    //Password get set
    /**
     * Method for setup user's password
     * 
     * @param password
     */
    public void setPassword(String password){
    	this.password = password;
    }
    /**
     * 
     * @return user's password
     */
    public String getPassword(){
		return this.password;
	}
    
    //Avatar get set
    /**
     * Method for setup user's password
     * 
     * @param avatar
     */
    public void setAvatar(String avatar){
    	this.avatar = avatar;
    }
    /**
     * 
     * @return user's avatar
     */
    public String getAvatar(){
		return this.avatar;
	}
    
}
