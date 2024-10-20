package com.scm.repositories;

import com.scm.entity.GrainDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrainDetailRepository extends JpaRepository<GrainDetail,Integer> {
    List<GrainDetail> findByGrainId(Integer id);
}
