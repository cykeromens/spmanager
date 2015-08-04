/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.user;

import com.cykeromens.model.user.User;
import com.cykeromens.model.user.employee.Employee;
import com.cykeromens.service.user.UserService;
import java.util.List;

import com.cykeromens.web.form.user.EmployeeEditForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author cykeromens
 */
public interface EmployeeService extends UserService{
    
    public List<Employee> findByDepartment(String department);
    public Page<Employee> findAllByPage(Pageable pageable);
    public Employee create(EmployeeEditForm form);
}
