package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Moteur_Jeu.Moteur;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton confirm;
	private JLabel information;
	private JLabel info_bon;
	private JLabel info_moins_bon;
	private JLabel info_tent;
	private static String[] couleur = {"rouge","bleu","jaune","vert","gris","violet"};
	private int i;
	private int m_tentative = 12;
	private ArrayList<String> tab_dep = Moteur.ChoixDeDepart(couleur);
	
	public Interface() {
		setSize(800,400);
		setTitle("Master Mind");
		
		JPanel panelData = new JPanel(new GridLayout(0,4,10,10));
		panelData.setBorder(new EmptyBorder(10,10,10,10));
		b1 = new JButton();
		panelData.add(b1);
		b2 = new JButton();
		panelData.add(b2);
		b3 = new JButton();
		panelData.add(b3);
		b4 = new JButton();
		panelData.add(b4);
		add(panelData);

		
		JPanel panelInfo = new JPanel(new GridLayout(1,2,10,10));
		panelInfo.setBorder(new EmptyBorder(10,10,10,10));
		information = new JLabel("Choisissez vos couleur");
		panelInfo.add(information);
		info_bon = new JLabel();
		panelInfo.add(info_bon);
		info_moins_bon = new JLabel();
		panelInfo.add(info_moins_bon);
		info_tent = new JLabel("tentatives restantes : " + m_tentative);
		panelInfo.add(info_tent);
		confirm = new JButton("soumettre le choix");
		panelInfo.add(confirm);
		add(panelInfo, BorderLayout.SOUTH);
		confirm.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}
	
	
	public static void main(String[] args) {
		Interface Laucher = new Interface();
		Laucher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Laucher.setVisible(true);
	}
	
	public void roulement(Object object, String[] couleur) {
		((AbstractButton) object).setText(couleur [i]);
		switch (couleur [i]) {
		
			case "rouge":
				((AbstractButton) object).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\Master_Mind_GUI\\src\\Image\\rouge.png"));
				break;

			case "bleu":
				((AbstractButton) object).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\Master_Mind_GUI\\src\\Image\\bleu.jpg"));
				break;

			case "jaune":
				((AbstractButton) object).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\Master_Mind_GUI\\src\\Image\\jaune.jpg"));
				break;

			case "vert":
				((AbstractButton) object).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\Master_Mind_GUI\\src\\Image\\vert.jpg"));
				break;

			case "gris":
				((AbstractButton) object).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\Master_Mind_GUI\\src\\Image\\gris.jpg"));
				break;

			case "violet":
				((AbstractButton) object).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\Master_Mind_GUI\\src\\Image\\violet.jpg"));
				break;
	
		}
		i++;
		if (i == 6) {
			i = 0;
		}
		
	}
	
	public ArrayList<String> recup_value(){
		ArrayList<String> tab = new ArrayList<String>();
		tab.add(0,b1.getText());
		tab.add(1,b2.getText());
		tab.add(2,b3.getText());
		tab.add(3,b4.getText());
		System.out.println(tab);
		return tab;
	}
	
	public void Verification_GUI(ArrayList<String> tab_joueur, ArrayList<String> tab_depart){
		int bon = 0;
		int bon_mais_pas_a_la_bonne_place = 0;
		for(int i = 0; i <= tab_joueur.size()-1; i++) {
			if(tab_joueur.get(i).equals(tab_depart.get(i))) {
				bon++;
			}
			else if(tab_joueur.contains(tab_depart.get(i))) {
				bon_mais_pas_a_la_bonne_place ++;
			}	
		}
		
		if(tab_joueur.equals(tab_depart)) {
			JOptionPane.showMessageDialog(this, "Le joueur a gagne !");
		}
		else if(m_tentative == 0) {
			JOptionPane.showMessageDialog(this, "Le joueur a perdu ...");
		}
		else {
			information.setText("Ce n'est pas la bonne combinaison, vous perdez une tentative");
			info_bon.setText(bon + " couleur à la bonne place");
			info_moins_bon.setText(bon_mais_pas_a_la_bonne_place + " couleur bonne mais pas a la bonne place");
			m_tentative --;
			info_tent.setText("tentatives restantes : " + m_tentative);
			System.out.println(m_tentative);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirm) {
			ArrayList<String> tab_joueur = new ArrayList<String>();
			tab_joueur = recup_value();
			Verification_GUI(tab_joueur,tab_dep);
		}
		else {
			roulement(e.getSource(), couleur);
		}
	}

}

