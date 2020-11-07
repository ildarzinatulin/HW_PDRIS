package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
class UserStorageService {
    private Map<String, User> users;

    public UserStorageService() {
        this.users = new HashMap();
    }

    public void add(User user) {
        users.put(user.getName(), user);
    }

    public boolean isUserExist(User user) {
        return users.containsKey(user.getName());
    }

    public boolean isPasswordCorrect(User user) {
        if (!isUserExist(user)) {
            return false;
        }
        return users.get(user.getName()).getPassword().equals(user.getPassword());
    }
}
