/******************************************************************************
 * Copyright © 2015 thinkstep AG.
 * All rights reserved.
 *****************************************************************************/

package edu.com.softserveinc.bawl;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SecurityContextHolder.class})
public abstract class AbstractBawlFunctionalTest {

    protected void setupMockSecurityContext(){
        PowerMockito.mockStatic(SecurityContextHolder.class);
        final SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken("admin", "admin", "ROLE_ADMIN");
        Mockito.when(securityContext.getAuthentication()).thenReturn(testingAuthenticationToken);
        PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(securityContext);

    }
}
