package player;

import items.*;
import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println();
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
    public Nom getNom() {
        return this.nom;
    }


    /**
     * Méthode getDeck - Renvoie le deck du joueur
     *
     * @return leDeckDuJoueur
     */
    public Packet getDeck() {
        return this.deck;
    }


    /**
     * Méthode getPack - Renvoie le packet du deck du joueur
     *
     * @return lePackDuDeckDuJoueur
     */
    public ArrayList<Carte> getPack() {
        return this.deck.pack;
    }


    /**
     * Méthode getIdentifiant - Renvoie l'identifiant (commun à la classe) d'un
     * joueur
     *
     * @return lIdentifiantDeLaClasse
     */
    public int getIdentifiant() {
        return Player.identifiant;
    }


    /**
     * Méthode getCardByIndex - Selectionne une carte à partir de son index
     *
     * @param index
     * @return laCarteParLIndex
     */
    public Carte getCardByIndex(int index) {
        return this.deck.getCardByIndex(index);
    }


    /**
     * Méthode piocher - Le joueur pioche une carte
     *
     * @param e
     */
    public void piocher(Carte e) {
        this.deck.piocher(e);
    }


    /**
     * Méthode piocherDeux - Le joueur pioche 2 cartes
     *
     * @param general
     */
    public void piocherDeux(Packet general) {
        this.deck.piocherDeux(general);
    }


    /**
     * Méthode piocherCinq - Le joueur pioche 5 cartes
     *
     * @param general
     */
    public void piocherCinq(Packet general) {
        this.deck.piocherCinq(general);
    }


    /**
     * Méthode poserCarte - Le joueur pose une carte e, sur un autre packet
     *
     * @param table
     * @param e
     */
    public void poserCarte(Packet table, int e) {
        table.piocher(this.getCardByIndex(e));
    }


    public boolean gagant() {
        return this.deck.pack.isEmpty();
    }


    public boolean checkValueCardChoice(int input) {
        Scanner scan = new Scanner(System.in);
        while (input > this.getPack().size() || input < 0) {
            System.out.println("Carte non selectionnable, choisis-en une autre");
            this.checkValueCardChoice(scan.nextInt());
        } return true;
    }

    
    // Ajout d'un 2e constructeur où on pourra choisir son nom avant une partie.
    // void passerTour() {} // On verra plus tard
}
