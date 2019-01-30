package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.ParkingImp;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;


@SuppressWarnings("deprecation")
public class ParkingTest {
	
	private Repositorio repositorio;
	private VehiculoModel vehiculoCar;
	private VehiculoModel vehiculoMoto;
	private ParkingService parkingReposity;
	private ParkingImp parking;
	

	@Before
	public void setup() {
		repositorio = Mockito.mock(Repositorio.class);
		parkingReposity = Mockito.mock(ParkingService.class);
		
		
		vehiculoCar = new VehiculoModel("CARRO","RRO789");
		vehiculoMoto = new VehiculoModel("MOTO", "xme11d");
	
	
				
		parking = new ParkingImp();
	}
	
	@Test
	public void checkInTestReady() {

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
		Mockito.when(repositorio.findByPlaca("RRO789")).thenReturn(vehiculoCar);
		VehiculoModel vehiculo= repositorio.findByPlaca("RRO789");
		assertEquals(vehiculoCar,vehiculo);
		
	}
	
}
