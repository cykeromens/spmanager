/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.web.controller.location;

import com.cykeromens.model.location.Area;
import com.cykeromens.model.location.State;
import com.cykeromens.service.location.AreaService;
import com.cykeromens.service.location.StateService;
import com.cykeromens.web.util.Message;
import com.cykeromens.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 *
 * @author cykeromens
 */
@Controller
@RequestMapping("/countries/{id}/{zid}/{sid}")
@SessionAttributes("area")
public class AreaController{
    
    AreaService areaService;
    StateService stateService;
    MessageSource messageSource;

    @Autowired
    public AreaController(AreaService areaService, StateService stateService, MessageSource messageSource) {
        this.areaService = areaService;
        this.stateService = stateService;
        this.messageSource = messageSource;
    }

    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public String listArea(Model uiModel, Area area,@PathVariable("sid") Long sid,@RequestParam(value="aid", required = false) Long aid){
        if(aid==null){
            State state =(State) stateService.findById(sid).get();
            uiModel.addAttribute("areas",state.getAreas());
            return "area/list";
        }else{
        area = (Area) areaService.findById(aid).get();
        uiModel.addAttribute("area",area);
        return "area/show";
        }
    }
   
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"}, method = RequestMethod.GET)
    public String areaForm(@RequestParam(value="id", required = false) Long id,Model uiModel, Area area, SessionStatus sessionStatus){
        //sessionStatus.setComplete();
        if(id==null){
            area = new Area();
            uiModel.addAttribute("area",area);
            return "area/create";
        }else{
            area = (Area) areaService.findById(id).get();
            uiModel.addAttribute("area",area);
            return "area/update";
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"},method = RequestMethod.POST)
    public String AreaEdit(Model uiModel,@PathVariable("id")Long id,@PathVariable("zid")Long zid,@Valid Area area, @PathVariable("sid") Long sid,@RequestParam(value="aid", required = false) Long aid, BindingResult bindingResult, 
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus sessionStatus){
        State state = (State) stateService.findById(sid).get();
        if(aid==null){
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                        new Message("error", messageSource.getMessage("area_creation_fail",new Object[]{}, locale)));
                return "area/create";
            }

                area.setState(state);
                areaService.save(area);
                redirectAttributes.addFlashAttribute("message", 
                            new Message("success", messageSource.getMessage("area_creation_success",new Object[]{}, locale))
                );
                sessionStatus.setComplete();
                return "redirect:/countries/"+state.getZone().getCountry().getId().toString()+"/"+state.getZone().getId().toString()+"/"+state.getId().toString();
        }else{
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                        new Message("error", messageSource.getMessage("area_update_fail",new Object[]{}, locale)));
                return "area/update";
            }

            area.setState(state);
            areaService.save(area);
            redirectAttributes.addFlashAttribute("message", 
                        new Message("success", messageSource.getMessage("area_update_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/countries/"+state.getZone().getCountry().getId().toString()+"/"+state.getZone().getId().toString()+"/"+state.getId().toString();
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String delete(Area area,@PathVariable("sid") Long sid, BindingResult result, SessionStatus sessionStatus,RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest httpServletRequest) {
        State state = (State)stateService.findById(sid).get();
        areaService.delete(area);
        redirectAttributes.addFlashAttribute("message", 
            new Message("success", messageSource.getMessage("area_update_success",new Object[]{}, locale))
            );
        sessionStatus.setComplete();
        return "redirect:/countries/"+ UrlUtil.encodeUrlPathSegment(state.getZone().getCountry().getId().toString() + "/" + state.getZone().getId().toString(), httpServletRequest);
    }
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject constructJson(Model uiModel, @RequestParam(required = false, value="aid") Long aid) {
//         JSONObject responseObj = new JSONObject();
//        if(aid !=null){
//            area area = (area)areaService.findById(aid);
//            responseObj.put("data", area);
//        }else{
//            List<area> areas = (List<area>) areaService.findAll();
//            responseObj.put("data", areas);
//        }
//        return responseObj;
//    }
//
//
    
}
