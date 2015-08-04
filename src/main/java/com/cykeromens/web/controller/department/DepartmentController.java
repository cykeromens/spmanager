/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.web.controller.department;

import com.cykeromens.model.department.Department;
import com.cykeromens.service.department.DepartmentService;
import com.cykeromens.web.form.department.DepartmentForm;
import com.cykeromens.web.util.Message;
import com.cykeromens.web.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 *
 * @author cykeromens
 */
@Controller
@RequestMapping("/departments")
@SessionAttributes("department")
public class DepartmentController {

    DepartmentService departmentService;
    MessageSource messageSource;

    @Autowired
    DepartmentController(DepartmentService departmentService, MessageSource messageSource){
        this.departmentService = departmentService;
        this.messageSource = messageSource;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String listByPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value="size", required = false, defaultValue = "10") int size,
                                   @RequestParam(value="sortBy",required = false, defaultValue = "createdDate") String sort,
                                   Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale
    ){
        Pageable pageable = new PageRequest(page,size, Utils.sortBy(sort));
        //uiModel.addAttribute("chirps", chirpService.findAllChirps(Utils.sortBy(sort)));
        return "department/list";
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    public String editDepartmentForm( Model uiModel){
        uiModel.addAttribute("department", new DepartmentForm());
        return "department/edit";
    }

    @RequestMapping(method = RequestMethod.POST, params = "form")
    public String AddDepartment(@Valid DepartmentForm df, BindingResult bindingResult, Model uiModel,HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                           Locale locale){
        if(bindingResult.hasErrors()){
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("department_registration_fail",new Object[]{}, locale)));
            return "department/edit";
        }
        departmentService.createDepartment(df);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("department_registration_success",new Object[]{}, locale)));
        return "redirect:/department";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showDepartment(@PathVariable("id") Long id, Model uiModel){
        Department department = (Department)departmentService.findById(id).get();
        uiModel.addAttribute("department", department);
        return "department/show";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "form")
    public String updateDepartmentForm(@PathVariable("id") Long id, Model uiModel) {

        Department d = (Department)departmentService.findById(id).get();
        DepartmentForm departmentForm = new DepartmentForm(d.getName(),d.getObjective(),d.getDescription());
        uiModel.addAttribute("department", departmentForm);
        return "department/edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = "form")
    public String updateDepartment(@Valid DepartmentForm df, @PathVariable("id") Long id, BindingResult bindingResult, Model uiModel,
                            RedirectAttributes redirectAttributes, Locale locale){
        if(bindingResult.hasErrors()){
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("department_update_fail",new Object[]{}, locale)));
            return "redirect:/departments";
        }
        Department d = (Department)departmentService.findById(id).get();
        d.setName(df.getName());
        d.setObjective(df.getObjective());
        d.setDescription(df.getDescription());

        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("department_update_success", new Object[]{}, locale)));
        return "redirect:/users";

    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public String deleteDepartment(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale){
        departmentService.delete(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("chirp_delete_success", new Object[]{}, locale)));
        return "redirect:/users";
    }

}