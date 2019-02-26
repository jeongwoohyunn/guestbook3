package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth.Role;

public class Authlnterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler)
			throws Exception {
		
		//1. Handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			//핸들러 두개가 아니면 디폴트 서블릿으로가라고 1번 true
			return true;
		}
		
		//2. Casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method에 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation( Auth.class );
		
		//3-1. Method에 @Auth가 안 붙어 있으면 class(type)의 @Auth 받아오기
		/*
		if( auth == null ) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation( Auth.class );
		}
		*/
		//4. Method 와 class에 @Auth가 안 붙어 있다. - 인증안해도되는 메소드
		if( auth == null ) {
			return true;
		}
		
		// 5. @Auth 가 불어 있기 때문에 로그인 여부 ( 인증 여부 ) 를 확인해야한다.
				HttpSession session = request.getSession();
				UserVo authuser = null;
				if(session != null) {
					authuser = (UserVo)session.getAttribute("authuser");
				}

				if(authuser == null) {
					response.sendRedirect(request.getContextPath()+"/user/login");
					return false;
				}

				//5-1. role 비교작업 
				Role role = auth.value();
				if(authuser.getRole().equals(role.toString())) {
					//접근허용
					return true;
				} else {
					//접근거절
					response.sendRedirect(request.getContextPath()+"/");
					return false;
				}

				// 6. 접근허용
				//return true;

			}

}