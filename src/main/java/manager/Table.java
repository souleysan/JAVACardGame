package manager;
import items.*;
import java.util.ArrayList;
import player.*;

public class Table {

    ArrayList<Player> joueurs;
    Packet jeu, pioche;

    public Table() {
        this.joueurs= new ArrayList<>();
        this.jeu = new Packet();
        this.jeu.init();
        this.pioche = new Packet();
    }

}