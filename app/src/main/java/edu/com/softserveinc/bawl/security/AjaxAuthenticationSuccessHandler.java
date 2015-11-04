package edu.com.softserveinc.bawl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

@Component
public class AjaxAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

    public static final Logger LOG=Logger.getLogger(AjaxAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
	}
}