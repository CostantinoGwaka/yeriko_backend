package com.isofttz.yeriko_backend.repository;

import com.isofttz.yeriko_backend.controller.ChurchYear;
import com.isofttz.yeriko_backend.entities.ChurchYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChurchYearRepository extends JpaRepository<ChurchYearEntity,Long> {

    boolean existsByChurchYear(String churchYear);

    boolean existsById(Long yearId);

    ChurchYearEntity findChurchYearEntityById(Long budgetId);

    @Query("SELECT c FROM ChurchYearEntity c WHERE c.isActive = true")
    ChurchYearEntity findActiveChurchYearEntity();

}
