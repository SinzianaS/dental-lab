package com.dental.lab.service;

import com.dental.lab.data.dao.DentalWorkDao;
import com.dental.lab.data.domain.DentalWork;
import com.dental.lab.data.dto.DentalWorkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DentalWorkService {

    private final DentalWorkDao dentalWorkDao;

    public List<DentalWork> getAllDentalWorks(){
        List<DentalWork> dentalWorkList = dentalWorkDao.getAll();
        return dentalWorkList;
    }


    public DentalWork findById(UUID id) {
        return dentalWorkDao.find(id);
    }

    public DentalWork insertDentalWork(DentalWorkDto dentalWorkDto) {
        DentalWork newDentalWork = new DentalWork();
        newDentalWork.setColor(dentalWorkDto.getColor());
        newDentalWork.setType(dentalWorkDto.getType());
        newDentalWork.setStatus(dentalWorkDto.getStatus());

        return dentalWorkDao.insert(newDentalWork);
    }


}
