package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Ibaa
 *
 */

public class AnalyticsCounter {

	/**
	 * the main method to run the program
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// PArcourir le fichier et recuperer la liste des lignes dans un tableau "List"
		// Grace à ReadSymptomDataFromFile
		ISymptomReader iReader = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> symptoms = iReader.getSymptoms();

		// Compter les symptomes recuperés de la liste et creer un tableau
		// qui contient clé= symptome/valeur=occurence
		AnalyticsCounter ac = new AnalyticsCounter();
		Map<String, Integer> sortedResult = ac.countSymptoms(symptoms);

		// Elaborer un fichier result.out pour afficher le contenu de la liste triée
		writeOutPutFile("result.out", sortedResult);
	}

	/**
	 * la fonction countSymptoms permet de compter et trier les symptomes
	 * 
	 * @param lst liste des symptomes disponibles dans le fichier
	 * 
	 * @return un TreeMap avec les symptomes triés par ordre alphabétique avec le
	 *         nombre d'occurence de chacun
	 * 
	 */
	public TreeMap<String, Integer> countSymptoms(List<String> lst) {
		TreeMap<String, Integer> counter = new TreeMap<String, Integer>();

		for (String item : lst) {
			if (counter.get(item) == null) {
				counter.put(item, 1);
			} else {
				counter.put(item, counter.get(item) + 1);
			}
		}
		return counter;

	}

	/**
	 * Elaboration d'un fichier resultat d'un MAP
	 * 
	 * @param fileName c'est le nom du fichier resultat
	 * 
	 * @param output   c'est le MAP à exploiter par le fichier resultant
	 * 
	 */
	public static void writeOutPutFile(String fileName, Map<String, Integer> output) throws IOException {

		FileWriter writer;
		writer = null;
		try {
			writer = new FileWriter(fileName);

			// Ecriture de la premiere ligne de l'output: header

			writer.write("Symptom" + "|" + "Occurence" + "\n");

			// Ecriture du contenu

			for (Map.Entry<String, Integer> symptom : output.entrySet()) {

				writer.write(symptom.getKey() + "=" + symptom.getValue() + " ;\n");

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// fermer le fichier

			writer.close();

		}
	}
}
