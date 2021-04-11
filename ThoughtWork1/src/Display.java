import java.io.File;
import java.util.ArrayList;

public class Display {

    //Display file names in the directory.
    public void displayOrderList(String[] contents){
        if(contents != null || contents.length > 0) {
            System.out.println("Order List:");
            for (int i = 0; i < contents.length; i++) {
                System.out.println(i + 1 + ". " + contents[i]);
            }
        }else{
            System.out.println("Currently No Orders In Folder");
        }
    }

    //Option display for users to decide if they want to continue the program or not.
    public void continueProgramDisplay(){
        System.out.println("Continue: ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("\nAction: ");
    }


    //Prints original list
    public void printOriginalList(ArrayList<String> printThis){
        printThis.forEach(System.out::println);
    }

    //Does the calculations and prints the result list.
    public void printReceiptList(ArrayList<String> printTotal){
        Method method = new Method();
        double[] amount = method.resultArray(printTotal);
        double totalAmount = method.totalAmount(amount);
        double[]taxAmounts = method.totalTaxes(printTotal);
        double totalTax = method.totalAmount(taxAmounts);
        totalTax = Math.round(totalTax * 100.0) / 100.0;
        printTotal = method.resultString(printTotal, amount);
        printTotal.forEach(System.out::println);
        System.out.println("Sales Tax: " + totalTax);
        System.out.println("Total: " + totalAmount);

    }


}

