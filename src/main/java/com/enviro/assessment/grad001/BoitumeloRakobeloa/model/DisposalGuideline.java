package com.enviro.assessment.grad001.BoitumeloRakobeloa.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @Column
    private String guidelines;

    // Constructors, getters, and setters
    public DisposalGuideline() {
    }

    public DisposalGuideline(Long id, String category, String guidelines) {
        this.id = id;
        this.category = category;
        this.guidelines = guidelines;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGuidelines(String guidelines) {
        this.guidelines = guidelines;
    }
}
