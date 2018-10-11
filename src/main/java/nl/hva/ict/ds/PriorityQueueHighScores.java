package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Use a priority queue
 * 
 * @author Joey Blankendaal (500778751), Keegan Meijer (500781475)
 */
public class PriorityQueueHighScores implements HighScoreList, Comparator<Player> {
    private PriorityQueue<Player> players = new PriorityQueue<>(10, this);

    @Override
    public void add(Player player) {
        players.add(player);
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> sortedPlayers = new ArrayList<>(10);

        if (numberOfHighScores == 0) {
            return sortedPlayers;
        }

        while (players.size() != 0) {
            sortedPlayers.add(players.remove());
        }

        for (Player player: sortedPlayers) {
            players.add(player);
        }

        return sortedPlayers;
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) {
        List<Player> matchedPlayers = new ArrayList<>();

        for (Player player : players) {
            if (player.getFirstName().equals(firstName) && player.getLastName().equals(lastName)) {
                matchedPlayers.add(player);
            }
        }

        return matchedPlayers;
    }

    @Override
    public int compare(Player a, Player b) {
        if (a.getHighScore() > b.getHighScore()) {
            return -1;
        }

        if (a.getHighScore() < b.getHighScore()) {
            return 1;
        }

        return 0;
    }

    public int getHighscoreSize() {
        return players.size();
    }
}