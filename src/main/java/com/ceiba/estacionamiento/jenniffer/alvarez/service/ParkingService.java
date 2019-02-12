package com.ceiba.estacionamiento.jenniffer.alvarez.service;


import java.util.List;

import javax.transaction.Transactional;

import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.RespuestaParaControlador;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
@Transactional
public interface ParkingService {
	
	public void ingresarVehiculo(Vehiculo vehiculo) throws DomainException;
	public Vehiculo facturacionVehiculo(String placa) throws DomainException;
	public Vehiculo findVehiculo(String placa) throws DomainException;
	public RespuestaParaControlador<List<Vehiculo>> findAll();
	

}
