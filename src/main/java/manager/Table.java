package manager;
import items.*;
import java.util.ArrayList;
import player.*;

public class Table {

    ArrayList<Player> joueurs;
    Packet jeu, pioche;
    int nbJoueurs;

    public Table(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
        this.joueurs = new ArrayList<>(); this.initPlayers(nbJoueurs);
        this.jeu = new Packet(); this.jeu.init();
        this.pioche = new Packet();
    }

    ArrayList getJoueurs() { return this.joueurs; }
    int getNbJoueurs() { return this.nbJoueurs; }
    Packet getJeu() { return this.jeu; }
    Packet getPioche() { return this.pioche; }

    void description() {
        System.out.println("La table est compsosée de : " + this.getNbJoueurs() + " valeureux joueurs \n\nPRÉSENTATIONS :");
        for (int i = 0; i < this.joueurs.size(); i++) {
            this.joueurs.get(i).description();
        }
    }


    ArrayList<Player> initPlayers(int nbJoueurs) {
        for (int i = 0; i < nbJoueurs; i++) {
            this.joueurs.add(new Player());
        }
        return joueurs;
    }




    public static void main (String [] args) {
        Table table = new Table(3);
        table.description();
    }



}