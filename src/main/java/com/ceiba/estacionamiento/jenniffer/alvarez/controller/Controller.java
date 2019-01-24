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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Controller {

	
	@Autowired
	Repositorio repositorio;
	

	
	@PostMapping("/Estacionamiento/Anadir")
	public VehiculoModel postVehiculo(@RequestBody VehiculoModel vehiculo){
		System.out.println("añadiendo vehiculo...");
		
		VehiculoModel vehiculos= repositorio.insert(new VehiculoModel(
				vehiculo.getTipo(),vehiculo.getPlaca(),vehiculo.getCilindraje()));
		System.out.println(vehiculos.getId());
		System.out.println(vehiculos.getPlaca());
		
		return vehiculos;
	}
	

	
	
	
	
	
}

