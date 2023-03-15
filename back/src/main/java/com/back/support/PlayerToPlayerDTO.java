package com.back.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.PlayerDTO;
import com.back.model.Player;
import com.back.model.Skills;

@Component
public class PlayerToPlayerDTO implements Converter<Player, PlayerDTO>{

	@Autowired
	private SkillsToSkillsDTO toSkillsDto;
	
	@Override
	public PlayerDTO convert(Player player) {
		PlayerDTO dto = new PlayerDTO();
		dto.setId(player.getId());
		dto.setImage(player.getImage());
		dto.setPlayerName(player.getPlayerName());
		dto.setSalary(player.getSalary());
		if(player.getSportClub().getId() == null) {
			dto.setSportClubId((long) 1);
		} else {
			dto.setSportClubId(player.getSportClub().getId());
		}
		dto.setSportClubName(player.getSportClub().getName());
		List<Skills> skills = new ArrayList<>(player.getSkills());
		dto.setSkills(new ArrayList<>(toSkillsDto.convert(skills)));
		return dto;
	}
	
	public List<PlayerDTO> convert(List<Player> players) {
		List<PlayerDTO> dtos = new ArrayList<>();
		
		for(Player p : players) {
			PlayerDTO dto = convert(p);
			dtos.add(dto);
		}
		return dtos;
	}

}
