/*
 * PictureServer.java
 *
 * Version:
 *     1
 *
 * Revisions:
 *     1
 */

/**
 * This class is used to read the image file and send the data to the Picture client
 * 
 * Note: The first player guesses the second word and reveals the second file
 * so here, first player guesses dwight
 * and the second player guesses prisonmike
 * 
 * @author      Sharvai Patil (sp4479@g.rit.edu)
 * @author      Pranali Divekar (pd1267@g.rit.edu)
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class PictureServer {

	/**
	* main method that creates the PictureServer object and executes startServer()
	*
	* @param       		   args		Command args to init port
	*
	* @return              void
	*
	*/
	public static void main(String[] args) throws IOException {

		PictureServer ob = new PictureServer();

		int port = Integer.parseInt(args[1]);
		ob.startServer(port);
	}

	/**
	* This method starts the server, reads filenames from the clients and then writes
	* the file contents to the clients
	*
	* @param       		   int		port number
	*
	* @return              void
	*
	*/
	public void startServer(int port) throws IOException{

		System.out.println("Starting the server...");

		// creating the socket
		ServerSocket serverSocket = new ServerSocket(port);
		Socket clientSocket = serverSocket.accept();
		
		System.out.println("Connection to client established");

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// Dealing with the first client
		// Reading from the client - picture class 
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		String filename1 = in.readLine();
		System.out.println("Filename received: "+filename1);

		// Writing to the client
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

		File imagefile1 = new File(filename1);
		Scanner sc1 = new Scanner(imagefile1);

		do{
			String line = sc1.nextLine();
			out.println(line);		//writes to client line by line

		}while(sc1.hasNextLine());
		out.println("DONE");

		sc1.close();

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// Dealing with the second client
		// Reading from the client
		String filename2 = in.readLine();
		System.out.println("Filename received: "+filename2);

		File imagefile2 = new File(filename2);
		Scanner sc2 = new Scanner(imagefile2);

		do{
			String line = sc2.nextLine();
			out.println(line);

		}while(sc2.hasNextLine());
		out.println("DONE");

		sc2.close();
		
		serverSocket.close();
		clientSocket.close();
		in.close();
		out.close();
		
	}
}