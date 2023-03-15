package com.back.dto;

import java.util.List;

public class SportClubDTO {
	private Long id;
	private String name;
	private List<PlayerDTO> players;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PlayerDTO> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}
	
	
}
