package com.enviro.assessment.grad001.BoitumeloRakobeloa.controller;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.DisposalGuidelines;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.service.DisposalGuidelinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelinesController {
    @Autowired
    private DisposalGuidelinesService disposalGuidelinesService;

    @GetMapping
    public ResponseEntity<List<DisposalGuidelines>> getAllDisposalGuidelines() {
        List<DisposalGuidelines> disposalGuidelines = disposalGuidelinesService.getAllDisposalGuidelines();
        return ResponseEntity.ok(disposalGuidelines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuidelines> getDisposalGuidelinesById(@PathVariable Long id) {
        Optional<DisposalGuidelines> disposalGuidelines = disposalGuidelinesService.getDisposalGuidelinesById(id);
        return disposalGuidelines.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisposalGuidelines> createDisposalGuidelines(@Valid @RequestBody DisposalGuidelines disposalGuidelines) {
        DisposalGuidelines createdDisposalGuidelines = disposalGuidelinesService.createDisposalGuidelines(disposalGuidelines);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDisposalGuidelines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuidelines> updateDisposalGuidelines(@PathVariable Long id, @Valid @RequestBody DisposalGuidelines updatedDisposalGuidelines) {
        Optional<DisposalGuidelines> updatedGuidelines = disposalGuidelinesService.updateDisposalGuidelines(id, updatedDisposalGuidelines);
        return updatedGuidelines.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisposalGuidelines(@PathVariable Long id) {
        disposalGuidelinesService.deleteDisposalGuidelines(id);
        return ResponseEntity.noContent().build();
    }
}
