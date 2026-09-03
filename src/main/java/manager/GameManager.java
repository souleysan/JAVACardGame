package manager;

import java.util.ArrayList;
import java.util.Scanner;

import items.Carte;
import items.Packet;
import player.Player;

public class GameManager {

    /**
     * Attribut Table de la Classe GameManager
     */
    Table plateau;
    /**
     * Attribut scan1 de la Classe GameManager
     */
    Scanner scan1 = new Scanner(System.in);
    /**
     * Attribut printManager de la Classe GameManager
     */
    PrintManager printManager;
    /**
     * Enumerateur d'etat de la Classe GameManager
     */
    static enum State {CARTE_POSEE, A_PIOCHER, A_ABANDONNE, RETOUR_MENU}
    /**
     * Attribut abs de la Classe GameManager - Le tableau des abandons
     */
    static ArrayList<Integer> abs = new ArrayList<>();
    /**
     * Attribut game_rang de la Classe GameManager
     */
    int game_rang = 0;

    /**
     * [Unique] Constructeur de la classe GameManager - Initialise les
     * paramètres de la partie
     */
    public GameManager() {
        int nbJoueurs = this.parameterNumber("joueurs");
        int nbCartes = this.parameterNumber("cartes");
        this.plateau = new Table(nbJoueurs, nbCartes);
        this.printManager = new PrintManager();
    }


    /**
     * Méthode getNBJoueurs - Getter du nombre de Player
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
     * Methode getAbs - Renvoie la table des abandons
     * 
     * @return laTableDesAbandons
     */
    ArrayList <Integer> getAbs() {
        return GameManager.abs;
    }


    /**
     * Methode getPM - Getter de l'attribut printManager
     * 
     * @return printManager
     */
    public PrintManager getPM() {
        return this.printManager;
    }


    /**
     * Methode getLastCard - Renvoie la derniere carte de la table
     * 
     * @return laDerniereCarteDeLaTable
     */
    Carte getLastCard() {
        return this.getPlateau().getLastCard();
    }


    /**
     * Methode getJeu - Renvoie le deck de jeu de la partie
     * 
     * @return leDeckDeJeuDeLaPartie
     */
    Packet getJeu() {
        return this.getPlateau().getJeu();
    }


    /**
     * Methode getJoueur - Renvoie un joueur cible
     * 
     * @param player_id
     * 
     * @return unJoueurCible
     */
    public Player getJoueur(int player_id) {
        return this.getPlateau().getPlayer(player_id);
    }


    /**
     * Methode getPlayer_list - Getter de la liste des joueurs de la partie
     * 
     * @return laListeDesJoueursDeLaPartie
     */
    public ArrayList <Player> getPlayer_list() {
        return getPlateau().getPlayerList();
    }


    /**
     * Methode getWinner - Renvoie le gagant de la partie
     * 
     * @return leGagantDeLaPartie
     */
    Player getWinner() {
        for (Player p : this.getPlayer_list()) {
            if (p.getWin()) {
                return p;
            }
        } return null;
    }

    
    /**
     * Methode - Renvoie l'index d'avancee de la partie
     * 
     * @return indexDAvancee
     */
    int get_game_rang() {
        return this.game_rang;
    }


    /**
     * Méthode nombreDeJoueur - Initialise un paramètre du nombre de Player/Carte
     *
     * @return nombreDeJoueur/nombreDeCartes;
     */
    final int parameterNumber(String parameter) {
        int parameterNumber;

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
     * Méthode compare_Array - Renvoie l'id du joueur manquant dans la table des abandons
     * Le seul n'ayant pas abandonné
     *
     * @return unEntierManquant
     */
    public int compare_Array() {
        int[] tab_Player_Index = new int[this.getNbJoueurs()];

        for (int i = 0; i < this.getNbJoueurs(); i++) {
            tab_Player_Index[i] = this.getJoueur(i).getId();
        }

        int missing = 0;
        for (int i : tab_Player_Index) {
            if (!this.getAbs().contains(i)) {
                missing = i;
            }
        }

        return missing;
    }


   /**
     * Methode addToAbs - Ajoute le joueur cible à la table des abandons
     * 
     * @param player_targeted
     */
    void addToAbs(Player player_targeted) {
        this.getAbs().add(player_targeted.getId());
    }

    
    /**
     * Méthode check_choice2 - Verifie la validite d'une carte, par rapport à un joueur
     */
    boolean check_choice2(Player player_targeted, int input) {
        return player_targeted.checkValueCardChoice(input);
    }


    /**
     * Methode piocher - Permet à un joueur de piocher
     */
    void piocher(Player player_targeted) {
        int rand = (int) (Math.random() * this.getPlateau().getPioche().getSize());
        player_targeted.piocher(this.getPlateau().getPioche().defausser(this.getPlateau().getPioche().getCardByIndex(rand)));
    }


    /**
     * Methode poser_carte - Permet à un joueur de poser une carte
     * 
     * @param player_targeted
     * @param card_id
     */
    void poser_carte(Player player_targeted, int card_id) {
        player_targeted.poserCarte(this.getJeu(), card_id);
    }


    /**
     * Methode deckEmpty - Renvoie le remplissage du deck du joueur cible
     * 
     * @return siLeDeckEstVide
     */
    boolean deckEmpty(Player player_targeted) {
        return player_targeted.deckEmpty();
    }


    /**
     * Methode dernier_joueur - Renvoie s'il ne reste qu'un joueur dans la partie
     * 
     * @return sIlNeResteQuUnJoueur
     */
    boolean dernier_joueur() {
        return this.getAbs().size() == this.getNbJoueurs() - 1;
    }


    /**
     * Methode winner_here - Renvoie si un joueur possede l'etat de gagant
     * 
     * @return etatDeVictoireDUnJoueur
     */
    boolean winner_here() {
        for (Player p : this.getPlayer_list()) {
            if (p.getWin()) {
                return true;
            }
        } return false;
    }


    /**
     * Methode - Initialise l'index d'avancee de la partie 
     * 
     * @param e
     */
    void set_game_rang(int e) {
        this.game_rang = e;
    }


    /**
     * Methode - Incremente l'index d'avancee de la partie
     * 
     * @return lIndexDAvancee
     */
    int increase_rang() {
        this.set_game_rang(this.get_game_rang() + 1);
        return this.get_game_rang();
    }


    /**
     * Methode - Decremente l'index d'avancee de la partie
     * 
     * @return lIndexDAvancee
     */
    int decrease_rang() {
        this.set_game_rang(this.get_game_rang() - 1);
        return this.get_game_rang();
    }


    /**
     * Methode describe_game_players - Description des informations des joueurs de la partie
     */
    public void describe_game_players() {
        this.getPM().lineJump(1);
        String player_list = "";
        for (Player p : this.getPlayer_list()) {
            player_list += p.getNom() + " : " + p.getNbCartes() + " cartes restantes\n";
        }
        System.out.println(player_list);
        this.getPM().lineJump(2);
    }


    /**
     * Methode describe_game_card - Description de la derniere carte en jeu
     */
    public void describe_game_card() {
        this.getPM().describe_game_card(this);
    }


    /**
     * Methode describe_current_player - Description du joueur actuel
     */
    public void describe_current_player(Player player_targeted) {
        this.getPM().describe_current_player(player_targeted);

    }


    /**
     * Methode getPioche - Getter de la pioche du jeu
     * 
     * @return laPiocheDuJeu
     */
    Packet getPioche() {
        return this.getPlateau().getPioche();
    }


    /**
     * Methode reload_pioche - Rafraichit la pioche en ajoutant les cartes [inutiles] de la pile de jeu
     */
    public void reload_pioche() {
        this.getPioche().initPioche(this.getJeu());
    }




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Méthode game - Fonction qui gère la partie
     */
    Player game_1() {

        int rang = 0;           // Index de selection des Player
        int input, choice;
        int nbj = this.getNbJoueurs();

        Player current = this.getPlateau().getPlayer(rang % nbj);           // Player actuel
        Player gagnant;         // Player gagnant
        Carte lastCard;         // Derniere carte du packet
        ArrayList<Integer> abs = new ArrayList<>();            // Table des abandons

        while (abs.size() != this.getNbJoueurs() - 1) {         // Condition de fin de partie : Ne reste qu'un Player
    
            printManager.separation();
            this.getPlateau().description();
            printManager.lineJump(2);

            current = this.getPlateau().getPlayer(rang % nbj);
            lastCard = this.getPlateau().getLastCard();
            choice = -1;

            while (this.estdansABS(current.getId(), abs)) {         // Condition :  On ne selectionne pas un Player ayant déjà abandonné
                rang++;
                current = this.getPlateau().getPlayer(rang % nbj);  // On selectionne le Player suivant
            }

            printManager.separation();
            current.description();
            this.getPlateau().getJeu().description();
            printManager.lineJump(1);

            printManager.propositions();

            while (true) {          // Scanner de selection d'une proposition
                if (scan1.hasNextInt()) {
                    input = scan1.nextInt();
                    break;
                } else {
                    printManager.printError(1);
                    printManager.propositions();
                    scan1.next();
                } // else { while(!scan1.hasNextInt()) { input = scan1.nextInt(); } }
            }

            if (this.checkValuePropositions(input)) {

                switch (input) {
                    case 1 -> {          // Choix 1 : Player veut poser une carte
                        printManager.printError(2);

                        if (scan1.hasNextInt()) {
                            input = scan1.nextInt();
                            if (input == 0) {
                                continue;
                            } // Option de retour au menu, avec 0
                            else if (input == 1) {          // Confirmation, avec 1

                                current.getDeck().descriptionNumerotee();
                                printManager.printError(3);

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
                                            if (abs.size() == this.getNbJoueurs() - 1) {
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

                                printManager.printError(4);

                                if (scan1.hasNextInt()) {
                                    input = scan1.nextInt();

                                } else {
                                    scan1.next();
                                    printManager.printError(5);
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
                                            if (abs.size() == this.getNbJoueurs() - 1) {
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
                            printManager.printError(5);
                            continue;
                        }

                        if (input != 11) {

                            current.poserCarte(this.getPlateau().getJeu(), choice);

                            if (current.deckEmpty()) {          // Condition player gagnant
                                gagnant = current;
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
                        printManager.printError(2);
                        if (scan1.hasNextInt()) {
                            input = scan1.nextInt();
                        } else {
                            scan1.next();
                            printManager.printError(5);
                            continue;
                        }

                        if (input == 0) { continue; } // Option de retour au menu, avec 0
                        else if (input == 1) { // Confirmation, avec 1
                            abs.add(current.getId());
                            if (abs.size() == this.getNbJoueurs() - 1) {
                                current = this.getPlateau().getPlayer((rang + 1) % nbj);
                                gagnant = current;
                                return gagnant;
                            }
                        }
                    }

                    default -> {            // Par default, si une proposition n'est pas correcte, le joueur resaisie
                        printManager.printError(1);    
                        rang--;
                    }
                }

                printManager.vaccum();
            } else {
                rang--;
                printManager.vaccum();
                printManager.printError(1);
            }

            rang++;
            printManager.vaccum();
        }

        printManager.vaccum();
        return current;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    /**
     * Methode action - Chargee de proposer et valider les chiox de jeu, faits au joueur actuel
     * 
     * @return unEtatDeJeuDuJoueur
     */
    State action() {

        this.getPM().propositions2();
        this.getPM().lineJump(1);
        int input = this.getPM().checkInput2(4, this.getPM().printError2(1));

        switch (input) {
            case 0 -> { return State.RETOUR_MENU; }
            case 1 -> { return State.CARTE_POSEE; }
            case 2 -> { return State.A_PIOCHER; }
            case 100 -> { return State.A_ABANDONNE; }
            default -> {
                this.getPM().printError2(4);
                return State.RETOUR_MENU;
            }
        }
    }


    /**
     * Methode turn - Orchestre la logique de jeu selon l'etat du joueur actuel
     * 
     * @param player_targeted
     */
    void turn(Player player_targeted) {


        // REFLEXION :
            // - ASSIGNEMENT DES ETAT AUX JOUEURS (CLASSE PLAYER)
            // - EN FONCTION DE L'ETAT DU JOUEUR, ON AFFICHE DANS LA DESCRIPTION (describe_game_players())
            // FERA L'OBJET D'UNE NOUVELLE LIGNE D'ETAT SOUS LES CARTES RESTANTES
                // - ex : PLAYER_1 ABANDONNE --> descr : PLAYER_1 : A_ABANDONNE
                // - ex : PLAYER_4 PIOCHE --> descr : PLAYER_4 : A_PIOCHE


        switch(action()) {
            case RETOUR_MENU -> {
                this.getPM().cleaning();
                this.decrease_rang();
            }

            case CARTE_POSEE -> {

                int input = this.getPM().checkInput2(4, "Choisissez votre carte : ");
                Carte lastCard = this.getLastCard();

                if (this.check_choice2(player_targeted, input) && lastCard.association(player_targeted.getCardByIndex(input - 1))) {

                    this.reload_pioche();
                    this.poser_carte(player_targeted, input - 1);

                    if (this.deckEmpty(player_targeted)) {
                        player_targeted.setWin();
                    }
                } else { this.decrease_rang(); }
            }

            case A_PIOCHER -> {
                this.piocher(player_targeted);
            }

            case A_ABANDONNE -> {
                addToAbs(player_targeted);

                if (this.dernier_joueur()) {
                    this.getJoueur(this.compare_Array()).setWin();
                }
            }
        }
    }


    /**
     * Methode game_2 - Chargee d'organiser la partie
     * 
     * @return leGagantDeLaPartie
     */
    public Player game_2() {

        this.getPM().cleaning();
        Player current;

        while (!this.winner_here()) {

            current = this.getPlateau().getPlayer(this.get_game_rang() % this.getNbJoueurs());
            
                this.describe_game_players();// current.description();
                this.describe_current_player(current);
                this.describe_game_card();// this.getPlateau().getJeu().description();
                printManager.lineJump(1);//

            this.turn(current);
            this.increase_rang();

            this.getPM().cleaning();
        }

        System.out.println(this.getWinner().getNom() + " vous avez gagné, mes félicitations 🎉");       // MIGRATION PRINTMANAGER


        // REFLEXION :
            // - PROPOSITION DE RELANCE D'UNE PARTIE, SELON "Oui"/"Non"
                // - SI "OUI"   :   RENVOIE LA LISTE DE TOUS LES GAGNANTS CONSECUTIFS --> game_consecutives
                // - SI "NON"   :   game_2 RENVOIE SON GAGNANT (COMME HABITUELLEMENT)

        return this.getWinner();
    }




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {

        GameManager gamecontrol = new GameManager();

        gamecontrol.game_2();
        // System.out.println("Nos félicitations : " + gamecontrol.game().getNom() + ", vous êtes le grand gagnant");

    }
}
