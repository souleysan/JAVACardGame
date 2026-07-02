package player;
import items.*;
import java.util.ArrayList;

public class Player {

    enum Nom {PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4};

    Nom nom;
    Packet deck;
    static int identifiant = 0;

    public Player(/*int nombreDeCarte*/) {
        // Ajout d'un 2e constructeur où on pourra choisir son nom avant une partie.
        this.nom = Player.Nom.values()[this.getIdentifiant()];
        this.deck = new Packet();
        // this.initPlayerDeck(deck, nombreDeCarte);
        Player.identifiant++;
    }

    public void description() {
        System.out.println(this.getNom() + " __________");
        this.deck.description();
    }

    public void initPacket(Packet general, int num) {
        this.deck.initPacket(general, num);
    }

    Nom getNom() { return this.nom; }
    Packet getDeck() { return this.deck; }
    ArrayList<Carte> getPack() { return this.deck.pack; }
    int getIdentifiant() { return Player.identifiant; }
    Carte getCardByIndex(int index) { return this.deck.getCardByIndex(index); }

    void piocher(Carte e) { this.deck.piocher(e); }
    void piocherDeux(Packet general) { this.deck.piocherDeux(general); }
    void piocherCinq(Packet general) { this.deck.piocherCinq(general); }
    
    void poserCarte(Packet table, int e) { table.piocher(this.getCardByIndex(e)); }
    void passerTour() {} // On verra plus tard



    public static void main (String [] args) {


        Player p = new Player();
        p.piocher(new Carte(Carte.Valeur.AS, Carte.Couleur.CARREAUX));
        p.description();


    }

}