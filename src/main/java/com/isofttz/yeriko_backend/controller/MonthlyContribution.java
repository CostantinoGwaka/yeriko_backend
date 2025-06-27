package com.isofttz.yeriko_backend.controller;


import com.isofttz.yeriko_backend.entities.MonthlyCollectionsEntity;
import com.isofttz.yeriko_backend.entities.TotalSummaryDTO;
import com.isofttz.yeriko_backend.model.ResponseModel;
import com.isofttz.yeriko_backend.services.MonthlyCollectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monthly")
public class MonthlyContribution {

    @Autowired
    MonthlyCollectionServices monthlyCollectionServices;

    @PostMapping("/add")
    public ResponseModel<MonthlyCollectionsEntity> registerUserMonthlyCollections(@RequestBody MonthlyCollectionsEntity monthlyCollectionsEntity){
        final MonthlyCollectionsEntity savedMonthlyCollection = monthlyCollectionServices.registerUserMonthlyCollectionsEntity(monthlyCollectionsEntity);
        return new ResponseModel<>(HttpStatus.OK.value(), "Monthly Collections registered successfully",savedMonthlyCollection);
    }

    @PutMapping("/update")
    public ResponseModel<MonthlyCollectionsEntity> updateUserMonthlyCollections(@RequestBody MonthlyCollectionsEntity monthlyCollectionsEntity) {
        final MonthlyCollectionsEntity updatedExpenses = monthlyCollectionServices.updateUserMonthlyCollectionsEntity(monthlyCollectionsEntity);
        return new ResponseModel<>(HttpStatus.OK.value(), "Monthly Collections updated successfully", updatedExpenses);
    }

    @GetMapping("/getMonthly/{userId}")
    public ResponseModel<List<MonthlyCollectionsEntity>> getUserMonthlyCollections(@PathVariable(name = "userId") Long userId){
        final List<MonthlyCollectionsEntity> savedMonthlyCollectionsUpdated = monthlyCollectionServices.getAllMonthlyCollectionsEntityByUserId(userId);

        if(savedMonthlyCollectionsUpdated.isEmpty()){
            return new ResponseModel<>(HttpStatus.NOT_FOUND.value(),"Monthly Collections not found",savedMonthlyCollectionsUpdated);
        }else{
            return new ResponseModel<>(HttpStatus.OK.value(), "Monthly Collections found successfully",savedMonthlyCollectionsUpdated);
        }
    }

    @GetMapping("/total/{userId}/{year}")
    public ResponseEntity<ResponseModel<List<TotalSummaryDTO>>> getTotalByUser(@PathVariable String userId,@PathVariable String year) {
        int totalMonthly = monthlyCollectionServices.getTotalMonthlyCollectionsEntityByUserId(userId);
        int totalMonthlyByCurrent = monthlyCollectionServices.getTotalIncomeByUserIdAndCurrentYear(userId,year);


        List<TotalSummaryDTO> totals = List.of(
                new TotalSummaryDTO("totalMonthly", totalMonthly),
                new TotalSummaryDTO("totalMonthlyByCurrent", totalMonthlyByCurrent)
        );

        ResponseModel<List<TotalSummaryDTO>> response = new ResponseModel<>(
                HttpStatus.OK.value(),
                "User totals fetched successfully",
                totals
        );
        return ResponseEntity.ok(response);
    }
}
