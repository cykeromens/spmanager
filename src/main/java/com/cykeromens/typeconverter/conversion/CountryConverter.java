/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.location.Country;
import com.cykeromens.service.location.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class CountryConverter implements Converter<String, Country>{

    CountryService countryService;

    @Autowired
    public CountryConverter(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Country convert(String countryIdStr) {
        long countryId;
        Country country;
        try{
               countryId = Long.parseLong(countryIdStr);
               country =(Country) countryService.findById(countryId).get();
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Country.class), countryIdStr, null);
        }
        return country;
    }
}
