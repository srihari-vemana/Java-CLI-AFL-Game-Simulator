import java.util.ArrayList;
/**
 * Class which simulates a game of Australian rules football based on random chance.
 * Two teams are involved and the user decides the number of star players in each team.
 * Players can be injured and reported.
 * The game involves 4 quarters and 80 events per quarter.
 *
 * This class contains the main method.
 * 
 * @author Srihari Vemana
 * @version ver1.0
 */
public class AFLGame
{
    // Class variables
    private static FileIO fileIO = new FileIO();
    private static Generator generator = new Generator();
    private static Input input = new Input();
    private static Validator validator = new Validator();

    // Field variables
    private Player ballCarrier;
    private Team holdingTeam;
    private boolean isGameLoopValid;
    private Player previousBallCarrier;
    private Team teamA;
    private Team teamB;

    /**
     * Default constructor which creates the object of AFLGame class.
     *
     */
    public AFLGame()
    {
        this.ballCarrier = null;
        this.holdingTeam = null;
        this.isGameLoopValid = true;
        this.previousBallCarrier = null;
        this.teamA = null;
        this.teamB = null;
    }

    /**
     * Parameterised constructor which creates the object of AFLGame class.
     *
     * @param   ballCarrier     the Player object currently in possession of the ball.
     * @param   holdingTeam     the Team object to which the ballCarrier belongs.
     * @param   isGameLoopValid the boolean which indicates if game loop is valid or should be terminated.
     * @param   previousBallCarrier the Player object of the player that previously held the ball.
     * @param   teamA           the Team object of Team A.
     * @param   teamB           the Team object of Team B.
     */
    public AFLGame(
                    Player ballCarrier,
                    Team holdingTeam,
                    boolean isGameLoopValid,
                    Player previousBallCarrier,
                    Team teamA,
                    Team teamB
                    )
    {
        this.ballCarrier = ballCarrier;
        this.holdingTeam = holdingTeam;
        this.isGameLoopValid = isGameLoopValid;
        this.previousBallCarrier = previousBallCarrier;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    /**
    * This method prints the statistics of each player in a team after a match.
    * (Name, Kicks, Goals, Behinds, Passes, %ofEffectiveDisposals, Position)
    *
    * @param    teamObj the Team object whose player stats are to be printed.
    */
    public void displayIndividualPlayerStats(Team teamObj)
    {
        ArrayList<Player> players = teamObj.getPlayers();
        System.out.println("  Individual statistics: ");
        int metricLength = 10; // Length of each cell in the grid to be printed.
        System.out.println("    Name          Kicks     Goals     Behinds   Pass      Percent   Position"); // Headers.
        for (Player player: players)
        {   
            // Player name.
            String playerName = player.getName();
            int isStarPlayer = (player.getIsStarPlayer()) ? 1 : 0;
            System.out.print("    " + playerName + ((isStarPlayer == 1) ? "*" : "") + " ".repeat(metricLength + 4 - (playerName.length() + isStarPlayer)));
            
            // Player kick count in the game.
            int kickCount = player.getGameKickCount();
            System.out.print(kickCount + " ".repeat(metricLength - String.valueOf(kickCount).length()));

            // Player goal count in the game.
            int goalCount = player.getGameGoalCount();
            System.out.print(goalCount + " ".repeat(metricLength - String.valueOf(goalCount).length()));

            // Player behind count in the game.
            int behindCount = player.getGameBehindCount();
            System.out.print(behindCount + " ".repeat(metricLength - String.valueOf(behindCount).length()));

            // Player passes count in the game.
            int passCount = player.getGamePassCount();
            System.out.print(passCount + " ".repeat(metricLength - String.valueOf(passCount).length()));

            // Player %ofEffectiveDisposals in the game, in 999.99 format.
            String percentage = String.format("%.2f", player.getPercentageOfEffectiveDisposals());
            System.out.print(percentage + "%" + " ".repeat(metricLength - (percentage.length() + 1)));

            //Player details along with status effects.
            System.out.println
            (
                player.getPosition() +
                ((player.getIsInjured()) ? " (injured)" : "") +
                ((player.getIsReserve()) ? ((player.getPosition().equals("Reserve")) ? "" : " (Reserve)") : "") +
                ((player.getIsReported()) ? " Reported" : "")
            );
        }
        System.out.println("");
    }

    /**
    * This method prints the players that were injured or reported
    * in the simulated game for both teams.
    *
    * @param    condition   the String which decides which players details to print -- Reported or Injured
    */
    public void displayPlayerDetails(String condition)
    {   
        if (validator.isInStringArray(condition, new String[] {"Injured", "Reported"}))
        {
            ArrayList<Integer> playerIndices = null;
            ArrayList<Player> allTeamPlayers = null;
            System.out.println(condition + " Players");
            System.out.println("===============");

            for (int i = 0; i < 2; i++)
            {   
                Team teamObj = (i == 0) ? teamA : teamB;
                playerIndices = (condition.equals("Injured"))
                                ? teamObj.getInjuredPlayerIndices()
                                : teamObj.getReportedPlayerIndices();
                allTeamPlayers = teamObj.getPlayers();
                System.out.println("Team: " + teamObj.getTeamName());
                for (int j : playerIndices)
                {
                    Player player = allTeamPlayers.get(j);
                    System.out.println("  " + player.getName() + " (" + player.getPosition() + ")");
                }
            }
            System.out.println("");   
        }
        else
        {
            System.out.println("Error: Invalid Conidition entered. Please enter either 'Reported' or 'Injured'");
        }
    }

    /**
    * This method prints the summary of the quarter which includes
    * the name and score of each team in the game so far. 
    *
    */
    public void displayQuarterSummary()
    {
        System.out.println("\nQuarter has finished\n");
        System.out.println("Quarter Summary");
        System.out.println("    " + teamA.getTeamName() + " has " + teamA.getTeamScore() + " points");
        System.out.println("    " + teamB.getTeamName() + " has " + teamB.getTeamScore() + " points");
    }

    /**
    * This method prints the player kick or goal stats for a given
    * list of players.
    *
    * @param    players an ArrayList of Player objects whose stats are to be printed.
    * @param    metric  a String which decides which metric to print.
    */
    public void displayPlayerKickStats(ArrayList<Player> players, String metric)
    {
        if (validator.isInStringArray(metric, new String[] {"kicks", "goals"}))
        for (Player player : players)
        {
            if (metric.equals("kicks"))
            {
                System.out.println("    " + player.getName() + " kicked the ball " + player.getGameKickCount() + " times");
            }
            else if (metric.equals("goals"))
            {
                System.out.println("    " + player.getName() + " kicked " + player.getGameGoalCount() + " goals");
            }
            else
            {
                System.out.println("Invalid player metric.");
            }
        }
        else
        {
            System.out.println("Error: Invalid metric entered. Please enter 'goals' or 'kicks'");
        }
    }

    /**
    * This method prints the statistics of the team in the game, onto the console.
    * The stats include:
    *   Goals, Behinds, Team Score, Players with highest no. of kicks in the game, Players with highest no. of goals in game.
    * 
    * @param    teamObj the Team object of the team whose stats are to be printed. 
    */
    public void displayTeamStats(Team teamObj)
    {
        System.out.println(teamObj.getTeamName());
        System.out.println("  Total goals: " + teamObj.getGoalCount());
        System.out.println("  Total behinds: " + teamObj.getBehindCount());
        System.out.println("  Total score: " + teamObj.getTeamScore());

        System.out.println("  Who has the greatest number of kicks?");
        displayPlayerKickStats(teamObj.getPlayersWithHighestMetricCount("kicks"), "kicks");

        System.out.println("  Who kicked the most goals?");
        displayPlayerKickStats(teamObj.getPlayersWithHighestMetricCount("goals"), "goals");

        displayIndividualPlayerStats(teamObj);
    }

    /**
    * This method displays the game results and writes the output files.
    * It is called at the end of the game simulation.
    * 
    */
    public void endGame()
    {
        System.out.println("\nGame result");
        System.out.println("=============");
        if (!isGameLoopValid)
        {
             System.out.println(holdingTeam.getTeamName() + " wins the match with " + holdingTeam.getTeamScore() + " points as opponent team forfeits.\n");
        }
        else if (teamA.getTeamScore() == teamB.getTeamScore())
        {
            System.out.println("The game is a draw with both teams scoring " + teamA.getTeamScore() + " points");
        }
        else
        {   
            holdingTeam = (teamA.getTeamScore() > teamB.getTeamScore()) ? teamA : teamB; // Using variable to track winning team.
            System.out.println(holdingTeam.getTeamName() + " wins the match with " + holdingTeam.getTeamScore() + " points.\n");
        }
        // Swapping reserve and injured players back to their original indices.
        teamA.swapPlayersBack();
        teamB.swapPlayersBack();

        displayTeamStats(teamA);
        displayTeamStats(teamB);
        displayPlayerDetails("Injured");
        displayPlayerDetails("Reported");

        // Call the functions to write the output text files.
        System.out.println("Writing output files...");
        fileIO.writeTeamFile(teamA);
        fileIO.writeTeamFile(teamB);

        System.out.println("Goodbye!");
    }

    /**
    * Accessor method to get the player current holding the ball.
    *
    * @return   the Player object of the player currently holding the ball.
    */
    public Player getBallCarrier() 
    { 
        return this.ballCarrier;
    }

    /**
    * Accessor method to get the AFLGame class' generator obj.
    *
    * @return   the Generator class object.
    */
    public static Generator getGenerator() 
    { 
        return generator;
    }

    /**
    * Accessor method to get the team that is currently in possession of the ball.
    *
    * @return   the Team object of the ball possessing team.
    */
    public Team getHoldingTeam()
    {
        return this.holdingTeam;
    }

    /**
    * Accessor method to get the AFLGame class' input obj.
    *
    * @return   the Input class object.
    */
    public static Input getInput() 
    { 
        return input;
    }

    /**
    * Accessor method to get the game loop validity status.
    *
    * @return   the game loop validity as a boolean.
    */
    public boolean getIsGameLoopValid()
    {
        return this.isGameLoopValid;
    }

    /**
    * Accessor method to get the player previously holding the ball.
    *
    * @return   the Player object of the player previously holding the ball.
    */
    public Player getPreviousBallCarrier() 
    { 
        return this.previousBallCarrier;
    }

    /**
    * Accessor method to get the object referencing Team A.
    *
    * @return   the Team class object of Team A.
    */
    public Team getTeamA()
    {
        return this.teamA;
    }

    /**
    * Accessor method to get the object referencing Team B.
    *
    * @return   the Team class object of Team B.
    */
    public Team getTeamB()
    {
        return this.teamB;
    }

    /**
    * Accessor method to get the AFLGame class' validator obj.
    *
    * @return   the Validator class object.
    */
    public static Validator getValidator()
    {
        return validator;
    }

    /**
    * This main method which initialises the AFLGame class and
    * calls the method to initialise the game simulation.
    *
    */
    public static void main(String[] args)
    {
        AFLGame gameObject = new AFLGame();
        gameObject.startGame();
    }

    /**
    * Mutator method to set the player currently holding the ball.
    *
    * @param   newBallCarrier   the Player class object of the player to be set.
    */
    public void setBallCarrier(Player newBallCarrier)
    {
        if (validator.isValidObject(newBallCarrier))
        {
            this.ballCarrier = newBallCarrier;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the generator object for AFLGame class.
    *
    * @param   newGenerator   The Generator class object to be set.
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
    * Mutator method to set the team currently holding the ball.
    *
    * @param   newHoldingTeam   the Team class object of the team to be set.
    */
    public void setHoldingTeam(Team newHoldingTeam)
    {
        if (validator.isValidObject(newHoldingTeam))
        {
            this.holdingTeam = newHoldingTeam;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the input object for AFLGame class.
    *
    * @param   newInput   The Input class object to be set.
    */
    public static void setInput(Input newInput)
    {
        if (validator.isValidObject(newInput))
        {
            input = newInput;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the game loop validity status.
    *
    * @param   validityStatus   the game loop validity as a boolean.
    */
    public void setIsGameLoopValid(boolean validityStatus)
    {   
        this.isGameLoopValid = validityStatus;
    }

    /**
    * Mutator method to set the player previously holding the ball.
    *
    * @param   newPreviousBallCarrier   the Player class object of the player to be set.
    */
    public void setPreviousBallCarrier(Player newPreviousBallCarrier)
    {
        if (validator.isValidObject(newPreviousBallCarrier))
        {
            this.previousBallCarrier = newPreviousBallCarrier;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * This method takes user input and sets the number of star players in each team.
    * Valid inputs: Integer in range 0-8
    *
    */
    public void setStarPlayers()
    {
        int starPlayerCount;
        String userInput;
        Team team;
        for (int i = 0; i < 2; i++)
        {
            boolean isValidCount = false;
            while (!isValidCount)
            {
                team = (i == 0) ? teamA : teamB;
                userInput = input.acceptStringInput("Enter number of star players in " + team.getTeamName() + " [0-8]: ");
                try
                {
                    starPlayerCount = Integer.valueOf(userInput);
                    if (validator.isIntegerInRange(starPlayerCount, 0, 8))
                    {
                        team.setStarPlayers(starPlayerCount);
                        isValidCount = true;
                    }
                    else
                    {
                        System.out.println("Error: Please only enter an Integer between 0 & 8. Try again.");
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error: Not an Integer. Try again.");
                }
            }
        }  
    }

    /**
    * Mutator method to set the Team object for Team A.
    *
    * @param    newTeamA   the Team class object of the team to be set.
    */
    public void setTeamA(Team newTeamA)
    {
        if (validator.isValidObject(newTeamA))
        {
            this.teamA = newTeamA;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the Team object for Team B.
    *
    * @param    newTeamB   the Team class object of the team to be set.
    */
    public void setTeamB(Team newTeamB)
    {
        if (validator.isValidObject(newTeamB))
        {
            this.teamB = newTeamB;
        }
        else
        {
            System.out.println("Error: Entered object is NULL");
        }
    }

    /**
    * Mutator method to set the validator object for AFLGame class.
    *
    * @param   newValidator   The Validator class object to be set.
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
    * This method simulates the event of a ball being kicked.
    * It handles all the outcomes of a ball kick and calls the appropriate 
    * method to handle player injury and reporting.
    */
    public void simulateBallKick()
    {
        String[] outcome = ballCarrier.kickBall(); // Array holding event outcome and next player position.
        previousBallCarrier = ballCarrier;
        boolean injuryChance = false;
        boolean reportChance = false;

        if (outcome[0].equals("Turnover"))
        {
            switchHoldingTeam();
            ballCarrier = holdingTeam.getRandomPlayer(outcome[1]);
            System.out.println(previousBallCarrier.getDetails() + " lost the ball to " + ballCarrier.getDetails());
            injuryChance = generator.checkInjuryChance();
            reportChance = generator.checkReportChance();
        }
        else if (outcome[0].equals("Pass"))
        {
            ballCarrier = holdingTeam.getRandomPlayer(outcome[1]);
            System.out.println(previousBallCarrier.getDetails() + " passed the ball to " + ballCarrier.getDetails());
            injuryChance = generator.checkInjuryChance();
            reportChance = generator.checkReportChance();
        }
        else if (outcome[0].equals("Behind"))
        {
            System.out.println(ballCarrier.getDetails() + " got a behind, scored 1 point");
            holdingTeam.setBehindCount(holdingTeam.getBehindCount() + 1); // Update team behind count
            switchHoldingTeam();
            ballCarrier = holdingTeam.getRandomPlayer(outcome[1]);
        }
        else
        {
            System.out.println(ballCarrier.getDetails() + " got a goal, scored 6 points");
            holdingTeam.setGoalCount(holdingTeam.getGoalCount() + 1); // Update team goal count
            ballCarrier = null; // Ball is returned to the center of the field
        }

        // Accouncing the current ball carrier at the end of the event.
        if (!outcome[0].equals("Goal"))
        {
            System.out.println(ballCarrier.getDetails() + " has the ball");
        }

        // Check if injury occurs during Turnover or Pass.
        if (injuryChance)
        {
            simulateInjury(previousBallCarrier);
        }

        // Check if reporting occurs during Turnover or Pass.
        if (reportChance)
        {
            simulateReport(previousBallCarrier);
        }
    }

    /**
    * This method simulated benching of an injured player and replacing
    * the injured player with a reserve player.
    * It terminated the game loop if number of injured players exceeds 4.
    *
    * @param injuredPlayer  the Player class object of the injured player.
    */
    public void simulateInjury(Player injuredPlayer)
    {
        if(!validator.isValidObject(injuredPlayer))
        {
            System.out.println("Error: Entered object is NULL.");
        }
        else
        {
            injuredPlayer.setIsInjured(true);
            System.out.println(injuredPlayer.getDetails() + " is injured");

            // Get injured player's team object
            String playerTeamName = injuredPlayer.getTeamName();
            Team playerTeam = (playerTeamName.equals(teamA.getTeamName())) ? teamA : teamB;

            // Swap with reserve player if available
            if (playerTeam.getInjuredPlayerIndices().size() >= 4)
            {
                // No reserve players available. Team loses match.
                isGameLoopValid = false;
                System.out.println
                (
                    playerTeamName + " doesn't have enough players and forfeits the match."
                );
                holdingTeam = playerTeam;
                switchHoldingTeam(); // Use holdingTeam variable to keep track of winning team.
            }
            else
            {
                Player replacementPlayer  = playerTeam.swapWithReserve(injuredPlayer);
                replacementPlayer.setPosition(injuredPlayer.getPosition());
                System.out.println
                (
                    "Found a replacement player: " + replacementPlayer.getName() + 
                    " who is playing the " + replacementPlayer.getPosition() + " field position"
                );
            }
        }
    }

    /**
    * This method simulates a quarter of 80 events in the AFLGame.
    * It loops only if game loop is valid. 
    */
    public void simulateQuarter()
    {
        // Loop 80 times as there are 80 events in a quarter.
        for (int i = 0; i < 80; i++)
        {
            if (!isGameLoopValid)
            {
                break;
            }

            System.out.println("#" + (i+1) + ":");
            if (ballCarrier == null)
            {
                // Ball is in the field center
                System.out.println("Starting from the center circle");
                holdingTeam = generator.pickRandomTeam(teamA, teamB);
                ballCarrier = holdingTeam.getRandomPlayer("Midfielder");
                System.out.println(ballCarrier.getDetails() + " got the ball in the center circle");
            }
            simulateBallKick();
        }
    }

    /**
    * This method handles player reporting.
    * A message is printed to the console and the reported player data is logged.
    * Appropriate field variables of player and team of player are updated. 
    */
    public void simulateReport(Player reportedPlayer)
    {
        if (!reportedPlayer.getIsReported())
        {
            System.out.println(reportedPlayer.getDetails() + " has violated a game rule and is reported by an upmire");

            // Update field variables for Player and Team objects.
            reportedPlayer.setIsReported(true);
            String playerTeamName = reportedPlayer.getTeamName();
            Team playerTeam = (playerTeamName.equals(teamA.getTeamName())) ? teamA : teamB;
            playerTeam.recordReportedPlayer(reportedPlayer);
        };
    }

    /**
    * This method starts the game.
    * It initialises the team objects and simulates a game. 
    */
    public void startGame()
    {
        teamA = fileIO.readTeamFile("teamA.txt");
        teamB = fileIO.readTeamFile("teamB.txt");
        setStarPlayers();
        for (int i = 0; i < 4; i++)
        {
            if (!isGameLoopValid)
            {
                break;
            }
            System.out.println("\n***** Quarter " + (i+1) + " *****");
            simulateQuarter();
            ballCarrier = null;
            if (isGameLoopValid)
            {
                displayQuarterSummary();
            }
        }
        endGame();
    }

    /**
    * This method assigns the holdingTeam variable the Team object of
    * the opponent team of the current holdingTeam at the time of method call.
    */
    public void switchHoldingTeam()
    {
        holdingTeam = (holdingTeam.getTeamName().equals("Team A")) ? teamB : teamA;
    }

    /**
    * Returns a string that describes the state of the object.
    *
    * @return   the state of the AFLGame object as a String.
    */
    public String toString()
    {
        return  "Current State of AFLGame object:" + 
                "\nCurrent Ball Carrier: " + ballCarrier.getName() + 
                "\nPrevious Ball Carrier: " + previousBallCarrier.getName() + 
                "\nHolding Team: " + holdingTeam.getTeamName() + 
                "\nGame loop validity: " + isGameLoopValid + 
                "\nTeam A Object:\n" + teamA +
                "\nTeam B Object:\n" + teamB;
    }

}
