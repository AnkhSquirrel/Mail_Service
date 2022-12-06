package fr.kyo.servicemail.security;

import fr.kyo.servicemail.dao.DAOFactory;

import java.util.Base64;

public class MyApiKey {


    private MyApiKey() {
    }

    public static String generateApiKey() {
        double randomDouble = Math.random() * 64;
        byte[] encodedBytes = Base64.getEncoder().encode(String.valueOf(randomDouble).getBytes());
        return new String(encodedBytes);
    }

    public static boolean isValide(String apiKey) {
        return DAOFactory.getApiKeyDAO().getByApiKey(apiKey) != null;
    }
}
