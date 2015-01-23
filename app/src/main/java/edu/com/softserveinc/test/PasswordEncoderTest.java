package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.utils.PasswordEncoder;

public class PasswordEncoderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(new PasswordEncoder("Pass").encode());
		assertTrue(true);
	}

}
//$2a$10$7CM7wciz5hbkn.unVH7AluCxPhjrK/34Mh4n66vREm8/Xo.p.rngq

//$2a$10$wIlerKAVi1jSFbFNQToo1.TS5gmFhYc/LZ9vT0ZnjBVm/.eyEfjoG

//$2a$10$3W4AMqbVc1bxPnRs/IkulOHedpmy7s/OG2n/RvQi91AWHEnU12Xc2
