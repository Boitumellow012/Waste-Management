package com.enviro.assessment.grad001.BoitumeloRakobeloa.model;

import jakarta.persistence.*;

@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

}
