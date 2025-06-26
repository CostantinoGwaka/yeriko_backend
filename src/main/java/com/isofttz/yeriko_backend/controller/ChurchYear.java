package com.isofttz.yeriko_backend.controller;


import com.isofttz.yeriko_backend.entities.ChurchYearEntity;
import com.isofttz.yeriko_backend.model.ResponseModel;
import com.isofttz.yeriko_backend.services.ChurchYearServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/churchYear")
public class ChurchYear {
    @Autowired
    ChurchYearServices churchYearServices;

    @PostMapping("/add")
    public ResponseModel<ChurchYearEntity> registerChurchYearEntity(@RequestBody ChurchYearEntity churchYear){
        final ChurchYearEntity year = churchYearServices.registerChurchYearEntity(churchYear);
        return new ResponseModel<>(HttpStatus.OK.value(), "Church Year registered successfully",year);
    }

    @PutMapping("/update")
    public ResponseModel<ChurchYearEntity> updateChurchYearEntity(@RequestBody ChurchYearEntity churchYear) {
        final ChurchYearEntity year = churchYearServices.updateChurchYearEntity(churchYear);
        return new ResponseModel<>(HttpStatus.OK.value(), "Church Year updated successfully", year);
    }

    @PostMapping("/deleteYear/{yearId}")
    public ResponseModel<Boolean> deleteUserBillsById(@PathVariable(name = "yearId") Long yearId){
        final Boolean deleted = churchYearServices.deleteUChurchYearEntityById(yearId);
        return new ResponseModel<>(HttpStatus.OK.value(), "Church Year deleted successfully", deleted);
    }


    @GetMapping("/getYearsById/{yearId}")
    public ResponseModel<ChurchYearEntity> findUserBillsById(@PathVariable(name = "yearId") Long yearId){
        final ChurchYearEntity year = churchYearServices.findChurchYearEntityById(yearId);
        return new ResponseModel<>(HttpStatus.OK.value(), "Church Year found successfully", year);
    }

    @GetMapping("/getActiveYear")
    public ResponseModel<ChurchYearEntity> findActiveChurchYearEntity(){
        final ChurchYearEntity year = churchYearServices.getActiveChurchYearEntity();
        return new ResponseModel<>(HttpStatus.OK.value(), "Church Year found successfully", year);
    }
}
