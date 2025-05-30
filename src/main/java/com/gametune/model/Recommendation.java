package com.gametune.model;

import jakarta.persistence.*;

@Entity
public class Recommendation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToOne
    private HardwareProfile hardware;

    @Enumerated(EnumType.STRING)
    private Quality quality; //Setting like "Low", "Medium", "High", "Ultra". Later support for custom settings can be added.
    private String resolution;
    private String frameGen;
    private int estimatedFps;
}
