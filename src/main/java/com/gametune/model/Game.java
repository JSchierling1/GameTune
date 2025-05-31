package com.gametune.model;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String publisher;

    @Enumerated(EnumType.STRING)
    private GameGenre genre;
    private boolean supportsDLSS;
    private boolean supportsFSR;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cpu", column = @Column(name = "min_cpu")),
            @AttributeOverride(name = "gpuModel", column = @Column(name = "min_gpu")),
            @AttributeOverride(name = "ram", column = @Column(name = "min_ram"))
    })
    private HardwareRequirement minRequirements;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cpu", column = @Column(name = "rec_cpu")),
            @AttributeOverride(name = "gpuModel", column = @Column(name = "rec_gpu")),
            @AttributeOverride(name = "ram", column = @Column(name = "rec_ram"))
    })
    private HardwareRequirement recRequirements;

    //Getters and Setters
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameGenre getGenre() {
        return genre;
    }

    public void setGenre(GameGenre genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setSupportsDLSS(boolean supportsDLSS) {
        this.supportsDLSS = supportsDLSS;
    }

    public boolean getSupportsDLSS() {
        return supportsDLSS;
    }

    public void setSupportsFSR(boolean supportsFSR) {
        this.supportsFSR = supportsFSR;
    }

    public boolean getSupportsFSR() {
        return supportsFSR;
    }

    public HardwareRequirement getMinRequirements() {
        return minRequirements;
    }

    public void setMinRequirements(HardwareRequirement minRequirements) {
        this.minRequirements = minRequirements;
    }

    public HardwareRequirement getRecRequirements() {
        return recRequirements;
    }

    public void setRecRequirements(HardwareRequirement recRequirements) {
        this.recRequirements = recRequirements;
    }
}
