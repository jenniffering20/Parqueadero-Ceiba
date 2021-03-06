package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DayNotValidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.VehiculoNoParqueadoException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.TypeInvalidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.Parqueadero;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioFactura;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioVehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;


public class ParkingTest {
	

	
	@Mock
	private RepositorioFactura repositorioFactura;
	
	private ParkingService parkingReposity;
	
	private Vehiculo vehiculoCar;
	private Vehiculo vehiculoMoto;
	private Factura facturaCar;
	private Factura facturaMoto;
	private Parqueadero parking;


	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		
		repositorioFactura = Mockito.mock(RepositorioFactura.class);
		parkingReposity = Mockito.mock(ParkingService.class);
		
		
		vehiculoCar = new Vehiculo("CARRO","RRO789",0);
		facturaCar= new Factura("RRO789", vehiculoCar);
		vehiculoMoto = new Vehiculo("MOTO", "XME11d", 650);
		facturaMoto= new Factura("XME11d", vehiculoMoto);

		parking = new Parqueadero(repositorioFactura);
	}

	
	
	@Test
	public void checkInTestReady() throws Exception {

		parkingReposity.ingresarVehiculoFactura(vehiculoCar);
		Mockito.verify(parkingReposity).ingresarVehiculoFactura(vehiculoCar);
	}
	
	@Test
	public void checkOutTestReady() throws Exception {

		parkingReposity.facturacionVehiculoSalida(vehiculoCar.getPlaca());
		Mockito.verify(parkingReposity).facturacionVehiculoSalida(vehiculoCar.getPlaca());
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
	public void findVehicleOk() throws DomainException {
		
		when(repositorioFactura.findByPlaca("RRO789")).thenReturn(facturaCar);
		Factura vehiculo= parking.encontrarFactura("RRO789");
		
		assertEquals(vehiculoCar,vehiculo.getVehiculo());	
	}
	
	
	@Test(expected = VehiculoNoParqueadoException.class)
	public void findVehicleNotOk() throws DomainException {
		
		when(repositorioFactura.findByPlaca("RRO789")).thenReturn(facturaCar);
		Factura vehiculo= parking.encontrarFactura("ERR781");
			
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
		
		Boolean restrictionLetter = parking.restriccionPlacasQueInicianConLetraA("A");
		assertTrue(restrictionLetter);
	}
	
	@Test
	public void restriccionplacasInicianConAyDiaInvalido() {
	LocalDateTime day= LocalDateTime.now();
	LocalDateTime notValidDay = day.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
	Vehiculo vehiculoCarplacaWithA = new Vehiculo("CARRO","ARO789",0);

	parking.validDate(notValidDay);
	parking.setDay(notValidDay);
	Boolean restrictionLetter= parking.restriccionPlacasQueInicianConLetraA(vehiculoCarplacaWithA.getPlaca());
	assertTrue(restrictionLetter);
	}
	
	@Test
	public void restriccionplacasInicianConAyDiavalido() {
	LocalDateTime day= LocalDateTime.now();
	LocalDateTime notValidDay = day.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

	parking.validDate(notValidDay);
	parking.setDay(notValidDay);
	Boolean restrictionLetter= parking.restriccionPlacasQueInicianConLetraA("PRO789");
	assertFalse(restrictionLetter);
	}
	
	
	@Test
	public void restrictionLetterNotOk() {
		
		Boolean restrictionLetter = parking.restriccionPlacasQueInicianConLetraA("B");
		assertFalse(restrictionLetter);
	}
	
	@Test(expected = ParkingFullException.class)
	public void checkInFullParkingException() throws DomainException{
		Long fullCarro = (long) 20;
		
		parking.setFullCarros(fullCarro);
		parking.ingresarVehiculoFactura(vehiculoCar);
	}
	
	@Test(expected = TypeInvalidException.class)
	public void checkInInvalidTypeException() throws DomainException{
		Vehiculo vehiculoSinTipo = new Vehiculo("","RRO789",0);	
		parking.ingresarVehiculoFactura(vehiculoSinTipo);
	}
	
	@Test(expected =DayNotValidException.class)
	public void checkInDayNotValidExceptionForPlacaStarWithA() throws DomainException{
		
		LocalDateTime day= LocalDateTime.now();
		LocalDateTime notValidDay = day.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		Vehiculo vehiculoCarplacaWithA = new Vehiculo("CARRO","ARO789",0);

		parking.validDate(notValidDay);
		parking.setDay(notValidDay);
		parking.ingresarVehiculoFactura(vehiculoCarplacaWithA);
		
	}
	
	
	@Test(expected =RegisteredVehicleException.class)
	public void checkInVehicleRegistered()  throws DomainException{
		
		when(repositorioFactura.findByPlaca("RRO789")).thenReturn(facturaCar);
		Vehiculo vehiculoRegistered = new Vehiculo("CARRO","RRO789",0);
		
		parking.ingresarVehiculoFactura(vehiculoRegistered);
	}
	
	@Test
	public void checkInVehicleOk()  throws DomainException{
		
		when(repositorioFactura.findByPlaca("RRO789")).thenReturn(null);
		Vehiculo vehiculo= new Vehiculo("CARRO","RRO789",0);
		parking.ingresarVehiculoFactura(vehiculo);
		when(repositorioFactura.findByPlaca("RRO789")).thenReturn(facturaCar);
		Factura factura=parking.encontrarFactura(vehiculo.getPlaca());
		
		assertEquals(vehiculo.getPlaca(),factura.getPlaca());
		
	}
	
	@Test
	public void testAllVehicles(){
		List<Factura>vehiculos = new ArrayList<Factura>();
		vehiculos.add(facturaCar);
		
		when(repositorioFactura.findAll()).thenReturn(vehiculos);
		List<Factura> responseController = parking.todasFacturas();
		
		assertFalse(responseController.isEmpty());
	}
	
	@Test
	public void testSalidaVehiculoOk() throws DomainException{
		LocalDateTime fechaEntrada = LocalDateTime.of(2019, 2, 4, 10, 00);
		facturaCar.setFechaIngreso(fechaEntrada);
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 2, 4, 12, 00);
		facturaCar.setFechaSalida(fechaSalida);
		
		when(repositorioFactura.findByPlaca("RRO789")).thenReturn(facturaCar);
		
		
		Factura factura= parking.facturacionVehiculoSalida("RRO789");
		assertEquals("RRO789s",factura.getPlaca() );
		
	}
		
	
	
	@Test 
	public void testDayOk() {
		LocalDateTime day=LocalDateTime.now();
		
		assertEquals(day, parking.fecha(day));
	}
	
	


	
	
	
	
}
