package com.ceiba.estacionamiento.jenniffer.alvarez.controller;


import com.ceiba.estacionamiento.jenniffer.alvarez.logic.ParkingImp;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	@PostMapping("/Estacionamiento/Anadir")
	public VehiculoModel postVehiculo(@RequestBody VehiculoModel vehiculo) {
		VehiculoModel vehiculoInsert = parkingService.checkIn(vehiculo.getTipo(),vehiculo.getPlaca());
		if(vehiculoInsert != null) {
			return vehiculoInsert;
			}
		return null;
	}
	
	//GET
	
	@RequestMapping(value = "/Estacionamiento/Vehiculos/{placa}", method = RequestMethod.GET)
	public VehiculoModel  getByPlaca(@PathVariable("placa") String placa){
		return parkingService.findVehiculo(placa);
		}
	
/* 
 * @RequestMapping(value = "/Estacionamiento/Vehiculos", method = RequestMethod.GET) 
	public List<VehiculoModel> getAllVehiculos() { 
		return repositorio.findAll();
		} //COREGIR ERROR
	
	//PUT
	@RequestMapping(value ="/Estacionamiento/EditVehiculos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<VehiculoModel> upDateVehiculo(@PathVariable("id") String id, 
			@Valid @RequestBody VehiculoModel vehiculoModel){
		
		return repositorio.findById(id).map(vehiculos -> {
					vehiculos.setPlaca(vehiculoModel.getPlaca());
					VehiculoModel upDateVehiculo = repositorio.save(vehiculos);
					return ResponseEntity.ok().body(upDateVehiculo);
				}).orElse(ResponseEntity.notFound().build());
		
	}

	//DELETE 
	@RequestMapping(value ="/Estacionamiento/salidaVehiculo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> salidaVehiculo(@PathVariable("id") String id){
		return repositorio.findById(id)
				.map(vehiculo -> {
					repositorio.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	
	*/
	

	
	
	
	
	
}

