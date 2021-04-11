import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Order {

    //ArrayList to get the item from the order txt file.
     private ArrayList<String> eachItem;

     //Not only initialize the class but the ArrayList.
    public Order(){
        eachItem = new ArrayList<>();
    }

    //Gives other users access to the item ArrayList.
    public ArrayList<String> getEachItemList(){
        return eachItem;
    }

    //Allows user to pick the file that contains the items in the Order folder.
     public String pickOrder() {
         File directoryPath = new File("Orders");
         String contents[] = directoryPath.list();
         Display display = new Display();
         String fileName = null;
        int size = contents.length;
        if(contents != null || size != 0){
            int pick = 0;
            while(pick < 1 || pick > size){
                display.displayOrderList(contents);
                System.out.print("\nAction: ");
                pick = Input.setInt();
                System.out.println();
            }
            fileName = contents[pick - 1];

        }
        return fileName;
     }

     //Reads the specific file and gets all the items that it contains.
    public void getOrder(String orderText){
        try {
            Scanner scanner = new Scanner(new File("Orders/" + orderText));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                eachItem.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Change the item to be ready for calculating and receipt printin later.
    public ArrayList<String> formatItems(ArrayList<String> orderItems){
        ArrayList<String> newOrderFormat = new ArrayList<>();
        for (String listingItem : orderItems){
            listingItem = (listingItem.contains("Input")) ? listingItem.replace("Input", "Output"): listingItem;
            listingItem = (listingItem.contains(" at ")) ? listingItem.replace(" at ", ":"): listingItem;
            newOrderFormat.add(listingItem);
        }
        return newOrderFormat;
    }

    //Clears the ArrayList passed
    public void clearList(ArrayList<String>... clearThis){
        for(ArrayList<String> clearing: clearThis){
            clearing.clear();
        }
    }
}
