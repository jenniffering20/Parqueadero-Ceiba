package com.ceiba.estacionamiento.jenniffer.alvarez.service;


import java.util.List;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.GeneralException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;

public interface ParkingService {
	
	public ResponseController<List<VehiculoModel>> checkIn(VehiculoModel vehiculo) throws GeneralException;
	public ResponseController<List<VehiculoModel>> checkOut(String placa);
	public VehiculoModel findVehiculo(String id);
	public ResponseController<List<VehiculoModel>> findAll();
	

}
