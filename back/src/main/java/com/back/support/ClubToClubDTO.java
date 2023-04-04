package com.back.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.SportClubDTO;
import com.back.model.Player;
import com.back.model.SportClub;

@Component
public class ClubToClubDTO implements Converter<SportClub, SportClubDTO>{

	@Autowired
	private PlayerToPlayerDTO toPlayerDto;
	
	@Override
	public SportClubDTO convert(SportClub club) {
		SportClubDTO dto = new SportClubDTO();
		dto.setId(club.getId());
		dto.setName(club.getName());
        List<Player> players = new ArrayList<>(club.getPlayers());
        dto.setPlayers(new ArrayList<>(toPlayerDto.convert(players)));
		return dto;
	}
	
	public List<SportClubDTO> convert(List<SportClub> clubs) {
		List<SportClubDTO> clubDTOs = new ArrayList<>();
		for(SportClub s: clubs) {
			SportClubDTO dto = convert(s);
			clubDTOs.add(dto);
		}
		return clubDTOs;
	}

}
