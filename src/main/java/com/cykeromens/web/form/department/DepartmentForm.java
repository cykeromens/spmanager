package com.cykeromens.web.form.department;

import javax.validation.constraints.NotNull;

/**
 * Created by omens on 8/2/15.
 */
public class DepartmentForm {

    @NotNull
    private String name;
    private String objective;
    private String description;

    public DepartmentForm() {
    }

    public DepartmentForm(String name, String objective, String description) {
        this.name = name;
        this.objective = objective;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
