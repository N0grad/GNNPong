package Genetic;
import java.util.Arrays;

public class Generation {

	private Individu[] population;
	private final double CHANCE_DE_MUTER = 1;
	private final double POURCENTAGE_ELITE_RETENU = 0.2;
	private int nbGeneration = 1;
	
	public Generation (int nbIndividu) {
		this.population = new Individu[nbIndividu];
		this.fillGeneration();
	}
	
	private void fillGeneration() {
		for (int i = 0; i < this.population.length; i++) {
			this.population[i] = new Individu();
		}
	}
	
	public void notation(Generation lesIndividus, int posYBalle) {
		
		Individu[] laPopulation = lesIndividus.getPopulation();
		int point = 0;
		
		for (Individu individu : laPopulation) {
			
			if (individu.getVivant()) {
				
				if (posYBalle >= individu.getPosY() && posYBalle <= individu.getPosY() + individu.getLongueur()) {
					point = 1000;
				}
				
				else {
					point = -1 * Math.abs(individu.getPosY() - posYBalle);
					individu.setVivant(false);
				}
			
				individu.addPoint(point);
				
			}
			
		}
	}
	
	public void classement() {
		Arrays.sort(this.population);
	}
	
	public void nextGeneration() {
		int nombreEliteRetenu = (int) (this.population.length * this.POURCENTAGE_ELITE_RETENU);
		int nbDeMutation = (int) (this.population[0].getLeCerveau().getNbConnection() * (5 / 100.0)) + 1; //5% de mutation (+1)

		for (int i = 0; i < nombreEliteRetenu; i++) {
			Individu enfant = this.population[i].reproduction();
			
			for (int j = 0; j < nbDeMutation ; j++) {
				enfant.mutation();
				enfant.mutation();
			}
			
			if ((this.population[i].getHistorique().equals(""))) {
				this.population[i].addHistorique(""+i);
			}

			enfant.addHistorique(this.population[i].getHistorique()+" : "+(i+1));
			
			this.population[i+nombreEliteRetenu] = enfant;
		}
		
		for (int i = nombreEliteRetenu * 2; i < this.population.length; i++) {
			this.population[i] = new Individu();
			this.population[i].addHistorique("Sans parent");
		}
		
		this.resetElite();
		
		this.setNbGeneration(this.nbGeneration + 1);
		
	}
	

	public void setNbGeneration(int nbGeneration) {
		this.nbGeneration = nbGeneration;
	}
	
	public int getNbGeneration() {
		return this.nbGeneration;
	}
	
	public boolean tousMorts() {
		
		boolean ret = true;
		int i = 0;
		
		while (i < this.population.length) {
			
			if (this.population[i].getVivant()) {
				ret = false;
				i = this.population.length;
			}
			
			i++;
		}
		
		return ret;
	
		
	}
	
	public Individu[] getPopulation() {
		return this.population;
	}
	
	public int getNombreVivant() {
		int ret = 0;
		
		for (int i = 0; i < this.population.length; i++) {
			if (this.population[i].getVivant()) {
				ret++;
			}
		}
		
		return ret;
	}

	public void resetElite() {
		int nombreEliteRetenu = (int) (this.population.length * this.POURCENTAGE_ELITE_RETENU);
		
		for (int i = 0; i < nombreEliteRetenu; i++) {
			this.population[i].setPoint(0);
			this.population[i].setPosY(275);
			this.population[i].setVivant(true);
		}
	}
	
	public void printMoyenneScore() {
		int total = 0;
		
		for (int i = 0; i < this.population.length; i++) {
			total += this.population[i].getPoint();
		}
		
		System.out.println("\nMOYENNE : "+(total / this.population.length));
	}

	public double getCHANCE_DE_MUTER() {
		return this.CHANCE_DE_MUTER;
	}
	
	public double getPOURCENTAGE_ELITE_RETENU() {
		return this.POURCENTAGE_ELITE_RETENU;
	}
}
