package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler( Exception.class )
	public ModelAndView handleException( HttpServletRequest request, Exception e ) throws Exception {
		
		//1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace( new PrintWriter( errors ) );
		
		//2. 안내페이지 가기
		ModelAndView mav = new ModelAndView();
		mav.addObject( "uri", request.getRequestURI() );
		mav.addObject( "exception", errors.toString() );
		mav.setViewName( "error/exception" );
		
		return mav;
	}
	
	
}