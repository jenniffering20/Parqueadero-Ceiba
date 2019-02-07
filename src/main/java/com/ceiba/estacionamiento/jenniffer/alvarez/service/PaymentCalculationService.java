package com.ceiba.estacionamiento.jenniffer.alvarez.service;

import java.math.BigDecimal;

public interface PaymentCalculationService {
	public BigDecimal paymentCalculationCar (long horas);
	public BigDecimal paymentCalculationMoto (long horas,int cilindraje);

}
