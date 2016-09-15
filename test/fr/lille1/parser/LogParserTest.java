package fr.lille1.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.lille1.core.SIR;
import fr.lille1.core.SIRException;

public class LogParserTest {

	// LinesToList
	
	@Test(expected = SIRException.class)
	public void testLinesToListNullFilename() {
		LogParser logP = new LogParser(null);
		logP.linesToList(SIR.CATEGORIE);
	}

	@Test(expected = SIRException.class)
	public void testLinesToListWrongFilename() {
		LogParser logP = new LogParser("troltoll");
		logP.linesToList(SIR.CATEGORIE);
	}

	@Test(expected = SIRException.class)
	public void testLinesToListWrongType() {
		LogParser logP = new LogParser("../SIR_TP1/resources/Log-clients-themes.txt");
		logP.linesToList(4);
	}
	
	@Test
	public void testLinesToListOK() {
		LogParser logP = new LogParser("../SIR_TP1/resources/Log-clients-themes.txt");
		assertEquals(logP.linesToList(SIR.USER).size(), 9);
		
	}
	
	// LineToMatrice
	
	@Test(expected = SIRException.class)
	public void testLinesToMatriceNullFilename() {
		LogParser logP = new LogParser(null);
		logP.linesToList(SIR.CATEGORIE);
	}

	@Test(expected = SIRException.class)
	public void testLinesToMatriceWrongFilename() {
		LogParser logP = new LogParser("troltoll");
		logP.linesToList(SIR.CATEGORIE);
	}

	@Test(expected = SIRException.class)
	public void testLinesToMatriceListEmpty() {
		LogParser logP = new LogParser("../SIR_TP1/resources/Log-clients-themes.txt");
		logP.linesToMatrice(null, null);
	}
	
	@Test
	public void testLinesToMatrice() {
		LogParser logP = new LogParser("../SIR_TP1/resources/Log-clients-themes.txt");
		assertEquals(logP.linesToMatrice(logP.linesToList(SIR.USER), logP.linesToList(SIR.CATEGORIE)).length, 9);
	}


}
