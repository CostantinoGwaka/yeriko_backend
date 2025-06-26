package com.isofttz.yeriko_backend.servicesImpl;

import com.isofttz.yeriko_backend.entities.ChurchYearEntity;
import com.isofttz.yeriko_backend.model.ApiException;
import com.isofttz.yeriko_backend.repository.ChurchYearRepository;
import com.isofttz.yeriko_backend.services.ChurchYearServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChurchYearServiceImpl implements ChurchYearServices {

    @Autowired
    ChurchYearRepository churchYearRepository;

    @Override
    public ChurchYearEntity registerChurchYearEntity(ChurchYearEntity year) {
        ChurchYearEntity savedChurchYear;

        final boolean doesYearExist = churchYearRepository.existsByChurchYear(year.getChurchYear());

        if (doesYearExist) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Year already found!");
        }

        savedChurchYear = churchYearRepository.save(year);
        return savedChurchYear;
    }

    @Override
    public ChurchYearEntity updateChurchYearEntity(ChurchYearEntity year) {
        ChurchYearEntity updatedChurchYear;

        final boolean doesYearExist = churchYearRepository.existsById(year.getId());

        if (!doesYearExist) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Year not found!");
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
