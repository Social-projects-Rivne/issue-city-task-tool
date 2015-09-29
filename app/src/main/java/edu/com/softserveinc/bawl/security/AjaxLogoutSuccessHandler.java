package edu.com.softserveinc.bawl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

@Component
public class AjaxLogoutSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		LogoutSuccessHandler {

	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(AjaxLogoutSuccessHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
	}
}