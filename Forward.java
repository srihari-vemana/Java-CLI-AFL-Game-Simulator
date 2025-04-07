/**
 * This class is initialises a subclass of the Player class. 
 * The position of the player is pre-defined and it has position specific player probabilities.
 * 
 * @author Srihari Vemana
 * @version ver1.0
 */
public class Forward extends Player
{
    // Class variables
    private static final int[] forwardProbability = new int[] {30, 40, 20, 0, 0, 0, 10};
    private static final String position = "Forward";
    private static final int[] starForwardProbability = new int[] {45, 40, 10, 0, 0, 0, 5};

    /**
     * Default constructor for the Forward class.
     *
     */
    public Forward()
    {
        super();
        setPlayerProbability(forwardProbability);
    }

    /**
     * Parameterised constructor for the Forward class.
     *
     * @param   name        Accepts the player's name as a String.
     * @param   seasonGoals Accepts the number of goals kicked by the player in the season as an Integer.
     * @param   teamName    Accepts the player's team name as a String.
     */
    public Forward(String name, int seasonGoals, String teamName)
    {
        super(name, position, seasonGoals, teamName);
        setPlayerProbability(forwardProbability);
    }

    /**
    * Accessor method to get the Player class' generator obj.
    * It is accessible here due to "protected" access modifier.
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
        return super.getIsInjured();
    }

    /**
    * Accessor method to get to know if the player is a Reserve player or not.
    *
    * @return   the player's reserve status as a boolean.
    */
    public boolean getIsReserve()
    {
        return super.getIsReserve();
    }

    /**
    * Accessor method to get the player's star status.
    *
    * @return   the player's star status as a boolean.
    */
    public boolean getIsStarPlayer()
    {
        return super.getIsStarPlayer();
    }

    /**
    * Accessor method to get the player name.
    *
    * @return   the name of the player as a String.
    */
    public String getName()
    {
        return super.getName();
    }

    /**
    * Accessor method to get the player's outcome probabilities.
    *
    * @return   the player's outcome probablities as an Integer Array.
    */
    public int[] getPlayerProbability()
    {
        return super.getPlayerProbability();
    }

    /**
    * Accessor method to get the player's field position.
    *
    * @return   the field position of the player as a String.
    */
    public String getPosition()
    {
        return super.getPosition();
    }

    /**
    * Accessor method to get the number of goals kicked by the player in the season.
    *
    * @return   the number of goals kicked by the player in the season as an Integer.
    */
    public int getSeasonGoals()
    {
        return super.getSeasonGoals();
    }

    /**
    * Accessor method to get the player's team name.
    *
    * @return   the player's team name as a String.
    */
    public String getTeamName()
    {
        return super.getTeamName();
    }

    /**
    * Mutator method to set the player as a star player and update player probabilities
    *
    * @param   starStatus    The player's new star status as a boolean.
    */
    @Override
    public void setIsStarPlayer(boolean starStatus)
    {
        super.setIsStarPlayer(starStatus);
        setPlayerProbability(starForwardProbability);
    }

    /**
    * Returns a string that describes the state of the object.
    *
    * @return   the state of the object as a String.
    */
    public String toString()
    {
        return super.toString();
    }
}
