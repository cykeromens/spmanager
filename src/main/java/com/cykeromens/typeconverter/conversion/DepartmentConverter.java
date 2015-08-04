/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.department.Department;
import com.cykeromens.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class DepartmentConverter implements Converter<String,Department>{

    DepartmentService departmentService;

    @Autowired
    public DepartmentConverter(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public Department convert(String deptIdStr) {
        long deptId;
        Department dept;
        try{
               deptId = Long.parseLong(deptIdStr);
               dept =(Department) departmentService.findById(deptId).get();
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Department.class), deptIdStr, null);
        }
        return dept;
 
    }
}
