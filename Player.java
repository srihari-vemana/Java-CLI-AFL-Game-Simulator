import java.util.Arrays;
/**
 * Class which represents each player in the AFLGame simulation and their behaviour.
 * 
 * @author Srihari Vemana
 * @version ver1.0
 */
public class Player
{
    // Class variables
    protected static Generator generator = new Generator();
    protected static Validator validator = new Validator();

    //Field variables
    private int gameBehindCount;
    private int gameGoalCount;
    private int gameKickCount;
    private int gamePassCount;
    private boolean isInjured;
    private boolean isReported;
    private boolean isReserve;
    private boolean isStarPlayer;
    private String name;
    private String position;
    private int seasonGoals;
    private String teamName;
    private int[] playerProbability;

    /**
     * Default constructor for the Player class.
     *
     */
    public Player()
    {
        this.gameBehindCount = 0;
        this.gameGoalCount = 0;
        this.gameKickCount = 0;
        this.gamePassCount = 0;
        this.isInjured = false;
        this.isReported = false;
        this.isReserve = false;
        this.isStarPlayer = false;
        this.name = "Unknown";
        this.playerProbability = new int[] {0, 0, 0, 0, 0, 0, 0};
        this.position = "Unknown";
        this.seasonGoals = 0;
        this.teamName = "N";
    }

    /**
     * Parameterised constructor for the Player class.
     *
     * @param   name        Accepts the player's name as a String.
     * @param   position    Accepts the player's field position as a String.
     * @param   seasonGoals Accepts the number of goals kicked by the player in the season as an Integer.
     * @param   teamName    Accepts the player's team name as a String.
     */
    public Player(String name, String position, int seasonGoals, String teamName)
    {
        this.gameBehindCount = 0;
        this.gameGoalCount = 0;
        this.gameKickCount = 0;
        this.gamePassCount = 0;
        this.isInjured = false;
        this.isReported = false;
        this.isReserve = false;
        this.isStarPlayer = false;
        this.name = (
            validator.isValidObject(position) && 
            (name.trim().length() > 0)
            ) ? name : "Unknown";
        this.playerProbability = new int[] {0, 0, 0, 0, 0, 0, 0};
        this.position = (
            validator.isValidObject(position) && 
            validator.isInStringArray(position, new String[] {"Forward", "Midfielder", "Defender", "Reserve"})
            ) ? position : "Unknown";
        this.seasonGoals = (seasonGoals >= 0) ? seasonGoals : 0;
        this.teamName = teamName;
    }

    /**
    * This method returns a string with the player name, position and their team name.
    *
    * @return   a String with the player details.
    */
    public String getDetails()
    {
        return getName() + " (" + getPosition() + ", " + getTeamName() + ")";
    }

    /**
    * Accessor method to get the number of times the player kicked a behind in the game.
    *
    * @return   the player's current behind count as an Integer.
    */
    public int getGameBehindCount()
    {
        return this.gameBehindCount;
    }

    /**
    * Accessor method to get the number of times the player kicked a goal in the game.
    *
    * @return   the player's current goal count as an Integer.
    */
    public int getGameGoalCount()
    {
        return this.gameGoalCount;
    }

    /**
    * Accessor method to get the number of times the player kicked the ball in the game.
    *
    * @return   the player's current kick count as an Integer.
    */
    public int getGameKickCount()
    {
        return this.gameKickCount;
    }

    /**
    * Accessor method to get the number of times the player passed the ball in the game.
    *
    * @return   the player's current pass count as an Integer.
    */
    public int getGamePassCount()
    {
        return this.gamePassCount;
    }

    /**
    * Accessor method to get the Player class' generator obj.
    *
    * @return   the Player class' generator obj.
    */
    public static Generator getGenerator()
    {
        return generator;
    }

    /**
    * Accessor method to get the player's injury status.
    *
    * @return   the player's injury status as a boolean.
    */
    public boolean getIsInjured()
    {
        return this.isInjured;
    }

    /**
    * Accessor method to get to know if the player is reported or not.
    *
    * @return   the player's report status as a boolean.
    */
    public boolean getIsReported()
    {
        return this.isReported;
    }

    /**
    * Accessor method to get to know if the player is a Reserve player or not.
    *
    * @return   the player's reserve status as a boolean.
    */
    public boolean getIsReserve()
    {
        return this.isReserve;
    }

    /**
    * Accessor method to get the player's star status.
    *
    * @return   the player's star status as a boolean.
    */
    public boolean getIsStarPlayer()
    {
        return this.isStarPlayer;
    }

    /**
    * Accessor method to get the player name.
    *
    * @return   the name of the player as a String.
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * This method calculated and returns the percentage of effective disposals of the player.
    * It is defined as: ( Goals + Behinds + Passes ) * 100 / (Kicks)
    * Returns zero if number of kicks = 0.
    *
    * @return   a double with the percentage value.
    */
    public double getPercentageOfEffectiveDisposals()
    {
        if (gameKickCount != 0)
        {
            return ((gameGoalCount + gameBehindCount + gamePassCount) * 100.00 / gameKickCount);
        }
        return 0.0;
    }

    /**
    * Accessor method to get the player's outcome probabilities.
    *
    * @return   the player's outcome probablities as an Integer Array.
    */
    public int[] getPlayerProbability()
    {
        return this.playerProbability;
    }

    /**
    * Accessor method to get the player's field position.
    *
    * @return   the field position of the player as a String.
    */
    public String getPosition()
    {
        return this.position;
    }

    /**
    * Accessor method to get the number of goals kicked by the player in the season.
    *
    * @return   the number of goals kicked by the player in the season as an Integer.
    */
    public int getSeasonGoals()
    {
        return this.seasonGoals;
    }

    /**
    * Accessor method to get the player's team name.
    *
    * @return   the player's team name as a String.
    */
    public String getTeamName()
    {
        return this.teamName;
    }

    /**
    * This method decides the outcome of a kick using the player probabilities
    * and random number generated by the Generator.
    * Example:
    * If the probabilities are [30, 40, 20, 0, 0, 0, 10], number generated is 78,
    * and probability sums are [30, 70, 90, 90, 90, 90, 100]:
    *   check 1: 78 < 30 ? --> Fails. Go to next condition.
    *   check 2: 78 < 70 ? --> Fails. Go to next condition.
    *   check 3: 78 < 90 ? --> Success. This condition is selected as the outcome.
    *
    * @return   a array of Strings holding the outcome of the kick and the player
    *           position the ball is being transfered to (turnover or pass).
    */
    public String[] kickBall()
    {
        int outcome = generator.generateNumberInRange(0, 99); // Generates a number between 0 & 99
        
        // The value range the outcome integer falls into decides the outcome of the kick.
        if (outcome < generator.probabilitySum(0, this.playerProbability))
        {
            // Score Goal
            this.gameKickCount++;
            this.gameGoalCount++;
            this.seasonGoals++;
            return new String[] {"Goal",""};
        }
        else if (outcome < generator.probabilitySum(1, this.playerProbability))
        {
            // Score Behind
            this.gameKickCount++;
            this.gameBehindCount++;
            return new String[] {"Behind","Defender"};
        }
        else if (outcome < generator.probabilitySum(2, this.playerProbability))
        {
            // Pass to forward teammate
            this.gameKickCount++;
            this.gamePassCount++;
            return new String[] {"Pass","Forward"};
        }
        else if (outcome < generator.probabilitySum(3, this.playerProbability))
        {
            // Pass to midfielder teammate
            this.gameKickCount++;
            this.gamePassCount++;
            return new String[] {"Pass","Midfielder"};
        }
        else if (outcome < generator.probabilitySum(4, this.playerProbability))
        {
            // Turnover to opposition forward
            this.gameKickCount++;
            return new String[] {"Turnover","Forward"};
        }
        else if (outcome < generator.probabilitySum(5, this.playerProbability))
        {
            // Turnover to opposition midfielder
            this.gameKickCount++;
            return new String[] {"Turnover","Midfielder"};
        }
        else
        {
            // Turnover to opposition defender
            this.gameKickCount++;
            return new String[] {"Turnover","Defender"};
        }
    }

    /**
    * Mutator method to set the player's behind count.
    *
    * @param    newBehindCount  the players's new behind count.
    */
    public void setGameBehindCount(int newBehindCount)
    {
        this.gameBehindCount = (newBehindCount >= 0) ? newBehindCount : 0;
    }

    /**
    * Mutator method to set the player's goal count.
    *
    * @param    newGoalCount  the players's new goal count.
    */
    public void setGameGoalCount(int newGoalCount)
    {
        this.gameGoalCount = (newGoalCount >= 0) ? newGoalCount : 0;
    }

    /**
    * Mutator method to set the player's kick count.
    *
    * @param    newKickCount  the players's new kick count.
    */
    public void setGameKickCount(int newKickCount)
    {
        this.gameKickCount = (newKickCount >= 0) ? newKickCount : 0;
    }

    /**
    * Mutator method to set the player's pass count.
    *
    * @param    newPassCount  the players's new pass count.
    */
    public void setGamePassCount(int newPassCount)
    {
        this.gamePassCount = (newPassCount >= 0) ? newPassCount : 0;
    }

    /**
    * Mutator method to set the Player class' generator obj.
    *
    * @param    newGenerator   the Player class' new Generator class object.
    */
    public static void setGenerator(Generator newGenerator)
    {
        if (validator.isValidObject(newGenerator))
        {
            generator = newGenerator;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the player's injury status.
    *
    * @param    injuryStatus   the player's new injury status as a boolean.
    */
    public void setIsInjured(boolean injuryStatus)
    {
        this.isInjured = injuryStatus;
    }

    /**
    * Mutator method to set the player's report status.
    *
    * @param    reportStatus   the player's new report status as a boolean.
    */
    public void setIsReported(boolean reportStatus)
    {
        this.isReported = reportStatus;
    }

    /**
     * Mutator method to set the player as a reserve player.
     *
     * @param   reserveStatus   The player's new reserve status as a boolean.
     */
    public void setIsReserve(boolean reserveStatus)
    {
        this.isReserve = reserveStatus;
    }

    /**
     * Mutator method to set the player as a star player.
     *
     * @param   starStatus    The player's new star status as a boolean.
     */
    public void setIsStarPlayer(boolean starStatus)
    {
        this.isStarPlayer = starStatus;
    }

    /**
     * Mutator method to set the player name.
     *
     * @param   newName    The new player name as a string.
     */
    public void setName(String newName)
    {
        if (validator.isValidObject(newName) && (newName.trim().length() > 0))
        {
            this.name = newName;
        }
        else
        {
            this.name = "Unknown";
        }
    }

    /**
     * Mutator method to set the player's outcome probabilities.
     *
     * @param   newPlayerProbability    The new player outcome probabilities as int array.
     */
    public void setPlayerProbability(int[] newPlayerProbability)
    {
        this.playerProbability = newPlayerProbability;
    }

    /**
     * Mutator method to set the player's field position.
     *
     * @param   newPosition    The new field position as a string.
     */
    public void setPosition(String newPosition)
    {
        if (
            validator.isValidObject(newPosition) && 
            validator.isInStringArray(newPosition, new String[] {"Forward", "Midfielder", "Defender", "Reserve"})
            )
        {
            this.position = newPosition;
        }
        else
        {
            this.position = "Unknown";
        }
    }

    /**
    * Mutator method to set the number of goals kicked by the player in the season.
    *
    * @param    newSeasonGoals  the number of goals kicked by the player in the season as an int.
    */
    public void setSeasonGoals(int newSeasonGoals)
    {
        this.seasonGoals = (newSeasonGoals >= 0) ? newSeasonGoals : 0;
    }

    /**
    * Mutator method to set the player's team name.
    *
    * @param    newTeamName  the name of the player's team as a String.
    */
    public void setTeamName(String newTeamName)
    {
        if (validator.isValidObject(newTeamName) && (newTeamName.trim().length() > 0))
        {
            this.teamName = newTeamName;
        }
        else
        {
            this.teamName = "Unknown";
        }
    }

    /**
    * Returns a string that represents the current state of the object.
    *
    * @return   the current state of Player object as a String.
    */
    public String toString()
    {
        return "Player name: " + this.name +
        "\nPlayer field position:  " + this.position +
        "\nPlayer Team: " + this.teamName +
        "\nPlayer goals in this season: " + (this.seasonGoals + this.gameGoalCount) +
        "\nStar Status: " + this.isStarPlayer +
        "\nIs a reserve player: " + this.isReserve +
        "\nInjury Status: " + this.isInjured +
        "\nReported Status: " + this.isReported +
        "\nPlayer probabilities: " + Arrays.toString(this.playerProbability) + 
        "\nCurrent game goals : " + this.gameGoalCount +
        "\nCurrent game behinds : " + this.gameBehindCount +
        "\nCurrent game passes : " + this.gamePassCount +
        "\nCurrent game kicks : " + this.gameKickCount + "\n";
    }
}
