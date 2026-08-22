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
     * Méthode initPlayers - Création des joueurs de la partie 
     * A partir de 2, jusqu'à 4 Players
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
     * Méthode initPacket - Création d'un deck de num cartes, pour
     * chaque Player
     *
     * @param num
     */
    public void initPacket(int num) {
        for (int i = 0; i < this.getNbJoueurs(); i++) {
            this.joueurs.get(i).initPacket(this.jeu, num);
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
     * Méthode description - Affiche une description de la Table
     */
    public void description() {
        System.out.println("\n\nLa table est compsosée de : " + this.getNbJoueurs() + " valeureux joueurs\n\n");
        for (int i = 0; i < this.joueurs.size(); i++) {
            this.joueurs.get(i).description(); System.out.println();
        } System.out.print("Voici le jeu : "); this.getLastCard().description();
    }


    /**
     * Méthode getNbJoueurs - Getter du nombre de Player
     * 
     * @return leNombreDeJoueur
     */
    public int getNbJoueurs() {
        return this.nbJoueurs;
    }


    /**
     * Méthode getJeu - Getter du jeu [packet complet]
     * 
     * @return lePacketDeJeuActuel
     */
    public Packet getJeu() {
        return this.jeu;
    }


    /**
     * Méthode getPioche - Getter de la pioche
     * 
     * @return laPioche
     */
    public Packet getPioche() {
        return this.pioche;
    }


    /**
     * Méthode getPlayer - Getter d'un Player
     * 
     * @param index
     * @return leJoueurALIndexIndex
     */
    public Player getPlayer(int index) {
        return this.joueurs.get(index);
    }


    /**
     * Méthode getLastCard - Getter de la dernière carte de la pile
     * 
     * @return laDerniereCarteDeLaPile
     */
    public Carte getLastCard() {
        return this.getJeu().getCardByIndex(this.getJeu().pack.size() - 1);
    }
}
