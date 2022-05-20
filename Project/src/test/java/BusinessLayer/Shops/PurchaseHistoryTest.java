package BusinessLayer.Shops;

import BusinessLayer.Products.Product;
import BusinessLayer.Products.Users.Guest;
import BusinessLayer.Products.Users.SubscribedUser;
import BusinessLayer.Products.Users.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurchaseHistoryTest {

    private final SubscribedUser founder = createFounder();
    private Shop s1;
    private Product p1;
    private Product p2;
    private PurchaseHistory purchaseHistory;
    private User u1;


    @Before
    public void setUp() {
        s1 = createShop();
        p1 = new Product(1, "a", 5, 100);
        p2 =new Product(2, "b", 10, 100);
        createShopWithTwoProducts();
        u1 = createUserWithItemInBasket(s1.getId(),p1.getID(),10,p1.getPrice());
        purchaseHistory = new PurchaseHistory(s1,u1.getName());
        s1.addBasket(u1.getName(), u1.getBasket(s1.getId()));
        s1.purchaseBasket(u1.getName());
    }

    @Test
    public void makePurchaseTest() {
        purchaseHistory.makePurchase();
        assertEquals(1, purchaseHistory.getPast_purchases().size());
    }
    private Shop createShop() {
        return new Shop(100, "shop","testing shop", founder);
    }


    private Shop createShopWithTwoProducts() throws IllegalStateException {
        s1.addProduct(p1);
        s1.addProduct(p2);
        return s1;
    }

    private SubscribedUser createFounder() {
        return new SubscribedUser("Founder Guy","Guy123456");
    }

    private User createUserWithItemInBasket(int shopid, int productid, int quantity,double price)
    {
        User u = new Guest("Yuval");
        u.saveProducts(shopid, productid, quantity,price);
        return u;
    }

}