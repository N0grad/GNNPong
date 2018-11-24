package Genetic;

import NeuralNetwork.Reseau;

public class Individu implements Comparable<Individu>{

	private int posX = 0;
	private int posY = 275;
	private int largeur = 20;
	private int longueur = 50;
	private int point = 0;
	
	private boolean vivant = true;
	
	private Reseau leCerveau;
	
	private String historique = "";
	
	public Individu() {
		this.leCerveau = new Reseau(new int[] {2, 6, 2});
	}
	
	public Individu(Reseau cerveau) {
		this.leCerveau = cerveau;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void bouge(int balleX, double diffHauteur, int largeurTerrain) {
		
		leCerveau.setValeurEntree(new double[] {balleX / 774.0, diffHauteur / 572.0});
		leCerveau.propagationAvant();
		
		double[] val = leCerveau.getValeurSortie();
		
		if (val[0] > val[1] && this.posY > 0) {
			this.posY -= 1;
		}
		else if (val[1] > val[0] && this.posY < (largeurTerrain - longueur)){
			this.posY += 1;
		}
	
	}

	public int getLongueur() {
		return this.longueur;
	}

	public int compareTo(Individu autreIndividu) {
		return autreIndividu.getPoint() - this.point;
	}


	public void setPoint(int point) {
		this.point = point;
	}
	
	public Reseau getLeCerveau() {
		return this.leCerveau;
	}

	public Individu reproduction() {
		
		Individu enfant = new Individu();
		Reseau cerveau = enfant.getLeCerveau();
		
		cerveau.setValeurDesConnections(this.leCerveau.getValeurDesConnections());
		
		return enfant;
		
	}
	

	public void mutation() {
		
		this.getLeCerveau().setValeurRandomConnection();

	}


	public void printPoint() {
		System.out.print("Point: "+this.point+" / ");
	}
	
	public int getPoint() {
		return this.point;
	}

	public void addPoint(int point) {
		this.point += point;
	}

	public boolean getVivant() {
		return this.vivant;
	}
	
	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	
	public void addHistorique(String ajout) {
		this.historique += (" / "+ajout);
	}
	
	public void setHistorique(String ajout) {
		this.historique = ajout;
	}
	
	public void resetHistorique() {
		this.historique = "";
	}

	public String getHistorique() {
		return this.historique;
	}
	
}
