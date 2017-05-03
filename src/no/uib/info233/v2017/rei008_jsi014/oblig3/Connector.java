package no.uib.info233.v2017.rei008_jsi014.oblig3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Class to connect to a database
 */
public class Connector {

    private PreparedStatement statement;

    /**
     * Makes a connection to the database
     * @return Connection to the database
     * @throws Exception if connection is bad
     */
    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://wildboy.uib.no/Syuty";
            String usr = "Syuty";
            String pwr = "(+DDq2sSyk(3P)}8";
            Connection conn = DriverManager.getConnection(url, usr, pwr);


            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Handles the SQL-queries and manipulations in the database
     * @param player the player to be updated
     * @param score player score
     * @throws Exception if connection is bad
     */
    public void updateRanking(Player player, float score) throws Exception {
        float prevScore;
        statement = getConnection().prepareStatement("SELECT score FROM ranking WHERE player = '"+player.getName()+"'");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            prevScore = result.getFloat(1);
            score += prevScore;
        }
        statement = getConnection().prepareStatement("REPLACE INTO ranking(player, score) VALUES ('"+player.getName()+"', "+score+")");
        statement.executeUpdate();

    }
}