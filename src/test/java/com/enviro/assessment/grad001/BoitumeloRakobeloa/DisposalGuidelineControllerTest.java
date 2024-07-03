package com.enviro.assessment.grad001.BoitumeloRakobeloa;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.controller.DisposalGuidelineController;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.DisposalGuideline;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.DisposalGuidelineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
public class DisposalGuidelineControllerTest {
    @InjectMocks
    DisposalGuidelineController disposalGuidelineController;

    @Mock
    DisposalGuidelineRepository disposalGuidelineRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDisposalGuidelines() {
        List<DisposalGuideline> guidelines = new ArrayList<>();
        guidelines.add(new DisposalGuideline(1L, "Plastic", "Recycle"));

        when(disposalGuidelineRepository.findAll()).thenReturn(guidelines);

        ResponseEntity<List<DisposalGuideline>> response = disposalGuidelineController.getAllDisposalGuidelines();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

        when(disposalGuidelineRepository.findAll()).thenReturn(new ArrayList<>());
        response = disposalGuidelineController.getAllDisposalGuidelines();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetDisposalGuidelineById() {
        DisposalGuideline guideline = new DisposalGuideline(1L, "Plastic", "Recycle");

        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.of(guideline));
        ResponseEntity<DisposalGuideline> response = disposalGuidelineController.getDisposalGuidelineById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(guideline, response.getBody());

        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.empty());
        response = disposalGuidelineController.getDisposalGuidelineById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddDisposalGuideline() {
        DisposalGuideline guideline = new DisposalGuideline(1L, "Plastic", "Recycle");

        when(disposalGuidelineRepository.save(any(DisposalGuideline.class))).thenReturn(guideline);
        ResponseEntity<DisposalGuideline> response = disposalGuidelineController.addDisposalGuideline(guideline);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(guideline, response.getBody());
    }

    @Test
    public void testUpdateDisposalGuideline() {
        DisposalGuideline existingGuideline = new DisposalGuideline(1L, "Plastic", "Recycle");
        DisposalGuideline updatedGuideline = new DisposalGuideline(1L, "Plastic", "Do not litter");

        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.of(existingGuideline));
        when(disposalGuidelineRepository.save(any(DisposalGuideline.class))).thenReturn(updatedGuideline);

        ResponseEntity<DisposalGuideline> response = disposalGuidelineController.updateDisposalGuideline(1L, updatedGuideline);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedGuideline, response.getBody());

        when(disposalGuidelineRepository.findById(1L)).thenReturn(Optional.empty());
        response = disposalGuidelineController.updateDisposalGuideline(1L, updatedGuideline);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteDisposalGuidelineById() {
        doNothing().when(disposalGuidelineRepository).deleteById(1L);

        ResponseEntity<HttpStatus> response = disposalGuidelineController.deleteDisposalGuideline(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteAllDisposalGuidelines() {
        doNothing().when(disposalGuidelineRepository).deleteAll();

        ResponseEntity<HttpStatus> response = disposalGuidelineController.deleteAllDisposalGuidelines();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
