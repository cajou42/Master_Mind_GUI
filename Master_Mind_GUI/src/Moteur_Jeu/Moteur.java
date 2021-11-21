package Moteur_Jeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Moteur {
	
	public static ArrayList<String> ChoixDeDepart(String[] couleur) {
		ArrayList<String> tab = new ArrayList<String>();
		for(int i=0; i <= 3; i++) {
			double random = (Math.random() * ((5 - 0) + 1)) + 0;
			String temp = couleur[(int) random];
			tab.add(temp);
		}
		System.out.println(tab);
		return tab; 
	}
	
	public ArrayList<String> TourJoueur() {
		ArrayList<String> tab = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			int size = 0;
			while(size != 4) {
				System.out.println("choisissez une couleur parmi : rouge, bleu, jaune, vert, gris, violet");
				String line = reader.readLine();
				System.out.println(line);
				if(line.equals("rouge") || line.equals("bleu") || line.equals("jaune") || line.equals("vert") || line.equals("gris") || line.equals("violet")) {
					tab.add(line);
					System.out.println("vous avez mit la couleur :" + line);
					System.out.println(tab);
				}
				size++;
				System.out.println(size);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tab;
	}
	
	
	
	public int Verification(ArrayList<String> tab_joueur, ArrayList<String> tab_depart, int tentative){
		int bon = 0;
		int bon_mais_pas_a_la_bonne_place = 0;
		for(int i = 0; i <= tab_joueur.size()-1; i++) {
			if(tab_joueur.get(i).equals(tab_depart.get(i))) {
				System.out.println("alu");
				bon++;
			}
			else if(tab_joueur.contains(tab_depart.get(i))) {
				bon_mais_pas_a_la_bonne_place ++;
			}	
		}
		
		if(tab_joueur.equals(tab_joueur)) {
			System.out.println("Le joueur a gagner !");
		}
		else if(tentative == 0) {
			System.out.println("Le joueur a perdu ...");
		}
		else {
			System.out.println("Ce n'est pas la bonne combinaison, vous perdez une tentative");
			System.out.println(tab_joueur);
			System.out.println(tab_depart);
			System.out.println(bon + " couleur à la bonne place");
			System.out.println(bon_mais_pas_a_la_bonne_place + " couleur bonne mais pas a la bonne place");

			tentative --;
		}
		return tentative;
	}
	
}
