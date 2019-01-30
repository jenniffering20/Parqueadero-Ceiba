package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ceiba.estacionamiento.jenniffer.alvarez.logic.ParkingImp;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;


@SuppressWarnings("deprecation")
public class ParkingTest {
	
	@Mock
	private Repositorio repositorio;
	
	//@InjectMocks
	private ParkingService parkingReposity;
	
	private VehiculoModel vehiculoCar;
	private VehiculoModel vehiculoMoto;
	private ParkingImp parking;
	

	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		
		repositorio = Mockito.mock(Repositorio.class);
		parkingReposity = Mockito.mock(ParkingService.class);
		
		vehiculoCar = new VehiculoModel("CARRO","RRO789",0);
		vehiculoMoto = new VehiculoModel("MOTO", "xme11d", 650);

		parking = new ParkingImp(repositorio);
	}
	
	
	@Test
	public void checkInTestReady() throws Exception {

		parkingReposity.checkIn(vehiculoCar);
		Mockito.verify(parkingReposity).checkIn(vehiculoCar);
	}
	
	@Test
	public void checkInTestFullParkinkCar() {
		
		Long lleno = (long) 20;
		parking.setFullCarros(lleno);
		Boolean isFullParking = parking.fullParking(vehiculoCar.getTipo());
		assertTrue(isFullParking);
		
	}
	
	@Test
	public void checkInTestFullParkinkMoto() {
		
		Long lleno = (long) 10;
		parking.setFullMotos(lleno);
		Boolean isFullParking = parking.fullParking(vehiculoMoto.getTipo());
		assertTrue(isFullParking);	
	}
	
	@Test
	public void findVehicleOk() {
		when(repositorio.findByPlaca("RRO789")).thenReturn(vehiculoCar);
		VehiculoModel vehiculo= parking.findVehiculo("RRO789");
		assertEquals(vehiculoCar,vehiculo);
		
	}
	
	@Test
	public void parkingFull() {
		Long fullCarro = (long) 20;
		Long fullMoto = (long) 10;
		parking.setFullCarros(fullCarro);
		parking.setFullMotos(fullMoto);
		Boolean isFullParking = parking.fullParking(vehiculoMoto.getTipo());
		assertTrue(isFullParking);
	}
	
	@Test
	public void restrictionLetterOk() {
		Boolean restrictionLetter = parking.restrictionLetter("A");
		assertTrue(restrictionLetter);
	}

	/*
	@Test 
	public void updateNumberOfVehiclesOk() {
		when(repositorio.countByTipo("carro")).thenReturn((long)16);
		long numberOfVehicles= parking.UpdateNumberOfVehicles();
		
		assertEquals((long)16,numberOfVehicles);
	}
	*/
	
	
}
