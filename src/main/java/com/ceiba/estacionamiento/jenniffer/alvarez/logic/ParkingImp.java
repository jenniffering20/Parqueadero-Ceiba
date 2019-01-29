package com.ceiba.estacionamiento.jenniffer.alvarez.logic;


import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
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
public class ParkingImp implements ParkingService {
	

	static int FULL_CARROS=20;
	static int FULL_MOTOS=10;
	static String FULL_MESSAGE="El estacionamiento no tiene espacio disponible";
	static String MESSAGE_NO_AUTHORIZATION="No esta autorizado para ingresar";
	static final String LETRA_RESTRICCION="A";
	
	@Autowired
	Repositorio repositorio;
	
	public ParkingImp() {
		 
	}
	
	

	@Override
	public VehiculoModel checkIn(String tipo, String placa) {
		LocalDateTime day = LocalDateTime.now();
		if(fullParking(tipo)) { 
			System.out.println(FULL_MESSAGE);
			return null;
		}else { 
			if(restrictionLetter(placa)) {
				if(validDate(day)) {
					System.out.println(MESSAGE_NO_AUTHORIZATION);
					return null;
				}
			}
			VehiculoModel newVehiculo =new  VehiculoModel(tipo,placa);
			newVehiculo.setFechaIngreso(day);
			VehiculoModel vehiculos = repositorio.save(newVehiculo);
			reduceAvailability(tipo);
			return vehiculos;			
		}
		}
		



	@Override
	public void checkOut(long tiempo, String tipo) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public long totalTime() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Boolean restrictionLetter(String placa) {
		Boolean letter= placa.toUpperCase().startsWith(LETRA_RESTRICCION);
		 boolean restriction = (letter)? true: false; 
		return restriction;
	}



	@Override
	public Boolean fullParking(String tipo) {
		Boolean full=false;
		full=(FULL_CARROS== 0 && FULL_MOTOS==0)?true:false;
		if(tipo.equalsIgnoreCase("CARRO") && FULL_CARROS== 0)
			full= true;
		if(tipo.equalsIgnoreCase("MOTO") && FULL_MOTOS== 0)
			full= true;
		
		return full;

	}



	@Override
	public Boolean validDate(LocalDateTime dateCheckIn) {
		Boolean validDate = (dateCheckIn.getDayOfWeek().getValue()==1 ||dateCheckIn.getDayOfWeek().getValue()==0)
				? false: true;
		return validDate;
	}



	@Override
	public void reduceAvailability(String tipo) {
		if(tipo.equalsIgnoreCase("CARRO"))
			FULL_CARROS-=1;
		else
			FULL_MOTOS-=1;
	
		System.out.println(FULL_CARROS);
		System.out.println(FULL_MOTOS);
	}


/*
	@Override
	public ResponseEntity<VehiculoModel> findVehiculo(String id) {
		return repositorio.findById(id)
				.map(vehiculoModel -> ResponseEntity.ok().body(vehiculoModel))
				.orElse(ResponseEntity.notFound().build());
	}

*/	

	

}
