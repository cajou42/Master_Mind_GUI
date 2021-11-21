package Test_Jeu;

import java.util.ArrayList;

import Moteur_Jeu.Moteur;

public class Test {
	private static String[] m_couleur = {"rouge","bleu","jaune","vert","gris","violet"}; 
	private static int m_tentative = 12;
	
	public static void main(String[] args) {
		Moteur test = new Moteur();
		ArrayList<String> tab = new ArrayList<String>();
		ArrayList<String> tabj = new ArrayList<String>();
		tab = test.ChoixDeDepart(m_couleur);
		//System.out.println(test.ChoixDeDepart(m_couleur));
		tabj = test.TourJoueur();
		System.out.println(test.Verification(tabj, tab, m_tentative));
	}
}
