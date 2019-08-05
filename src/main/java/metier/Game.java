package metier;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import model.Player;
import references.GameStatus;

@Getter
@Setter
public class Game {
	
	private String status;
	private Set set;
	
	
	public Game(Set set) {
		
		this.set = set;
		this.status = GameStatus.OTHER.getStatus();
		

		//reset players set values 
		
		this.set.getMatch().getFirstPlayer().setAdvantagePlayer(false);
		this.set.getMatch().getSecondPlayer().setAdvantagePlayer(false);
		
		this.set.getMatch().getFirstPlayer().setCurrentGameScore(0);
		this.set.getMatch().getSecondPlayer().setCurrentGameScore(0);
		
	}
	
	
	public Player standardGameStart() {
		Player winningPlayer = null;
		while(!this.status.equals(GameStatus.END.getStatus())) {
			String status = this.status;
			winningPlayer = this.getRandomWinPlayer();
			// if is a Deuce state
			if(this.status.equals(GameStatus.DEUCE.getStatus())) {
				winningPlayer.setAdvantagePlayer(true);
				status = GameStatus.ADVANTAGE.getStatus();
				
			}
			//if is a advantage state 
			if(this.status.equals(GameStatus.ADVANTAGE.getStatus())) {
				//if the winPlayer has advantage
				if(winningPlayer.isAdvantagePlayer()) {
					status = GameStatus.END.getStatus();
				}
				
				//if the player has lost the advantage
				if(!winningPlayer.isAdvantagePlayer()) {
					status = GameStatus.DEUCE.getStatus();
					this.set.getMatch().getFirstPlayer().setAdvantagePlayer(false);
					this.set.getMatch().getSecondPlayer().setAdvantagePlayer(false);
				}
			}
				
			if(this.status.equals(GameStatus.OTHER.getStatus())) {
				Player lostingPlayer = this.getLostPlayer(winningPlayer);
				if(winningPlayer.getCurrentGameScore()==40) {
					//if the player wins the game
					if(lostingPlayer.getCurrentGameScore()<=30) {
						status = GameStatus.END.getStatus();
					}
					
					if(lostingPlayer.getCurrentGameScore()==40 ) {
						status = GameStatus.DEUCE.getStatus();
					}
				}
				
				if(winningPlayer.getCurrentGameScore()<40) {
					int newScore = winningPlayer.getCurrentGameScore()>=30 ? 40 : winningPlayer.getCurrentGameScore()+15;
					winningPlayer.setCurrentGameScore(newScore);
					
					if(lostingPlayer.getCurrentGameScore() == 40 && winningPlayer.getCurrentGameScore() == 40 ) {
						status = GameStatus.DEUCE.getStatus();
					}
				}

			}
			this.status = status;
			
			if(!this.status.equals(GameStatus.END.getStatus()))
			  System.out.println(this.toString());
			
		}
		
		
		
		return winningPlayer;

	}
	
	public Player tieBreakGameStart() {
		boolean end = false;
		Player winningPlayer = null;
		while(!end) {
			winningPlayer = this.getRandomWinPlayer();
			Player lostingPlayer = this.getLostPlayer(winningPlayer);
			winningPlayer.setCurrentGameScore(winningPlayer.getCurrentGameScore()+1);
			
			//
			if((winningPlayer.getCurrentGameScore()>=7) && (winningPlayer.getCurrentGameScore()-lostingPlayer.getCurrentGameScore())>=2) {
				end = true;
			}	
		}
		
		return winningPlayer;
	}
	
	
	
	private Player getRandomWinPlayer() {
		int  r = new Random().nextInt(2);
		return r==0 ? this.set.getMatch().getFirstPlayer() : this.set.getMatch().getSecondPlayer(); 
	}
	
	
	
	private Player getLostPlayer(Player winPlayer) {
		if(winPlayer.equals(this.set.getMatch().getFirstPlayer()))
			return this.set.getMatch().getSecondPlayer();
		
		return this.set.getMatch().getFirstPlayer();
		
	}
	
	
	
	public void setStatus(GameStatus status) {
		this.status = status.getStatus();
	}
	
	
	public String toString() {
		String s = "";
		
		String status = this.status;
		if(status.equals(GameStatus.OTHER.getStatus())){
			status = this.getSet().getMatch().getFirstPlayer().getCurrentGameScore()+ "-" +this.getSet().getMatch().getSecondPlayer().getCurrentGameScore();
		}
		if(this.set.getMatch().getSets()!=null)
			for(Set set : this.set.getMatch().getSets()) {
				s = s + set.getScore();
			}
		
		return "_________________________\n"
			+"Player  1 : "+ this.set.getMatch().getFirstPlayer().getName() + "\n"
			+"Player  2 : " +this.set.getMatch().getSecondPlayer().getName() + "\n"
			+"Score : " + s +"\n"
			+"Current game status : " + status+ "\n"
			+"Match Status : "+ this.set.getMatch().getStatus() +"";
	}
	
	
	
	

}
