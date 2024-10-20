package com.scm.repositories;

import com.scm.entity.Grain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrainRepository extends JpaRepository<Grain,Integer> {
}
