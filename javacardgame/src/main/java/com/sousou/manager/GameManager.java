package com.sousou.manager;

import com.sousou.items.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.sousou.player.Player;

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
     * [Unique] Constructeur de la classe GameManager - Initialise les
     * paramètres de la partie
     */
    public GameManager() {
        int nbJoueurs = this.parameterNumber("joueurs");
        int nbCartes = this.parameterNumber("cartes");
        this.plateau = new Table(nbJoueurs, nbCartes);
    }


    /**
     * Méthode getNBJoueurs - àGetter du nombre de Player
     *
     * @return leNombreDePlayer
     */
    int getNbJoueurs() {
        return this.getPlateau().getNbJoueurs();
    }


    /**
     * Méthode getPlateau - Getter de la table
     * 
     * @return lePlateauDeLaPartie
     */
    Table getPlateau() {
        return this.plateau;
    }


    /**
     * Méthode nombreDeJoueur - Initialise un paramètre du nombre de Player/Carte
     *
     * @return nombreDeJoueur/nombreDeCartes;
     */
    final int parameterNumber(String parameter) {
        int parameterNumber;

        // CONTROLE INPUT, SI J'ENTRE AUTRE QU'UN ENTIER
        switch (parameter) {

            case "joueurs" -> {
                System.out.print("Combien de joueurs (jusqu'à 4) ? : ");

                while (!scan1.hasNextInt()) {
                    System.out.println("Paramètre invalide... ");
                    scan1.next();
                }

                parameterNumber = this.scan1.nextInt();
                while (parameterNumber < 2 || parameterNumber > 4) {

                    System.out.println("Le nombre de joueurs est invalide, essayez avec + ou - de joueurs...");
                    parameterNumber = scan1.nextInt();
                }

                break;
            }

            case "cartes" -> {
                System.out.print("Combien de cartes (jusqu'à 10) ? : ");

                while (!scan1.hasNextInt()) {
                    System.out.println("Paramètre invalide... ");
                    scan1.next();
                }

                parameterNumber = this.scan1.nextInt();
                while (parameterNumber < 1 || parameterNumber > 10) {

                    System.out.println("Le nombre de cartes est invalide, essayez avec + ou - de cartes...");
                    parameterNumber = scan1.nextInt();
                }

                break;
            }

            default ->
                throw new AssertionError();
        }
        return parameterNumber;

    }


    /**
     * Méthode propositions - Affiche les actions possibles pendant la partie
     */
    void propositions() { // MOOVE CLASS AFFICHAGE
        System.out.println("Voulez-vous poser une carte ? (tapez 1) ----- GOOD");
        System.out.println("Voulez-vous piocher une carte (tapez 2) ----- GOOD");
        System.out.println("Voulez-vous abandonner la partie ? (tapez 3) ----- GOOD\n");
        System.out.print("Votre choix : ");
    }


    /**
     * Méthode checkValuePropositions - Vérifie la validité de la selection
     * d'une action
     * 
     * @return siLaPropositionEstValable
     */
    boolean checkValuePropositions(int input) {
        return !(input > 3 || input < 1);
    }


    /**
     * Méthode estDansABS - Renvoie TRUE, si un entier index se trouve dans un
     * tableau
     *
     * @return siLIndexSeTrouveDansLeTableau
     */
    boolean estdansABS(int index, ArrayList<Integer> tab) {
        return tab.contains(index);
    }


    /**
     * Méthode compare_Array_to_tab - Renvoie un entier manquant dans la tab
     *
     * @return unEntierManquant
     */
    int compare_Array_to_tab(ArrayList<Integer> abs) {
        int[] tabIndexPlayer = new int[this.getNbJoueurs()];
        int missing = 0;

        for (int i = 0; i < this.getNbJoueurs(); i++) {
            tabIndexPlayer[i] = this.getPlateau().joueurs.get(i).getId();
        }

        for (int i : tabIndexPlayer) {
            if (!abs.contains(i)) {
                missing = i;
            }
        }

        return missing;
    }


    /**
     * Méthode game - Fonction qui gère la partie
     */
    public Player game() {

        int rang = 0;           // Index de selection des Player
        int input, choice;
        int nbj = this.plateau.getNbJoueurs();

        Player current = this.getPlateau().getPlayer(rang % nbj);           // Player actuel
        Player gagnant;         // Player gagnant
        Carte lastCard;         // Derniere carte du packet
        ArrayList<Integer> abs = new ArrayList<>();            // Table des abandons

        while (abs.size() != this.plateau.getNbJoueurs() - 1) {         // Condition de fin de partie : Ne reste qu'un Player

            System.out.println("_________________________________________________new turn ");
            this.getPlateau().description();
            System.out.println("\n");

            current = this.getPlateau().getPlayer(rang % nbj);
            lastCard = this.getPlateau().getLastCard();
            choice = -1;

            while (this.estdansABS(current.getId(), abs)) {         // Condition :  On ne selectionne pas un Player ayant déjà abandonné
                rang++;
                current = this.getPlateau().getPlayer(rang % nbj);  // On selectionne le Player suivant
            }

            System.out.println("_________________________________________________new player : ");
            current.description();
            this.getPlateau().getJeu().description();
            System.out.println();

            this.propositions();

            while (true) {          // Scanner de selection d'une proposition
                if (scan1.hasNextInt()) {
                    input = scan1.nextInt();
                    break;
                } else {
                    System.out.println("\n\nProposition invalide, entrez un choix correct...\n");
                    this.propositions();
                    scan1.next();
                } // else { while(!scan1.hasNextInt()) { input = scan1.nextInt(); } }
            }

            if (this.checkValuePropositions(input)) {

                switch (input) {
                    case 1 -> {          // Choix 1 : Player veut poser une carte
                        System.out.print("Pour revenir au menu, tapez [le chiffre] 0, sinon tapez 1 : ");

                        if (scan1.hasNextInt()) {
                            input = scan1.nextInt();
                            if (input == 0) {
                                continue;
                            } // Option de retour au menu, avec 0
                            else if (input == 1) {          // Confirmation, avec 1

                                current.getDeck().descriptionNumerotee();
                                System.out.print("\nChoisissez votre carte\n 0, si tu veux piocher parce que tu t'es trompé ou 11 pour abandonner : ");

                                if (scan1.hasNextInt()) {
                                    input = scan1.nextInt();
                                    // choice =+ input;
                                }
                                if (input == 0 || input == 11) {
                                    switch (input) {
                                        case 0 -> {         // Player pioche une carte, car il s'est trompé de choix
                                            int rand = (int) (Math.random() * this.getPlateau().getPioche().getSize());
                                            current.piocher(this.getPlateau().getPioche().defausser(this.getPlateau().getPioche().getCardByIndex(rand)));
                                            break;
                                        }
                                        case 11 -> {            // Player abandonne la partie
                                            abs.add(current.getId());
                                            if (abs.size() == this.plateau.getNbJoueurs() - 1) {
                                                current = this.getPlateau().getPlayer((rang + 1) % nbj);
                                                gagnant = current;
                                                return gagnant;
                                            }
                                        }
                                    } break;
                                }
                            }

                            choice += input;

                            while (!(current.checkValueCardChoice(choice)) // Condition : Player current selectionne une carte de son deck + controle du match avec la derniere carte du plateau
                                    || !(current.getDeck().getCardByIndex(choice).compareCouleur(lastCard)
                                    || current.getDeck().getCardByIndex(choice).compareValeur(lastCard))) {

                                System.out.print("Carte non valide, choisissez-en une autre, tapez 0 pour piocher ou 11 pour abandonner la partie : ");

                                if (scan1.hasNextInt()) {
                                    input = scan1.nextInt();

                                } else {
                                    scan1.next();
                                    System.out.println("Entrez un entier valide... ");
                                    continue;
                                }

                                if (input == 11 || input == 12) {

                                    switch (input) {

                                        case 11 -> {           // Option de pioche après erreur de selection de carte
                                            int rand = (int) (Math.random() * this.getPlateau().getPioche().getSize());
                                            current.piocher(this.getPlateau().getPioche().defausser(this.getPlateau().getPioche().getCardByIndex(rand)));
                                        }
                                        case 12 -> {           // Option d'abandon, après erreur de selection de carte
                                            abs.add(current.getId());
                                            if (abs.size() == this.plateau.getNbJoueurs() - 1) {
                                                current = this.getPlateau().getPlayer((rang + 1) % nbj);
                                                gagnant = current;
                                                return gagnant;
                                            }
                                        }
                                    } break;
                                } else { choice = input - 1; }
                            }
                        } else {
                            scan1.next();
                            System.out.println("Entrez un entier valide...");
                            continue;
                        }

                        if (input != 11) {

                            current.poserCarte(this.getPlateau().getJeu(), choice);

                            if (current.deckEmpty()) {          // Condition player gagnant
                                gagnant = current;
                                System.out.println("CC0______________________________");
                                return gagnant;
                            }
                        }

                        break;
                    }

                    case 2 -> {         // Choix 2 : Player veut piocher une carte
                        int rand = (int) (Math.random() * this.getPlateau().getPioche().getSize());
                        current.piocher(this.getPlateau().getPioche().defausser(this.getPlateau().getPioche().getCardByIndex(rand)));
                        // current.description();
                    }

                    case 3 -> {         // Choix 3 : Player décide d'abandonner la partie
                        System.out.println("Pour revenir au menu, tapez [le chiffre] 0, sinon tapez 1");
                        if (scan1.hasNextInt()) {
                            input = scan1.nextInt();
                        } else {
                            scan1.next();
                            System.out.println("Entrez un entier valide...");
                            continue;
                        }

                        if (input == 0) { continue; } // Option de retour au menu, avec 0
                        else if (input == 1) { // Confirmation, avec 1
                            abs.add(current.getId());
                            if (abs.size() == this.plateau.getNbJoueurs() - 1) {
                                current = this.getPlateau().getPlayer((rang + 1) % nbj);
                                gagnant = current;
                                return gagnant;
                            }
                        }
                    }

                    default -> {            // Par default, si une proposition n'est pas correcte, le joueur resaisie
                        System.out.println("Veuillez entrer une proposition valide...");
                        rang--;
                    }
                }

                System.out.print("\033[2J\033[H");          // efface, puis remonte le curseur
                System.out.flush();         // Force la remontée, par l'envoie de la sequence precedante
            } else {
                rang--;
                System.out.print("\033[2J\033[H");          // efface, PUIS remonte le curseur
                System.out.flush();         // Force la remontée, par l'envoie de la sequence precedante
                System.out.println("\n\n\n\nProposition invalide, entrez un choix correct...");
            }

            rang++;
            System.out.print("\033[2J\033[H"); // efface, PUIS remonte le curseur
            System.out.flush();         // Player prec = this.getPlateau().getPlayer((rang - 1) % nbj);;
        }

        System.out.print("\033[2J\033[H");          // efface, PUIS remonte le curseur
        System.out.flush();         // Force la remontée, par l'envoie de la sequence precedante

        return current;
    }

    Player game_final() { return new Player(); }

    /*
        public static void main(String[] args) {
            GameManager g = new GameManager();
            System.out.println("Nos félicitations : " + g.game().getNom() + ", vous êtes le grand gagnant");
        }
    */
}
