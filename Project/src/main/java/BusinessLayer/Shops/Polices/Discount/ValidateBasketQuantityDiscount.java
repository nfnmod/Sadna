package BusinessLayer.Shops.Polices.Discount;

import BusinessLayer.Mappers.Converter;
import BusinessLayer.Shops.Shop;
import BusinessLayer.Users.Basket;
public class ValidateBasketQuantityDiscount implements DiscountPred{
    private int ruleId;

    private int basketquantity;
    boolean cantBeMore;

    public ValidateBasketQuantityDiscount(int basketquantity,boolean cantBeMore) {
        this.basketquantity = basketquantity;
        this.ruleId = atomicRuleID.incrementAndGet();
        this.cantBeMore =cantBeMore;

    }

    public ValidateBasketQuantityDiscount(int ruleId, int basketquantity, boolean cantBeMore) {
        this.ruleId = ruleId;
        this.basketquantity = basketquantity;
        this.cantBeMore = cantBeMore;
    }

    @Override
    public boolean validateDiscount(Basket basket) {
        int currQuantity=0;
        for (int i: basket.getProducts().keySet())
        {
            currQuantity+= basket.getProducts().get(i);
        }
        if(cantBeMore)
            return currQuantity<=basketquantity;
        return currQuantity>=basketquantity;
    }

    @Override
    public int getID(){
        return this.ruleId;
    }

    public int getRuleId() {
        return ruleId;
    }

    public int getBasketquantity() {
        return basketquantity;
    }

    public boolean isCantBeMore() {
        return cantBeMore;
    }

    @Override
    public ORM.Shops.Discounts.DiscountPred toEntity(Converter c, ORM.Shops.Shop shop) {
        return c.toEntity(this, shop);
    }
}