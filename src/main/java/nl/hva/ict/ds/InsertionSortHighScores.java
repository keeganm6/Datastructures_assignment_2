package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Keegan Meijer (500718475)
 *
 * Selection sort:
 * This approach will sort the list when a new player has been added to the high-score list.
 *
 *
 * Your first approach is to sort the list with players after a new player has been added to the high-score list.
 * There are two different variants you want to investigate.
 * This variant looks a lot like the implementation your sister used,
 * but you suspect it to be more efficient because you just sort the list and don’t have to duplicate it
 * and remove high- scores once they have been added to the list that is returned.
 *
 */
public class InsertionSortHighScores implements HighScoreList{
    private List<Player> playersList = new ArrayList<Player>();

    /**
     * Add a new player to the list of high-scores.
     * @param player
     */
    @Override
    public void add(Player player) {
        playersList.add(player);
        sortHighscores(player);
    }

    /**
     * Return a list with players descending sorted on their high-score. If there are less then numberOfHighScores players
     * a list containing all players is returned. If there are more high-scores then numberOfHighScore only the first
     * numberOfHighScores are returned.
     *
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return at list of, maximum numberOfHighScores, players with the highest high-scores in descending order.
     * @throws IndexOutOfBoundsException when retrieving a non existing object from the sub list.
     */
//    TODO: INDEX OUT OF BOUNDS EXCEPTION PLZ but how?!
    @Override
    public List<Player> getHighScores(int numberOfHighScores) throws IndexOutOfBoundsException{
        if(playersList.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        return playersList.subList(0, Math.min(numberOfHighScores, playersList.size()));
    }


    /**
     * Finds players based on their first and last name. At least one of the parameters must have a value other
     * then null or an empty String, {@see String#isEmpty()} for a definition of an empty String.
     * Players match the search values if their names start with the parameters.
     * @param firstName the firstname of the players must start with or be equal to this value, can be null or empty if
     *                 lastName is not null or empty.
     * @param lastName the lastname of the playersmust start with or be equal to this value, can be null or empty if
     *                 firstName is not null or empty
     * @return a list of players that either match by firstname, lastname or both.
     * @throws IllegalArgumentException if both firstName and lastName or null or empty.
     */
    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        return null;
    }


    /**
     *
     * @return The sorted list of players based on: highscores, fname, lastname and size
     */
    public List<Player> sortHighscores(Player addPlayer) {
        Collections.sort(playersList);
        return playersList;
    }

    /**
     * Handy AF
     */
    public int getInsertionSortHighscoresSize(){
        return playersList.size();
    }
}

//        for (Player player: playersList) {
////          Sorts based on descending order (big(gie)>small(s))
//            addPlayer.compareTo(player);
//        }
//
//        return playersList;

