package com.enviro.assessment.grad001.BoitumeloRakobeloa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="DisposalGuidelines")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DisposalGuideline {
       @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String category;

    @Column
    private String guideline;
}
