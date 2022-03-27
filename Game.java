/*
 * Game.java
 *
 * Version:
 *     1
 *
 * Revisions:
 *     1
 */

/**
 * This class is used to drive the 2 player image guessing game. 
 * 
 * Note: The first player guesses the second word and reveals the second file
 * so here, first player guesses dwight
 * and the second player guesses prisonmike
 * 
 * @author      Sharvai Patil (sp4479@g.rit.edu)
 * @author      Pranali Divekar (pd1267@g.rit.edu)
 */
import java.io.*;
import java.util.*;


public class Game {
	
	// player objects 
	Player me;
	Player you;
	
	/**
	* This constructor method initializes all the required parameters like player names, words to be guessed and image files 
	* 
	* 
	* @param       myName    			Stores the name of the first player 
	*			   yourWords			Stores the word to be guessed by the first player
	*			   yourPictureFilename	Stores the image file which is to be revealed if player 1 guesses the correct letters
	*
	*			   yourName				Stores the name of the second player
	*			   myWords				Stores the word to be guessed by the second order
	*			   myPictureFilename	Stores the image file which is to be revealed if player 2 guesses the correct letters
	*
	* @return      void
	*
	*/
	Game(String myName, String yourWords, String yourPictureFilename, String yourName, String myWords, String myPictureFilename ) {

		// that is, an exchange occurs
		// your guess word and picture goes to my object
		me = new Player(myName, yourWords, yourPictureFilename, 1);

		// my guess word and picture goes to you object
		you = new Player(yourName, myWords,myPictureFilename, 2);
	}

	int winner = -1; // 0 if player 1 wins and 1 if player 2 wins
	
	/**
	* This method creates an object of the class, initializes the guessedWords with '.' characters
	* and gets the arguments from the command line
	*
	* @param       args    command-line arguments
	*
	* @return              void
	*
	*/

	public static void main(String args[]) throws FileNotFoundException{

		String myName = "";
		String myWords = "";
		String myWordsFilename = "";
		String myPictureFilename = "";

		String yourName = "";
		String yourWords = "";
		String yourWordsFilename = "";
		String yourPictureFilename = "";

		
		if(args[0].equals("-me")){
			// fetching my data from arguments
			myName = args[1];
			myWordsFilename = args[3];
			myWords = getWord(myWordsFilename);
			myPictureFilename = args[5];

			// fetching your data from arguments 
			yourName = args[7];
			yourWordsFilename = args[9];
			yourWords = getWord(yourWordsFilename);
						yourPictureFilename = args[11];
		}
		else{
			// fetching my data from arguments
			myName = args[7];
			myWordsFilename = args[9];
			myWords = getWord(myWordsFilename);
			myPictureFilename = args[11];

			// fetching your data from arguments 
			yourName = args[1];
			yourWordsFilename = args[3];
			yourWords = getWord(yourWordsFilename);
			yourPictureFilename = args[5];
		}

		//instantiating object of class to begin the game
		Game ob = new Game(myName, yourWords, yourPictureFilename, yourName, myWords, myPictureFilename);
		ob.init();

		ob.runGame();
	}
	
	/**
	* This method is used to read the word to be guessed from the word file 
	* 
	* @param       filename    Stores the word to be guessed 
	*
	* @return           String	
	*
	*/

	static String getWord(String filename) throws FileNotFoundException {

		File wordFile = new File(filename);
		Scanner sc = new Scanner(wordFile);

		String word  = sc.nextLine();

		sc.close();

		return word;
	}
	
	/**
	* This method is used to initialise both the image and guessed image for both the players  
	* 
	* @param       		none
	*
	* @return           void	
	*
	*/
	void init() throws FileNotFoundException{
		
		me.initPicture();
		you.initPicture();

		
	}
	/**
	* This method is used to start the game  
	* 
	* @param       		none 
	*
	* @return           void 	
	*
	*/

	void runGame() {
		int player = 1;
		do{
			if(player==1)
				winner = me.playTurn();
			else
				winner = you.playTurn();

			player = (player+1)%2;

		}while(winner==-1);
	}

}