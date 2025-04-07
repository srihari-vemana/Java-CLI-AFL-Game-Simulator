/**
 * Class which validates the user input.
 * 
 * @author Srihari Vemana
 * @version ver4.0
 */
public class Validator
{
    /**
     * Default constructor which creates the object of Validator class.
     *
     */
    public Validator()
    {
        //Empty constructor because of no field variables.
    }

    /**
    * Checks if a given string is present in a given integer array.
    *
    * @param    input   Integer which is being checked for.
    * @param    group   Integer array in which the search is being made.
    *
    * @return   true if the input integer is in the given integer array.
    */
    public boolean isInIntegerArray(int input, int[] array)
    {
        boolean isInArray = false;
        for (int arrayItem : array)
        {
            if (input == arrayItem)
            {
                isInArray = true;
                return isInArray;
            }
        }
        return isInArray;
    }

    /**
    * Checks if a given string is present in a given string array.
    *
    * @param    input   String which is being checked for.
    * @param    group   String array in which the search is being made.
    *
    * @return   true if the input string is in the given string array.
    */
    public boolean isInStringArray(String input, String[] array)
    {
        boolean isInArray = false;
        for (String arrayItem : array)
        {
            if (input.equals(arrayItem))
            {
                isInArray = true;
                return isInArray;
            }
        }
        return isInArray;
    }

    /**
    * Checks if given integer is of the specified range.
    *
    * @param    input   Integer which is being evaluated.
    * @param    min     Minimum value for integer.
    * @param    max     Maximum value for integer.
    *
    * @return   true if the integer is within specified range.
    */
    public boolean isIntegerInRange(int input, int min, int max)
    {
        boolean inRange = false;
        if (input >= min && input <= max)
        {
            inRange = true;
        }
        return inRange;
    }

    /**
    * Checks if a given Player object is a valid player or not. 
    *
    * @param    player  The Player class object whole validity is being checked.
    *
    * @return   validity status as a boolean.
    */
    public boolean isValidObject(Object obj)
    {
        boolean isValidObject = false;
        if (obj != null)
        {
            isValidObject = true;
        }
        return isValidObject;
    }

    /**
    * Checks if given string is of the specified length.
    *
    * @param    input   String which is being evaluated.
    * @param    min     Minimum length for string.
    * @param    max     Maximum length for string.
    *
    * @return   true if the string is within specified length.
    */
    public boolean stringLengthRange(String input, int min, int max)
    {
        boolean inRange = false;
        if (input != null && input.trim().length() >= min  && input.trim().length() <= max)
        {
            inRange = true;
        }
        return inRange;
    }

    /**
    * Returns a string that describes the class.
    *
    * @return   the description of Validator Class.
    */
    public String toString()
    {
        return "This class is used to validate user input";
    }
}
