import java.util.ArrayList;

public class Method{

    //Splits the string at the ":" element
    private String[] splitter(String splitThis){
        String[] splice = null;
        if(!splitThis.contains("Input")){
            splice = splitThis.split(":");
        }
        return splice;
    }

    //Parses the price from the String passed into the method.
    private double getPrice(String item) {
        double price = Double.parseDouble(splitter(item)[1]);
        return price;
    }

    //Calculates the prices from the string.
    private double calculate(String item){
        double price = getPrice(item);
        Taxes includeTax = taxLambda(item);
        if(includeTax != null){
            price = includeTax.addTax(price) + price;
            double rounded = Math.round(price * 100.0) / 100.0;
            price = rounded;
        }
        return price;
    }

    //Contains an array of the results of the calculations.
    public double[] resultArray(ArrayList<String> items){
        int size = items.size()-1;
        double prices[] = new double[size];
        for(int i = 0; i < size; i++){
            prices[i] = calculate(items.get(i+1));
        }
        return prices;
    }

    //Replaces the result string with updatedAmount
    public ArrayList<String> resultString(ArrayList<String> items, double[] results){
        ArrayList<String> updatedList = new ArrayList<>();
        updatedList.add(items.get(0));
        int i = 0;
        for(String item: items){
            if(item.contains("Output")){
                continue;
            }
            String splitted = splitter(item)[0];
            splitted = splitted + ": " + results[i++];
            updatedList.add(splitted);
        }
        return updatedList;
    }

    //Add all the amounts together;
    public double totalAmount(double[] amount){
        double totalAmount = 0.0;
        for(double money: amount){
            totalAmount += money;
        }
        return totalAmount;
    }

    //Gets all the taxes from the calculations
    public double[] totalTaxes(ArrayList<String> items){
        int size = items.size();
        double[] taxes = new double[size];
        double price = 0;
        taxes[0] = 0;
        for(int i = 1; i < size; i++){
            Taxes taxEquation = taxLambda(items.get(i));
            if(taxEquation == null){
                taxes[i] = 0;
                continue;
            }
            price = getPrice(items.get(i));
            taxes[i] = taxEquation.addTax(price);
        }
        return taxes;
    }

    //Lambda for taxes that needs to be added.
    private Taxes taxLambda(String item){
        Taxes addTax = null;
        if(TaxOptions.isNoTax(item) && !TaxOptions.isImported(item)){
            return null;
        } else if (!TaxOptions.isBasic(item)) {
            Double tax = (TaxOptions.isBoth(item)) ? Taxes.BOTH_TAX : Taxes.IMPORT_TAX;
            addTax = (x) -> x * tax;
        } else if (TaxOptions.isBasic(item)) {
            addTax = (x) -> x * Taxes.BASIC_TAX;
        }
        return addTax;
    }
}
