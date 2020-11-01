package fr.mds.axel.java.tp.bataille.navale.model;

import java.util.ArrayList;
import java.util.List;

import fr.mds.axel.java.tp.bataille.navale.utils.Constante;

public class Map {

	private List<List<Case>> grid;
	
	public Map(int sizeX, int sizeY) {
		this.initializeMap(sizeX, sizeY);
	}

	public Map(List<List<Case>> grid) {
		this.grid = grid;
	}

	public List<List<Case>> getGrid() {
		return grid;
	}

	public void setGrid(List<List<Case>> grid) {
		this.grid = grid;
	}

	private void initializeMap(int sizeX, int sizeY) {
		this.grid = new ArrayList<>();
		for (int i = 0; i < sizeY; i++) {
			List<Case> line = new ArrayList();
			for (int j = 0; j < sizeX; j++) {
				line.add(new Case(0));
			}
			this.grid.add(line);
		}
	}
	
	public void displayGrid() {
		for (int i = 0; i < this.grid.size(); i++) {
			String line = "";
			for (int j = 0; j < this.grid.get(i).size(); j++) {
				line += "[" + this.grid.get(i).get(j).getValue() + "] ";
			}
			System.out.println(line);
		}
		System.out.println("\n");
	}
	
	public boolean estPlacable(Bateau navire, int x, int y, int direction) {
		boolean verif = true;
		  if(direction == Constante.HORIZONTAL) {
			  if(navire.getTaille() <= (Constante.MAP_X - x)) {
				  for(int j = 0; j < navire.getTaille(); j++ ) {
					  if(this.grid.get(x+j).get(y).getValue() != 0) {
						  verif = false;
					  }
				  }
			  } else {
				  for(int k = 0; k < navire.getTaille(); k++ ) {
					  if(this.grid.get(x-k).get(y).getValue() != 0) {
						  verif = false;
					  }
				  }
			  }
		  } else {
			  if(navire.getTaille() <= (Constante.MAP_Y - y)) {
				  for(int j = 0; j < navire.getTaille(); j++ ) {
					  if(this.grid.get(x).get(y+j).getValue() != 0) {
						  verif = false;
					  }
				  }
		  	  } else {
		  		for(int k = 0; k < navire.getTaille(); k++ ) {
		  			if(this.grid.get(x).get(y-k).getValue() != 0) {
		  				verif = false;
					  }
				  }
		  	  }
		  }
		  return verif;
	}
	
	public int countCasesTouche() {
		int touche = 0;
		for (int i = 0; i < this.grid.size(); i++) {
			for (int j = 0; j < this.grid.get(i).size(); j++) {
				if(this.grid.get(i).get(j).getValue() == Constante.TOUCHE) {
					touche++;
				}
			}
		}
		return touche;
	}
	
	
}
