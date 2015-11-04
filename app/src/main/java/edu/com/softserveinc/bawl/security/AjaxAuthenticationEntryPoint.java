package edu.com.softserveinc.bawl.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public static final Logger LOG=Logger.getLogger(AjaxAuthenticationEntryPoint.class);

	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}