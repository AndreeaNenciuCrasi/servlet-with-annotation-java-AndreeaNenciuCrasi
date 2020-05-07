package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "cartServlet", urlPatterns = {"/itemsCart"}, loadOnStartup = 1)
public class ShoppingCartServlet extends HttpServlet{
    private Cart cart;

    public ShoppingCartServlet() {
        this.cart = new Cart();
    }
}
