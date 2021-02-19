package org.trang.test.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.trang.test.DBUtils.DBUser;

import org.trang.test.model.User;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/editUser":
				showEditUserForm(request, response);
				break;
			case "/updateUser":
				updateUser(request, response);
				break;
			case "/activeUser":
				activeUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String isActive=request.getParameter("is_active");
		String URI="http://localhost:8080/FoodOrderApp/rest/User";
        Client client=Client.create();  // thu vien jersey-client
        client.setFollowRedirects(Boolean.TRUE);
        WebResource resource=client.resource(URI);  // link duong dan den rest api
        
        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl(); 
        queryParams.add("id", id);
        queryParams.add("isActive", isActive);
        String res=resource.path("activeUser").queryParams(queryParams).put(String.class); // 
        Boolean rs= Boolean.parseBoolean(res);
        if(rs) {
        	    
        	request.getRequestDispatcher("/listUser").forward(request, response);
        	
        }
        else {
        	response.getWriter().append("active fail").append(request.getContextPath());
        }
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String sdt = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		User user=new User(id,email, firstName, lastName, sdt, address);
		//-----------------------------------------//
        
        String URI="http://localhost:8080/FoodOrderApp/rest/User";
        Client client=Client.create();  // thu vien jersey-client
        client.setFollowRedirects(Boolean.TRUE);
        WebResource resource=client.resource(URI);  // link duong dan den rest api
        
        
        String res=resource.path("updateUser").put(String.class,user); //   put(class<T>, object)
        Boolean rs= Boolean.parseBoolean(res);
        if(rs) {
        	    
        	request.getRequestDispatcher("/listUser").forward(request, response);
        	
        }
        else {
        	response.getWriter().append("Update fail").append(request.getContextPath());
        }
        
		
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException, IOException {
		// TODO Auto-generated method stub
		
		User user=(User) request.getSession().getAttribute("USER");
		int user_id = user.getUser_id();
		
		String URI = "http://localhost:8080/FoodOrderApp/rest/User";
		Client client = Client.create(); // thu vien jersey-client
		client.setFollowRedirects(Boolean.TRUE);
		WebResource resource = client.resource(URI); // link duong dan den rest api



		// ----------------list------------//
		GenericType<List<User>> gt = new GenericType<List<User>>() {
		};
		Collection<User> listUsers = resource.path("list/"+user_id).accept(MediaType.APPLICATION_XML_TYPE).get(gt);

		request.setAttribute("listUsers", listUsers);
		request.getRequestDispatcher("UserManager.jsp").forward(request, response);
	}

	private void showEditUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		User user=null;
		try {
			user = DBUser.getUserByID(id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("FormUser.jsp");
        dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
