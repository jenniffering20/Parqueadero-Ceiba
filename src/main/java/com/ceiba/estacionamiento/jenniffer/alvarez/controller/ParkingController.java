package com.ceiba.estacionamiento.jenniffer.alvarez.controller;

import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:4200")
public class ParkingController {

	@Autowired
	ParkingService parkingService;


	
	@RequestMapping(value = "/estacionamientos/salidaFactura/{placa}", method = RequestMethod.GET)
	public Factura checkOutVehiculoF(@PathVariable("placa") String placa) throws DomainException {
		return parkingService.facturacionVehiculoSalida(placa);
		
		
	}
	@RequestMapping(value = "/estacionamientos/vehiculosFacturas", method = RequestMethod.GET)
	public List<Factura> getAllVehiculosFacturas() {
		return parkingService.todasFacturas();
	}
	
	
	
	@RequestMapping(value = "/estacionamientos/vehiculoFacturas/{placa}", method = RequestMethod.GET)
	public Factura getFacturaByPlaca(@PathVariable("placa") String placa) throws DomainException {
		return parkingService.encontrarFactura(placa);
	}
	
	@PostMapping("/estacionamientosFactura")
	@ResponseStatus(HttpStatus.CREATED)
	public void ingresarVehiculoFactura(@RequestBody Vehiculo vehiculo) throws DomainException{
		parkingService.ingresarVehiculoFactura(vehiculo);
	}
	
	

}
