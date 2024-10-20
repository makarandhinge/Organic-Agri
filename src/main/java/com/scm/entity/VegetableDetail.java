package com.scm.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VegetableDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vegetable_id")
    private Vegetable vegetable;

    private String month;
    private String growthStage;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String details;

    // Getters and setters
    // ...
}
