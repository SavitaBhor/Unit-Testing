package com.examples.unittesting.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SomeBusinessTest {
	
	@Before
	public void setUp() {
		
	}

	@Test
	public void calculateSum_Basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();		
		assertEquals(6, business.calculateSum(new int[] {1,2,3}));
	}
	
	@Test
	public void calculateSum_Empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();		
		assertEquals(0, business.calculateSum(new int[] {}));
	}
	
	@Test
	public void calculateSum_Onevalue() {
		SomeBusinessImpl business = new SomeBusinessImpl();		
		assertEquals(1, business.calculateSum(new int[] {1}));
	}

}
