package fr.kyo.servicemail.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiKey {
    private int id;
    private String key;
    private int uses;

    public ApiKey(int id, String key, int uses) {
        this.id = id;
        this.key = key;
        this.uses = uses;
    }

    public ApiKey(String key) {
        this.key = key;
    }

}
