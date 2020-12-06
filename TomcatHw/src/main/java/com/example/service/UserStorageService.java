package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.User;

public class UserStorageService {
    private final Map<String, User> users;

    public UserStorageService() {
        this.users = new HashMap();
    }

    public void add(User user) {
        this.users.put(user.getName(), user);
    }

    public void addAll(List<User> users) {
        users.forEach(user -> this.users.put(user.getName(), user));
    }

    public void addAll(Map<String, User> users) {
        this.users.putAll(users);
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
