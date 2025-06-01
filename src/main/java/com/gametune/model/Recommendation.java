package com.gametune.model;

import com.gametune.model.hardware.HardwareProfile;
import jakarta.persistence.*;

@Entity
public class Recommendation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToOne
    private HardwareProfile hardware;

    @Enumerated(EnumType.STRING)
    private Quality quality; //Setting like "Low", "Medium", "High", "Ultra". Later support for custom settings can be added.
    private String resolution;
    private String frameGen;
    private int estimatedFps;

    public boolean isPlayable(HardwareProfile hardwareProfile, Game game){
        //Whether the game is playable on this hardware profile with the given settings
        boolean playable;
        if (hardwareProfile.getGpu().getScore()>= game.getMinRequirements().getGpu().getScore() && hardwareProfile.getCpu().getScore()>= game.getMinRequirements().getCpu().getScore() && hardwareProfile.getRam() >= game.getMinRequirements().getRam()) {
            playable = true;
        } else {
            playable = false;
        }
        return playable;
    }

    public boolean recRequirementsMet(HardwareProfile hardwareProfile, Game game) {
        //Whether the game is playable on this hardware profile with the given settings
        boolean recRequirementsMet;
        if (hardwareProfile.getGpu().getScore()>= game.getRecRequirements().getGpu().getScore() && hardwareProfile.getCpu().getScore()>= game.getRecRequirements().getCpu().getScore() && hardwareProfile.getRam() >= game.getRecRequirements().getRam()) {
            recRequirementsMet = true;
        } else {
            recRequirementsMet = false;
        }
        return recRequirementsMet;
    }



}
