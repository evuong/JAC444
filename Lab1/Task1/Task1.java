import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        System.out.print("Enter a credit card number as a long integer: \n  ");

        //validates input to see that it was a long int entered
        Scanner input = new Scanner(System.in);
        while (!input.hasNextLong()) {
            input.next();
            System.out.print("Please enter a long integer: ");
        }

        //accepts input after validation
        long cc = input.nextLong();

        //string to hold whether or not cc number is valid
        String valid = isValid(cc) ? "valid" : "invalid";

        //printing out result
        System.out.println(cc + " is " + valid);
    }

    /** Return true if the card number is valid */
    public static boolean isValid (long number){

        //If the result from Step 4 is divisible by 10, the card number is valid;
        int result = (sumOfDoubleEvenPlace(number) + sumOfOddPlace(number))%10;

        //if it is divisible by 10 AND prefix is a correct value then card number is valid
        if (result == 0 && prefixMatched(number, getPrefix(number)))
            return true;
        else return false;
    }

    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number){

        //convert to string to get value at each position easier
        String num = Long.toString(number);
        int sum = 0;

        for (int i = getSize(number); i > 0; i-=2) {

            //since number is now a string of char, need to use getNumericValue to convert back to an int
            int val = 2*(Character.getNumericValue(num.charAt(i-2)));

            //if value is 2 digits, need to add those two digits together using function getDigit
            if (val > 9)
                sum += getDigit(val);
            else sum += val;

        }
        return sum;
    }

    /** Return this number if it is a single digit, otherwise,
     * return the sum of the two digits */
    public static int getDigit(int number){

        //number%10 to get second digit, number/10 to get first digit
        return number%10 + number/10;
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number){
        String num = Long.toString(number);
        int sum = 0;

        for (int i = getSize(number); i > 0; i-=2) {
            sum += Character.getNumericValue(num.charAt(i-1));
        }
        return sum;
    }

    /** Return true if the digit d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        String num = Long.toString(number);
        String prefix = Integer.toString(d);

        //first check to see if prefix is a valid number
        if (d == 4 || d == 5 || d == 37 || d == 6) {

            //check length of prefix
            if (prefix.length() == 1) {
                //checks first char (position 0) if it matches d
                if (Character.getNumericValue(num.charAt(0)) == d) {
                    return true;
                }
            }
            else if (prefix.length() == 2) {
                if (Integer.parseInt(num.substring(0,2)) == d) {
                    return true;
                }
            }
        }
        //if anything false return false
        else return false;



        //if one digit prefix
        if (prefix.length() == 1) {
            //checks first char (position 0) if it matches d
            if (Character.getNumericValue(num.charAt(0)) == d) {
                return true;
            }
            else return false;
        }
        //if any other prefix digit count
        else {
            //convert substring back to int to have more accurate comparison
            if (Integer.parseInt(num.substring(0,prefix.length())) == d) {
                return true;
            }
            else return false;
        }
    }

    /** Return the number of digits in d */
    public static int getSize(long d){
        String num = Long.toString(d);
        return num.length();
    }


    //dont get what this means, not needed
    //adjusted
    /** Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number. */
    public static int getPrefix(long number) {
        String num = Long.toString(number);
        return Character.getNumericValue(num.charAt(0));
    }
}
