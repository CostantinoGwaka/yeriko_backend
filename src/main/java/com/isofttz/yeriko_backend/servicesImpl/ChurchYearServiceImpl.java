package com.isofttz.yeriko_backend.servicesImpl;

import com.isofttz.yeriko_backend.entities.ChurchYearEntity;
import com.isofttz.yeriko_backend.model.ApiException;
import com.isofttz.yeriko_backend.repository.ChurchYearRepository;
import com.isofttz.yeriko_backend.services.ChurchYearServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ChurchYearServiceImpl implements ChurchYearServices {

    @Autowired
    ChurchYearRepository churchYearRepository;

    @Override
    public ChurchYearEntity registerChurchYearEntity(ChurchYearEntity year) {
        final boolean doesYearExist = churchYearRepository.existsByChurchYear(year.getChurchYear());

        if (doesYearExist) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Year already found!");
        }

        // If the new year is marked as active, deactivate all others first
        if (Boolean.TRUE.equals(year.getIsActive())) {
            churchYearRepository.deactivateAllChurchYears();
        }

        return churchYearRepository.save(year);
    }

    @Override
    public ChurchYearEntity updateChurchYearEntity(ChurchYearEntity year) {
        ChurchYearEntity updatedChurchYear;

        final boolean doesYearExist = churchYearRepository.existsById(year.getId());

        if (!doesYearExist) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Year not found!");
        }

        // If the new year is marked as active, deactivate all others first
        if (Boolean.TRUE.equals(year.getIsActive())) {
            churchYearRepository.deactivateAllChurchYears();
        }

        updatedChurchYear = churchYearRepository.save(year);
        return updatedChurchYear;
    }

    @Override
    public ChurchYearEntity getActiveChurchYearEntity() {
        return churchYearRepository.findActiveChurchYearEntity();
    }


    @Override
    public Boolean deleteUChurchYearEntityById(Long yearId) {
        final Boolean billExists = churchYearRepository.existsById(yearId);

        if(!billExists){
            throw new ApiException(HttpStatus.BAD_REQUEST, "year not found");
        }

        if (churchYearRepository.existsById(yearId)) {
            churchYearRepository.deleteById(yearId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ChurchYearEntity findChurchYearEntityById(Long yearId) {
        return churchYearRepository.findChurchYearEntityById(yearId);
    }


}
