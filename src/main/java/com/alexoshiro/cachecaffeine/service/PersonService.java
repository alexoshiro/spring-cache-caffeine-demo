package com.alexoshiro.cachecaffeine.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alexoshiro.cachecaffeine.model.Person;

@Service
public class PersonService {

	Map<Integer, Person> people = new HashMap<>();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 5; i++)
			people.put(i, new Person(i, "mock" + i));
	}

	@Cacheable(value = "person", unless="#result == null")
	public Person getPerson(int personId) throws InterruptedException {
		Thread.sleep(3000); // simulate database search
		return people.get(personId);
	}
}
