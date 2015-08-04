/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.location.State;
import com.cykeromens.service.location.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class StateConverter implements Converter<String, State>{

    StateService stateService;

    @Autowired
    public StateConverter(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    public State convert(String stateIdStr) {
        long stateId;
        State state;
        try{
               stateId = Long.parseLong(stateIdStr);
               state =(State) stateService.findById(stateId).get();
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(State.class), stateIdStr, null);
        }
        return state;
    }
}
