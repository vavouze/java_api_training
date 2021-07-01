package fr.lernejo.navy_battle;

public enum ShipState {
    MISS("miss"),
    HIT("hit"),
    SUNK("sunk"),
    ;
    public final String state;

    ShipState(String state) {
        this.state = state;
    }
}
