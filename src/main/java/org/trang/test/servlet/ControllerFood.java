package org.trang.test.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;



import org.trang.test.dao.DBFood;
import org.trang.test.model.Food;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;


/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 * 
 * @author www.codejava.net
 */

public class ControllerFood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();


		try {
			switch (action) {
			case "/newFood":
				showNewForm(request, response);
				break;
			case "/createFood":
				createFood(request, response);
				break;
			case "/deleteFood":
				deleteFood(request, response);
				break;
			case "/editFood":
				showEditForm(request, response);
				break;
			case "/updateFood":
				updateFood(request, response);
				break;
			default:
				listFood(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listFood(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String URI = "http://localhost:8080/FoodOrderApp/rest/Foods";
		Client client = Client.create(); // thu vien jersey-client
		client.setFollowRedirects(Boolean.TRUE);
		WebResource resource = client.resource(URI); // link duong dan den rest api

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();

		// ----------------list------------//
		GenericType<List<Food>> gt = new GenericType<List<Food>>() {
		};
		Collection<Food> listFoods = resource.path("list").queryParams(queryParams)
				.accept(MediaType.APPLICATION_XML_TYPE).get(gt);

		request.setAttribute("listFoods", listFoods);
		request.getRequestDispatcher("FoodManager.jsp").forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("FoodForm.jsp");
        dispatcher.forward(request, response);

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		Food food=null;
		try {
			food = DBFood.getFoodbyID(id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("FoodForm.jsp");
        request.setAttribute("food", food);
        dispatcher.forward(request, response);

	}

	private void createFood(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		    String name = request.getParameter("name");
	        float price = Float.parseFloat(request.getParameter("price"));
	        String img = request.getParameter("img");
	        String info = request.getParameter("info");
	        Food food=new Food(name,price,img,info);
	        
	        //-----------------------------------------//
	        
	        String URI="http://localhost:8080/FoodOrderApp/rest/Foods";
	        Client client=Client.create();  // thu vien jersey-client
	        client.setFollowRedirects(Boolean.TRUE);
	        WebResource resource=client.resource(URI);  // link duong dan den rest api
	        	       	        
	        String res=resource.path("create").post(String.class,food); //   post(class<T>, object)
	        Boolean rs= Boolean.parseBoolean(res);
	        if(rs) {	        	    
	        	request.getRequestDispatcher("/listFood").forward(request, response);
	        	
	        }
	        else {
	        	response.getWriter().append("Update fail").append(request.getContextPath());
	        }

	}

	private void updateFood(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		    int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        float price = Float.parseFloat(request.getParameter("price"));
	        String img = request.getParameter("img");
	        String info = request.getParameter("info");
	        Food food=new Food(id,name,price,img,info);
	        
	        //-----------------------------------------//
	        
	        String URI="http://localhost:8080/FoodOrderApp/rest/Foods";
	        Client client=Client.create();  // thu vien jersey-client
	        client.setFollowRedirects(Boolean.TRUE);
	        WebResource resource=client.resource(URI);  // link duong dan den rest api
	        	       	        
	        String res=resource.path("update").put(String.class,food); //   put(class<T>, object)
	        Boolean rs= Boolean.parseBoolean(res);
	        if(rs) {
	        	    
	        	request.getRequestDispatcher("/listFood").forward(request, response);
	        	
	        }
	        else {
	        	response.getWriter().append("Update fail").append(request.getContextPath());
	        }
	        

	}

	private void deleteFood(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String id =request.getParameter("id");
		
		String URI="http://localhost:8080/FoodOrderApp/rest/Foods";
        Client client=Client.create();  // thu vien jersey-client
        client.setFollowRedirects(Boolean.TRUE);
        WebResource resource=client.resource(URI);  // link duong dan den rest api
        
        resource.path("delete/"+id).delete();
        request.getRequestDispatcher("/listFood").forward(request, response);

	}
}