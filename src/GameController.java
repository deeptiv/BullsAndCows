import java.util.Scanner;


public class GameController {

	public String playerInputString;

	public static String displayStringToPlayer = "";

	void startNewGame(){

		int numTrials = 0;
		boolean gameOver = false;

		WordMakerPlayer wordMakerPlayer = new WordMakerPlayer();
		WordBreakerPlayer wordBreakerPlayer = new WordBreakerPlayer();

		System.out.println("Word Maker Player input four letter dictionary word: ");
		Scanner scan = new Scanner(System.in);
		String makerString = scan.nextLine();
		if (!Utilities.checkInputStringLenghtAndValidDictWord(makerString)){
			scan.close();
			return;
		};
		wordMakerPlayer.setWordMakerString(makerString);

		CountBullsAndCows countBullsAndCows = new CountBullsAndCows();

		System.out.println("Word Breaker Player guess the four letter dictionary word: ");
		while (numTrials < WordBreakerPlayer.MAX_NUMBER_OF_TRIES && !gameOver){
			String breakerString = scan.nextLine();
			if(Utilities.checkInputStringLenghtAndValidDictWord(breakerString)) 
			{	
				wordBreakerPlayer.setWordBreakerString(breakerString);


				BullsAndCows bullsAndCows = countBullsAndCows.countBullsAndCows(wordMakerPlayer, wordBreakerPlayer);

				gameOver = isGameOver(bullsAndCows);

				System.out.println(displayMessageForPlayer(wordBreakerPlayer, bullsAndCows, gameOver));

				numTrials ++;
			}
		}

		scan.close();

	}

	public boolean isGameOver(BullsAndCows bullsAndCows){
		if (bullsAndCows.getBulls() == 4) 
			return true;
		else return false;
	}

	public static String displayMessageForPlayer(WordBreakerPlayer wordBreakerPlayer, BullsAndCows bullsAndCows, Boolean gameOver){
		String currentDisplay = wordBreakerPlayer.getWordBreakerString();

		String toDisplay= currentDisplay.concat(" Bulls: " + bullsAndCows.getBulls() + " Cows: " + bullsAndCows.getCows());

		if(gameOver){
			toDisplay = toDisplay.concat(" You Won!!");
		}

		return toDisplay;
	}

	public static void main(String[] args){
		GameController gc = new GameController();
		gc.startNewGame();
	}

}
