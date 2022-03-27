/*
 * Player.java
 *
 * Version:
 *     1
 *
 * Revisions:
 *     1
 */

/**
 * This program allows 2 players to play an image-guessing game. This class is used to initialise the image and print the guessed image 
 * 
 * Note: The first player guesses the second word and reveals the second file
 * so here, first player guesses dwight
 * and the second player guesses prisonmike
 * 
 * @author      Sharvai Patil (sp4479@g.rit.edu)
 * @author      Pranali Divekar (pd1267@g.rit.edu)
 */
import java.util.*;
import java.io.*;

public class Player {

	String name;
	Picture picture;
	int playerNumber;
	
	/**
	* This constructor method initializes the required parameters for players 
	* 
	* 
	* @param       name    			Stores the name of the first player 
	*			   words			Stores the word to be guessed by the first player
	*			   filename			Stores the image file which is to be revealed if player 1 guesses the correct letters
	*			   playerNumber     Stores the number of player (1 or 2)
	*
	* @return      void
	*
	*/

	Player(String name, String words, String filename, int playerNumber) {
		this.name = name;

		picture = new Picture(words, filename);

		this.playerNumber = playerNumber;
	}

	public static void main(String args[]) {

	}
	/**
	* This method initializes the guessed image vector and the original image vector.  
	* 
	* @param       		none
	*
	* @return           void	
	*
	*/

	public void initPicture() throws FileNotFoundException{
		picture.initImage();
	}
	
	/**
	* This method is used to print the picture.
	* 
	* @param       		none
	*
	* @return           void	
	*
	*/

	public void printPicture() {
		picture.printImage();
	}
	
	/**
	* This method is used to get the guessed letter.  
	* 
	* @param       		none
	*
	* @return           char 	
	*
	*/

	public char getInput() {
		Scanner sc = new Scanner(System.in);

		System.out.print(name+"'s turn ("+(picture.guessedWord)+"): ");
		char guessedLetter = sc.nextLine().charAt(0);

		return guessedLetter;
	}
	
	/**
	* This method runs the game for respective player and returns the winner 
	* 
	* @param       		none
	*
	* @return           int 	
	*
	*/

	public int playTurn() {
		
		char guessLetter = this.getInput();
		boolean isGuessCorrect = false;
		int winner = -1;

		int index = -1;
		int startIndex = 0; //start index in word2 of where to start searching for the guessedLetter
		
		// searching for the guessLetter
		do{
			index = this.picture.word.indexOf(guessLetter, startIndex);
			
			// searching for the next occurance of the guessLetter if it has already been guessed
			if(index!=-1){
				if(this.picture.guessedWord.charAt(index)!='.'){
					startIndex=index+1;
					index=-1;
				}
			}
			else{
				break;
			}
		}while(index==-1);

		// the guess was correct
		if(index!=-1){
			System.out.println("YOUR GUESS IS CORRECT!");

			isGuessCorrect=true;
			
			// checking how many letters have been revealed
			// if all have been revealed, then this player is the winner 
			this.picture.lettersRevealed+=1;
			if(this.picture.lettersRevealed < this.picture.word.length())
				this.picture.percentRevealed += 1.0/this.picture.word.length()*this.picture.totalCharacters;
			else{
				winner=playerNumber;
				this.picture.percentRevealed = this.picture.percentRevealed + (this.picture.totalCharacters - this.picture.percentRevealed);
				System.out.println(name+" WINS!!");
			}

			if(winner==-1){
				// generating the guessedword after adding the new guessed letter
				this.picture.guessedWord = this.picture.guessedWord.substring(0,index)+guessLetter+this.picture.guessedWord.substring(index+1);
				System.out.println("Guessed word: "+this.picture.guessedWord);
				// revealing characters of guessedImage2
				double tempPercentRevealed = 0;
				here:{
					do{
						for(int i=0; i<this.picture.image.size(); i++){
							for(int j=0; j<this.picture.image.get(i).size(); j++){
								
								// going over every pixel and then randomly deciding if
								// it should be revealed or not
								if(Math.random()*2>1){
									Vector<Character> temp = this.picture.guessedImage.get(i);
									
									// revealing the pixel
									temp.set(j, this.picture.image.get(i).get(j));
									this.picture.guessedImage.set(i, temp);
									tempPercentRevealed+=1;

									if(tempPercentRevealed==this.picture.percentRevealed)
										break here; //breaks out of all loops
								}
							}
						}
					}while(tempPercentRevealed<this.picture.percentRevealed);
				}
			}

		}
		else{
			System.out.println("Wrong Guess!");
		}

		if(isGuessCorrect)
			this.picture.printImage();

		return winner;
	}
}