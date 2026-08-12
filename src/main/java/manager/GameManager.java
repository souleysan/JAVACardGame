package manager;

import items.*;
import java.util.ArrayList;
import java.util.Scanner;
import player.Player;

public class GameManager {

    /**
     * Attribut Table de la Classe GameManager
     */
    Table plateau;
    /**
     * Scanner num de la Classe GameManager    
     */
    Scanner scan1 = new Scanner(System.in);
    /**
     * Scanner num de la Classe GameManager    
     */
    Scanner scan2 = new Scanner(System.in);
    /**
     * Scanner num de la Classe GameManager    
     */
    Scanner scan3 = new Scanner(System.in);


    /**
     * [Unique] Constructeur de la classe GameManager - Initialise les paramètres de la partie
     */
    public GameManager() {
        int nbJoueurs = this.nombreDeJoueur();
        int nbCartes = this.nombreDeCarte();
        this.plateau = new Table(nbJoueurs, nbCartes);
    }


    /**
     * Méthode getNBJoueurs - àGetter du nombre de Player
     * 
     * @return leNombreDePlayer
     */
    int getNbJoueurs() { return this.getPlateau().getNbJoueurs(); }


    /**
     * Méthode getPlateau - Getter de la table
     */
    Table getPlateau() { return this.plateau; }


    /**
     * Méthode nombreDeJoueur - Initialise le paramètre du nombree de Player
     * 
     * @return nombreDeJoueur;
     */
    int nombreDeJoueur() { //---------------------------------------------------------------------------------------------GESTION D'ERREUR
        System.out.print("Combien de joueurs (jusqu'à 4) ? ");
        int nombreDeJoueur = this.scan1.nextInt();

        /* ---------------------------------------------------------------------------------------------GESTION D'ERREUR
        while (nombreDeJoueur < 2 && nombreDeJoueur > 4) {
            System.out.println("Le nombre de joueurs est invalide, essayez avec + ou - de joueurs..."); // CORRIGER DANS TABLE.INITPLAYERS()  -->  SI NOMBREDEJOUEURS...
            this.nombreDeJoueur();
        }
        */

        return nombreDeJoueur;
    }


    /**
     * Méthode nombreDeCarte - Initialise le paramètre du nombree de carte par Player
     * 
     * @return nombreDeJoueur;
     */
    int nombreDeCarte() { //---------------------------------------------------------------------------------------------GESTION D'ERREUR
        System.out.print("Combien de cartes (jusqu'à 10) ? ");
        int nombreDeCartes = this.scan2.nextInt();

        /* ---------------------------------------------------------------------------------------------------------------GESTION D'ERREUR
        while (nombreDeCartes < 2 && nombreDeCartes > 4) {
            System.out.println("Le nombre de joueurs est invalide, essayez avec + ou - de joueurs..."); // CORRIGER DANS TABLE.INITPACKET()  -->  SI nombreDeCartes...
            this.nombreDeCartes();
        }
        */

        return nombreDeCartes;
    }


    /**
     * Méthode propositions - Affiche les actions possibles pendant la partie
     * MOVE CLASSE AFFICHAGE !
     */
    void propositions() {
        System.out.println("Voulez-vous poser une carte ? (tapez 1) ----- PROCESS");
        System.out.println("Voulez-vous piocher une carte (tapez 2) ----- GOOD");
        System.out.println("Voulez-vous abandonner la partie ? (tapez 3) ----- STAND BY");
    }


    /** 
     * Méthode checkValuePropositions - Vérifie la validité de la selection d'une action
     */
    void checkValuePropositions(int input) {
        while (input > 3 || input < 1) {
            System.out.println("Entree invalide... reesayez");
            input = scan3.nextInt();
        }
    }


    /**
     * Méthode estDansABS - Renvoie TRUE, si un entier index se trouve dans un tableau
     * 
     * @return siLIndexSeTrouveDansLeTableau
     */
    boolean estdansABS(int index, ArrayList <Integer> tab) {
        return tab.contains(index);
    }


    /**
     * Méthode compare_Array_to_tab - Renvoie un entier manquant dans la tab
     * 
     * @return unEntierManquant
     */
    int compare_Array_to_tab(ArrayList<Integer> abs) {
        int [] tabIndexPlayer = new int [this.getNbJoueurs()];
        int missing = 0;

        for (int i = 0; i < this.getNbJoueurs(); i++) {
            tabIndexPlayer[i] = this.getPlateau().joueurs.get(i).getId();
        }

        for (int i : tabIndexPlayer) {
            if (!abs.contains(i)) { missing = i; }
        }
        
        return missing;
    }


    /**
     * Méthode game - Foction qui gère la partie
     */
    void game() {

        int rang = 0;           // Index de selection des Player
        Player gagnant;         // Playre gagnant

        int nbj, input, choice;
        ArrayList <Integer> abs = new ArrayList<>();            // Table des abandons
    

        while (abs.size() != this.plateau.getNbJoueurs() - 1) {         // Condition de fin de partie : Ne reste qu'un Player

            System.out.println("_________________________________________________\nnew turn ");
            this.getPlateau().description();
            System.out.println("\n");

            nbj = this.plateau.getNbJoueurs();
            Player current = this.getPlateau().getPlayer(rang % nbj);

            Carte lastCard = this.getPlateau().getLastCard();


            while (this.estdansABS(current.getId(), abs)) {         // Condition :  On ne selectionne pas un Player ayant déjà abandonné
                rang++;
                current = this.getPlateau().getPlayer(rang % nbj);  // On selectionne le Player suivant
            }


            System.out.println("_________________________________________________\nnew player : ");
            current.description();
                
            this.propositions();
            input = scan3.nextInt();            // Scanner de selection d'une proposition

            this.checkValuePropositions(input); // IMBRICAGE WHILE : TANT QUE LE CHECKVALUEPROPOSITION EST FAUX, ON REDEMANDE
            

            if (input == 1) {           // Choix 1 : Player pose une carte

                System.out.println("Pour revenir au menu, tapez [le chiffre] 0, sinon tapez 1");
                if (scan3.nextInt() == 0) {         // Option de retour au menu, avec 0
                    continue;
                } else if (scan3.nextInt() == 1) {          // Confirmation, avec 1

                    System.out.println("Choisissez votre carte (Tapez le numéro de la carte souhaitée) : ");
                    input = scan3.nextInt();
                    choice = input - 1;

                    /*while (!(current.checkValueCardChoice(choice)
                            && (current.getDeck().getCardByIndex(choice).compareTotal(lastCard)
                            || current.getDeck().getCardByIndex(choice).compareCouleur(lastCard)
                            || current.getDeck().getCardByIndex(choice).compareValeur(lastCard)))) {
                    */
                        current.poserCarte(this.getPlateau().getJeu(), choice);
                        input = scan3.nextInt();
                        // choice = input - 1;

                    // }

                    current.poserCarte(this.getPlateau().getJeu(), scan3.nextInt());
                    this.getPlateau().description();

                    // break;
                }
            }
            
            else if (input == 2) {          // Choix 2 : Player pioche une carte [aléatoire]

                int rand = (int) (Math.random() * this.getPlateau().getPioche().getSize());
                current.piocher(this.getPlateau().getPioche().defausser( this.getPlateau().getPioche().getCardByIndex(rand) ));
                current.description();

            }
            
            else if (input == 3) {          // Choix 3 : Player décide d'abandonner

                System.out.println("Pour revenir au menu, tapez [le chiffre] 0, sinon tapez 1");
                input = scan3.nextInt();

                if (input == 0) {           // Option de retour au menu, avec 0
                    continue;
                } 
                
                else if (input == 1) {          // Confirmation, avec 1
                    abs.add(current.getId());
                }
            }


            // Ajouter condition si deck vide


            if (abs.size() == this.getNbJoueurs() - 1) {           // Condition : Il ne reste qu'un joueur après l'abandon du Player actuel

                current = this.getPlateau().getPlayer( this.compare_Array_to_tab(abs) % nbj);
                gagnant = current;

                System.out.println(gagnant.getNom() + ", vous êtes le grand gagnant de la partie");
            }

        }
    }

    public static void main(String[] args) {

        GameManager g = new GameManager();
        g.game();

    }
}
