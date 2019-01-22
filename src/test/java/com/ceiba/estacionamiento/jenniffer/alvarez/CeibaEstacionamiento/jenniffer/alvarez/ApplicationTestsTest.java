package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ceiba.estacionamiento.jenniffer.alvarez.Person;

public class ApplicationTestsTest {

	@Test
	public void canConstructPersonWithAName() {
		
		Person person=new Person("Larry");
		assertEquals("Larry",person.getName());
	}

}
