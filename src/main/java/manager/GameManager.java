package manager;

import items.*;
import java.util.Scanner;
import player.Player;

public class GameManager {

    Table plateau;
    Scanner scan1 = new Scanner(System.in);
    Scanner scan2 = new Scanner(System.in);
    Scanner scan3 = new Scanner(System.in);

    public GameManager() {
        int nbJoueurs = this.nombreDeJoueur();
        int nbCartes = this.nombreDeCarte();
        this.plateau = new Table(nbJoueurs, nbCartes);
    }

    int getNbJoueurs() { return this.getPlateau().getNbJoueurs(); }

    Table getPlateau() {
        return this.plateau;
    }

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

    void propositions() {
        System.out.println("Voulez-vous poser une carte ? (tapez 1) ----- PROCESS");
        System.out.println("Voulez-vous piocher une carte (tapez 2) ----- GOOD");
        System.out.println("Voulez-vous abandonner la partie ? (tapez 3) ----- GOOD");
       // System.out.println("Retour ? (tapez 4) ----- PROCESS\n");     //      OPTION SUPPLEMENTAIRE

    }

    void checkValuePropositions(int input) {
        while (input > 3 || input < 1) {
            System.out.println("Entree invalide... reesayez");
            input = scan3.nextInt();
        }
        // return input; // <= 4 && input >= 0;
    }


    void game() { // Subdivision des taches, impérative !

        int rang = 0;
        int nbj, input, choice;
        Player gagnant;

        while (!this.plateau.resteUnJoueur()) {


            System.out.println("_________________________________________________\nnew turn ");
            this.getPlateau().description(); // -------------------------------------------------------- "La table est composée de..."
            System.out.println("\n");

            nbj = this.plateau.getNbJoueurs();
            Carte lastCard = this.getPlateau().getLastCard();
            Player current = this.plateau.selectPlayer(rang % nbj);

            System.out.println("_________________________________________________\nnew player : ");
            current.description();

            this.propositions();
            input = scan3.nextInt();

            this.checkValuePropositions(input);


            // IMBRICAGE WHILE (TANT QUE L'UNE DES VALEURS N'EST PAS CORRECTE)
            if (input == 1) { // Le joueur pose une carte


                // OPTION DE RETOUR AX PROPOSITIONS


                System.out.println("Choisissez votre carte : ");
                input = scan3.nextInt();
                choice = input - 1;

                while (!(current.checkValueCardChoice(choice)
                        && (current.getDeck().getCardByIndex(choice).compareTotal(lastCard)
                        || current.getDeck().getCardByIndex(choice).compareCouleur(lastCard)
                        || current.getDeck().getCardByIndex(choice).compareValeur(lastCard)))) {

                    current.poserCarte(this.getPlateau().getJeu(), choice);
                    input = scan3.nextInt();
                    choice = input - 1;

                }

                current.poserCarte(this.getPlateau().getJeu(), scan3.nextInt());
                this.getPlateau().description();

                break;

            }
            
            
            else if (input == 2) {  // OK

                int rand = (int) (Math.random() * this.getPlateau().getPioche().getSize());
                current.piocher(this.getPlateau().getPioche().defausser( this.getPlateau().getPioche().getCardByIndex(rand) ));
                current.description();

            } 
            
            
            else if (input == 3) { // Le joueur abandone la partie + CONFIRMATION ----------------------------------------------- OK


                /*
                OPTION DE RETOUR - CONFIRMATION
                ---> System.out.println("Etes-vous sûr ???\n Tapez o pour quitter, sinon, tapez n : ");
                ---> String str = scan3.nextLine();
                */


                /*
                if (this.getNbJoueurs() == 2) { //  S'il ne reste que 2 joueur, l'autre gagne par abandon du current.
                    gagnant = this.getPlateau().selectPlayer(rang + 1);
                    System.out.print("le grad gagnant : ");
                    gagnant.description();
                    break;
                }
                */

                this.getPlateau().removePlayer(rang);
                this.getPlateau().reduceNbJoueur();
                rang = -1;
                // (rang - 1) % this.getNbJoueurs();
                
                // JOUEURS  :  J1   J2   J3   J4
                // _____________________________________________________
                // normal   :   0    1    2    3   % this.nbJoueurs()
                // remove1  :   4    5    6   //   % this.nbJoueurs()
                // remove1  :   7    8    9   //
                // normal   :   8    9   10   11   % this.nbJoueurs()

            }

            // S'IL NE RESTE QU'UN JOUEUR, IL GAGNE LA PARTIE ! ----------------------------------------------------------------OK
                if (this.getPlateau().resteUnJoueur()) {
                    // gagnant = current;
                    gagnant = this.getPlateau().selectPlayer(0);
                    System.out.println(gagnant.getNom() + ", vous êtes le grand gagnant de la partie");

                    System.out.println("partie condition while 3 - abandon 2222222222222222222222&");

                    // return gagnant;
                }

            rang++;
            System.out.println("________________________________________________________________________");

        }





        // PAS OBLIGATOIRE, SUPPRIMAABLE MÊME (SUITE)
        // S'IL NE RESTE QU'UN JOUEUR, IL GAGNE LA PARTIE ! ----------------------------------------------------------------OK

        /*
        if (this.getPlateau().resteUnJoueur()) {
            // gagnant = this.getPlateau().selectPlayer(0);
            System.out.println(this.getPlateau().selectPlayer(0).getNom() + ", vous êtes le grand gagnant de la partie");

            System.out.println("partie condition while 3 - abandon 3333333333333333");
            // return gagnant;
        }
        */
    }

    public static void main(String[] args) {

        GameManager g = new GameManager();
        g.game();

    }

}
