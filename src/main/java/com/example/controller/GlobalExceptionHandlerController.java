package com.example.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {
	@ExceptionHandler(value = Exception.class)
	public String handlerException(Exception e) {
		if(e instanceof SQLException) {
			return "error";
		}else{
			return "error";
		}
	}
}
