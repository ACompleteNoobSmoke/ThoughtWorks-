public class TaxOptions {

    public static boolean isNoTax(String item){
        if(item.contains("book") || item.contains("chocolate") || item.contains("pill")){
            return true;
        }
        return false;
    }

    public static boolean isBasic(String item){
        if( !isNoTax(item) && !isImported(item)){
            return true;
        }
        return false;
    }

    public static boolean isImported(String item){
        if(item.contains("imported")){
            return true;
        }
        return false;
    }

    public static boolean isBoth(String item){
        if( !isNoTax(item) && isImported(item)){
            return true;
        }
        return false;
    }
}
