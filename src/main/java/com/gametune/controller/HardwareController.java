package com.gametune.controller;

import org.springframework.web.bind.annotation.*;
import com.gametune.model.hardware.HardwareProfile;
import com.gametune.repository.HardwareRepository;

import java.util.List;

@RestController
@RequestMapping("/api/hardware")
public class HardwareController {
    private final HardwareRepository hardwareRepository;

    public HardwareController(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @GetMapping
    public List<HardwareProfile> getAllProfiles() {
        return hardwareRepository.findAll();
    }
}
