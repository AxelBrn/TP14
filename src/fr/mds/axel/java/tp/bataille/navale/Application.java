package fr.mds.axel.java.tp.bataille.navale;

import java.util.ArrayList;
import java.util.List;

import fr.mds.axel.java.tp.bataille.navale.manager.Bataille;
import fr.mds.axel.java.tp.bataille.navale.model.Bateau;
import fr.mds.axel.java.tp.bataille.navale.model.Corvette;
import fr.mds.axel.java.tp.bataille.navale.model.Croiseur;
import fr.mds.axel.java.tp.bataille.navale.model.Destroyer;
import fr.mds.axel.java.tp.bataille.navale.model.Joueur;
import fr.mds.axel.java.tp.bataille.navale.model.Map;
import fr.mds.axel.java.tp.bataille.navale.model.PorteAvion;
import fr.mds.axel.java.tp.bataille.navale.utils.Constante;

public class Application {
	
	private static Bataille bataille;

	public static void main(String[] args) {
		createGame();
	}
	
	private static void createGame() {
		List<Joueur> players = new ArrayList<>();
		for (int i = 0; i < Constante.JOUEURS; i++) {
			players.add(new Joueur(i+1, new Map(Constante.MAP_X, Constante.MAP_Y), addNaviresForPlayer()));
			players.get(i).displayMap();
		}
		bataille = new Bataille(players);
		bataille.startGame();
	}
	
	private static List<Bateau> addNaviresForPlayer() {
		List<Bateau> navires = new ArrayList();
		for(int i = 0; i < Constante.NB_CORVETTE; i++) {
			navires.add(new Corvette());
		}
		for(int i = 0; i < Constante.NB_DESTROYER; i++) {
			navires.add(new Destroyer());
		}
		for(int i = 0; i < Constante.NB_CROISEUR; i++) {
			navires.add(new Croiseur());
		}
		for(int i = 0; i < Constante.NB_PORTE_AVION; i++) {
			navires.add(new PorteAvion());
		}
		return navires;
	}
	

}
