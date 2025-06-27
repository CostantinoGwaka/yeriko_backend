package com.isofttz.yeriko_backend.servicesImpl;

import com.isofttz.yeriko_backend.entities.ChurchTimeTableEntity;
import com.isofttz.yeriko_backend.entities.MonthlyCollectionsEntity;
import com.isofttz.yeriko_backend.model.ApiException;
import com.isofttz.yeriko_backend.repository.AppUserRepository;
import com.isofttz.yeriko_backend.repository.ChurchTimeTableRepository;
import com.isofttz.yeriko_backend.repository.ChurchYearRepository;
import com.isofttz.yeriko_backend.services.ChurchTimeTableServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ChurchTimeTableServicesImpl implements ChurchTimeTableServices {

    @Autowired
    ChurchTimeTableRepository churchTimeTableRepository;

    @Autowired
    private ChurchYearRepository churchYearRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public ChurchTimeTableEntity registerChurchTimeTableEntity(ChurchTimeTableEntity churchTimeTableEntity) {

        ChurchTimeTableEntity savedTimeTable;

        Long userId = churchTimeTableEntity.getUser().getId();
        Long yearId = churchTimeTableEntity.getChurchYearEntity().getId();

        boolean userExists = appUserRepository.existsById(userId);
        boolean yearExists = churchYearRepository.existsById(yearId);

        if (!userExists) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User not found!");
        }

        if (!yearExists) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Church year not found!");
        }

        savedTimeTable = churchTimeTableRepository.save(churchTimeTableEntity);
        return savedTimeTable;
    }

    @Override
    public ChurchTimeTableEntity updateChurchTimeTableEntity(ChurchTimeTableEntity churchTimeTableEntity) {
        ChurchTimeTableEntity updatedTimeTable;

        final boolean doesExist = churchTimeTableRepository.existsById(churchTimeTableEntity.getId());

        if (!doesExist) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Time Table not found!");
        }

        updatedTimeTable = churchTimeTableRepository.save(churchTimeTableEntity);
        return updatedTimeTable;
    }

    @Override
    public List<ChurchTimeTableEntity> getAllChurchTimeTableEntity() {
        return churchTimeTableRepository.findAll();
    }
}
