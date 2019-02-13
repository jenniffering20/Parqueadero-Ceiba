package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import java.math.BigDecimal;

public final class ConstantesCalculoFactura {

	
	public static final BigDecimal PRECIO_HORA_CARRO=new BigDecimal("1000");
	public static final BigDecimal PRECIO_HORA_MOTO=new BigDecimal("500");
	public static final BigDecimal PRECIO_DIA_CARRO=new BigDecimal("8000");
	public static final BigDecimal PRECIO_DIA_MOTO=new BigDecimal("4000");
	public static final BigDecimal PRECIO_ADICION_CILINDRAJE=new BigDecimal("2000");
	public static final int CILINDRAJE_PARA_COBRO_EXTA=500;
	public static final int DAY_FINISH_HOUR=9;
	public static final int DAY_HOUR=24;
	public static final Long MINUTOS_HORAS=60L;
}
