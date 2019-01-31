package com.ceiba.estacionamiento.jenniffer.alvarez.exception;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;

public class DayNotValidException extends GeneralException{

	public DayNotValidException() {
		super(Constantes.MESSAGE_NO_AUTHORIZATION);
		
	}

}
