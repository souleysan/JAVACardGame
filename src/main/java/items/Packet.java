package items;
import java.util.ArrayList;

public class Packet {

    public ArrayList<Carte> pack;

    public Packet() { this.pack = new ArrayList<>(); }


    public void init() {
        for (int i = 0; i < (3 + 1); i++) {
            for (int j = 0; j < (12 + 1); j++) {
                Carte carte = new Carte(Carte.Valeur.values()[j], Carte.Couleur.values()[i]);
                pack.add(carte);
            }
        }
    }

    public void initPremiereCarte(Packet general) {
        int rand = (int) (general.getSize() * Math.random());
        this.piocher(/* general.getCardByIndex(rand) */ general.defausser(general.getCardByIndex(rand)));
    }

    public void initPioche(Packet general) {
        // for (int i = 0; i < general.getSize(); i++) {
        //     this.piocher(general.defausser(general.getCardByIndex(i)));
        //     i--;
        // }

        while (!general.isEmpty()) {
            this.piocher(general.defausser(general.getCardByIndex(0)));
        }
    }

    public void description() {
        for (int i = 0; i < this.getSize(); i++) {
            this.pack.get(i).description();
        }
    }

    int getSize() { return this.pack.size(); }
    public Carte getCardByIndex(int index) { return this.pack.get(index); }
    boolean isEmpty() { return this.getSize() == 0; }

    public boolean estDansPack(Carte e) { return this.pack.contains(e); }

    public void piocher(Carte e) { this.pack.add(e); }

    public void piocherDeux(Packet general) {
        for (int i = 0; i < 2; i++) {
            int rand = (int) (general.getSize() * Math.random());
            this.piocher(general.getCardByIndex(rand));
            general.defausser(general.getCardByIndex(rand));
        }
    }

    public void piocherCinq(Packet general) {
        for (int i = 0; i < 5; i++) {
            int rand = (int) (general.getSize() * Math.random());
            this.piocher(general.getCardByIndex(rand));
            general.defausser(general.getCardByIndex(rand));
        }
    }

    public void initPacket(Packet general, int num) {
        for (int i = 0; i < num; i++) {
            int rand = (int) (general.getSize() * Math.random());
            this.piocher(general.defausser(general.getCardByIndex(rand)));
            //  getCardByIndex(rand));
            // general.defausser(general.getCardByIndex(rand));
        }
    }



    Carte defausser(Carte e) {
        Carte def = new Carte(e.getValeur(), e.getCouleur());
        this.pack.remove(e);
        return def;
    }


    public static void main(String[] args) {
        Packet p = new Packet();
        Packet d = new Packet();
        Packet c = new Packet();
        Packet pC = new Packet();
        Packet r = new Packet();
        
        p.init(); // Initialise tout le packet de carte
        p.description();
        System.out.println("===========================================");
        
        /* d.piocherDeux(p); // pioche 2 cartes
        c.piocherCinq(p); // pioche 5 cartes
        pC.initPremiereCarte(p); // Initialise la table composée d'1 carte

        d.description();
        System.out.println("______________");
        c.description();
        System.out.println("______________");
        pC.description();
        System.out.println("______________");
        p.description();
        System.out.println("______________");

        c.defausser(c.defausser(c.getCardByIndex(0))); // On retire la 1ere carte du deck de nom c
        c.description();
        System.out.println("______________");
        System.out.println("______________");
        System.out.println("______________");
        System.out.println("______________");
        r.initPacket(p, 9); r.description();
        d.description();
        System.out.println("______________");
        c.description();
        System.out.println("______________");
        pC.description();
        System.out.println("______________");

        p.description(); */

        d.initPioche(p);
        d.description();
        System.out.println("===========================================");
        p.description();


    }

}
