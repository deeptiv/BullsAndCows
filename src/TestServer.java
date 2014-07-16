import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class TestServer {

	/**
	 * Runs the application. Pairs up clients that connect.
	 */
	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(8901);
		try{
			WordMakerPlayer wordMakerPlayer = null ;
			WordBreakerPlayer wordBreakerPlayer = null;

			while(true){
				Socket clientSocket = serverSocket.accept();
				System.out.println("Test Server is Running");
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;
				inputLine = in.readLine();
				System.out.println("inputLine is "+ inputLine);

				if(inputLine != null){
					if (inputLine.equals("maker")){
						System.out.println("maker trying to connect");
						wordMakerPlayer = new WordMakerPlayer(clientSocket);
						out.println("maker started");
					}
					else if (inputLine.equals("breaker")){
						wordBreakerPlayer = new WordBreakerPlayer(clientSocket);

						out.println("breaker started");
					}
				}
				if ( (wordMakerPlayer != null) && (wordBreakerPlayer != null)){
					System.out.println("opponents set");
					wordMakerPlayer.setOpponent(wordBreakerPlayer);
					wordBreakerPlayer.setOpponent(wordMakerPlayer);
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