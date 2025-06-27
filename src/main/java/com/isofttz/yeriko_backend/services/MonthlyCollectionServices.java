package com.isofttz.yeriko_backend.services;

import com.isofttz.yeriko_backend.entities.MonthlyCollectionsEntity;

import java.util.List;

public interface MonthlyCollectionServices {
    MonthlyCollectionsEntity registerUserMonthlyCollectionsEntity(MonthlyCollectionsEntity monthlyCollectionsEntity);

    MonthlyCollectionsEntity updateUserMonthlyCollectionsEntity(MonthlyCollectionsEntity monthlyCollectionsEntity);

    List<MonthlyCollectionsEntity> getAllMonthlyCollectionsEntityByUserId(Long userId);

    int getTotalMonthlyCollectionsEntityByUserId(String userId);

    int getTotalIncomeByUserIdAndCurrentYear(String userId,String currentYear);

    Boolean deleteMonthlyCollectionsEntityById(Long monthlyCollectionsEntityId);
}
