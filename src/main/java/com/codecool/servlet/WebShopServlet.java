package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "webShopServlet", urlPatterns = {"/webShop"}, loadOnStartup = 1)
public class WebShopServlet extends HttpServlet{
    private Stock shopStock;
    private Cart cart;

    public WebShopServlet() {
        this.shopStock = new Stock();
        this.cart = new Cart();
    }

    public void initStock(){
        shopStock.addItemInStock(new Item("Nivea Creme", 10.00));
        shopStock.addItemInStock(new Item("Name of the rose", 45.00));
        shopStock.addItemInStock(new Item("Phone SAMSUNG Galaxy", 700.00));
        shopStock.addItemInStock(new Item("Night Dress", 899.00));
        shopStock.addItemInStock(new Item("Gaming Headset", 170.00));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        initStock();
        StringBuffer buffer = new StringBuffer();
        for (Item item : this.shopStock.getStockList()) {
            buffer.append("<tr>");
            buffer.append("<td>" + item.getName() + "</td>");
            buffer.append("<td>" + item.getPrice() + "</td>");
            buffer.append("<td><a href= \"/?addItemInCart=" +item.getName() +"\"><button>Add</button></a></td>");
            buffer.append("<td><a href=\"/?removeItemFromCart=" + item.getName()+"\"><button>Remove</button></a></td>");
            buffer.append("</tr>");
        }

        PrintWriter out = response.getWriter();
        String title = "Initial Stock";

        out.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<table>" +
                        "<thead>"+
                        "<th>Product</th>"+
                        "<th>Price RON</th>"+
                        "<th>Add</th>"+
                        "<th>Remove</th>"+
                        "</thead>"+
                        "<tbody>"+
                        buffer +
                        "</tbody>" +
                        "</table>" +
                        "<div><a href=\"/another\">Cart</a></div>" +
                        "</body></html>"
        );

        String itemToAddInCart = request.getParameter("addItemInCart");
        String itemToRemoveFromCart = request.getParameter("removeItemFromCart");

        if(itemToAddInCart!=null){
            for(Item item: this.shopStock.getStockList()){
                if(item.getName().equals(itemToAddInCart)){
                    cart.addNewItem(item);
                }
            }
        }

        if(itemToRemoveFromCart!=null){
            for(Item item: this.shopStock.getStockList()){
                if(item.getName().equals(itemToAddInCart)){
                    cart.removeItem(item);
                }
            }
        }
    }

}
