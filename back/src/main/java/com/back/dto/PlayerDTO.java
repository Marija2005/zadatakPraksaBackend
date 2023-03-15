package com.back.dto;

import java.util.List;

public class PlayerDTO {
	private Long id;
	private String playerName;
	private int salary;
	private String image;
	private Long sportClubId;
	private String sportClubName;
	private List<SkillsDTO> skills;
	
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
	public Long getSportClubId() {
		return sportClubId;
	}
	public void setSportClubId(Long sportClubId) {
		this.sportClubId = sportClubId;
	}
	public String getSportClubName() {
		return sportClubName;
	}
	public void setSportClubName(String sportClubName) {
		this.sportClubName = sportClubName;
	}
	public List<SkillsDTO> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillsDTO> skills) {
		this.skills = skills;
	}
	
}
