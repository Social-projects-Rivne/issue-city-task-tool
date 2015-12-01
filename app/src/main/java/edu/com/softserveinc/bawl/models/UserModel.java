package edu.com.softserveinc.bawl.models;

import com.google.common.base.Objects;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="USERS")
public class UserModel {

    public static final Logger LOG=Logger.getLogger(UserModel.class);

	@Id
    @GeneratedValue
    @Column(unique=true, name="ID")
	private int id;
	
    @Size(max=30)
    @Column(name="NAME")
    @NotNull
    @NotEmpty
	private String name;
    
    @Email
    @NotEmpty
    @Column(unique=true, name="EMAIL")
	private String email;
        
    @Size(max = 30)
    @NotEmpty
    @Column(unique=true, name="LOGIN")
	private String login;
    
    @Column(name="ROLE_ID")
	@Enumerated(EnumType.ORDINAL)
	private UserRole role;
    
    @NotEmpty
    @Column(name="PASSWORD")
	private String password;
    
    @Column(name="AVATAR_URL")
	private String avatar;
    
    
    /**
     * Extended Constructor for adding all fields
     * 
     * @param name
     * @param email
     * @param login
     * @param role
     * @param password
     * @param avatar
     */
    public UserModel(String name, String email, String login,
    		int role, String password, String avatar ){
    	
    	this.name = name;
    	this.email = email;
    	this.login = login;
    	this.role = UserRole.getByRoleId(role);
    	this.password = password;
    	this.avatar = avatar;
    }
    /**
     * Constructor
     */
    public UserModel(){
    	
    }
    
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
    
    /**
     * Method for setup user's Role_id
     * 
     * @param role
     */
    public void setRole(UserRole role){
    	this.role = role;
    }

    /**
     * Method for setup user's Role_id
     *
     * @param role
     */
    public void setRole(int role){
    	this.role = UserRole.getByRoleId(role);
    }
    /**
     * 
     * @return user's role
     */
    public UserRole getRole(){
		return this.role;
	}
    
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserModel userModel = (UserModel) o;
		return Objects.equal(id, userModel.id) &&
				Objects.equal(name, userModel.name) &&
				Objects.equal(email, userModel.email) &&
				Objects.equal(login, userModel.login) &&
				Objects.equal(role, userModel.role) &&
				Objects.equal(password, userModel.password) &&
				Objects.equal(avatar, userModel.avatar);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, email, login, role, password, avatar);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.add("email", email)
				.add("login", login)
				.add("role", role)
				.add("password", password)
				.add("avatar", avatar)
				.toString();
	}


}
