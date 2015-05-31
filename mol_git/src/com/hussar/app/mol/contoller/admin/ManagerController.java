package com.hussar.app.mol.contoller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hussar.app.mol.model.ManagerEntity;
import com.hussar.app.mol.service.manager.ManagerService;
import com.hussar.app.mol.service.org.OrganizationService;
import com.hussar.framework.common.domain.ListPageInfo;
import com.hussar.framework.common.domain.PagingCountBean;

@Controller
@RequestMapping("/admin")
public class ManagerController {
	
	private static final String PAGE_LIST_INFO = "manager_page_list_info";
	
	@Autowired
	private OrganizationService organizationService; 
	@Autowired
	private ManagerService managerService;
	
	
	@RequestMapping(value = "/managerList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String getOrganizationList(Model model, HttpSession session, @RequestParam(required = false) Integer page){
		int currentPage = page == null ? 1 : page;
		ListPageInfo listPageInfo = getListPageInfo(session);
		if(listPageInfo != null){
			currentPage = listPageInfo.getPage();
		}else{
			listPageInfo = new ListPageInfo();
			listPageInfo.setPage(currentPage);
		}
		model.addAttribute( "listPageInfo", listPageInfo);
		model.addAttribute("magagerList", managerService.getManagerEntityList(currentPage, listPageInfo.getMaxCountOfPerPage()));
		PagingCountBean pagingCountBean = managerService.getPagingCountBean(currentPage, listPageInfo.getMaxCountOfPerPage());
		model.addAttribute( "pageInfo", pagingCountBean);
		return "admin/managerList";
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
	
	@RequestMapping(value = "/managerOperation.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String viewOrgInfo(Model model, @RequestParam(required = false) Integer id, @RequestParam(required = false) String operationType){
		model.addAttribute( "manager", getOrganizationEntity(id));
//		if(listPageInfo != null){
//			session.setAttribute(PAGE_LIST_INFO, listPageInfo);
//		}
		if("view".equals(operationType)){
			return "admin/managerDetail";
		}else{
			model.addAttribute( "managerList", managerService.getManagerList());
			return "admin/managerUpdate";
		}
	}
	
	private ManagerEntity getOrganizationEntity(Integer id){
		if(id == null){
			return new ManagerEntity();
		}else{
			return this.managerService.getEntity(id);
		}
	}
	
	@RequestMapping(value = "/updateManager.do", method = RequestMethod.POST)
	public RedirectView setPosition(@ModelAttribute("manager") ManagerEntity manager, RedirectAttributes attributes){
		if(manager.getSuperManager() != null && manager.getSuperManager().getId() == null){
			manager.setSuperManager(null);
		}
		managerService.update(manager);
		attributes.addFlashAttribute("msg", "设置职务成功！");
		return new RedirectView("/admin/managerList.do", true, false, true);
	}
	
	@RequestMapping(value = "/deleteManager.do", method = RequestMethod.POST)
	public RedirectView setPosition(@ModelAttribute("id") Integer id, RedirectAttributes attributes){
		managerService.delete(id);
		attributes.addFlashAttribute("msg", "删除职务成功！");
		return new RedirectView("/admin/managerList.do", true, false, true);
	}
	
}
