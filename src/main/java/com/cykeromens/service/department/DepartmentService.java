/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.department;

import com.cykeromens.model.department.Department;
import com.cykeromens.service.Service;
import com.cykeromens.web.form.department.DepartmentForm;

/**
 *
 * @author cykeromens
 */
public interface DepartmentService extends Service{
    
    public Department findDepartmentByName(String name);
    public Department createDepartment(DepartmentForm departmentForm);
}
