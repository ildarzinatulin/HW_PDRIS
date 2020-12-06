package com.example;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserStorageService userStorageService;
    private final ValidationService validationService;
    private final LoginAuditService loginAuditService;

    private static final String MAIN_VIEW = "index";
    private static final String SUCCESSFULLY_LOGIN = "successfully_login";
    private static final String REGISTRATION = "registration";
    private static final String SUCCESSFULLY_REGISTERED = "successfully_registered";
    private static final String AUDIT = "audit";

    @Autowired
    public MainController(UserStorageService userStorageService, ValidationService validationService,
            LoginAuditService loginAuditService) {
        this.userStorageService = userStorageService;
        this.validationService = validationService;
        this.loginAuditService = loginAuditService;
    }

    @GetMapping("/")
    public String login() {
        return MAIN_VIEW;
    }

    private void setAttributesForLogin(Model model, boolean isUserExist, boolean isPasswordCorrect,
            String lastUserName) {
        model.addAttribute("isUserExist", isUserExist);
        model.addAttribute("isPasswordCorrect", isPasswordCorrect);
        model.addAttribute("lastUserName", lastUserName);
    }

    @PostMapping("/")
    public String login(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            boolean isUserExist = userStorageService.isUserExist(user);
            boolean isPasswordCorrect = userStorageService.isPasswordCorrect(user);
            setAttributesForLogin(model, isUserExist, isPasswordCorrect, user.getName());
            StringBuilder error = new StringBuilder();
            if (!isUserExist) {
                error.append(" User now exist. ");
            }
            if (!isPasswordCorrect) {
                error.append(" Password is incorrect. ");
            }
            loginAuditService.addLogs(Instant.now(), user, isUserExist && isPasswordCorrect,
                    error.toString());
            if (isUserExist && isPasswordCorrect) {
                return SUCCESSFULLY_LOGIN;
            }
        }
        return MAIN_VIEW;
    }

    @GetMapping("/registration")
    public String registration() {
        return REGISTRATION;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        ValidationService.Status status = validationService.validate(user);
        switch (status) {
            case OK:
                userStorageService.add(user);
                setAttributesForRegistration(model, false, false);
                return SUCCESSFULLY_REGISTERED;
            case USER_ALREADY_EXIST:
                setAttributesForRegistration(model, false, true);
                break;
            case TRY_TO_USE_ADMIN_NAME:
                setAttributesForRegistration(model, true, false);
                break;
        }
        return REGISTRATION;
    }

    @GetMapping("/audit")
    public String audit(Model model) {
        model.addAttribute("audit", loginAuditService.getLogs());
        return AUDIT;
    }

    private void setAttributesForRegistration(Model model, boolean didTryToUseAdminName, boolean didTryToUseExistName) {
        model.addAttribute("didTryToUseAdminName", didTryToUseAdminName);
        model.addAttribute("didTryToUseExistName", didTryToUseExistName);
    }
}
