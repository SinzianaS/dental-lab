package com.dental.lab.controller;

import com.dental.lab.data.dao.PatientDao;
import com.dental.lab.data.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final PatientDao patientDao;

    @Transactional
    @GetMapping(path = "/getSomething")
    public String getSomething() {
        Patient patient= new Patient();
        patient.setName("Test");
        patientDao.addPatient(patient);
        return "Test String";
    }
}
