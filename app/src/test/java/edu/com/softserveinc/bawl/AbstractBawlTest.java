/******************************************************************************
 * Copyright © 2015 thinkstep AG.
 * All rights reserved.
 *****************************************************************************/

package edu.com.softserveinc.bawl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-root-context.xml"})
public abstract class AbstractBawlTest {

}
