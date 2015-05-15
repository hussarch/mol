package com.hussar.app.mol.common.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hussar.app.mol.common.ot.OrderTimeCalculator;

public class OrderTimeCalculatorTest {

	private OrderTimeCalculator calculator;
	
	
	@Before
	public void setUp() throws Exception {
		calculator = new OrderTimeCalculator();
		calculator.init();
	}

	@Test
	public void test() {
		assertEquals("11:30", calculator.getStartOrderTime(1));
		
		assertEquals("11:50", calculator.getStartOrderTime(2));
		
		assertEquals("12:10", calculator.getStartOrderTime(3));
		
		assertEquals("12:30", calculator.getStartOrderTime(4));
		
		assertEquals("14:30", calculator.getStartOrderTime(10));
		
	}

}
