package manager;

import java.util.Scanner;

public class PrintManager {

    private final Scanner scan;

    public PrintManager() {
        scan = new Scanner(System.in);
    }


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
        System.out.println(prompt  /* Méthode str pour renvoyer du texte */);
        while (!this.scan.hasNextInt()) {
            scan.next();
            System.out.println(this.printError2(5));
            System.out.println(this.printError2(errorId));
        } return scan.nextInt();
    }       // Méthode 1


    public void lineJump(int lineJumped) {
        for (int i = 0; i < lineJumped; i++) {
            System.out.println();
        }
    }


    public void separation() {
        System.out.println("____________________________________________________________");
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



    public String printError2(int errorIdentificationchoice) {

        switch (errorIdentificationchoice) {            
            case 1 : return "\n\nProposition invalide, entrez un choix correct...\n";
            case 2 : return "Pour revenir au menu, tapez [le chiffre] 0, sinon tapez 1 : ";
            case 3 : return "\nChoisissez votre carte\n 0, si tu veux piocher parce que tu t'es trompé ou 11 pour abandonner : ";
            case 4 : return "Carte non valide, choisissez-en une autre, tapez 0 pour piocher ou 11 pour abandonner la partie : ";
            case 5 : return "Entrez un entier valide... ";
            default : return "";
        }
    }


    public void vaccum() {
        System.out.print("\033[2J\033[H");          // efface, puis remonte le curseur
        System.out.flush();         // Force la remontée, par l'envoie de la sequence precedante
    }
}
