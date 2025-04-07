/**
 * Class used to generate random numbers, calculate probability of 
 * event occuring and calculating sums of arrays.
 * 
 * @author Srihari Vemana
 * @version ver1.0
 */
public class Generator
{
    // Class variables
    public static final double probOfReportPerEvent = 1 - Math.pow((1 - 0.01), (1.0/320));

    // Field variables
    int randomNumber;

    /**
     * Default constructor which creates the object of the Generator class.
     *
     */
    public Generator()
    {
        this.randomNumber = -1;
    }

    /**
     * Parameterised constructor which creates an object of the Generator class.
     *
     * @param   randomNumber    an default value for the random number as an Integer.
     */
    public Generator(int randomNumber)
    {
        this.randomNumber = randomNumber;
    }

    /**
    * This method emulates the chance of an event occuring based on a
    * threshold percentage(integer) value.
    * Example:
    *   If an event has 5% chance of occuring, it would occur when a number
    *   randomly generated between 0-99(inclusive) is less than 5.
    *
    * @return   a boolean indicative of the event's occurence
    */
    public boolean checkChance(int threshold)
    {
        boolean isConditionMet = false;
        randomNumber = generateNumberInRange(0,99);
        if (randomNumber < threshold)
        {
            isConditionMet = true;
        }
        return isConditionMet;
    }

    /**
    * This method emulates a 2% chance of player injury per event and returns a
    * boolean which indicates the outcome of the check.
    *
    * @return a boolean representing the injury status.
    */
    public boolean checkInjuryChance()
    {
        return checkChance(2);
    }

    /**
    * This method emulates a 1% chance of player getting reporting during a game
    *  and returns a boolean which indicates the outcome of the check.
    *
    * Deduced through mathematics of Cumulative Probability.
    * Derivation:
    *   Let probability of "Something" during an event = p
    *   Probability of Something not occuring = 1-p
    *   Probability of Something not occuring 'm' events in a row: (1-p)^m
    *   Thus, probability of "Something occuring once in m events" (X): 1 - (1-p)^made
    *   We have:
    *       p = probOfReportPerEvent
    *       X = 0.01 (1%)
    *       "Something" = report
    *       NoOfQuarters = 4
    *       NoOfEventsPerQuarter = 80
    *       m = Number of events per game = NoOfQuarters * NoOfEventsPerQuarter = 4 * 80 = 360
    *       0.01 = 1 - (1 - p)^360
    *       p = 1 - (1 - 0.01)^(1/360)
    *
    * @return a boolean representing the report status.
    */
    public boolean checkReportChance()
    {
        return (Math.random() < probOfReportPerEvent) ? true : false;
    }

    /**
    * This method generates a random number within a given range.
    * The range is defined by min and max params, the range is inclusive of min and max values.
    *
    * @param    min     an integer that represents the minimum value that can be generated.
    * @param    max     an integer that represents the maximum value that can be generated.
    *
    * @return   the randomly generated integer.
    */
    public int generateNumberInRange(int min, int max)
    {
        // swap values if max is less than min
        if (max < min)
        {
            min = max + min;
            max = min - max;
            min = min - max;
        }
        int range = max - min + 1;
        this.randomNumber = (int)(Math.random() * range + min);
        return this.randomNumber;
    }

    /**
    * Accessor method to get the most recent random number generated.
    *
    * @return   the most recently generated random number as an Integer.
    */
    public int getRandomNumber()
    {
        return this.randomNumber;
    }

    /**
    * This method picks one of the two teams by random
    * and returns the team object that was chosen.
    *
    * @param    teamA   Team object of Team A.
    * @param    teamB   Team object of Team B.
    *
    * @return   the team obj of the chosen team.
    */
    public Team pickRandomTeam(Team teamA, Team teamB)
    {
        randomNumber = generateNumberInRange(1,100);
        // Team A is chosen if generated number is even. Else, Team B is chosen.
        return (randomNumber % 2 == 0) ? teamA : teamB; 
    }

    /**
    * This method takes an array of integers and returns the sum of
    * the values up to a certain index(range) in the array.
    *
    * @param    range               the index value up to which the sum is being calculated, as an Integer.
    * @param    playerProbability   the array of integers on which the summation is being performed.
    *
    * @return   the sum of the values of the array within the range.
    */
    public int probabilitySum(int range, int[] playerProbability)
    {
        int sum = 0;
        for (int i = 0; i <= range; i++)
        {
            sum += playerProbability[i];
        }
        return sum;
    }

    /**
    * Mutator method to set the value of the field variable.
    *
    * @param    newRandomNumber     the new value to be set, as an Integer.
    */
    public void setRandomNumber(int newRandomNumber)
    {
        this.randomNumber = newRandomNumber;
    }

    /**
    * Returns a string that describes the class.
    *
    * @return   the description of Generator Class.
    */
    public String toString()
    {
        return "This class generates random integers and integer sums." +
               "\nThe most recently generated random number is: " + this.randomNumber;
    }
}
