package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.log.LogFactory;

@ControllerAdvice////모든 익셉션은 여기로 온다.
public class GlobalExceptionHandler {
	
	//private static final Log LOG = LogFactory.getLog(GlobalExceptionHandler);
	
	@ExceptionHandler(UserDaoException.class)
	public ModelAndView handlerException(HttpServletRequest request, Exception e) {
		//1. 로깅 작업
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//LOG.error(errors.toString());
		//2. 시스템 오류 안내 화면
		ModelAndView mav = new ModelAndView();
		mav.addObject("errors",errors.toString());
		mav.setViewName("error/exception");
		return null;
	}
}
