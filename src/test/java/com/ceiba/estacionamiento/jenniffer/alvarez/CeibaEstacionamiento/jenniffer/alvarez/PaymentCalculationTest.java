package com.ceiba.estacionamiento.jenniffer.alvarez.CeibaEstacionamiento.jenniffer.alvarez;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import com.ceiba.estacionamiento.jenniffer.alvarez.logic.PaymentCalculation;

public class PaymentCalculationTest {

	private PaymentCalculation paymentCalculation;
	
	@Before
	public void setup() {
		paymentCalculation= new PaymentCalculation();
	}
	
	@Test
	public void paymentCalculationCarTest() {
		BigDecimal valorEsperado=new BigDecimal("2000");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.PaymentCalculationCar(120L);	
	
		assertEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoTestCilindrajenMenor() {
		BigDecimal valorEsperado=new BigDecimal("1000");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.PaymentCalculationMoto(120L,400);	
	
		assertEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoTestCilindrajenMayor() {
		BigDecimal valorEsperado=new BigDecimal("2500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.PaymentCalculationMoto(60L, 650);	
	
		assertEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationCarTestDay() {
		BigDecimal valorEsperado=new BigDecimal("17000");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.PaymentCalculationCar(2881L);	
	
		assertEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoCilindrajeMenorTestDay() {
		BigDecimal valorEsperado=new BigDecimal("8500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.PaymentCalculationMoto(2881L,400);	
	
		assertEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoCilindrajeMatorTestDay() {
		BigDecimal valorEsperado=new BigDecimal("10500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.PaymentCalculationMoto(2881L,650);	
	
		assertEquals(valorEsperado, valorReal);
	}


}
