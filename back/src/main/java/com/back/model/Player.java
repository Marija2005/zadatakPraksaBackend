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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String playerName;
	
	@Column
	private int salary;
	
	@Column
	private String image;
	
	@OneToMany(mappedBy = "player" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Skills> skills = new ArrayList<>();
	
	//@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@ManyToOne
	private SportClub sportClub;
	
	public Player() {
		
	}

	public Player(Long id, String playerName, int salary, String image) {
		super();
		this.id = id;
		this.playerName = playerName;
		this.salary = salary;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public SportClub getSportClub() {
		if(sportClub != null) return sportClub;
		else return new SportClub(0, "N/A");
	}

	public void setSportClub(SportClub sportClub) {
		this.sportClub = sportClub;
	}
}
