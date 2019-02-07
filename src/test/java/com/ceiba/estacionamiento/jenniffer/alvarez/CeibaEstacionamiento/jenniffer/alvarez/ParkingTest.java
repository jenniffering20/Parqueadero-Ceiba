package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DayNotValidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.GeneralException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.TypeInvalidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.Parking;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;


public class ParkingTest {
	
	@Mock
	private Repositorio repositorio;
	
	//@InjectMocks
	private ParkingService parkingReposity;
	
	private VehiculoModel vehiculoCar;
	private VehiculoModel vehiculoMoto;
	private Parking parking;


	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		
		repositorio = Mockito.mock(Repositorio.class);
		parkingReposity = Mockito.mock(ParkingService.class);
		
		
		vehiculoCar = new VehiculoModel("CARRO","RRO789",0);
		vehiculoMoto = new VehiculoModel("MOTO", "XME11d", 650);

		parking = new Parking(repositorio);
	}

	
	
	@Test
	public void checkInTestReady() throws Exception {

		parkingReposity.checkIn(vehiculoCar);
		Mockito.verify(parkingReposity).checkIn(vehiculoCar);
	}
	
	@Test
	public void checkOutTestReady() throws Exception {

		parkingReposity.checkOut(vehiculoCar.getPlaca());
		Mockito.verify(parkingReposity).checkOut(vehiculoCar.getPlaca());
	}
	
	@Test
	public void checkInTestFullParkinkCar() {

		Long lleno = (long) 20;
		
		parking.setFullCarros(lleno);
		Boolean isFullParking = parking.fullParking(vehiculoCar.getTipo());
		
		assertTrue(isFullParking);
		
	}
	@Test
	public void checkInTestNotFullParkinkCar() {
		
		Long Notlleno = (long) 10;
		
		parking.setFullCarros(Notlleno);
		Boolean isFullParking = parking.fullParking(vehiculoCar.getTipo());
		
		assertFalse(isFullParking);
		
	}
	
	@Test
	public void checkInTestFullParkinkMoto() {
		
		Long lleno = (long) 10;
		
		parking.setFullMotos(lleno);
		Boolean isFullParking = parking.fullParking(vehiculoMoto.getTipo());
		
		assertTrue(isFullParking);	
	}
	@Test
	public void checkInTestNotFullParkinkMoto() {
		
		Long lleno = (long) 5;
		
		parking.setFullMotos(lleno);
		Boolean isFullParking = parking.fullParking(vehiculoMoto.getTipo());
		
		assertFalse(isFullParking);	
	}
	
	@Test
	public void findVehicleOk() {
		
		when(repositorio.findByPlaca("RRO789")).thenReturn(vehiculoCar);
		VehiculoModel vehiculo= parking.findVehiculo("RRO789");
		
		assertEquals(vehiculoCar,vehiculo);	
	}
	
	@Test
	public void findVehicleNotOk() {
		
		when(repositorio.findByPlaca("RRO789")).thenReturn(vehiculoCar);
		VehiculoModel vehiculo= parking.findVehiculo("ERR781");
		
		assertNotEquals(vehiculoCar,vehiculo);	
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
	public void NotParkingFull() {
		Long fullCarro = (long) 10;
		Long fullMoto = (long) 8;
		
		parking.setFullCarros(fullCarro);
		parking.setFullMotos(fullMoto);
		Boolean isFullParking = parking.fullParking(vehiculoMoto.getTipo());
		
		assertFalse(isFullParking);
	}
	
	@Test
	public void restrictionLetterOk() {
		
		Boolean restrictionLetter = parking.restrictionLetter("A");
		assertTrue(restrictionLetter);
	}
	
	@Test
	public void restrictionLetterNotOk() {
		
		Boolean restrictionLetter = parking.restrictionLetter("B");
		assertFalse(restrictionLetter);
	}
	
	@Test(expected = ParkingFullException.class)
	public void checkInFullParkingException() throws GeneralException{
		Long fullCarro = (long) 20;
		
		parking.setFullCarros(fullCarro);
		parking.checkIn(vehiculoCar);
	}
	
	@Test(expected = TypeInvalidException.class)
	public void checkInInvalidTypeException() throws GeneralException{
		VehiculoModel vehiculoSinTipo = new VehiculoModel("","RRO789",0);	
		parking.checkIn(vehiculoSinTipo);
	}
	
	@Test(expected =DayNotValidException.class)
	public void checkInDayNotValidExceptionForPlacaStarWithA() throws GeneralException{
		
		LocalDateTime day= LocalDateTime.now();
		LocalDateTime notValidDay = day.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		VehiculoModel vehiculoCarplacaWithA = new VehiculoModel("CARRO","ARO789",0);

		parking.validDate(notValidDay);
		parking.setDay(notValidDay);
		parking.checkIn(vehiculoCarplacaWithA);
		
	}
	
	@Test(expected =RegisteredVehicleException.class)
	public void checkInVehicleRegistered()  throws GeneralException{
		
		when(repositorio.findByPlaca("RRO789")).thenReturn(vehiculoCar);
		VehiculoModel vehiculoRegistered = new VehiculoModel("CARRO","RRO789",0);
		
		parking.checkIn(vehiculoRegistered);
	}
	
	@Test
	public void testAllVehicles(){
		List<VehiculoModel>vehiculos = new ArrayList<VehiculoModel>();
		vehiculos.add(vehiculoCar);
		
		when(repositorio.findAll()).thenReturn(vehiculos);
		ResponseController<List<VehiculoModel>> responseController = parking.findAll();
		
		assertFalse(responseController.getDato().isEmpty());
	}
	
	@Test
	public void testAllVehiclesNotVehicles(){
		List<VehiculoModel>vehiculos = new ArrayList<VehiculoModel>();
		
		
		when(repositorio.findAll()).thenReturn(vehiculos);
		ResponseController<List<VehiculoModel>> responseController = parking.findAll();
		
		assertEquals(responseController.getMensage(),Constantes.NOT_VEHICLES);
	}
	
	@Test
	public void testCheckInVehicleSuccessful() throws GeneralException{
		
		ResponseController<List<VehiculoModel>> responseController = parking.checkIn(vehiculoCar);
		
		assertEquals(responseController.getMensage(),Constantes.VEHICLE_REGISTERED_SUCCESSFUL);
	}
	
	@Test 
	public void testDayOk() {
		LocalDateTime day=LocalDateTime.now();
		
		assertEquals(day, parking.date(day));
	}
	
	


	
	
	
	
}
