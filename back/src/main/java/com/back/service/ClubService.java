package com.back.service;

import java.util.List;
import java.util.Optional;

import com.back.model.SportClub;

public interface ClubService {
	List<SportClub> findAll();
	SportClub save(SportClub sportClub);
	SportClub delete(Long id);
	SportClub findOneById(Long id);
	Optional<SportClub> findOne(Long id);
	SportClub update(SportClub sportClub);
	//Player removePlayerFromClub(Player player);
}
