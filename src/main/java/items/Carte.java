package items;

public class Carte {

    /**
     * Enumerateur de Valeur de la Classe Carte
     */
    public enum Valeur {
        AS, DEUX, TROIS, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, VALET, DAME, ROI
    };

    /**
     * Enumerateur de Couleur de la Classe Carte
     */
    public enum Couleur {
        CARREAUX, COEUR, PIQUE, TREFLE
    };

    /**
     * Attribut Valeur de la Classe Carte - Type : Valeur
     */
    Valeur valeur;
    /**
     * Attribut Couleur de la Classe Carte - Type : Valeur
     */
    Couleur couleur;

    /**
     * [Unique] Constructeur de la Classe Carte - Initialise les attributs
     * valeur et couleur de la carte
     *
     * @param Valeur v
     * @param Couleur c
     */
    public Carte(Valeur v, Couleur c) {
        this.valeur = v;
        this.couleur = c;
    }


    /**
     * Méthode getValeur - Getter de l'attibut valeur
     *
     * @return laValeurDeLaCarte
     */
    Valeur getValeur() {
        return this.valeur;
    }


    /**
     * Méthode getCouleur - Getter de l'attibut couleur
     *
     * @return laCouleurDeLaCarte
     */
    Couleur getCouleur() {
        return this.couleur;
    }


    /**
     * Méthode description - Fournit une description de la carte 
     * [ex] --> AS de PIQUE
     */
    void description() {
        System.out.println(this.valeur + " de " + this.couleur);
    }


    /**
     * Méthode estHuit - Revoie TRUE, si la carte possède la valeur 8
     *
     * @return siLaCarteEstUnHuit
     */
    public boolean estHuit() {
        return this.valeur == Valeur.HUIT;
    }


    /**
     * Méthode estSept - Revoie TRUE, si la carte possède la valeur 7
     *
     * @return siLaCarteEstUnSept
     */
    public boolean estSept() {
        return this.valeur == Valeur.SEPT;
    }


    /**
     * Méthode estDeux - Revoie TRUE, si la carte possède la valeur 2
     *
     * @return siLaCarteEstUnDeux
     */
    public boolean estDeux() {
        return this.valeur == Valeur.DEUX;
    }


    /**
     * Méthode estAs - Revoie TRUE, si la carte possède la valeur AS
     *
     * @return siLaCarteEstUnAS
     */
    public boolean estAs() {
        return this.valeur == Valeur.AS;
    }


    /**
     * Méthode compareValeur - Renvoie TRUE, si les valeurs de la carte mère et
     * cible sont les mêmes
     *
     * @param Carte e
     * @return siValeursSontIdentiques
     */
    public boolean compareValeur(Carte e) {
        return this.valeur == e.valeur;
    }


    /**
     * Méthode compareCouleur - Renvoie TRUE, si les couleurs de la carte mère
     * et cible sont les mêmes
     *
     * @param Carte e
     * @return siCouleursSontIdentiques
     */
    public boolean compareCouleur(Carte e) {
        return this.couleur == e.couleur;
    }


    /**
     * Méthode compareTotal - Renvoie TRUE, si les valeur et couleur de la carte
     * mère et cible sont les mêmes
     *
     * @param Carte e
     * @return siValeurEtCouleurSontIdentiques
     */
    public boolean compareTotal(Carte e) {
        return this.compareValeur(e) && this.compareCouleur(e);
    }

}
