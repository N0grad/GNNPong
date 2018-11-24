package Objet;

public class Balle {

	private int posX;
	private int posY;
	private int diametre;
	
	private boolean versLaDroite;
	private boolean versLeBas;
	
	public Balle(int posX, int posY, int diametre) {
		this.posX = posX;
		this.posY = posY;
		this.diametre = diametre;
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
	
	public int getDiametre() {
		return this.diametre;
	}
	
	public void bouge(int longueurTerrain, int largeurTerrain, int longueurJoueur, int largeurJoueur) {
		
		
		if (posX <= largeurJoueur) {
			versLaDroite = true;
		}
		else if (posX >= longueurTerrain - largeurJoueur - diametre) {
			versLaDroite = false;
		}
		
		if (posY <= 0) {
			versLeBas = true;
		}
		else if (posY >= largeurTerrain - diametre) {
			versLeBas = false;
		}
		
		if (versLaDroite) {
			this.posX += 1;
		}
		else {
			this.posX -= 1;
		}
		
		if (versLeBas) {
			this.posY += 1;
		}
		else {
			this.posY -= 1;
		}
	}
	
}
