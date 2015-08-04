/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.location.Area;
import com.cykeromens.service.location.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class AreaConverter implements Converter<String, Area>{

    AreaService areaService;

    @Autowired
    public AreaConverter(AreaService areaService) {
        this.areaService = areaService;
    }

    @Override
    public Area convert(String areaIdStr) {
        long areaId;
        Area area;
        try{
               areaId = Long.parseLong(areaIdStr);
               area =(Area) areaService.findById(areaId).get();
               System.out.println("area name is: "+area.getName());
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Area.class), areaIdStr, null);
        }
        return area;
 
    }
}
