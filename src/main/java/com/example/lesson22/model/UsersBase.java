package com.example.lesson22.model;

import lombok.*;

import java.util.concurrent.ConcurrentHashMap;

public class UsersBase {

    private static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public static boolean add(User user) {
        if (users.containsKey(user.getLogin())) {
            return false;
        } else {
            users.put(user.getLogin(), user);
            return true;
        }
    }
}
