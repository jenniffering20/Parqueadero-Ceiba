package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Estacionamiento")
public class Factura {
	
	
	@Id
	private String registro;
	private String placa;
	private Vehiculo vehiculo;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private BigDecimal totalPago;
	
	 public Factura(String placa,Vehiculo vehiculo) {
		 	this.placa=placa;
			this.vehiculo = vehiculo;
		}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
   

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public BigDecimal getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(BigDecimal totalPago) {
		this.totalPago = totalPago;
	}
	

}
