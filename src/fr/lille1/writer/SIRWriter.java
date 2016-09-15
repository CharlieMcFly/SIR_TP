package fr.lille1.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import fr.lille1.core.SIRException;

/**
 * 
 * Class that write list or matrice to a file
 * 
 * @author Charlie
 *
 */
public class SIRWriter {

	private String destination;
	
	public SIRWriter(String destination){
		this.destination = destination;
	}
	
	/**
	 * Put the list into a file
	 * 
	 * @param myList
	 * @param filename
	 */
	public void listToFile(Set<String> myList, String filename){
		
		 try {
			 System.out.println("Writing " + filename);
			 Files.write(Paths.get(destination + filename),myList,Charset.defaultCharset());
		} catch (IOException e) {
			throw new SIRException("Error : Problem when writing in the file", e);
		}
	}

	/**
	 * Put a matrix into a file
	 * 
	 * @param myMatrice
	 * @param filename
	 */
	public void matriceToFile(long[][] myMatrice, String filename){
		try {
			System.out.println("Writing " + filename );
	        BufferedWriter bw = new BufferedWriter(new FileWriter(destination + filename));
	        for (int i = 0; i < myMatrice.length; i++) {
	            for (int j = 0; j < myMatrice[i].length; j++) {
	                bw.write(myMatrice[i][j] + ((j == myMatrice[i].length-1) ? "" : "\t"));
	            }
	            bw.newLine();
	        }
	        bw.flush();
	        bw.close();
	    } catch (IOException e) {
	    	throw new SIRException("Error : Problem when writing in the file", e);
	    }
	}
	
}
