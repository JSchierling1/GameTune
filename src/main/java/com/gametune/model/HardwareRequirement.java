package com.gametune.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class HardwareRequirement {

    private String cpu;
    private String gpuModel;
    private int ram;

    public HardwareRequirement() {}

    public HardwareRequirement(String cpu, String gpuModel, int ram) {
        this.cpu = cpu;
        this.gpuModel = gpuModel;
        this.ram = ram;
    }

    // Getters and Setters
    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpuModel;
    }

    public void setGpu(String gpuModel) {
        this.gpuModel = gpuModel;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
