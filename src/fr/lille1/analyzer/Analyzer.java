package fr.lille1.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * REFACTOR
 * 
 * @author Charlie
 *
 */
public abstract class Analyzer {

	public String filename;
	
	public Analyzer(String filename){
		this.filename = filename;
	}
	
	/**
	 * 
	 * @param list2
	 * @return
	 */
	public List<Long> nbProductList(List<List<Long>> list2){
		
		List<Long> totalProduit = new ArrayList<Long>();
		for (List<Long> list : list2) {
			long total = 0;
			for (Long l : list) {
				total += l;
			}
			totalProduit.add(total);
		} 
		return totalProduit;
	}
	
	/**
	 *  
	 * @param list
	 * @return
	 */
	public long[][] listToMatrice(List<Long> list){
		
		long[][] mat = new long[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				mat[i][j] = list.get(i) + list.get(j);
			}
		}
		return mat;
	}

}
