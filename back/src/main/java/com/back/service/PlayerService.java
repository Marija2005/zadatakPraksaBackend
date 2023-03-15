package com.back.service;

import java.util.List;

import com.back.model.Player;

public interface PlayerService {
	List<Player> findAll();
	Player findOneById(Long id);
	Player save(Player player);
	Player update(Player player);
	Player delete(Long id);
	List<Player> findByClubId(Long clubId);
}
