package com.back.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.SkillsDTO;
import com.back.model.Skills;
import com.back.service.PlayerService;
import com.back.service.SkillsService;

@Component
public class SkillsDTOToSkills implements Converter<SkillsDTO, Skills>{
	
	@Autowired
	private SkillsService skillsService;
	@Autowired
	private PlayerService playerService;
	
	@Override
	public Skills convert(SkillsDTO dto) {
		Skills skills = null;
		if(dto.getId() == null) {
			skills = new Skills();
		} else {
			skills = skillsService.findOneById(dto.getId());
		}
		if(skills != null) {
			skills.setId(dto.getId());
			skills.setDescription(dto.getDescription());
			skills.setName(dto.getName());
			skills.setPlayer(playerService.findOneById(dto.getPlayerId()));
		}
		return skills;
	}

}
