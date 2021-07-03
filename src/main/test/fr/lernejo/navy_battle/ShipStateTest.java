package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class ShipStateTest {
    @Test
    void valueOf() {
        Assertions.assertThat(ShipState.MISS.state).as("value of").isEqualTo("miss");
        Assertions.assertThat(ShipState.HIT.state).as("value of").isEqualTo("hit");
        Assertions.assertThat(ShipState.SUNK.state).as("value of").isEqualTo("sunk");
    }
}
