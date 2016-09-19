package fr.lille1.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.lille1.core.SIRException;

/**
 * Class that parse the log file into a list or a matrix
 * 
 * @author Charlie
 *
 */
public class LogParser {

	private String filename;
	
	/**
	 * Constructor.
	 * 
	 * @param path of the file
	 */
	public LogParser(String filename){
		this.filename = filename;
	}
	
	/**
	 * Parse the file into a list of user or categories
	 * 
	 * @param 1 of 2, users or categories
	 * @return list of one of them
	 */
	public Set<String> linesToList(int type){
		
		try (Stream<String> stream = Files.lines(Paths.get(this.filename))) {
			
			return stream.map(line -> line.split(";")[type]).collect(Collectors.toSet());
		
		} catch (IOException e) {
			throw new SIRException("Error : Problem with linesToList function", e);
		} catch (NullPointerException e){
			throw new SIRException("Error : The filename is null or wrong",e);
		} catch (ArrayIndexOutOfBoundsException e){
			throw new SIRException("Error : Out of the Split section", e);
		}
	}
	
	/**
	 * Build the matrice of 2 list (here users and categories)
	 * 
	 * @param users
	 * @param cats
	 * @return matrix of 2 list
	 */
	public long[][] linesToMatrice(Set<String> users, Set<String> cats){
		if(users == null || cats == null)
			throw new SIRException("Error : Arguments are null : users "+users+" or cats "+cats);
		long [][] matrice = new long[users.size()][cats.size()]; 
		Stream<String> stream ;
		try{
			int countUser = 0, countCat;
			for (String user : users ){
				System.out.println(user);
				countCat = 0;
				stream = Files.lines(Paths.get(this.filename));
				List<String> listUsers = stream.filter(line -> user.equals(line.split(";")[1])).collect(Collectors.toList());
				for (String cat : cats) {
					Stream<String> st = listUsers.stream();
					long nbCatUser = st.filter(l -> l.contains(cat)).count();
					matrice[countUser][countCat] = nbCatUser;
					countCat++;
					System.out.println(cat + " : " + nbCatUser);
				}
				countUser++;
				System.out.println("----------");
			}
			return matrice;
		} catch (IOException e) {
			throw new SIRException("Error : Problem with linesToList function", e);
		}catch (NullPointerException e){
			throw new SIRException("Error : The filename is null or wrong",e);
		} catch (ArrayIndexOutOfBoundsException e){
			throw new SIRException("Error : Out of the Split section", e);
		}
	}
}
