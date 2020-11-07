package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    @Autowired
    private UserStorageService userStorageService;

    enum Status {
        OK,
        USER_ALREADY_EXIST,
        TRY_TO_USE_ADMIN_NAME
    }

    Status validate(User user) {
        if (user.getName().equals("admin")) {
            return Status.TRY_TO_USE_ADMIN_NAME;
        }
        if (userStorageService.isUserExist(user)) {
            return Status.USER_ALREADY_EXIST;
        }
        return Status.OK;
    }
}
