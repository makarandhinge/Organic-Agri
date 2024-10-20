package com.scm.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FlowerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;

    private String month;
    private String growthStage;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String details;

    // Getters and setters
    // ...
}
