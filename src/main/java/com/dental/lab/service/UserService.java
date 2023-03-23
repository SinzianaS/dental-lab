package com.dental.lab.service;

import com.dental.lab.data.dao.UserDao;
import com.dental.lab.data.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    public User save(User user) {
        return userDao.save(user);
    }

}
