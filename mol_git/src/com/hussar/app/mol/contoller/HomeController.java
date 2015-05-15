package com.hussar.app.mol.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hussar.app.mol.service.user.UserService;

/**
 * @HomeController.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:34:07 2014-8-25
 * ©2014, some rights reserved
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userService; 
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String getIndex(@RequestParam String name, Model model){
		model.addAttribute( "userName", name);
		return "home";
	}

}
