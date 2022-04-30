package BusinessLayer.Users;

import BusinessLayer.Shops.Shop;
import BusinessLayer.Users.BaseActions.BaseActionType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.NoPermissionException;

import static org.junit.jupiter.api.Assertions.*;

class SubscribedUserTest {
    private SubscribedUser user1;
    private SubscribedUser user2;
    private SubscribedUser toAssign;
    private Shop shop1;
    private Shop shop2;
    ShopManager sm1;
    @BeforeEach
    void setUp() {
        user1 =new SubscribedUser("user1");
        user2 = new SubscribedUser("user2");
        toAssign=new SubscribedUser("toAssign");
        shop1=new Shop(1,"shop1");
        shop2=new Shop(2,"shop2");

        sm1 = new ShopManager(shop1,user1);
        sm1.AddAction(BaseActionType.ASSIGN_SHOP_MANAGER);
    }

    @Test
    void addAdministrator() {
        assertNull(user1.getAdministrator(shop1.getId()));
        user1.addAdministrator(shop1.getId(),sm1);
        assertEquals(user1.getAdministrator(shop1.getId()),sm1);
        user1.addAdministrator(shop1.getId(),new ShopAdministrator(shop2,user1));
        assertNull(user1.getAdministrator(shop2.getId()));
        assertEquals(user1.getAdministrator(shop1.getId()),sm1);
    }

    @Test
    void assignShopManager() {
        user1.addAdministrator(shop1.getId(),sm1);
        try {
            user2.assignShopManager(shop2.getId(),toAssign);
            fail("do the transaction with out a premmission");
        } catch (NoPermissionException ignore) {
            assertNull(toAssign.getAdministrator(shop2.getId()));
        }

        try{
            user1.assignShopManager(shop1.getId(),toAssign);
            assertNull(toAssign.getAdministrator(shop1.getId()));
        } catch (NoPermissionException e) {
            fail("supposed to succeed but got exception :"+e.getMessage());
        }

        try {
            toAssign.assignShopManager(shop1.getId(),toAssign);
            fail("do the transaction with out a premmission");
        } catch (NoPermissionException ignore) {
            assertNull(user2.getAdministrator(shop1.getId()));
        }
    }
}