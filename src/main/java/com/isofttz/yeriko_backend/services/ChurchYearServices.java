package com.isofttz.yeriko_backend.services;

import com.isofttz.yeriko_backend.entities.ChurchYearEntity;

import java.util.List;

public interface ChurchYearServices {
    ChurchYearEntity registerChurchYearEntity(ChurchYearEntity year);

    ChurchYearEntity updateChurchYearEntity(ChurchYearEntity year);

    ChurchYearEntity getActiveChurchYearEntity();

    Boolean deleteUChurchYearEntityById(Long yearId);

    ChurchYearEntity findChurchYearEntityById(Long yearId);
}
