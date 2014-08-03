import java.io.IOException;
import java.net.Socket;

import com.mongodb.BasicDBObject;


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


			// Tell the player that it is her turn.


			output.println("Word Breaker Player guess the four letter dictionary word:\n\n");
			boolean run = true;
			// Repeatedly get commands from the client and process them.
			while (run) {


				CountBullsAndCows countBullsAndCows = new CountBullsAndCows();

				while (numTrials < WordBreakerPlayer.MAX_NUMBER_OF_TRIES && !gameOver && run ){
					//	output.println("your guess");
					String breakerString = null;
					String command = input.readLine();
					if(command != null){
						if (command.startsWith("BreakerInput")) {
							breakerString = command.substring(12);
						} else if(command.equals("quit")){
							System.out.println("breaker received quit");
							run = false;
							this.reStart = true;
						}
						if(Utilities.checkInputStringLenghtAndValidDictWord(breakerString)) 
						{	
							System.out.println("received breaker string " + breakerString);
							this.setWordBreakerString(breakerString);
							BullsAndCows bullsAndCows = countBullsAndCows.countBullsAndCows( (WordMakerPlayer)(this.getOpponent()),this);
							gameOver = Game.isGameOver(bullsAndCows);

							BasicDBObject doc = new BasicDBObject("player", "breaker")
							.append("breakerWord", breakerString)
							.append("bulls", bullsAndCows.getBulls())
							.append("cows", bullsAndCows.getCows())
							.append("gameId",this.gameId );
							DbClient.insertInTestCollection(doc);

							output.println( GameController.displayMessageForPlayer(this, bullsAndCows, gameOver)  );
							this.opponent.output.println(GameController.displayMessageForPlayer(this, bullsAndCows, gameOver) );

							numTrials ++;

							if(numTrials >= WordBreakerPlayer.MAX_NUMBER_OF_TRIES){
								output.println("max number of trials reached!!");
								this.opponent.output.println("max number of trials reached!!");
							}
						}
					}
					//					else{
					//						output.println("please input a four letter valid dictionary word");
					//					}
				}
				run = false;
				this.reStart = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//try {socket.close();} catch (IOException e) {}
		}
	}



}
