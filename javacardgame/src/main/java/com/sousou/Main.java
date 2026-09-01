package com.sousou;

import com.sousou.manager.GameManager;

public class Main {
    public static void main( String[] args ) {
        // System.out.println("Hello World !");
        GameManager gestion = new GameManager();
        System.out.println("Nos félicitations : " + gestion.game().getNom() + ", vous êtes le grand gagnant");
    }
}