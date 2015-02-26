package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.security.PasswordEncoder;

public class PasswordEncoderTest {
	
	PasswordEncoder encoder = new PasswordEncoder(11);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		encoder.setPassvord("Pass");
		System.out.println(encoder.encode());
		assertTrue(true);
	}
	@Test
	public void compearTest() {
		assertTrue(encoder.compare("Pass", "$2a$11$0Ctak47c7yrhI46hnyFADefgDTw2NPVO4OgeRFGJtBE1GmXAB6.7q"));
	}

}
//$2a$10$7CM7wciz5hbkn.unVH7AluCxPhjrK/34Mh4n66vREm8/Xo.p.rngq

//$2a$10$wIlerKAVi1jSFbFNQToo1.TS5gmFhYc/LZ9vT0ZnjBVm/.eyEfjoG

//$2a$10$3W4AMqbVc1bxPnRs/IkulOHedpmy7s/OG2n/RvQi91AWHEnU12Xc2
