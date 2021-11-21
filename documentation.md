# projet final de java
Louis MERLAUD

## Somaire
- Présentation du projet
- version terminal
    - initialisation
    - tour du joueur
    - vérification
- version graphique
    - initialisation
    - intéraction avec le joueur
- point à améliorer sur le projet
- source

## Présentation du projet : 
ce projet vise à faire un "master mind" en java, le master est un jeu dans lequel un joueur choisie une combinaison de quatre couleur parmi six donnée, un autre joueur doit alors réproduire cette séquence de couleur avec un certain nombre d'essaie.
dans le programme la combinaison de départ est généré aléatoirement.

le projet contien deux versions du jeu : une version qui ce joue avec le terminal et une autre qui ce joue depuis une interface graphique.

le projet contient les packets suivant :
- le packet Moteur qui contient les fonctions de la version terminal
- le packet Test qui permet de lancer et de tester la version terminal
- le packet GUI qui contient les fonctions et permet de lancer la version graphique
- le packet Image qui contient des images utiliser dans la versiongraphique

ici la combinaison départ sera toujours montré au démarage des fonctions afin simplifier les tests

## version terminal

**initialisation**
on commence déjà par initialisé les variables `m_couleur` et `m_tentative` dans la classe `Test.java`,
la première permet d'initialiser la combinaison de départ et la seconde le nombre de tentative du joueur

```
private static String[] m_couleur = {"rouge","bleu","jaune","vert","gris","violet"}; 
private static int m_tentative = 12;
```

Dans la classe `Moteur.java`, on détermine la combinaison de départ avec la fonction `ChoixDeDepart` : 
```
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
```
Dans cette fonction on génére un nombre aléatoire compris entre 0 et 5 et on récupère une valeur du tableau passé en paramètre (dans notre cas se sera `m_couleur`) à l'index de ce nombre, le l'ArrayList  retourné sera notre combinaison de départ.

**tour du joueur**

pour que le joueur fasse son choix de combinaison, on va utiliser la fonction `TourJoueur` : 
```
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
```

cette fonction va retourner un ArrayList contenant la combinaison du joueur, tant que l'ArrayList n'a pas une taille de 4,
le joueur est inviter à entrer une couleur parmi celle autorisé directement dans le terminal, on récupère cette information grâce à la ligne `String line = reader.readLine();`, puis ensuite on ajoute ce résultat dans l'ArrayList que sera retourné.

**Vérification**

on compare la combinaison du joueur avec celle de départ grâce à la fonction `Verification` : 
```
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
```

Cette fonction vérifie si les index de la combinaison de départ (ici `tab_depart`) et celles de la combinaison du joueur (`tab_joueur`) sont identique, si c'est le cas la variable `bon` s'incrémente, dans le cas échéant on regarde si la combinaison de départ contient quand même la couleur entré par le joueur à cette index grâce à la fonction `contains`, dans ce cas la variable `bon_mais_pas_a_la_bonne_place` s'incrémente.
A la fin du programe on affiche au joueur son résultat et on décrément la variable tentative passé en paramètre (dans notre cas il s'agit de la variable `m_tentative`) si cette variable atteint 0, le joueur perd.

voici à quoi ressemble la fonction de test : 
```
	public static void main(String[] args) {
		Moteur test = new Moteur();
		ArrayList<String> tab = new ArrayList<String>();
		ArrayList<String> tabj = new ArrayList<String>();
		tab = test.ChoixDeDepart(m_couleur);
		//System.out.println(test.ChoixDeDepart(m_couleur));
		tabj = test.TourJoueur();
		System.out.println(test.Verification(tabj, tab, m_tentative));
	}
```

## version graphique

**initialisation**
dans `Interface.java` : 
comme précédement on initialise les variables `couleur` et `m_tentative`, on définie aussi la variable `tab_dep`qui nous permet de générer la combinaison de départ grâce à la fonction `ChoixDeDepart` utiliser précédement, on initialise aussi une variable `i` qui servira de compteur pour plus tard
on créer en amont le boutons et les label que l'on va utiliser dans l'interface
```
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
```

on créer l'interface avec le constructeur : 
```
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
```

dans notre constructeur on fait deux pannel : le `panelData` qui contien les boutons pour entrer les combinaisons et le `panelInfo` qui va nous permettre de transmettre les résultats au joueur, et aussi permettre de valider son choix

on peut lancer le programe grâce à la fonction `main` : 
```
	public static void main(String[] args) {
		Interface Laucher = new Interface();
		Laucher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Laucher.setVisible(true);
	}
```

**intéraction avec le joueur**

pour que le joueur puisse entrer ses choix, on associe un événement aux boutons avec l'`actionPerformed` : 
```
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
```
intéressons nous d'abord au cas ou le boutons pressé n'est pas `confirm`, dans ce cas présent la fonction `roulement` sera appelé :
```
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
```
Cette fonction va permettre un roulement de couleur à chaque apuis sur le bouton, pour cela on écrit la nom de la couleur dans le bouton (`((AbstractButton) object).setText(couleur [i]);`), on utilise le compteur `i` initialisé plus tôt pour parcourir le tableau contenant les couleurs, ce compteur est remis à zéro s'il ateint six afin d'évité de sortir du tableau et d'avoir une erreur.
on utilise également un `case` pour changer la couleur du bouton, pour la couleur choisie, pour ce faire on utilise `setIcon` qui va nous permettre de mettre une image dans le bouton (les images sont dans le packet `Image`)

Maitenant regardons le bouton `confirm`, ce bouton permet de confirmer le choix du joueur et de lancer les vérifications;
deux fonctions sont appelées lors d'un appuie : 

la fonction `recup_value` : 
```
	public ArrayList<String> recup_value(){
		ArrayList<String> tab = new ArrayList<String>();
		tab.add(0,b1.getText());
		tab.add(1,b2.getText());
		tab.add(2,b3.getText());
		tab.add(3,b4.getText());
		System.out.println(tab);
		return tab;
	}
```
Cette fonction permet de simplement récupérer les choix du joueur via le nom des couleurs inscrit dans les boutons (ils ne sont visible à cause des images, mais ils sont bien là) et de les mettres dans un Arraylist

et la fonction `Verification_GUI` : 
```
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

```
Cette fonction est la même que la fonction `Verification` de la version terminal, mais est adaptée à l'interface graphique, on affiche notament les résultats en bas de la fenêtre grâce à des Labels situés dans le `panelInfo`, si le joueur gagne ou perd une pop-up viendra l'en informer (`JOptionPane.showMessageDialog`)

## point à améliorer sur le projet
- certain bug encore, notament sur la fonction de vérification qui peut faire des sienne si le choix de départ contient plusieurs fois la même couleur

- manque de gestion d'erreur 

## source

https://stackoverflow.com/questions/1200621/how-do-i-declare-and-initialize-an-array-in-java

https://dzone.com/articles/random-number-generation-in-java

https://dzone.com/articles/interactive-console-applications-in-java

https://www.data-transitionnumerique.com/java-switch-case/

https://www.youtube.com/watch?v=gkqNh-XGJlY