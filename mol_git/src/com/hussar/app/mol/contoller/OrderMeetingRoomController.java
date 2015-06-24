package com.hussar.app.mol.contoller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	    List<MeetingRoomEntity> roomList = meetingRoomService.getMeetingRoomList();
		if(roomList.size() > 0){
			model.addAttribute("orderList", getMeetingRoomOrderInfoList(roomList));
			model.addAttribute("orderedRoomArray", getOrdered(roomList));
			ScheduledMeetingEntity scheduledMeeting = new ScheduledMeetingEntity();
			scheduledMeeting.setOrderDate(new Date());
			model.addAttribute("scheduledMeeting", scheduledMeeting);
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
		info.setName(entity.getZhName() + "(" + entity.getLocation() + ")");
		info.setTds(getTds(entity));
		return info;
	}

	private String getTds(MeetingRoomEntity roomEntity) {
		List<ScheduledMeetingEntity> scheduledMeetingList = roomEntity.getScheduledMeetingList();
		StringBuilder tds = new StringBuilder();
		int j = 0;
		for(int i = 0; i < 18; i++){
			if(j < scheduledMeetingList.size()){
				ScheduledMeetingEntity entity = scheduledMeetingList.get(j);
				int startIndex = entity.getStartTimeIndex();
				if(i == startIndex){
					int colspanSize = entity.getDurationUnitNumber();
					tds.append("<td class=\"selected\" ");
					if(colspanSize > 1){
						tds.append("colspan=\"").append(colspanSize).append("\">");
					}
					tds.append(getInnerHtml(roomEntity.getId(), i, colspanSize));
					tds.append("</td>");
					j++;
					continue;
				}
			}
			tds.append("<td>");
			tds.append(getInnerHtml(roomEntity.getId(), i, 1));
			tds.append("</td>");
		}
		return tds.toString();
	}
	
	private String getInnerHtml(int roomId, int index, int value){
		StringBuilder input = new StringBuilder();
		input.append("<input type=\"hidden\" id=\"td_").append(roomId).append("-").append(index).append("\" value=\"({'roomId':")
		   .append(roomId).append(",'timeIndex':").append(index).append(",'duration':").append(value).append("})\">");
		return input.toString();
	}
	
	private String getOrdered(List<MeetingRoomEntity> meetingRoomList){
	    StringBuilder builder = new StringBuilder();
	    builder.append("{");
	    for(MeetingRoomEntity room : meetingRoomList){
	        builder.append(room.getId()).append(":[").append(getOrdered(room)).append("],");
	    }
	    builder.append("}");
	    return builder.toString();
	}

    private String getOrdered(MeetingRoomEntity room) {
        StringBuilder builder = new StringBuilder();
        List<ScheduledMeetingEntity> list = room.getScheduledMeetingList();
        if(list != null){
            for(ScheduledMeetingEntity item : list){
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append(item.getStartTimeIndex());
                int i = 1;
                while(i < item.getDurationUnitNumber()){
                    builder.append(",");
                    builder.append(item.getStartTimeIndex() + i);
                    i++;
                }
            }
        }
        return builder.toString();
    }
	
    @RequestMapping(value = "/order.do", method = {RequestMethod.POST})
	public String startOrder(Model model, @ModelAttribute("scheduledMeeting")ScheduledMeetingEntity scheduledMeeting,  @RequestParam(required = true)Integer roomId){
    	scheduledMeeting.setMeetingRoom(meetingRoomService.getEntity(roomId));
    	model.addAttribute("scheduledMeeting", scheduledMeeting);
    	return "mr/selectMembers";
    }
    
    
	
}
