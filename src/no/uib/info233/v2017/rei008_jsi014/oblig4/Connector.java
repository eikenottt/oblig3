package no.uib.info233.v2017.rei008_jsi014.oblig4;


import java.sql.*;
import java.util.HashMap;

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

    public GameMaster loadSaved(String gameID)throws SQLException{

        Statement stmt = null;
        String query = "SELECT game_id, player_1, player_2, game_position," +
                        "player_1_energy, player_2_energy" +
                        "FROM saved_games WHERE game_id = 'gameID'";

        GameMaster gameMaster = new GameMaster();
        try {

            statement = getConnection().prepareStatement("SELECT * FROM saved_games WHERE game_id = ?");
            statement.setString(1, gameID);
            ResultSet rs = statement.executeQuery();

                rs.next();
                String id = rs.getString("game_id");
                gameMaster.setGameID(id);

                rs.next();
                String p1 = rs.getString("player_1");
                rs.next();
                String p2 = rs.getString("player_2");
                rs.next();

                rs.next();
                int gamePos = rs.getInt("game_position");
                gameMaster.setGamePosition(gamePos);


                int p1Energy = rs.getInt("player_1_energy");
                rs.next();
                int p2Energy = rs.getInt("player_2_energy");
                Player player1 = new HumanPlayer(p1);
                Player player2 = new HumanPlayer(p2);

                player1.setCurrentEnergy(p1Energy);
                player2.setCurrentEnergy(p2Energy);


                gameMaster.setPlayers(player1, player2);


                System.out.println("Loaded:  \n ID: " + id + " \n Player 1: " + p1 + " With " +p1Energy+ " Energy." +"\n Player 2: " +p2+ " With " + p2Energy + " Energy.");

                return gameMaster;



            } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return gameMaster;
    }

    public HashMap<String, HashMap<String,Float>> getMultiplayerMap(HashMap<String, Float> playersMap){
        HashMap< String, HashMap<String, Float>> multiplayerMap = new HashMap<>();
        try {
            statement = getConnection().prepareStatement("SELECT player_1, player_1_random, player, score FROM open_games, oblig4.ranking WHERE player_1 = player");
            ResultSet result = statement.executeQuery();

            while(result.next()){

                playersMap.put(result.getString(1), result.getFloat(4));
                multiplayerMap.put(result.getString(2), playersMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return multiplayerMap;
    }

}