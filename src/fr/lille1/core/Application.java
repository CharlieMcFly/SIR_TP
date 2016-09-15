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
public class Application {

	public static final int USER = 1;
	public static final int CATEGORIE = 2;
	
	public static void main(String[] args) {
		
		String destination = "../SIR_TP1/resources/";
		String filename = destination + "Log-clients-themes.txt";
		LogParser log = new LogParser(filename);
		Set<String> users = log.linesToList(USER);
		Set<String> categories = log.linesToList(CATEGORIE);
		long[][] matrice = log.linesToMatrice(users, categories);
		SIRWriter writer = new SIRWriter(destination);
		writer.listToFile(users, "users.txt");
		writer.listToFile(categories, "categories.txt");
		writer.matriceToFile(matrice, "MUT.txt");
		System.out.println("------ END ------");
	}
	
}
