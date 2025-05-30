package com.gametune.repository;

import com.gametune.model.HardwareProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareRepository extends JpaRepository<HardwareProfile, Long> {}
