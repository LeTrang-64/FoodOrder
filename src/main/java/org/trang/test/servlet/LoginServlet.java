package org.trang.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.trang.test.model.User;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginCheck")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		
		
		String password = encrypt(request.getParameter("password"));

		

		// -------------API---------------//

		String URI = "http://localhost:8080/FoodOrderApp/rest/User"; // xac dinh url den sevice
		Client client = Client.create(); // tao client object qua thu vien jersey-client
		client.setFollowRedirects(Boolean.TRUE);// Thiáº¿t láº­p truyá»�n yÃªu cáº§u trá»±c tiáº¿p Ä‘áº¿n Ä‘á»ƒ truy cáº­p resources thÃ´ng
												// qua phÆ°Æ¡ng thá»©c setFollowRedirecteds
		WebResource resource = client.resource(URI); // link duong dan den rest api

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl(); // xac dinh tap tham so truy cap resource
																				// voi nhieu tham so ta dung
																				// MulticaluedMap
		queryParams.add("email", email); // set params
		queryParams.add("password", password);

		// --------------Login---------------//
		GenericType<User> guser = new GenericType<User>() { // tao ra 1 kieu du lieu guser
		};
		User user=null;

        try {
        	user = resource.path("checkLogin").queryParams(queryParams).accept(MediaType.APPLICATION_XML_TYPE)
    				.get(guser); 
		} catch (Exception e) {
			
		}

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("USER", user);
			Boolean isManager = (user.getIsManager() == 1) ? true : false;
			Boolean isActive = (user.getIsActive() == 1) ? true : false;
			
			
			if (isManager) {	
				
				response.sendRedirect("AdminPage.jsp");
			} else {
				if(isActive)
				response.sendRedirect("Welcome.jsp");
				else {
					
					response.sendRedirect("LoginFail.jsp");
				}
			}

		} else {
			response.sendRedirect("LoginFail.jsp");
		}

	}
	//MD5
	public static String encrypt(String plaintext) {

        MessageDigest messageDigest = null;
        String hash=null;

        try{
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(plaintext.getBytes("UTF8"));
            byte[] raw = messageDigest.digest();
            hash = new String(Base64.encode(raw));


        }catch(Exception nsa){}

            return hash;

}
}
