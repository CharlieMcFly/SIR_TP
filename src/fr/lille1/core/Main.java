package fr.lille1.core;

public class Main {

	public static void main(String[] args) {
		String destination = "../SIR_TP1/resources/";
		String filename = "Log-clients-themes.txt";
		SIR sir = new SIR(destination, filename);
		sir.analyze();
	}

}
