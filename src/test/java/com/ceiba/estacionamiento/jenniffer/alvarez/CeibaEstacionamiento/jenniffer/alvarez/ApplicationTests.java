package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.controller.Controller;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	//private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);
	
	@Autowired
	private Repositorio repositorio;
	
	@Autowired
	private Controller controller;
	
	
	
	
	
	@Test 
	public void A�adirVehiculo() {
		VehiculoModel  vehiculoEsperado = new VehiculoModel("Carro","stj589");
		VehiculoModel vehiculoReal = controller.postVehiculo(vehiculoEsperado);	
		
		assertEquals(vehiculoEsperado.getPlaca(),vehiculoReal.getPlaca());
		assertEquals(vehiculoEsperado.getTipo(),vehiculoReal.getTipo());	
	}
	
	
	
	
	


	
	

}

