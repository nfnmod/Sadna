package BusinessLayer.Shops.Polices.Discount;

import BusinessLayer.Products.Product;
import BusinessLayer.Shops.Shop;
import BusinessLayer.Users.Basket;
import BusinessLayer.Users.SubscribedUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductQuantityInPriceDiscountTest {

    private ProductQuantityInPriceDiscount productQuantityInPriceDiscount;

    private Basket basket;


    @Before
    public void setUp() throws Exception {

        basket = new Basket(1);

    }

    @Test
    public void calculateDiscountOneProductDiscount() {
        basket.saveProducts(1,11,5);
        basket.saveProducts(2,100,15);
        productQuantityInPriceDiscount= new ProductQuantityInPriceDiscount(1,5,20);
        Assert.assertEquals(11*5-(20*2+ 5),productQuantityInPriceDiscount.calculateDiscount(basket),0.1);
    }

    @Test
    public void calculateDiscountDifferentProductDiscount() {
        basket.saveProducts(2,100,15);
        productQuantityInPriceDiscount= new ProductQuantityInPriceDiscount(1,5,20);
        Assert.assertEquals(0,productQuantityInPriceDiscount.calculateDiscount(basket),0.1);
    }

}