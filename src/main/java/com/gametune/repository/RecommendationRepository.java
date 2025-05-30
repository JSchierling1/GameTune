package com.gametune.repository;

import com.gametune.model.Recommendation;
import com.gametune.model.Game;
import com.gametune.model.HardwareProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Optional<Recommendation> findByHardwareAndGame(HardwareProfile hardware, Game game);
}
