package com.back.service;

import java.util.List;

import com.back.model.Skills;


public interface SkillsService {
	List<Skills> findAll();
	Skills findOneById(Long id);
	Skills save(Skills skills);
	Skills delete(Long id);
	Skills update(Skills skills);
	List<Skills> findByPlayerId(Long playerId);
}
