import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class UniversalBallotCounterv2 {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        final int SENTINEL = -1;
        int idNumber = 1;

        // initialize new hashmap
        HashMap<Integer, Integer> votes = new HashMap();

        int value = 0;
        for (int i = 0; i <= 2000548; i++) {
            votes.put(i, value);
        }

        while (idNumber > SENTINEL) {
            System.out.print("Enter candidate's ID number (1-2548, any negative number to exit): ");
            // Allow user to enter the ID number of the selected candidate on each ballot

            String strIdNumber = scnr.nextLine(); // read as String

            try {
                idNumber = Integer.parseInt(strIdNumber);

                if (idNumber <= SENTINEL) {
                    break;
                }

                if (votes.containsKey(idNumber)) {
                    Integer val = votes.get(idNumber);
                    val++;
                    votes.put(idNumber, val);
                }
                else {
                    // Include input validation to ensure that the user can't type in an ID number above 2548
                    System.out.println("Invalid number! Try again.");
                }

            }
            catch (NumberFormatException e) {
                // handle exception error
                System.out.println("Your input is not a number! Try again.");
            }
        }

        int max = 0;
        // upon existing, displays a list of the candidates ID numbers and their number of votes received but only if the candidate received at least one vote
        System.out.println("Vote Counts: ");
        System.out.println("------------");

        //         Display the winner of the election (candidate with the most votes)
        for (int i = 0; i < votes.size(); i++) {
            if (votes.get(i) > 0) {
                System.out.println("Candidate " + Integer.toString(i) + ": " + votes.get(i) + " vote(s)");
            }

            if (votes.get(i) > max)
            {
                max = votes.get(i);
            }
        }


        // If there is a tie, display all candidates with the highest number of votes
        System.out.println("\nElection Winner(s): ");
        System.out.println("-------------------");

        for (int i = 0; i < votes.size(); i++) {
            if (votes.get(i) == max) {
                System.out.println("Candidate " + Integer.toString(i));
            }
        }
    }
}
