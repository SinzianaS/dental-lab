package com.dental.lab.service;


import com.dental.lab.data.dao.RoleDao;
import com.dental.lab.data.domain.Role;
import com.dental.lab.data.domain.enums.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {


    private RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Optional<Role> findByName(RoleEnum name) {
        return roleDao.findByName(name);
    }

}