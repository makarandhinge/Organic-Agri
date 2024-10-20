package com.scm.repositories;

import com.scm.entity.VegetableDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetableDetailRepository extends JpaRepository<VegetableDetail, Integer> {
    List<VegetableDetail> findByVegetableId(Integer id);
}
