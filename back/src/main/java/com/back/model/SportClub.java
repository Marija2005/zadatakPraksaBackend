package com.back.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SportClub {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "sportClub" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Player> players = new ArrayList<>();
	
	public SportClub() {}
	
	public SportClub(long id, String name) {
		this.id= id;
		this.name = name;
		//this.players = new List<Player>();
	}

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

	public List<Player> getPlayers() {
		if(players != null)return players;
		else return new ArrayList<Player>();
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	
}
