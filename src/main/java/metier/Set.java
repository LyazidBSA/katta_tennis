package metier;

import lombok.Getter;
import lombok.Setter;
import model.Player;


@Getter
@Setter
public class Set {

	
	private Match match;
	private String score;
	private boolean isInProgressSet;
	
	public Set(Match match) {
		
		this.match = match;
		this.isInProgressSet = true;
		
		//reset players set values 
		this.match.getFirstPlayer().setCurrentSetScore(0);
		this.match.getSecondPlayer().setCurrentSetScore(0);
		
		this.score = "("+ this.match.getFirstPlayer().getCurrentSetScore()+"-"+this.match.getSecondPlayer().getCurrentSetScore()+")";
	}
	
	public Player start() {
		
		Player winningPlayer = null;
		Player lostingPlayer = null;
		while(isInProgressSet) {
			Game game = new Game(this);
			winningPlayer = game.standardGameStart();
			lostingPlayer = this.getLostingPlayer(winningPlayer);
			
			winningPlayer.setCurrentSetScore(winningPlayer.getCurrentSetScore()+1);
			
			if(winningPlayer.getCurrentSetScore() >= 6) {
				if(winningPlayer.getCurrentSetScore()-lostingPlayer.getCurrentSetScore()>=2)
					this.isInProgressSet = false;
				
				if(lostingPlayer.getCurrentSetScore() == 6) {
					game = new Game(this);
					winningPlayer = game.tieBreakGameStart();
					winningPlayer.setCurrentSetScore(winningPlayer.getCurrentSetScore()+1);
					this.isInProgressSet = false;
				}
				
			}
			
			
			this.score = "("+ this.match.getFirstPlayer().getCurrentSetScore()+"-"+this.match.getSecondPlayer().getCurrentSetScore()+")";

			
			
	
		}
		return winningPlayer;
	}
	
	private Player getLostingPlayer(Player winningPlayer) {
		if(winningPlayer.equals(this.match.getFirstPlayer()))
			return this.match.getSecondPlayer();
		
		return this.match.getFirstPlayer();
		
	}
	
}
