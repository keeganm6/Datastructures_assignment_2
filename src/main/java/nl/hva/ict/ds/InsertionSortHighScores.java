package nl.hva.ict.ds;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Selection sort:
 * This approach will sort the list when a new player has been added to the high-score list.
 *
 * @author Joey Blankendaal (500778751), Keegan Meijer (500781475)
 *
 * Your first approach is to sort the list with players after a new player has been added to the high-score list.
 * There are two different variants you want to investigate.
 * This variant looks a lot like the implementation your sister used,
 * but you suspect it to be more efficient because you just sort the list and don’t have to duplicate it
 * and remove high- scores once they have been added to the list that is returned.
 *
 */
public class InsertionSortHighScores implements HighScoreList, Comparator<Player> {
    private List<Player> playersList = new ArrayList<>();

    /**
     * Add a new player to the list of high-scores.
     * @param player
     */
    @Override
    public void add(Player player) {
        playersList.add(player);
        selectionSortHighscores((ArrayList<Player>)playersList);
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
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
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
        List<Player> foundPlayers = new ArrayList<>();

        for (Player player : playersList) {
            if (playersList.contains(firstName)|| playersList.contains(lastName)) {
                foundPlayers.add(player);
            }
            
            if (playersList.contains(firstName.concat(lastName)) || playersList.contains(lastName.concat(firstName))) {
                foundPlayers.add(player);
            }
        }
        
        return foundPlayers;
    }

    /**
     * @return The sorted list of players based on: highscores (later?: fname, lastname and size)
     */
    public void selectionSortHighscores(ArrayList<Player> players){
        int arrayLength = players.size();
        
        for (int i = 0; i + 1 < arrayLength ; i++) {
            int minimum = i;
            
            for (int j = i+1; j < arrayLength ; j++) {
                if(compare(players.get(j), players.get(minimum)) <0 ) minimum = j;
            }
            
            Player switchPlayer = players.get(i);
            players.set(i, players.get(minimum));
            players.set(minimum, switchPlayer);
        }
    }

    /**
     * @param o1 The first player object for comparison
     * @param o2 The second player object for comparison
     * @return A postive, negative or 0 integer based on the comparison
     *
     * This function works in descending order:
     *
     * The function will compare two highscores (o1 and o2). It will sort the objects
     *  - Returns negative when o1 > o2, in other words this will set the biggest highscore in front of the smaller
     *  - Returns positivie when o1 < o2, in other words this will set the lower highscore after the smaller
     *  - Returns 0 when o1 == o2, in other words will keep same order (Thoughts for later: implement fallback sorting option)
     */
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getHighScore() > o2.getHighScore()) {
            return -1;
        }

        if (o1.getHighScore() < o2.getHighScore()) {
            return 1;
        }

        return 0;
    }
}