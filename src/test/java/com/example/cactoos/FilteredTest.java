package com.example.cactoos;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class FilteredTest {

	private static final String AAAAAAAAAAAAAAAAABBBBBBBBCCCCCCCCC = "aaaaaaaaaaaaaaaaabbbbbbbbccccccccc";

	@Test
	public void test() throws IOException {
		String asString = new EncryptedX(AAAAAAAAAAAAAAAAABBBBBBBBCCCCCCCCC).asString();
		System.out.println("first: " + asString);
		
		InputStream stream = new ByteArrayInputStream(AAAAAAAAAAAAAAAAABBBBBBBBCCCCCCCCC.getBytes());
		Encrypted eee = new EncryptedX(stream);
		
		assertEquals(eee.asString(), eee.asString());
		System.out.println("1: " + eee.asString());
		System.out.println("2: " + eee.asString());
	}

}
