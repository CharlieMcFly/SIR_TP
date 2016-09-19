package fr.lille1.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fr.lille1.core.SIRException;

public class AnalyzerUsers extends Analyzer {

	public AnalyzerUsers(String filename) {
		super(filename);
	}

	public List<Long> fileToMatrice() {

		try (Stream<String> stream = Files.lines(Paths.get(this.filename))) {
			
			return stream.collect(() -> {
                List<Long> list = new ArrayList<>();
                return list;
            },
            (list, s) -> {
            	long total = 0;
            	String[] nb = s.split(";");
            	for (int i = 0; i < nb.length; i++) {
            		total += Long.parseLong(nb[i]);
            	}
            	list.add(total);
            },
            (list1, list2) -> {
            	
            });
		} catch (IOException e) {
			throw new SIRException("Error", e);
		}
	}

}
