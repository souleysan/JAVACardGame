package manager;

import items.*;
import java.util.ArrayList;
import player.*;

public class Table {

    /**
     * Attribut joueurs de la Classe Table
     */
    ArrayList<Player> joueurs;

    /**
     * Attributs jeu et pioche de la Classe Table
     */
    Packet jeu, pioche;

    /**
     * Attribut nbJoueurs de la Classe Table
     */
    int nbJoueurs;

    /**
     * Méthode description - Affiche une description du plan de jeu (la Table)
     */
    void description() {
        System.out.println("La table est compsosée de : " + this.getNbJoueurs() + " valeureux joueurs\nPRÉSENTATIONS :");
        for (int i = 0; i < this.joueurs.size(); i++) {
            this.joueurs.get(i).description();
        }
    }


    /**
     * Méthode initPlayers - Création des joueurs de la partie (de 2 à 4
     * joueurs)
     *
     * @param nbJoueurs
     */
    void initPlayers(int nbJoueurs) {
        if (nbJoueurs >= 2 && nbJoueurs <= 4) {
            for (int i = 0; i < nbJoueurs; i++) {
                this.joueurs.add(new Player());
            }
        }
    }


    /**
     * Méthode initPacket - Création d'un deck de nombreDeCarte cartes, pour
     * chaque joueur de la partie
     *
     * @param nombreDeCarte
     */
    void initPacket(int nombreDeCarte) {
        for (int i = 0; i < this.getNbJoueurs(); i++) {
            this.joueurs.get(i).initPacket(this.jeu, nombreDeCarte);
        }
    }


    /**
     * Méthode initPioche - Création de la pioche à partir d'un packet
     *
     * @param general
     */
    void initPioche(Packet general) {
        this.pioche.initPioche(general);
    }


    /**
     * [Unique] Constructeur de la Classe Table Initialise le jeu, la pioche,
     * les joueurs et la 1ere carte [de table] de la partie
     *
     * @param nombreDeJoueurs
     * @param nombreDeCarte
     */
    public Table(int nombreDeJoueurs, int nombreDeCarte) {
        this.nbJoueurs = nombreDeJoueurs;
        this.jeu = new Packet();
        this.getJeu().init();
        this.pioche = new Packet();
        this.joueurs = new ArrayList<>();
        this.initPlayers(this.getNbJoueurs());
        this.initPacket(nombreDeCarte);
        this.pioche.initPioche(this.jeu);
        this.jeu.initPremiereCarte(this.pioche);
    }


    /**
     * Méthode getNbJoueurs - Renvoie le nombre de joueurs de la partie
     */
    int getNbJoueurs() {
        return this.nbJoueurs;
    }


    /**
     * Méthode getJeu - Renvoie le jeu [complet] de la partie
     */
    Packet getJeu() {
        return this.jeu;
    }


    /**
     * Méthode getPioche - Renvoie la pioche de la partie
     */
    Packet getPioche() {
        return this.pioche;
    }

}
