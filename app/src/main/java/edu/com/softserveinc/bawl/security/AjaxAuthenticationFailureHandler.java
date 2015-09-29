package edu.com.softserveinc.bawl.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

@Component
public class AjaxAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(AjaxAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"Authentication failed");
	}
}