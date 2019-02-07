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
	
	private LocalDateTime day;

	public LocalDateTime getDay() {
		return day;
	}

	public void setDay(LocalDateTime day) {
		this.day = day;
	}
	

	@Override
	public VehiculoModel goOut(VehiculoModel vehiculoSalida) {
		setDay(LocalDateTime.now());
		vehiculoSalida.setFechaSalida(getDay());
		
		PaymentCalculation payment=new PaymentCalculation();
		
		BigDecimal paymentCalculation;
		long horas = calculateStay(vehiculoSalida.getFechaIngreso(), vehiculoSalida.getFechaSalida());
		
		if(vehiculoSalida.getTipo().equalsIgnoreCase(Constantes.CARRO)) {
			
			paymentCalculation = payment.PaymentCalculationCar(horas);
		}else {
			paymentCalculation = payment.PaymentCalculationMoto(horas, vehiculoSalida.getCilindraje());
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
