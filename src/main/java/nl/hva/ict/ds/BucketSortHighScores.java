package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.List;

public class BucketSortHighScores  implements HighScoreList{
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
//      Utilizing the insertion sort class find method to prevent duplicate code
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

//        List<Player> foundPlayers = new ArrayList<>();
//
//        for(Player player: playerList){
////          Checks if either firstname or lastname exists, if so add the player to the temp list
//            if (playerList.contains(firstName)|| playerList.contains(lastName)){foundPlayers.add(player);}
////            if(playersList.contains(||playersList.contains(lastName)))
//            if (playerList.contains(firstName.concat(lastName))||playerList.contains(lastName.concat(firstName))){foundPlayers.add(player);}
//        }
//        return foundPlayers;


