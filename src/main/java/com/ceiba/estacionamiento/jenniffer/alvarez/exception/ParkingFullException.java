package com.ceiba.estacionamiento.jenniffer.alvarez.exception;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;

public class ParkingFullException extends GeneralException{

	public ParkingFullException() {
		super(Constantes.FULL_MESSAGE);
		
	}

}