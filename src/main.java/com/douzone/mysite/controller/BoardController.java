package com.douzone.mysite.controller;

import java.util.Map;

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
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardservice;

	@RequestMapping( "" )
	public String list(
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ) {
		
		Map<String, Object> map = boardservice.getMessageList( page, keyword );
		model.addAttribute( "map", map );
		
		return "board/list";
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

}