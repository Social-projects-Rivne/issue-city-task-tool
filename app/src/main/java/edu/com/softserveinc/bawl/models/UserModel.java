package edu.com.softserveinc.bawl.models;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import org.apache.log4j.Logger;

//TODO:Add comments for annotation
//TODO:Add more constructors

@Entity
@Table(name="users")
public class UserModel {
    
    /**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(UserModel.class);

	/**
	 * User ID
	 */
	@Id
    @GeneratedValue //it will be auto incremented value in table 
    @Column(unique=true, name="id")
	protected int id;
	
	/**
	 * Name max length = 30
	 */
    @Size(max=30) 
    @Column(name="name") //name of column in table
    @NotNull(message="It can't be empty") // it needed for validation 
    @NotEmpty  //annotation for hibernate, it is needed for that in DB this field couldn't be empty
    protected String name;
    
	/**
	 * E-mail
	 */
    @Email
    @NotEmpty(message="It can't be empty")
    @Column(unique=true, name="email") //name of column in table
    protected String email;
        
    /**
	 * Login max length = 30
	 */
    @Size(max = 30)
    @NotEmpty(message="It can't be empty")
    @Column(unique=true, name="login") //name of column in table
    protected String login;
    
    /**
     * Reole id min = 0 max = 3
	 */ 
//	@Size(min = 0, max = 3) 
    @NotNull(message="It can't be empty")
    @Column(name="role_id") //name of column in table
    //TODO: Add hibernate annotation for relation between tables
    protected int role_id;
    
     /**
	 * Passvord min length = 6, max length = 32
	 */
    //@Size(min = 6, max = 32) 
    @NotEmpty(message="It can't be empty")
    @Column(name="password") //name of column in table
    protected String password;
    
    /**
     * Avatar url
	 */
    @Column(name="avatar_url") //name of column in table
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
    
    //id get set
    /**
     * Method for setup user's id
     * 
     */
    public void setId(int id) {
		this.id = id;
	}
    /**
     * Method for get user's id
     * @return
     */
    public int getId(){
    	return this.id;
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
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + role_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role_id != other.role_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", email=" + email
				+ ", login=" + login + ", role_id=" + role_id + "]";
	}
    
}
