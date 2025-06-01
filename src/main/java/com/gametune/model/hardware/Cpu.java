package com.gametune.model.hardware;

import jakarta.persistence.*;

@Entity
public class Cpu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    @Enumerated(EnumType.STRING)
    private CpuVendor vendor;
    //private int cores;
    //private int threads;
    //private float baseClock; // Base clock speed in GHz
    private int score; // Performance score based on benchmarks

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

    public CpuVendor getVendor() {
        return vendor;
    }

    public void setVendor(CpuVendor vendor) {
        this.vendor = vendor;
    }

//    public int getCores() {
//        return cores;
//    }
//
//    public void setCores(int cores) {
//        this.cores = cores;
//    }
//
//    public int getThreads() {
//        return threads;
//    }
//
//    public void setThreads(int threads) {
//        this.threads = threads;
//    }
//
//    public float getBaseClock() {
//        return baseClock;
//    }
//
//    public void setBaseClock(float baseClock) {
//        this.baseClock = baseClock;
//    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
