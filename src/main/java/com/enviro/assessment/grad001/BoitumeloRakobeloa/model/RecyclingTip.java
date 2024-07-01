package com.enviro.assessment.grad001.BoitumeloRakobeloa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Entity
public class RecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String category;

    @NotNull
    private String tip;

    // Constructors, getters, and setters
    public RecyclingTip() {
    }

    public RecyclingTip(Long id, String category, String tip) {
        this.id = id;
        this.category = category;
        this.tip = tip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
