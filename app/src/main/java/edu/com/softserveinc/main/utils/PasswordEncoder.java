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
	private BCryptPasswordEncoder encoder;	
	
	public PasswordEncoder(int strenhtg) {
		encoder = new BCryptPasswordEncoder(strenhtg);
	}
	
	public PasswordEncoder(String password) {
			this.password = password;
			encoder = new BCryptPasswordEncoder(11);
	}
	
	public void setPassvord(String password){
		this.password = password;
	}
	
	/**
	 * It encodes password
	 * @return SHA-512 hex string
	 */
	public String encode(){
		
		if(password.length()<61)
			return encoder.encode(password);
		else 
			return password;
	}
	
}
