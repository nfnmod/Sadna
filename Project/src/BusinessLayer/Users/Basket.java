package BusinessLayer.Users;

import java.util.Collection;
import java.util.HashMap;

public class Basket {
    private int shopid;
    //the key is the product id in the specific store
    //the value will be the quantity of the product
    private HashMap<Integer , Integer> products = new HashMap<>();

    //secret
    public boolean saveProducts(int productid, int quantity) {
        if(!products.containsKey(productid))
        {
            products.put(productid,quantity);
            return true;
        }
        return false;
    }


    public boolean removeProduct(int productid) {
        if (products.containsKey(productid)) {
            products.remove(productid);
            return true;
        }
        return false;
    }

    public boolean editProductQuantity(int productid, int newquantity) {
        if (products.containsKey(productid)) {
            products.put(productid, newquantity);
            return true;
        }
        return false;
    }
    public HashMap<Integer, Integer> getProducts() {
        return products;
    }


}
