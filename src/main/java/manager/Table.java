package manager;

import items.*;
import java.util.ArrayList;
import player.*;

public class Table {

    ArrayList<Player> joueurs;
    Packet jeu, pioche;
    int nbJoueurs;

    void description() {
        System.out.println("La table est compsosée de : " + this.getNbJoueurs() + " valeureux joueurs\nPRÉSENTATIONS :");
        for (int i = 0; i < this.joueurs.size(); i++) {
            this.joueurs.get(i).description();
        } // this.getJeu().description();
    }

    void initPlayers(int nbJoueurs) {
        if (nbJoueurs >= 2 && nbJoueurs <= 4) {
            for (int i = 0; i < nbJoueurs; i++) {
                this.joueurs.add(new Player());
            }
        }
    }

    void initPacket(int nombreDeCarte) {
        for (int i = 0; i < this.getNbJoueurs(); i++) {
            this.joueurs.get(i).initPacket(this.jeu, nombreDeCarte);
        }
    }

    void initPioche(Packet general) { this.pioche.initPioche(general); }

    public Table(int nombreDeJoueurs, int nombreDeCarte) {
        this.nbJoueurs = nombreDeJoueurs;
        this.jeu = new Packet(); this.getJeu().init();
        this.pioche = new Packet();
        this.joueurs = new ArrayList<>(); this.initPlayers(this.getNombreDeJoueurs());
        this.initPacket(nombreDeCarte);
        this.pioche.initPioche(this.jeu);
        this.jeu.initPremiereCarte(this.pioche);
    }

    int getNbJoueurs() { return this.nbJoueurs; }
    Packet getJeu() { return this.jeu; }
    Packet getPioche() { return this.pioche; }
    int getNombreDeJoueurs() { return this.nbJoueurs; }




    public static void main(String[] args) {
        Table table = new Table(4, 5);
        System.out.println("JEU ====================================================");
        table.jeu.description();
        System.out.println("PIOCHE ====================================================");
        table.pioche.description();
        System.out.println("GENERAL ====================================================");
        table.description();
        // System.out.println("JEU FINAL ====================================================");
        // table.jeu.description();
    }



}