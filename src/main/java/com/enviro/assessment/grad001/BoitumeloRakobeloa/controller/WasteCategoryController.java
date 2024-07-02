package com.enviro.assessment.grad001.BoitumeloRakobeloa.controller;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.WasteCategory;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {
    @Autowired
    WasteCategoryRepository wasteCategoryRepository;

    @GetMapping("/getAllWasteCategorys")
    public ResponseEntity<List<WasteCategory>> getAllWasteCategorys() {
        try {
            List<WasteCategory> WasteCategoryList = new ArrayList<>();
            wasteCategoryRepository.findAll().forEach(WasteCategoryList::add);

            if (WasteCategoryList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(WasteCategoryList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getWasteCategoryById/{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        Optional<WasteCategory> WasteCategoryObj = wasteCategoryRepository.findById(id);
        if (WasteCategoryObj.isPresent()) {
            return new ResponseEntity<>(WasteCategoryObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addWasteCategory")
    public ResponseEntity<WasteCategory> addWasteCategory(@RequestBody WasteCategory WasteCategory) {
        try {
            WasteCategory WasteCategoryObj = wasteCategoryRepository.save(WasteCategory);
            return new ResponseEntity<>(WasteCategoryObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateWasteCategory/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(@PathVariable Long id, @RequestBody WasteCategory WasteCategory) {
        try {
            Optional<WasteCategory> WasteCategoryData = wasteCategoryRepository.findById(id);
            if (WasteCategoryData.isPresent()) {
                WasteCategory updatedWasteCategoryData = WasteCategoryData.get();
                // updatedWasteCategoryData.setGuideline(WasteCategory.getGuideline());
                // updatedWasteCategoryData.setAuthor(WasteCategory.getAuthor());

                WasteCategory WasteCategoryObj = wasteCategoryRepository.save(updatedWasteCategoryData);
                return new ResponseEntity<>(WasteCategoryObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteWasteCategoryById/{id}")
    public ResponseEntity<HttpStatus> deleteWasteCategory(@PathVariable Long id) {
        try {
            wasteCategoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllWasteCategorys")
    public ResponseEntity<HttpStatus> deleteAllWasteCategorys() {
        try {
            wasteCategoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

