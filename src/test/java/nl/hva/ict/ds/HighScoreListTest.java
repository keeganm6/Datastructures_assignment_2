package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Stopwatch;

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
        dumbledore = new Player("Albus", "Dumbledore", getHighScore());
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

    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
        highScores.add(harry);

        for (Player player : highScores.getHighScores(2)) {
            System.out.println(player.getFirstName() + " " + player.toString());
        }

        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

    // Extra unit tests go here
    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }

    /**
<<<<<<< HEAD
     * @author Joey Blankendaal (500778751), Keegan Meijer (500781475)
     * <p>
     * Checks if the given index is valid,
     * when retrieving a specific object from the newly created highscores list.
=======
     * Checks if the given index is valid,
     * when retrieving a specific object from the newly created highscores list.
     *
     * @author Keegan Meijer (500781475), Joey Blankendaal (500778751)
>>>>>>> 7fa5e807fc46045d75745c1bb98bf0ddf7d061e9
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOutOfBoundsExceptionGetHighScores() {
        highScores.getHighScores(1).get(1);
    }

    /**
<<<<<<< HEAD
     * @author Joey Blankendaal (500778751), Keegan Meijer (500781475)
     * <p>
=======
>>>>>>> 7fa5e807fc46045d75745c1bb98bf0ddf7d061e9
     * This function will test a long list and check if the list is sorted.
     * For running the tests we'll check if the third object is the same as expected but
     * we have a for each loop commented, so that the tester is able to see the raw data output.
     * All the other players attributes are the same because we're only testing the highscores.
     * <p>
     * Excecpted output: [dumbledore(120) - second(109) - first(104) - nearlyHeadlessNick(102]
     *
     * @author Keegan Meijer (500781475), Joey Blankendaal (500778751)
     */
    @Test
    public void highScoresSortingTest() {
        Player first = new Player("Nicholaz1", "de Mimsy-Porpington", 500000);
        Player second = new Player("Nicholaz2", "de Mimsy-Porpington", 700000);
        Player third = new Player("Nicholaz3", "de Mimsy-Porpington", 300000);
        Player fourth = new Player("Nicholaz4", "de Mimsy-Porpington", 900000);

        highScores.add(first);
        highScores.add(second);
        highScores.add(third);
        highScores.add(fourth);

//        FOR PRINT THE LIST
//        for (Player player: highScores.getHighScores(4)) {
//            System.out.println("[+] - Player in highscores array: " + player.getFirstName()+" " + player.toString());
//        }


        ArrayList<Player> sortedArrayList = new ArrayList<>();
        sortedArrayList.add(fourth);
        sortedArrayList.add(second);
        sortedArrayList.add(first);
        sortedArrayList.add(third);

        assertEquals(sortedArrayList, highScores.getHighScores(4));
    }

    /**
     * This test has been added in order to test the specific bucket list ordering
     * The test will work for InsertionSortHighScores and PriorityQueueHighScores as well,
     * but there is no difference between the highScoresSortingTest and this test.
     * <p>
     * While testing and checking the BucketSortHighScores, please use the debugger to check
     * the buckets if you want to make sure the list has been build correctly and order.
     */
    @Test
    public void sortingBucketListExtraTest() {
        Player first = new Player("Nicholaz1", "de Mimsy-Porpington", 510000);
        Player second = new Player("Nicholaz2", "de Mimsy-Porpington", 520000);
        Player third = new Player("Nicholaz1", "de Mimsy-Porpington", 530000);

        highScores.add(first);
        highScores.add(second);
        highScores.add(third);

        ArrayList<Player> sortedArrayList = new ArrayList<>();
        sortedArrayList.add(third);
        sortedArrayList.add(second);
        sortedArrayList.add(first);

        assertEquals(sortedArrayList, highScores.getHighScores(3));
    }

    /**
     * Checks if the HighScoreList adds all the numbers correct. Running this function can be used
     * for calculating
     */

    @Test
    public void checkHighScoreSize() {
        for (int i = 0; i < 50; i++) {
            highScores.add(new Player("keegan", "meijer", i));
        }

        assertEquals(highScores.getHighScores(100).size(), 50);
    }

    /**
     * Investigate how the efficient the different implementations are.
     * Start with adding 100 (different!) high-scores to the high-score list and measure the time it took.
     * Do this at least 10 times and use the mean of those 10 measurements.
     * After that you double the number of high-scores and you take another 10 measurements.
     * And so forth and so forth until running it takes more the 15 seconds to add the players to the list,
     * or you reach 10.000.000 players. (Make sure you use the -Xint JVM option when measuring the time!)
     */
    @Test
    public void investigateTimeDifferentImplementations() {
        final int SECONDS_DIVIDER = 1000000000;
        final int MAX_PLAYERS_FOR_TIME_TEST = 10000000;
//        Creeer een lijst met steeds dubbele nummer tot onder de 10.000.000
        int elapsedTime = 0;
//      Start from 100
        outerloop:
        for (int listAmount = 100; listAmount < MAX_PLAYERS_FOR_TIME_TEST; listAmount = listAmount * 2) {
            for (int i = 0; i < 10; i++) {
//             Run every test 10 times
                long start = System.nanoTime();

                highScores.add(new Player("KEEGANMEIJER", "JOEYBLANKENDAAL", i*10000));
//                System.out.println(i*10000);
//                System.out.println("[+] List amount: - " + listAmount);
//                System.out.print(listAmount+";");
                long end = System.nanoTime();
//                System.out.println("[+] - Running time (nanoSeconds): " + (int)(end - start));
                System.out.print((end - start)+";");
                if ((int)(end - start)<15){break outerloop;}
            }
        }
    }
}

//        while (listAmount < 10000000){
//            listAmount = listAmount * 2;
//            System.out.println("[+] - " + listAmount);
//            for (int i = 0; i < listAmount; i++) {
//                long start = System.nanoTime();
//                highScores.add(new Player("KEEGANMEIJER", "JOEYBLANKENDAAL", i));
//                long end = System.nanoTime();
//                elapsedTime = (int)(end - start)/SECONDS_DIVIDER;
//                System.out.println(elapsedTime);
//                if (elapsedTime> 15){ return;}
//            }
//        }


//        Meet de tijd die het kost om een player te adden.

//        Print output

//        for (int num : new int[] {500, 1000, 5000, }) {
//            for (int i = 0; i < 4; i++) {
//                String sort = null;
//                int[] array = new int[num];
//                randomlyFillArray(array, 1, 1000);
//                long startTime = System.currentTimeMillis();
//                if (i == 1) {
//                    sort = "SelectionSort";
//                    SelectionSort(array);
//                } else if (i == ...) {
//                    // analogeous for other sorts
//                }
//                long stopTime = System.currentTimeMillis();
//                long elapsedTime = stopTime - startTime;
//                System.out.println(sort + " for " + num + " integers: " + elapsedTime);
//            }
//        }


//
////some try with nested loops
//        long end = System.nanoTime();
//        long elapsedTime = end - start;
//        for (int i = 0; i < 10000000; i++) {
//            System.out.println(i);
//            long seconds = System.nanoTime();
//            int dep = (int)seconds/1000000000;
//            System.out.println(dep);
//        }


    /** TODO: IMPLEMENT FOR FALLBACK SORTING METHOD
     *
     * @author Joey Blankendaal (500778751), Keegan Meijer (500781475)
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