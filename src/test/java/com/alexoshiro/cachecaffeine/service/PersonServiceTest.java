package com.alexoshiro.cachecaffeine.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Test
	public void testCache() throws InterruptedException {
		//First time it should call the method normally, begin interrupted by Thread.sleep, so the time to return should be 5 seconds
		long start = System.currentTimeMillis();
		personService.getPerson(1);
		long end = System.currentTimeMillis();
		assertTrue(end - start >= 3000);
		
		//Second time it should use the cache when calling the method, so it should not step into Thread.sleep, so the time to return should be almost instantly
		start = System.currentTimeMillis();
		personService.getPerson(1);
		end = System.currentTimeMillis();
		assertTrue(end - start < 1000);
		
		start = System.currentTimeMillis();
		personService.getPerson(2);
		end = System.currentTimeMillis();
		assertTrue(end - start >= 3000);
		
		start = System.currentTimeMillis();
		personService.getPerson(1);
		end = System.currentTimeMillis();
		assertTrue(end - start < 1000);
		
		start = System.currentTimeMillis();
		personService.getPerson(2);
		end = System.currentTimeMillis();
		assertTrue(end - start < 1000);
	}
}
