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
		
		valorReal = paymentCalculation.paymentCalculationCar(120L);	
	
		assertEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationCarTestError() {
		BigDecimal valorEsperado=new BigDecimal("1000");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationCar(120L);	
	
		assertNotEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoTestCilindrajenMenor() {
		BigDecimal valorEsperado=new BigDecimal("1000");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(120L,400);	
	
		assertEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoTestCilindrajenMenorError() {
		BigDecimal valorEsperado=new BigDecimal("500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(120L,400);	
	
		assertNotEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoTestCilindrajenMayor() {
		BigDecimal valorEsperado=new BigDecimal("2500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(60L, 650);	
	
		assertEquals(valorEsperado, valorReal);
	}
	@Test
	public void paymentCalculationMotoTestCilindrajenMayorError() {
		BigDecimal valorEsperado=new BigDecimal("500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(60L, 650);	
	
		assertNotEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationCarTestDay() {
		BigDecimal valorEsperado=new BigDecimal("17000");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationCar(2881L);	
	
		assertEquals(valorEsperado, valorReal);
	}
	@Test
	public void paymentCalculationCarTestDayError() {
		BigDecimal valorEsperado=new BigDecimal("1000");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationCar(2881L);	
	
		assertNotEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoCilindrajeMenorTestDay() {
		BigDecimal valorEsperado=new BigDecimal("8500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(2881L,400);	
	
		assertEquals(valorEsperado, valorReal);
	}
	@Test
	public void paymentCalculationMotoCilindrajeMenorTestDayError() {
		BigDecimal valorEsperado=new BigDecimal("6500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(2881L,400);	
	
		assertNotEquals(valorEsperado, valorReal);
	}
	
	@Test
	public void paymentCalculationMotoCilindrajeMatorTestDay() {
		BigDecimal valorEsperado=new BigDecimal("10500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(2881L,650);	
	
		assertEquals(valorEsperado, valorReal);
	}
	@Test
	public void paymentCalculationMotoCilindrajeMatorTestDayError() {
		BigDecimal valorEsperado=new BigDecimal("1500");
		BigDecimal valorReal;
		
		valorReal = paymentCalculation.paymentCalculationMoto(2881L,650);	
	
		assertNotEquals(valorEsperado, valorReal);
	}


}
