/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.web.controller.location;

import com.cykeromens.model.location.Country;
import com.cykeromens.service.location.CountryService;
import com.cykeromens.web.util.Message;
import com.cykeromens.web.util.UrlUtil;
import com.cykeromens.web.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
@RequestMapping("/countries")
@SessionAttributes("country")
public class CountryController {
    
    CountryService countryService;
    MessageSource messageSource;

    @Autowired
    public CountryController(CountryService countryService, MessageSource messageSource) {
        this.countryService = countryService;
        this.messageSource = messageSource;
    }

    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public String listCountry(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(value="size", required = false, defaultValue = "10") int size,
                              @RequestParam(value="sortBy",required = false, defaultValue = "createdDate") String sort,
                              Model uiModel, Country country,@RequestParam(value="id", required = false) Long id){
        if(id==null){
            Pageable pageable = new PageRequest(page,size, Utils.sortBy(sort));

            Page<Country> countries= (Page<Country>) countryService.findAll(pageable);
            uiModel.addAttribute("countries",countries);
            return "country/list";
        }else{
        country = (Country) countryService.findById(id).get();
        uiModel.addAttribute("country",country);
        return "country/show";
        }
    }
    
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"}, method = RequestMethod.GET)
    public String countryForm(@RequestParam(value="id", required = false) Long id,Model uiModel, Country country){
        if(id==null){
            country = new Country();
            uiModel.addAttribute("country",country);
            return "country/create";
        }else{
            country = (Country) countryService.findById(id).get();
            uiModel.addAttribute("country",country);
            return "country/update";
        }
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params={"form"},method = RequestMethod.POST)
    public String countrySave(Model uiModel,@Valid Country country, @RequestParam(value="id", required = false) Long id, BindingResult bindingResult, 
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus sessionStatus){
        if(id==null){
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                    new Message("error", messageSource.getMessage("country_creation_fail",new Object[]{}, locale)));
                return "country/create";
            }
                country = (Country) countryService.save(country);
                redirectAttributes.addFlashAttribute("message", 
                    new Message("success", messageSource.getMessage("country_creation_success",new Object[]{}, locale))
                );
                sessionStatus.setComplete();
                return "redirect:/countries?id="+UrlUtil.encodeUrlPathSegment(country.getId().toString(), httpServletRequest);
        }else{
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                        new Message("error", messageSource.getMessage("country_update_fail",new Object[]{}, locale)));
                return "country/update";
            }

            country = (Country) countryService.save(country);
            redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("country_update_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/countries?id="+ UrlUtil.encodeUrlPathSegment(country.getId().toString(), httpServletRequest);
        }
    }
    
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String deleteCountry(Country country, BindingResult result, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, Locale locale) {
        countryService.delete(country);
        redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("country_delete_success",new Object[]{}, locale))
            );
        sessionStatus.setComplete();
        return "redirect:/countries";
    }
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject constructJson(Model uiModel, @RequestParam(required = false, value="id") Long id) {
//         JSONObject responseObj = new JSONObject();
//        if(id !=null){
//            Country country = (Country)countryService.findById(id);
//            responseObj.put("data", country);
//        }else{
//            List<Country> countries = (List<Country>) countryService.findAll();
//            responseObj.put("data", countries);
//        }
//        return responseObj;
//    }
}
