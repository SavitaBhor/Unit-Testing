package com.examples.unittesting.business;

import java.util.Arrays;

import com.examples.unittesting.data.SomeDataService;

public class SomeBusinessImpl {
	
	private SomeDataService someDataService;
		
	public void setSomeDataService(SomeDataService someDataService) {
		this.someDataService = someDataService;
	}

	public int calculateSum(int[] data) {
//		int sum = 0;
//		for(int value: data) {
//			sum += value;
//		}
//		return sum;
		return Arrays.stream(data).reduce(Integer::sum).orElse(0);
	}
	
	public int calculateSumUsingDataService() {
		int sum = 0;
		int[] data = someDataService.retrieveAllData();
		for(int value: data) {
			sum += value;
		}
		// smeDataService.storeSum(sum); verify the argument or check the arguments
		return sum;
	}
}
