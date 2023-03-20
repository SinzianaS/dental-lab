package com.dental.lab.service;

import com.dental.lab.data.dao.DentalWorkDao;
import com.dental.lab.data.domain.DentalWork;
import com.dental.lab.data.domain.enums.Color;
import com.dental.lab.data.domain.enums.Status;
import com.dental.lab.data.domain.enums.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class DentalWorkService {

    private final DentalWorkDao dentalWorkDao;

    public DentalWorkService(DentalWorkDao dentalWorksDAO) {
        this.dentalWorkDao = dentalWorksDAO;
    }


    public List<DentalWork> getAllDentalWorks() {
        return dentalWorkDao.getAll();
    }


    public DentalWork getDentalWorkById(UUID id) {
        return dentalWorkDao.find(id);
    }


    public void saveDentalWork(DentalWork dentalWork) {
        dentalWorkDao.insert(dentalWork);
    }

    public DentalWork updateDentalWork(UUID id, DentalWork dentalWork) {
        return dentalWorkDao.merge(dentalWork);

    }


    public void deleteDentalWorkById(UUID id) {
        dentalWorkDao.delete(id);
    }

    /**
     * Find all dental works that match the specified status.
     *
     * @param status the status of the dental works to find
     * @return a list of dental works that match the specified status
     */

    public List<DentalWork> getDentalWorksByStatus(Status status) {
        return dentalWorkDao.findDentalWorksByStatus(status);
    }


    public List<DentalWork> getDentalWorksByType(Type type) {
        return dentalWorkDao.findDentalWorksByType(type);
    }


    public List<DentalWork> getDentalWorksByColor(Color color) {
        return dentalWorkDao.findDentalWorksByColor(color);
    }
}
