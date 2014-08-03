import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Player extends Thread{

	public String name;
	Socket socket;
	String inputString;
	BufferedReader input;
	PrintWriter output;
	Player opponent;
	String gameId;
	Boolean reStart = false;

	/**
	 * Constructs a handler thread for a given socket and mark
	 * initializes the stream fields, displays the first two
	 * welcoming messages.
	 */
	public Player(Socket socket) {
		this.socket = socket;
		try {
			input = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
			output.println("WELCOME ");
			output.println("Waiting for opponent to connect");
		} catch (IOException e) {
			System.out.println("Player died: " + e);
		}
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * sets the opponents
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public Player getOpponent(){
		return this.opponent;
	}

}
