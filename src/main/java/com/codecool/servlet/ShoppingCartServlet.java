package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cartServlet", urlPatterns = {"/itemsCart"}, loadOnStartup = 1)
public class ShoppingCartServlet extends HttpServlet{
    private Cart cart;

    public ShoppingCartServlet() {
        this.cart = new Cart();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        double sumCart = 0;

        StringBuffer buffer = new StringBuffer();

        for (Item item : this.cart.getMyItems()) {
            buffer.append("<li>" + item.getName() + item.getPrice() + "</li>");
            sumCart +=item.getPrice();
            buffer.append("<li>" + sumCart + "</li>");
        }

        PrintWriter out = response.getWriter();
        String title = "Cart";

        out.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<ul>" +
                        buffer +
                        "</ul>" +
                        "<div>TOTAL Price" + sumCart +
                        "</div>" +
                        "<div><a href=\"/webShop\">Back to Items List</a></div>" +
                        "</body></html>"
        );
    }
}
