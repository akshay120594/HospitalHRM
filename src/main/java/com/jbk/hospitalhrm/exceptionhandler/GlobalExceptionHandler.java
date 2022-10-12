package com.jbk.hospitalhrm.exceptionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String,Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("Time",new Date());
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			map.put(fieldError.getField(),fieldError.getDefaultMessage());
			
		}
		return map;
		
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
	}
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<String> handleDoctorNotFoundException(DoctorNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
	}
	@ExceptionHandler(NotEnoughContent.class)
	public ResponseEntity<String> handleNotEnoughContent(NotEnoughContent ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
	}

}
