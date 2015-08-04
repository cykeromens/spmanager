/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.web.controller.location;

import com.cykeromens.model.location.Area;
import com.cykeromens.model.location.City;
import com.cykeromens.service.location.AreaService;
import com.cykeromens.service.location.CityService;
import com.cykeromens.web.util.Message;
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
@RequestMapping("/countries/{id}/{zid}/{sid}/{aid}")
@SessionAttributes("area")
public class CityController {
    
    CityService cityService;
    AreaService areaService;
    MessageSource messageSource;

    @Autowired
    public CityController(CityService cityService, AreaService areaService, MessageSource messageSource) {
        this.cityService = cityService;
        this.areaService = areaService;
        this.messageSource = messageSource;
    }

    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public String listCities(Model uiModel, City city,@PathVariable("aid")Long aid,@RequestParam(value="cid", required = false) Long cid){
        Area area = (Area)areaService.findById(aid).get();
        if(cid==null){
            //uiModel.addAttribute("area", area);
            uiModel.addAttribute("cities",area.getCities());
            return "city/list";
        }else{
            uiModel.addAttribute("area", area);
            city = (City) cityService.findById(cid).get();
            uiModel.addAttribute("city",city);
            return "city/show";
        }
    }
   
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"}, method = RequestMethod.GET)
    public String cityForm(@PathVariable("aid")Long aid,@RequestParam(value="id", required = false) Long id,Model uiModel, City city){
        Area area = (Area)areaService.findById(aid).get();
        if(id==null){
            city = new City();
            uiModel.addAttribute("area", area);
            uiModel.addAttribute("city",city);
            return "city/create";
        }else{
            uiModel.addAttribute("area", area);
            city = (City) cityService.findById(id).get();
            uiModel.addAttribute("city",city);
            return "city/update";
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"},method = RequestMethod.POST)
    public String cityEdit(Model uiModel,@Valid City city, @PathVariable("aid")Long aid, @RequestParam(value="id", required = false) Long id, BindingResult bindingResult, 
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus sessionStatus){
        Area area = (Area)areaService.findById(aid).get();
        if(area ==null){
            return "city/create";
        }
        if(id==null){
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                new Message("error", messageSource.getMessage("city_creation_fail",new Object[]{}, locale)));
                return "city/create";
            }
            city.setArea(area);
            cityService.save(city);
            redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("city_creation_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/countries?/"+area.getState().getZone().getCountry().getId().toString()+"/"+area.getState().getZone().getId().toString()+"/"+area.getState().getId().toString()+"/"+area.getId().toString();
        }else{
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                        new Message("error", messageSource.getMessage("city_update_fail",new Object[]{}, locale)));
                return "city/update";
            }
            city.setArea(area);
            cityService.save(city);
            redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("city_update_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/countries?/"+area.getState().getZone().getCountry().getId().toString()+"/"+area.getState().getZone().getId().toString()+"/"+area.getState().getId().toString()+"/"+area.getId().toString();
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String delete(City city, BindingResult result, SessionStatus sessionStatus, RedirectAttributes redirectAttributes,Locale locale) {
        cityService.delete(city);
        redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("city_delete_success",new Object[]{}, locale))
            );
        sessionStatus.setComplete();
        return "redirect:/cities";
    }
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject constructJson(Model uiModel, @RequestParam(required = false, value="cid") Long cid) {
//         JSONObject responseObj = new JSONObject();
//        if(cid !=null){
//            city city = (city)cityService.findById(cid);
//            responseObj.put("data", city);
//        }else{
//            List<city> cities = (List<city>) cityService.findAll();
//            responseObj.put("data", cities);
//        }
//        return responseObj;
//    }
}
