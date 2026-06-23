import java.util.ArrayList;
public class Packet {

    ArrayList<Carte> pack;

    public Packet() {
        this.pack = new ArrayList<>();
    }

    void init() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Carte carte = new Carte(Carte.Valeur.values()[j], Carte.Couleur.values()[i]);
                pack.add(carte);
            }
        }
    }

    void descritpion() {
        for (int i = 0; i < pack.size(); i++) {
            pack.get(i).description();
        }
    }

    void descritpionNumerotee() {
        for (int i = 0; i < pack.size(); i++) {
            System.out.print(1 + i + " - "); pack.get(i).description();
            //System.out.println();
        }
    }

    void premiereCarte() {
        // while ((int)(p.pack.size()*Math.random()) != 51) {
        //     System.out.println("pas encore...");
        // } System.out.println("Fais attention cela peut e^tre `OutOfBounds`");
    }



}
