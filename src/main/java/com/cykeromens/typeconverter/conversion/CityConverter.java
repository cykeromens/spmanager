/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.location.City;
import com.cykeromens.service.location.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class CityConverter implements Converter<String, City>{

    CityService cityService;

    @Autowired
    public CityConverter(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public City convert(String cityIdStr) {
        long cityId;
        City city;
        try{
               cityId = Long.parseLong(cityIdStr);
               city =(City) cityService.findById(cityId).get();
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(City.class), cityIdStr, null);
        }
        return city;
    }
    
}
