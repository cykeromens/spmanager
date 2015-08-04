/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.repository.department;

import com.cykeromens.model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author cykeromens
 */
public interface DepartmentRepository extends JpaRepository<Department, Long>{

    @Query("select p FROM Department p WHERE p.name=:name")
    public Department findDeptByName(@Param("name")String name);

}
