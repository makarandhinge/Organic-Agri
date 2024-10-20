package com.scm.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GrainDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grain_id")
    private Grain grain;

    private String month;
    private String growthStage;
    @Lob
    @Column(columnDefinition = "TEXT")  // Explicitly set column type if needed
    private String details; //// Use @Lob annotation for TEXT or LONGTEXT fields

    // Getters and setters
    // ...
}

