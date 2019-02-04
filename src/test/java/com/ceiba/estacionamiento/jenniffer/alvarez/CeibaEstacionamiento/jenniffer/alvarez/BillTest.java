package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.Bill;


public class BillTest {
	private Bill bill;
	
	@Before
	public void setup() {
		bill= new Bill();
	}

	@Test
	public void testCalculationStay() {
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		
		Long horasReales= bill.calculateStay(fechaEntrada, fechaSalida);
		Long horasEsperadas=120L;
		
		assertEquals(horasEsperadas, horasReales);
	}

}
