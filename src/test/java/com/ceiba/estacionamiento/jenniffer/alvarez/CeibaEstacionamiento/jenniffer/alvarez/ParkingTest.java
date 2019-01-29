package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.estacionamiento.jenniffer.alvarez.logic.ParkingImp;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;

public class ParkingTest {
	
	private Repositorio repositorio;
	private VehiculoModel vehiculo;
	private ParkingService parkingReposity;
	private ParkingImp parking;
	

	@Before(value = "")
	public void setup() {
		repositorio = Mockito.mock(Repositorio.class);
		parkingReposity = Mockito.mock(ParkingService.class);
	
		
		vehiculo = new VehiculoModel("CARRO","XME11D");
		parking = new ParkingImp();
	}
	
	@Test
	public void checkInTest() {
		parking.checkIn(vehiculo.getTipo(),vehiculo.getPlaca());
		Mockito.verify(parkingReposity).checkIn(vehiculo.getTipo(),vehiculo.getPlaca());
	}

}
