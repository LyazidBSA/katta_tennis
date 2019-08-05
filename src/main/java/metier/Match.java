package metier;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import model.Player;

@Getter
@Setter
public class Match {
	
	private Player firstPlayer;
	private Player secondPlayer;
	private List<Set> sets;
	private String status; 
	
	public Match(Player firstPlayer, Player secondPlayer) {
		
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		
		System.out.println(this.toString());
	}
	
	
	public void start() {
		this.sets = new ArrayList<>();
		this.status = "in progress";
		while(this.status.equals("in progress")) {
			Set set = new Set(this);
			this.sets.add(set);
			Player winningPlayer = set.start();
			winningPlayer.setMatchScore(winningPlayer.getMatchScore()+1);
			
			if(winningPlayer.getMatchScore()>=3) {
				this.status = winningPlayer.getName() + " wins";
				System.out.println(this.toString());
			}
		}
	}


	@Override
	public String toString() {
		
		String score = null;
		
		if(this.sets!=null) {
			score = "";
			for(Set set : this.sets) {
				
				score = score + set.getScore();
			}
		}
		
		String s =  "_________________________\n"
				+ "Player1 :" + firstPlayer.getName() + "\n"
				+ "Player2 : " + secondPlayer.getName();
		
    	s = score != null ? s + "\nScore : " + score : s ;
		s = this.status != null ? s + "\nMatch Status : " + this.status : s;
		
		return s;
	}
	
	

}
