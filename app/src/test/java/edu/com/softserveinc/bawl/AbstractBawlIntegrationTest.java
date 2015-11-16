/******************************************************************************
 * Copyright © 2015 thinkstep AG.
 * All rights reserved.
 *****************************************************************************/

package edu.com.softserveinc.bawl;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest({SecurityContextHolder.class})
@ContextConfiguration(locations = {"classpath:test-root-context.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })
public abstract class AbstractBawlIntegrationTest {

}
