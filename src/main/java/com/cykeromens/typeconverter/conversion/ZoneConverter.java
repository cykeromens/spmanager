/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.location.Zone;
import com.cykeromens.service.location.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class ZoneConverter implements Converter<String, Zone>{

    ZoneService zoneService;

    @Autowired
    public ZoneConverter(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @Override
    public Zone convert(String zoneIdStr) {
        long zoneId;
        Zone zone;
        try{
               zoneId = Long.parseLong(zoneIdStr);
               zone =(Zone) zoneService.findById(zoneId).get();
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Zone.class), zoneIdStr, null);
        }
        return zone;
    }
}
