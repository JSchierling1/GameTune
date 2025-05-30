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

    private String quality;
    private String resolution;
    private String dlss;
    private int estimatedFps;
}
