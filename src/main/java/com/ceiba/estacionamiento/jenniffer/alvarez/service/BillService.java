package com.ceiba.estacionamiento.jenniffer.alvarez.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;

public interface BillService {
	
	public VehiculoModel goOut(VehiculoModel vehiculoSalida);
	public long calculateStay(LocalDateTime fechaEntrada, LocalDateTime fechaSalida);
	//public BigDecimal payment();
	
	

}
