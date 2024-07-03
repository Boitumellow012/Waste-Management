package com.enviro.assessment.grad001.BoitumeloRakobeloa;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.RecyclingTipRepository;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.controller.RecyclingTipController;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.RecyclingTip;
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
public class RecyclingTipControllerTest {
    @InjectMocks
    RecyclingTipController recyclingTipController;

    @Mock
    RecyclingTipRepository recyclingTipRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRecyclingTips() {
        List<RecyclingTip> tips = new ArrayList<>();
        tips.add(new RecyclingTip(1L, "Plastic", "Recycle plastic bottles"));

        when(recyclingTipRepository.findAll()).thenReturn(tips);

        ResponseEntity<List<RecyclingTip>> response = recyclingTipController.getAllRecyclingTips();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

        when(recyclingTipRepository.findAll()).thenReturn(new ArrayList<>());
        response = recyclingTipController.getAllRecyclingTips();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetRecyclingTipById() {
        RecyclingTip tip = new RecyclingTip(1L, "Plastic", "Recycle plastic bottles");

        when(recyclingTipRepository.findById(1L)).thenReturn(Optional.of(tip));
        ResponseEntity<RecyclingTip> response = recyclingTipController.getRecyclingTipById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tip, response.getBody());

        when(recyclingTipRepository.findById(1L)).thenReturn(Optional.empty());
        response = recyclingTipController.getRecyclingTipById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddRecyclingTip() {
        RecyclingTip tip = new RecyclingTip(1L, "Plastic", "Recycle plastic bottles");

        when(recyclingTipRepository.save(any(RecyclingTip.class))).thenReturn(tip);
        ResponseEntity<RecyclingTip> response = recyclingTipController.addRecyclingTip(tip);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tip, response.getBody());
    }

    @Test
    public void testUpdateRecyclingTip() {
        RecyclingTip existingTip = new RecyclingTip(1L, "Plastic", "Recycle plastic bottles");
        RecyclingTip updatedTip = new RecyclingTip(1L, "Plastic", "Rinse plastic bottles before recycling");

        when(recyclingTipRepository.findById(1L)).thenReturn(Optional.of(existingTip));
        when(recyclingTipRepository.save(any(RecyclingTip.class))).thenReturn(updatedTip);

        ResponseEntity<RecyclingTip> response = recyclingTipController.updateRecyclingTip(1L, updatedTip);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedTip, response.getBody());

        when(recyclingTipRepository.findById(1L)).thenReturn(Optional.empty());
        response = recyclingTipController.updateRecyclingTip(1L, updatedTip);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteRecyclingTipById() {
        doNothing().when(recyclingTipRepository).deleteById(1L);

        ResponseEntity<HttpStatus> response = recyclingTipController.deleteRecyclingTip(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteAllRecyclingTips() {
        doNothing().when(recyclingTipRepository).deleteAll();

        ResponseEntity<HttpStatus> response = recyclingTipController.deleteAllRecyclingTips();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
