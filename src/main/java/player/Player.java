package player;
import items.*;

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
    

    Nom getNom() { return this.nom; }
    int getIdentifiant() { return Player.identifiant; }

    void piocher(Carte e) { this.deck.piocher(e); }

}