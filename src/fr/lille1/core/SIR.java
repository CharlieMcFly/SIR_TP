package fr.lille1.core;

import java.util.Set;

import fr.lille1.parser.LogParser;
import fr.lille1.writer.SIRWriter;

/**
 * Class main, that parse a log file into list which are written in a file
 * 
 * @author Charlie
 *
 */
public class SIR {

	public static final int USER = 1;
	public static final int CATEGORIE = 2;
	private String destination;
	private String filename;
	
	public SIR(String destination, String filename){
		this.destination = destination;
		this.filename = filename;
	}
	
	/**
	 * Execute the class
	 */
	public void execute() {
		
		LogParser log = new LogParser(destination + filename);
		Set<String> users = log.linesToList(USER);
		Set<String> categories = log.linesToList(CATEGORIE);
		long[][] matrice = log.linesToMatrice(users, categories);
		SIRWriter writer = new SIRWriter(destination);
		writer.listToFile(users, "users.txt");
		writer.listToFile(categories, "categories.txt");
		writer.matriceToFile(matrice, "MUT.txt");
	}
	
}
