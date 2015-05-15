package com.hussar.app.mol.contoller.admin;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hussar.app.mol.model.OrganizationEntity;
import com.hussar.app.mol.service.manager.ManagerService;
import com.hussar.app.mol.service.org.OrganizationService;
import com.hussar.framework.common.domain.ListPageInfo;
import com.hussar.framework.common.domain.PagingCountBean;

@Controller
@RequestMapping("/admin")
public class OrganizationController {
	
	private static final String PAGE_LIST_INFO = "orgs_page_list_info";
	
	@Autowired
	private OrganizationService organizationService; 
	@Autowired
	private ManagerService managerService;
	
	
	@RequestMapping(value = "/orgList.do", method = {RequestMethod.POST, RequestMethod.GET})
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
		PagingCountBean pagingCountBean = organizationService.getPagingCountBean(currentPage, listPageInfo.getMaxCountOfPerPage());
		model.addAttribute( "pageInfo", pagingCountBean);
		model.addAttribute("orgList", organizationService.getOrganizationEntityList(currentPage, listPageInfo.getMaxCountOfPerPage()));
		return "admin/orgList";
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
	
	@RequestMapping(value = "/orgOperation.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String viewOrgInfo(Model model, @RequestParam(required = false) Integer id, @RequestParam(required = false) String operationType){
		model.addAttribute( "orgInfo", getOrganizationEntity(id));
		if("view".equals(operationType)){
			return "admin/orgDetail";
		}
		model.addAttribute( "managerList", managerService.getManagerList());
		model.addAttribute( "orgList", organizationService.getOrganizationList());
		if("update".equals(operationType)){
			return "admin/orgUpdate";
		}else{
			return "admin/orgCreate";
		}
	}
	
	private OrganizationEntity getOrganizationEntity(Integer id){
		if(id == null){
			return new OrganizationEntity();
		}else{
			return this.organizationService.getEntity(id);
		}
	}
	
	@RequestMapping(value = "/createOrg.do", method = RequestMethod.POST)
	public RedirectView createOrgInfo(@ModelAttribute("orgInfo") OrganizationEntity organization, RedirectAttributes attributes){
		disposeEmptySuperOrg(organization);
		organizationService.add(organization);
		attributes.addFlashAttribute("msg", "添加成功！");
		return new RedirectView("/admin/orgList.do", true, false, true);
	}

	private void disposeEmptySuperOrg(OrganizationEntity organization) {
	    if(organization.getSuperOrganization() != null && organization.getSuperOrganization().getId() == null){
			organization.setSuperOrganization(null);
		}
    }
	
	@RequestMapping(value = "/deleteOrg.do", method = RequestMethod.POST)
	public RedirectView deleteOrgInfo(@RequestParam(required = true) Integer id, RedirectAttributes attributes){
		organizationService.delete(id);
		attributes.addFlashAttribute("msg", "删除成功！");
		return new RedirectView("/admin/orgList.do", true, false, true);
	}
	
	@RequestMapping(value = "/updateOrg.do", method = RequestMethod.POST)
	public RedirectView updateOrgInfo(@ModelAttribute("orgInfo") OrganizationEntity organization, RedirectAttributes attributes){
		disposeEmptySuperOrg(organization);
		organizationService.update(organization);
		attributes.addFlashAttribute("msg", "修改成功！");
		return new RedirectView("/admin/orgList.do", true, false, true);
	}
	
	@RequestMapping(value = "/orgValidate.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody  
	public String isExist(String fieldName, String value, Integer id){
		if(StringUtils.isEmpty(value)){
			return "true";
		}
		if(id < 0){
			return String.valueOf(this.organizationService.checkExist(fieldName, value));
		}else{
			return String.valueOf(this.organizationService.checkExist(fieldName, value, id));
		}
	}
	
}
