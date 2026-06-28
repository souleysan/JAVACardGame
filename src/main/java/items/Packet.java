package items;
import java.util.ArrayList;

public class Packet {

    public ArrayList<Carte> pack;

    public Packet() {
        this.pack = new ArrayList<>();
    }

    int getSize() {
        return this.pack.size();
    }

    public void init() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Carte carte = new Carte(Carte.Valeur.values()[j], Carte.Couleur.values()[i]);
                pack.add(carte);
            }
        }
    }

    // void initPioche(Packet general) {
    //     for (int i = 0; i < general.pack.size(); i++) {
    //         this.piocher(general.defausser(general.pack.get(i)));
    //     }
    // }

    void initPremiereCarte(Packet general) {
        int rand = (int)(general.getSize() * Math.random());
        this.piocher(general.pack.get(rand));
    }

    boolean estDansPack(Carte e) {
        return this.pack.contains(e);
    }

    public void description() {
        for (int i = 0; i < this.getSize(); i++) {
            this.pack.get(i).description();
        }
    }

    public void piocher(Carte e) {
        this.pack.add(e);
    }

    /*
    public Carte defausser(Carte e) {
        this.estDansPack(e) ? 
            Carte ret = e;
            this.pack.remove(e);
            return ret;            
        } else {}
    }

    public void piocherDeux(Packet general) {
        for (int i = 0; i < 2; i++) {
            int rand = (int)(general.getSize() * Math.random());
            this.piocher(general.pack.get(rand));
            general.defausser(general.pack.get(rand));
        }
    }

    public void piocherCinq(Packet general) {
        for (int i = 0; i < 5; i++) {
            int rand = (int)(general.pack.size() * Math.random());
            this.piocher(general.pack.get(rand));
            general.defausser(general.pack.get(rand));
        }
    }






    public static void main(String[] args) {
        Packet p = new Packet();
        Packet d = new Packet();
        Packet c = new Packet();
        Packet pC = new Packet();
        p.init();
        d.piocherDeux(p);
        c.piocherCinq(p);
        pC.initPremiereCarte(p);

        d.description();
        System.out.println("______________");
        c.description();
        System.out.println("______________");
        p.description();
        System.out.println("______________");
        pC.description();


    }
        */

}
