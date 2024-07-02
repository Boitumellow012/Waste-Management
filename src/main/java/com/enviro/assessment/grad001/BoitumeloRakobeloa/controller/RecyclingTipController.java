package com.enviro.assessment.grad001.BoitumeloRakobeloa.controller;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.RecyclingTip;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.RecyclingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipController {
    @Autowired
    RecyclingTipRepository recyclingTipRepository;

    @GetMapping("/getAllRecyclingTips")
    public ResponseEntity<List<RecyclingTip>> getAllRecyclingTips() {
        try {
            List<RecyclingTip> RecyclingTipList = new ArrayList<>();
            recyclingTipRepository.findAll().forEach(RecyclingTipList::add);

            if (RecyclingTipList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(RecyclingTipList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRecyclingTipById/{id}")
    public ResponseEntity<RecyclingTip> getRecyclingTipById(@PathVariable Long id) {
        Optional<RecyclingTip> RecyclingTipObj = recyclingTipRepository.findById(id);
        if (RecyclingTipObj.isPresent()) {
            return new ResponseEntity<>(RecyclingTipObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addRecyclingTip")
    public ResponseEntity<RecyclingTip> addRecyclingTip(@RequestBody RecyclingTip RecyclingTip) {
        try {
            RecyclingTip RecyclingTipObj = recyclingTipRepository.save(RecyclingTip);
            return new ResponseEntity<>(RecyclingTipObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateRecyclingTip/{id}")
    public ResponseEntity<RecyclingTip> updateRecyclingTip(@PathVariable Long id, @RequestBody RecyclingTip RecyclingTip) {
        try {
            Optional<RecyclingTip> RecyclingTipData = recyclingTipRepository.findById(id);
            if (RecyclingTipData.isPresent()) {
                RecyclingTip updatedRecyclingTipData = RecyclingTipData.get();
                // updatedRecyclingTipData.setGuideline(RecyclingTip.getGuideline());
                // updatedRecyclingTipData.setAuthor(RecyclingTip.getAuthor());

                RecyclingTip RecyclingTipObj = recyclingTipRepository.save(updatedRecyclingTipData);
                return new ResponseEntity<>(RecyclingTipObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteRecyclingTipById/{id}")
    public ResponseEntity<HttpStatus> deleteRecyclingTip(@PathVariable Long id) {
        try {
            recyclingTipRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllRecyclingTips")
    public ResponseEntity<HttpStatus> deleteAllRecyclingTips() {
        try {
            recyclingTipRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
