package com.gametune.model;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
