package fr.lille1.core;

import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import fr.lille1.analyzer.AnalyzerCategories;
import fr.lille1.analyzer.AnalyzerUsers;
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
		// PARSER
		Set<String> users = log.linesToList(USER);
		Set<String> categories = log.linesToList(CATEGORIE);
		long[][] matrice = log.linesToMatrice(users, categories);
		// WRITER 
		SIRWriter writer = new SIRWriter(destination);
		writer.listToFile(users, "users.txt");
		writer.listToFile(categories, "categories.txt");
		writer.matriceToFile(matrice, "MUT.txt");
	}
	
	public void analyze(){
		if(Paths.get(destination + "MUT.txt").toFile().exists()){
			// ANALYZER CAT
			AnalyzerCategories categories = new AnalyzerCategories(destination + "MUT.txt");
			List<List<Long>> list = categories.fileToMatrice();
			List<Long> listInt = categories.nbProductList(list);
			long[][] mat = categories.listToMatrice(listInt);
			// ANALYZER USER
			AnalyzerUsers users = new AnalyzerUsers(destination + "MUT.txt");
			List<Long> listUsers = users.fileToMatrice();
			long[][] matUser = users.listToMatrice(listUsers);
						
			// WRITER
			SIRWriter writer = new SIRWriter(destination);
			writer.matriceToFile(mat, "categories-mat.txt");
			writer.matriceToFile(matUser, "users-mat.txt");
		}else{
			this.execute();
			this.analyze();
		}
		
	}	
	
}
