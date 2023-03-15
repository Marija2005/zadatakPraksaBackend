package com.back.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.model.Player;
import com.back.model.SportClub;
import com.back.repository.ClubRepository;
import com.back.repository.PlayerRepository;
import com.back.service.ClubService;

@Service 
public class ClubServiceImpl implements ClubService {
	
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public List<SportClub> findAll() {
		return clubRepository.findAll();
	}

	@Override
	public SportClub save(SportClub sportClub) {
		return clubRepository.save(sportClub);
	}

	@Override
	public SportClub delete(Long id) {
		Optional<SportClub> sportClub = clubRepository.findById(id);
		if(sportClub.isPresent()) {
			clubRepository.deleteById(id);
			return sportClub.get();
		}
		return null;
	}

	@Override
	public SportClub findOneById(Long id) {
		return clubRepository.findOneById(id);
	}

	@Override
	public Optional<SportClub> findOne(Long id) {
		return clubRepository.findById(id);
	}

	@Override
	public SportClub update(SportClub sportClub) {
		return clubRepository.save(sportClub);
	}

	/*@Override
	public Player removePlayerFromClub(Player player) {
		return clubRepository.removePlayerFromClub(player);
	}*/
}
