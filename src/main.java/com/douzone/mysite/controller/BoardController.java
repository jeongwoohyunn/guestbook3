package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", boardservice.list());
		return "/board/list";
	}
}
