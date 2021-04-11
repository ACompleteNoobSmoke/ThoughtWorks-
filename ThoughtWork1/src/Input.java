import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    //Scanner for user inputs.
    private static Scanner scan = new Scanner(System.in);

    //User set int for inputs.
    public static int setInt(){
        int newInt = 0;
        try{
            newInt = scan.nextInt();
            scan.nextLine();
        }catch (InputMismatchException e){
            scan.nextLine();
        }
        return newInt;
    }

    //Closes the scanner.
    public static void closeScanner(){
        scan.close();
    }
}
