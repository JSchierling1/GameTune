package com.gametune.model;

import jakarta.persistence.*;

@Entity
public class HardwareProfile {
    @Id
    @GeneratedValue
    private Long id;

    private String cpu;
    private String gpu;
    private int ram;
}
