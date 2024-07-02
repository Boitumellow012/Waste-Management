package com.enviro.assessment.grad001.BoitumeloRakobeloa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="RecyclingTips")
@Setter
@Getter
@ToString
public class RecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String category;

    @Column
    private String description;

    @Column
    private String guidelines;
}
