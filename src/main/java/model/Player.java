package model;

import lombok.Getter;
import lombok.Setter;
import metier.Person;
import references.GameStatus;

@Getter
@Setter
public class Player extends Person {

	private int currentGameScore;
	private int currentSetScore;
	private boolean isAdvantagePlayer;
	private int matchScore;
	
	public Player(String name) {
		super(name);
		this.currentGameScore = 0;
		this.currentSetScore = 0;
		this.matchScore = 0;
	}
	
	public void reset() {
		
	}
		
}
