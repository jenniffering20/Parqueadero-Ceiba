package com.ceiba.estacionamiento.jenniffer.avlarez.testIntegration;
public class ParqueaderoIntegracinTest{
	
}
/*
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.estacionamiento.jenniffer.alvarez.Application;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.TypeInvalidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioFactura;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class ParqueaderoIntegracinTest {
	
	@Autowired
    private ParkingService parkingService;
	
	 @Autowired
    private RepositorioFactura vehiculoRepositorio;  
	  
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
		  parkingService.ingresarVehiculoFactura(vehiculo);
	        Factura vehiculoBuscar = vehiculoRepositorio.findByPlaca("GDK174");
	        
	        assertEquals("GDK174", vehiculoBuscar.getPlaca());
	        assertEquals("carro", vehiculoBuscar.getVehiculo().getTipo());
	        assertNotNull(vehiculoBuscar);
	        vehiculoRepositorio.delete(vehiculoBuscar);
	  }
	  
	  
	  @Test
	  public void testEncontarPorNombreNoEsta() {
		  
		  Factura FacturaBuscar = vehiculoRepositorio.findByPlaca("GDK174");
		  assertNull(FacturaBuscar);
	  }
	  
	 
	    @Test(expected =RegisteredVehicleException.class)
	    public void vehiculoRegistrado() throws DomainException {
	    	parkingService.ingresarVehiculoFactura(vehiculoRegistrado);
	    parkingService.ingresarVehiculoFactura(vehiculoRegistrado);
	    	
	    	
	    	Factura vehiculoBuscar = vehiculoRepositorio.findByPlaca("YOL475");
	    	vehiculoRepositorio.delete(vehiculoBuscar);
	    }
	    
	    @Test(expected=TypeInvalidException.class)
	    public void vehiculoSinTipoTest() throws DomainException {
	    	 parkingService.ingresarVehiculoFactura(vehiculoSinTipo);
	    }
	    
	  
	    
	  

	

}
*/