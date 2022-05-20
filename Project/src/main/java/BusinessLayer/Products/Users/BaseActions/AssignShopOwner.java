package BusinessLayer.Products.Users.BaseActions;

import BusinessLayer.Products.Users.ShopOwner;
import BusinessLayer.Products.Users.SubscribedUser;
import BusinessLayer.Shops.Shop;
import BusinessLayer.Products.Users.ShopAdministrator;

public class AssignShopOwner extends BaseAction {
    private Shop s;
    private SubscribedUser u;

    public AssignShopOwner(Shop s, SubscribedUser u) {
        this.s = s;
        this.u = u;
    }

    public boolean act(SubscribedUser userToAssign, String appointer){
        ShopOwner o = new ShopOwner(s, u,appointer, false);
        if(userToAssign.getAdministrator(s.getId())== null && s.addAdministrator(userToAssign.getUserName(), o)){
            ShopAdministrator admin = userToAssign.addAdministrator(s.getId(),o);
            u.getAdministrator(s.getId()).addAppoint(admin);
            return true;
        }
        return false;
    }
}
