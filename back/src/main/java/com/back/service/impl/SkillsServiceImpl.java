package com.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.model.Skills;
import com.back.repository.SkillsRepository;
import com.back.service.SkillsService;

@Service
public class SkillsServiceImpl implements SkillsService{
	
	@Autowired
	private SkillsRepository skillsRepository;
	
	@Override
	public List<Skills> findAll() {
		return skillsRepository.findAll();
	}

	@Override
	public Skills findOneById(Long id) {
		return skillsRepository.findOneById(id);
	}

	@Override
	public Skills save(Skills skills) {
		return skillsRepository.save(skills);
	}

	@Override
	public Skills delete(Long id) {
		Skills skills = skillsRepository.findOneById(id);
		if(skills != null) {
			skillsRepository.delete(skills);
		}
		return null;
	}

	@Override
	public Skills update(Skills skills) {
		return skillsRepository.save(skills);
	}

	@Override
	public List<Skills> findByPlayerId(Long playerId) {
		return skillsRepository.findByPlayerId(playerId);
	}

}
