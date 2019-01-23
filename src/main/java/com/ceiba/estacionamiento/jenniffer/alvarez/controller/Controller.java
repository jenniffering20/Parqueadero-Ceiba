package com.ceiba.estacionamiento.jenniffer.alvarez.controller;

import javax.validation.Valid;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Controller {

	
	@Autowired
	Repositorio repositorio;
	
	@GetMapping("/AllVehiculos")
	public List<VehiculoModel> getAllVehiculos(){
		System.out.println("Get all vehiculos...");
		
		List<VehiculoModel> vehiculos = new ArrayList<>();
		repositorio.findAll().forEach(vehiculos::add);
		return vehiculos;
	}
	
	@PostMapping("/Estacionamiento/Anadir")
	public VehiculoModel postVehiculo(@RequestBody VehiculoModel vehiculo){
		System.out.println("añadiendo vehiculo...");
		
		VehiculoModel vehiculos= repositorio.insert(new VehiculoModel(
				vehiculo.getTipo(),vehiculo.getPlaca(),vehiculo.getCilindraje()));
		return vehiculos;
	}
}

