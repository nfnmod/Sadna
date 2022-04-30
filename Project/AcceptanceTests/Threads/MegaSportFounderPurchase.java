package Threads;

import Bridge.SubscribedUserBridge;
import Mocks.Guest;
import Mocks.RegistrationInfo;
import Mocks.User;
import Tests.SubscribedUserTests;
import Tests.UserTests;

public class MegaSportFounderPurchase extends Thread{
    public void run(){
        SubscribedUserBridge bridge = SubscribedUserTests.getUserBridge();
        int castroID = UserTests.shops[UserTests.castro_ID].ID;
        Guest g = bridge.visit();
        User MegaSportFounder = bridge.login(g.ID,new RegistrationInfo("castroFounder","castro_rocks"));

        bridge.addProductToCart(MegaSportFounder.ID,castroID,2,10);
    }
}
