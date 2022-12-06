package fr.kyo.servicemail.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;

public class ServiceMailConnect {

    private static Connection connexion;

    private ServiceMailConnect(){}

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setServerName("127.0.0.1");
                ds.setPortNumber(1433);
                ds.setDatabaseName("ServiceMail_bdd");
                ds.setIntegratedSecurity(false);
                ds.setEncrypt(false);
                ds.setUser("sa");
                ds.setPassword("azerty@123456");

                connexion = ds.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }

}
