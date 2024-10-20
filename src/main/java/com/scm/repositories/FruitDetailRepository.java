package com.scm.repositories;

import com.scm.entity.FruitDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitDetailRepository extends JpaRepository<FruitDetail, Integer> {
    List<FruitDetail> findByFruitId(Integer id);
}
