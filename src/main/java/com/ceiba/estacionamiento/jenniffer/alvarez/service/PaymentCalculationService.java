package com.ceiba.estacionamiento.jenniffer.alvarez.service;

import java.math.BigDecimal;

public interface PaymentCalculationService {
	public BigDecimal PaymentCalculationCar (long horas);
	public BigDecimal PaymentCalculationMoto (long horas,int cilindraje);

}
