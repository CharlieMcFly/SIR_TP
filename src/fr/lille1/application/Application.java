package fr.lille1.application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * NEED REFACTORING
 * 
 * @author Charlie
 *
 */
public class Application {

	public static void main(String[] args) {
		
		String fileName = "../SIR_TP1/resources/Log-clients-themes.txt";
		List<String> list = new ArrayList<String>();
		List<String> users= new ArrayList<String>();
		List<String> cat = new ArrayList<String>();
		Map<String, List<String>> mat = new HashMap<>();		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			
			// users
			list = stream
					.map(string -> string.split(";"))
					.flatMap(Arrays::stream)
					.filter(mot -> !isNumeric(mot))
					.collect(Collectors.toList());
			
			for (int i = 0; i < list.size()-1; i+=2) {
				String user = list.get(i);
				String category = list.get(i+1);
				if(!users.contains(user))
				{
					users.add(user);
					mat.put(user, new ArrayList<String>());
				}else{
					mat.get(user).add(category);
				}
				if(!cat.contains(category)){
					cat.add(category);
					mat.get(user).add(category);	
				}
			}
			System.out.println("--- USERS ---");
			Files.write(Paths.get("../SIR_TP1/resources/users.txt"),users,Charset.defaultCharset());
			System.out.println("--- CATEGORIES");
			Files.write(Paths.get("../SIR_TP1/resources/categories.txt"),cat,Charset.defaultCharset());
			System.out.println("--- MATRICE ---");
			
			
			
			
			String result = mat.entrySet()
		            .stream()
		            .map(entry -> entry.getKey() + " - [ " + countingValue(entry.getValue()) + " ]\n")
		            .collect(Collectors.joining(" "));
		    System.out.println(result);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static String countingValue(List<String> list){
	
	Map<String, Integer> map = new HashMap<>();
		for (String string : list) {
			if(!map.containsKey(string))
				map.put(string, 1);
			else
				map.replace(string, map.get(string)+1);
		}	
	
		return map.entrySet()
	            .stream()
	            .map(entry -> entry.getKey() + " - " + entry.getValue())
	            .collect(Collectors.joining(", "));
		
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
