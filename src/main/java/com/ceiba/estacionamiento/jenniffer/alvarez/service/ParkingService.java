package com.ceiba.estacionamiento.jenniffer.alvarez.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;

public interface ParkingService {
	
	public VehiculoModel checkIn(String tipo, String placa);
	public void checkOut(long tiempo,String tipo);
	public long totalTime();
	public Boolean restrictionLetter(String placa);
	public Boolean fullParking(String tipo);
	public Boolean validDate(LocalDateTime dateCheckIn);
	public void reduceAvailability(String tipo);
	
	

}
