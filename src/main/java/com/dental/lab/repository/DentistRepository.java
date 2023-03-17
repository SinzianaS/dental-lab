package com.dental.lab.repository;

import com.dental.lab.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, UUID> {

    Optional<Dentist> findByAddress(String address);

    List<Dentist> findByName(String name);

    List<Dentist> findByClinic(String clinic);

}
