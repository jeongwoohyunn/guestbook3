package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")//매핑앞에 넣어준다
public class UserController {
	
	@Autowired//
	private UserService userService;

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "/user/join";
	}//get은 바로 받아오는것
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}//post는 직접 쳐줘야된다.
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@RequestMapping("/modify")
	public String modify() {
		return "/user/modify";
	}
}