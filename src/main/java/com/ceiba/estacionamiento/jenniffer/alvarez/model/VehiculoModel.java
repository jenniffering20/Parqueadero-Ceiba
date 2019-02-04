package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Estacionamientoo")
public class VehiculoModel {
	@Id
	private String registro;
	private String tipo;
	private String placa;
	private int cilindraje;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private BigDecimal totalPago;

	
	public VehiculoModel(String tipo,String placa,int cilindraje) {
		this.tipo=tipo;
		this.placa=placa;	
		this.cilindraje= cilindraje;
		this.fechaIngreso= fechaIngreso;
	
	}
	public VehiculoModel() {
		
	}
	
	
	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime localDateTime) {
		this.fechaIngreso = localDateTime;
	}


	public String getId() {
		return registro;
	}


	public void setId(String id) {
		this.registro = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
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
	@Override
	public String toString() {
		return "VehiculoModel [id=" + registro + ", tipo=" + tipo + ", placa=" + placa;
	}
	
	
}
