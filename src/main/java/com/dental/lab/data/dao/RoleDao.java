package com.dental.lab.data.dao;

import com.dental.lab.data.domain.Role;
import com.dental.lab.data.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleEnum name);
}