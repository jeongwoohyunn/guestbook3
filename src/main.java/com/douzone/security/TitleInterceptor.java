package com.douzone.security;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.SiteVo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
public class TitleInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		ServletContext sc = request.getServletContext();

		ModelMap m = modelAndView.getModelMap();
		System.out.println(m);
		SiteVo siteVo = (SiteVo) m.get("siteVo");
		System.out.println(siteVo);
		if (sc == null) {
			// modelAndView.addObject("siteVo", vo);

			m.addAttribute("siteVo", siteVo);
			System.out.println(m);
		}
	}
}
