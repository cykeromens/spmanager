/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.user.jpaImpl;

import com.cykeromens.model.user.employee.Employee;
import com.cykeromens.repository.user.UserRepository;
import com.cykeromens.service.user.jpaImpl.UserServiceImpl;
import com.cykeromens.web.form.user.EmployeeEditForm;
import com.cykeromens.repository.user.employee.EmployeeRepository;
import com.cykeromens.service.user.EmployeeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cykeromens
 */
@Service("employeeService")
@Repository
@Transactional
public class EmployeeServiceImpl extends UserServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,UserRepository userRepository) {
        super(userRepository);
        this.employeeRepository = employeeRepository;
    }


    @Override @Transactional(readOnly = true)
    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override @Transactional(readOnly = true)
    public Page<Employee> findAllByPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee create(EmployeeEditForm ef) {
        Employee employee = new Employee(ef);
        return employeeRepository.save(employee);
    }

}
