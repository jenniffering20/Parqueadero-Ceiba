package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.math.BigDecimal;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.PaymentCalculationService;

public class PaymentCalculation implements PaymentCalculationService{
	
	
	
	public PaymentCalculation() {
		super();
	}

	public BigDecimal paymentCalculationCar (long hora) {
		BigDecimal pay;
		
		long horas= hora / 60L;
		long minutos= hora % 60L;
		if(minutos > 0L) {
			horas=horas+1L;
		}
		
		if(horas <= Constantes.DAY_HOUR) {
			
		if(horas < Constantes.DAY_FINISH_HOUR ) {
			
			BigDecimal horasParking = BigDecimal.valueOf(horas);
			 pay= horasParking.multiply(Constantes.PRECIO_HORA_CARRO);
		}else{
			BigDecimal horasParking = BigDecimal.valueOf(1L);
			 pay= horasParking.multiply(Constantes.PRECIO_DIA_CARRO);
		} 
		}else{

			BigDecimal diasParking = BigDecimal.valueOf(horas/Constantes.DAY_HOUR);
			BigDecimal horasParking = BigDecimal.valueOf(horas % Constantes.DAY_HOUR);		
			BigDecimal diasPayment= diasParking.multiply(Constantes.PRECIO_DIA_CARRO);
			BigDecimal horasPayment= horasParking.multiply(Constantes.PRECIO_HORA_CARRO);
			
			pay= diasPayment.add(horasPayment);	
		}
		
		return pay;
	}
	
	
	
	public BigDecimal paymentCalculationMoto (long hora,int cilindraje) {
		BigDecimal pay;
		
		long horas= hora / 60L;
		long minutos= hora % 60L;
		
		if(minutos > 0L) {
			horas=horas+1L;
		}

		if(horas <= Constantes.DAY_HOUR) {
		if(horas < Constantes.DAY_FINISH_HOUR && horas <=Constantes.DAY_HOUR) {
			
			
			BigDecimal horasParking = BigDecimal.valueOf(horas);
			 pay= horasParking.multiply(Constantes.PRECIO_HORA_MOTO);
		}else {
			BigDecimal horasParking = BigDecimal.valueOf(1L);
			 pay= horasParking.multiply(Constantes.PRECIO_DIA_MOTO);
		} 
		}else {

			BigDecimal diasParking = BigDecimal.valueOf(horas/Constantes.DAY_HOUR);
			BigDecimal horasParking = BigDecimal.valueOf(horas % Constantes.DAY_HOUR);
			BigDecimal diasPayment= diasParking.multiply(Constantes.PRECIO_DIA_MOTO);
			BigDecimal horasPayment= horasParking.multiply(Constantes.PRECIO_HORA_MOTO);
			
			pay= diasPayment.add(horasPayment);	
		}
		
		if(cilindraje > 500) {
			pay= pay.add(Constantes.PRECIO_ADICION_CILINDRAJE);
		}
		
		return pay;
	}

}
