package com.back.contoller;

import java.util.List;

import javax.validation.Valid;

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

import com.back.dto.SkillsDTO;
import com.back.model.Skills;
import com.back.service.SkillsService;
import com.back.support.SkillsDTOToSkills;
import com.back.support.SkillsToSkillsDTO;

@RestController
@RequestMapping(value="/api/skills", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillsController {
	
	@Autowired
	private SkillsService skillsService;
	@Autowired
	private SkillsDTOToSkills toSkills;
	@Autowired
	private SkillsToSkillsDTO toSkillsDto;
	
	@GetMapping
	public ResponseEntity<List<SkillsDTO>> getAll() {
		List<Skills> skills = skillsService.findAll();
		return new ResponseEntity<>(toSkillsDto.convert(skills), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SkillsDTO> getOne(@PathVariable Long id) {
		Skills skill = skillsService.findOneById(id);
		if (skill != null) {
			return new ResponseEntity<>(toSkillsDto.convert(skill), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SkillsDTO> create(@Valid @RequestBody SkillsDTO skillsDTO) {
		Skills skills = toSkills.convert(skillsDTO);
		Skills saveSkills = skillsService.save(skills);
		
		return new ResponseEntity<>(toSkillsDto.convert(saveSkills), HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasRole('ROLE_EDITOR')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SkillsDTO> update(@PathVariable Long id, @Valid @RequestBody SkillsDTO skillsDTO) {
		if(!id.equals(skillsDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Skills skills = toSkills.convert(skillsDTO);
		Skills saveSkills = skillsService.save(skills);
		
		return new ResponseEntity<>(toSkillsDto.convert(saveSkills), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		Skills deleteSkill = skillsService.delete(id);
		if(deleteSkill != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
