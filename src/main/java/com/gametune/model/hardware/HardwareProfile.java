package com.gametune.model.hardware;

import jakarta.persistence.*;

@Entity
public class HardwareProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //Name for hardware profile, e.g. name of user's PC or a specific hardware configuration

    @ManyToOne
    private Cpu cpu; // CPU object, which contains vendor, model, cores, threads, etc.

    @ManyToOne
    private Gpu gpu; // GPU object, which contains vendor, model, memory, etc.

    private int ram; //in GB

    //Getters and Setters
    protected void setId(Long id) {
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

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Transient
    public int getScore(){
        return cpu.getScore() + gpu.getScore();
    }


}