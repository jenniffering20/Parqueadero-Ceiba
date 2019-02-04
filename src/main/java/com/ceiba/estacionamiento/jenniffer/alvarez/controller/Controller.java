package com.ceiba.estacionamiento.jenniffer.alvarez.controller;


import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DayNotValidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.GeneralException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
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
@CrossOrigin("*")
public class Controller {

	@Autowired
	ParkingService parkingService;

	//POST
	@PostMapping("/estacionamientos")
	public ResponseEntity<ResponseController<VehiculoModel>> postVehiculo(@RequestBody VehiculoModel vehiculo) throws GeneralException{

		try {
		parkingService.checkIn(vehiculo);
		}catch(RegisteredVehicleException e) {
	
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseController<VehiculoModel>(Constantes.REGISTERED_MESSAGE));
		}catch(DayNotValidException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseController<VehiculoModel>(Constantes.MESSAGE_NO_AUTHORIZATION));
		}catch(ParkingFullException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseController<VehiculoModel>(Constantes.FULL_MESSAGE));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseController<VehiculoModel>(Constantes.VEHICLE_REGISTERED_SUCCESSFUL));
			
	}
	
	//GET
	
	@RequestMapping(value = "/estacionamientos/vehiculos/{placa}", method = RequestMethod.GET)
	public VehiculoModel  getByPlaca(@PathVariable("placa") String placa){
		return parkingService.findVehiculo(placa);
		}
	
 
	@RequestMapping(value = "/estacionamientos/vehiculos", method = RequestMethod.GET) 
	public ResponseController<List<VehiculoModel>> getAllVehiculos() { 
		return parkingService.findAll();
		} 
	
	
	@RequestMapping(value = "/estacionamientos/salida/{placa}", method = RequestMethod.GET) 
	public ResponseEntity<ResponseController<VehiculoModel>> checkOutVehiculo(@PathVariable("placa") String placa){
		parkingService.checkOut(placa);
		 VehiculoModel vehicleCheckOut= parkingService.findVehiculo(placa);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseController<VehiculoModel>(Constantes.CHECKED_VEHICLE,vehicleCheckOut));

	}
	
	
}

