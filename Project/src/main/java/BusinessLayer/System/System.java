package BusinessLayer.System;

import BusinessLayer.Notifications.Notification;
import BusinessLayer.Notifications.Notifier;
import BusinessLayer.Shops.PurchaseHistoryController;
import BusinessLayer.Shops.Shop;
import BusinessLayer.Users.UserController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class System {
    private final Notifier notifier;
    private ExternalServicesSystem externSystem;
    private PurchaseHistoryController purchaseHistoryServices;
    private ConcurrentHashMap<Integer, Shop> shops;

    public System() {
        notifier = new Notifier();;
    }

    static private class SystemHolder{
        static final System s = new System();
    }

    public static System getInstance()
    {
        return SystemHolder.s;
    }

    public void initialize(){
        externSystem = new ExternalServicesSystem();
        shops = new ConcurrentHashMap<>();
        purchaseHistoryServices= PurchaseHistoryController.getInstance();
        UserController.getInstance().createSystemManager("Admin","ILoveIttaiNeria");
    }

    public ConcurrentHashMap<Integer,Boolean> pay(ConcurrentHashMap<Integer,Double> totalPrices, PaymentMethod method){
        ConcurrentHashMap<Integer,Boolean> paymentSituation= new ConcurrentHashMap<>();
        for(int shopId: totalPrices.keySet())
        {
            if(totalPrices.get(shopId)>0 && method != null) {
                paymentSituation.put(shopId, getExternSystem().pay(totalPrices.get(shopId), method));
            }
            else
            {
                paymentSituation.put(shopId,false);
            }
        }
        return paymentSituation;
    }

    public ConcurrentHashMap<Integer, Boolean> checkSupply(ConcurrentHashMap<Integer,PackageInfo> packages){
        ConcurrentHashMap<Integer, Boolean> supplySituation= new ConcurrentHashMap<>();
        for(Integer idx : packages.keySet())
        {
            supplySituation.put(idx, getExternSystem().checkSupply(packages.get(idx)));
        }
        return supplySituation;
    }

    public PurchaseHistoryController getPurchaseHistoryServices() {
        return purchaseHistoryServices;
    }

    public ExternalServicesSystem getExternSystem() {
        return externSystem;
    }

    public synchronized void addPayment(Payment p){
        externSystem.addPayment(p);
    }

    public synchronized void addSupply(Supply s){
        externSystem.addSupply(s);
    }

    public int getPaymentSize(){//for tests only
        return externSystem.getPaymentSize();
    }

    public int getSupplySize(){//for tests only
        return externSystem.getSupplySize();
    }

    public Notifier getNotifier(){
        return notifier;
    }


}
