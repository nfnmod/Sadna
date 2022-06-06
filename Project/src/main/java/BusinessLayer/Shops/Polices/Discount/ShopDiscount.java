package BusinessLayer.Shops.Polices.Discount;

import BusinessLayer.Shops.Shop;
import BusinessLayer.Users.Basket;

public class ShopDiscount implements DiscountPolicy {
    int basketQuantity;
    double discount;

    public ShopDiscount( int basketQuantity,double discount)
    {
        this.basketQuantity= basketQuantity;
        this.discount = discount;
    }

    @Override
    public double calculateDiscount(Basket basket)
    {
        int currentQuantity=0;
        double currentPrice=0;
        for (int productId:basket.getProducts().keySet()) {
            currentQuantity+= basket.getProducts().get(productId);
            currentPrice+= basket.getPrices().get(productId)*basket.getProducts().get(productId);
        }
        if(currentQuantity>=basketQuantity)
            return discount*currentPrice;

        return 0;

    }



}
