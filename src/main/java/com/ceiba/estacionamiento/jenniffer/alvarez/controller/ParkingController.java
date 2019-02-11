package com.ceiba.estacionamiento.jenniffer.alvarez.controller;

import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// POST
	@PostMapping("/estacionamientos")
	public void ingresarVehiculo(@RequestBody Vehiculo vehiculo) throws DomainException {
		parkingService.ingresar(vehiculo);
	}

	// GET

	@RequestMapping(value = "/estacionamientos/vehiculos/{placa}", method = RequestMethod.GET)
	public ResponseEntity<ResponseController<Vehiculo>> getByPlaca(@PathVariable("placa") String placa) {
		Vehiculo vehiculo = parkingService.findVehiculo(placa);

		if (vehiculo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseController<Vehiculo>(Constantes.NOT_VEHICLE_ISPARKING));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseController<Vehiculo>("VEHICULO", vehiculo));
	}

	
	@RequestMapping(value = "/estacionamientos/vehiculos", method = RequestMethod.GET)
	public ResponseController<List<Vehiculo>> getAllVehiculos() {
		return parkingService.findAll();
	}

	
	@RequestMapping(value = "/estacionamientos/salida/{placa}", method = RequestMethod.GET)
	public ResponseEntity<ResponseController<Vehiculo>> checkOutVehiculo(@PathVariable("placa") String placa) {
		parkingService.checkOut(placa);
		Vehiculo vehicleCheckOut = parkingService.findVehiculo(placa + "s");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseController<Vehiculo>(Constantes.CHECKED_VEHICLE, vehicleCheckOut));

	}

}
