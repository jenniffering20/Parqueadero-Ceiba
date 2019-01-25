package com.ceiba.estacionamiento.jenniffer.alvarez.controller;


import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;
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
	Repositorio repositorio;
	

	//POST
	@PostMapping("/Estacionamiento/Anadir")
	public VehiculoModel postVehiculo(@RequestBody VehiculoModel vehiculo){
		System.out.println("añadiendo vehiculo...");
		
		VehiculoModel vehiculos= repositorio.insert(new VehiculoModel(
				vehiculo.getTipo(),vehiculo.getPlaca()));
		return vehiculos;
	}
	
	//GET
	@RequestMapping(value = "/Estacionamiento/Vehiculos", method = RequestMethod.GET) 
	public List<VehiculoModel> getAllVehiculos() { 
		return repositorio.findAll();
		}
	
	@RequestMapping(value = "/Estacionamiento/Vehiculos/{id}", method = RequestMethod.GET)
	public ResponseEntity<VehiculoModel> getById(@PathVariable("id") String id){
		return repositorio.findById(id)
				.map(vehiculoModel -> ResponseEntity.ok().body(vehiculoModel))
				.orElse(ResponseEntity.notFound().build());
		}
	
	//PUT
	@RequestMapping(value ="/Estacionamiento/EditVehiculos", method = RequestMethod.PUT)
	public ResponseEntity<VehiculoModel> upDateVehiculo(@PathVariable("id") String id, 
			@Valid @RequestBody VehiculoModel vehiculoModel ){
		
		return repositorio.findById(id)
				.map(vehiculos -> {
					vehiculos.setPlaca(vehiculoModel.getPlaca());
					VehiculoModel upDateVehiculo = repositorio.save(vehiculos);
					return ResponseEntity.ok().body(upDateVehiculo);
				}).orElse(ResponseEntity.notFound().build());
		
	}

	//DELETE 
	
	
	
	

	
	
	
	
	
}

