package com.back.contoller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.dto.PlayerDTO;
import com.back.dto.SportClubDTO;
import com.back.model.Player;
import com.back.model.SportClub;
import com.back.service.ClubService;
import com.back.service.PlayerService;
import com.back.support.ClubDTOToClub;
import com.back.support.ClubToClubDTO;
import com.back.support.PlayerDTOToPlayer;
import com.back.support.PlayerToPlayerDTO;

@RestController
@RequestMapping(value="/api/clubs", produces=MediaType.APPLICATION_JSON_VALUE)
public class ClubController {
	
	@Autowired
	private ClubService clubService;
	@Autowired
	private ClubDTOToClub toClub;
	@Autowired
	private ClubToClubDTO toClubDto;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private PlayerToPlayerDTO toPlayerDto;
	@Autowired
	private PlayerDTOToPlayer toPlayer;
	
	@GetMapping
	public ResponseEntity<List<SportClubDTO>> getAll() {
		List<SportClub> clubs = clubService.findAll();
		return new ResponseEntity<>(toClubDto.convert(clubs), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SportClubDTO> getOne(@PathVariable Long id) {
		SportClub club = clubService.findOneById(id);
		
		if (club != null) {
			return new ResponseEntity<>(toClubDto.convert(club), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@PreAuthorize("hasRole('ROLE_EDITOR')")
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SportClubDTO> update(@PathVariable Long id, @Valid @RequestBody SportClubDTO clubDTO) {
		if(!id.equals(clubDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		SportClub club = toClub.convert(clubDTO);
		SportClub saveClub = clubService.save(club);
		
		return new ResponseEntity<>(toClubDto.convert(saveClub), HttpStatus.OK);
	}
	
	/*@PutMapping(value="//{id}/removePlayer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerDTO> removePlayerFromClub(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDTO) {
		
	}*/
	
	//@PreAuthorize("hasRole('ROLE_EDITOR')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SportClubDTO> create(@Valid @RequestBody SportClubDTO clubDTO) {
		
		/*List<Player> players = new ArrayList<>();
		List<PlayerDTO> playerDTOs = new ArrayList<>();
		Player player = new Player();
		for (PlayerDTO pl: playerDTOs) {
			player.setId(pl.getId());
			player.setImage(pl.getImage());
			player.setPlayerName(pl.getPlayerName());
			player.setSalary(pl.getSalary());
			players.add(player);
		}*/
		
		SportClub club = toClub.convert(clubDTO);
		SportClub saveClub = clubService.save(club);
		
		return new ResponseEntity<>(toClubDto.convert(saveClub), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		SportClub deleteClub = clubService.delete(id);
		if(deleteClub != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
