/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.department.jpaImpl;

import com.cykeromens.model.department.Department;
import com.cykeromens.repository.department.DepartmentRepository;
import com.cykeromens.service.department.DepartmentService;
import com.cykeromens.web.form.department.DepartmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author cykeromens
 */
@Service(value = "departmentService")
@Repository
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepositry) {
        this.departmentRepository = departmentRepositry;
    }

    @Override @Transactional(readOnly = true)
    public Department findDepartmentByName(String name) {
       return departmentRepository.findDeptByName(name);
    }

    @Override
    public Department createDepartment(DepartmentForm df) {
        Department department = new Department(df.getName(), df.getObjective(), df.getDescription());
        return departmentRepository.save(department);
    }

    @Override @Transactional(readOnly = true)
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(departmentRepository.findOne(id));
    }

    @Override
    public Object save(Object object) {
        Department dept = null;
        if(object instanceof Department){
            dept = (Department) object;
            return departmentRepository.saveAndFlush(dept);
        }
        return  dept;
    }

    @Override
    public void delete(Object object) {
        if(object instanceof Department){
            Department dept = (Department) object;
            departmentRepository.delete(dept);
        }
    }

    @Override
    public Long countAll() {
        return departmentRepository.count();
    }
    
}
