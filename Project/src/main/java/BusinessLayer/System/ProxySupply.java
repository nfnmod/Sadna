package BusinessLayer.System;

import BusinessLayer.Products.ProductInfo;
import BusinessLayer.Users.User;

public class ProxySupply implements Supply {
    private Supply s = null;
    @Override
    public boolean checkSupply(ProductInfo product) {
        if(s != null)
            return s.checkSupply(product);
        return product.getProductquantity()>0;
    }
}
