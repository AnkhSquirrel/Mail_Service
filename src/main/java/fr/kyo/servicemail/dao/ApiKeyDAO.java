package fr.kyo.servicemail.dao;

import fr.kyo.servicemail.entity.ApiKey;
import lombok.Getter;

import java.sql.*;

@Getter
public class ApiKeyDAO {
    protected Connection connection;

    protected ApiKeyDAO(Connection connection) {
        this.connection = connection;
    }

    public ApiKey getByApiKey(String apiKey) {
        String requete = "SELECT * from ApiKeys where api_key = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            preparedStatement.setString(1, apiKey);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) return new ApiKey(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(String apiKey) {
        String requete = "INSERT INTO ApiKeys (api_key, uses) VALUES (?, ?)";
        try (PreparedStatement  preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString( 1 , apiKey);
            preparedStatement.setInt(2, 0);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            connection.commit();
            if(rs.next()) return rs.getInt(1);
        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    public boolean addUse(String apiKey){
        String requete = "UPDATE Apikeys SET uses = ? WHERE api_key = ?";
        try (PreparedStatement  preparedStatement = connection.prepareStatement(requete)){
            connection.setAutoCommit(false);
            ApiKey apikeyBefore = getByApiKey(apiKey);
            preparedStatement.setInt(1, apikeyBefore.getUses() + 1);
            preparedStatement.setString(2, apiKey);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean exists(String apiKey) {
        String requete = "SELECT api_key_id from ApiKeys where api_key = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            preparedStatement.setString(1, apiKey);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
