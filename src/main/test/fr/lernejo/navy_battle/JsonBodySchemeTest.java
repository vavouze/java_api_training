package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.json.JsonBodyScheme;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class JsonBodySchemeTest {

    final JsonBodyScheme json = new JsonBodyScheme("test","url","message");

    @Test
    void getId() {
        Assertions.assertThat(json.getId()).as("getter id").isEqualTo("test");
    }

    @Test
    void getMessage() {
        Assertions.assertThat(json.getMessage()).as("getter message").isEqualTo("message");
    }

    @Test
    void getUrl() {
        Assertions.assertThat(json.getUrl()).as("getter url").isEqualTo("url");
    }
}
