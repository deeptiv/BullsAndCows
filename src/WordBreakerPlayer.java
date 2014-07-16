import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class WordBreakerPlayer extends Player{

	public WordBreakerPlayer(Socket socket) {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	public WordBreakerPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String wordBreakerString;

	public static int MAX_NUMBER_OF_TRIES = 12;

	public String wordBreakerDisplayString;

	public String getWordBreakerString() {
		return wordBreakerString;
	}

	public void setWordBreakerString(String wordBreakerString) {
		this.wordBreakerString = wordBreakerString;
	}

	String appendTowordBreakerDisplayString(String str){
		String returnString = wordBreakerDisplayString.concat(str);
		return returnString;
	}

	public void run() {
		try {
			int numTrials = 0;
			boolean gameOver = false;
			// The thread is only started after everyone connects.
			//	output.println("Word Breaker Player");

			// Tell the first player that it is her turn.


			output.println("Word Breaker Player guess the four letter dictionary word:\n\n");

			// Repeatedly get commands from the client and process them.
			while (true) {


				CountBullsAndCows countBullsAndCows = new CountBullsAndCows();
				//countBullsAndCows.initializeMakerHash(wordMakerPlayer);

				//System.out.println("Word Breaker Player guess the four letter dictionary word: ");
				while (numTrials < WordBreakerPlayer.MAX_NUMBER_OF_TRIES && !gameOver){
					//output.println("your guess");
					//String breakerString = scan.nextLine();
					String breakerString = null;
					String command = input.readLine();
					if (command.startsWith("BreakerInput")) {
						breakerString = command.substring(12);


					}
					if(Utilities.checkInputStringLenghtAndValidDictWord(breakerString)) 
					{	
						System.out.println("received breaker string " + breakerString);
						this.setWordBreakerString(breakerString);
						//						WordMakerPlayer wm = (WordMakerPlayer)this.getOpponent();
						//						System.out.println("maker string" + wm.getWordMakerString());

						BullsAndCows bullsAndCows = countBullsAndCows.countBullsAndCows( (WordMakerPlayer)(this.getOpponent()),this);

						gameOver = Game.isGameOver(bullsAndCows);

						output.println( GameController.displayMessageForPlayer(this, bullsAndCows, gameOver)  );
						this.opponent.output.println(GameController.displayMessageForPlayer(this, bullsAndCows, gameOver) );

						numTrials ++;

						if(numTrials >= WordBreakerPlayer.MAX_NUMBER_OF_TRIES){
							output.println("max number of trials reached!!");
							this.opponent.output.println("max number of trials reached!!");
						}
					}
//					else{
//						output.println("please input a four letter valid dictionary word");
//					}
				}


			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {socket.close();} catch (IOException e) {}
		}
	}



}
