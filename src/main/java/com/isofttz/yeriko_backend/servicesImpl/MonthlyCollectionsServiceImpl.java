package com.isofttz.yeriko_backend.servicesImpl;

import com.isofttz.yeriko_backend.entities.MonthlyCollectionsEntity;
import com.isofttz.yeriko_backend.model.ApiException;
import com.isofttz.yeriko_backend.repository.AppUserRepository;
import com.isofttz.yeriko_backend.repository.ChurchYearRepository;
import com.isofttz.yeriko_backend.repository.MonthlyCollectionRepository;
import com.isofttz.yeriko_backend.services.MonthlyCollectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyCollectionsServiceImpl implements MonthlyCollectionServices {

    @Autowired
    MonthlyCollectionRepository monthlyCollectionRepository;

    @Autowired
    private ChurchYearRepository churchYearRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public MonthlyCollectionsEntity registerUserMonthlyCollectionsEntity(MonthlyCollectionsEntity monthlyCollectionsEntity) {
        Long userId = monthlyCollectionsEntity.getUser().getId();
        Long yearId = monthlyCollectionsEntity.getChurchYearEntity().getId();

        boolean userExists = appUserRepository.existsById(userId);
        boolean yearExists = churchYearRepository.existsById(yearId);

        if (!userExists) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User not found!");
        }

        if (!yearExists) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Church year not found!");
        }

        boolean alreadySaved = monthlyCollectionRepository.existsByUser_IdAndChurchYearEntity_Id(userId, yearId);
        if (alreadySaved) {
            throw new ApiException(HttpStatus.CONFLICT, "Data for this user and year already exists!");
        }

        return monthlyCollectionRepository.save(monthlyCollectionsEntity);
    }

    @Override
    public MonthlyCollectionsEntity updateUserMonthlyCollectionsEntity(MonthlyCollectionsEntity monthlyCollectionsEntity) {
        MonthlyCollectionsEntity updatedMonthlyCollectionsEntity;

        final boolean doesExist = monthlyCollectionRepository.existsById(monthlyCollectionsEntity.getId());

        if (!doesExist) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Monthly Collections not found!");
        }

        updatedMonthlyCollectionsEntity = monthlyCollectionRepository.save(monthlyCollectionsEntity);
        return updatedMonthlyCollectionsEntity;
    }

    @Override
    public List<MonthlyCollectionsEntity> getAllMonthlyCollectionsEntityByUserId(Long userId) {
        List<MonthlyCollectionsEntity> expenses = monthlyCollectionRepository.findByUser_Id(userId);

        if (expenses.isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User expenses not found");
        }

        return expenses;
    }

    @Override
    public int getTotalMonthlyCollectionsEntityByUserId(String userId) {
        Integer total = monthlyCollectionRepository.getTotalMonthlyCollectionsEntityByUserId(userId);
        return total != null ? total : 0;
    }

    @Override
    public int getTotalIncomeByUserIdAndCurrentYear(String userId, String currentYear) {
        Integer total = monthlyCollectionRepository.getTotalMonthlyCollectionsEntityByUserIdAndYear(userId,currentYear);
        return total != null ? total : 0;
    }

    @Override
    public Boolean deleteMonthlyCollectionsEntityById(Long monthlyCollectionsEntityId) {
        return null;
    }
}
