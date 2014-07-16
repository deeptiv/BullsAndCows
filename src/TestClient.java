import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;


public class TestClient {
	private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static String player;
    public TestClient(String serverAddress, String player) throws Exception {
    	
        // Setup networking
    	  
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        try{
	        if(player.equals("maker")){
	        		out.println("maker");	
	        }else if (player.equals("breaker")){
	        		out.println("breaker");
	        	 
	        }
        }finally {
           // socket.close();
        }
    }
    public void play() throws Exception {
        String response;
        
        try {
            while (true) {
            	Scanner scan = new Scanner(System.in);
           		
                response = in.readLine();
                if(response != null){
                	System.out.println(response);
                	if(response.startsWith("input a four letter")){
                		String makerString = scan.nextLine();
                		makerString = "MakerInput" + makerString;
                		out.println(makerString);
                	}
                	
                	if(response.equals("your guess")){
                		String breakerString = scan.nextLine();
                		breakerString = "BreakerInput" + breakerString;
                		out.println(breakerString);
                	}
                }
            }
            
        }
        finally {
            socket.close();
        }
    }
	/**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            System.out.println("want to join as wordMaker or wordBreaker?");
            Scanner scan = new Scanner(System.in);
    		player = scan.nextLine();
           TestClient client = new TestClient(serverAddress, player);
           
           client.play();
//            if (!client.wantsToPlayAgain()) {
//                break;
//            }
        
    }
}
