package com.hussar.app.mol.contoller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hussar.app.common.CommonConstants;
import com.hussar.app.mol.domain.MassageBookingInfo;
import com.hussar.app.mol.model.MassageBookingEntity;
import com.hussar.app.mol.model.RoleType;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.app.mol.service.msj.MassageBookingService;
import com.hussar.framework.common.domain.NameValueBean;
import com.hussar.framework.exceptions.BusinessException;

/**
 * @MassageBookingController.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午8:51:39 2014-12-23
 * ©2014, some rights reserved
 */
@Controller
@RequestMapping("/massageBooking")
public class MassageBookingController {
	
	@Autowired
	private MassageBookingService massageBookingService; 
	
	@RequestMapping(value = "/current.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String getCurrentBookingList(HttpSession session, HttpServletRequest request, Model model, @RequestParam(required = false) String msg){
		UserEntity user = (UserEntity) session.getAttribute(CommonConstants.CURRENT_LONGIN_USER);
		List<MassageBookingInfo> bookingList = massageBookingService.getMassageBookInfo(user);
		model.addAttribute( "bookingList", bookingList);
		model.addAttribute( "myOrderNumber", getMyOrderNumber(bookingList, user));
		model.addAttribute( "orderDate", getOrderDate());
		if(msg != null){
			model.addAttribute( "msg", msg);
		}
		if(isAdmin(user)){
			model.addAttribute("orderDateList", getOrderDateList());
			model.addAttribute("orderDay", new NameValueBean());
		}
		return "msj/booking"; 
	}
	
	private String getOrderDate(){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd EEEE");
		return formatter.format(massageBookingService.getOrderDate());
	}
	
	private List<NameValueBean> getOrderDateList() {
		List<NameValueBean> list = new ArrayList<>();
		list.add(new NameValueBean());
		list.add(new NameValueBean("周一", Calendar.MONDAY));
		list.add(new NameValueBean("周二", Calendar.TUESDAY));
		list.add(new NameValueBean("周三", Calendar.WEDNESDAY));
		list.add(new NameValueBean("周四", Calendar.THURSDAY));
		list.add(new NameValueBean("周五", Calendar.FRIDAY));
		list.add(new NameValueBean("周六", Calendar.SATURDAY));
		list.add(new NameValueBean("周日", Calendar.SUNDAY));
		return list;
	}

	private boolean isAdmin(UserEntity user){
		if(user == null){
			return false;
		}
		if(RoleType.admin.equals(user.getRole()) || RoleType.super_admin.equals(user.getRole())){
			return true;
		}else{
			return false;
		}
	}
	
	private Integer getMyOrderNumber(List<MassageBookingInfo> bookingList, UserEntity user) {
		if(user == null){
			return 0;
		}
		for(MassageBookingInfo info : bookingList){
			if(info.getBookingEntity() != null && user.equals(info.getBookingEntity().getUserInfo())){
				return info.getBookingEntity().getOrderNumber();
			}
		}
		return null;
	}

	@RequestMapping(value = "/setOrder.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody  
	public String setOrder(HttpSession session, int orderNumber, boolean flag){
		UserEntity user = (UserEntity) session.getAttribute(CommonConstants.CURRENT_LONGIN_USER);
		if(user == null){
			return "no_login";
		}
		try {
			if(flag){
				MassageBookingEntity bookingEntity = new MassageBookingEntity(); 
				bookingEntity.setUserInfo(user);
				bookingEntity.setOrderNumber(orderNumber);
				massageBookingService.addBooking(bookingEntity);
			}else{
				massageBookingService.removeBooking(orderNumber);
			}
			
		} catch (BusinessException e) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping(value = "/setOrderDay.do", method = RequestMethod.POST)
	public RedirectView setOrderDay(HttpSession session, @Valid NameValueBean oderDay, RedirectAttributes attributes){
		Calendar calendar = Calendar.getInstance();
		int currrentDayNo = calendar.get(Calendar.DAY_OF_WEEK);
		if(currrentDayNo == 1){
			if(oderDay.getIntValue() > 1){
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}
		}
		calendar.set(Calendar.DAY_OF_WEEK, oderDay.getIntValue());
		if(oderDay.getIntValue() == 1){
			calendar.add(Calendar.DAY_OF_MONTH, 7);
		}
		massageBookingService.setOrderDate(calendar.getTime());
		attributes.addFlashAttribute("msg", "重置按摩时间成功！");
		return new RedirectView("/massageBooking/current.do", true, false, true);
	}
	
	
	@RequestMapping(value = "/cancelOrder.do", method = RequestMethod.POST)
	public String cancelOrder(@RequestParam int orderNumber){
		massageBookingService.removeBooking(orderNumber);
		return "redirect:/massageBooking/current.do";
	}
	
}
