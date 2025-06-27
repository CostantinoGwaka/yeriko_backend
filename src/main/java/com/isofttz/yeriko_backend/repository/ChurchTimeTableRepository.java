package com.isofttz.yeriko_backend.repository;

import com.isofttz.yeriko_backend.entities.ChurchTimeTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChurchTimeTableRepository extends JpaRepository<ChurchTimeTableEntity,Long> {

    boolean existsById(Long billId);

    ChurchTimeTableEntity findChurchTimeTableEntityById(Long timeTableId);

}
