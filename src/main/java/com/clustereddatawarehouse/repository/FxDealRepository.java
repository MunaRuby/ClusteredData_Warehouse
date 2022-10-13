package com.clustereddatawarehouse.repository;

import com.clustereddatawarehouse.model.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, Long> {
    Optional<FxDeal> findByUniqueId(UUID uniqueId);
}
