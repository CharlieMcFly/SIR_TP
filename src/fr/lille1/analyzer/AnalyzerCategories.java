package fr.lille1.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fr.lille1.core.SIRException;

public class AnalyzerCategories extends Analyzer{

	public AnalyzerCategories(String filename){
		super(filename);
	}

	/**
	 * ETAPE 1
	 * 
	 * @return
	 */
	public List<List<Long>> fileToMatrice(){

		try (Stream<String> stream = Files.lines(Paths.get(this.filename))) {
			
			return stream.collect(() -> {
                List<List<Long>> list = new ArrayList<>();
                return list;
            },
            (list, s) -> {
            	String[] nb = s.split(";");
            	for (int i = 0; i < nb.length; i++) {
            		if(i > list.size() - 1)
            			list.add(new ArrayList<Long>());
            		list.get(i).add(Long.parseLong(nb[i]));
            	}
            },
            (list1, list2) -> {
            	
            });
		} catch (IOException e) {
			throw new SIRException("Error", e);
		}
	}
	
	
		
}
