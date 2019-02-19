package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping({"","/main"})//멀티매핑이 된다.
	public String main() {
		//return "/WEB-INF/views/main/index.jsp";
		return "/main/index";//spring servlet에서 jsp붙여주므로 빼도된다.
		//매핑된 주소로 검색하면 return값으로 들어간다.
	}
}
/* 						@ResponseBody
 *  modelandview
 *  string -> viewname
 *  objcet x
 */