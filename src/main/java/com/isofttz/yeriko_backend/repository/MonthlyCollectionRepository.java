package com.isofttz.yeriko_backend.repository;

import com.isofttz.yeriko_backend.entities.MonthlyCollectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonthlyCollectionRepository extends JpaRepository<MonthlyCollectionsEntity,Long> {
    List<MonthlyCollectionsEntity> findByUser_Id(Long userId);

    boolean existsById(Long expenseId);

    boolean existsByUser_IdAndChurchYearEntity_Id(Long userId, Long churchYearId); // ✅ correct

    MonthlyCollectionsEntity findMonthlyCollectionsEntityById(Long expenseId);

    @Query("SELECT SUM(t.amount) FROM MonthlyCollectionsEntity t WHERE t.user.id = :userId")
    Integer getTotalMonthlyCollectionByUserId(@Param("userId") String userId);

    @Query("SELECT SUM(t.amount) FROM MonthlyCollectionsEntity t WHERE t.user.id  = :userId AND t.churchYearEntity.churchYear = :churchYear")
    Integer getTotalMonthlyAndYearCollectionByUserId(@Param("userId") String userId,@Param("churchYear") String churchYear);

    @Query("SELECT SUM(t.amount) FROM MonthlyCollectionsEntity t WHERE t.user.id = :userId")
    Integer getTotalMonthlyCollectionsEntityByUserId(@Param("userId") String userId);

    @Query("SELECT SUM(t.amount) FROM MonthlyCollectionsEntity t WHERE t.user.id = :userId AND t.churchYearEntity.churchYear = :currentYear")
    Integer getTotalMonthlyCollectionsEntityByUserIdAndYear(@Param("userId") String userId,@Param("currentYear") String currentYear);

}
