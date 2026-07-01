package manager;

import items.*;
import java.util.ArrayList;
import player.*;

public class Table {

    ArrayList<Player> joueurs;
    Packet jeu, pioche;
    int nbJoueurs;

    public Table(int nombreDeJoueurs) {
        this.nbJoueurs = nombreDeJoueurs;
        this.joueurs = new ArrayList<>();
        this.initPlayers(this.getNombreDeJoueurs());
        this.jeu = new Packet();
        this.jeu.init();
        this.pioche = new Packet();
    }

    ArrayList getJoueurs() {
        return this.joueurs;
    }

    int getNbJoueurs() {
        return this.nbJoueurs;
    }

    Packet getJeu() {
        return this.jeu;
    }

    Packet getPioche() {
        return this.pioche;
    }

    void description() {
        System.out.println("La table est compsosée de : " + this.getNbJoueurs() + " valeureux joueurs \n\nPRÉSENTATIONS :");
        for (int i = 0; i < this.joueurs.size(); i++) {
            this.joueurs.get(i).description();
        }
    }

    int getNombreDeJoueurs() {
        return this.nbJoueurs;
    }

    ArrayList<Player> initPlayers(int nbJoueurs) {
        if (nbJoueurs >= 2 && nbJoueurs <= 4) {
            for (int i = 0; i < nbJoueurs; i++) {
                this.joueurs.add(new Player());
            } return joueurs;
        } else { return null;}
    }

    public static void main(String[] args) {
        Table table = new Table(3);
        table.description();
    }

}
