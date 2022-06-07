package BusinessLayer.Shops.Polices.Discount;

import BusinessLayer.Users.Basket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DiscountPlusPolicy implements NumericDiscountRules{
    private int connectId;
    Collection<DiscountRules> discountPolicies;

    public DiscountPlusPolicy(Collection<DiscountRules> discountPolicies) {
        this.discountPolicies = new ArrayList<>();
        this.discountPolicies.addAll(discountPolicies);
        this.connectId = atomicconnectId.incrementAndGet();
    }

    public DiscountPlusPolicy(DiscountRules discountPolicy) {
        this.discountPolicies = new ArrayList<>();
        this.discountPolicies.add(discountPolicy);
        this.connectId = atomicconnectId.incrementAndGet();
    }

    @Override
    public double calculateDiscount(Basket basket) {
        Iterator<DiscountRules> i = discountPolicies.iterator();
        double price =i.next().calculateDiscount(basket);
        while (i.hasNext())
        {
            price += i.next().calculateDiscount(basket);
        }
        return price;
    }

    @Override
    public void add(DiscountRules discountRules) {
        discountPolicies.add(discountRules);
    }

    @Override
    public boolean remove(DiscountRules discountRules) {
        return discountPolicies.remove(discountRules);
    }
}
