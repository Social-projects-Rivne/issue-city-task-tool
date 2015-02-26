package edu.com.softserveinc.main.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	private String password;
	private BCryptPasswordEncoder encoder;

	public PasswordEncoder(String password) {
		this.password = password;
		encoder = new BCryptPasswordEncoder();
	}

	public void setPassvord(String password) {
		this.password = password;
	}

	public boolean compare(String pass1, String pass2) {
		return encoder.matches(pass1, pass2);
	}

	public String encode() {

		if (password.length() < 61)
			return encoder.encode(password);
		else
			return password;
	}

}
