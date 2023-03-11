package com.dental.lab.repository;

import com.dental.lab.data.domain.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, UUID> {
}
