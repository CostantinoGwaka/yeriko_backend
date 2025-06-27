package com.isofttz.yeriko_backend.controller;

import com.isofttz.yeriko_backend.entities.ChurchTimeTableEntity;
import com.isofttz.yeriko_backend.entities.MonthlyCollectionsEntity;
import com.isofttz.yeriko_backend.model.ResponseModel;
import com.isofttz.yeriko_backend.services.ChurchTimeTableServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/churchTimeTable")
public class ChurchTimeTable {

    @Autowired
    ChurchTimeTableServices churchTimeTableServices;

    @PostMapping("/add")
    public ResponseModel<ChurchTimeTableEntity> registerChurchTimeTableServices(@RequestBody ChurchTimeTableEntity churchTimeTableEntity){
        final ChurchTimeTableEntity savedTimeTable = churchTimeTableServices.registerChurchTimeTableEntity(churchTimeTableEntity);
        return new ResponseModel<>(HttpStatus.OK.value(), "Time Table registered successfully",savedTimeTable);
    }

    @PutMapping("/update")
    public ResponseModel<ChurchTimeTableEntity> updateUserMonthlyCollections(@RequestBody ChurchTimeTableEntity churchTimeTableEntity) {
        final ChurchTimeTableEntity updatedTimeTable = churchTimeTableServices.updateChurchTimeTableEntity(churchTimeTableEntity);
        return new ResponseModel<>(HttpStatus.OK.value(), "Time Table updated successfully", updatedTimeTable);
    }

    @GetMapping("/getAllTime")
    public ResponseModel<List<ChurchTimeTableEntity>> getAllChurchTimeTable(){
        final List<ChurchTimeTableEntity> savedTimeTables = churchTimeTableServices.getAllChurchTimeTableEntity();

        if(savedTimeTables.isEmpty()){
            return new ResponseModel<>(HttpStatus.NOT_FOUND.value(),"Time Table not found",savedTimeTables);
        }else{
            return new ResponseModel<>(HttpStatus.OK.value(), "Time Table found successfully",savedTimeTables);
        }
    }
}
