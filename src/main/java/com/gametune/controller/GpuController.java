package com.gametune.controller;

import com.gametune.model.hardware.Gpu;
import com.gametune.repository.GpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gpus")
public class GpuController {

    @Autowired
    private JdbcTemplate jdbc;

    private final GpuRepository gpuRepository;

    public GpuController(GpuRepository gpuRepository) {
        this.gpuRepository = gpuRepository;
    }

    @PostMapping
    public Gpu createGpu(@RequestBody Gpu gpu) {
        return gpuRepository.save(gpu);
    }

    @GetMapping
    public List<Gpu> getAllGpus() {
        return gpuRepository.findAll();
    }

    @DeleteMapping
    public void deleteAllGpus() {
        gpuRepository.deleteAll();
        jdbc.execute("ALTER SEQUENCE gpu_id_seq RESTART WITH 1");
    }

    @DeleteMapping
    public void deleteGpuById(@RequestParam Long id) {
        gpuRepository.deleteById(id);
    }
}