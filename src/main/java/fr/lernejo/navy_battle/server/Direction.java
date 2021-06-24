package fr.lernejo.navy_battle.server;

public enum Direction {
    BAS(+1),
    HAUT(-1),
    GAUCHE(-1),
    DROITE(+1),
    ;
    public final Integer coef;


    Direction(int coef) {
        this.coef = coef;
    }
}
