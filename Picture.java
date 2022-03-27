/*
 * Picture.java
 *
 * Version:
 *     1
 *
 * Revisions:
 *     1
 */

/**
 * This class is used to initialise the image and print the guessed image 
 * 
 * Note: The first player guesses the second word and reveals the second file
 * so here, first player guesses dwight
 * and the second player guesses prisonmike
 * 
 * @author      Sharvai Patil (sp4479@g.rit.edu)
 * @author      Pranali Divekar (pd1267@g.rit.edu)
 */

import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class Picture{

	// 2D vectors to store the images
	Vector<Vector<Character>> image = new Vector<Vector<Character>>(); 

	String filepath;

	String word = "";

	// Strings that store the guessed part of the words
	String guessedWord = "";

	// 2D vectors to store the guessed part of the images
	Vector<Vector<Character>> guessedImage = new Vector<Vector<Character>>(); 

	int lettersRevealed = 0;
	double percentRevealed = 0.0d;
	int totalCharacters = 0;
	
	/**
	* This constructor method initializes the required parameters for players 
	* 
	* 
	* @param       word    			Stores the word to be guessed  
	*			   filepath			Stores the image file which is to be revealed if a player guesses the correct letters
	*
	* @return      void
	*
	*/
	 
	Picture(String word, String filepath) {

		this.word = word;
		this.filepath = filepath;

		//initializing guess word string  
		for(int i = 0; i<word.length(); i++)
			guessedWord+='.';
	}

	/**
	* This method creates the guessed and orignal image vector.
	*
	* @param       		   none 
	*
	* @return              void
	*
	*/
	
	
	public void initImage() throws FileNotFoundException {

		System.out.println(filepath);

		File imagefile = new File(filepath);
		Scanner sc = new Scanner(imagefile);

		do{
			String line = sc.nextLine();
			Vector<Character> temp = new Vector<Character>();
			Vector<Character> guessedTemp = new Vector<Character>();

			for(int i = 0; i<line.length(); i++){
				temp.add(line.charAt(i));
				guessedTemp.add('.');
				totalCharacters+=1;
			}

			image.add(temp);
			guessedImage.add(guessedTemp);
		}while(sc.hasNextLine());

	}

	 /**
	 * This method is used to print the guessed image. 
	 * 
	 * @param            none
	 *
	 * @return           void
	 *
	 */		
	public void printImage(){
		for(int i=0; i<guessedImage.size(); i++){
			for(int j=0; j<guessedImage.get(i).size(); j++){
				System.out.print(guessedImage.get(i).get(j));
			}
			System.out.println();
		}
		
	}


}