package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains some unit tests. They by no means ensure that all the requirements are implemented
 * correctly.
 */
public class HighScoreListTest {
    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    private HighScoreList highScores;
    private Player nearlyHeadlessNick;
    private Player dumbledore;

    @Before
    public void setup() {
        // Here you should select your implementation to be tested.
//        highScores = new DummyHighScores();
//        highScores = new InsertionSortHighScores();
        highScores = new BucketSortHighScores();
//        highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);
    }

    @Test
    public void noPlayerNoHighScore() {
        assertTrue("There are high-score while there should be no high-scores!", highScores.getHighScores(1).isEmpty());
    }

    @Test
    public void whenNoHighScoreIsAskedForNonShouldBeGiven() {
        highScores.add(dumbledore);
        System.out.println(highScores.getHighScores(0).size());

        assertEquals(0, highScores.getHighScores(0).size());
    }

    @Test
    public void noMoreHighScoresCanBeGivenThenPresent() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);


        assertEquals(2, highScores.getHighScores(10).size());
    }

    @Test
    public void keepAllHighScores() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(2).size());
    }

    @Test
    public void singlePlayerHasHighScore() {
        highScores.add(dumbledore);

        assertEquals(dumbledore, highScores.getHighScores(1).get(0));
    }


//  TODO: blablabla based on highscores?
    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
        highScores.add(harry);

//      Print ArrayList
        for (Player player: highScores.getHighScores(2)) {
            System.out.println(player.getFirstName()+" " + player.toString());
        }

        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

    // Extra unit tests go here

    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }

    /**
     *
     * @author Keegan Meijer (500781475)
     *
     * Checks if the given index is valid,
     * when retrieving a specific object from the newly created highscores list.
     *
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void indexOutOfBoundsExceptionGetHighScores() {
        highScores.getHighScores(1).get(1);
    }


    /**
     *
     * @author Keegan Meijer (500781475)
     *
     * This function will test a long list and check if the list is sorted.
     * For running the tests we'll check if the third object is the same as expected but
     * we have a for each loop commented, so that the tester is able to see the raw data output.
     * All the other players attributes are the same because we're only testing the highscores.
     *
     * Excecpted output: [dumbledore(120) - second(109) - first(104) - nearlyHeadlessNick(102]
     */

    @Test
    public void highScoresSortingTest(){
        Player first = new Player("Nicholaz1", "de Mimsy-Porpington", 104);
        Player second = new Player("Nicholaz2", "de Mimsy-Porpington", 109);
        nearlyHeadlessNick = new Player("Nicholaz3", "de Mimsy-Porpington", 102);
        dumbledore = new Player("Nicholaz4", "de Mimsy-Porpington", 200000);

        highScores.add(first);
        highScores.add(second);
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

//        FOR PRINT THE LIST
        for (Player player: highScores.getHighScores(4)) {
            System.out.println("[+] - Player in highscores array: " + player.getFirstName()+" " + player.toString());
        }

        ArrayList<Player> sortedArrayList = new ArrayList<>();
        sortedArrayList.add(dumbledore);
        sortedArrayList.add(second);
        sortedArrayList.add(first);
        sortedArrayList.add(nearlyHeadlessNick);


        assertEquals(sortedArrayList, highScores.getHighScores(4));
    }


    @Test
    public void checkHighScoreSize(){
        for (int i = 0; i < 50; i++) {
            highScores.add(new Player("keegan", "meijer", i));
        }
        System.out.println(highScores);

    }
}

    /** TODO: IMPLEMENT FOR FALLBACK SORTING METHOD
     *
     * @author Keegan Meijer (500781475)
     *
     * Create two objects with the same score but different names (ending: s and z).
     * Checks if the objects are ordered in the right position, the order should be:
     *  nicholar - dumbledore - nearlyHeadlessNick
     *
     * The tests checks if nicholar is the first element and if nearlyheadlessnick is the last.
     */
//    @Test
//    public void firstNameSortingTest(){
//        nearlyHeadlessNick = new Player("Nicholaz", "de Mimsy-Porpington", 100);
//        dumbledore = new Player("Nicholas", "de Mimsy-Porpington", 100);
//        Player nicholar = new Player("Nicholar", "de Mimsy-Porpington", 100);
//
//        highScores.add(nearlyHeadlessNick);
//        highScores.add(dumbledore);
//        highScores.add(nicholar);
//
//        assertTrue(nicholar.equals(highScores.getHighScores(3).get(0)));
//
////      A-mazing?((InsertionSortHighScores) highScores).getInsertionSortHighscoresSize();
////      Not sure if it's handy for the other sorting implementations, for this reason I choose this approach
//        assertEquals(nearlyHeadlessNick, highScores.getHighScores(3).get(((InsertionSortHighScores) highScores).getInsertionSortHighscoresSize()-1));
//    }