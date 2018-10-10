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
//        sort();
    }

    // Misschien wat meer doen met numberOfHighScores dan alleen if==0
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> sortedPlayers = new ArrayList<>(10);

//      TODO: CHECK DIT FF --> playersList.subList(0, Math.min(numberOfHighScores, playersList.size()))
//      Is vgm ook efficienter dan nieuwe array list maken maar weet niet precies hoe n wat tbh
        if (numberOfHighScores == 0) {
            return sortedPlayers;
        }

        while (players.size() != 0) {
            //for (int i = numberOfHighScores; players.size() > 0; i--) {
            sortedPlayers.add(players.remove());
        }

        for (Player player: sortedPlayers) {
            players.add(player);
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

//    public void sort() {
//        for (int i = 0; i < players.size(); i++) {
//            players.add(players.remove());
//        }
//    }

//        System.out.println("[+] - Removed player: " + players.remove());
//        while (players.size()!= 0){
//            this.players.add(playerz.remove());
//        }
//        Player player;
//        while ((player = this.players.poll()) != null){
//            System.out.println("[+] - Player in while loop: " + player);
//            players.add(player);
//        }


//        for (int i = 0; i < players.size(); i++) {
//            sortedPlayers.add(player);
//        }
//        while ((player = players.poll()) != null){
//            System.out.println("while je kankermoeder");
//            System.out.println(player);
//            sortedPlayers.add(player);
//        }


//
//        while (players.size() != 0) {
//        //for (int i = numberOfHighScores; players.size() > 0; i--) {
//            sortedPlayers.add(players.poll());
//        }
