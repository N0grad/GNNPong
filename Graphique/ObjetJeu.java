package Graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import Genetic.Generation;
import Genetic.Individu;
import Objet.Balle;

public class ObjetJeu extends JPanel {

	private static final long serialVersionUID = 6537959821272207341L;
	
	private Generation lesIndividus;
	private Balle balle;
	
	
	public ObjetJeu(Generation lesIndividus, Balle balle) {
		this.lesIndividus = lesIndividus;
		this.balle = balle;
		
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0,  0, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < lesIndividus.getPopulation().length; i++) {
			Individu individu = lesIndividus.getPopulation()[i];
			if (individu.getVivant()) {
				g.setColor(Color.RED);
				g.fillRect(individu.getPosX(), individu.getPosY(), 20, 50);
				g.setColor(Color.GRAY);
				g.drawRect(individu.getPosX(), individu.getPosY(), 20, 50);
			}
		}
		
		g.setColor(Color.WHITE);
		Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g.setFont(fonte);
		g.drawString("Nombre d'individu en vie : "+lesIndividus.getNombreVivant(), 475, 30);
		
		g.setColor(Color.WHITE);
		g.fillOval(balle.getPosX(), balle.getPosY(), 6, 6);
		
		g.setColor(Color.WHITE);
		g.fillRect(this.getWidth() / 2, 0, 1, this.getHeight());

	}

}
