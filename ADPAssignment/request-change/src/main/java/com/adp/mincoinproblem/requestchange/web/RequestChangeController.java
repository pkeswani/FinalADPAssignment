package com.adp.mincoinproblem.requestchange.web;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adp.mincoinproblem.requestchange.exception.RequestedChangeNotFoundException;
import com.adp.mincoinproblem.requestchange.service.RequestChangeService;

@RestController
@Validated
@RequestMapping("/reqChange")
public class RequestChangeController {
	
	@Autowired
	RequestChangeService changeSvc;
	
	@GetMapping("/coins/{bill}")
	public ResponseEntity<String> getMinCoins(@PathVariable("bill") @Min(1) @Max(100) int bill)
	{
		try {
		String msg;
		
			msg = changeSvc.getMinCoins(bill);
		
			//return new ResponseEntity<>(list,HttpStatus.OK);
			return ResponseEntity.ok().body(msg);
		}
		catch(RequestedChangeNotFoundException exception)
		{
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Change cannot be tendered");
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Change cannot be tendered");
		}
		
	}
	
	 @ExceptionHandler(ConstraintViolationException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
	    return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	  }

}
