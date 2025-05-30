package com.gametune.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class HardwareRequirement {

    private String cpu;
    private String gpu;
    private int ram;

    public HardwareRequirement() {}

    public HardwareRequirement(String cpu, String gpu, int ram) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
    }

    // Getter und Setter
}
