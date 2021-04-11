import java.util.ArrayList;

public class Program {

    //Decision to continue the program or not
    public boolean decision(Display display){
        int i = 0;
        while(i < 1 || i > 2){
            display.continueProgramDisplay();
            i = Input.setInt();
        }
        return (i == 1) ? true: false;
    }

    //Gets ArrayList of the orders in the text file
    public ArrayList<String> gettingOrders(Order orders){
        String fileName = orders.pickOrder();
        orders.getOrder(fileName);
        return orders.getEachItemList();
    }

    //Prints both the original order list and the result list
    public void printingReceipts(Display display, ArrayList<String>... printThese){
        display.printOriginalList(printThese[0]);
        System.out.println();
        display.printReceiptList(printThese[1]);
        System.out.println();
    }

    public static void main(String[] args){
        Order orders = new Order();
        Display display = new Display();
        Program program= new Program();
        boolean loop = true;
        while(loop) {
            ArrayList<String> originalList = program.gettingOrders(orders);
            ArrayList<String> resultList = orders.formatItems(originalList);
            program.printingReceipts(display, originalList, resultList);
            orders.clearList(originalList, resultList);
            loop = program.decision(display);
        }
        Input.closeScanner();

    }
}
