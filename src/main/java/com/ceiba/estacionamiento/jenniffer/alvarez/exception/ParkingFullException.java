package com.ceiba.estacionamiento.jenniffer.alvarez.exception;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesMensajes;

public class ParkingFullException extends DomainException{

	public ParkingFullException() {
		super(ConstantesMensajes.FULL_MESSAGE);
		
	}

}
