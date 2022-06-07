package BusinessLayer.Shops.Polices.Discount;

import BusinessLayer.Users.Basket;

import java.util.Collection;

public class DiscountXorPolicy implements NumericDiscountRules{

    private DiscountRules discountRules1;
    private DiscountRules discountRules2;
    //if true discountRules1 else discountRules2;
    private DiscountPred tiebreaker;

    @Override
    public double calculateDiscount(Basket basket){
        double dr1=discountRules1.calculateDiscount(basket);
        double dr2=discountRules2.calculateDiscount(basket);
        if(dr1>0)
        {
            if (dr2>0)
            {
                if(tiebreaker.validateDiscount(basket))
                    return dr1;
                else
                    return dr2;
            }
            else
                return dr1;
        }
        if (dr2>0)
            return dr2;

        return 0;
    }
}
