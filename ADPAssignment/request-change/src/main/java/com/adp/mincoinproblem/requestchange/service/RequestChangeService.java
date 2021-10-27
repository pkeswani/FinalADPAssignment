package com.adp.mincoinproblem.requestchange.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.adp.mincoinproblem.requestchange.exception.RequestedChangeNotFoundException;

@Service
public class RequestChangeService {
	
	long[] availableBills = {1,2,5,10,20,50,100};
	
	double[] coinDenom = {0.01,0.05,0.1,0.25};
	
	double[] coinDenomInCents = {1,5,10,25};
	
	
	
	int[] cointLimit= {};
	
	Map<Integer,Integer> coins = new HashMap<Integer,Integer>();
	
	public RequestChangeService()
	{
		coins.put(1, 100);
		coins.put(5, 100);
		coins.put(10, 100);
		coins.put(25, 100);
		
		Arrays.sort(coinDenom);

	}
	
	public String getMinCoins(int bill) throws Exception
	{
		Optional<List<Integer>> change = getMinCoinChange(coinDenomInCents, bill);
		String msg=null;
		if(!change.isPresent())
			throw new RequestedChangeNotFoundException("Change cannot be tendered");
		else if (change.get().get(0)>100)
		{
			throw new RequestedChangeNotFoundException("Change cannot be tendered");
		}
		else {
			
			
		    return "At least " + change.get().get(0) 
	        + " coins "+" of " + change.get().get(1)+" cents are required to make a value of " + bill;
			
			
		}
		
	}

	
	private Optional<List<Integer>> getMinCoinChange(double[] coinDenom2,Integer bill)
	{
		
		int totalCoins = coinDenom2.length;
		
		Integer billInCents=bill*100;

		  // Creating array which stores subproblems' solutions
		  double[][] arr = new double[totalCoins + 1][billInCents + 1];

		  // Initialising first row with +ve infinity
		  for(int j = 0; j <= billInCents; j++){
			  arr[0][j] = Integer.MAX_VALUE-1;
		  }	      
		  // Initialising first column with 0
		  for(int i = 1; i <= totalCoins; i++){
			  arr[i][0] = 0;
		  }
		  for(int i = 1; i <= totalCoins; i++){
			  for(int j = 1; j <= billInCents; j++){
				  if(coinDenom2[i - 1] <= j)
					  arr[i][j] = min(1 + arr[i][(int) (j - coinDenom2[i - 1])], arr[i - 1][j]);
				  else
					  arr[i][j] = arr[i - 1][j];
			  }
		  }
		  
		   for(double[] array: arr) { 
		        System.out.print("[");
		        for (double n: array) {  
		          System.out.print(n + " "); // printing each item
		        }
		        System.out.print("]"); // printing new line
		      }
		      System.out.println("]\n");
		      
		      List<Integer> result = new ArrayList<Integer>();
		      
		      result.add((int)arr[totalCoins][billInCents]);
		      result.add((int)coinDenom2[totalCoins-1]);

		      return Optional.of(result);
		  
		
	}
	
		  double min(double a, double b){
		    if(a <= b){
		      return a;
		    }
		    else{
		      return b;
		    }
		  }

}
