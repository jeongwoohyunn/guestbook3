package com.douzone.mysite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user") // 매핑앞에 넣어준다
public class UserController {

	@Autowired //
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/user/join";
	}// get은 바로 받아오는것 직접 도메인에 입력값을 넣어줘야된다.

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}// post는 패킷안에 숨겨져서 전송한다. 숨길때는 post

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/user/login";
	}

	// modelandview언제쓰는지
	// 위에서 login get값을 받은것, 여기서는 세션과, request, userVo를 넘긴다.
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute UserVo userVo, HttpSession session, HttpServletRequest request) {
		// 필요한 이메일과 패스워드만 가져와서 밑에서 비교한다.
		UserVo authuser = userService.login(userVo.getEmail(), userVo.getPassword());
		if (authuser == null) {
			/* 인증실패 */
			request.setAttribute("result", "fail");
			return "user/login";
		}
		session = request.getSession(true);
		session.setAttribute("authuser", authuser);
		return "redirect:/";
	}

	//
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		UserVo authuser = (UserVo) session.getAttribute("authuser");
		if (session != null && authuser != null) {
			session.removeAttribute("authuser");
			session.invalidate();
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(HttpSession session, Model model) {
		UserVo authuser = (UserVo) session.getAttribute("authuser");
		if (authuser == null) {
			return "redirect:/";
		}
		model.addAttribute("vo", userService.modifyselect(authuser));
		return "user/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpSession session, @ModelAttribute UserVo userVo) {
		session.setAttribute("authuser", userService.modify(userVo));
		return "redirect:/";
	}
}