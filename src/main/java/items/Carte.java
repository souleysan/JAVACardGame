package items;

public class Carte {

    enum Valeur {AS, DEUX, TROIS, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, VALET, DAME, ROI};
    enum Couleur {CARREAUX, COEUR, PIQUE, TREFLE};

    Valeur valeur;
    Couleur couleur;

    public Carte(Valeur v, Couleur c) {
        this.valeur = v; this.couleur = c;
    }

    Valeur getValeur() { return this.valeur; }
    Couleur getCouleur() { return this.couleur; }

    void description() { System.out.println("Je suis un " + this.valeur + " de " + this.couleur); }

    boolean estHuit() { return this.valeur == Valeur.HUIT; }
    boolean estSept() { return this.valeur == Valeur.SEPT; }
    boolean estDeux() { return this.valeur == Valeur.DEUX; }
    boolean estAs() { return this.valeur == Valeur.AS; }

    boolean compareTotal(Carte e) { return this.compareValeur(e) && this.compareCouleur(e); }
    boolean compareValeur(Carte e) { return this.valeur == e.valeur; }
    boolean compareCouleur(Carte e) { return this.couleur == e.couleur; }

}