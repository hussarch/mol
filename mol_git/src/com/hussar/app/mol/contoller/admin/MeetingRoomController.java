/**
 * 
 */
package com.hussar.app.mol.contoller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hussar.app.mol.service.mr.MeetingRoomService;
import com.hussar.app.mol.service.sm.ScheduledMeetingService;

/**
 * @author yi.xiao
 *
 */
@Controller
@RequestMapping("/admin/mr")
public class MeetingRoomController {
    
    @Autowired
    private MeetingRoomService meetingRoomService;
    
    @Autowired
    private ScheduledMeetingService scheduledMeetingService;
    
    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public void getList(Model model, HttpSession session, @RequestParam(required = false) Integer page){
        
        
        
    }
    
}
