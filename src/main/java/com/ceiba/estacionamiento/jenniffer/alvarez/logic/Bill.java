package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.time.Duration;
import java.time.LocalDateTime;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;

public class Bill implements BillService {
	
	
	
	public Bill() {
		
		
	}

	@Override
	public VehiculoModel goOut(VehiculoModel vehiculoSalida) {
		vehiculoSalida.setFechaSalida(LocalDateTime.now());
		
		return null;
	}

	@Override
	public long calculateStay(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		
		long diffHoras=Duration.between(fechaEntrada,fechaSalida).toHours();
		return diffHoras;
	}
	
	

}
