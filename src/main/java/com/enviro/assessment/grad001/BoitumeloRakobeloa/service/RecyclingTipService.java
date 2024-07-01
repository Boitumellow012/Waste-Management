package com.enviro.assessment.grad001.BoitumeloRakobeloa.service;

import com.enviro.assessment.grad001.BoitumeloRakobeloa.model.RecyclingTip;
import com.enviro.assessment.grad001.BoitumeloRakobeloa.repo.RecyclingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipService {
    @Autowired
    private RecyclingTipRepository recyclingTipRepository;

    public List<RecyclingTip> getAllRecyclingTips() {
        return recyclingTipRepository.findAll();
    }

    public Optional<RecyclingTip> getRecyclingTipById(Long id) {
        return recyclingTipRepository.findById(id);
    }

    public RecyclingTip createRecyclingTip(RecyclingTip recyclingTip) {
        return recyclingTipRepository.save(recyclingTip);
    }

    public Optional<RecyclingTip> updateRecyclingTip(Long id, RecyclingTip updatedRecyclingTip) {
        Optional<RecyclingTip> existingTipOptional = recyclingTipRepository.findById(id);
        if (existingTipOptional.isPresent()) {
            RecyclingTip existingTip = existingTipOptional.get();
            existingTip.setCategory(updatedRecyclingTip.getCategory());
            existingTip.setTip(updatedRecyclingTip.getTip());
            return Optional.of(recyclingTipRepository.save(existingTip));
        } else {
            return Optional.empty();
        }
    }

    public void deleteRecyclingTip(Long id) {
        recyclingTipRepository.deleteById(id);
    }
}
