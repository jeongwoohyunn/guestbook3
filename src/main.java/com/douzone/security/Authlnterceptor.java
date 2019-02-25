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
			return true;
		}
		
		//2. Casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method에 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation( Auth.class );
		
		//3-1. Method에 @Auth가 안 붙어 있으면 class(type)의 @Auth 받아오기
		if( auth == null ) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation( Auth.class );
		}
		
		//4. Method에 @Auth가 안 붙어 있으면
		if( auth == null ) {
			return true;
		}
		
		//5. @Auth 붙어 있기 때문에 로그인 여부(인증여부)를 확인해야 한다.
		HttpSession session = request.getSession();
		UserVo authUser = (session == null) ? null : (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}

		//5.1 Role 비교 작업
		Role role = auth.value();
		
		
		
		//6. 접근 허용
		return true;
	}

}