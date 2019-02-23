package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

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
	//get받은걸로 비교 세션이 있으면 = 로그인이 됬을때 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpSession session,@ModelAttribute BoardVo boardVo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(null == authUser) {
			return "redirect:/";
		}
		boardservice.delete(boardVo);
		return "redirect:/board/list";	
	}
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public String insert(HttpSession session,@ModelAttribute BoardVo BoardVo) {
		
		boardservice.insert(BoardVo);
		return "redirect:/guestbook/list";
	}
}