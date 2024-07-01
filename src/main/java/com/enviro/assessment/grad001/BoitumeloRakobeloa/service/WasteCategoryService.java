package com.enviro.assessment.grad001.BoitumeloRakobeloa.service;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.WasteCategory;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {
    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    public List<WasteCategory> getAllWasteCategories() {
        return wasteCategoryRepository.findAll();
    }

    public Optional<WasteCategory> getWasteCategoryById(Long id) {
        return wasteCategoryRepository.findById(id);
    }

    public WasteCategory createWasteCategory(WasteCategory wasteCategory) {
        return wasteCategoryRepository.save(wasteCategory);
    }

    public WasteCategory updateWasteCategory(Long id, WasteCategory wasteCategory) {
        Optional<WasteCategory> optionalWasteCategory = wasteCategoryRepository.findById(id);
        if (optionalWasteCategory.isPresent()) {
            WasteCategory existingWasteCategory = optionalWasteCategory.get();
            existingWasteCategory.setName(wasteCategory.getName());
            return wasteCategoryRepository.save(existingWasteCategory);
        } else {
            return null;
        }
    }

    public void deleteWasteCategory(Long id) {
        wasteCategoryRepository.deleteById(id);
    }
}
