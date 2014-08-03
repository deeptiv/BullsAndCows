
/**
 * A two-player game.
 */
class Game {
    Player wordMakerPlayer;
	Player wordBreakerPlayer;
    String gameId;

    public Player getWordMakerPlayer() {
		return wordMakerPlayer;
	}
	public void setWordMakerPlayer(Player wordMakerPlayer) {
		this.wordMakerPlayer = wordMakerPlayer;
	}
	public Player getWordBreakerPlayer() {
		return wordBreakerPlayer;
	}
	public void setWordBreakerPlayer(Player wordBreakerPlayer) {
		this.wordBreakerPlayer = wordBreakerPlayer;
	}

	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}



    /**
     * Returns whether the current state of the game
     */
    public static boolean isGameOver(BullsAndCows bullsAndCows){
		if (bullsAndCows.getBulls() == 4) 
			return true;
		else return false;
	}

    

    /**
     *  This method checks to see if the input is legal
     */
    public synchronized boolean legalInput(String inputString) {
    	if (Utilities.checkInputStringLenghtAndValidDictWord(inputString)){
			return true;
		};
        return false;
    }
}
