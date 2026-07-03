package player;

import items.*;
import java.util.ArrayList;

public class Player {

    /**
     * Enumarateur de noms par defaut Nom nomsParDefautPossiblesDansLeJeu
     */
    enum Nom {
        PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4
    };

    /**
     * Attribut nom de la Classe Player - Le nom du joueur
     */
    Nom nom;

    /**
     * Attribut deck de la Classe Player - Le deck du joueur
     */
    Packet deck;

    /**
     * Attribut identifiant de la Classe Player (commun à tous les Player du
     * jeu)
     */
    static int identifiant = 0;

    /**
     * Constructeur de la Classe Player
     */
    public Player() {
        this.nom = Player.Nom.values()[this.getIdentifiant()];
        this.deck = new Packet();
        Player.identifiant++;
    }


    /**
     * Méthode description - Affiche une description du joueur
     */
    public void description() {
        System.out.println(this.getNom() + " __________");
        this.deck.description();
    }


    /**
     * Méthode initPacket - Initialise un deck de num cartes pour le joueur, à
     * partir d'un packet
     *
     * @param general
     * @param num
     */
    public void initPacket(Packet general, int num) {
        this.deck.initPacket(general, num);
    }


    /**
     * Méthode getNom - Renvoie le nom du joueur
     *
     * @return leNomDuJoueur
     */
    Nom getNom() {
        return this.nom;
    }


    /**
     * Méthode getDeck - Renvoie le deck du joueur
     *
     * @return leDeckDuJoueur
     */
    Packet getDeck() {
        return this.deck;
    }


    /**
     * Méthode getPack - Renvoie le packet du deck du joueur
     *
     * @return lePackDuDeckDuJoueur
     */
    ArrayList<Carte> getPack() {
        return this.deck.pack;
    }


    /**
     * Méthode getIdentifiant - Renvoie l'identifiant (commun à la classe) d'un
     * joueur
     *
     * @return lIdentifiantDeLaClasse
     */
    int getIdentifiant() {
        return Player.identifiant;
    }


    /**
     * Méthode getCardByIndex - Selectionne une carte à partir de son index
     *
     * @param index
     * @return laCarteParLIndex
     */
    Carte getCardByIndex(int index) {
        return this.deck.getCardByIndex(index);
    }


    /**
     * Méthode piocher - Le joueur pioche une carte
     *
     * @param e
     */
    void piocher(Carte e) {
        this.deck.piocher(e);
    }


    /**
     * Méthode piocherDeux - Le joueur pioche 2 cartes
     *
     * @param general
     */
    void piocherDeux(Packet general) {
        this.deck.piocherDeux(general);
    }


    /**
     * Méthode piocherCinq - Le joueur pioche 5 cartes
     *
     * @param general
     */
    void piocherCinq(Packet general) {
        this.deck.piocherCinq(general);
    }


    /**
     * Méthode poserCarte - Le joueur pose une carte e, sur un autre packet
     *
     * @param table
     * @param e
     */
    void poserCarte(Packet table, int e) {
        table.piocher(this.getCardByIndex(e));
    }


    // Ajout d'un 2e constructeur où on pourra choisir son nom avant une partie.
    // void passerTour() {} // On verra plus tard
}
