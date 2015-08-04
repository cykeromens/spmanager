/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.web.controller.user.employee;

import com.cykeromens.model.department.Department;
import com.cykeromens.model.image.Image;
import com.cykeromens.model.location.Country;
import com.cykeromens.model.user.Role;
import com.cykeromens.model.user.UserTitle;
import com.cykeromens.model.user.employee.Employee;
import com.cykeromens.service.user.EmployeeService;
import com.cykeromens.service.department.DepartmentService;
import com.cykeromens.web.form.user.EmployeeEditForm;
import com.cykeromens.web.util.Message;
import com.cykeromens.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author cykeromens
 */
@Controller
@RequestMapping("/employees")
@SessionAttributes("employee")
public class EmployeeController {

    MessageSource messageSource;
    EmployeeService employeeService;
    DepartmentService departmentService;

    @Autowired
    public EmployeeController(MessageSource messageSource, EmployeeService employeeService, DepartmentService departmentService) {
        this.messageSource = messageSource;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public String employeeList(Model uiModel, Employee employee,@RequestParam(value="id", required = false) Long id){
        if(id==null){
            return "employee/list";
        }else{
            employee = (Employee) employeeService.findUserById(id).get();
            uiModel.addAttribute("employee",employee);
            return "employee/show";
        }
    }
    
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(params={"form"}, method = RequestMethod.GET)
    public String employeeEditForm(@RequestParam(value="id", required = false) Long id,Model uiModel, EmployeeEditForm editForm){
        if(id==null){
            uiModel.addAttribute("departments", "--");
            uiModel.addAttribute("titles", UserTitle.values());
            uiModel.addAttribute("roles", Role.values());
            uiModel.addAttribute("employee",new EmployeeEditForm());
            return "employee/create";
        }else{
            uiModel.addAttribute("titles", UserTitle.values());
            uiModel.addAttribute("departments", "--");
            uiModel.addAttribute("roles", Role.values());
            Employee e= (Employee) employeeService.findUserById(id).get();
            Country country = e.getContactDetails().getAddress().getCountry();
            editForm = new EmployeeEditForm(e);
            uiModel.addAttribute("employee",editForm);
            return "employee/update";
        }
    }
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(params={"form"},method = RequestMethod.POST)
    public String employeeSave(@Valid EmployeeEditForm editForm,Model uiModel, @RequestParam(value="id", required = false) Long id, BindingResult bindingResult,
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus sessionStatus
            ){
        
        if(id==null){
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                    new Message("error", messageSource.getMessage("employee_registration_fail",new Object[]{}, locale)));
                return "employee/create";
            }

            Employee emp =  employeeService.create(editForm);
            redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("employee_registration_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/employees?id="+ UrlUtil.encodeUrlPathSegment(emp.getId().toString(), httpServletRequest);
        }else{
            if(bindingResult.hasErrors()){
                uiModel.addAttribute("message", 
                    new Message("error", messageSource.getMessage("employee_update_fail",new Object[]{}, locale)));
                return "employee/update";
            }
            Employee emp = employeeService.create(editForm);
            redirectAttributes.addFlashAttribute("message", 
                new Message("success", messageSource.getMessage("employee_update_success",new Object[]{}, locale))
            );
            sessionStatus.setComplete();
            return "redirect:/employees?id="+UrlUtil.encodeUrlPathSegment(emp.getId().toString(), httpServletRequest);
        }
    }
    
//     @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
//     @ResponseBody
//     public Image downloadPhoto(@RequestParam(value="id", required = false) Long id) {
//        Employee employee = (Employee) employeeService.findById(id);
//        if (employee.getPhoto() != null) {
//            //System.out.println("Downloading photo for id: {} with size: {}", employee.getId(),employee.getPhoto().length);
//        }
//        return employee.getPhoto();
//     }
//
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String delete(Employee employee, SessionStatus sessionStatus) {
        employeeService.deleteUser(employee);
        sessionStatus.setComplete();
        return "redirect:/employees";
    }
    
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/staffs", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject constructJson(Model uiModel, @RequestParam(required = false, value="id") Long id) {
//         JSONObject responseObj = new JSONObject();
//        if(id !=null){
//            Employee employee = (Employee)employeeService.findById(id);
//            responseObj.put("data", employee);
//        }else{
//            List<Employee> employees = (List<Employee>) employeeService.findAll();
//            responseObj.put("data", employees);
//        }
//        return responseObj;
//    }
}
 