package fr.lernejo.navy_battle.json;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonResponseSchemeTest {

    final JsonResponseScheme json = new JsonResponseScheme("hit",false);


    @Test
    void getConsequence() {
        Assertions.assertThat(json.getConsequence()).as("getter consequence").isEqualTo("hit");
    }

    @Test
    void getShipLeft() {
        Assertions.assertThat(json.getShipLeft()).as("getter consequence").isEqualTo(false);
    }
}
