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
 * the main method	
 * @param args
 * compiler le programme
 */

		public static void main(String[] args) {
			// TODO Auto-generated method stub
	
/**
 * 
 * PArcourir le fichier et recuperer la liste des lignes dans un tableau "List"
 * Grace à ReadSymptomDataFromFile
 * 
 */
			ISymptomReader iReader = new ReadSymptomDataFromFile("symptoms.txt");		
			
			List<String> symptoms = iReader.GetSymptoms();
			
/**
 * 
 * Compter les symptomes recuperés de la liste et 
 * creer un tableau
 * qui contient clé= symptome/valeur=occurence
 * 			
 */	
			
			AnalyticsCounter ac = new AnalyticsCounter();
			Map<String, Integer> sortedResult = ac.countSymptoms(symptoms);
			
/**
 * 
 * Elaborer un fichier result.out pour afficher le contenu de la liste triée
 * 			
 */
			writeOutPutFile("result.out", sortedResult);
		}
		
/**
 * 		
 * @param lst
 * compter l'occurence des symptomes et les placer dans un TreeMap
 * 
 * @return
 * le TreeMap retourne un Tableau des symptomes triés par ordre alphabétique 
 * avec le nombre d'occurence de chacun
 * 
 */
		public TreeMap<String, Integer> countSymptoms(List<String> lst)
		{
			TreeMap <String, Integer> counter = new TreeMap <String, Integer>();
			
			for (String item : lst) {
				if (counter.get(item)== null) {
					counter.put(item, 1);
				} else {
					counter.put(item, counter.get(item) +1);
				}
			}
			return counter;
			
		}
				
		// Elaboration de l'output
/**
 * 		
 * @param fileName
 * creer un fichier pour écrire les résultats dedans
 * 
 * @param output
 * écrire le contenu du fichier qui est sous forme de tableau "Map"
 * String: nom de chaque symptome
 * Integer: le nombre d'occurence de chacun
 */
		public static void writeOutPutFile(String fileName, Map<String, Integer> output) {

			FileWriter writer;
			try {
				writer = new FileWriter(fileName);

				// Ecriture de la premiere ligne de l'output: header
				
				writer.write("Symptom" + "|" + "Occurence" + "\n");

				// Ecriture du contenu
				
				for (Map.Entry<String, Integer> symptom : output.entrySet()) {

					writer.write(symptom.getKey() + "=" + symptom.getValue() + " ;\n");

				}

				// fermer le fichier
				
				writer.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
}
