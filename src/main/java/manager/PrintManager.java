package manager;

import java.util.Scanner;

import player.Player;

public class PrintManager {

    /**
     * Attribut scan de la Classe PrintManager
     */
    private final Scanner scan;

    /**
     * [Unique] Constructeur de la Classe PrintManager
     */
    public PrintManager() {
        this.scan = new Scanner(System.in);
    }

    // AJOUT DU CODE COULEUR EN FONCITON DU JOUEUR + CLASSE PLAYER


    /**
     * Methode lineJump - Parametre les sauts de ligne
     * 
     * @param lineJumped
     */
    public void lineJump(int lineJumped) {
        for (int i = 0; i < lineJumped; i++) {
            System.out.println(" ");
        }
    }


    /**
     * Methode separation - Affiche une separation
     */
    public void separation() {
        System.out.println("____________________________________________________________");
    }


    /**
     * Methode vaccum - Remonte le terminal pour un affichage initial (en haut à gauche)
     */
    public void vaccum() {
        System.out.print("\u001B[H\u001B[2J");
        System.out.flush();         // Force la remontée, par l'envoie de la sequence precedante
    }


    public void cleaning(){
        this.lineJump(15);
        this.vaccum();
        this.separation();
    }

///////////////  TO SUPP  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Méthode propositions - Affiche les actions possibles pendant la partie
     */
    public void propositions() {
        System.out.println("Voulez-vous poser une carte ? (tapez 1) ----- GOOD");
        System.out.println("Voulez-vous piocher une carte (tapez 2) ----- GOOD");
        System.out.println("Voulez-vous abandonner la partie ? (tapez 3) ----- GOOD\n");
        System.out.print("Votre choix : ");
    }


    public int checkInput(int errorId, String prompt) {
        System.out.print(prompt);
        while (!this.scan.hasNextInt()) {
            scan.next();
            this.printError(errorId);
        } return scan.nextInt();
    }

    
    public void printError(int errorIdentification) {

        switch (errorIdentification) {            
            case 1 -> { System.out.println("\n\nProposition invalide, entrez un choix correct...\n"); break; }
            case 2 -> { System.out.print("Pour revenir au menu, tapez [le chiffre] 0, sinon tapez 1 : "); break; }
            case 3 -> { System.out.print("\nChoisissez votre carte\n 0, si tu veux piocher parce que tu t'es trompé ou 11 pour abandonner : "); break; }
            case 4 -> { System.out.print("Carte non valide, choisissez-en une autre, tapez 0 pour piocher ou 11 pour abandonner la partie : "); break; }
            case 5 -> { System.out.println("Entrez un entier valide... "); break; }
            default -> { }
        }
    }

///////////////  OFFICIAL  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Methode propositions2 - Affichage des propositions de jeu
     */
    public void propositions2() {
        this.separation();
        System.out.print("""
            Voulez-vous retourner au menu principal ? (tapez 0)
            Voulez-vous poser une carte ? (tapez 1)
            Voulez-vous piocher une carte (tapez 2)
            Voulez-vous abandonner la partie ? (tapez 100)
        """);
    }


    /**
     * Methode checkInput2 - Controle des entrees [Integer] du terinal
     * 
     * @param errorId
     * @param prompt
     * 
     * @return unEntierCorrect
     */
    public int checkInput2(int errorId, String prompt) {
        System.out.print(prompt);
        while (!this.scan.hasNextInt()) {
            scan.next();
            System.out.println(this.printError2(errorId));
        } return scan.nextInt();
    }


    /**
     * Methode printError2 - Centralise et affiche les messages d'erreur personalises
     */
    public String printError2(int errorIdentificationchoice) {

        switch (errorIdentificationchoice) {          
            case 1 -> { return "Entrez votre choix : "; }
            case 2 -> { return "Entrez un entier valide... "; }
            case 3 -> { return "Selectionnez une autre card\n0, si vous voulez piocher\n11, pour abandonner : "; }
            case 4 -> { return "Proposition invalide, entrez un choix correct..."; }
            default -> { return "\n"; }
        }
    }


    public void describe_game_card(GameManager game) {                          // MIGRATION PRINTMANAGER
        System.out.print("Carte en jeu : ");
        game.getLastCard().description();
    }


    public void describe_current_player(Player player_targeted) {
        System.out.print("Player actuel : ");
        player_targeted.description();
    }

}
