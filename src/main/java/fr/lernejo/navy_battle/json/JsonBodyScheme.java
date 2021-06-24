package fr.lernejo.navy_battle.json;

public class JsonBodyScheme {
    private final String id;
    private final String url;
    private final String message;

    public JsonBodyScheme(String id, String url, String message) {
        this.id = id;
        this.url = url;
        this.message = message;
    }

    //getter
    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
