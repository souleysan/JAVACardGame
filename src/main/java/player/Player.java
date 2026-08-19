package player;

import items.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    /**
     * Enumarateur de noms par defaut [nomsParDefautPossiblesDansLeJeu]
     */
    enum Nom {
        PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4
    };

    /**
     * Attribut Nom de la Classe Player - Le nom du Player
     */
    Nom nom;
    /**
     * Attribut Deck de la Classe Player - Le deck du Player
     */
    Packet deck;
    /**
     * Attribut identifiant de la Classe Player (commun à tous les Player du
     * jeu)
     */
    static int identifiant = 0;
    /**
     * Attribut Id de la Classe Player - (propre à chaque Player)
     */
    int id;

    /**
     * [Unique] Constructeur de la Classe Player
     */
    public Player() {
        this.id = this.getIdentifiant();
        this.nom = Player.Nom.values()[this.getIdentifiant()];
        this.deck = new Packet();
        Player.identifiant++;
    }


    /**
     * Méthode description - Affiche une description du Player
     */
    public void description() {
        System.out.println(this.getNom() + " __________");
        this.deck.descriptionNumerotee();
        System.out.println();
    }


    /**
     * Méthode initPacket - Initialise un deck de num cartes, à
     * partir d'un packet
     *
     * @param general
     * @param num
     */
    public void initPacket(Packet general, int num) {
        this.deck.initPacket(general, num);
    }


    public boolean deckEmpty() {
        return this.getDeck().isEmpty();
    }


    /**
     * Méthode getNom - Getter de l'attribut nom
     *
     * @return leNomDuJoueur
     */
    public Nom getNom() {
        return this.nom;
    }


    /**
     * Méthode getDeck - Getter de l'attribut deck
     *
     * @return leDeckDuJoueur
     */
    public Packet getDeck() {
        return this.deck;
    }


    /**
     * Méthode getPack - Getter du packet de l'attribut deck
     *
     * @return lePackDuDeckDuJoueur
     */
    public ArrayList<Carte> getPack() {
        return this.deck.pack;
    }


    /**
     * Méthode getIdentifiant - Getter de l'identifiant (commun à la classe)
     *
     * @return lIdentifiantDeLaClasse
     */
    public int getIdentifiant() {
        return Player.identifiant;
    }


    /**
     * Méthode getId - Getter de l'attribut id (unique)
     * 
     * @return lIdDuPlayer
     */
    public int getId() {
        return this.id;
    }


    /**
     * Méthode getCardByIndex - Getter d'une carte à partir de son index
     *
     * @param index
     * @return laCarteParLIndex
     */
    public Carte getCardByIndex(int index) {
        return this.deck.getCardByIndex(index);
    }


    /**
     * Méthode piocher - Le Player pioche une carte
     *
     * @param e
     */
    public void piocher(Carte e) {
        this.deck.piocher(e);
    }


    /**
     * Méthode piocherDeux - Le Player pioche 2 cartes
     *
     * @param general
     */
    public void piocherDeux(Packet general) {
        this.deck.piocherDeux(general);
    }


    /**
     * Méthode piocherCinq - Le Player pioche 5 cartes
     *
     * @param general
     */
    public void piocherCinq(Packet general) {
        this.deck.piocherCinq(general);
    }


    /**
     * Méthode poserCarte - Le Player pose une carte e
     *
     * @param table
     * @param e
     */
    public void poserCarte(Packet table, int e) {
        table.piocher(this.getDeck().defausser(this.getCardByIndex(e)));
    }


    /**
     * Méthode checkValueCardChoice - Vérifie la validité d'une carte
     * 
     * @param input - L'entrée standard
     * @return correctSelectedCard
     */
    public boolean checkValueCardChoice(int input) {
        /*Scanner scan = new Scanner(System.in);
        while (input > this.getPack().size() || input < 0) {
            System.out.print("Carte non selectionnable, choisis-en une autre : ");
            input = scan.nextInt();
        }
        scan.close();
        return true;
        */

        return !(input > this.getPack().size() || input < 0);
    }


    // Ajout d'un 2e constructeur où on pourra choisir son nom avant une partie.
    // void passerTour() {} // On verra plus tard

}