package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.Bill;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;


public class BillTest {
	private Bill bill;
	
	@Mock
	private Repositorio repositorio;
	
	private Vehiculo vehiculoCar;
	
	@Before
	public void setup() {
		bill= new Bill();
		repositorio = Mockito.mock(Repositorio.class);
		
		
	}

	@Test
	public void testCalculationStay() {
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		
		Long horasReales= bill.calculateStay(fechaEntrada, fechaSalida);
		Long horasEsperadas=120L;
		
		assertEquals(horasEsperadas, horasReales);
	}
	@Test
	public void testCalculationBadStay() {
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		
		Long horasReales= bill.calculateStay(fechaEntrada, fechaSalida);
		Long horasEsperadas=130L;
		
		assertNotEquals(horasEsperadas, horasReales);
	}
	
	
	@Test 
	public void gotOutOk() {
		
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		vehiculoCar = new Vehiculo("CARRO","RRO789",0);
		vehiculoCar.setFechaIngreso(fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		vehiculoCar.setFechaSalida(fechaSalida);
		BigDecimal totalPago=new BigDecimal("2000");
		
		bill.goOut(vehiculoCar);
		 
		assertEquals(totalPago,vehiculoCar.getTotalPago());
		
		
	}
	
	@Test 
	public void gotOutNotOk() {
		
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		vehiculoCar = new Vehiculo("CARRO","RRO789",0);
		vehiculoCar.setFechaIngreso(fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		vehiculoCar.setFechaSalida(fechaSalida);
		BigDecimal totalPago=new BigDecimal("1000");
		
		bill.goOut(vehiculoCar);
		 
		assertNotEquals(totalPago,vehiculoCar.getTotalPago());
		
		
	}
	

}
