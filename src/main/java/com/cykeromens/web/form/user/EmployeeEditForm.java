package com.cykeromens.web.form.user;

import com.cykeromens.model.user.employee.Employee;
import com.cykeromens.web.form.UserCreateForm;

/**
 * Created by omens on 8/1/15.
 */
public class EmployeeEditForm extends UserCreateForm{

    private String department;
    private Enum role;

    public EmployeeEditForm() {
    }

    public EmployeeEditForm(Employee e) {

        super(e.getTitle(),e.getFirstName(),e.getLastName(),e.getContactDetails().getEmail()
                ,e.getContactDetails().getMobilePhoneNumber(),e.getUsername(),e.getContactDetails().getAddress().getCountry().getName(),
                e.getContactDetails().getAddress().getZone().getName(), e.getContactDetails().getAddress().getState().getName(),e.getContactDetails().getAddress().getArea().getName(),
                e.getContactDetails().getAddress().getCity().getName(),e.getContactDetails().getAddress().getStreet());
        this.department = e.getDepartment().getName();
        this.role = e.getRole();
    }




    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Enum role) {
        this.role = role;
    }
}
