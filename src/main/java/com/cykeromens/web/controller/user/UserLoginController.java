/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.web.controller.user;


import com.cykeromens.service.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cykeromens
 */
@Controller
public class UserLoginController {
    @Autowired
    EmployeeService employeeService;
//
//   @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(HttpServletRequest httpServletRequest,ModelMap model) {
//        //model.addAttribute("userLogin", userLogin);
//        return "login";
//    }
//    @RequestMapping(value = "/accessdenied || error", method = RequestMethod.GET)
//    public String loginError(ModelMap model) {
//        model.addAttribute("error", "true");
//        return "denied";
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(ModelMap model, HttpServletRequest httpServletRequest) {
//        httpServletRequest.getSession().invalidate();
//        return "logout";
//    }

    
}
