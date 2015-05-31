/**
 * 
 */
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

import com.hussar.app.mol.contoller.CommonController;
import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.app.mol.service.mr.MeetingRoomService;
import com.hussar.app.mol.service.sm.ScheduledMeetingService;
import com.hussar.framework.common.domain.ListPageInfo;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @author yi.xiao
 *
 */
@Controller
@RequestMapping("/admin/mr")
public class MeetingRoomController extends CommonController{
    
    @Autowired
    private MeetingRoomService meetingRoomService;
    
    @Autowired
    private ScheduledMeetingService scheduledMeetingService;
    
    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String getList(Model model, HttpSession session, @RequestParam(required = false) Integer page){
    	ListPageInfo listPageInfo = getListPageInfo(page, session);
    	model.addAttribute( "listPageInfo", listPageInfo);
		model.addAttribute("list", meetingRoomService.getMeetingRoomList(listPageInfo.getPage(), listPageInfo.getMaxCountOfPerPage()));
		PagingCountBean pagingCountBean = meetingRoomService.getPagingCountBean(listPageInfo.getPage(), listPageInfo.getMaxCountOfPerPage());
		model.addAttribute( "pageInfo", pagingCountBean);
		return "admin/mr/meetingRoomList";
    }
    
    @RequestMapping(value = "/into.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String intoPage( HttpSession session, Model model, @RequestParam(required = false) Integer id, 
    		@RequestParam(required = false) String type, @ModelAttribute("listPageInfo")ListPageInfo listPageInfo){
    	MeetingRoomEntity entity = getEntity(id);
    	String view;
    	if(listPageInfo != null){
			session.setAttribute(getListPageInfoTag(), listPageInfo);
		}
    	if("view".equals(type)){
    		view = "admin/mr/meetingRoomDetail";
    	}else if("update".equals(type)){
    		view = "admin/mr/meetingRoomUpdate";
    	}else{
    		view = "admin/mr/meetingRoomCreate";
    	}
    	model.addAttribute("entity", entity);
    	return view;
    }

	private MeetingRoomEntity getEntity(Integer id) {
		if(id == null){
			return new MeetingRoomEntity();
		}else{
			return meetingRoomService.getEntity(id);
		}
	}
	
	
	@RequestMapping(value = "/create.do", method = RequestMethod.POST)
	public RedirectView create(@ModelAttribute("entity") MeetingRoomEntity meetingRoomEntity, RedirectAttributes attributes){
		meetingRoomService.add(meetingRoomEntity);
		attributes.addFlashAttribute("msg", "添加成功！");
		return new RedirectView("/admin/mr/list.do", true, false, true);
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public RedirectView update(@ModelAttribute("entity") MeetingRoomEntity meetingRoomEntity, RedirectAttributes attributes){
		meetingRoomService.update(meetingRoomEntity);
		attributes.addFlashAttribute("msg", "修改成功！");
		return new RedirectView("/admin/mr/list.do", true, false, true);
	}
	
	@RequestMapping(value = "/validate.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody  
	public String isExist(String fieldName, String value, Integer id){
		if(StringUtils.isEmpty(value)){
			return "true";
		}
		if(id < 0){
			return String.valueOf(this.meetingRoomService.checkExist(fieldName, value));
		}else{
			return String.valueOf(this.meetingRoomService.checkExist(fieldName, value, id));
		}
	}
    
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public RedirectView delete(@ModelAttribute("id") Integer id, RedirectAttributes attributes){
		meetingRoomService.delete(id);
		attributes.addFlashAttribute("msg", "删除成功！");
		return new RedirectView("/admin/mr/list.do", true, false, true);
	}
	
    
}
