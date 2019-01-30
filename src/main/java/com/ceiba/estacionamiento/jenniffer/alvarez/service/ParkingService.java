package com.ceiba.estacionamiento.jenniffer.alvarez.service;

import java.time.LocalDateTime;
import java.util.List;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;

public interface ParkingService {
	
	public VehiculoModel checkIn(VehiculoModel vehiculo) throws Exception;
	public void checkOut(String placa);
	public VehiculoModel findVehiculo(String id);
	public List<VehiculoModel> registeredVehicle(String tipo,String id);
	public List<VehiculoModel> vehicles(String tipo);
	
	

}
