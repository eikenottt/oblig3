package no.uib.info233.v2017.rei008_jsi014.oblig3;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    public Connector() {

    }

    public Connection getConnection() throws Exception {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            String url = "jdbc:mysql://localhost:8889/songs";
            String usr = "root";
            String pwr = "root";
            Connection conn = DriverManager.getConnection( url, usr, pwr ) ;


            return conn;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateRanking(Player player, float score) {
        //SQL shit
    }

}
