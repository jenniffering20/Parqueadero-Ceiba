package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.FacturaI;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioVehiculo;


public class BillTest {
	private FacturaI bill;
	
	@Mock
	private RepositorioVehiculo repositorio;
	
	private Vehiculo vehiculoCar;
	private Factura facturaCar;
	private Factura facturaMoto;
	private Vehiculo vehiculoMoto;
	@Before
	public void setup() {
		bill= new FacturaI();
		repositorio = Mockito.mock(RepositorioVehiculo.class);
		
		
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
		facturaCar= new Factura("RRO789",vehiculoCar);
		facturaCar.setFechaIngreso(fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		facturaCar.setFechaSalida(fechaSalida);
		BigDecimal totalPago=new BigDecimal("2000");
		
		bill.goOutt(facturaCar);
		 
		assertEquals(totalPago,facturaCar.getTotalPago());
		
		
	}
	
	@Test 
	public void gotOutNotOk() {
		
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		vehiculoCar = new Vehiculo("CARRO","RRO789",0);
		facturaCar= new Factura("RRO789",vehiculoCar);
		facturaCar.setFechaIngreso(fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		facturaCar.setFechaSalida(fechaSalida);
		BigDecimal totalPago=new BigDecimal("1000");
		
		bill.goOutt(facturaCar);
		 
		assertNotEquals(totalPago,facturaCar.getTotalPago());
		
		
	}
	
	@Test
	public void gotOutMotoCilindrajeMenorOk() {
		
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		vehiculoMoto = new Vehiculo("MOTO","RRO789",460);
		facturaMoto= new Factura("RRO789",vehiculoMoto);
		facturaMoto.setFechaIngreso(fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		facturaMoto.setFechaSalida(fechaSalida);
		BigDecimal totalPago=new BigDecimal("1000");
		
		bill.goOutt(facturaMoto);
		 
		assertEquals(totalPago,facturaMoto.getTotalPago());
	}
	
	@Test
	public void gotOutMotoCilindrajeMayorOk() {
		
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		vehiculoMoto = new Vehiculo("MOTO","RRO789",560);
		facturaMoto= new Factura("RRO789",vehiculoMoto);
		facturaMoto.setFechaIngreso(fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		facturaMoto.setFechaSalida(fechaSalida);
		BigDecimal totalPago=new BigDecimal("3000");
		
		bill.goOutt(facturaMoto);
		 
		assertEquals(totalPago,facturaMoto.getTotalPago());
	}
	

}
