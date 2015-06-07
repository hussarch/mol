package com.hussar.app.mol.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hussar.app.mol.domain.MeetingRoomOrderInfo;
import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.app.mol.model.ScheduledMeetingEntity;
import com.hussar.app.mol.service.mr.MeetingRoomService;

/**
 * @OrderMeetingRoomController.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-31, ©2015 some rights reserved
 */
@Controller
@RequestMapping("/order/mr")
public class OrderMeetingRoomController {
	
	@Autowired
	private MeetingRoomService meetingRoomService;
	
	@RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String getOrderList(Model model, @RequestParam(required = false) String msg){
		List<MeetingRoomOrderInfo> list = getMeetingRoomOrderInfoList(meetingRoomService.getMeetingRoomList());
		if(list.size() > 0){
			model.addAttribute("orderList", list);
		}else{
			model.addAttribute("msg", "请先添加会议室");
		}
		return "mr/orderMeetingRoom";
	}

	private List<MeetingRoomOrderInfo> getMeetingRoomOrderInfoList(List<MeetingRoomEntity> meetingRoomList) {
		List<MeetingRoomOrderInfo> list = new ArrayList<MeetingRoomOrderInfo>();
		for(MeetingRoomEntity entity : meetingRoomList){
			list.add(getMeetingRoomOrderInfo(entity));
		}
		return list;
	}

	private MeetingRoomOrderInfo getMeetingRoomOrderInfo(MeetingRoomEntity entity) {
		MeetingRoomOrderInfo info = new MeetingRoomOrderInfo();
		info.setId(entity.getId());
		info.setName(entity.getZhName() + "/n" + entity.getLocation());
		info.setTds(getTds(entity.getScheduledMeetingList()));
		return info;
	}

	private String getTds(List<ScheduledMeetingEntity> scheduledMeetingList) {
		StringBuilder tds = new StringBuilder();
		int j = 0;
		for(int i = 0; i < 19; i++){
			if(j < scheduledMeetingList.size()){
				ScheduledMeetingEntity entity = scheduledMeetingList.get(j);
				int startIndex = entity.getStartTimeIndex();
				if(i == startIndex){
					int colspanSize = entity.getDurationUnitNumber();
					tds.append("<td class=\"selected\" ");
					if(colspanSize > 1){
						tds.append("colspan=\"").append(colspanSize).append("\">");
					}
					tds.append(getInnerHtml(i, colspanSize));
					tds.append("</td>");
					j++;
					continue;
				}
			}
			tds.append("<td>");
			tds.append(getInnerHtml(i, 1));
			tds.append("</td>");
		}
		return tds.toString();
	}
	
	private String getInnerHtml(int index, int value){
		StringBuilder input = new StringBuilder();
		input.append("<input type=\"hidden\" id=\"td_").append(index).append("\" value=\"")
		   .append(index).append("_").append(value).append("\">");
		return input.toString();
	}
	
	
}
