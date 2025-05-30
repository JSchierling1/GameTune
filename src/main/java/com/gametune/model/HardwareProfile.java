package com.gametune.model;

import jakarta.persistence.*;

@Entity
public class HardwareProfile {

    @Id
    @GeneratedValue
    private Long id;

    private String cpu;

    @Enumerated(EnumType.STRING)
    private GpuVendor gpuVendor;
    private String gpuModel;
    private boolean supportsFrameGen;
    private int gpuMemory; //in GB

    private int ram; //in GB
}