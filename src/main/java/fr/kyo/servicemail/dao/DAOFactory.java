package fr.kyo.servicemail.dao;

import java.sql.Connection;

public class DAOFactory {

    private static final Connection connexion = ServiceMailConnect.getInstance();

    private DAOFactory() {}
    public static ApiKeyDAO getApiKeyDAO() {
        return new ApiKeyDAO(connexion);
    }

}
