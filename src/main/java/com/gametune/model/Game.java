package com.gametune.model;

import com.gametune.model.hardware.HardwareProfile;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "min_profile_id")
    private HardwareProfile minRequirements;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rec_profile_id")
    private HardwareProfile recRequirements;

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

    public HardwareProfile getMinRequirements() {
        return minRequirements;
    }

    public void setMinRequirements(HardwareProfile minRequirements) {
        this.minRequirements = minRequirements;
    }

    public HardwareProfile getRecRequirements() {
        return recRequirements;
    }

    public void setRecRequirements(HardwareProfile recRequirements) {
        this.recRequirements = recRequirements;
    }
}
