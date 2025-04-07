import java.util.*; // Arrays, Exceptions
/**
 * Class which tests other classes' behaviour.
 * 
 * @author Srihari Vemana
 * @version ver1.0
 */
public class Test
{
    // Default constructor of Test class.
    public Test()
    {
        // No field variables to initialise.
    }

    /**
    * This is the main method of the Test class.
    * It initialises a Test class object.
    */
    public static void main(String[] args)
    {
        Test tester = new Test();
        tester.testPlayerClass();
    }

    /**
    * This method comprehensively tests the Player class.
    */
    public void testPlayerClass()
    {
        //Test 1
        System.out.println("Test 1");
        System.out.println("Player object with default constructor");
        Player p1 = new Player();
        System.out.println(p1.toString());
        System.out.println();

        //Test 2.1
        System.out.println("Test 2.1");
        System.out.println("Player object with non-default constructor and valid parameter values");
        Player p2 = new Player("player1", "Forward", 4, "A");
        System.out.println(p2.toString());
        System.out.println();

        //Test 2.2
        System.out.println("Test 2.2");
        System.out.println("Player object with non-default constructor and invalid parameter values");
        Player p3 = new Player("", "NotAPosition", -1, "4");
        System.out.println(p3.toString());
        System.out.println();

        //Test 3 - Testing Accessors
        try
        {
            System.out.println("Test 3");
            System.out.println("behing count: " + p2.getGameBehindCount());
            System.out.println("goal count: " + p2.getGameGoalCount());
            System.out.println("kick count: " + p2.getGameKickCount());
            System.out.println("pass count: " + p2.getGamePassCount());
            System.out.println("Generator object: " + p2.getGenerator());
            System.out.println("is injured: " + p2.getIsInjured());
            System.out.println("is reported: " + p2.getIsReported());
            System.out.println("is reserve: " + p2.getIsReserve());
            System.out.println("is star: " + p2.getIsStarPlayer());
            System.out.println("name: " + p2.getName());
            System.out.println(Arrays.toString(p2.getPlayerProbability()));
            System.out.println("position: " + p2.getPosition());
            System.out.println("season goals: " + p2.getSeasonGoals());
            System.out.println("team name: " + p2.getTeamName());
            System.out.println();
        }
        catch (Exception e)
        {
            System.out.println("Test 3 Failed");
        }

        //Test 4 - Testing Mutators
        // 4.1
        System.out.println("Test 4.1");
        p1.setGameBehindCount(45);
        System.out.println("Positive Test Result " + p1.getGameBehindCount());
        p1.setGameBehindCount(-1);
        System.out.println("Negative Test Result " + p1.getGameBehindCount());
        System.out.println();

        // 4.2
        System.out.println("Test 4.2");
        p1.setGameGoalCount(23);
        System.out.println("Positive Test Result " + p1.getGameGoalCount());
        p1.setGameGoalCount(-1);
        System.out.println("Negative Test Result " + p1.getGameGoalCount());
        System.out.println();

        // 4.3
        System.out.println("Test 4.3");
        p1.setGameKickCount(5);
        System.out.println("Positive Test Result " + p1.getGameKickCount());
        p1.setGameKickCount(-1);
        System.out.println("Negative Test Result " + p1.getGameKickCount());
        System.out.println();

        // 4.4
        System.out.println("Test 4.4");
        p1.setGamePassCount(67);
        System.out.println("Positive Test Result " + p1.getGamePassCount());
        p1.setGamePassCount(-1);
        System.out.println("Negative Test Result " + p1.getGamePassCount());
        System.out.println();

        //4.5
        System.out.println("Test 4.5");
        p1.setIsInjured(true);
        System.out.println("Positive Test Result " + p1.getIsInjured());
        System.out.println();

        //4.6
        System.out.println("Test 4.6");
        p1.setIsReported(true);
        System.out.println("Positive Test Result " + p1.getIsReported());
        System.out.println();

        //4.7
        System.out.println("Test 4.7");
        p1.setIsReserve(true);
        System.out.println("Positive Test Result " + p1.getIsReserve());
        System.out.println();

        //4.8
        System.out.println("Test 4.8");
        p1.setIsStarPlayer(true);
        System.out.println("Positive Test Result " + p1.getIsStarPlayer());
        System.out.println();

        //4.9
        System.out.println("Test 4.9");
        p1.setName("Hello");
        System.out.println("Positive Test Result " + p1.getName());
        p1.setName(null);
        System.out.println("Negative Test Result " + p1.getName());
        System.out.println();

        //4.10
        System.out.println("Test 4.10");
        p1.setPlayerProbability(new int[] {0,0,0,95,5,0,0});
        System.out.println("Positive Test Result: " + Arrays.toString(p1.getPlayerProbability()));
        System.out.println();

        // 4.11
        System.out.println("Test 4.11");
        p1.setPosition("Midfielder");
        System.out.println("Positive Test Result: " + p1.getPosition());
        p1.setPosition("NotAPosition");
        System.out.println("Negative Test Result: " + p1.getPosition());
        System.out.println(" ");

        // 4.12
        System.out.println("Test 4.12");
        p1.setSeasonGoals(4);
        System.out.println("Positive Test Result: " + p1.getSeasonGoals());
        p1.setSeasonGoals(-1);
        System.out.println("Negative Test Result: " + p1.getSeasonGoals());
        System.out.println(" ");

        // 4.13
        System.out.println("Test 4.13");
        p1.setTeamName("Team B");
        System.out.println("Positive Test Result: " + p1.getTeamName());
        p1.setTeamName(null);
        System.out.println("Negative Test Result: " + p1.getTeamName());
        System.out.println(" ");

        //Test 5 - Other Methods
        //5.1
        System.out.println("Test 5");
        /*
        1.	getDetails()
        2.	getPercentageOfEffectiveDisposals()
        3.	toString()
        */
        System.out.println(p1.getDetails());
        System.out.println(p1.getPercentageOfEffectiveDisposals());
        System.out.println(p1);
    }

    /**
    * Returns a string that describes the class.
    *
    * @return   the description of Test Class.
    */
    public String toString()
    {
        return "This class tests other classes.";
    }
}
