package edu.com.softserveinc.main.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Password encoder class
 * 
 * @author Nazar
 *
 */
public class PasswordEncoder {

	private String password;

	public PasswordEncoder(String password) {
			this.password = password;
	}
	
	public void setPassvord(String password){
		this.password = password;
	}
	
	/**
	 * It encodes password
	 * @return SHA-512 hex string
	 */
	public String encode(){
		
		if(password.length()<67)
			return new BCryptPasswordEncoder().encode(password);
		else 
			return password;
	}
	
}
