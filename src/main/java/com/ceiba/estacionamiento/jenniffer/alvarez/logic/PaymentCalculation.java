package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.math.BigDecimal;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesCalculoFactura;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.PaymentCalculationService;

public class PaymentCalculation implements PaymentCalculationService{
	
	
	
	public PaymentCalculation() {
		super();
	}

	public BigDecimal paymentCalculationCar (long hora) {
		BigDecimal pay = null;
		
		long horas= hora / ConstantesCalculoFactura.MINUTOS_HORAS;
		long minutos= hora % ConstantesCalculoFactura.MINUTOS_HORAS;
		if(minutos > 0L) {
			horas=horas+1L;
		}
		
		if(horas <= ConstantesCalculoFactura.DAY_HOUR) {
			
		if(horas < ConstantesCalculoFactura.DAY_FINISH_HOUR ) {
			
			BigDecimal horasParking = BigDecimal.valueOf(horas);
			 pay= horasParking.multiply(ConstantesCalculoFactura.PRECIO_HORA_CARRO);
		}else{
			BigDecimal horasParking = BigDecimal.valueOf(1L);
			 pay= horasParking.multiply(ConstantesCalculoFactura.PRECIO_DIA_CARRO);
		} 
		}else{

			BigDecimal diasParking = BigDecimal.valueOf(horas/ConstantesCalculoFactura.DAY_HOUR);
			BigDecimal horasParking = BigDecimal.valueOf(horas % ConstantesCalculoFactura.DAY_HOUR);		
			BigDecimal diasPayment= diasParking.multiply(ConstantesCalculoFactura.PRECIO_DIA_CARRO);
			BigDecimal horasPayment= horasParking.multiply(ConstantesCalculoFactura.PRECIO_HORA_CARRO);
			
			pay= diasPayment.add(horasPayment);	
		}
		
		return pay;
	}
	
	
	
	public BigDecimal paymentCalculationMoto (long hora,int cilindraje) {
		BigDecimal pay= null;
		
		long horas= hora / ConstantesCalculoFactura.MINUTOS_HORAS;
		long minutos= hora % ConstantesCalculoFactura.MINUTOS_HORAS;
		
		if(minutos > 0L) {
			horas=horas+1L;
		}

		if(horas <= ConstantesCalculoFactura.DAY_HOUR) {
		if(horas < ConstantesCalculoFactura.DAY_FINISH_HOUR) {
			
			
			BigDecimal horasParking = BigDecimal.valueOf(horas);
			 pay= horasParking.multiply(ConstantesCalculoFactura.PRECIO_HORA_MOTO);
		}
		else {
			BigDecimal horasParking = BigDecimal.valueOf(1L);
			 pay= horasParking.multiply(ConstantesCalculoFactura.PRECIO_DIA_MOTO);
		}
		}else {

			BigDecimal diasParking = BigDecimal.valueOf(horas/ConstantesCalculoFactura.DAY_HOUR);
			BigDecimal horasParking = BigDecimal.valueOf(horas % ConstantesCalculoFactura.DAY_HOUR);
			BigDecimal diasPayment= diasParking.multiply(ConstantesCalculoFactura.PRECIO_DIA_MOTO);
			BigDecimal horasPayment= horasParking.multiply(ConstantesCalculoFactura.PRECIO_HORA_MOTO);
			
			pay= diasPayment.add(horasPayment);	
		}
		
		if(cilindraje > ConstantesCalculoFactura.CILINDRAJE_PARA_COBRO_EXTA) {
			pay= pay.add(ConstantesCalculoFactura.PRECIO_ADICION_CILINDRAJE);
		}
		
		return pay;
	}
	

}
