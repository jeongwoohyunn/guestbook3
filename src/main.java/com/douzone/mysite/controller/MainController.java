package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.SiteService;


@Controller
public class MainController {

	@Autowired
	private SiteService siteService;

	@RequestMapping( { "/", "/main" } )
	public String index( Model model ) {
		//SiteVo siteVo = siteService.getSite();
		//model.addAttribute("site", siteVo);
		return "main/index";
	}

	@ResponseBody
	@RequestMapping( "/hello" )
	public String hello() {
		return "<h1>안녕하세요.</h1>";
	}
}