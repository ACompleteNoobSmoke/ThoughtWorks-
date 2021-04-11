public interface Taxes {
    double BASIC_TAX = 0.10;
    double IMPORT_TAX = 0.05;
    double BOTH_TAX = 0.15;

    double addTax(double price);
}
