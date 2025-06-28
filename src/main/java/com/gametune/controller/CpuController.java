package com.gametune.controller;

import com.gametune.model.hardware.Cpu;
import com.gametune.repository.CpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cpus")
public class CpuController {

    @Autowired
    private JdbcTemplate jdbc;

    private final CpuRepository cpuRepository;

    public CpuController(CpuRepository cpuRepository) {
        this.cpuRepository = cpuRepository;
    }

    @PostMapping
    public List<Cpu> createCpu(@RequestBody List<Cpu> cpus) {
        return cpuRepository.saveAll(cpus);
    }

    @GetMapping
    public List<Cpu> getAllCpus() {
        return cpuRepository.findAll();
    }

    @DeleteMapping
    public void deleteAllCpus() {
        cpuRepository.deleteAll();
        jdbc.execute("ALTER SEQUENCE cpu_id_seq RESTART WITH 1");
    }

    @DeleteMapping("/{id}")
    public void deleteCpuById(@PathVariable Long id) {
        cpuRepository.deleteById(id);
    }
}
