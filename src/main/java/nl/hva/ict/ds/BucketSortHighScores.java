package nl.hva.ict.ds;

import java.util.*;

/**
 * Bucket sorting the high scores.
 * 
 * @author Joey Blankendaal (500778751), Keegan Meijer (500781475)
 */
public class BucketSortHighScores implements HighScoreList, Comparator<Player> {
    private List<Player> playerList = new ArrayList<>();
    private List<Player> bucketBuilder;
    int numberOfBuckets = 10;
    Bucket[] bucketList = new Bucket[numberOfBuckets];

    /**
     * Creates the bucketList, which contains buckets, the list will be the same length as numberOfBuckets
     */
    public BucketSortHighScores(){
        
        for (int i = 0; i < bucketList.length; i++) {
            bucketList[i] = new Bucket();
        }
    }

    @Override
    public void add(Player player) {
        long bucketIndex = ((player.getHighScore() * 10) / 1000000);
//        System.out.println("[+] - BucketIndex of " + player.getFirstName() +" "+ bucketIndex);
        bucketList[(int) bucketIndex].bucket.add(player);

//        Sorts each bucket
        for (int i = 0; i < bucketList.length; i++) {
            Collections.sort((ArrayList)bucketList[i].bucket, this);
        }

//        Add the bucket to the correct list
        bucketBuilder = new ArrayList<>();
        for (int i = numberOfBuckets - 1; i >= 0; i--) {
            for (int j = 0; j < bucketList[i].bucket.size(); j++) {
                bucketBuilder.add(bucketList[i].bucket.get(j));
            }
        }
        playerList = bucketBuilder;
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

    /**
     * This class will create a bucket, these will be used to fill the playerList.
     */
    class Bucket {
        ArrayList<Player> bucket = new ArrayList<>();
    }
}