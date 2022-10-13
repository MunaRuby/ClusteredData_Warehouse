package com.clustereddatawarehouse.repository;

import com.clustereddatawarehouse.model.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, Long> {
}
