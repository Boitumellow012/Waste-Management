package com.enviro.assessment.grad001.BoitumeloRakobeloa;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.controller.WasteCategoryController;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.WasteCategory;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.WasteCategoryRepository;
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
public class WasteCategoryControllerTest {
    @InjectMocks
    WasteCategoryController wasteCategoryController;

    @Mock
    WasteCategoryRepository wasteCategoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWasteCategories() {
        List<WasteCategory> categories = new ArrayList<>();
        categories.add(new WasteCategory(1L, "Plastic", "Plastic materials"));

        when(wasteCategoryRepository.findAll()).thenReturn(categories);

        ResponseEntity<List<WasteCategory>> response = wasteCategoryController.getAllWasteCategorys();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

        when(wasteCategoryRepository.findAll()).thenReturn(new ArrayList<>());
        response = wasteCategoryController.getAllWasteCategorys();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetWasteCategoryById() {
        WasteCategory category = new WasteCategory(1L, "Plastic", "Plastic materials");

        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.of(category));
        ResponseEntity<WasteCategory> response = wasteCategoryController.getWasteCategoryById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());

        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.empty());
        response = wasteCategoryController.getWasteCategoryById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddWasteCategory() {
        WasteCategory category = new WasteCategory(1L, "Plastic", "Plastic materials");

        when(wasteCategoryRepository.save(any(WasteCategory.class))).thenReturn(category);
        ResponseEntity<WasteCategory> response = wasteCategoryController.addWasteCategory(category);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testUpdateWasteCategory() {
        WasteCategory existingCategory = new WasteCategory(1L, "Plastic", "Plastic materials");
        WasteCategory updatedCategory = new WasteCategory(1L, "Plastic", "Updated description");

        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(wasteCategoryRepository.save(any(WasteCategory.class))).thenReturn(updatedCategory);

        ResponseEntity<WasteCategory> response = wasteCategoryController.updateWasteCategory(1L, updatedCategory);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedCategory, response.getBody());

        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.empty());
        response = wasteCategoryController.updateWasteCategory(1L, updatedCategory);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteWasteCategoryById() {
        doNothing().when(wasteCategoryRepository).deleteById(1L);

        ResponseEntity<HttpStatus> response = wasteCategoryController.deleteWasteCategory(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteAllWasteCategories() {
        doNothing().when(wasteCategoryRepository).deleteAll();

        ResponseEntity<HttpStatus> response = wasteCategoryController.deleteAllWasteCategorys();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
