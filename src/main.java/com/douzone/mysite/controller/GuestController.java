package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestController {
	@Autowired
	private GuestService guestservice;

	// model은 모델값만 보내줄때 modelandview는 둘다 전달할때 쓴다.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", guestservice.list());
		return "guestbook/list";
	}

	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(@ModelAttribute GuestbookVo guestbookVo) {
		guestservice.write(guestbookVo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="no", required=true) Long no) {
		return "guestbook/deleteform";
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestservice.delete(guestbookVo);
		return "redirect:/guestbook/list";
	}
}
