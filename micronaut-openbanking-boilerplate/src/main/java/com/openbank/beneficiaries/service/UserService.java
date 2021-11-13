package com.openbank.beneficiaries.service;

import com.openbank.beneficiaries.model.User;

import java.util.NoSuchElementException;

public interface UserService {

    public User findUser(String username) throws NoSuchElementException;
}
