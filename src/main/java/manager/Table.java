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
    public void description() {
        System.out.println("\n\nLa table est compsosée de : " + this.getNbJoueurs() + " valeureux joueurs\n\n");
        for (int i = 0; i < this.joueurs.size(); i++) {
            this.joueurs.get(i).description(); System.out.println();
        }
        // this.getJeu().description();
        System.out.print("Voici le jeu : "); this.jeu.description();
    }


    /**
     * Méthode initPlayers - Création des joueurs de la partie (de 2 à 4
     * joueurs)
     *
     * @param nbJoueurs
     */
    public void initPlayers(int nbJoueurs) {
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
    public void initPacket(int nombreDeCarte) {
        for (int i = 0; i < this.getNbJoueurs(); i++) {
            this.joueurs.get(i).initPacket(this.jeu, nombreDeCarte);
        }
    }


    /**
     * Méthode initPioche - Création de la pioche à partir d'un packet
     *
     * @param general
     */
    public void initPioche(Packet general) {
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
    public int getNbJoueurs() {
        return this.nbJoueurs;
    }


    /**
     * Méthode getJeu - Renvoie le jeu [complet] de la partie
     */
    public Packet getJeu() {
        return this.jeu;
    }


    // public void setTourDeJeu(ArrayList <Player> p) {
    //     this.joueurs = p;
    // }


    public void setNbJoueur(int nbJoueur) {
        this.nbJoueurs = nbJoueur;
    }


    public void reduceNbJoueur() {
        this.setNbJoueur(nbJoueurs - 1);
    }


    /**
     * Méthode getPioche - Renvoie la pioche de la partie
     */
    public Packet getPioche() {
        return this.pioche;
    }


    public boolean resteUnJoueur() {
        return this.joueurs.size() == 1;
    }


    public Player selectPlayer(int index) {
        return this.joueurs.get(index);
    }

    public Carte getLastCard() {
        return this.getJeu().getCardByIndex(this.getJeu().pack.size() - 1);
    }

    public void removePlayer(int index) {
        this.joueurs.remove(index);
        this.setNbJoueur(this.getNbJoueurs() - 1);
    }

    public static void main(String[] args) {
        Table p = new Table(3,3);
        // p.selectPlayer(2).description();
        // p.description();
        // p.getLastCard();
        System.out.println("remove----------------------------------------------");
        // p.reomvePlayer(0);
        // p.description();
        System.out.println("remove----------------------------------------------");
        // p.reomvePlayer(0);
        p.description();
        // p.selectPlayer((-1 + p.getNbJoueurs())%p.getNbJoueurs()).description();
        // p.selectPlayer((-2 + p.getNbJoueurs())%p.getNbJoueurs()).description();
        // p.selectPlayer((-3 + p.getNbJoueurs())%p.getNbJoueurs()).description();
        //System.out.println();
    }

}
