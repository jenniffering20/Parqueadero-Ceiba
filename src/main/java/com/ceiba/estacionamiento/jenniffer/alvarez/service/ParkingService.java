package com.ceiba.estacionamiento.jenniffer.alvarez.service;

import java.time.LocalDateTime;
import java.util.List;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;

public interface ParkingService {
	
	public VehiculoModel checkIn(VehiculoModel vehiculo);
	public void checkOut(String placa);
	public Boolean restrictionLetter(String placa);
	public Boolean fullParking(String tipo);
	public Boolean validDate(LocalDateTime dateCheckIn);
	public void reduceAvailability(String tipo);
	public VehiculoModel findVehiculo(String id);
	public List<VehiculoModel> registeredVehicle(String tipo,String id);
	public List<VehiculoModel> vehicles(String tipo);
	
	

}
