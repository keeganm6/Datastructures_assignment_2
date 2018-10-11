package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Bucket sorting the high scores.
 * 
 * @author Joey Blankendaal (500778751), Keegan Meijer (500781475)
 */
public class BucketSortHighScores implements HighScoreList {
    private List<Player> playerList = new ArrayList<>();
    private List<Player> temp;
    int numberOfBuckets = 10;
    Bucket[] bucketList = new Bucket[numberOfBuckets];

    /**
     * Creates the bucketList, which contains buckets, the list will be the same length as numberOfBuckets
     */
    public BucketSortHighScores(){
        System.out.println("[+] - Bucket sort length: " + bucketList.length);
        
        for (int i = 0; i < bucketList.length; i++) {
            bucketList[i] = new Bucket();
        }
    }

    @Override
    public void add(Player player) {
        playerList.add(player);
        long bucketIndex = ((player.getHighScore() * 10) / 10000001);
        bucketList[(int) bucketIndex].bucket.add(player);

        InsertionSortHighScores is = new InsertionSortHighScores();
        
        for (int i = 0; i < bucketList.length; i++) {
            is.selectionSortHighscores(bucketList[i].bucket);
        }

        temp = new ArrayList<>();
        
        for (int i = bucketList.length - 1; i >= 0; i--) {
            for (int j = 0; j < bucketList[i].bucket.size(); j++) {
                temp.add(bucketList[i].bucket.get(j));
            }
        }
        
        playerList = temp;
    }
    
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        return playerList.subList(0, Math.min(numberOfHighScores, playerList.size()));
    }
    
    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        InsertionSortHighScores is = new InsertionSortHighScores();
        return is.findPlayer(firstName, lastName);
    }

    /**
     * This class will create a bucket, these will be used to fill the playerList.
     */
    class Bucket {
        ArrayList<Player> bucket = new ArrayList<>();
    }
}