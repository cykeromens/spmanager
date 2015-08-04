/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.repository.user.employee;

import com.cykeromens.model.user.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author cykeromens
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT c FROM Employee c WHERE c.department=:department")
    public List<Employee> findByDepartment(@Param("department")String department);
}
