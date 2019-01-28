package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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

	
	public VehiculoModel(String tipo,String placa) {
		this.tipo=tipo;
		this.placa=placa;	
	
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


	
	@Override
	public String toString() {
		return "VehiculoModel [id=" + registro + ", tipo=" + tipo + ", placa=" + placa;
	}
	
	
}
