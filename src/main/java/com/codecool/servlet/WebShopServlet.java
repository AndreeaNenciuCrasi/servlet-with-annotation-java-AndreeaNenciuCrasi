package com.codecool.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "webShopServlet", urlPatterns = {"/"}, loadOnStartup = 1)
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

    public void init(){
        initStock();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

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
        String title = "WebShop";

        out.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<table>" +
                        "<thead>"+
                        "<th><b>Product</b></th>"+
                        "<th><b>Price RON</b></th>"+
                        "<th><b>Add</b></th>"+
                        "<th><b>Remove</b></th>"+
                        "</thead>"+
                        "<tbody>"+
                        buffer +
                        "</tbody>" +
                        "</table>" +
                        "<div><a href=\"/itemsCart\"><h2>Cart</h2></a></div>" +
                        "<div><a href= \"/?checkout=true\"><button>Checkout</button></a></div>" +
                        "</body></html>"
        );


        String itemToAddInCart = request.getParameter("addItemInCart");
        String itemToRemoveFromCart = request.getParameter("removeItemFromCart");
        String checkout = request.getParameter("checkout");

        if(itemToAddInCart!=null){
            for(Item item: this.shopStock.getStockList()){
                if(item.getName().equals(itemToAddInCart)){
                    System.out.println(item);
                    cart.addNewItem(item);
                }
            }
        }

        if(itemToRemoveFromCart!=null){
            for(Item item: this.shopStock.getStockList()){
                if(item.getName().equals(itemToRemoveFromCart)){
                    cart.removeItem(item);
                }
            }
        }

        if(checkout != null){
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("/itemsCart").forward(request, response);
        }

    }

}
