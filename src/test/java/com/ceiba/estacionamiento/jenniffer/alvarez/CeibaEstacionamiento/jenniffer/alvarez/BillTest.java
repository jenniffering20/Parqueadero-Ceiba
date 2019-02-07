package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.Bill;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;


public class BillTest {
	private Bill bill;
	
	@Mock
	private Repositorio repositorio;
	
	private VehiculoModel vehiculoCar;
	private VehiculoModel vehiculoMoto;
	
	@Before
	public void setup() {
		bill= new Bill();
		repositorio = Mockito.mock(Repositorio.class);
		
		
		vehiculoMoto = new VehiculoModel("MOTO", "XME11d", 650);
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
	
	/*
	@Test 
	public void gotOutOk() {
		
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		vehiculoCar = new VehiculoModel("CARRO","RRO789",0,fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 10, 00);
		vehiculoCar.setFechaSalida(fechaSalida);
		BigDecimal totalPago=new BigDecimal("2000");
		
	
		bill.goOut(vehiculoCar);
		 

		assertEquals(totalPago,vehiculoCar.getTotalPago());
		
		
	}
	*/

}
