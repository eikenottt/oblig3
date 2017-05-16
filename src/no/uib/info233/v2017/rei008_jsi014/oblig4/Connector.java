package no.uib.info233.v2017.rei008_jsi014.oblig4;


import java.sql.*;

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
            String url = "jdbc:mysql://wildboy.uib.no/oblig4";
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

    public void updateSavedGame(String gameId, Player player1, Player player2, int gamePosition){
        try {
            statement = getConnection().prepareStatement( "INSERT INTO saved_games (game_id, player_1, player_2, game_position, player_1_energy, player_2_energy) VALUES ('"+gameId+"', '"+player1.getName()+"', '"+player2.getName()+"', '"+gamePosition+"', "+player1.getCurrentEnergy()+", "+player2.getCurrentEnergy()+")");
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadSaved(GameMaster gameMaster){
//        try {
//            statement = getConnection().prepareStatement("SELECT ROW FROM oblig4.saved_games WHERE game_id="+gameMaster.getGameID()+"");
//            ResultSet rs = statement.executeQuery();
//            String id = rs.getString(1);
//            gameMaster.setGameID(id);
//
//            String p1 = rs.getString(2);
//            String p2 = rs.getString(3);
//            Player player1 =  new HumanPlayer(p1);
//            Player player2 = new PassivePlayer(p2);
//            int p1Energy = rs.getInt(5);
//            int p2Energy = rs.getInt(6);
//            player1.setCurrentEnergy(p1Energy);
//            player2.setCurrentEnergy(p2Energy);
//            gameMaster.setPlayers(player1, player2);
//
//            int gamePos = rs.getInt(4);
//            gameMaster.set



//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}