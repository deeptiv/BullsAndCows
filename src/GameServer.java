import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class GameServer {

	/**
	 * Runs the application. Pairs up clients that connect.
	 */
	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(8901);
		try{
			WordMakerPlayer wordMakerPlayer = null;
			WordBreakerPlayer wordBreakerPlayer = null;
			SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
			Socket breakerSocket = null;
			Socket makerSocket = null;
			while(true){
				

				Socket clientSocket = serverSocket.accept();
				System.out.println("Server is Running");
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
				if(wordBreakerPlayer != null && wordBreakerPlayer.reStart){	
					
					System.out.println("resetting game");
					wordBreakerPlayer = null;
					wordMakerPlayer = null;
				//	makerSocket.close();
				//	breakerSocket.close();
				}

				String inputLine;
				inputLine = in.readLine();

				DbClient.getDBConnection();

				if(inputLine != null){
					System.out.println("inputline " + inputLine);
					if (inputLine.equals("maker")){
						makerSocket = clientSocket;
						System.out.println("maker trying to connect");
						wordMakerPlayer = new WordMakerPlayer(makerSocket);
						out.println("maker started");
					}
					else if (inputLine.equals("breaker")){
						breakerSocket = clientSocket;
						wordBreakerPlayer = new WordBreakerPlayer(breakerSocket);
						out.println("breaker started");
					}

				}
				if ( (wordMakerPlayer != null) && (wordBreakerPlayer != null)){
					System.out.println("opponents set");
					wordMakerPlayer.setOpponent(wordBreakerPlayer);
					wordBreakerPlayer.setOpponent(wordMakerPlayer);
					String gameId = gen.nextSessionId();
					wordBreakerPlayer.gameId = gameId;
					wordMakerPlayer.gameId = gameId;
					System.out.println("game id " + gameId);
					out.println("opponents set");	
					wordMakerPlayer.start();
					wordBreakerPlayer.start();
				}
			}
		} finally{
			serverSocket.close();
		}
	}

}