package items;
import java.util.ArrayList;

public class Packet {

    ArrayList<Carte> pack;

    public Packet() {
        this.pack = new ArrayList<>();
    }

    public void init() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Carte carte = new Carte(Carte.Valeur.values()[j], Carte.Couleur.values()[i]);
                pack.add(carte);
            }
        }
    }

    void description() {
        for (int i = 0; i < pack.size(); i++) {
            this.pack.get(i).description();
        }
    }

    public  void piocher(Carte e) { this.pack.add(e); }

    void premiereCarte() {
        // while ((int)(p.pack.size()*Math.random()) != 51) {
        //     System.out.println("pas encore...");
        // } System.out.println("Fais attention cela peut e^tre `OutOfBounds`");
    }



}
