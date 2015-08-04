/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.web.controller.user.retailer;

import com.cykeromens.model.user.retailer.Retailer;
import com.cykeromens.service.user.EmployeeService;
import com.cykeromens.service.department.DepartmentService;
import com.cykeromens.service.location.CountryService;
import com.cykeromens.service.user.RetailerService;
import com.cykeromens.service.store.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 *
 * @author cykeromens
 */
@Controller
@RequestMapping("/retailers")
@SessionAttributes("retailer")
public class RetailerController {
    @Autowired
    MessageSource messageSource;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CountryService countryService;
   // @Autowired
    RetailerService retailerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;
    
    
    
    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public String retatailerList(Model uiModel, Retailer retailer,@RequestParam(value="id", required = false) Long id){
        if(id==null){
            //List<Retailer> retailers = (List<Retailer>) retailerService.findAll();
            //uiModel.addAttribute("retailers", retailers);
            return "retailer/list";
        }else{
            retailer = (Retailer) retailerService.findUserById(id).get();
            uiModel.addAttribute("retailer",retailer);
            return "retailer/show";
        }
    }
    
//
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/listRetailer", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public RetailerGrid listGrid(
//        @RequestParam("_search") Boolean search,
//        @RequestParam(value="filters", required=false) String filters,
//        @RequestParam(value = "page", required = false) Integer page,
//        @RequestParam(value = "rows", required = false) Integer rows,
//        @RequestParam(value = "sidx", required = false) String sortBy,
//        @RequestParam(value = "sord", required = false) String order) {
//
//        // Process order by
//        Sort sort = null;
//        String orderBy = sortBy;
//        if (orderBy != null && orderBy.equals("createdDateString"))
//            orderBy = "createdDateString";
//
//        if (orderBy != null && order != null) {
//            if (order.equals("desc")) {
//                sort = new Sort(Sort.Direction.DESC, orderBy);
//
//            } else
//                sort = new Sort(Sort.Direction.ASC, orderBy);
//        }
//        // Constructs page request for current page
//        // Note: page number for Spring Data JPA starts with font_awesome, while jqGrid starts with 1
//        PageRequest pageRequest;
//        if (sort != null) {
//            pageRequest = new PageRequest(page - 1, rows, sort);
//        }
//        else {
//            pageRequest = new PageRequest(page - 1, rows);
//        }
//        if (search == true) {
//            return getFilteredRecords(filters, pageRequest);
//        }
//        //pageRequest = new PageRequest(1, 1);
//        Page<Retailer> retailerPage = retailerService.findAllByPage(pageRequest);
//        // Construct the grid data that will return as JSON data
//        RetailerGrid retailerGrid = new RetailerGrid();
//        retailerGrid.setCurrentPage(retailerPage.getNumber() + 1);
//        retailerGrid.setTotalPages(retailerPage.getTotalPages());
//        retailerGrid.setTotalRecords(retailerPage.getTotalElements());
//        ArrayList<Retailer> newArrayList = Lists.newArrayList(retailerPage.iterator());
//
//        retailerGrid.setRetailerData(retailerPage.getContent());
//        return retailerGrid;
//    }
//
//    /**
//     * the retailer id for a sales rep is 327681 should not be altered
//     * @param uiModel
//     * @return
//     */
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(value = "/chart", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public JSONObject constructChart(Model uiModel) {
//        Map<String,String> map = new HashMap<String,String>();
//        Department department = (Department) departmentService.findById(32768l);
//        List<Employee> employees = employeeService.findByDepartment(department.getName());
//        List salesRepRetailers = new ArrayList();
//        JSONObject responseObj = new JSONObject();
//        JSONObject empObj;
//        for(Employee emp:employees){
//            Long totalRetailer = retailerService.countRetailersInArea(emp.getUsername());
//            empObj = new JSONObject();
//            empObj.put("name", emp.getUsername());
//            empObj.put("totalRetailer", totalRetailer);
//            map.put(emp.getUsername(), totalRetailer.toString());
//            salesRepRetailers.add(empObj);
//        }
//
//        responseObj.put("data", salesRepRetailers);
//        return (JSONObject) responseObj;
//    }
//
////    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
////    @RequestMapping(value = "/chart", method = RequestMethod.GET, produces="application/json")
////    @ResponseBody
////    public JSONObject constructChart(Model uiModel) {
////        Map<String,String> map = new HashMap<String,String>();
////        List<State> states = (List<State>) stateService.findAll();
////        List salesRepRetailers = new ArrayList();
////        JSONObject responseObj = new JSONObject();
////        JSONObject empObj;
////        for(Employee emp:employees){
////            Long totalRetailer = retailerService.countRetailersInArea(emp.getUsername());
////            empObj = new JSONObject();
////            empObj.put("name", emp.getUsername());
////            empObj.put("totalRetailer", totalRetailer);
////            map.put(emp.getUsername(), totalRetailer.toString());
////            salesRepRetailers.add(empObj);
////        }
////
////        responseObj.put("salesRepRetailers", salesRepRetailers);
////        return (JSONObject) responseObj;
////    }
//
//
//
//    public RetailerGrid getFilteredRecords(String filters, Pageable pageable){
//        Page<Retailer> retailerPage = null;
//        JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
//        for (JqgridFilter.Rule rule: jqgridFilter.getRules()) {
//            if("businessName".equals(rule.getField())){
//               retailerPage = retailerService.searchByBusinessName("%"+rule.getData()+"%", pageable);
//            }else if("name".equals(rule.getField())){
//               retailerPage = retailerService.searchByBusinessOwner("%"+rule.getData()+"%", pageable);
//            }else if("area".equals(rule.getField())){
//               retailerPage = retailerService.searchByArea("%"+rule.getData()+"%", pageable);
//            }else if("email".equals(rule.getField())){
//               retailerPage = retailerService.searchByEmail("%"+rule.getData()+"%", pageable);
//            }else if("category".equals(rule.getField())){
//               retailerPage = retailerService.searchByCategory("%"+rule.getData()+"%", pageable);
//            }else if("comment".equals(rule.getField())){
//               retailerPage = retailerService.searchByComments("%"+rule.getData()+"%", pageable);
//            }else if("salesRep".equals(rule.getField())){
//               retailerPage = retailerService.searchBySalesRep("%"+rule.getData()+"%", pageable);
//            }else if("dateTime".equals(rule.getField())){
//               retailerPage = retailerService.searchByDateCreated("%"+rule.getData()+"%", pageable);
//            }else if("businessPhoneNumber".equals(rule.getField())){
//               retailerPage = retailerService.searchByBusinessPhone("%"+rule.getData()+"%", pageable);
//            }else if("street".equals(rule.getField())){
//               retailerPage = retailerService.searchByStreet("%"+rule.getData()+"%", pageable);
//            }
//        }
//
//        RetailerGrid retailerGrid = new RetailerGrid();
//        retailerGrid.setCurrentPage(retailerPage.getNumber() + 1);
//        retailerGrid.setTotalPages(retailerPage.getTotalPages());
//        retailerGrid.setTotalRecords(retailerPage.getTotalElements());
//        retailerGrid.setRetailerData(Lists.newArrayList(retailerPage.iterator()));
//        return retailerGrid;
//    }
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(params={"form"}, method = RequestMethod.GET)
//    public String RetailerEditForm(@RequestParam(value="id", required = false) Long id,ModelMap uiModelMap, Retailer retailer){
//        Map<String, String > categorys = new HashMap<String,String>();
//        Map<String, String > countrys = new HashMap<String,String>();
//        List<Category> categories = (List<Category>) categoryService.findAll();
//        List<Country> countries = (List<Country>) countryService.findAll();
//        countrys.put("", "----------");
//        for (Category cat : categories) {
//            categorys.put(cat.getId().toString(), cat.getName());
//        }
//        for (Country country : countries) {
//            countrys.put(country.getId().toString(), country.getName());
//        }
//        if(id==null){
//            retailer = new Retailer();
//            retailer.setStatus("TRACKER");
//            uiModelMap.addAttribute("countries", countrys);
//            uiModelMap.addAttribute("categories", categorys);
//            uiModelMap.addAttribute("retailer",retailer);
//            return "retailer/create";
//        }else{
//            //uiModelMap.addAttribute("areas", areaService.findAll());
//            uiModelMap.addAttribute("categories", categorys);
//            retailer = (Retailer) retailerService.findById(id);
//            uiModelMap.addAttribute("countries", countrys);
//            uiModelMap.addAttribute("retailer",retailer);
//            return "retailer/update";
//        }
//    }
//
//    @PreAuthorize(value = "hasRole('ROLE_STAFF')")
//    @RequestMapping(params={"form"},method = RequestMethod.POST)
//    public String retailerSave(@Valid Retailer retailer,Model uiModel, @RequestParam(value="id", required = false) Long id, BindingResult bindingResult,
//        HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus sessionStatus, Principal principal){
//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
//        Employee salesRep = (Employee) employeeService.findByUsername(user.getUsername());
//        if(id==null){
//             if(bindingResult.hasErrors()){
//                uiModel.addAttribute("message",
//                        new Message("error", messageSource.getMessage("retailer_registration_fail",new Object[]{}, locale)));
//                return "retailer/create";
//            }
//
//
//                if("sales".equals(salesRep.getDepartment().getName().toLowerCase()) || "comenuko@spacepointe.com".equals(salesRep.getContactDetails().getEmail().toLowerCase())){
//                    retailer.setSalesRep(salesRep);
//                    }else{
//                    redirectAttributes.addFlashAttribute("message",
//                            new Message("error", messageSource.getMessage("no_access_allowed",new Object[]{}, locale))
//                        );
//                return "redirect:/retailers";
//                }
//
//                Account account = new Account(retailer);
//                retailer.setAccount(account);
//                DateTime createdDate = DateTime.now();
//                retailer.setCreatedDate(createdDate);
//                retailer.setCreatedBy(salesRep.getFirstName() +" "+ salesRep.getLastName());
//
//                retailer = (Retailer) retailerService.save(retailer);
//                //JOptionPane.showMessageDialog(null,retailer.getContactInfo().getAddress().getCountry().getName());
//                if(retailer !=null){
//                    redirectAttributes.addFlashAttribute("message",
//                                new Message("success", messageSource.getMessage("retailer_registration_success",new Object[]{}, locale))
//                    );
//                    sessionStatus.setComplete();
//                    return "redirect:/retailers?id="+UrlUtil.encodeUrlPathSegment(retailer.getId().toString(), httpServletRequest);
//                }else{
//                    return "redirect:/retailers";
//                }
//
//        }else{
//            if(bindingResult.hasErrors()){
//                uiModel.addAttribute("message",
//                        new Message("error", messageSource.getMessage("retailer_update_fail",new Object[]{}, locale)));
//                return "retailer/update";
//            }
//            if(retailer.getSalesRep() ==null){
//                retailer.setSalesRep(salesRep);
//            }
//            if(retailer.getAccount()==null){
//                Account account = new Account(retailer);
//                retailer.setAccount(account);
//            }
//            retailer.setLastModifiedBy(salesRep.getFirstName() +" "+ salesRep.getLastName());
//            DateTime lastModified = DateTime.now();
//            retailer.setLastModifiedDate(lastModified);
//            retailer = (Retailer) retailerService.save(retailer);
//            redirectAttributes.addFlashAttribute("message",
//                new Message("success", messageSource.getMessage("retailer_update_success",new Object[]{}, locale))
//            );
//            sessionStatus.setComplete();
//            return "redirect:/retailers?id="+UrlUtil.encodeUrlPathSegment(retailer.getId().toString(), httpServletRequest);
//        }
//    }
//
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
//    @RequestMapping(params = "delete", method = RequestMethod.POST)
//    public String delete(Retailer retailer, BindingResult result, SessionStatus sessionStatus,Locale locale, RedirectAttributes redirectAttributes) {
//        retailerService.delete(retailer);
//        redirectAttributes.addFlashAttribute("message",
//                new Message("success", messageSource.getMessage("retailer_delete_success",new Object[]{}, locale))
//            );
//        sessionStatus.setComplete();
//        return "redirect:/retailers";
//    }
//
//
//     @RequestMapping(value = "/datatable", method = RequestMethod.GET)
//    public String show(ModelMap model) {
//        return "datatable";
//    }
//
//    /*Table data load - This loads the data for the table*/
//    @RequestMapping(value = "/getAllRetailers", method = RequestMethod.GET,produces="application/json")
//    @ResponseBody
//    public JsonJTableRetailerList getPageableRetailers(@RequestParam int jtStartIndex, @RequestParam int jtPageSize) {
//        JsonJTableRetailerList jstr;
//        Collection<Retailer> retailerList;
//        try {
//            Long numOfRetailers = retailerService.countAll();
//            retailerList = retailerService.getAllRetailers(jtStartIndex,jtPageSize);
//            jstr = new JsonJTableRetailerList("OK",retailerList,numOfRetailers);
//        } catch (Exception e) {
//            jstr = new JsonJTableRetailerList("ERROR",e.getMessage());
//        }
//        //JOptionPane.showInputDialog(retailerList.size());
//        return jstr;
//        //return "redirect:/retailers";
//    }
//
//    /*Cascaded drop down part one - loads the categories*/
//    @RequestMapping(value = "/datatable/categories", method = RequestMethod.POST)
//    public @ResponseBody JsonJTableRetailerList getCategories(@RequestParam(value = "retailerId", required = true) Long retailerId) {
//        JsonJTableRetailerList jstr;
//        Collection<Category> categories;
//        try {
//            if (retailerId==font_awesome) {
//               categories = (List<Category>) categoryService.findAll();
//            } else {
//                Retailer retailer = (Retailer)retailerService.findById(retailerId);
//                categories = retailer.getCategory();
//            }
//
//            jstr = new JsonJTableRetailerList("OK",categories.toString());
//        } catch (Exception e) {
//            jstr = new JsonJTableRetailerList("ERROR",e.getMessage());
//        }
//        return jstr;
//    }
//
//    /*Cascaded drop down part two - loads the sub-categories*/
////    @RequestMapping(value = "/datatable/subcategories", method = RequestMethod.POST)
////    public @ResponseBody JsonJTableRetailerList getSubCategoryByCategory(@RequestParam(value = "categoryId", required = true) String categoryId) {
////        JsonJTableRetailerList jstr;
////        List<Retailer> subcategories;
////        try {
////            if (categoryId.equals("font_awesome")) {
////                subcategories = expenseService.listExpenseSubCategories();
////            } else {
////                subcategories = categoryService.getSubCategoriesForCategory(categoryId);
////            }
////            jstr = new JsonJTableExpenseOptionsResponse("OK",subcategories);
////        } catch (Exception e) {
////            jstr = new JsonJTableExpenseOptionsResponse("ERROR",e.getMessage());
////        }
////        return jstr;
////    }
//
//    /*CRUD operation - Add the expense */
//    @RequestMapping(value = "/datatable/addRetailer", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonJTableRetailer insertGroup(@ModelAttribute Retailer retailerBean, BindingResult result) {
//        JsonJTableRetailer jsonJtableResponse;
//        if (result.hasErrors()) {
//            jsonJtableResponse = new JsonJTableRetailer("ERROR","Form invalid");
//        }
//        try {
//            retailerService.save(retailerBean);
//            jsonJtableResponse = new JsonJTableRetailer("OK",retailerBean);
//        } catch (Exception e) {
//            jsonJtableResponse = new JsonJTableRetailer("ERROR",e.getMessage());
//        }
//        return jsonJtableResponse;
//    }
//
//    /*CRUD operation - Update the expense */
//    @RequestMapping(value = "/datatable/updateRetailer", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonJTableRetailer updateRole(@ModelAttribute Retailer retailerBean, BindingResult result) {
//        JsonJTableRetailer jsonJtableResponse;
//        if (result.hasErrors()) {
//            jsonJtableResponse = new JsonJTableRetailer("ERROR","Form invalid");
//        }
//        try {
//            retailerService.save(retailerBean);
//            jsonJtableResponse = new JsonJTableRetailer("OK",retailerBean);
//        } catch (Exception e) {
//            jsonJtableResponse = new JsonJTableRetailer("ERROR",e.getMessage());
//        }
//        return jsonJtableResponse;
//    }
//
//    /*CRUD operation - Delete the expense */
//    @RequestMapping(value = "/datatable/deleteExpense", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonJTableRetailer delete(@RequestParam String retailerId) {
//        JsonJTableRetailer jsonJtableResponse;
//        try {
//            retailerService.delete(new Long(retailerId));
//            jsonJtableResponse = new JsonJTableRetailer("OK");
//        } catch (Exception e) {
//            jsonJtableResponse = new JsonJTableRetailer("ERROR",e.getMessage());
//        }
//        return jsonJtableResponse;
//    }
}

