package com.gametune.model.hardware;

import jakarta.persistence.*;

@Entity
public class Gpu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    @Enumerated(EnumType.STRING)
    private GpuVendor vendor;
    private int vram; // VRAM in GB
    private int score; // Performance score based on benchmarks
    private boolean supportsFrameGen; // Whether the GPU supports frame generation technologies like DLSS or FSR

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public GpuVendor getVendor() {
        return vendor;
    }

    public void setVendor(GpuVendor vendor) {
        this.vendor = vendor;
    }

    public int getVram() {
        return vram;
    }

    public void setVram(int vram) {
        this.vram = vram;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSupportsFrameGen() {
        return supportsFrameGen;
    }

    public void setSupportsFrameGen(boolean supportsFrameGen) {
        this.supportsFrameGen = supportsFrameGen;
    }
}
