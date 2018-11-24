import java.util.Random;

import javax.swing.JFrame;

import Genetic.Generation;
import Genetic.Individu;
import Graphique.ObjetJeu;
import Objet.Balle;

public class Main {
	
	public final int diametreBalle = 6;
	
	public final int longueurTerrain = 800;
	public final int largeurTerrain = 600;
	
	public final int longueurJoueur = 50;
	public final int largeurJoueur = 20;
	
	public final int nombreJoueur = 100;
	
	public static int nbGeneration = 0;
	
	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			
			Main main = new Main();
			main.launch();

		}

		System.out.println("Nombre de génération pour maitriser le jeu (random) : " + (nbGeneration / 100) + " générations.");
		
	}

	
	public void launch() {
		
		
		//placement de la balle au milieu de terrain
		Balle balle = new Balle( (longueurTerrain /2) - (diametreBalle/ 2), (largeurTerrain /2) - (diametreBalle/ 2), diametreBalle); 
		
		//génération initial de X individus
		Generation lesIndividus = new Generation(nombreJoueur);
		
		//on récupère la populatiopn
		Individu[] population = lesIndividus.getPopulation();
		
		//création de la fenêtre
		JFrame fenetre = new JFrame();
		ObjetJeu panel = new ObjetJeu(lesIndividus, balle);
		
		fenetre.setTitle("Genetic Neural Network - Pong");
		fenetre.setSize(longueurTerrain, largeurTerrain);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);
		fenetre.setContentPane(panel);
		
		fenetre.setVisible(true);

		//compteur de generation
		int nb = 1;
		
		boolean fini = false;
		
		//BOUCLE DE JEU
		while (!fini) {
			
			//déplacement de la balle
			balle.bouge(longueurTerrain, largeurTerrain, longueurJoueur, largeurJoueur);
			
			//déplacement des joueurs
			for (int i = 0; i < population.length; i++) {
				
				Individu joueur = population[i];
				
				//Calcul de la différence de hauteur entre le milieu du joueur et le milieu de la balle
				double milieuIndividu = joueur.getPosY() + (joueur.getLongueur() / 2.0);
				double milieuBalle = balle.getPosY() + (balle.getDiametre() / 2.0);
				
				double diffHauteur = milieuIndividu - milieuBalle;
						
				if (joueur.getVivant()) {
					joueur.bouge(balle.getPosX(), diffHauteur, largeurTerrain); //Ne lui donnez que la diffHauteur > ALL
					if (joueur.getPoint() > 1000000) {
						 joueur.getLeCerveau().printReseau();
						 System.out.println(joueur.getHistorique());
						 fini = true;
						 fenetre.setVisible(false);
					}
				}

			}
			
			panel.repaint();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//lorsque la balle touche le camp des individus
			if (balle.getPosX() <= largeurJoueur) {

				//on note les individus vivant et on kill ce qui n'ont pas touché la balle
				lesIndividus.notation(lesIndividus, balle.getPosY());
				
				//si tous les individus sont morts
				if (lesIndividus.tousMorts()) {
					
					//on fait le classement
					lesIndividus.classement();
					
					//on affiche les poins des 10% meilleures meilleurs
					for (int i = 0; i < (population.length * 0.2); i++) {
						lesIndividus.getPopulation()[i].printPoint();
					}
					
					//on engendre la nouvelle génération
					lesIndividus.nextGeneration();
					
					Main.nbGeneration = lesIndividus.getNbGeneration();
					
					System.out.println("\n----------- GENERATION "+nb+"-----------");
					nb++;
					
					//on génère sur une position Y qui fait que le premier lancé ne touchera pas la partie supérieur ou inférieur gauche
					
					/*balle = new Balle( (longueurTerrain /2) - (diametreBalle/ 2), (largeurTerrain /2) - (diametreBalle/ 2), diametreBalle);*/
					
					Random r = new Random();
					if (r.nextDouble() > 0.5) {
						balle.setPosX((longueurTerrain / 2) - (diametreBalle / 2)); 
						balle.setPosY(r.nextInt(320)); 
					}
					else {
						balle.setPosX((longueurTerrain / 2) - (diametreBalle / 2)); 
						balle.setPosY(r.nextInt(170) + 430);
					}
					
				}
				
			}
			
			
			
		}
		
	}
	
}
