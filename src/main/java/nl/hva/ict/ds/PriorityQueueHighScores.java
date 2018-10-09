package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Use a priority queue
 * 
 * @author Joey Blankendaal (500778751)
 */
public class PriorityQueueHighScores implements HighScoreList, Comparator<Player> {
    private List<Player> players = new ArrayList<>();
    
    @Override
    public void add(Player player) {
        players.add(player);
    }
    
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        PriorityQueue<Player> queue = new PriorityQueue<>(numberOfHighScores, this);
        List<Player> sortedPlayers = new ArrayList<>();
        
        while (queue.size() != 0) {
            sortedPlayers.add(queue.remove());
        }
        
        return sortedPlayers;
    }
    
    // Rewrite dit later maar
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
        if (a.getHighScore() < b.getHighScore()) {
            return -1;
        }
        
        if (a.getHighScore() > b.getHighScore()) {
            return 1;
        }
        
        return 0;
    }
}