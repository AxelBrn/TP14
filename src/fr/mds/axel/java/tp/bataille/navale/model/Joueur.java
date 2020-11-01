package fr.mds.axel.java.tp.bataille.navale.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mds.axel.java.tp.bataille.navale.utils.Constante;

public class Joueur {

	private int id;
	private Map map;
	private List<Bateau> navires;

	public Joueur() {}
	
	public Joueur(int id, Map map, List<Bateau> navires) {
		this.id = id;
		this.map = map;
		this.navires = navires;
		this.placeToutBateau();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Bateau> getNavires() {
		return navires;
	}

	public void setNavires(List<Bateau> navires) {
		this.navires = navires;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addNavire(Bateau navire) {
		if(this.navires == null) {
			this.navires = new ArrayList<>();
		}
		this.navires.add(navire);
	}
	
	public void displayMap() {
		System.out.println("Map du joueur " + this.id + " : \n");
		this.map.displayGrid();
	}
	
	
	private void placeToutBateau() {
		for (int i = 0; i < this.navires.size(); i++) {
			this.placeBateau(i);
		}
	}
	
	private void placeBateau(int index) {
			  Random rand = new Random();
			  int x;
			  int y;
			  int direction;
			  do {
				  x = rand.nextInt(Constante.MAP_X);
				  y = rand.nextInt(Constante.MAP_Y);
				  direction = rand.nextInt(2);
			  } while (!this.map.estPlacable(this.navires.get(index), x, y, direction));
			  
			  this.placeBateauDansCarte(index, x, y, direction);
			  
	  }
	
	private void placeBateauDansCarte(int index, int x, int y, int direction) {
		  if(direction == Constante.HORIZONTAL) {
			  if(this.navires.get(index).getTaille() <= (Constante.MAP_X - x)) {
				  for(int j = 0; j < this.navires.get(index).getTaille(); j++ ) {
					  this.map.getGrid().get(x+j).get(y).setValue(this.navires.get(index).getId());
					  this.navires.get(index).getCases().add(new Case(this.navires.get(index).getId()));
				  }
			  } else {
				  for(int k = 0; k < this.navires.get(index).getTaille(); k++ ) {
					  this.map.getGrid().get(x-k).get(y).setValue(this.navires.get(index).getId());
					  this.navires.get(index).getCases().add(new Case(this.navires.get(index).getId()));
				  }
			  }
		  }else {
			  if(this.navires.get(index).getTaille() <= (Constante.MAP_Y - y)) {
				  for(int j = 0; j < this.navires.get(index).getTaille(); j++ ) {
					  this.map.getGrid().get(x).get(y+j).setValue(this.navires.get(index).getId());
					  this.navires.get(index).getCases().add(new Case(this.navires.get(index).getId()));
				  }
		  	  } else {
		  		for(int k = 0; k < this.navires.get(index).getTaille(); k++ ) {
		  			this.map.getGrid().get(x).get(y-k).setValue(this.navires.get(index).getId());
		  			this.navires.get(index).getCases().add(new Case(this.navires.get(index).getId()));
				  }
		  	  }
		  }
	  }
	
	   public boolean estVivant() {
		   boolean verif = true;
		   int nbCasesNavires = 0;
		   for(int i = 0; i < this.navires.size(); i++) {
			   nbCasesNavires += this.navires.get(i).getTaille();
		   }
		   
		   if(this.map.countCasesTouche() == nbCasesNavires) {
			   verif = false;
		   }
		   return verif;
	   }
}
