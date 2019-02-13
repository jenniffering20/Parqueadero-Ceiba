package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Estacionamientoo")

public class Vehiculo {
	@Id
	private String registro;
	private String tipo;
	private String placa;
	private int cilindraje;

	
	public Vehiculo(String tipo,String placa,int cilindraje) {
		this.tipo=tipo;
		this.placa=placa;	
		this.cilindraje= cilindraje;

	}
	
	public Vehiculo() {
		
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

	
	
}
