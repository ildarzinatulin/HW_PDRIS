package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserStorageService userStorageService;
    @Autowired
    private ValidationService validationService;
    private static final String MAIN_VIEW = "index";
    private static final String SUCCESSFULLY_LOGIN = "successfully_login";
    private static final String REGISTRATION = "registration";
    private static final String SUCCESSFULLY_REGISTERED = "successfully_registered";
    private boolean isUserExist = true;
    private boolean isPasswordCorrect = true;
    private boolean didTryToUseAdminName = false;
    private boolean didTryToUseExistName = false;
    private String lastName = "";

    @GetMapping("/")
    public String login(Model model) {
        setAttributesForLogin(model);
        isUserExist = true;
        isPasswordCorrect = true;

        return MAIN_VIEW;
    }

    private void setAttributesForLogin(Model model) {
        model.addAttribute("isUserExist", isUserExist);
        model.addAttribute("isPasswordCorrect", isPasswordCorrect);
        model.addAttribute("lastName", lastName);
    }

    @PostMapping("/")
    public String login(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        lastName = user.getName();
        if (!bindingResult.hasErrors()) {
            isUserExist = userStorageService.isUserExist(user);
            isPasswordCorrect = userStorageService.isPasswordCorrect(user);
            setAttributesForLogin(model);
            if (isUserExist && isPasswordCorrect) {
                return SUCCESSFULLY_LOGIN;
            }
        }
        return MAIN_VIEW;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        setAttributesForRegistration(model);
        return REGISTRATION;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        ValidationService.Status status = validationService.validate(user);
        switch (status) {
            case OK:
                didTryToUseAdminName = false;
                didTryToUseExistName = false;
                isUserExist = true;
                isPasswordCorrect = true;
                userStorageService.add(user);
                return SUCCESSFULLY_REGISTERED;
            case USER_ALREADY_EXIST:
                didTryToUseAdminName = false;
                didTryToUseExistName = true;
                isUserExist = true;
                isPasswordCorrect = true;
                setAttributesForRegistration(model);
                break;
            case TRY_TO_USE_ADMIN_NAME:
                didTryToUseAdminName = true;
                didTryToUseExistName = false;
                isUserExist = true;
                isPasswordCorrect = true;
                setAttributesForRegistration(model);
                break;
        }
        return REGISTRATION;
    }

    private void setAttributesForRegistration(Model model) {
        model.addAttribute("didTryToUseAdminName", didTryToUseAdminName);
        model.addAttribute("didTryToUseExistName", didTryToUseExistName);
    }
}
