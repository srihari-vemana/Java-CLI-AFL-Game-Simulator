import java.util.ArrayList;
import java.util.Collections;
/**
 * Class which represents each Team in the AFLGame simulation and their behaviour.
 * 
 * @author Srihari Vemana
 * @version ver1.0
 */
 public class Team
 {
    // Class variables
    private static Generator generator = new Generator();
    private static String[] playerPositions = {"Forward", "Midfielder", "Defender"};
    private static Validator validator = new Validator();

    // Field variables
    private int behindCount;
    private int goalCount;
    private ArrayList<Integer> injuredPlayerIndices;
    private ArrayList<Player> players;
    private ArrayList<Integer> reportedPlayerIndices;
    private ArrayList<Integer> reservePlayerIndices;
    private String teamName;

    /**
     * Default constructor which creates the object of the Team class.
     *
     */
    public Team()
    {
        this.behindCount = 0;
        this.goalCount = 0;
        this.injuredPlayerIndices = new ArrayList<>();
        this.players = new ArrayList<>();
        this.reportedPlayerIndices = new ArrayList<>();
        this.reservePlayerIndices = new ArrayList<>();
        this.teamName = "Unknown";
    }

    /**
     * Parameterised constructor which creates an object of the Team class.
     *
     * @param   teamName    Accepts the team's name as a String.
     * @param   players     Accepts an ArrayList of player class objects that belong to the team.
     */
    public Team(String teamName, ArrayList<Player> players)
    {
        this.behindCount = 0;
        this.goalCount = 0;
        this.injuredPlayerIndices = new ArrayList<>();
        this.players = players;
        this.reportedPlayerIndices = new ArrayList<>();
        this.reservePlayerIndices = new ArrayList<>();
        this.teamName = teamName;
    }

    /**
    * Accessor method to get the team's current behind count.
    *
    * @return   the team's current behind count as an Integer.
    */
    public int getBehindCount()
    {
        return this.behindCount;
    }

    /**
    * Accessor method to get the team's current goal count.
    *
    * @return   the team's current goal count as an Integer.
    */
    public int getGoalCount()
    {
        return this.goalCount;
    }

    /**
    * Accessor method to get the Team class' generator obj.
    *
    * @return   the Team class' generator obj.
    */
    public static Generator getGenerator()
    {
        return generator;
    }

    /**
    * Accessor method to get the player-list index values of injured players.
    *
    * @return   an ArrayList of index values that belong to the injured players.
    */
    public ArrayList<Integer> getInjuredPlayerIndices()
    {
        return this.injuredPlayerIndices;
    }

    /**
    * This method takes a player object and finds its respective
    * index value in the players ArrayList and returns it. 
    *
    * @param    player  the player object whose index value is to be found.
    *
    * @return   index value of the player as an Integer. Return -1 if player is not in ArrayList.
    */
    public int getPlayerIndex(Player player)
    {
        for (int i = 0; i < this.players.size(); i++)
        {
            if (this.players.get(i) == player)
            {
                return i;
            }
        }
        // Player not found.
        System.out.println(">> ERROR: PLAYER NOT FOUND");
        return -1;
    }

    /**
    * Accessor method to get the team's players.
    *
    * @return   an ArrayList of Player objects that belong to the team.
    */
    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    /**
    * This method finds the players with the highest number of kicks and goals in a team.
    *
    * @param    metric  A string representing the metric to be counted.
    * 
    * @return   an ArrayList of player objects of player with highest goal/kick count.
    */
    public ArrayList<Player> getPlayersWithHighestMetricCount(String metric)
    {
        int maxCount = 0;
        ArrayList<Player> playersWithHighestMetricCount = new ArrayList<Player>();
        for (Player player : players)
        {
            int playerMetricCount = (metric.equals("kicks")) ? player.getGameKickCount() : player.getGameGoalCount();
            if (playerMetricCount == maxCount)
            {
                playersWithHighestMetricCount.add(player);
            }
            else if (playerMetricCount > maxCount)
            {
                maxCount = playerMetricCount;
                playersWithHighestMetricCount = new ArrayList<Player>();
                playersWithHighestMetricCount.add(player);
            }
        }
        return playersWithHighestMetricCount;
    }

    /**
    * This method returns the player index start and end values for the specified player position.
    * If position match is not found, the reserve player index range is returned.
    * The values are fixed as the input text file is always in a certain order.
    *
    * @param    position    the position of the player index range to be returned as an Integer Array.
    *
    * @return   an Integer Array of the player position index start and end values.  
    */
    public int[] getPositionRange(String position)
    {
        int[] positionRange = new int[2];
        if (validator.isInStringArray(position, Team.playerPositions))
        {
            if (position.equals("Forward"))
            {
                // Every team has 5 forwards.
                positionRange[0] = 0;
                positionRange[1] = 4;
            }
            else if (position.equals("Midfielder"))
            {
                // Every team has 8 midfielders.
                positionRange[0] = 5;
                positionRange[1] = 12;
            }
            else
            {
                // Every team has 5 defenders.
                positionRange[0] = 13;
                positionRange[1] = 17;
            }
        }
        else
        {
           // Set postition range to reserve player index range.
           // Every team has 4 reserve players.
           positionRange[0] = 18;
           positionRange[1] = 21;
        }
        return positionRange;
    }

    /**
    * This method picks a random un-injured player of a specific position from
    * the team and returns the player obj from the players ArrayList.
    *
    * @param    position    the position of the random player as a String.
    *
    * @return   the random player obj.
    */
    public Player getRandomPlayer(String position)
    {
        boolean isValidPlayerIndex = false;
        int randomPlayerIndex = -1;

        int[] positionIndexRange = this.getPositionRange(position);
        while(!isValidPlayerIndex)
        {
            randomPlayerIndex = generator.generateNumberInRange(positionIndexRange[0], positionIndexRange[1]);

            // Check if random player index is not of an injured player since
            // injured and reserve players are stored in the same index range.
            if (!this.injuredPlayerIndices.contains(randomPlayerIndex))
            {
                isValidPlayerIndex = true;
            }
        }
        return this.players.get(randomPlayerIndex);
    }

    /**
    * Accessor method to get the player-list index values of reported players.
    *
    * @return   an ArrayList of index values that belong to the reported players.
    */
    public ArrayList<Integer> getReportedPlayerIndices()
    {
        return this.reportedPlayerIndices;
    }

    /**
    * Accessor method to get the team's name.
    *
    * @return   the team's name as a String.
    */
    public String getTeamName()
    {
        return this.teamName;
    }

    /**
    * Accessor method to get the team's current score.
    *
    * @return   the team's current score as an Integer.
    */
    public int getTeamScore()
    {
        return ((this.goalCount * 6) + this.behindCount);
    }

    /**
    * Accessor method to get the Validator object.
    *
    * @return   the Validator object.
    */
    public static Validator getValidator()
    {
        return validator;
    }

    /**
    * This method adds the player-list index of a reported player to the
    * reported player index list. 
    *
    * @param    reportedPlayer the Player obj of the reported player. 
    */
    public void recordReportedPlayer(Player reportedPlayer)
    {
        this.reportedPlayerIndices.add(getPlayerIndex(reportedPlayer));
    }

    /**
    * Mutator method to set the team's behind count.
    *
    * @param    newBehindCount  the team's new behind count.
    */
    public void setBehindCount(int newBehindCount)
    {
        this.behindCount = newBehindCount;
    }

    /**
    * Mutator method to set the Team class' generator obj.
    *
    * @param    newGenerator   the Team class' new Generator class object.
    */
    public static void setGenerator(Generator newGenerator)
    {
        generator = newGenerator;
    }

    /**
    * Mutator method to set the team's goal count.
    *
    * @param    newGoalCount  the team's new goal count.
    */
    public void setGoalCount(int newGoalCount)
    {
        this.goalCount = newGoalCount;
    }

    /**
    * Mutator method to set the injured player indices.
    *
    * @param    newInjuredPlayerIndices   the player indices as an ArrayList of Integers.
    */
    public void setInjuredPlayerIndices(ArrayList<Integer> newInjuredPlayerIndices)
    {
        if (validator.isValidObject(newInjuredPlayerIndices))
        {
            this.injuredPlayerIndices = newInjuredPlayerIndices;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }
    
    /**
    * Mutator method to set the player list of the team.
    *
    * @param    newPlayers   the team's new players as an ArrayList of Player objects.
    */
    public void setPlayers(ArrayList<Player> newPlayers)
    {
        if (validator.isValidObject(newPlayers))
        {
            this.players = newPlayers;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the reported player indices.
    *
    * @param    newReportedPlayerIndices   the player indices as an ArrayList of Integers.
    */
    public void setReportedPlayerIndices(ArrayList<Integer> newReportedPlayerIndices)
    {
        if (validator.isValidObject(newReportedPlayerIndices))
        {
            this.reportedPlayerIndices = newReportedPlayerIndices;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the reserve player indices.
    *
    * @param    newReservePlayerIndices   the player indices as an ArrayList of Integers.
    */
    public void setReservePlayerIndices(ArrayList<Integer> newReservePlayerIndices)
    {
        if (validator.isValidObject(newReservePlayerIndices))
        {
            this.reservePlayerIndices = newReservePlayerIndices;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * This method selects a number of random players from
    * the player list and sets them as Star Players.
    *
    *  @param   starPlayerCount  the number of star players to be set as an Integer.
    */
    public void setStarPlayers(int starPlayerCount)
    {
        int generatedNumber;
        int[] generatedNumbers = new int[starPlayerCount]; // Maintain an array to ensure all generated numbers are unique
        boolean isValidNumber;
        Player player;

        // Loop till the required number of star players are set.
        for (int i = 0; i < starPlayerCount; i++)
        {
            isValidNumber = false;
            while(!isValidNumber)
            {
                generatedNumber = generator.generateNumberInRange(0,this.players.size() - 1);

                // Check if number generated is new & unique and set player at index of generated number as star player.
                if (!validator.isInIntegerArray(generatedNumber, generatedNumbers))
                {
                    player = this.players.get(generatedNumber);
                    player.setIsStarPlayer(true);
                    generatedNumbers[i] = generatedNumber;
                    isValidNumber = true;
                }
            }
        }
    }

    /**
    * Mutator method to set the team's name.
    *
    * @param    newTeamName   the team's new name as a String.
    */
    public void setTeamName(String newTeamName)
    {
        this.teamName = newTeamName;
    }

    /**
    * Mutator method to set the Validator object.
    *
    * @param    newValidator  the Validator object to be set.
    */
    public static void setValidator(Validator newValidator)
    {
        if (validator.isValidObject(newValidator))
        {
            validator = newValidator;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * This method swaps the injured & reserve players back to their original index values.
    * We swap back in reverse order to swap players back to their original positions. 
    *
    */
    public void swapPlayersBack()
    {
        try
        {
            for (int i = this.reservePlayerIndices.size() - 1; i >= 0 ; i--)
            {
                Collections.swap(this.players, reservePlayerIndices.get(i), injuredPlayerIndices.get(i));
            }
        }
        catch (Exception e)
        {
            System.out.println("Index Out Of Range. Swap unsuccessful");
        }
    }

    /**
    * This method swaps the player objects of the injured player
    * and a random reserve player in the players ArrayList.
    * After the swap, reserve player index will store the injured player obj
    * and injured player index will store the reserve player obj.
    *
    * @param    injuredPlayer   the player obj of the injured player to we swapped.  
    *
    * @return   the resever player obj replacing the injured player.
    */
    public Player swapWithReserve(Player injuredPlayer)
    {
        if (validator.isValidObject(injuredPlayer))
        {
            Player reservePlayer = this.getRandomPlayer("Reserve");
            int reservePlayerIndex = this.getPlayerIndex(reservePlayer);
            int injuredPlayerIndex = this.getPlayerIndex(injuredPlayer);

            // Swap injured and reserve player objects in the players ArrayList.
            Collections.swap(this.players, injuredPlayerIndex, reservePlayerIndex);

            this.injuredPlayerIndices.add(this.getPlayerIndex(injuredPlayer));
            this.reservePlayerIndices.add(this.getPlayerIndex(reservePlayer));
            return reservePlayer;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
            return new Player();
        }
    }

    /**
    * Returns a string that describes the state of the object.
    *
    * @return   the state of the Team object as a String.
    */
    public String toString()
    {
        return  "Team name: " + this.teamName +
                "\nCurrent team score: " + getTeamScore() +
                "\nNumber of behinds scored: " + this.behindCount +
                "\nNumber of goals scored: " + this.goalCount + 
                "\nArrayList of injured player indices: " + this.injuredPlayerIndices +
                "\nArrayList of reported player indices: " + this.reportedPlayerIndices +
                "\nArrayList of reserve player indices: " + this.reservePlayerIndices +
                "\nArrayList of player objects: " + this.players;
    }
 }
