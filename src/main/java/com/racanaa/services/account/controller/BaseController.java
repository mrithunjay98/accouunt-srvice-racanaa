package com.racanaa.services.account.controller;


import com.racanaa.services.account.dto.validator.PasswordValidator;
import lombok.Data;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Data
public abstract class BaseController {

    // =================================================
    // Class Variables
    // =================================================

    // =================================================
    // Instance Variables
    // =================================================

    // =================================================
    // Constructors
    // =================================================

    // =================================================
    // Class Methods
    // =================================================

    // =================================================
    // Instance Methods
    // =================================================

    /**
     * Inits the binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new PasswordValidator());
    }
}
