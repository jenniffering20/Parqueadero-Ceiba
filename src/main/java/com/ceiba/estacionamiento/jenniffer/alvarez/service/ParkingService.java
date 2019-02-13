package com.ceiba.estacionamiento.jenniffer.alvarez.service;


import java.util.List;

import javax.transaction.Transactional;

import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
@Transactional
public interface ParkingService {
	
	
	
	public void ingresarVehiculoFactura(Vehiculo vehiculo) throws DomainException;
	public List<Factura> todasFacturas();
	public Factura encontrarFactura(String placa)throws DomainException;
	public Factura facturacionVehiculoSalida(String placa) throws DomainException;
	

}
