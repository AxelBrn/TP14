package fr.mds.axel.java.tp.bataille.navale.model;

import java.util.ArrayList;
import java.util.List;

public class Bateau {

	private int id;
	private int taille;
	private List<Case> cases;
	
	public Bateau(int id, int taille) {
		this.id = id;
		this.taille = taille;
		this.cases = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public void addCase(Case c) {
		if(this.cases == null) {
			this.cases = new ArrayList<>();
		}
		this.cases.add(c);
	}
	
	
}
