package org.trang.test.servlet;

import java.io.IOException;
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
 * Servlet implementation class registerServlet
 */
@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = encrypt( request.getParameter("password"));
		String sdt = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		int isManager = 0;
		int isActive=1;

		User user = new User(email, password, firstName, lastName, sdt, address, isManager,isActive);

		// -------------API---------------//

		String URI = "http://localhost:8080/FoodOrderApp/rest/User"; // xac dinh url den sevice
		Client client = Client.create(); // tao client object qua thu vien jersey-client
		client.setFollowRedirects(Boolean.TRUE);// Thiáº¿t láº­p truyá»�n yÃªu cáº§u trá»±c tiáº¿p Ä‘áº¿n Ä‘á»ƒ truy cáº­p resources thÃ´ng
												// qua phÆ°Æ¡ng thá»©c setFollowRedirecteds
		WebResource resource = client.resource(URI); // link duong dan den rest api

		
		// --------------Register---------------//
		

		String result = resource.path("register").post(String.class,user); // xÃ¡c Ä‘á»‹nh path sub resource vá»›i phÆ°Æ¡ng thá»©c path
		// dÃ¹ng phÆ°Æ¡ng thá»©c get Ä‘á»ƒ láº¥y dá»¯ liá»‡u tráº£ vá»� vÃ  Ã©p vá»� kiá»ƒu chuáº©n client
        Boolean rs=Boolean.parseBoolean(result);
        HttpSession session = request.getSession();
		
		if (rs) {
			session.setAttribute("successMessage", "Register success");
			response.sendRedirect("Login.jsp");
		} else {
			session.setAttribute("errorMessage", "Error");
		}

	}

}
