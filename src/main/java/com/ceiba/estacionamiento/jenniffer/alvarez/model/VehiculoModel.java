package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Estacionamientoo")
public class VehiculoModel {
	@Id
	private String id;
	
	private String tipo;
	private String placa;
	
	
	
	public VehiculoModel(String tipo,String placa) {
		this.tipo=tipo;
		this.placa=placa;
		
	}
	
	
	public VehiculoModel() {
		
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
		return "VehiculoModel [id=" + id + ", tipo=" + tipo + ", placa=" + placa;
	}
	
	
}
