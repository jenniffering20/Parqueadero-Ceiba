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
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.TypeInvalidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.Parqueadero;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;


public class ParkingTest {
	
	@Mock
	private Repositorio repositorio;
	
	//@InjectMocks
	private ParkingService parkingReposity;
	
	private Vehiculo vehiculoCar;
	private Vehiculo vehiculoMoto;
	private Parqueadero parking;


	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		
		repositorio = Mockito.mock(Repositorio.class);
		parkingReposity = Mockito.mock(ParkingService.class);
		
		
		vehiculoCar = new Vehiculo("CARRO","RRO789",0);
		vehiculoMoto = new Vehiculo("MOTO", "XME11d", 650);

		parking = new Parqueadero(repositorio);
	}

	
	
	@Test
	public void checkInTestReady() throws Exception {

		parkingReposity.ingresar(vehiculoCar);
		Mockito.verify(parkingReposity).ingresar(vehiculoCar);
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
		Vehiculo vehiculo= parking.findVehiculo("RRO789");
		
		assertEquals(vehiculoCar,vehiculo);	
	}
	
	@Test
	public void findVehicleNotOk() {
		
		when(repositorio.findByPlaca("RRO789")).thenReturn(vehiculoCar);
		Vehiculo vehiculo= parking.findVehiculo("ERR781");
		
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
	public void checkInFullParkingException() throws DomainException{
		Long fullCarro = (long) 20;
		
		parking.setFullCarros(fullCarro);
		parking.ingresar(vehiculoCar);
	}
	
	@Test(expected = TypeInvalidException.class)
	public void checkInInvalidTypeException() throws DomainException{
		Vehiculo vehiculoSinTipo = new Vehiculo("","RRO789",0);	
		parking.ingresar(vehiculoSinTipo);
	}
	
	@Test(expected =DayNotValidException.class)
	public void checkInDayNotValidExceptionForPlacaStarWithA() throws DomainException{
		
		LocalDateTime day= LocalDateTime.now();
		LocalDateTime notValidDay = day.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		Vehiculo vehiculoCarplacaWithA = new Vehiculo("CARRO","ARO789",0);

		parking.validDate(notValidDay);
		parking.setDay(notValidDay);
		parking.ingresar(vehiculoCarplacaWithA);
		
	}
	
	@Test(expected =RegisteredVehicleException.class)
	public void checkInVehicleRegistered()  throws DomainException{
		
		when(repositorio.findByPlaca("RRO789")).thenReturn(vehiculoCar);
		Vehiculo vehiculoRegistered = new Vehiculo("CARRO","RRO789",0);
		
		parking.ingresar(vehiculoRegistered);
	}
	
	@Test
	public void testAllVehicles(){
		List<Vehiculo>vehiculos = new ArrayList<Vehiculo>();
		vehiculos.add(vehiculoCar);
		
		when(repositorio.findAll()).thenReturn(vehiculos);
		ResponseController<List<Vehiculo>> responseController = parking.findAll();
		
		assertFalse(responseController.getDato().isEmpty());
	}
	
	@Test
	public void testAllVehiclesNotVehicles(){
		List<Vehiculo>vehiculos = new ArrayList<Vehiculo>();
		
		
		when(repositorio.findAll()).thenReturn(vehiculos);
		ResponseController<List<Vehiculo>> responseController = parking.findAll();
		
		assertEquals(responseController.getMensage(),Constantes.NOT_VEHICLES);
	}
	
	
	
	@Test 
	public void testDayOk() {
		LocalDateTime day=LocalDateTime.now();
		
		assertEquals(day, parking.date(day));
	}
	
	


	
	
	
	
}
