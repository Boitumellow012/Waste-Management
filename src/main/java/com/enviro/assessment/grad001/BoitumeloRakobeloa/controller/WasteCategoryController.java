package com.enviro.assessment.grad001.BoitumeloRakobeloa.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.WasteCategory;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.WasteCategoryRepository;

@RestController
@RequestMapping("/api")
public class WasteCategoryController {

    @Autowired
    WasteCategoryRepository WasteCategoryRepository;

    @GetMapping("/getAllWasteCategorys")
    public ResponseEntity<List<WasteCategory>> getAllWasteCategorys() {
        try {
            List<WasteCategory> WasteCategoryList = new ArrayList<>();
            WasteCategoryRepository.findAll().forEach(WasteCategoryList::add);

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
        Optional<WasteCategory> WasteCategoryObj = WasteCategoryRepository.findById(id);
        if (WasteCategoryObj.isPresent()) {
            return new ResponseEntity<>(WasteCategoryObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addWasteCategory")
    public ResponseEntity<WasteCategory> addWasteCategory(@RequestBody WasteCategory WasteCategory) {
        try {
            WasteCategory WasteCategoryObj = WasteCategoryRepository.save(WasteCategory);
            return new ResponseEntity<>(WasteCategoryObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }           

    @PostMapping("/updateWasteCategory/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(@PathVariable Long id, @RequestBody WasteCategory WasteCategory) {
        try {
            Optional<WasteCategory> WasteCategoryData = WasteCategoryRepository.findById(id);
            if (WasteCategoryData.isPresent()) {
                WasteCategory updatedWasteCategoryData = WasteCategoryData.get();
               // updatedWasteCategoryData.setGuideline(WasteCategory.getGuideline());
               // updatedWasteCategoryData.setAuthor(WasteCategory.getAuthor());

                WasteCategory WasteCategoryObj = WasteCategoryRepository.save(updatedWasteCategoryData);
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
            WasteCategoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllWasteCategorys")
    public ResponseEntity<HttpStatus> deleteAllWasteCategorys() {
        try {
            WasteCategoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


