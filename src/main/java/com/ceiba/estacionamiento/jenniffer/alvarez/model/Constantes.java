package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import java.math.BigDecimal;

public class Constantes {
	
	public static final BigDecimal PRECIO_HORA_CARRO=new BigDecimal("1000");
	public static final BigDecimal PRECIO_HORA_MOTO=new BigDecimal("500");
	public static final BigDecimal PRECIO_DIA_CARRO=new BigDecimal("8000");
	public static final BigDecimal PRECIO_DIA_MOTO=new BigDecimal("4000");
	public static final BigDecimal PRECIO_ADICION_CILINDRAJE=new BigDecimal("2000");
	public static final String FULL_MESSAGE = "El estacionamiento no tiene espacio disponible";
	public static final String NOT_VALID_TYPE ="Seleccionar un tipo de vehiculo";
	public static final String REGISTERED_MESSAGE = "la Placa ya se encuentra registrada, verificar";
	public static final String MESSAGE_NO_AUTHORIZATION = "No esta autorizado para ingresar";
	public static final String VEHICLE_REGISTERED_SUCCESSFUL="Registro Exitoso";
	public static final String CHECKED_VEHICLE="Vehiculo facturado";
	public static final String NOT_VEHICLES="No hay vehiculos en el parqueadero";
	public static final String NOT_VEHICLE_ISPARKING="El vehiculo no se encuantra en el parqueadero";
	public static final String LETRA_RESTRICCION = "A";
	public static final String CARRO="CARRO";
	public static final String MOTO="MOTO";
	public static final int DAY_FINISH_HOUR=9;
	public static final int DAY_HOUR=24;
}
