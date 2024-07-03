package com.enviro.assessment.grad001.BoitumeloRakobeloa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="WasteCategorys")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class WasteCategory {
       @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;
}
