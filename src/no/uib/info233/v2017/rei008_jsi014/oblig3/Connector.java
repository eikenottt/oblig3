package no.uib.info233.v2017.rei008_jsi014.oblig3;

import finals.Player2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connector {

    private ResultSet result;
    private PreparedStatement statement;

    public Connector() {

    }

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

    public void updateRanking(Player2 player, float score) throws Exception {
        float prevScore;
        statement = getConnection().prepareStatement("SELECT score FROM ranking WHERE player = '"+player.getName()+"'");
        result = statement.executeQuery();
        while (result.next()) {
            prevScore = result.getFloat(1);
            score += prevScore;
        }
        statement = getConnection().prepareStatement("REPLACE INTO ranking(player, score) VALUES ('"+player.getName()+"', "+score+")");
        statement.executeUpdate();

    }
}

   /* String url = "jdbc:mysql://wildboy.uib.no/Syuty";
    String usr = "Syuty";
    String pwr = "(+DDq2sSyk(3P)}8";

    String url = "jdbc:mysql://localhost:8889/highscore";
            String usr = "root";
            String pwr = "root";

*/