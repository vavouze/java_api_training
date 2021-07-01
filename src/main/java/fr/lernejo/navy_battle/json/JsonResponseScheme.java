package fr.lernejo.navy_battle.json;

import fr.lernejo.navy_battle.ShipState;

public class JsonResponseScheme {
    private final String consequence;
    private final boolean shipLeft;

    public JsonResponseScheme(String consequence, boolean shipLeft) {
        this.consequence = consequence;
        this.shipLeft = shipLeft;
    }

    //getter
    public String getConsequence() {
        return consequence;
    }
    public boolean getShipLeft() {
        return shipLeft;
    }
}
