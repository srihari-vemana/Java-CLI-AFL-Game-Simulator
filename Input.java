import java.util.Scanner;
/**
 * Class which takes input from user.
 * 
 * @author Srihari Vemana
 * @version ver1.0
 */
public class Input
{
    // Field variables
    private Scanner console;
    
    /**
     * Default constructor which creates the object of Grid class.
     *
     */
    public Input()
    {
        this.console = new Scanner(System.in);
    }

    /**
     * Accepts a String input from the user and returns it.
     *
     * @param   prompt  String to be displayed to the user to prompt an input.
     *
     * @return  a string that was taken as input from user.
     */
    public String acceptStringInput(String prompt)
    {
        System.out.print(prompt);
        String inputString = this.console.nextLine();
        return inputString.trim();
    }

    /**
    * Accessor method to get the Scanner object.
    *
    * @return   the Scanner object.
    */
    public Scanner getScanner()
    {
        return this.console;
    }

    /**
    * Mutator method to set the Scanner object for Input.
    *
    * @param    console  the Scanner object to be set.
    */
    public void setScanner(Scanner console)
    {
        this.console = console;
    }

    /**
    * Returns a string that describes the class.
    *
    * @return   the description of Input Class.
    */
    public String toString()
    {
        return "This is a custom user-defined class that takes user input";
    }

}
