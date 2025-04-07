import java.io.*; //FileReader, FileWriter, Exceptions
import java.util.ArrayList;
import java.util.Scanner;
/**
* Class which handles all Reading and Writing tasks related to files.
*
* @author Srihari Vemana
* @version ver1.0
*/
public class FileIO
{
    String fileName;
    /**
     * Default constructor which creates the object of the FileIO class.
     *
     */
    public FileIO()
    {
        this.fileName = "No File";
    }

    /**
     * Parameterised constructor for the FileIO class.
     *
     * @param   fileName    Accepts the file name as a String.
     */
    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    /**
    * Accessor method to get the file name.
    *
    * @return   the file name as a String.
    */
    public String getFileName()
    {
        return fileName;
    }

    /**
    * This method reads the given team text file and creates the appropriate
    * team and player class object.
    *
    * @param    fileName    Accepts the file name as a String.
    *
    * @return the Team calss object of the created team.
    */
    public Team readTeamFile(String fileName)
    {
        ArrayList<Player> players = new ArrayList<>();
        FileReader inputFile = null;
        Team team = null;
        if (fileName != null && fileName.trim().length() > 0)
        {
            try
            {
                inputFile = new FileReader(fileName);
                Scanner parser = new Scanner(inputFile);
                String teamName = parser.nextLine().trim();
                while (parser.hasNextLine())
                {
                    String[] arrPlayers = parser.nextLine().split(",");

                    // Create Player subclasses based on player position.
                    Player player = null;
                    if (arrPlayers[1].equals("Forward"))
                    {
                        player = new Forward
                                        (
                                        arrPlayers[0], 
                                        Integer.valueOf(arrPlayers[2]),
                                        teamName
                                        );
                    }
                    else if (arrPlayers[1].equals("Midfielder"))
                    {
                        player = new Midfielder
                                        (
                                        arrPlayers[0], 
                                        Integer.valueOf(arrPlayers[2]),
                                        teamName
                                        );
                    }
                    else if (arrPlayers[1].equals("Defender"))
                    {
                        player = new Defender
                                        (
                                        arrPlayers[0], 
                                        Integer.valueOf(arrPlayers[2]),
                                        teamName
                                        );
                    }
                    else
                    {
                        // Reserve players are instances of super class Player
                        player = new Player
                                        (
                                        arrPlayers[0], 
                                        arrPlayers[1],
                                        Integer.valueOf(arrPlayers[2]),
                                        teamName
                                        );
                        player.setIsReserve(true);
                    }
                    
                    // Add created player object tp player list.
                    players.add(player);
                }
                parser.close();

                //Create the Team class object. 
                team = new Team(teamName, players);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            try
            {
                inputFile.close();
            }
            catch (IOException e)
            {
                System.out.println("Error: file couldn't be closed.");
            }
        }
        else
        {
            System.out.println("File name not entered.");
        }
        return team;
    }

    /**
     * Mutator method to set the file name.
     *
     * @param   newFileName    The new file name as a String.
     */
    public void setFileName(String newFileName)
    {
        this.fileName = newFileName;
    }

    /**
    * Returns a string that describes the class.
    *
    * @return   the description of FileIO Class.
    */
    public String toString()
    {
        return "FileIO Object to read and write the file: " + this.fileName;
    }

    /**
    * This method takes a team object and writes a text file with the 
    * team and player details.
    *
    * @param    team    Team object of the team whose text file is to be written.
    */
    public void writeTeamFile(Team team)
    {
        String teamName = team.getTeamName();
        String fileName = "team" + teamName.split(" ")[1] + "Updated.txt";
        try
        {
            PrintWriter outputFile = new PrintWriter(fileName);
            outputFile.println(teamName);
            for (Player player : team.getPlayers())
            {
                outputFile.println(player.getName() + "," + player.getPosition() + "," + player.getSeasonGoals());
            }

            try
            {
                outputFile.close();
            }
            catch (Exception e)
            {
                System.out.println("Error: file couldn't be closed.");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
