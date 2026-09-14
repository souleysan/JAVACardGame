package items;

import java.util.ArrayList;

public class Packet {

    /**
     * Attribut pack de la Classe Packet
     */
    public ArrayList<Carte> pack;

    /**
     * [Unique] Constructeur de la Classe Packet
     */
    public Packet() {
        this.pack = new ArrayList<>();
    }


    /**
     * Méthode init - Crée un packet de carte complet (52 cartes)
     */
    public void init() {
        for (int i = 0; i < (3 + 1); i++) {
            for (int j = 0; j < (12 + 1); j++) {
                Carte carte = new Carte(Carte.Valeur.values()[j], Carte.Couleur.values()[i]);
                pack.add(carte);
            }
        }
    }


    /**
     * Méthode initPremiereCarte - Initialise un packet avec une seule carte
     * [aléatoire], à partir d'un packet
     *
     * @param general
     */
    public void initPremiereCarte(Packet general) {
        int rand = (int) (general.getSize() * Math.random());
        this.piocher(general.defausser(general.getCardByIndex(rand)));
    }


    /**
     * Méthode initPioche - Initialise la pioche avec le reste des cartes d'un
     * packet
     *
     * @param general
     */
    public void initPioche(Packet general) {
        while (!general.isEmpty()) {
            this.piocher(general.defausser(general.getCardByIndex(0)));
        }
    }


    /**
     * Méthode description - Affiche une description du packet, carte par carte
     */
    public void description() {
        for (int i = 0; i < this.getSize(); i++) {
            this.pack.get(i).description();
        }
    }


    /**
     * Méthode descriptionNumerotee - Affiche une description du packet, numerotant chaque carte de la premiere à la n-ieme
     */
    public void descriptionNumerotee() {
        for (int i = 0; i < this.getSize(); i++) {
            System.out.print((i + 1) + " : ");
            this.pack.get(i).description();
        }
    }


    /**
     * Méthode getSize - Getter de la taille du packet
     *
     * @return laTailleDuPacket
     */
    public int getSize() {
        return this.pack.size();
    }


    /**
     * Methode getPack - Getter du packet de carte
     * @return
     */
    ArrayList<Carte> getPack() {
        return this.pack;
    }


    /**
     * Méthode getCardByIndex - Getter d'une carte dans le packet, grâce à
     * son index
     *
     * @param index
     * @return carteSelectionnee
     */
    public Carte getCardByIndex(int index) {
        return this.pack.get(index);
    }


    /**
     * Méthode isEmpty - Renvoie TRUE, si le packet est vide
     *
     * @return siLePacketEstVide
     */
    public boolean isEmpty() {
        return this.pack.isEmpty();
    }


    /**
     * Méthode estDansPack - Renvoie TRUE, si la carte est dans le packet
     *
     * @param e
     * @return siLaCarteEstDansLePacket
     */
    public boolean estDansPack(Carte e) {
        return this.pack.contains(e);
    }


    /**
     * Méthode piocher - Pioche une carte sélectionnee
     *
     * @param e
     */
    public void piocher(Carte e) {
        this.pack.add(e);
    }


    /**
     * Méthode piocherDeux - Pioche 2 cartes d'affilé, à partir d'un packet
     *
     * @param general
     */
    public void piocherDeux(Packet general) {
        for (int i = 0; i < 2; i++) {
            int rand = (int) (general.getSize() * Math.random());
            this.piocher(general.getCardByIndex(rand));
            general.defausser(general.getCardByIndex(rand));
        }
    }


    /**
     * Méthode piocherCinq - Pioche 5 cartes d'affilé, à partir d'un packet
     *
     * @param general
     */
    public void piocherCinq(Packet general) {
        for (int i = 0; i < 5; i++) {
            int rand = (int) (general.getSize() * Math.random());
            this.piocher(general.getCardByIndex(rand));
            general.defausser(general.getCardByIndex(rand));
        }
    }


    /**
     * Méthode initPacket - Initialise un packet de num cartes, à partir d'un
     * packet
     *
     * @param general
     * @param num
     */
    public void initPacket(Packet general, int num) {
        for (int i = 0; i < num; i++) {
            int rand = (int) (general.getSize() * Math.random());
            this.piocher(general.defausser(general.getCardByIndex(rand)));
        }
    }


    /**
     * Méthode defausser - Defausse/Enleve une carte du packet
     *
     * @param e
     * @return laCarteDefaussee
     */
    public Carte defausser(Carte e) {
        //Carte def = new Carte(e.getValeur(), e.getCouleur());
        this.pack.remove(e);
        return e;
    }
}