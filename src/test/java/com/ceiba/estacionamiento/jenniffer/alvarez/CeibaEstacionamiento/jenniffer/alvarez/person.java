package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ceiba.estacionamiento.jenniffer.alvarez.Person;

public class person {

	@Test
	public void test() {
		Person persona= new Person("jenny");
		assertEquals("jenny",persona.getName());
	}

}
