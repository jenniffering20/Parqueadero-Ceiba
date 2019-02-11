package com.ceiba.estacionamiento.jenniffer.alvarez.service;


import java.util.List;


import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;

public interface ParkingService {
	
	public void ingresar(Vehiculo vehiculo) throws DomainException;
	public ResponseController<List<Vehiculo>> checkOut(String placa);
	public Vehiculo findVehiculo(String placa);
	public ResponseController<List<Vehiculo>> findAll();
	

}
