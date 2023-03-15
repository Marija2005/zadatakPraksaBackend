package com.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.model.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long>{
	Skills findOneById(Long id);
	List<Skills> findByPlayerId(Long playerId);
}
