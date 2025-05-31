package com.gametune.model;

import jakarta.persistence.*;

@Entity
public class HardwareProfile {

    @Id
    @GeneratedValue
    private Long id;
    private String name; //Name for hardware profile, e.g. name of user's PC or a specific hardware configuration
    private String cpu;

    @Enumerated(EnumType.STRING)
    private GpuVendor gpuVendor;
    private String gpuModel;
    private boolean supportsFrameGen;
    private int gpuMemory; //in GB

    private int ram; //in GB

    //Getters and Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public GpuVendor getGpuVendor() {
        return gpuVendor;
    }

    public void setGpuVendor(GpuVendor gpuVendor) {
        this.gpuVendor = gpuVendor;
    }

    public String getGpuModel() {
        return gpuModel;
    }

    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
    }

    public boolean supportsFrameGen() {
        return supportsFrameGen;
    }

    public void setSupportsFrameGen(boolean supportsFrameGen) {
        this.supportsFrameGen = supportsFrameGen;
    }

    public int getGpuMemory() {
        return gpuMemory;
    }

    public void setGpuMemory(int gpuMemory) {
        this.gpuMemory = gpuMemory;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }


}