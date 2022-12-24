package com.backend.service;

import com.backend.model.User;

public interface IUserService {
    public User getUser(Long id);
    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long id);
}
