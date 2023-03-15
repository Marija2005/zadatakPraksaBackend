package com.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.model.Player;
import com.back.model.SportClub;


@Repository
public interface ClubRepository extends JpaRepository<SportClub, Long>{
	SportClub findOneById(Long id);
	//Player removePlayerFromClub(Player player);
}
