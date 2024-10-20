package com.scm.repositories;

import com.scm.entity.FlowerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerDetailRepository extends JpaRepository<FlowerDetail, Integer> {
    List<FlowerDetail> findByFlowerId(Integer id);
}
