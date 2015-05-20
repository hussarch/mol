package com.hussar.app.mol.contoller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hussar.app.common.CommonConstants;
import com.hussar.app.mol.common.DateEditor;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.app.mol.service.org.OrganizationService;
import com.hussar.app.mol.service.user.UserService;
import com.hussar.framework.exceptions.InvalidParamException;

/**
 * @WelcomeController.java
 * @author XiaoYi(hussarch@126.com) Created on 下午10:05:58 2014-8-14 ©2014, some
 *         rights reserved
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value = "/intoRegist.do", method = RequestMethod.GET)
	public String startRegist(Model model){
		model.addAttribute( "userInfo", new UserEntity());
		model.addAttribute( "organizationList", organizationService.getOrganizationList());
		return "user/register";  
	}
	
	@RequestMapping(value = "/intoMenu.do", method = RequestMethod.GET)
	public String intoMene(){
		return "common/includeMenu";  
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor());  
	}  
	
	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public String regist(@ModelAttribute("userInfo") UserEntity userInfo, Model model) throws Exception {
		if(userInfo.getOrganization() != null && userInfo.getOrganization().getId() == null){
			userInfo.setOrganization(null);
		}
		String msg;
		String url;
		try{
			userService.addUser(userInfo);
			msg = "注册成功";
			url = "user/login";
		}catch(InvalidParamException e){
			msg = e.getMessage();
			model.addAttribute( "userInfo", userInfo);
			url = "user/register";
		}
		model.addAttribute("msg", msg);
		return url;
	}
	
	@RequestMapping(value = "/validate.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody  
	public String isExist(String fieldName, String type, String value, Integer id){
		if(id < 0){
			id = null;
		}
		return String.valueOf(this.userService.checkExist(fieldName, getClassTyep(type), value, id));
	}
	
	
	private Class<?> getClassTyep(String type){
		if("int".equals(type)){
			return Integer.class;
		}else{
			return String.class;
		}
	}
	
	@RequestMapping(value = "/intoLogin.do", method = RequestMethod.GET)
	public String startLogin(){
		return "user/login";  
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam String name, @RequestParam String password, Model model, HttpSession session) throws Exception {
		UserEntity user = userService.getUserEntity(name);
		if(user != null){
			if(user.getPassword().equals(password)){
				session.setAttribute(CommonConstants.CURRENT_LONGIN_USER, user);
				return "redirect:/massageBooking/current.do";  
			}else{
				model.addAttribute("msg", "密码错误");
			}
		}else{
			model.addAttribute("msg", "该用户不存在，请重新输入");
		}
		return "user/login";  
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(CommonConstants.CURRENT_LONGIN_USER);
		return "user/login";  
	}
}
