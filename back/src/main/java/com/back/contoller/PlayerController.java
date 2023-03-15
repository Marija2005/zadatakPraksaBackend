package com.back.contoller;

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
import com.back.dto.SkillsDTO;
import com.back.model.Player;
import com.back.model.Skills;
import com.back.service.PlayerService;
import com.back.service.SkillsService;
import com.back.support.PlayerDTOToPlayer;
import com.back.support.PlayerToPlayerDTO;
import com.back.support.SkillsToSkillsDTO;

@RestController
@RequestMapping(value="/api/players", produces=MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	@Autowired
	private PlayerDTOToPlayer toPlayer;
	@Autowired
	private PlayerToPlayerDTO toPlayerDto;
	@Autowired
	private SkillsService skillsService;
	@Autowired
	private SkillsToSkillsDTO toSkillsDto;
	
	//@PreAuthorize("hasRole('ROLE_EDITOR')")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerDTO> create(@Valid @RequestBody PlayerDTO playerDTO) {
		Player player = toPlayer.convert(playerDTO);
		Player savePlayer = playerService.save(player);
		
		return new ResponseEntity<>(toPlayerDto.convert(savePlayer), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PlayerDTO>> getAll() {
		List<Player> players = playerService.findAll();
		return new ResponseEntity<>(toPlayerDto.convert(players),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlayerDTO> findOne(@PathVariable Long id) {
		Player player = playerService.findOneById(id);
		if(player != null) {
			return new ResponseEntity<>(toPlayerDto.convert(player), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@PreAuthorize("hasRole('ROLE_EDITOR')")
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDTO) {
		Player player = toPlayer.convert(playerDTO);
		Player savePlayer = playerService.save(player);
		
		return new ResponseEntity<>(toPlayerDto.convert(savePlayer), HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('ROLE_EDITOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		Player deletePlayer = playerService.delete(id);
		if( deletePlayer != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
