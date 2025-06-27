package com.isofttz.yeriko_backend.services;

import com.isofttz.yeriko_backend.entities.ChurchTimeTableEntity;
import com.isofttz.yeriko_backend.entities.MonthlyCollectionsEntity;

import java.util.List;

public interface ChurchTimeTableServices {

    ChurchTimeTableEntity registerChurchTimeTableEntity(ChurchTimeTableEntity churchTimeTableEntity);

    ChurchTimeTableEntity updateChurchTimeTableEntity(ChurchTimeTableEntity churchTimeTableEntity);

    List<ChurchTimeTableEntity> getAllChurchTimeTableEntity();
}
