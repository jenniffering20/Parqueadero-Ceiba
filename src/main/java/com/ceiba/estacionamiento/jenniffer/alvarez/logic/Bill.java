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
		
		PaymentCalculation payment=new PaymentCalculation();
		
		BigDecimal paymentCalculation;
		long horas = calculateStay(vehiculoSalida.getFechaIngreso(), vehiculoSalida.getFechaSalida());
		
		if(vehiculoSalida.getTipo().equalsIgnoreCase(Constantes.CARRO)) {
			
			paymentCalculation = payment.paymentCalculationCar(horas);
		}else {
			paymentCalculation = payment.paymentCalculationMoto(horas, vehiculoSalida.getCilindraje());
		}
		
		vehiculoSalida.setTotalPago(paymentCalculation);
		vehiculoSalida.setTipo("CheckOut");
		vehiculoSalida.setPlaca(vehiculoSalida.getPlaca()+"s");
		
		return vehiculoSalida;
	}


	public long calculateStay(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		
		return Duration.between(fechaEntrada,fechaSalida).toMinutes();
	}
	
	

}