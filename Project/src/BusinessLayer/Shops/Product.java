package BusinessLayer.Shops;

public interface  Product {
    int getID();
    String getName();
    double getPrice();
    int getQuantity();
    void setName(String name);
    void setPrice(double price);
    void setQuantity(int quantity);

}
