/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.web.controller.location;

import com.cykeromens.service.location.AreaService;
import com.cykeromens.service.location.CountryService;
import com.cykeromens.service.location.StateService;
import com.cykeromens.service.location.ZoneService;
import com.cykeromens.service.store.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author cykeromens
 */
//@Controller
public class LoadDataByJSon {
    
//    @Autowired
//    CountryService countryService;
//    @Autowired
//    ZoneService zoneService;
//    @Autowired
//    StateService stateService;
//    @Autowired
//    AreaService areaService;
//    @Autowired
//    CategoryService categoryService;
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value="/getZones",method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject zoneIncountry(Model uiModel,@RequestParam(value="countryId", required = true) Long cid) {
//        Country country =(Country)countryService.findById(cid);
//        Collection<Zone> zones =country.getZones();
//        List zonesInCountry = new ArrayList();
//        JSONObject responseObj = new JSONObject();
//        JSONObject zoneObj;
//        for(Zone zone:zones){
//            zoneObj = new JSONObject();
//            if(zoneObj.isEmpty()){
//                zoneObj.put("", "------");
//            }
//            zoneObj.put("id", zone.getId());
//            zoneObj.put("name", zone.getName());
//            //map.put(emp., totalRetailer.toString());
//            zonesInCountry.add(zoneObj);
//        }
//
//        responseObj.put("data", zonesInCountry);
//        return (JSONObject) responseObj;
//    }
//
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value="/getStates",method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject stateInZone(Model uiModel,@RequestParam(value="zoneId", required = true) Long zid) {
//        Zone zone =(Zone) zoneService.findById(zid);
//        Collection<State> states =zone.getStates();
//        List statesInZone = new ArrayList();
//        JSONObject responseObj = new JSONObject();
//        JSONObject stateObj;
//        for(State state:states){
//            stateObj = new JSONObject();
//            if(stateObj.isEmpty()){
//                stateObj.put("", "------");
//            }
//            stateObj.put("id", state.getId());
//            stateObj.put("name", state.getName());
//            //map.put(emp., totalRetailer.toString());
//            statesInZone.add(stateObj);
//        }
//
//        responseObj.put("data", statesInZone);
//        return (JSONObject) responseObj;
//    }
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value="/getAreas",method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject areaInState(Model uiModel,@RequestParam(value="stateId", required = true) Long sid) {
//        State state =(State) stateService.findById(sid);
//        Collection<area> areas =state.getAreas();
//        List areasInZone = new ArrayList();
//        JSONObject responseObj = new JSONObject();
//        JSONObject areaObj;
//        for(area area:areas){
//            areaObj = new JSONObject();
//            if(areaObj.isEmpty()){
//                areaObj.put("", "------");
//            }
//            areaObj.put("id", area.getId());
//            areaObj.put("name", area.getName());
//            areasInZone.add(areaObj);
//        }
//
//        responseObj.put("data", areasInZone);
//        return (JSONObject) responseObj;
//    }
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value="/getCities",method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject cityInArea(Model uiModel,@RequestParam(value="areaId", required = true) Long aid) {
//        area area =(area) areaService.findById(aid);
//        Collection<city> cities =area.getCities();
//        List citiesInArea = new ArrayList();
//        JSONObject responseObj = new JSONObject();
//        JSONObject cityObj;
//        for(city city:cities){
//            cityObj = new JSONObject();
//            if(cityObj.isEmpty()){
//                cityObj.put("", "------");
//            }
//            cityObj.put("id", city.getId());
//            cityObj.put("name", city.getName());
//            citiesInArea.add(cityObj);
//        }
//
//        responseObj.put("data", citiesInArea);
//        return (JSONObject) responseObj;
//    }
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value="/getSubcategories",method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject subCategories(Model uiModel,@RequestParam(value="cid", required = true) Long cid) {
//
//        Category category =(Category) categoryService.findById(cid);
//        Collection<SubCategory> subCategories =category.getSubCategories();
//        List subCats = new ArrayList();
//        JSONObject responseObj = new JSONObject();
//        JSONObject subObj;
//        subObj = new JSONObject();
//        subObj.put("", "-------");
//        for(SubCategory subCategory:subCategories){
//            subObj.put("id", subCategory.getId());
//            subObj.put("name", subCategory.getName());
//            subCats.add(subObj);
//        }
//
//        responseObj.put("subCategories", subCats);
//        return (JSONObject) responseObj;
//    }
}
