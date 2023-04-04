package com.back.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.model.Player;
import com.back.repository.PlayerRepository;
import com.back.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	@Override
	public Player findOneById(Long id) {
		return playerRepository.findOneById(id);
	}

	@Override
	public Player save(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Player update(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Player delete(Long id) {
		Optional<Player> player = playerRepository.findById(id);
		if(player.isPresent()) {
			playerRepository.deleteById(id);
			return player.get();
		}
		return null;
	}

	@Override
	public List<Player> findByClubId(Long clubId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public List<Player> findByClubId(Long clubId) {
		return playerRepository.findBySportClubId(clubId);
	}*/

}
