package com.ceiba.estacionamiento.jenniffer.alvarez.exception;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesMensajes;

public class DayNotValidException extends DomainException{

	public DayNotValidException() {
		super(ConstantesMensajes.MESSAGE_NO_AUTHORIZATION);
		
	}

}
