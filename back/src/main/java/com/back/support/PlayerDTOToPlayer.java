package com.back.support;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.PlayerDTO;
import com.back.model.Player;
import com.back.service.ClubService;
import com.back.service.PlayerService;
import com.back.service.SkillsService;

@Component
public class PlayerDTOToPlayer implements Converter<PlayerDTO, Player>{
	
	@Autowired
	private PlayerService playerService;
	@Autowired
	private ClubService sportClubService;
	@Autowired
	private SkillsService skillsService;
	
	@Override
	public Player convert(PlayerDTO dto) {
		Player player = null;
		if(dto.getId()==null) {
			player = new Player();
		} else {
			player = playerService.findOneById(dto.getId());
		}
		
		if(player!=null) {
			player.setId(dto.getId());
			player.setImage(dto.getImage());
			player.setPlayerName(dto.getPlayerName());
			player.setSalary(dto.getSalary());
			player.setSportClub(sportClubService.findOneById(dto.getSportClubId()));
			player.setSkills(skillsService.findByPlayerId(dto.getId()));
		}
		return player;
	}
	
	/*public List<Player> convert(List<PlayerDTO> dtos) {
		List<Player> newList = new ArrayList<>();
		for (PlayerDTO dto : dtos) {
			Player player = new Player(
					dto.getId(),
					dto.getPlayerName(),
					dto.getSalary(),
					dto.getImage()
					);
			newList.add(player);
			
		}
		return newList;
	}*/
}
