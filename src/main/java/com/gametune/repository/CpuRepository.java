package com.gametune.repository;

import com.gametune.model.Game;
import com.gametune.model.hardware.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CpuRepository extends JpaRepository<Cpu, Long> {
    Optional<Cpu> findByModel(String model);
}