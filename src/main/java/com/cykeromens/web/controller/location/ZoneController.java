/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.web.controller.location;

import com.cykeromens.model.location.Country;
import com.cykeromens.model.location.Zone;
import com.cykeromens.service.location.CountryService;
import com.cykeromens.service.location.ZoneService;
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
@RequestMapping("/countries/{id}")
@SessionAttributes("zone")
public class ZoneController {
    
    @Autowired
    ZoneService zoneService;
    @Autowired
    CountryService countryService;
    @Autowired
    MessageSource messageSource;
    
    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public String listZones(Model uiModel, Zone zone,@PathVariable("id") Long id,@RequestParam(value="zid", required = false) Long zid, SessionStatus sessionStatus){
        if(zid==null){
            Country country = (Country) countryService.findById(id).get();
            uiModel.addAttribute("zones",country.getZones());
            //sessionStatus.setComplete();
            return "zone/list";
        }else{
            zone = (Zone) zoneService.findById(zid).get();
            uiModel.addAttribute("zone",zone);
            return "zone/show";
        }
    }
   
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"}, method = RequestMethod.GET)
    public String zoneForm(@RequestParam(value="zid", required = false) Long zid,Model uiModel, Zone zone){
        if(zid==null){
            zone = new Zone();
            uiModel.addAttribute("zone",zone);
            return "zone/create";
        }else{
            zone = (Zone) zoneService.findById(zid).get();
            uiModel.addAttribute("zone",zone);
            return "zone/update";
        }
    }
    
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"},method = RequestMethod.POST)
    public String zoneEdit(Model uiModel,@Valid Zone zone,@PathVariable("id") Long id, @RequestParam(value="zid", required = false) Long zid, BindingResult bindingResult, 
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus sessionStatus){
        Country country=(Country)countryService.findById(id).get();
        if(zid==null){
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                    new Message("error", messageSource.getMessage("zone_creation_fail",new Object[]{}, locale)));
                return "zone/create";
            }
                zone.setCountry(country);
                zoneService.save(zone);
                redirectAttributes.addFlashAttribute("message", 
                    new Message("success", messageSource.getMessage("zone_creation_success",new Object[]{}, locale))
                );
                sessionStatus.setComplete();
                return "redirect:/countries/"+ UrlUtil.encodeUrlPathSegment(country.getId().toString(), httpServletRequest);
        }else{
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                    new Message("error", messageSource.getMessage("zone_update_fail",new Object[]{}, locale)));
                return "zone/update";
            }
            zone.setCountry(country);
            zoneService.save(zone);
            redirectAttributes.addFlashAttribute("message", 
               new Message("success", messageSource.getMessage("zone_update_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/countries/"+UrlUtil.encodeUrlPathSegment(country.getId().toString(), httpServletRequest);
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String delete(Country country, Zone zone, BindingResult result, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, 
            HttpServletRequest httpServletRequest,Locale locale) {
        zoneService.delete(zone);
        redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("zone_delete_success",new Object[]{}, locale))
            );
        sessionStatus.setComplete();
        return "redirect:/countries/"+UrlUtil.encodeUrlPathSegment(country.getId().toString(), httpServletRequest);
    }
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject constructJson(Model uiModel, @RequestParam(required = false, value="zid") Long zid) {
//         JSONObject responseObj = new JSONObject();
//        if(zid !=null){
//            Zone zone = (Zone)zoneService.findById(zid);
//            responseObj.put("data", zone);
//        }else{
//            List<Zone> zone = (List<Zone>) zoneService.findAll();
//            responseObj.put("data", zone);
//        }
//        return responseObj;
//    }
}
