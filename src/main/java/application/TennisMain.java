package application;

import metier.Match;
import model.Player;

public class TennisMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         System.out.println(" *******   Katta Tennis ********  ");
         
         Player player1 = new Player("player1");
         Player player2 = new Player("player2");
         
         Match match = new Match(player1,player2);
         match.start();
	}

}
