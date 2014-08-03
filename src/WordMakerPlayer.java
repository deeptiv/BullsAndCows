import java.io.IOException;
import java.net.Socket;

import com.mongodb.BasicDBObject;


public class WordMakerPlayer extends Player{

	public WordMakerPlayer(Socket socket) {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	public WordMakerPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String wordMakerString;

	public String getWordMakerString() {
		return wordMakerString;
	}

	public void setWordMakerString(String wordMakerString) {
		this.wordMakerString = wordMakerString;
	}

	public void run() {
		try {
			// The thread is only started after both players connect.
			output.println("Maker connected");

			// Tell the player that it is her turn.

			output.println("input a four letter word");
			boolean run = true;
			// Repeatedly get commands from the client and process them.
			while (run) {
				String makerString = null;
				String command = input.readLine();
				if(command != null){
					if (command.startsWith("MakerInput")) {
						makerString = command.substring(10);

						BasicDBObject doc = new BasicDBObject("player", "maker")
						.append("makerWord", makerString)
						.append("gameId",this.gameId );
						DbClient.insertInTestCollection(doc);

						this.setWordMakerString(makerString);
						System.out.println("received maker string :"+ makerString);
						output.println("Your word " + makerString + ".Opponent's guesses:\n" );
					}else if(command.equals("quit")){
						run = false;
					}
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
