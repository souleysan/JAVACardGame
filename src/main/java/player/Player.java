package player;
import items.*;
import java.util.ArrayList;

public class Player {

    enum Nom {PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4};

    Nom nom;
    Packet deck;
    static int identifiant = 1;

    public Player() {
        // AJOUT D'UN CONSTRUCTEUR OÙ L'ON POURRA CHOISIR SON NOM
        this.nom = Player.Nom.values()[this.getIdentifiant()];
        this.deck = new Packet();
        Player.identifiant++;
    }

    public void description() {
        System.out.println(this.getNom() + " __________"); this.deck.description();
    }

    Nom getNom() { return this.nom; }
    ArrayList<Carte> getPack() { return this.deck.pack; }
    int getIdentifiant() { return Player.identifiant; }

    void piocher(Carte e) { this.deck.piocher(e); }
    // void piocherDeux(Packet general) { this.deck.piocherDeux(general); }
    // void piocherCinq(Packet general) { this.deck.piocherCinq(general); }
    
    void poserCarte(Packet general, int e) {
        general.piocher(
            this.getPack().get(e));
    }


}