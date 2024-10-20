package com.scm.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FruitDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fruit_id")
    private Fruit fruit;

    private String month;
    private String growthStage;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String details;

    // Getters and setters
    // ...
}
