package com.ceiba.estacionamiento.jenniffer.avlarez.testIntegration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.estacionamiento.jenniffer.alvarez.Application;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.TypeInvalidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioVehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class ParqueaderoIntegracinTest {
	
	@Autowired
    private ParkingService parkingService;
	
	 @Autowired
    private RepositorioVehiculo vehiculoRepositorio;  
	  
	  private Vehiculo vehiculo;
	  private Vehiculo vehiculoRegistrado;
	  private Vehiculo vehiculoSinTipo;
	  
	  @Before
		public void setup() throws DomainException {
		  vehiculo= new Vehiculo("carro","GDK174",0);
		  vehiculoRegistrado = new Vehiculo("carro","YOL475",0);
		  vehiculoSinTipo = new Vehiculo("","SIN789",0);

		 
		  
	  }
	
	  @Test
	    public void testEncontrarPorNombre() throws DomainException {
		  parkingService.ingresarVehiculo(vehiculo);
	        Vehiculo vehiculoBuscar = vehiculoRepositorio.findByPlaca("GDK174");
	        
	        assertEquals("GDK174", vehiculoBuscar.getPlaca());
	        assertEquals("carro", vehiculoBuscar.getTipo());
	        assertNotNull(vehiculoBuscar);
	        vehiculoRepositorio.delete(vehiculoBuscar);
	  }
	  
	  
	  @Test
	  public void testEncontarPorNombreNoEsta() {
		  
		  Vehiculo vehiculoBuscar = vehiculoRepositorio.findByPlaca("GDK174");
		  assertNull(vehiculoBuscar);
	  }
	  
	 
	    @Test(expected =RegisteredVehicleException.class)
	    public void vehiculoRegistrado() throws DomainException {
	    	parkingService.ingresarVehiculo(vehiculoRegistrado);
	    parkingService.ingresarVehiculo(vehiculoRegistrado);
	    	
	    	
	    	Vehiculo vehiculoBuscar = vehiculoRepositorio.findByPlaca("YOL475");
	    	vehiculoRepositorio.delete(vehiculoBuscar);
	    }
	    
	    @Test(expected=TypeInvalidException.class)
	    public void vehiculoSinTipoTest() throws DomainException {
	    	 parkingService.ingresarVehiculo(vehiculoSinTipo);
	    }
	    
	  
	    
	  

	

}
