package nl.hva.ict.ds;

import java.util.Comparator;
import java.util.List;

/**
 * Use a priority queue
 * 
 * @author Joey Blankendaal (500778751)
 */
public class PriorityQueueHighScores implements HighScoreList, Comparator<Player> {
    @Override
    public void add(Player player) {
        
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        
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