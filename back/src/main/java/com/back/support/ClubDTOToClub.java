package com.back.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.SportClubDTO;
import com.back.model.SportClub;
import com.back.service.ClubService;
import com.back.service.PlayerService;

@Component
public class ClubDTOToClub implements Converter<SportClubDTO, SportClub>{
	
	@Autowired
	private ClubService clubService;
	@Autowired
	private PlayerService playerService;
	
	@Override
	public SportClub convert(SportClubDTO dto) {
		SportClub club = null;
		if(dto.getId() == null) {
			club = new SportClub();
		} else {
			Optional<SportClub> cl = clubService.findOne(dto.getId());
			if(cl.isPresent()) {
				club = cl.get();
			}
		}
		
		if(club != null) {
			club.setId(dto.getId());
			club.setName(dto.getName());
			club.setPlayers(playerService.findByClubId(dto.getId()));
		}
		return club;
	}

}
