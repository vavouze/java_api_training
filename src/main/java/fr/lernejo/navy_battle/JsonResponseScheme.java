package fr.lernejo.navy_battle;

public class JsonResponseScheme {
    private final ShipState consequence;
    private final boolean shipLeft;

    public JsonResponseScheme(ShipState consequence, boolean shipLeft) {
        this.consequence = consequence;
        this.shipLeft = shipLeft;
    }

    //getter
    public ShipState getConsequence() {
        return consequence;
    }
    public boolean getShipLeft() {
        return shipLeft;
    }
}
