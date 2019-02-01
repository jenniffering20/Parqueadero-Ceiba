package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;

public class Bill implements BillService {
	
	
	
	public Bill() {
		super();
	}

	@Override
	public VehiculoModel goOut(VehiculoModel vehiculoSalida) {
		
		vehiculoSalida.setFechaSalida(LocalDateTime.now());
		
		PaymentCalculation payment=new PaymentCalculation();
		BigDecimal PaymentCalculation;
		long horas = calculateStay(vehiculoSalida.getFechaIngreso(), vehiculoSalida.getFechaSalida());
		
		if(vehiculoSalida.getTipo().equalsIgnoreCase(Constantes.CARRO)) {
			
			PaymentCalculation = payment.PaymentCalculationCar(horas);
		}else {
			PaymentCalculation =payment.PaymentCalculationMoto(horas, vehiculoSalida.getCilindraje());
		}
		
		vehiculoSalida.setTotalPago(PaymentCalculation);
		
		return vehiculoSalida;
	}


	public long calculateStay(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		
		long diffHoras=Duration.between(fechaEntrada,fechaSalida).toHours();
		return diffHoras;
	}
	
	

}
