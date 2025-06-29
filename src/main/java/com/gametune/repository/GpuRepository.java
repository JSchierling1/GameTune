package com.gametune.repository;

import com.gametune.model.Game;
import com.gametune.model.hardware.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GpuRepository extends JpaRepository<Gpu, Long> {
    Optional<Gpu> findByModel(String model);
}