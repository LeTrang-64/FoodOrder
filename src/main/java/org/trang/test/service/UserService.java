package org.trang.test.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.trang.test.DBUtils.DBUser;
import org.trang.test.dao.DBFood;
import org.trang.test.model.Food;
import org.trang.test.model.User;


@Path("/User")
public class UserService {
	
	
	// URI:
    // /contextPath/servletPath/User/list
	@Path("/list/{user_id}")
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsers_JSON(@PathParam("user_id") String user_id) throws ClassNotFoundException {
		List<User> listUser=DBUser.getAll(Integer.parseInt(user_id));	
        return listUser;

    }
	
	@Path("/checkLogin")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User checkLogin(@QueryParam("email") String email,@QueryParam("password") String password) throws ClassNotFoundException {
		User user=DBUser.checkLogin(email,password);
		return user;
				
	}
	@Path("/register")
	@POST
	@Produces("text/plain")
	 public String register(User user) throws  ClassNotFoundException {
        Boolean rs= DBUser.registerUser(user);
         if(rs) {
 			
 	        return "true";
 		}
 		return "False";
    }
	@Path("/updateUser")
	@PUT
	@Produces("text/plain")
	 public String updateUser(User user) throws  ClassNotFoundException {
        Boolean rs= DBUser.updateUser(user);
         if(rs) {
 			
 	        return "true";
 		}
 		return "False";
    }
	@Path("/activeUser")
	@PUT
	@Produces("text/plain")
	 public String upgradeUser(@QueryParam("id") String id, @QueryParam("isActive") String isActive) throws  ClassNotFoundException {
		int user_id=Integer.parseInt(id);
		int is_Active=Integer.parseInt(isActive);
		if(is_Active==1) {
			is_Active=0;
		}
		else is_Active=1;
        Boolean rs= DBUser.activeUser(user_id,is_Active);
         if(rs) {
 			
 	        return "true";
 		}
 		return "False";
    }
	
	

}
