package com.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	Player findOneById(Long id);
	List<Player> findBySportClubId(Long id);
}
