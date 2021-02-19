/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuong.test.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.trang.test.dao.FoodDAO;
import org.trang.test.model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "testt", urlPatterns = {"/addToCart"})
public class addToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();
    	String food_id = request.getParameter("submit");
 	    User user=(User) session.getAttribute("USER");
 	    if(user==null) {
 	    	response.sendRedirect("Login.jsp");
 	    	return;
 	    }
    	
 
        try {
            new FoodDAO().sendData(user.getUser_id(), Integer.parseInt(food_id));
            sleep( Long.MAX_VALUE);
        } catch (Exception ex) {
            System.out.println("haha super trick");
            throw new Error(ex);
        }
    	request.getRequestDispatcher("Welcome.jsp").forward(request, response);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
