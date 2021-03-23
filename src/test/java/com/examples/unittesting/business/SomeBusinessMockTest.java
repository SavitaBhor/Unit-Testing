package com.examples.unittesting.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.examples.unittesting.data.SomeDataService;

//MockitoJUnitRunner-The automatic validation of framework usage is actually worth having. 
//It gives you better reporting if you make one of these mistakes.
//you can't use it if you need another JUnit runner, such as the Spring one.
@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTest {
	
	@InjectMocks
	SomeBusinessImpl business;	
	//SomeBusinessImpl business = new SomeBusinessImpl();	
	
	@Mock
	SomeDataService dataServiceMock;
	//SomeDataService dataServiceMock = mock(SomeDataService.class); no need because of @Mock
	
	// no need of this because of InjectMock
//	@Before
//	public void setUp() {
//		business.setSomeDataService(dataServiceMock);
//	}

	@Test
	public void calculateSumUsingDataService_Basic() {
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3});		
		assertEquals(6, business.calculateSumUsingDataService());
	}
	
	@Test
	public void calculateSumUsingDataService_Empty() {
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});		
		assertEquals(0, business.calculateSumUsingDataService());
	}
	
	@Test
	public void calculateSumUsingDataService_Onevalue() {
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1});		
		assertEquals(1, business.calculateSumUsingDataService());
	}

}
