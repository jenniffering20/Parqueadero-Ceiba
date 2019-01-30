package com.ceiba.estacionamiento.jenniffer.alvarez.logic;


import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;
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
	

	int fullCarros = 20;
	
	public int getFullCarros() {
		return fullCarros;
	}
	
	public void setFullCarros(int NumeroVehiculos) {
		fullCarros = NumeroVehiculos;
	}
	
	public int FULL_CARROS= getFullCarros();
	static int FULL_MOTOS=10;
	static String FULL_MESSAGE="El estacionamiento no tiene espacio disponible";
	static String REGISTERED_MESSAGE="la Placa ya se encuentra registrada, verificar";
	static String MESSAGE_NO_AUTHORIZATION="No esta autorizado para ingresar";
	static final String LETRA_RESTRICCION="A";
	
	@Autowired
	Repositorio repositorio;
	
	
	
	
	public ParkingImp() {
		 
	}
	
	

	@Override
	public VehiculoModel checkIn(VehiculoModel vehiculo) {
		LocalDateTime day = LocalDateTime.now();
		if(fullParking(vehiculo.getTipo())) { 
			System.out.println(FULL_MESSAGE);
			return null;
		}else { 
			if(restrictionLetter(vehiculo.getPlaca())) {
				if(validDate(day)) {
					System.out.println(MESSAGE_NO_AUTHORIZATION);
					return null;
				}
			}
			if(findVehiculo(vehiculo.getPlaca()) == null) {
			VehiculoModel newVehiculo =new  VehiculoModel(vehiculo.getTipo(),vehiculo.getPlaca());
			newVehiculo.setFechaIngreso(day);
			VehiculoModel vehiculos = repositorio.save(newVehiculo);
			reduceAvailability(vehiculo.getTipo());
			return vehiculos;	
			}else System.out.println(REGISTERED_MESSAGE);
			return null;
			
		}
		}
		

	@Override
	public void checkOut(String placa) {
		BillService bill = new Bill();
		VehiculoModel vehiculoToLeave = findVehiculo(placa);
		if(findVehiculo(placa) != null) {
			bill.goOut(vehiculoToLeave);
		}
	
	}

	
	private Boolean restrictionLetter(String placa) {
		Boolean letter= placa.toUpperCase().startsWith(LETRA_RESTRICCION);
		 boolean restriction = (letter)? true: false; 
		return restriction;
	}



	
	public Boolean fullParking(String tipo) {
		Boolean full=false;
		full = (getFullCarros() == 0 && FULL_MOTOS == 0) ? true:false;
		
		if(tipo.equalsIgnoreCase("CARRO") && getFullCarros()== 0) {
			full= true;
		}
			
		if(tipo.equalsIgnoreCase("MOTO") && FULL_MOTOS== 0) {
			full= true;
		}
		
		return full;
	}


	
	private Boolean validDate(LocalDateTime dateCheckIn) {
		Boolean validDate = (dateCheckIn.getDayOfWeek().getValue()==1 ||dateCheckIn.getDayOfWeek().getValue()==0)
				? false: true;
		return validDate;
	}



	private void reduceAvailability(String tipo) {
		
		if(tipo.equalsIgnoreCase("CARRO")) {
			int catidadVehiculos = getFullCarros();
			setFullCarros(catidadVehiculos - 1 );
		}
		
		else {
			FULL_MOTOS-=1;
		}
		
		System.out.println(getFullCarros());
		System.out.println(FULL_MOTOS);
	}


	@Override
	public VehiculoModel findVehiculo(String placa) {
		return repositorio.findByPlaca(placa);
				
	}



	@Override
	public List<VehiculoModel> registeredVehicle(String tipo, String id) {
		List<VehiculoModel> vehiculos= vehicles(tipo);
		return vehiculos;
	}



	@Override
	public List<VehiculoModel> vehicles(String tipo) {	
		return repositorio.findByTipo(tipo);
	}



	

}
