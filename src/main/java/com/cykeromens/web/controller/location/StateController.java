/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.web.controller.location;

import com.cykeromens.model.location.State;
import com.cykeromens.model.location.Zone;
import com.cykeromens.service.location.StateService;
import com.cykeromens.service.location.ZoneService;
import com.cykeromens.web.util.Message;
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
@RequestMapping("/countries/{id}/{zid}")
@SessionAttributes("state")
public class StateController {
    
    StateService stateService;
    ZoneService zoneService;
    MessageSource messageSource;



    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public String listState(Model uiModel, State state,@PathVariable("zid")Long zid,@RequestParam(value="sid", required = false) Long sid){
        if(sid==null){
            Zone zone = (Zone)zoneService.findById(zid).get();
            uiModel.addAttribute("states",zone.getStates());
            return "state/list";
        }else{
        state = (State) stateService.findById(sid).get();
        uiModel.addAttribute("state",state);
        return "state/show";
        }
    }
   
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"}, method = RequestMethod.GET)
    public String stateForm(@RequestParam(value="sid", required = false) Long sid,Model uiModel, State state, SessionStatus sessionStatus){
        //sessionStatus.setComplete();
        if(sid==null){
            state = new State();
            uiModel.addAttribute("state",state);
            return "state/create";
        }else{
            state = (State) stateService.findById(sid).get();
            uiModel.addAttribute("state",state);
            return "state/update";
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"},method = RequestMethod.POST)
    public String stateEdit(Model uiModel,@Valid State state, @PathVariable("zid")Long zid,@RequestParam(value="id", required = false) Long id, BindingResult bindingResult, 
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus sessionStatus){
        Zone z= (Zone) zoneService.findById(zid).get();
        if(id==null){
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                        new Message("error", messageSource.getMessage("state_creation_fail",new Object[]{}, locale)));
                return "state/create";
            }
                state.setZone(z);
                stateService.save(state);
                redirectAttributes.addFlashAttribute("message", 
                    new Message("success", messageSource.getMessage("state_creation_success",new Object[]{}, locale))
                );
                sessionStatus.setComplete();
                return "redirect:/countries/"+z.getCountry().getId().toString()+"/"+z.getId().toString();
        }else{
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                        new Message("error", messageSource.getMessage("state_update_fail",new Object[]{}, locale)));
                return "state/update";
            }
             state.setZone(z);
             stateService.save(state);
            redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("state_update_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/countries/"+z.getCountry().getId().toString()+"/"+z.getId().toString();
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String delete(Zone zone,State state, BindingResult result, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, Locale locale) {
        stateService.delete(state);
        redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("state_delete_success",new Object[]{}, locale))
            );
        sessionStatus.setComplete();
        return "redirect:/countries/"+zone.getCountry().getId().toString();
    }
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject constructJson(Model uiModel, @RequestParam(required = false, value="sid") Long id) {
//         JSONObject responseObj = new JSONObject();
//        if(id !=null){
//            State state = (State)stateService.findById(id);
//            responseObj.put("data", state);
//        }else{
//            List<State> states = (List<State>) stateService.findAll();
//            responseObj.put("data", states);
//        }
//        return responseObj;
//    }

}
