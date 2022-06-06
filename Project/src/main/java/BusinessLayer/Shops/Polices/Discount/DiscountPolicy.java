package BusinessLayer.Shops.Polices.Discount;

import BusinessLayer.Users.Basket;

public interface DiscountPolicy extends DiscountRules{

    public double calculateDiscount(Basket basket);

}
