package fr.mds.axel.java.tp.bataille.navale.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mds.axel.java.tp.bataille.navale.model.Bateau;
import fr.mds.axel.java.tp.bataille.navale.model.Case;
import fr.mds.axel.java.tp.bataille.navale.model.Joueur;
import fr.mds.axel.java.tp.bataille.navale.utils.Constante;

public class Bataille {
	
	private List<Joueur> players = new ArrayList<>();
	
	public Bataille() {
		
	}
	
	public Bataille(List<Joueur> players) {
		this.players = players;
	}
	
	public void startGame() {
		int currentPlayer = 0;
		
		while(this.checkAllPlayersIsAlive()) {
			this.tire(trouveCible(currentPlayer));
			this.players.get(trouveCible(currentPlayer)).displayMap();
	
			if(currentPlayer == (this.players.size() - 1)) {
				currentPlayer = 0;
			} else {
				currentPlayer++;
			}
		}
		System.out.println("Le gagnant est les joueur  " + this.getWinner().getId() + " !!!");
	}

	public List<Joueur> getPlayers() {
		return players;
	}

	public void setPlayers(List<Joueur> players) {
		this.players = players;
	}
	
	public void addNaviresByPlayer() {
		for(int i = 0; i < players.size(); i++) {
			
		}
	}
	
	private boolean checkAllPlayersIsAlive() {
		boolean verif = true;
		if(this.getWinner() != null) {
			verif = false;
		}
		return verif;
	}
	
	private Joueur getWinner() {
		List<Joueur> playersAlive = new ArrayList<>();
		for (int i = 0; i < this.players.size(); i++) {
			if(this.players.get(i).estVivant()) {
				playersAlive.add(this.players.get(i));
			}
		}
		if(playersAlive.size() > 1) {
			return null;
		}
		return playersAlive.get(0);
	}

	/**
	   * Trouve la prochaine cible en vie.
	   * @param attaquant l'indice de l'attaquant.
	   * @return l'adversaire suivant.
	   */
	  private int trouveCible(int attaquant) {
		int cible = attaquant;
		do {
			cible++;
			if(cible == this.players.size()) {
				cible = 0;
			}
		}
		while(!this.players.get(cible).estVivant());
		return cible;
	  }

	/**
	   * Tire sur le joueur ciblé
	   * @param joueurCible indice du joueur ciblé
	   */
	  public void tire(int joueurCible) {
		
		  Random rand = new Random();
		  int x;
		  int y;
		  do {
			  x = rand.nextInt(Constante.MAP_X);
			  y = rand.nextInt(Constante.MAP_Y);
		  } while(!verifTireIsPossible(joueurCible, x, y));
		  
		  int value = this.players.get(joueurCible).getMap().getGrid().get(y).get(x).getValue();
		  if(value != 0) {
			  this.players.get(joueurCible).getMap().getGrid().get(y).get(x).setValue(Constante.TOUCHE);
		  } else {
			  this.players.get(joueurCible).getMap().getGrid().get(y).get(x).setValue(Constante.LOUPE);
		  }
	  }
	  
	  public boolean verifTireIsPossible(int joueurCible, int x, int y) {
		  boolean verif = false;
		  int value = this.players.get(joueurCible).getMap().getGrid().get(y).get(x).getValue();
		  if(value != Constante.TOUCHE && value != Constante.LOUPE) {
			  verif = true;
		  }
		  return verif;
	  }
	
	
}
