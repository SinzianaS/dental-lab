package com.dental.lab.controller;

import com.dental.lab.data.domain.DentalWork;
import com.dental.lab.service.DentalWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DentalWorkController {

    private final DentalWorkService dentalWorkService;


    @GetMapping("/dentalWorks")
    public List<DentalWork> getAllWorks() {
        return dentalWorkService.getAllDentalWorks();
    }


}
