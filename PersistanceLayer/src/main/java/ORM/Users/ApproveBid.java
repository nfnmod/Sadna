//package ORM.Users;
//
//import ORM.Shops.Product;
//import ORM.Shops.Shop;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Entity
//@Table(name = "ApproveBid")
//@IdClass(ApproveBid.ApproveBidPK.class)
//public class ApproveBid {
//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Shop shop;
//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Product product;
//    private int quantity;
//    private double price;
////    @OneToMany
////    private Collection<BidApprovalStatus> administraitorsApproval;
//
//    public Shop getShop() {
//        return shop;
//    }
//
//    public void setShop(Shop shop) {
//        this.shop = shop;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public Collection<BidApprovalStatus> getAdministraitorsApproval() {
//        return administraitorsApproval;
//    }
//
//    public void setAdministraitorsApproval(Collection<BidApprovalStatus> administraitorsApproval) {
//        this.administraitorsApproval = administraitorsApproval;
//    }
//
//    public static class ApproveBidPK implements Serializable{
//        private Shop shop;
//        private Product product;
//
//        public ApproveBidPK(Shop shop, Product product) {
//            this.shop = shop;
//            this.product = product;
//        }
//
//        public ApproveBidPK() {
//        }
//    }
//}
