package references;

import lombok.Getter;

@Getter
public enum GameStatus {
	
	
	DEUCE("deuce"),
	ADVANTAGE("advantage"),
	END("end"),
	OTHER("other");

	private String status; 
	
	GameStatus(String status){
		this.status = status;
	}
}
