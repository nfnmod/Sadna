package ServiceLayer.interfaces;

import BusinessLayer.Products.Users.BaseActions.BaseActionType;
import ServiceLayer.Objects.AdministratorInfo;
import ServiceLayer.Objects.PurchaseHistoryInfo;
import ServiceLayer.Objects.Shop;
import ServiceLayer.Objects.User;
import ServiceLayer.Response;
import ServiceLayer.Result;

import java.util.Collection;

public interface SubscribedUserService extends UserService {

    Response<SystemManagerService> manageSystemAsSystemManager();

    Response<UserService> logout();

    Response<Shop> openShop(String name, String desc);

    Result assignShopManager(int shop, String userNameToAssign);

    Result assignShopOwner(int shop, String userNameToAssign);

    Result changeManagerPermission(int shop, String userNameToAssign, Collection<BaseActionType> types);

    Result closeShop(int shop);

    Response<AdministratorInfo> getAdministratorInfo(int shop);

    Response<PurchaseHistoryInfo> getHistoryInfo(int shop);

    Result updateProductQuantity(int shopID, int productID ,int newQuantity);

    Result updateProductPrice(int shopID, int productID, double newPrice);

    Result updateProductDescription(int shopID, int productID, String Desc);

    Result updateProductName(int shopID, int productID, String newName);

    Result deleteProductFromShop(int shopID, int productID);

    Result addProductToShop(int shopID, String desc, String name,String manufacturer,int productID, int quantity, double price);

    Result reopenShop(int shopID);

    Response<User> getUserInfo();
}
