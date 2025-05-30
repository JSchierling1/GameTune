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
            @AttributeOverride(name = "gpu", column = @Column(name = "min_gpu")),
            @AttributeOverride(name = "ram", column = @Column(name = "min_ram"))
    })
    private HardwareRequirement minRequirements;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cpu", column = @Column(name = "rec_cpu")),
            @AttributeOverride(name = "gpu", column = @Column(name = "rec_gpu")),
            @AttributeOverride(name = "ram", column = @Column(name = "rec_ram"))
    })
    private HardwareRequirement recRequirements;
}
