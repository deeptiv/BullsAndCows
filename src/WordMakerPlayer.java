import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


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
			// The thread is only started after everyone connects.
			output.println("Maker connected");

			// Tell the first player that it is her turn.

			output.println("input a four letter word");


			// Repeatedly get commands from the client and process them.
			while (true) {
//				Scanner scan = new Scanner(System.in);
//				String playerInput = scan.nextLine();
				String makerString = null;
				String command = input.readLine();
				if (command.startsWith("MakerInput")) {
					makerString = command.substring(10);
					
					this.setWordMakerString(makerString);
					System.out.println("received maker string :"+ makerString);
					output.println("Your word " + makerString + ".Opponent's guesses:\n" );
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
