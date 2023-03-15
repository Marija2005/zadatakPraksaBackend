package com.back.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.SkillsDTO;
import com.back.model.Skills;


@Component
public class SkillsToSkillsDTO implements Converter<Skills, SkillsDTO>{

	@Override
	public SkillsDTO convert(Skills skills) {
		SkillsDTO dto = new SkillsDTO();
		dto.setId(skills.getId());
		dto.setDescription(skills.getDescription());
		dto.setName(skills.getName());
		dto.setPlayerId(skills.getPlayer().getId());
		dto.setPlayerName(skills.getPlayer().getPlayerName());
		return dto;
	}
	
	public List<SkillsDTO> convert(List<Skills> skills) {
		List<SkillsDTO> skillsDTOs = new ArrayList<>();
		
		for(Skills s: skills) {
			SkillsDTO dto = convert(s);
			skillsDTOs.add(dto);
		}
		return skillsDTOs;
	}

}
