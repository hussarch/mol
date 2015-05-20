package com.hussar.app.mol.contoller.admin;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hussar.app.mol.common.DateEditor;
import com.hussar.app.mol.model.ManagerEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.app.mol.service.manager.ManagerService;
import com.hussar.app.mol.service.org.OrganizationService;
import com.hussar.app.mol.service.user.UserService;
import com.hussar.framework.common.domain.ListPageInfo;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @UserManagementController.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-4-12, ©2015 some rights reserved
 */
@Controller
@RequestMapping("/admin")
public class UserManagementController {
	
	private static final String PAGE_LIST_INFO = "page_list_info";
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value = "/userList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String getUserList(HttpSession session, Model model, @RequestParam(required = false) String condition, 
								@RequestParam(required = false) Integer page){
		int currentPage = page == null ? 1 : page;
		ListPageInfo listPageInfo = getListPageInfo(session);
		if(listPageInfo != null){
			condition = listPageInfo.getCondition();
			currentPage = listPageInfo.getPage();
		}else{
			listPageInfo = new ListPageInfo();
			listPageInfo.setCondition(condition);
			listPageInfo.setPage(currentPage);
		}
		List<UserEntity> list = userService.getUserEntityList(condition, currentPage, listPageInfo.getMaxCountOfPerPage());
		model.addAttribute( "userList", list);
		model.addAttribute( "condition", condition);
		model.addAttribute( "listPageInfo", listPageInfo);
		PagingCountBean pagingCountBean = userService.getPagingCountBean(condition, currentPage, listPageInfo.getMaxCountOfPerPage());
		model.addAttribute( "pageInfo", pagingCountBean);
		return "admin/userList";
	}
	
	@RequestMapping(value = "/operation.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String viewUserInfo(HttpSession session, Model model, @ModelAttribute("listPageInfo")ListPageInfo listPageInfo){
		UserEntity userInfo = this.userService.getUserEntity(listPageInfo.getId());
		if(listPageInfo != null){
			session.setAttribute(PAGE_LIST_INFO, listPageInfo);
		}
		String jsp;
		model.addAttribute( "userInfo", userInfo);
		if("update".equals(listPageInfo.getFlag())){
			model.addAttribute( "organizationList", organizationService.getOrganizationList());
			jsp = "admin/userUpdate";
		}else if("position".equals(listPageInfo.getFlag())){
			ManagerEntity entity = managerService.getEntity(userInfo);
			if(entity == null){
				entity = new ManagerEntity();
				entity.setUser(userInfo);
				model.addAttribute( "managerList", managerService.getManagerList());
				jsp = "admin/managerSet";
			}else{
				jsp = "redirect:/admin/managerList.do";
			}
			model.addAttribute( "manager", entity);
		}else{
			jsp = "admin/userDetail";
		}
		return jsp;
	}
	
	
	@RequestMapping(value = "/deleteUser.do", method = RequestMethod.POST)
	public RedirectView deleteUserInfo(HttpSession session, @RequestParam(required = true) Integer id, 
				@ModelAttribute("listPageInfo")ListPageInfo listPageInfo, RedirectAttributes attributes){
		if(listPageInfo != null){
			session.setAttribute(PAGE_LIST_INFO, listPageInfo);
		}
		userService.deleteUser(id);
		attributes.addFlashAttribute("msg", "员工删除成功！");
		return new RedirectView("/admin/userList.do", true, false, true);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor());  
	}  
	
	@RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
	public RedirectView updateUserInfo(@ModelAttribute("userInfo") UserEntity userInfo, RedirectAttributes attributes){
		if(userInfo.getOrganization() != null && userInfo.getOrganization().getId() == null){
			userInfo.setOrganization(null);
		}
		userService.updateUser(userInfo);
		attributes.addFlashAttribute("msg", "员工修改成功！");
		return new RedirectView("/admin/userList.do", true, false, true);
	}
	
	private ListPageInfo getListPageInfo(HttpSession session){
		Object info = session.getAttribute(PAGE_LIST_INFO);
		if(info != null){
			session.removeAttribute(PAGE_LIST_INFO);
			return (ListPageInfo)info;
		}else{
			return null;
		}
	}
	
	@RequestMapping(value = "/setPosition.do", method = RequestMethod.POST)
	public RedirectView setPosition(@ModelAttribute("manager") ManagerEntity manager, RedirectAttributes attributes){
		if(manager.getSuperManager() != null && manager.getSuperManager().getId() == null){
			manager.setSuperManager(null);
		}
		managerService.add(manager);
		attributes.addFlashAttribute("msg", "设置职务成功！");
		return new RedirectView("/admin/userList.do", true, false, true);
	}
	
	@RequestMapping(value = "/deletePosition.do", method = RequestMethod.POST)
	public RedirectView setPosition(@ModelAttribute("id") Integer id, RedirectAttributes attributes){
		managerService.delete(id);
		attributes.addFlashAttribute("msg", "删除职务成功！");
		return new RedirectView("/admin/userList.do", true, false, true);
	}
	
	

}
