package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import org.junit.Before;
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
	

	@Before
	public void setup() {
		repositorio = Mockito.mock(Repositorio.class);
		parkingReposity = Mockito.mock(ParkingService.class);
		
		vehiculo = new VehiculoModel("CARRO","RRO789");
	
		Mockito.when(parkingReposity.fullParking(vehiculo.getPlaca())).thenReturn(false);
		Mockito.when(parkingReposity.restrictionLetter(vehiculo.getPlaca())).thenReturn(false);
		Mockito.when(parkingReposity.validDate(vehiculo.getFechaIngreso())).thenReturn(false);
		Mockito.when(parkingReposity.findVehiculo(vehiculo.getPlaca())).thenReturn(null);
				
		parking = new ParkingImp();
	}
	
	@Test
	public void checkInTestReady() {
		parkingReposity.checkIn(vehiculo);
		Mockito.verify(parkingReposity).checkIn(vehiculo);
}
	
}