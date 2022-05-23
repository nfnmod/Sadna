package com.example.application.views.main;

import BusinessLayer.Products.ProductFilters;
import BusinessLayer.Shops.ShopFilters;
import ServiceLayer.Objects.*;
import ServiceLayer.UserServiceImp;
import com.helger.commons.annotation.Nonempty;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.DomEvent;
import com.vaadin.flow.dom.DomEventListener;
import com.vaadin.flow.router.Route;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Route("Guest")
public class GuestActionView extends AppLayout {
    Tabs tabs;
    UserServiceImp user;

    public GuestActionView() {
        user = new UserServiceImp();
        user.loginSystem();
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Guest Menu");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        tabs = getTabs();
        addToDrawer(tabs);
        addToNavbar(toggle, title);
    }

    private Tabs getTabs() {
        tabs = new Tabs();
        addTabWithClickEvent("Login", event -> UI.getCurrent().navigate(LoginView.class));
        /*addTabWithClickEvent("Save Product", event -> {});*/
        addTabWithClickEvent("Check Cart", this::checkCartEvent);
        addTabWithClickEvent("Search Products", this::searchProductsEvent);
        addTabWithClickEvent("Buy Cart", this::buyCartEvent);
        //addTab("Info on Shops and Products");
        addTabWithClickEvent("Exit", event -> {
            if(user.logoutSystem().isOk())
                UI.getCurrent().navigate(MainView.class);
        });
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private void buyCartEvent(DomEvent event) {
        PaymentForm layout = new PaymentForm();
        setContent(layout);
    }

    private void searchProductsEvent(DomEvent event) {
        HorizontalLayout layout = new HorizontalLayout();
        ShopFilters shopFilter = shop -> true;
        ProductFilters productFilter = product -> true;

        ShopsInfo shop = user.searchProducts(shopFilter, productFilter).getElement();
        if (shop != null) {
            Grid<Shop> grid = createShopGrid();
            Grid<ProductInfo> prodGrid = createProductInfoGrid();
            Collection<Shop> shops = shop.shops();
            grid.addItemClickListener(item -> {
                Shop curr = shops.stream()
                        .filter(prod -> item.getItem().equals(prod))
                        .findAny()
                        .orElse(null);
                if(curr != null){
                    Collection<ServiceLayer.Objects.Product> products = curr.shopProducts();
                    Stream<ProductInfo> newProducts = products.stream().map(product -> new ProductInfo(product.shopId(), product.productID(), product.quantity(), product.price()));
                    prodGrid.setItems(newProducts.toList());
                    layout.replace(layout.getComponentAt(1), prodGrid);
                }
            });
            grid.setItems(shops);
            layout.add(grid);
            layout.add(prodGrid);
        } else {
            Label label = new Label("No Cart");
            layout.add(label);
        }
        setContent(layout);
    }

    private void checkCartEvent(DomEvent event) {
        HorizontalLayout layout = new HorizontalLayout();
        Cart cart = user.showCart().getElement();
        if (cart != null) {
            Collection<Basket> basket = cart.baskets();
            Grid<Basket> grid = new Grid<>(Basket.class, false);
            grid.addColumn(Basket::shopId).setHeader("Shops");
            Grid<ProductInfo> prodGrid = createProductInfoGrid();
            grid.addItemClickListener(item -> {
                Basket curr = basket.stream()
                    .filter(prod -> item.getItem().equals(prod))
                    .findAny()
                    .orElse(null);
                if (curr != null) {
                    Collection<ProductInfo> products = curr.productsID();
                    prodGrid.setItems(products);
                    layout.replace(layout.getComponentAt(1), prodGrid);
                }
            });
            grid.setItems(basket);
            layout.add(grid);
            layout.add(prodGrid);
        } else {
            Label label = new Label("No Cart");
            layout.add(label);
        }
        setContent(layout);
    }

    private Grid<ProductInfo> createProductInfoGrid(){
        Grid<ProductInfo> prodGrid = new Grid<>(ProductInfo.class, false);
        prodGrid.addColumn(ProductInfo::Id).setHeader("Product ID");
        prodGrid.addColumn(ProductInfo::price).setHeader("Price");
        prodGrid.addColumn(ProductInfo::quantity).setHeader("Quantity");
        prodGrid.addColumn(ProductInfo::shopId).setHeader("Shop ID");
        return prodGrid;
    }

    private Grid<Shop> createShopGrid(){
        Grid<Shop> grid = new Grid<>(Shop.class, false);
        grid.addColumn(Shop::shopName).setHeader("Shop Name");
        return grid;
    }

    private void addTabWithClickEvent(String name, DomEventListener listener) {
        Tab tab = new Tab(name);
        tab.getElement().addEventListener("click", listener);
        tabs.add(tab);
    }
}