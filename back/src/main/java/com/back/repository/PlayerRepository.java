package com.back.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.model.Player;
import com.back.model.SportClub;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	Player findOneById(Long id);
	Collection<Player> findBySportClub(SportClub club);
}
