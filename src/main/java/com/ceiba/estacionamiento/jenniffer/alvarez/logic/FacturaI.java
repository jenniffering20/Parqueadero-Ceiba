package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesMensajes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;

public class FacturaI implements BillService {
	
	
	
	public FacturaI() {
		super();
	}
	@Override
	public Factura goOutt(Factura vehiculoSalida) {
		
		PaymentCalculation payment=new PaymentCalculation();
		
		BigDecimal paymentCalculation;
		long horas = calculateStay(vehiculoSalida.getFechaIngreso(), vehiculoSalida.getFechaSalida());
		
		if(vehiculoSalida.getVehiculo().getTipo().equalsIgnoreCase(ConstantesMensajes.CARRO)) {
			
			paymentCalculation = payment.paymentCalculationCar(horas);
		}else {
			paymentCalculation = payment.paymentCalculationMoto(horas, vehiculoSalida.getVehiculo().getCilindraje());
		}
		
		vehiculoSalida.setTotalPago(paymentCalculation);
		vehiculoSalida.setPlaca(vehiculoSalida.getPlaca()+"s");
		vehiculoSalida.getVehiculo().setTipo("_"+ vehiculoSalida.getVehiculo().getTipo());
		return vehiculoSalida;
	}
	
	

	


	public long calculateStay(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		
		return Duration.between(fechaEntrada,fechaSalida).toMinutes();
		
		
	}
	
		
	

}