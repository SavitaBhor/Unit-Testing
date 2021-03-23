package com.examples.unittesting.business;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;

import java.util.ArrayList;
import java.util.List;;

public class ListMockTest {
	//https://github.com/mockito/mockito/wiki/FAQ
	
	List<String> mock = mock(List.class);

	@Test
	public void size_basic() {		
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}
	
	@Test
	public void returnDifferentValues() {
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}

	@Test
	public void returnWithParameters() {
		when(mock.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals(null, mock.get(1));
		
		// default - numeric 0,boolean false, objects null, collections empty 
	}
	
	@Test
	public void returnWithGenericParameters() {
		when(mock.get(anyInt())).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals("in28Minutes", mock.get(1));
	}
	
	@Test
	public void verificationBasics() {
		//SUT
		String value1 = mock.get(0);
		String value2 = mock.get(1);
		//Verify
		verify(mock).get(0);
		verify(mock,times(2)).get(anyInt());
		verify(mock,atLeast(1)).get(anyInt());
		verify(mock,atLeastOnce()).get(anyInt());
		verify(mock,atMost(2)).get(anyInt());
		verify(mock,never()).get(2);
	}
	
	@Test
	public void argumentCapturing() {
		//SUT
		mock.add("Something");
		//verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock).add(captor.capture());
		assertEquals("Something",captor.getValue());
		
	}
	
	@Test
	public void multipleArgumentCapturing() {
		//SUT
		mock.add("Something1");
		mock.add("Something2");
		//verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock,times(2)).add(captor.capture());
		
		List<String> allValues = captor.getAllValues();
		assertEquals("Something1",allValues.get(0));
		assertEquals("Something2",allValues.get(1));
	}
	
	@Test
	public void mocking() {
		// Instead of real word actions
		ArrayList arrayListSpy = mock(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0)); //null
		System.out.println(arrayListSpy.size()); //0
		arrayListSpy.add("Test1");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size()); //0
		
		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size()); //5
		
		arrayListSpy.add("Test4");
		System.out.println(arrayListSpy.size()); //5
	}
	
	@Test
	public void spying() {
		// a spy by default retains behavior of the original class
		//let the action to happen and keep watch on them
		ArrayList arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0)); //Test0
		System.out.println(arrayListSpy.size()); //1
		arrayListSpy.add("Test1");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size()); //3
		
		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size()); //5
		
		arrayListSpy.add("Test4");
		System.out.println(arrayListSpy.size()); //5
	}
}
	















		