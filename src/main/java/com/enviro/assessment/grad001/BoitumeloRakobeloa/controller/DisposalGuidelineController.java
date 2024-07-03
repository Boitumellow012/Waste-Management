
package com.enviro.assessment.grad001.BoitumeloRakobeloa.controller;


import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.DisposalGuideline;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.DisposalGuidelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DisposalGuidelineController {

    @Autowired
    DisposalGuidelineRepository DisposalGuidelineRepository;

    @GetMapping("/getAllDisposalGuidelines")
    public ResponseEntity<List<DisposalGuideline>> getAllDisposalGuidelines() {
        try {
            List<DisposalGuideline> DisposalGuidelineList = new ArrayList<>();
            DisposalGuidelineRepository.findAll().forEach(DisposalGuidelineList::add);

            if (DisposalGuidelineList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(DisposalGuidelineList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDisposalGuidelineById/{id}")
    public ResponseEntity<DisposalGuideline> getDisposalGuidelineById(@PathVariable Long id) {
        Optional<DisposalGuideline> DisposalGuidelineObj = DisposalGuidelineRepository.findById(id);
        if (DisposalGuidelineObj.isPresent()) {
            return new ResponseEntity<>(DisposalGuidelineObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addDisposalGuideline")
    public ResponseEntity<DisposalGuideline> addDisposalGuideline(@RequestBody DisposalGuideline DisposalGuideline) {
        try {
            DisposalGuideline DisposalGuidelineObj = DisposalGuidelineRepository.save(DisposalGuideline);
            return new ResponseEntity<>(DisposalGuidelineObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }           

    @PostMapping("/updateDisposalGuideline/{id}")
    public ResponseEntity<DisposalGuideline> updateDisposalGuideline(@PathVariable Long id, @RequestBody DisposalGuideline DisposalGuideline) {
        try {
            Optional<DisposalGuideline> DisposalGuidelineData = DisposalGuidelineRepository.findById(id);
            if (DisposalGuidelineData.isPresent()) {
                DisposalGuideline updatedDisposalGuidelineData = DisposalGuidelineData.get();
               // updatedDisposalGuidelineData.setGuideline(DisposalGuideline.getGuideline());
               // updatedDisposalGuidelineData.setAuthor(DisposalGuideline.getAuthor());

                DisposalGuideline DisposalGuidelineObj = DisposalGuidelineRepository.save(updatedDisposalGuidelineData);
                return new ResponseEntity<>(DisposalGuidelineObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteDisposalGuidelineById/{id}")
    public ResponseEntity<HttpStatus> deleteDisposalGuideline(@PathVariable Long id) {
        try {
            DisposalGuidelineRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllDisposalGuidelines")
    public ResponseEntity<HttpStatus> deleteAllDisposalGuidelines() {
        try {
            DisposalGuidelineRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


